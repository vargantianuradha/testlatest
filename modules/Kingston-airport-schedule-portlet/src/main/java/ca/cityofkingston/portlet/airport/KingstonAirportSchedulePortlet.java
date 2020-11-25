package ca.cityofkingston.portlet.airport;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import ca.cityofkingston.portlet.airport.constants.KingstonAirportSchedulePortletKeys;

/**
 * @author RST021
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Kingston",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.header-portlet-javascript=/js/main.js",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Kingston airport schedule Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/schedule/view.jsp",
		"javax.portlet.init-param.config-template=/html/schedule/config.jsp",
		"javax.portlet.name=" + KingstonAirportSchedulePortletKeys.KingstonAirportSchedule,
		"javax.portlet.preferences=classpath:/META-INF/portlet-preferences/default-portlet-preferences.xml",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class KingstonAirportSchedulePortlet extends MVCPortlet {
	
	/**
     * @see MVCPortlet#MVCPortlet()
     */
    public KingstonAirportSchedulePortlet() {
        super();
    }
 
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)throws IOException, PortletException {
		//renderResponse.getCacheControl().setExpirationTime(30);
		//renderResponse.getCacheControl().setPublicScope(false);
		super.doView(renderRequest, renderResponse);
	}

}