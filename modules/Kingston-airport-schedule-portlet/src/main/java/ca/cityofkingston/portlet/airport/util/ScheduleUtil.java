package ca.cityofkingston.portlet.airport.util;

import java.io.IOException;

import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import ca.cityofkingston.portlet.airport.util.ScheduleCacheItem;
import ca.cityofkingston.portlet.airport.model.Schedule;


public class ScheduleUtil {

	public static Schedule getSchedule(String inUrl)throws IOException {
		WebCacheItem wci = new ScheduleCacheItem(inUrl);

		String key = ScheduleUtil.class.getName()+ StringPool.PERIOD + HttpUtil.encodeURL(inUrl);
		try {
			return (Schedule)WebCachePoolUtil.get(key, wci);
		}
		catch (ClassCastException cce) {
			WebCachePoolUtil.remove(key);

			return (Schedule)WebCachePoolUtil.get(key, wci);
		}
	}
	
	public static String formatScheduleTime(String scheduleTime) {
		String result = scheduleTime;
		
		if (scheduleTime.length() == 4)
			result = scheduleTime.substring(0,2) + ":" + scheduleTime.substring(2);
		
		return result;
	}
	
	public static String formatEstimatedArrival(String scheduleTime) {
		String result = scheduleTime;
		
		//-- Format is expected to be YYYYMMDDHHMM
		if (scheduleTime.length() == 12)
			result = scheduleTime.substring(8,10) + ":" + scheduleTime.substring(10,12);
		
		return result;
	}
	
	public static boolean isKnownAirline(String airlineCode) {
		if (airlineCode.equals("AC"))
			return true;
		
		return false;
	}
	
}
