package ca.cityofkingston.calendarevent.event;


import ca.cityofkingston.calendarevent.ConfigurationActionImpl;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.util.JCalendarUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.TimeZone;

import org.apache.commons.lang3.Validate;

public class CalendarEventView {
	public static final String EMPTY_LINK_HREF = "javascript:void(0);";
	RenderRequest renderRequest;
	public static CalendarEventView make(List<List<CalendarEvent>> eventPages, TimeZone tz) {
		Validate.notNull(eventPages);
		CalendarEventView result = new CalendarEventView(tz);
		result.eventPages = eventPages;
		return result;
	}

	private List<List<CalendarEvent>> eventPages = new ArrayList<List<CalendarEvent>>();
	private SimpleDateFormat eventFormat;
	private TimeZone viewTZ;
	private SimpleDateFormat eventMonthFormat, eventDayFormat ;
	
	private CalendarEventView(TimeZone tz) {
		eventMonthFormat = new SimpleDateFormat("MMM");
		eventDayFormat = new SimpleDateFormat("dd");
		eventFormat = new SimpleDateFormat("EEE, MMMM dd, yyyy hh:mm a");
		eventFormat.setTimeZone(tz);
		eventDayFormat.setTimeZone(tz);
		eventMonthFormat.setTimeZone(tz);
		viewTZ = tz;
	}

	public List<List<CalendarEvent>> getEvents() {
		return eventPages;
	}

	public String buildEventDescription(CalendarEvent event, String calendarLayoutFriendlyURL, HttpServletRequest request, boolean showEventStatus) {
		CalendarBooking calEvent = event.getCalendarBooking();
		java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(calEvent.getStartTime(), viewTZ);
		return "<div class='calendar-event-full-description'>"
				+ showStatus(showEventStatus, calEvent)
				+ "<div class='event-details'>"
				+ "<div class='event-details-title'>Start Date:</div>"
				+ "<div class='event-details-content'>"+ eventFormat.format(startTimeJCalendar.getTime()) + "</div>"
				+ "</div>"
				+ "<div class='event-details'>"
				+ "<div class='event-details-title'>Location:</div>"
				+ "<div class='event-details-content'>"+ calEvent.getLocation() + "</div>"
				+ "</div>"
				+ "<div class='event-details-title'>Description:</div>"
				+ HtmlUtil.extractText(calEvent.getDescription())
				+ getDetailsLink(event, calendarLayoutFriendlyURL, request)
			+ "</div>";
	}

	public String showStatus(boolean showEventStatus, CalendarBooking calEvent) {
		if (showEventStatus){
			return "<div class='event-details'>"
					+ "<div class='event-details-title'>Status:</div>"
					+ "<div class='event-details-content'>"+ getEventStatus(calEvent) + "</div>"
					+ "</div>";
		}

		return "";
	}

	public String getEventStatus(CalendarBooking calEvent) {
		if (calEvent.getExpandoBridge().hasAttribute(ConfigurationActionImpl.SHOW_STATUS_KEY)){
			Object statusObject = (Object)calEvent.getExpandoBridge().getAttribute(ConfigurationActionImpl.SHOW_STATUS_KEY, false);
			String [] statusValue = (String[])statusObject;
			return (statusValue == null || statusValue.length == 0 ) ?  StringPool.BLANK : statusValue[0];
		}
		return "";
	}
	public String buildEventAbstract(CalendarBooking event) {
		return "<div class='calendar-event-abstract'>"
				+ HtmlUtil.extractText(event.getDescription())
				+ "</div>";
	}

	public String buildEventTitle(CalendarBooking event,String title) {
		return buildEventTitle(event, null, null,title);
	}

	public String buildEventTitle(CalendarBooking event, String calendarLayoutFriendlyURL, HttpServletRequest request, String title) {
		return "<div class=\"calendar-event-content column\"><div class=\"calendar-event-title\"><h3>"
					+"<a href='" + getEventRenderURL(event, calendarLayoutFriendlyURL, request) + "'>"
					+ title
				+ "</a></h3></div>";
	}
	

	public String buildEventTitleSimple(CalendarBooking event, String calendarLayoutFriendlyURL, HttpServletRequest request ) {
		return "<span>"+eventFormat.format(event.getStartTime()) +"</span>" +
	      "<span style='padding-left: 10px'><a href='"+ getEventRenderURL(event, calendarLayoutFriendlyURL, request) + "'>"
				+ event.getTitle(request.getLocale()) +"</a></span>";
	}

