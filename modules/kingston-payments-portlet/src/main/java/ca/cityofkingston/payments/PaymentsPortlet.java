package ca.cityofkingston.payments;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import ca.cityofkingston.configuration.DemoConfiguration;
import ca.cityofkingston.payments.constants.PaymentsPortletKeys;

/**
 * @author RST028
 */
@Component(
		configurationPid = "ca.cityofkingston.configuration.DemoConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Kingston",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Kingston Online Payments",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-jsp=/view.jsp",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.init-param.default-gbiz-server-url=https://kingnet.cityofkingston.ca/gbizstore",
		"javax.portlet.name=" + PaymentsPortletKeys.Payments,
		"javax.portlet.resource-bundle=content.Language",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.requires-namespaced-parameters=false"
	},
	service = Portlet.class
)
public class PaymentsPortlet extends MVCPortlet {
	
	private static Log LOGGER = LogFactoryUtil.getLog(PaymentsPortlet.class);
	static String PORTLET_ID = ""; 
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String portlet_Id = themeDisplay.getPortletDisplay().getId();
		PORTLET_ID = "_"+portlet_Id+"_";
		LOGGER.debug("Doing view phase");
		//-- Given the environment/incoming parameters, make a normalized incoming request context.
		RequestContext request = RequestContext.makeRenderContext(getPortletConfig(), renderRequest, renderResponse);
		//-- Build the appropriate request for calling gBiz
		PaymentRequest gBizRequest = PaymentRequest.makeGetRequest(request);
		//-- Send the request and read the response from gBiz
		View view = gBizRequest.send();
		renderRequest.setAttribute("view", view);
		renderRequest.getPortletSession().setAttribute(RequestContext.GBIZ_COOKIE_NAME, view.getCookie());
		include(view.getPath(), renderRequest, renderResponse);
//		super.doView(renderRequest, renderResponse);
	}
	 
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String portlet_Id = themeDisplay.getPortletDisplay().getId();
		PORTLET_ID = "_"+portlet_Id+"_";
		LOGGER.debug("Doing process action phase");
		//-- Given the environment/incoming parameters, make a normalized incoming request context.
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(actionRequest);
		
		Map<String, String[]> mapData = httpRequest.getParameterMap();
		Enumeration<String> enumParam = actionRequest.getAttributeNames();
		RequestContext incomingRequest = RequestContext.makeActionContext(getPortletConfig(), actionRequest);
			mapData = incomingRequest.getRequestBody();
		//-- Build the appropriate request for calling gBiz
		PaymentRequest gBizRequest = PaymentRequest.makePostRequest(incomingRequest);
		
		//-- Present the response to the user.
		View view = gBizRequest.send();
		
		//-- store the respone and complete the processAction phase
		actionRequest.setAttribute("view", view);
		super.processAction(actionRequest, actionResponse);
	}
	
	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		 _log.info("#####Calling activate() method of Payment Portlet ######");
		_messageDisplayConfiguration = ConfigurableUtil.createConfigurable(DemoConfiguration.class, properties);
	}

	 private static final Log _log = LogFactoryUtil.getLog(PaymentsPortlet.class);
	private volatile DemoConfiguration _messageDisplayConfiguration;
}