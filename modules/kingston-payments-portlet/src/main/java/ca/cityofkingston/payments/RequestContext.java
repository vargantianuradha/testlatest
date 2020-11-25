package ca.cityofkingston.payments;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.portlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import ca.cityofkingston.configuration.PaymentConfiguration;

public class RequestContext {

	private static final String PRODUCT_ID = "ProductId";
	private static final String PROPERTY_ASSESSMENT_PRODUCT_ID = "PAL2002";
	private static final String DEFAULT_GBIZ_SERVER_URL_INIT_PARAM = "default-gbiz-server-url";
	public static final String GBIZ_COOKIE_NAME = "ASP.NET_SessionId";
	public static final String VIEW_PARAM_NAME = "view";
	public static final String LANG_PARAM_NAME = "l";

	private PortletConfig portletConfig;
	
	private RenderRequest renderRequest;
	private RenderResponse renderResponse;
	
	private ActionRequest actionRequest;
	private String language;
	
	//FIXME: RV we need to determine this value at runtime
	// See http://stackoverflow.com/questions/15310945/how-liferays-portlets-name-are-generated
	static final String PORTLET_ID = PaymentsPortlet.PORTLET_ID; 
	

	public static RequestContext makeRenderContext(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) {
		return new RequestContext(portletConfig, renderRequest, renderResponse);
	}

	public static RequestContext makeActionContext(PortletConfig portletConfig, ActionRequest actionRequest) {
		return new RequestContext(portletConfig, actionRequest);
	}	
	
	public PortletSession getPortletSession() {
		return getPortletRequest().getPortletSession();
	}

	public String getParameter(String paramName) {
		return getPortletRequest().getParameter(paramName);
	}

	public String getInitParameter(String paramName) {    
		return portletConfig.getInitParameter(paramName);
	}

	public PortletURL createGetURL() {
		return PortletURLFactoryUtil.create(getHttpRequest(), getPortletId(), getThemeDisplay().getPlid(), PortletRequest.RENDER_PHASE);
	}

	private String getPortletId() {
		return getThemeDisplay().getPortletDisplay().getId();
	}

	private ThemeDisplay getThemeDisplay() {
		ThemeDisplay td = (ThemeDisplay)getPortletRequest().getAttribute(WebKeys.THEME_DISPLAY);
		return td;
	}		
	
	public PortletURL createActionURL() {
		return renderResponse != null ? renderResponse.createActionURL() : null;
	}

	public String getRequestedURL() {
		return (String) getPortletRequest().getAttribute(WebKeys.CURRENT_URL);
	}

	public Map<String, String[]> getRequestBody() {
		
		//-- Oct 23, 2013 - this code used actionRequest.getParameterMap() but that would be an error if actionRequest was null.
		//--              - replaced with the getPortletRequest which will return something.
		Set<Entry<String, String[]>> entrySet = getPortletRequest().getParameterMap().entrySet();
		
		Map<String, String[]> bodyParams = new HashMap<String, String[]>();
		
		Iterator<Entry<String, String[]>> iterator = entrySet.iterator();
		while(iterator.hasNext()) {
			
			Entry<String, String[]> entry = iterator.next();
			String paramName = cleanParamName(entry);
			bodyParams.put(paramName, entry.getValue());				
		}
		
		return bodyParams;
	}

	public View getViewFromRequest() {
		return (View) getPortletRequest().getAttribute(RequestContext.VIEW_PARAM_NAME);
	}
	
	public String getServerURL() {
		
		String serverURL = getPreferences().getValue(PaymentConfiguration.GBIZ_URL_PREF_KEY, "");
		if(StringUtils.isEmpty(serverURL)) {     
			serverURL =portletConfig.getInitParameter(DEFAULT_GBIZ_SERVER_URL_INIT_PARAM);
		}
		return serverURL;
	}
	
	public String getScriptDocRoot() {
		//FIXME: RV look this up at runtime
		return "/o/ca.cityofkingston.payments";
	}

	public String getHeader(String header) {
		return getHttpRequest().getHeader(header);
	}

	public Cookie getGbizSessionCookie() {
		return (Cookie) getPortletSession().getAttribute(GBIZ_COOKIE_NAME);
	}

	public String getSourceIPAddress() {
		return getHttpRequest().getRemoteAddr();
	}

	public boolean isMailEnabled() {
		return Boolean.valueOf(getPreferences().getValue(PaymentConfiguration.IS_MAIL_ENABLED_PREF_KEY, ""));
	}
	
	public PaymentConfiguration getPaymentConfiguration() {
		try {
			Map<String, String[]> mapData = getHttpRequest().getParameterMap();
			 
			return PaymentConfiguration.make(getHttpRequest(), getPreferences());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	private String cleanParamName(Entry<String, String[]> entry) {
		return entry.getKey().replace("amp;", "").replace(PORTLET_ID, "");
	}	

	private PortletPreferences getPreferences() {
		
		PortletRequest request = getPortletRequest();
		
		PortletPreferences portletPreferences;

		try {
			portletPreferences = PortletPreferencesFactoryUtil.getPortletSetup(request, (String) request.getAttribute(WebKeys.PORTLET_ID));
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return portletPreferences;
	}

	private PortletRequest getPortletRequest() {
		return isRenderRequest() ? renderRequest : actionRequest;
	}

	private boolean isRenderRequest() {
		return renderRequest != null;
	}
	
	private HttpServletRequest getHttpRequest() {
		return PortalUtil.getHttpServletRequest(getPortletRequest());
	}
	
	private RequestContext(PortletConfig portletConfig) {
		this.portletConfig = portletConfig;
	}	
	
	private RequestContext(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) {
		this(portletConfig);
		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;
	}
	
	private RequestContext(PortletConfig portletConfig, ActionRequest actionRequest) {
		this(portletConfig);
		this.actionRequest = actionRequest;
	}

	public boolean isPropertyLookup() {
		return PROPERTY_ASSESSMENT_PRODUCT_ID.equalsIgnoreCase(getParameter(PRODUCT_ID));
	}

	public String getLang() {
		
		return language;
	}
	
	public void setLang(String inLang) {
		
		language = inLang;
	}	
}
