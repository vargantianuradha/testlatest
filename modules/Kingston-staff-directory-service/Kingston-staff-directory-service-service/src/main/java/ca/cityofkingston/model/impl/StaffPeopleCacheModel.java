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

package ca.cityofkingston.model.impl;

import aQute.bnd.annotation.ProviderType;

import ca.cityofkingston.model.StaffPeople;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StaffPeople in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see StaffPeople
 * @generated
 */
@ProviderType
public class StaffPeopleCacheModel implements CacheModel<StaffPeople>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StaffPeopleCacheModel)) {
			return false;
		}

		StaffPeopleCacheModel staffPeopleCacheModel = (StaffPeopleCacheModel)obj;

		if (id == staffPeopleCacheModel.id) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, id);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{id=");
		sb.append(id);
		sb.append(", group=");
		sb.append(group);
		sb.append(", department=");
		sb.append(department);
		sb.append(", division=");
		sb.append(division);
		sb.append(", unit=");
		sb.append(unit);
		sb.append(", title=");
		sb.append(title);
		sb.append(", last=");
		sb.append(last);
		sb.append(", first=");
		sb.append(first);
		sb.append(", name=");
		sb.append(name);
		sb.append(", phone=");
		sb.append(phone);
		sb.append(", fax=");
		sb.append(fax);
		sb.append(", email=");
		sb.append(email);
		sb.append(", tstamp=");
		sb.append(tstamp);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StaffPeople toEntityModel() {
		StaffPeopleImpl staffPeopleImpl = new StaffPeopleImpl();

		staffPeopleImpl.setId(id);

		if (group == null) {
			staffPeopleImpl.setGroup(StringPool.BLANK);
		}
		else {
			staffPeopleImpl.setGroup(group);
		}

		if (department == null) {
			staffPeopleImpl.setDepartment(StringPool.BLANK);
		}
		else {
			staffPeopleImpl.setDepartment(department);
		}

		if (division == null) {
			staffPeopleImpl.setDivision(StringPool.BLANK);
		}
		else {
			staffPeopleImpl.setDivision(division);
		}

		if (unit == null) {
			staffPeopleImpl.setUnit(StringPool.BLANK);
		}
		else {
			staffPeopleImpl.setUnit(unit);
		}

		if (title == null) {
			staffPeopleImpl.setTitle(StringPool.BLANK);
		}
		else {
			staffPeopleImpl.setTitle(title);
		}

		if (last == null) {
			staffPeopleImpl.setLast(StringPool.BLANK);
		}
		else {
			staffPeopleImpl.setLast(last);
		}

		if (first == null) {
			staffPeopleImpl.setFirst(StringPool.BLANK);
		}
		else {
			staffPeopleImpl.setFirst(first);
		}

		if (name == null) {
			staffPeopleImpl.setName(StringPool.BLANK);
		}
		else {
			staffPeopleImpl.setName(name);
		}

		if (phone == null) {
			staffPeopleImpl.setPhone(StringPool.BLANK);
		}
		else {
			staffPeopleImpl.setPhone(phone);
		}

		if (fax == null) {
			staffPeopleImpl.setFax(StringPool.BLANK);
		}
		else {
			staffPeopleImpl.setFax(fax);
		}

		if (email == null) {
			staffPeopleImpl.setEmail(StringPool.BLANK);
		}
		else {
			staffPeopleImpl.setEmail(email);
		}

		if (tstamp == Long.MIN_VALUE) {
			staffPeopleImpl.setTstamp(null);
		}
		else {
			staffPeopleImpl.setTstamp(new Date(tstamp));
		}

		staffPeopleImpl.resetOriginalValues();

		return staffPeopleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();
		group = objectInput.readUTF();
		department = objectInput.readUTF();
		division = objectInput.readUTF();
		unit = objectInput.readUTF();
		title = objectInput.readUTF();
		last = objectInput.readUTF();
		first = objectInput.readUTF();
		name = objectInput.readUTF();
		phone = objectInput.readUTF();
		fax = objectInput.readUTF();
		email = objectInput.readUTF();
		tstamp = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(id);

		if (group == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(group);
		}

		if (department == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(department);
		}

		if (division == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(division);
		}

		if (unit == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(unit);
		}

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (last == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(last);
		}

		if (first == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(first);
		}

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (phone == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(phone);
		}

		if (fax == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fax);
		}

		if (email == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(email);
		}

		objectOutput.writeLong(tstamp);
	}

	public long id;
	public String group;
	public String department;
	public String division;
	public String unit;
	public String title;
	public String last;
	public String first;
	public String name;
	public String phone;
	public String fax;
	public String email;
	public long tstamp;
}