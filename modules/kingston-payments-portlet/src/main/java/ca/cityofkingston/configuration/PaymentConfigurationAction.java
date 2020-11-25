package ca.cityofkingston.configuration;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import ca.cityofkingston.configuration.DemoConfiguration;
import ca.cityofkingston.payments.constants.PaymentsPortletKeys;


@Component(
		configurationPid = "ca.cityofkingston.configuration.DemoConfiguration",
		configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
		property = "javax.portlet.name=" + PaymentsPortletKeys.Payments,
		service = ConfigurationAction.class
	)
public class PaymentConfigurationAction extends DefaultConfigurationAction {

	public static final String PAYMENT_CONFIG_SUCCESS_MSG_KEY = "paymentConfigSuccess";

	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		PortletPreferences portletPreferences = getPreferences(actionRequest);
		
		PaymentConfiguration config = PaymentConfiguration.make(PortalUtil.getHttpServletRequest(actionRequest), portletPreferences);
		config.store();
		
		SessionMessages.add(actionRequest, PAYMENT_CONFIG_SUCCESS_MSG_KEY);
	}

	private PortletPreferences getPreferences(ActionRequest actionRequest)
			throws PortalException, SystemException {
		String portletResource = ParamUtil.getString(actionRequest,	PaymentConfiguration.PORTLET_RESOURCE);
		PortletPreferences portletPreferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
		return portletPreferences;
	}

	public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		return "/configuration.jsp";
	}	
	
	 @Activate
	    @Modified
	    protected void activate(Map<Object, Object> properties) {
	        _log.info("#####Calling activate() method of Payment Portlet######");
	        
	        _demoConfiguration = ConfigurableUtil.createConfigurable(DemoConfiguration.class, properties);
	    }

	    private static final Log _log = LogFactoryUtil.getLog(PaymentConfigurationAction.class);

	    private volatile DemoConfiguration _demoConfiguration;  

}