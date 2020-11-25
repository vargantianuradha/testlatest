package ca.cityofkingston.portlet.airport.configuration;

import java.util.Map;

import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import ca.cityofkingston.portlet.airport.constants.KingstonAirportSchedulePortletKeys;


@Component(
		configurationPid = "ca.cityofkingston.portlet.airport.configuration.AirportConfiguration",
		configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
		property = "javax.portlet.name=" + KingstonAirportSchedulePortletKeys.KingstonAirportSchedule,
		service = ConfigurationAction.class
	)
public class AirportConfigurationAction extends DefaultConfigurationAction {

	
	public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		return "/html/schedule/config.jsp";
	}	
	
 	@Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        
        
        _airportConfiguration = ConfigurableUtil.createConfigurable(AirportConfiguration.class, properties);
    }

    private volatile AirportConfiguration _airportConfiguration;  
    
    private static final Log _log = LogFactoryUtil.getLog(AirportConfigurationAction.class);

}
