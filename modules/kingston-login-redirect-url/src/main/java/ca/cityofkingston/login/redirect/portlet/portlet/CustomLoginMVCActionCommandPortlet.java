package ca.cityofkingston.login.redirect.portlet.portlet;

import ca.cityofkingston.login.redirect.portlet.constants.CustomLoginMVCActionCommandPortletKeys;

import com.liferay.portal.kernel.exception.CompanyMaxUsersException;
import com.liferay.portal.kernel.exception.CookieNotSupportedException;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PasswordExpiredException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.exception.UserIdException;
import com.liferay.portal.kernel.exception.UserLockoutException;
import com.liferay.portal.kernel.exception.UserPasswordException;
import com.liferay.portal.kernel.exception.UserScreenNameException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManager;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author RST028
 */
@Component(
		immediate = true,
		property = {
		"javax.portlet.name=" + CustomLoginMVCActionCommandPortletKeys.FAST_LOGIN,
		"javax.portlet.name=" + CustomLoginMVCActionCommandPortletKeys.LOGIN,
		"mvc.command.name=/login/login",
		"service.ranking:Integer=1100"
	},
	service = MVCActionCommand.class
)
public class CustomLoginMVCActionCommandPortlet extends BaseMVCActionCommand {

	private static final Log _log;
	@Reference
	private AuthenticatedSessionManager _authenticatedSessionManager;
	@Reference
	private Portal _portal;

	protected void doProcessAction(final ActionRequest actionRequest, final ActionResponse actionResponse) throws Exception {
		final ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
		boolean AUTH_LOGIN_DISABLED_FLAG = Boolean.FALSE;

		if(PropsKeys.AUTH_LOGIN_DISABLED.equals("true")) {
			AUTH_LOGIN_DISABLED_FLAG = Boolean.TRUE;
		}

		if (AUTH_LOGIN_DISABLED_FLAG) {
			actionResponse.sendRedirect(themeDisplay.getPathMain() + PropsKeys.AUTH_LOGIN_DISABLED_PATH);
			return;
		}
		try {
			this.login(themeDisplay, actionRequest, actionResponse);
			final boolean doActionAfterLogin = ParamUtil.getBoolean((PortletRequest)actionRequest, "doActionAfterLogin");
			if (doActionAfterLogin) {
				final LiferayPortletResponse liferayPortletResponse = this._portal.getLiferayPortletResponse((PortletResponse)actionResponse);
				final PortletURL renderURL = liferayPortletResponse.createRenderURL();
				renderURL.setParameter("mvcRenderCommandName", "/login/login_redirect");
				actionRequest.setAttribute("REDIRECT", (Object)renderURL.toString());
			}
		}
		catch (Exception e) {
			if (e instanceof AuthException) {
				final Throwable cause = e.getCause();
				if (cause instanceof PasswordExpiredException || cause instanceof UserLockoutException) {
					SessionErrors.add((PortletRequest)actionRequest, (Class)cause.getClass(), (Object)cause);
				}
				else {
					if (_log.isInfoEnabled()) {
						_log.info((Object)"Authentication failed");
					}
					SessionErrors.add((PortletRequest)actionRequest, (Class)e.getClass());
				}
			}
			else {
				if (!(e instanceof CompanyMaxUsersException) && !(e instanceof CookieNotSupportedException) && !(e instanceof NoSuchUserException) && !(e instanceof PasswordExpiredException) && !(e instanceof UserEmailAddressException) && !(e instanceof UserIdException) && !(e instanceof UserLockoutException) && !(e instanceof UserPasswordException) && !(e instanceof UserScreenNameException)) {
					_log.error((Object)e, (Throwable)e);
					this._portal.sendError(e, actionRequest, actionResponse);
					return;
				}
				SessionErrors.add((PortletRequest)actionRequest, (Class)e.getClass(), (Object)e);
			}
			this.postProcessAuthFailure(actionRequest, actionResponse);
			this.hideDefaultErrorMessage((PortletRequest)actionRequest);
		}
	}

	protected String getCompleteRedirectURL(final HttpServletRequest httpServletRequest, final String redirect) {
		final HttpSession session = httpServletRequest.getSession();
		final Boolean httpsInitial = (Boolean)session.getAttribute("HTTPS_INITIAL");
		String portalURL = null;
		boolean COMPANY_SECURITY_AUTH_REQUIRES_HTTPS_FLAG = Boolean.FALSE;
		if(PropsKeys.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS.equals("true")) {
			COMPANY_SECURITY_AUTH_REQUIRES_HTTPS_FLAG = Boolean.TRUE;
		}
		boolean SESSION_ENABLE_PHISHING_PROTECTION_FLAG = Boolean.FALSE;
		if(PropsKeys.SESSION_ENABLE_PHISHING_PROTECTION.equals("true")) {
			SESSION_ENABLE_PHISHING_PROTECTION_FLAG = Boolean.TRUE;
		}
		if (COMPANY_SECURITY_AUTH_REQUIRES_HTTPS_FLAG 
				&& !SESSION_ENABLE_PHISHING_PROTECTION_FLAG 
				&& httpsInitial != null 
				&& !httpsInitial) {
			portalURL = this._portal.getPortalURL(httpServletRequest, false);
		}
		else {
			portalURL = this._portal.getPortalURL(httpServletRequest);
		}
		return portalURL.concat(redirect);
	}

