package ca.cityofkingston.calendarevent;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import ca.cityofkingston.calendarevent.constants.CalendarEventPortletKeys;
import ca.cityofkingston.calendarevent.event.CalendarEvent;
import ca.cityofkingston.calendarevent.event.CalendarEventHandler;
import ca.cityofkingston.calendarevent.event.CalendarEventView;

/**
 * @author RST028
 */
@Component(
		configurationPid = "ca.cityofkingston.calendarevent.DemoConfiguration",
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=Kingston",
			"com.liferay.portlet.instanceable=false",
			"javax.portlet.display-name=Kingston Calendar Event Portlet",
			"com.liferay.portlet.header-portlet-javascript=/js/main.js",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/view.jsp",
			"javax.portlet.name=" + CalendarEventPortletKeys.CalendarEvent,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class CalendarEventPortlet extends MVCPortlet {
	
	public static final String CALENDAREVENT = "calendarevent";
	public static final String CAL_EVENTS = "cal_events";
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay currentThemeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = currentThemeDisplay.getScopeGroupId();
			User currentUser = currentThemeDisplay.getUser();

			TimeZone tz = currentUser.getTimeZone();
			
			Configuration configuration = makeConfiguration(renderRequest);
			
			if (configuration.getDisplayType().equals(ConfigurationActionImpl.DISPLAY_TYPE_PLAIN_LIST)) { 
				List<CalendarBooking> calEvents = CalendarBookingLocalServiceUtil.getCalendarBookings(-1, -1);
				renderRequest.setAttribute(CAL_EVENTS, calEvents);
				
			} else { 
				CalendarEventHandler calendarEventHandler = CalendarEventHandler.make(groupId, getCurrentDate(), configuration);
				List<List<CalendarEvent>> events = calendarEventHandler.getEvents();
				CalendarEventView view = CalendarEventView.make(events,tz);
				renderRequest.setAttribute(CALENDAREVENT, view);
			}
			
			super.doView(renderRequest, renderResponse);
		} catch (PortalException e) {
			throw new PortletException(e);
		} catch (SystemException e) {
			throw new PortletException(e);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		Configuration makeConfiguration(RenderRequest request) throws PortalException, SystemException {
		String portletInstanceId = (String) request.getAttribute(WebKeys.PORTLET_ID);
		PortletPreferences portletPreferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletInstanceId);
		return Configuration.make(portletPreferences);
	}
		private Date getCurrentDate() {
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(new Date());
		    calendar.set(Calendar.HOUR_OF_DAY, 0);
		    calendar.set(Calendar.MINUTE, 0);
		    calendar.set(Calendar.SECOND, 0);
		    calendar.set(Calendar.MILLISECOND, 0);
		    return calendar.getTime();
		}

	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		 _log.info("#####Calling activate() method  ######");
		_messageDisplayConfiguration = ConfigurableUtil.createConfigurable(DemoConfiguration.class, properties);
	}

	private static final Log _log = LogFactoryUtil.getLog(CalendarEventPortlet.class);
	private volatile DemoConfiguration _messageDisplayConfiguration;
}