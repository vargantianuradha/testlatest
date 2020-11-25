package ca.cityofkingston.navigation;

import ca.cityofkingston.navigation.catalogue.NavigationCatalogue;
import ca.cityofkingston.navigation.catalogue.NavigationItem;
import ca.cityofkingston.navigation.catalogue.NavigationRequestVars;
import ca.cityofkingston.navigation.constants.NavigationPortletKeys;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.Validate;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author RST028
 */
@Component(
	configurationPid = "ca.cityofkingston.navigation.DemoConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Kingston",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Kingston Navigation Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.name=" + NavigationPortletKeys.Navigation,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class NavigationPortlet extends MVCPortlet {
	public static final String CURRENT_NAVIGATION = "currentNavigation";
	public static final String MAIN_NAVIGATION = "mainNavigation";

	@Override
	public void doView(RenderRequest request, RenderResponse response)  {
		try {
			ThemeDisplay themeDisplay = tileSelectable(request);
			//NavigationRequestVars vars = makeRequestVars(request, themeDisplay);
			
			HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
			Configuration configuration = makeConfiguration(request);
			populateNavigationCatalogue(request, themeDisplay, httpServletRequest, configuration);
			super.doView(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	Configuration makeConfiguration(RenderRequest request) throws PortalException, SystemException {
		String portletInstanceId = (String) request.getAttribute(WebKeys.PORTLET_ID);
		PortletPreferences portletPreferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletInstanceId);
		return Configuration.make(portletPreferences);
	}
 
	NavigationCatalogue populateNavigationCatalogue(RenderRequest request, ThemeDisplay themeDisplay, HttpServletRequest httpServletRequest, Configuration configuration) throws PortalException {
		NavigationCatalogue navigationCatalogue = NavigationCatalogue.make(themeDisplay.getLayout(),themeDisplay, httpServletRequest, configuration.getMainNavigationLevel());
		if (configuration.isDisplayMainNavigation()) {  
		request.setAttribute(MAIN_NAVIGATION, navigationCatalogue.getMainItem());
		}
		if (configuration.isDisplayCurrentNavigation()) {  
			request.setAttribute(CURRENT_NAVIGATION, navigationCatalogue.getCurrentItem());
		}
		return navigationCatalogue;
	}

	NavigationRequestVars makeRequestVars(RenderRequest request, ThemeDisplay themeDisplay) throws PortalException, SystemException {
		HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		return NavigationRequestVars.make(httpServletRequest, themeDisplay);
	}

	ThemeDisplay tileSelectable(RenderRequest request) {
		// FIXME We need Tiles Selectable set to true
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Validate.notNull(themeDisplay);
		themeDisplay.setTilesSelectable(true);
		return themeDisplay;
	}


	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		 _log.info("#####Calling activate() method  ######");
		_messageDisplayConfiguration = ConfigurableUtil.createConfigurable(DemoConfiguration.class, properties);
	}

	 private static final Log _log = LogFactoryUtil.getLog(NavigationPortlet.class);
	private volatile DemoConfiguration _messageDisplayConfiguration;
}