package ca.cityofkingston.calendarevent.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.calendar.service.CalendarLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

public class LiferayUtils {
	private static final Log LOGGER = LogFactoryUtil.getLog(LiferayUtils.class);

	/**
	 * @param className
	 * @param classPK
	 * @return null if error or not found
	 * @throws SystemException 
	 */
	public AssetEntry getAssetEntry(String className, long classPK) throws SystemException {
		try {
			return AssetEntryLocalServiceUtil.getEntry(className, classPK);
		} catch (PortalException e) {
			LOGGER.error("Unable to get asset entry ["+className+":"+classPK+"]", e);
			return null;
		}
	}

	/**
	 * @param groupId
	 * @param calendar
	 * @return a list of cal events
	 * @throws SystemException
	 */
	public List<CalendarBooking> getEvents(long groupId, Date startDate, Date endDate) throws SystemException {
		
		List<com.liferay.calendar.model.Calendar> calendars = CalendarLocalServiceUtil.getCalendars(0,CalendarLocalServiceUtil.getCalendarsCount());
		List<CalendarBooking> allCalbookings = new ArrayList<CalendarBooking>();
		for(com.liferay.calendar.model.Calendar calendar: calendars){
			if(calendar.getGroupId()==groupId) { 
			List<CalendarBooking>  calbookings = CalendarBookingLocalServiceUtil.getCalendarBookings(calendar.getCalendarId());
			if(calbookings!=null && calbookings.size() > 0) {
				for(CalendarBooking calBooking : calbookings) { 
					Date newStartDate = new Date(calBooking.getStartTime());
					if(calBooking.isAllDay()){
						Calendar c =Calendar.getInstance();
						c.setTime(newStartDate);
						c.add(Calendar.HOUR,4);
						newStartDate = c.getTime();
					}
					if( (getStartTime(newStartDate.getTime()).compareTo(startDate)>=0 && getStartTime(newStartDate.getTime()).compareTo(endDate) <=0)){
						allCalbookings.add(calBooking);
					}
				}
			}
		  }
		}
		return allCalbookings;
	}
	
	public List<CalendarBooking> getAllPastEvents(long groupId, Date startDate, Date endDate) throws SystemException {
		long start = startDate.getTime();
		long end = endDate.getTime();
		Date startTime  = getStartTime(start);
		Date endTime = getStartTime(end);
		List<com.liferay.calendar.model.Calendar> calendars = CalendarLocalServiceUtil.getCalendars(0,CalendarLocalServiceUtil.getCalendarsCount());
		List<CalendarBooking> allCalbookings = new ArrayList<CalendarBooking>();
		for(com.liferay.calendar.model.Calendar calendar: calendars){
			if(calendar.getGroupId()==groupId) { 
			List<CalendarBooking>  calbookings = CalendarBookingLocalServiceUtil.getCalendarBookings(calendar.getCalendarId());
			if(calbookings!=null && calbookings.size() > 0) {
				for(CalendarBooking calBooking : calbookings) {
					if( (getStartTime(calBooking.getStartTime()).compareTo(startTime)>0 && getStartTime(calBooking.getStartTime()).compareTo(endTime) <0)){
						allCalbookings.add(calBooking);
					}
				}
			}
		  }
		}
		return allCalbookings;
	}
	
	private Date getStartTime(long convertDate) {
		Date date = new Date(convertDate);
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH);
	    int day = calendar.get(Calendar.DATE);
	    calendar.set(year, month, day, 0, 0, 0);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    return calendar.getTime();
	}
 
	/**
	 * 
	 * @param groupId
	 * @param name
	 * @return null if error or not found
	 * @throws SystemException
	 */
	public AssetTag getAssetTag(long groupId, String name) throws SystemException {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		try {
			return AssetTagLocalServiceUtil.getTag(groupId, name);
		} catch (PortalException e) {
			LOGGER.error("Unable to get asset tag ["+name+"]", e);
			return null;
		}
	}

	/**
	 * 
	 * @param groupId
	 * @param name
	 * @return null if not found
	 * @throws SystemException
	 */
	public AssetCategory getAssetCategory(long groupId, String name) throws SystemException {
		if (StringUtils.isBlank(name)) {
			return null;
		}

		DynamicQuery query = getAssetCategoryQuery(groupId, name);
		
		@SuppressWarnings("unchecked")
		List<AssetCategory> categories = AssetCategoryLocalServiceUtil.dynamicQuery(query);
		if (categories != null && categories.size() > 0) {
			return categories.get(0);
		}
		return null;
	}
	
	DynamicQuery getAssetCategoryQuery(long groupId, String name) {
		
		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();
		
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(AssetCategory.class, classLoader)
				.add(PropertyFactoryUtil.forName("name").eq(name))
				.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		
		return query;
	}
	
	

}
