package ca.cityofkingston.calendarevent.event;


import ca.cityofkingston.calendarevent.utils.LiferayUtils;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.TimeZoneUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.Validate;

public class CalendarLiferay {
	static final String CalEventClassName = CalendarBooking.class.getName();
	
	private LiferayUtils liferayUtils;
	private long groupId;

	public static CalendarLiferay make(long groupId) {
		CalendarLiferay result = new CalendarLiferay();
		result.groupId = groupId;
		result.setLiferayUtils(new LiferayUtils());
		
		return result;
	}
	
	private CalendarLiferay() {}

	public List<CalendarEvent> getCalendarEvents(Date startDate, Date endDate) throws SystemException, ParseException {
		List<CalendarEvent> results = new ArrayList<CalendarEvent>();
		List<CalendarBooking> calEvents = liferayUtils.getEvents(groupId, startDate, endDate);
		for (CalendarBooking calEvent : calEvents) {
			AssetEntry assetEntry = liferayUtils.getAssetEntry(CalEventClassName, calEvent.getCalendarBookingId());
			CalendarEvent calendarEvent = CalendarEvent.make(calEvent.getStartTime(), calEvent, assetEntry);
			results.add(calendarEvent);
		}
		return results;
	}
	public List<CalendarEvent> getCalendarPastEvents(Date startDate, Date endDate) throws SystemException {
		List<CalendarEvent> results = new ArrayList<CalendarEvent>();
		List<CalendarBooking> calEvents = liferayUtils.getAllPastEvents(groupId, startDate, endDate);
		for (CalendarBooking calEvent : calEvents) {
			AssetEntry assetEntry = liferayUtils.getAssetEntry(CalEventClassName, calEvent.getCalendarBookingId());
			CalendarEvent calendarEvent = CalendarEvent.make(calEvent.getStartTime(), calEvent, assetEntry);
			results.add(calendarEvent);
		}
		return results;
	}
	
	public void setLiferayUtils(LiferayUtils liferayUtils) {
		Validate.notNull(liferayUtils);
		this.liferayUtils = liferayUtils;
	}
	
}