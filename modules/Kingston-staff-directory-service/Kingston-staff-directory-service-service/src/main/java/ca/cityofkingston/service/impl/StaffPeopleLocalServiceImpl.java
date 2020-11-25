/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package ca.cityofkingston.service.impl;

import java.util.List;

import com.liferay.portal.kernel.exception.SystemException;

import ca.cityofkingston.model.StaffPeople;
import ca.cityofkingston.service.base.StaffPeopleLocalServiceBaseImpl;

/**
 * The implementation of the staff people local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link ca.cityofkingston.service.StaffPeopleLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StaffPeopleLocalServiceBaseImpl
 * @see ca.cityofkingston.service.StaffPeopleLocalServiceUtil
 */
public class StaffPeopleLocalServiceImpl extends StaffPeopleLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link ca.cityofkingston.service.StaffPeopleLocalServiceUtil} to access the staff people local service.
	 */
	
	public List<StaffPeople> findByLastnameAndFirstname(String last, String first) throws SystemException {

		return staffPeoplePersistence.findByLastnameAndFirstname(last + '%', first + '%');
	}
	
	public List<StaffPeople> getAll() throws SystemException{
		
		return staffPeoplePersistence.findAll();
		
	}
}