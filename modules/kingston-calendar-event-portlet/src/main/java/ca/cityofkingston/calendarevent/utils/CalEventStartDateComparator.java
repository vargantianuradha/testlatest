package ca.cityofkingston.calendarevent.utils;


import java.util.Comparator;
import java.util.Date;

import com.liferay.calendar.model.CalendarBooking;

public class CalEventStartDateComparator implements Comparator<CalendarBooking> {
//    @Override
	
	public CalEventStartDateComparator() {
	}
	@Override
    public int compare(CalendarBooking o1, CalendarBooking o2) {  
    
    long d1= o1.getStartTime();
    	long d2=  o2.getStartTime();
        return 0;
    }
}
