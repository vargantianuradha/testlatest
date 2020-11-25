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

package ca.cityofkingston.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the StaffPeople service. Represents a row in the &quot;dir_people&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see StaffPeopleModel
 * @see ca.cityofkingston.model.impl.StaffPeopleImpl
 * @see ca.cityofkingston.model.impl.StaffPeopleModelImpl
 * @generated
 */
@ImplementationClassName("ca.cityofkingston.model.impl.StaffPeopleImpl")
@ProviderType
public interface StaffPeople extends StaffPeopleModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link ca.cityofkingston.model.impl.StaffPeopleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StaffPeople, Long> ID_ACCESSOR = new Accessor<StaffPeople, Long>() {
			@Override
			public Long get(StaffPeople staffPeople) {
				return staffPeople.getId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<StaffPeople> getTypeClass() {
				return StaffPeople.class;
			}
		};
}