package ca.cityofkingston.calendarevent.event;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.TimeZoneUtil;

import ca.cityofkingston.calendarevent.ConfigurationActionImpl;

public class CalendarEvent implements Comparable<CalendarEvent> {
	private static final Log LOGGER = LogFactoryUtil.getLog(CalendarEvent.class);

	public static final int DAILY_RECURRENCE_VALUE = 3;
	public static final Format MONTH_FORMAT = new SimpleDateFormat("MMM");
	public static final Format DAY_FORMAT = new SimpleDateFormat("d");

	private Date calendarDate;
	private CalendarBooking calendarBooking;
	private AssetEntry assetEntry;
	private String eventStatus = StringUtils.EMPTY;
	private Integer status;
	

	public static CalendarEvent make(long calendarDate, CalendarBooking calendarBooking, AssetEntry assetEntry) {
		Validate.notNull(calendarDate);
		Validate.notNull(calendarBooking);
		Date calDate = new Date(calendarDate);
		CalendarEvent result = new CalendarEvent();
		result.calendarDate = calDate;
		result.calendarBooking = calendarBooking;
		result.assetEntry = assetEntry;
		result.status = calendarBooking.getStatus();
		if (calendarBooking.getExpandoBridge().hasAttribute(ConfigurationActionImpl.SHOW_STATUS_KEY)){
			result.eventStatus = calendarBooking.getExpandoBridge().getAttribute(ConfigurationActionImpl.SHOW_STATUS_KEY, false).toString();
		}
		return result;
	}

	private CalendarEvent() {}

	public long getCalendarBookingId() {
		return calendarBooking.getCalendarBookingId();
	}

	public Date getCalendarDate() {
		return calendarDate;
	}

	public CalendarBooking getCalendarBooking() {
		return calendarBooking;
	}

	public AssetEntry getAssetEntry() {
		return assetEntry;
	}

	public String getEventStatus() {
		return eventStatus;
	}
	
	public Integer getStatus() {
		return status;
	}

	public List<AssetCategory> getAssetCategories() {
		if (assetEntry == null) {
			return new ArrayList<AssetCategory>();
		}
		try {
			return assetEntry.getCategories();
		} catch (SystemException e) {
			LOGGER.error("Can not get catgeories!", e);
			return new ArrayList<AssetCategory>();
		}
	}

	public List<AssetTag> getAssetTags() {
		if (assetEntry == null) {
			return new ArrayList<AssetTag>();
		}
		try {
			return assetEntry.getTags();
		} catch (SystemException e) {
			LOGGER.error("Can not get tags!", e);
			return new ArrayList<AssetTag>();
		}
	}

	private Date adjust(Date date) {
//		if(calendarBooking.isTimeZoneSensitive()) {
			return Time.getDate(date, TimeZoneUtil.getDefault());
//		}
		
//		return date;
	}

	public String getStartTime() {
		long startDate =  getCalendarBooking().getStartTime() ;
		Date d= new Date(startDate);
		return DateFormat.getTimeInstance(DateFormat.SHORT).format(d).toString();
	}
	public String getStartMonth(TimeZone tz) {
		Format dateFormatDate = FastDateFormatFactoryUtil.getSimpleDateFormat("MMM", tz);
//		Date startDate = adjust(getCalEvent().getStartDate());
		return dateFormatDate.format(this.getCalendarDate());
	}
	public String getStartDay(TimeZone tz) {
		Format dateFormatDate = FastDateFormatFactoryUtil.getSimpleDateFormat("dd", tz);
		return dateFormatDate.format(this.getCalendarDate());
	}
	public String getEndDay(TimeZone tz) {
		//Date endDate = adjust(getCalEvent().getEndDate());
		Format dateFormatDate = FastDateFormatFactoryUtil.getSimpleDateFormat("dd", tz);
		return dateFormatDate.format(getCalendarBooking().getEndTime());
	}
	public boolean isDailyRecurrence() {
		return getCalendarBooking().isRecurring() && getCalendarBooking().getRecurringCalendarBookingId()  == DAILY_RECURRENCE_VALUE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (getCalendarBookingId() ^ (getCalendarBookingId() >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalendarEvent other = (CalendarEvent) obj;
		if (getCalendarBookingId() != other.getCalendarBookingId())
			return false;
		return true;
	}

	@Override
	public int compareTo(CalendarEvent other) {
		long eventId = getCalendarBookingId();
		long otherEventId = other.getCalendarBookingId();

		if (this.getCalendarBooking().getStartTime() > other.getCalendarBooking().getStartTime()) {
			return 1;
		}
		else if (this.getCalendarBooking().getStartTime() < other.getCalendarBooking().getStartTime()) {
			return -1;
		}
		return 0;
	}

	public static Comparator<CalendarEvent> getEventComparator(final boolean isAscending) {
		return new Comparator<CalendarEvent>() {
			@Override
			public int compare(CalendarEvent event1, CalendarEvent event2) {

				int result = 0;

				if(event1.getCalendarBooking().getStartTime() > (event2.getCalendarBooking().getStartTime())) {
					result = isAscending ? 1 : -1;
				} else if(event1.getCalendarBooking().getStartTime() < (event2.getCalendarBooking().getStartTime())) {
					result = isAscending ? -1 : 1;
				}

				return result;
			}
		};
	}
}