	public String buildEventIcon(CalendarEvent event) {
		return buildEventIcon(event, null, null);
	}


	public String buildEventIcon(CalendarEvent event, String calendarLayoutFriendlyURL, HttpServletRequest request) {
		CalendarBooking calEvent = event.getCalendarBooking();
	     if(calEvent.isAllDay()){
			Date date = new Date(calEvent.getStartTime());
			 Calendar calendar = Calendar.getInstance(viewTZ);
			 calendar.setTime(date);
			 //calendar.add(Calendar.DAY_OF_MONTH, 1);
			 calendar.set(Calendar.HOUR_OF_DAY, 0);
			 calEvent.setStartTime(calendar.getTimeInMillis());
		}
		java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(calEvent.getStartTime(), viewTZ);
		StringBuilder sb = new StringBuilder();
		sb.append("<div class='calendar-event-date-box column grid_6'>"
						+"<a href='" + getEventRenderURL(event.getCalendarBooking(), calendarLayoutFriendlyURL, request) + "'>"
						+ eventMonthFormat.format(startTimeJCalendar.getTime())	+ "<br/>" + eventDayFormat.format(startTimeJCalendar.getTime()) );

		if (event.isDailyRecurrence()) {
			sb.append(" - " + event.getEndDay(viewTZ));
		}
		sb.append("</a></div>");

		return sb.toString();
	}

	public String isFirstOrLastEvent(int i, int j) {
		if (eventPages.get(i).size() == 1){
			return "first-calendar-event last-calendar-event";
		}
		else if (j == (eventPages.get(i).size() - 1)) {
			return "last-calendar-event";
		}
		else if (j == 0) {
			return "first-calendar-event";
		}
		return "";
	}

	public String getDetailsLink(CalendarEvent calEvent, String calendarLayoutFriendlyURL, HttpServletRequest request) {
		return "<div class='event-details-event-link align-right'><a class='kingston-link rounded-button-link' href='"
				+ getEventRenderURL(calEvent.getCalendarBooking(), calendarLayoutFriendlyURL, request)
				+ "' aria-label='View details for "+ calEvent.getStartMonth(viewTZ) + " " + calEvent.getStartDay(viewTZ) + " " + calEvent.getCalendarBooking().getTitle(request.getLocale()) +"'>View Details</a></div>";
	}


	protected String getEventRenderURL(CalendarBooking calEvent, String calendarLayoutFriendlyURL, HttpServletRequest request)
	{
		if (calendarLayoutFriendlyURL == null && request == null){
			return EMPTY_LINK_HREF;
		}
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		
	    try {
	    	
	    	Layout l=LayoutLocalServiceUtil.getFriendlyURLLayout(10180, false, "/residents/city-calendar-events");
			LayoutTypePortlet articleLayoutTypePortlet = (LayoutTypePortlet)l.getLayoutType(); 
			List<String> allPortlets = articleLayoutTypePortlet.getPortletIds();
			String instanceId="";
			for (String p: allPortlets) 
			{ 
				if(p.contains("CalendarPortlet_INSTANCE")){
					
							String a[] = p.split("CalendarPortlet_INSTANCE_");
							instanceId=  a[1]; 
				}
			
			}
	    //	Layout previewPageLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroup().getGroupId(), false, calendarLayoutFriendlyURL);
	    //	PortletURL calEventURL = PortletURLFactoryUtil.create(request, PortletKeys.CALENDAR, previewPageLayout.getPlid(), PortletRequest.RENDER_PHASE);
	    //	calEventURL.setParameter("struts_action", "/calendar/view_event");
	    //	calEventURL.setParameter("eventId", String.valueOf(calEvent.getEventId()));
	    //	calEventURL.setParameter("redirect", themeDisplay.getURLCurrent());
//	    	String calEventURL = calendarLayoutFriendlyURL + StringPool.QUESTION + "p_p_id=8&_8_struts_action=/calendar/view_event&_8_eventId="+calEvent.getCalendarBookingId();
	    	
	    	String calEventURL = calendarLayoutFriendlyURL +  "/-/calendar/"+instanceId+"/event/"+calEvent.getCalendarBookingId();
	    	return calEventURL;
	    } catch (Exception e) {
	    	return themeDisplay.getScopeGroup().getFriendlyURL();
	    }
	}

}