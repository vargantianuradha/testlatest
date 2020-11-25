package ca.cityofkingston.calendarevent.predicate;

import ca.cityofkingston.calendarevent.event.CalendarEvent;

import com.google.common.base.Predicate;

import java.util.Set;
import java.util.TreeSet;

public class DailyRecurrencePredicate implements Predicate<CalendarEvent> {
	public static DailyRecurrencePredicate make() {
		return new DailyRecurrencePredicate();
	}
	
	private Set<CalendarEvent> dailyEvents = new TreeSet<CalendarEvent>();
	
	private DailyRecurrencePredicate() {}

	@Override
	public boolean apply(CalendarEvent calendarEvent) {
		if (!calendarEvent.isDailyRecurrence()) {
			return true;
		}
		if (!dailyEvents.contains(calendarEvent)) {
			dailyEvents.add(calendarEvent);
			return true;
		}
		return false;
	}
}