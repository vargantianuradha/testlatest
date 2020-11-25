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
package ca.cityofkingston.listeners;

import java.util.Calendar;
import java.util.Date;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.recurrence.Recurrence;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;

 
@Component(
	immediate = true,
	service = ModelListener.class
)
public class AssetEntryListener extends BaseModelListener<AssetEntry> {
	
	private static final Log _log = LogFactoryUtil.getLog(AssetEntryListener.class);
	
	@Override
	public void onBeforeCreate(AssetEntry model) throws ModelListenerException {
		updateDatesUsingCalendarEvent(model);
	}
	@Override
	public void onBeforeUpdate(AssetEntry model) throws ModelListenerException {
		updateDatesUsingCalendarEvent(model);
	}
	 
	// we are interested only in calendar events
		private void updateDatesUsingCalendarEvent(AssetEntry model) throws ModelListenerException {
			try {    
				long classNameId = model.getClassNameId();
				ClassName className = ClassNameLocalServiceUtil.getClassName(classNameId);
				if (className == null) {  
				_log.error("Can not find Class Name ["+classNameId+"]");
					return;
				}
				if (!CalendarBooking.class.getName().equals(className.getClassName())) { 
					return;
				}
				long classPK = model.getClassPK();
				CalendarBooking event = CalendarBookingLocalServiceUtil.getCalendarBooking(classPK);
				if (event == null) {  
					_log.error("Can not find Calendar Event ["+classPK+"]");
					return;
				}
				Date publishDate = event.getCreateDate();
				Date expirationDate = getExpirationDate(event);
				model.setPublishDate(publishDate);
				model.setExpirationDate(expirationDate);
			} catch (PortalException e) {
				throw new ModelListenerException(e);
			} catch (SystemException e) {
				throw new ModelListenerException(e);
			}
		}
		
		private Date getExpirationDate(CalendarBooking event) { 
			long startTime = event.getStartTime();
//			long endTime = startTime.getTime()+(Time.HOUR *event.getDurationHour())+(Time.MINUTE * event.getDurationMinute());
			long endTime = event.getEndTime();
			if (event.isRecurring()) {  
				Calendar until = null;
				Recurrence recurrence = event.getRecurrenceObj();
				if(recurrence!=null)
				 until = recurrence.getUntilJCalendar();
				return until == null ? new Date(endTime) : new Date(until.getTime().getTime());
			}
			
			return new Date(endTime);
		}
	 
} 