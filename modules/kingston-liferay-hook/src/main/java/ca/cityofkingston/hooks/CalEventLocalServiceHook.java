/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.cityofkingston.hooks;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalService;
import com.liferay.calendar.service.CalendarBookingLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;  

@Component(
	immediate = true,
	property = { 
			 "service.ranking:Integer=10000"
	},
	service = ServiceWrapper.class
)

public class CalEventLocalServiceHook extends CalendarBookingLocalServiceWrapper {

public CalEventLocalServiceHook(CalendarBookingLocalService calendarBookingLocalService) {
		super(_calendarBookingLocalService);
	}

public CalEventLocalServiceHook() {
	super(_calendarBookingLocalService);
}


@Reference(unbind = "-")
protected void setCalendarBookingLocalService(CalendarBookingLocalService calendarBookingLocalService) {
	_calendarBookingLocalService = calendarBookingLocalService;
}
private static CalendarBookingLocalService _calendarBookingLocalService;


@Override
public CalendarBooking updateCalendarBooking(long userId, long calendarBookingId, long calendarId,
		long[] childCalendarIds, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, String location,
		long startTime, long endTime, boolean allDay, String recurrence, long firstReminder, String firstReminderType,
		long secondReminder, String secondReminderType, ServiceContext serviceContext) throws PortalException {
	LOGGER.error("+ CalEventLocalService hook shows title="+titleMap);
	
	CalendarBooking calendarBooking =  super.updateCalendarBooking(userId, calendarBookingId, calendarId, childCalendarIds, titleMap, descriptionMap,
			location, startTime, endTime, allDay, recurrence, firstReminder, firstReminderType, secondReminder,
			secondReminderType, serviceContext);
	return calendarBooking;
}

private static final Log LOGGER = LogFactoryUtil.getLog(CalEventLocalServiceHook.class);
}
	
 