	protected void login(final ThemeDisplay themeDisplay, final ActionRequest actionRequest, final ActionResponse actionResponse) throws Exception {
		Long userId = 0l;
		final HttpServletRequest httpServletRequest = this._portal.getOriginalServletRequest(this._portal.getHttpServletRequest((PortletRequest)actionRequest));
		if (!themeDisplay.isSignedIn()) {
			final HttpServletResponse httpServletResponse = this._portal.getHttpServletResponse((PortletResponse)actionResponse);
			final String login = ParamUtil.getString((PortletRequest)actionRequest, "login");
			final String password = actionRequest.getParameter("password");
			final boolean rememberMe = ParamUtil.getBoolean((PortletRequest)actionRequest, "rememberMe");
			final String portletId = this._portal.getPortletId((PortletRequest)actionRequest);
			final PortletPreferences portletPreferences = PortletPreferencesFactoryUtil.getStrictPortletSetup(themeDisplay.getLayout(), portletId);
			final String authType = portletPreferences.getValue("authType", (String)null);
			this._authenticatedSessionManager.login(httpServletRequest, httpServletResponse, login, password, rememberMe, authType);
			userId = _authenticatedSessionManager.getAuthenticatedUserId(httpServletRequest, login, password, authType);
		}
		_log.info("AuthenticatedUserId :"+userId);
		String redirect = ParamUtil.getString((PortletRequest)actionRequest, "redirect");
		if (Validator.isNotNull(redirect)) {
			if (!themeDisplay.isSignedIn() && userId == 0l) {
				final LiferayPortletResponse liferayPortletResponse = this._portal.getLiferayPortletResponse((PortletResponse)actionResponse);
				final String portletId2 = this._portal.getPortletId((PortletRequest)actionRequest);
				final PortletURL actionURL = (PortletURL)liferayPortletResponse.createActionURL(portletId2);
				actionURL.setParameter("javax.portlet.action", "/login/login");
				actionURL.setParameter("saveLastPath", Boolean.FALSE.toString());
				actionURL.setParameter("redirect", redirect);
				actionRequest.setAttribute("REDIRECT", (Object)actionURL.toString());
				return;
			}
			redirect = this._portal.escapeRedirect(redirect);
			if (Validator.isNotNull(redirect) && !redirect.startsWith("http")) {
				redirect = this.getCompleteRedirectURL(httpServletRequest, redirect);
			}
		}
		final String mainPath = themeDisplay.getPathMain();

		boolean PORTAL_JAAS_ENABLE_FLAG = Boolean.FALSE;
		if(PropsKeys.PORTAL_JAAS_ENABLE.equals("true")) {
			PORTAL_JAAS_ENABLE_FLAG = Boolean.TRUE;
		}

		if (PORTAL_JAAS_ENABLE_FLAG) {
			if (Validator.isNotNull(redirect)) {
				redirect = mainPath.concat("/portal/protected?redirect=").concat(URLCodec.encodeURL(redirect));
			}
			else {
				redirect = mainPath.concat("/portal/protected");
			}
			actionResponse.sendRedirect(redirect);
		}
		else if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
		else {
			final boolean doActionAfterLogin = ParamUtil.getBoolean((PortletRequest)actionRequest, "doActionAfterLogin");
			if (doActionAfterLogin) {
				return;
			}
			actionResponse.sendRedirect(mainPath);
		}
	}

	protected void postProcessAuthFailure(final ActionRequest actionRequest, final ActionResponse actionResponse) throws Exception {
		final LiferayPortletRequest liferayPortletRequest = this._portal.getLiferayPortletRequest((PortletRequest)actionRequest);
		final String portletName = liferayPortletRequest.getPortletName();
		final Layout layout = (Layout)actionRequest.getAttribute("LAYOUT");
		final PortletURL portletURL = (PortletURL)PortletURLFactoryUtil.create((PortletRequest)actionRequest, liferayPortletRequest.getPortletName(), layout, "RENDER_PHASE");
		portletURL.setParameter("saveLastPath", Boolean.FALSE.toString());
		final String redirect = ParamUtil.getString((PortletRequest)actionRequest, "redirect");
		if (Validator.isNotNull(redirect)) {
			portletURL.setParameter("redirect", redirect);
		}
		final String login = ParamUtil.getString((PortletRequest)actionRequest, "login");
		if (Validator.isNotNull(login)) {
			SessionErrors.add((PortletRequest)actionRequest, "login", (Object)login);
		}
		if (portletName.equals("com_liferay_login_web_portlet_LoginPortlet")) {
			portletURL.setWindowState(WindowState.MAXIMIZED);
		}
		else {
			portletURL.setWindowState(actionRequest.getWindowState());
		}
		actionResponse.sendRedirect(portletURL.toString());
	}

	static {
		_log = LogFactoryUtil.getLog((Class)CustomLoginMVCActionCommandPortlet.class);
	}
}