package ca.cityofkingston.navigation;

import java.io.IOException;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import ca.cityofkingston.navigation.constants.NavigationPortletKeys;

@Component(
		configurationPid = "ca.cityofkingston.navigation.DemoConfiguration",
		configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
		property = "javax.portlet.name=" + NavigationPortletKeys.Navigation,
		service = ConfigurationAction.class
	)

public class ConfigurationActionImpl extends DefaultConfigurationAction  {
	
	public static final String [] LEVELS = new String[] {"0","1","2","3","4","5","6","7","8","9"};
	public static final int MAIN_NAVIGATION_LEVEL_DEFAULT = 0;

	public static final String PORTLET_RESOURCE = "portletResource";
	public static final String MAIN_NAVIGATION_LEVEL = "mainNavigationLevel";
	public static final String DISPLAY_CURRENT_NAVIGATION = "displayCurrentNavigation";
	public static final String DISPLAY_MAIN_NAVIGATION = "displayMainNavigation";

	@Override
	public void processAction(PortletConfig config, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		if (!Constants.UPDATE.equals(ParamUtil.getString(actionRequest, Constants.CMD))) {
			return;
		}
		String portletResource = ParamUtil.getString(actionRequest,	PORTLET_RESOURCE);
		PortletPreferences portletPreferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
		store(portletPreferences, actionRequest);
		SessionMessages.add(actionRequest, config.getPortletName()+ ".doConfigure");
//		actionResponse.setRenderParameter("jspPage", "/configuration.jsp");
	}

	void store(PortletPreferences portletPreferences, ActionRequest actionRequest) throws ReadOnlyException, ValidatorException, IOException {
		portletPreferences.setValue(MAIN_NAVIGATION_LEVEL, actionRequest.getParameter(MAIN_NAVIGATION_LEVEL));
		portletPreferences.setValue(DISPLAY_CURRENT_NAVIGATION, actionRequest.getParameter(DISPLAY_CURRENT_NAVIGATION));
		portletPreferences.setValue(DISPLAY_MAIN_NAVIGATION, actionRequest.getParameter(DISPLAY_MAIN_NAVIGATION));
		portletPreferences.store();
	}

 
	public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		return "/configuration.jsp";
	}
	    @Activate
	    @Modified
	    protected void activate(Map<Object, Object> properties) {
	        _log.info("#####Calling activate() method######");
	        
	        _demoConfiguration = ConfigurableUtil.createConfigurable(DemoConfiguration.class, properties);
	    }

	    private static final Log _log = LogFactoryUtil.getLog(ConfigurationActionImpl.class);

	    private volatile DemoConfiguration _demoConfiguration;  
	    
	    
}

	