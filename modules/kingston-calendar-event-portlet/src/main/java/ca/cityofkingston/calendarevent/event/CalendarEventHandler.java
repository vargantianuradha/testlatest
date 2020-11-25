package ca.cityofkingston.calendarevent.event;


import ca.cityofkingston.calendarevent.Configuration;
import ca.cityofkingston.calendarevent.predicate.PredicateBuilder;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.TimeZoneUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateUtils;

public class CalendarEventHandler {
	public static CalendarEventHandler make(long groupId, Date calendarDate, Configuration configuration) throws SystemException, ParseException{
		Validate.notNull(calendarDate);
		Validate.notNull(configuration);
		CalendarEventHandler result = new CalendarEventHandler();
		result.calendarDate = calendarDate;
		result.configuration = configuration;
		result.calendarLiferay = CalendarLiferay.make(groupId);
		result.predicateBuilder = PredicateBuilder.make(groupId, configuration);
		return result;
	}

	private Date calendarDate;
	private Configuration configuration;
	private CalendarLiferay calendarLiferay;
	private PredicateBuilder predicateBuilder;

	private CalendarEventHandler() {}

	public List<List<CalendarEvent>> getEvents() throws SystemException, ParseException {
		int daysForward = configuration.getDaysForward();
		
		List<CalendarEvent> calendarEvents = getEvents(daysForward);
		Predicate<CalendarEvent> predicate = predicateBuilder.getPredicate();
		Collection<CalendarEvent> filtered = Collections2.filter(calendarEvents, predicate);
		List<CalendarEvent> results = sort(filtered);
		int numItems = configuration.getNumberOfItemsPerPage();
		List<List<CalendarEvent>> pages = Lists.partition(results, numItems);
		return pages;
	}

	protected List<CalendarEvent> sort(Collection<CalendarEvent> filtered) {

		boolean isAscending = !configuration.isDisplayingPastEvents();
		configuration.isDisplayingPastEvents();

		List<CalendarEvent> results = Lists.newArrayList(filtered);
		Collections.sort(results, CalendarEvent.getEventComparator(isAscending));
		return results;
	}

	protected List<CalendarEvent> getEvents(int daysForward) throws SystemException, ParseException {

		List<CalendarEvent> calendarEvents = new ArrayList<CalendarEvent>();
		if(configuration.isDisplayingPastEvents()) { 
			calendarEvents.addAll(getPastEvents(calendarDate));
		}  
		//for (int i = 0; i < daysForward; i++) {
			Date endDate = DateUtils.addDays(calendarDate, daysForward);
			calendarEvents.addAll(calendarLiferay.getCalendarEvents(calendarDate, endDate));
	//	}
		return calendarEvents;
	}

	List<CalendarEvent> getPastEvents(Date todayDate) throws SystemException {

		List<CalendarEvent> calendarEvents = new ArrayList<CalendarEvent>();

		Date currentDate = getCalendarEventEpoch();
		//while(!DateUtils.isSameDay(currentDate, todayDate)) { 
			calendarEvents.addAll(calendarLiferay.getCalendarPastEvents(currentDate, todayDate));
		//	currentDate = DateUtils.addDays(currentDate, 1);
		//}
		return calendarEvents;
	}

	Date getCalendarEventEpoch() {
		Calendar c = Calendar.getInstance(TimeZoneUtil.getDefault());
		c.setTimeInMillis(0);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.YEAR, 2012);
		c.set(Calendar.HOUR_OF_DAY, 0);

		return c.getTime();
	}

	void setPredicateBuilder(PredicateBuilder predicateBuilder) {
		Validate.notNull(predicateBuilder);
		this.predicateBuilder = predicateBuilder;
	}

	void setCalendarLiferay(CalendarLiferay calendarLiferay) {
		Validate.notNull(calendarLiferay);
		this.calendarLiferay = calendarLiferay;
	}
}