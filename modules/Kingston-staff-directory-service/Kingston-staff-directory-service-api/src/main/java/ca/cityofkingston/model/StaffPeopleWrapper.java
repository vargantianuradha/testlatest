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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link StaffPeople}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StaffPeople
 * @generated
 */
@ProviderType
public class StaffPeopleWrapper implements StaffPeople,
	ModelWrapper<StaffPeople> {
	public StaffPeopleWrapper(StaffPeople staffPeople) {
		_staffPeople = staffPeople;
	}

	@Override
	public Class<?> getModelClass() {
		return StaffPeople.class;
	}

	@Override
	public String getModelClassName() {
		return StaffPeople.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("group", getGroup());
		attributes.put("department", getDepartment());
		attributes.put("division", getDivision());
		attributes.put("unit", getUnit());
		attributes.put("title", getTitle());
		attributes.put("last", getLast());
		attributes.put("first", getFirst());
		attributes.put("name", getName());
		attributes.put("phone", getPhone());
		attributes.put("fax", getFax());
		attributes.put("email", getEmail());
		attributes.put("tstamp", getTstamp());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String group = (String)attributes.get("group");

		if (group != null) {
			setGroup(group);
		}

		String department = (String)attributes.get("department");

		if (department != null) {
			setDepartment(department);
		}

		String division = (String)attributes.get("division");

		if (division != null) {
			setDivision(division);
		}

		String unit = (String)attributes.get("unit");

		if (unit != null) {
			setUnit(unit);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String last = (String)attributes.get("last");

		if (last != null) {
			setLast(last);
		}

		String first = (String)attributes.get("first");

		if (first != null) {
			setFirst(first);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String phone = (String)attributes.get("phone");

		if (phone != null) {
			setPhone(phone);
		}

		String fax = (String)attributes.get("fax");

		if (fax != null) {
			setFax(fax);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		Date tstamp = (Date)attributes.get("tstamp");

		if (tstamp != null) {
			setTstamp(tstamp);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _staffPeople.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _staffPeople.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _staffPeople.isNew();
	}

	@Override
	public ca.cityofkingston.model.StaffPeople toEscapedModel() {
		return new StaffPeopleWrapper(_staffPeople.toEscapedModel());
	}

	@Override
	public ca.cityofkingston.model.StaffPeople toUnescapedModel() {
		return new StaffPeopleWrapper(_staffPeople.toUnescapedModel());
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _staffPeople.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ca.cityofkingston.model.StaffPeople> toCacheModel() {
		return _staffPeople.toCacheModel();
	}

	@Override
	public int compareTo(ca.cityofkingston.model.StaffPeople staffPeople) {
		return _staffPeople.compareTo(staffPeople);
	}

	@Override
	public int hashCode() {
		return _staffPeople.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _staffPeople.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new StaffPeopleWrapper((StaffPeople)_staffPeople.clone());
	}

	/**
	* Returns the department of this staff people.
	*
	* @return the department of this staff people
	*/
	@Override
	public java.lang.String getDepartment() {
		return _staffPeople.getDepartment();
	}

	/**
	* Returns the division of this staff people.
	*
	* @return the division of this staff people
	*/
	@Override
	public java.lang.String getDivision() {
		return _staffPeople.getDivision();
	}

	/**
	* Returns the email of this staff people.
	*
	* @return the email of this staff people
	*/
	@Override
	public java.lang.String getEmail() {
		return _staffPeople.getEmail();
	}

	/**
	* Returns the fax of this staff people.
	*
	* @return the fax of this staff people
	*/
	@Override
	public java.lang.String getFax() {
		return _staffPeople.getFax();
	}

	/**
	* Returns the first of this staff people.
	*
	* @return the first of this staff people
	*/
	@Override
	public java.lang.String getFirst() {
		return _staffPeople.getFirst();
	}

	/**
	* Returns the group of this staff people.
	*
	* @return the group of this staff people
	*/
	@Override
	public java.lang.String getGroup() {
		return _staffPeople.getGroup();
	}

	/**
	* Returns the last of this staff people.
	*
	* @return the last of this staff people
	*/
	@Override
	public java.lang.String getLast() {
		return _staffPeople.getLast();
	}

	/**
	* Returns the name of this staff people.
	*
	* @return the name of this staff people
	*/
	@Override
	public java.lang.String getName() {
		return _staffPeople.getName();
	}

	/**
	* Returns the phone of this staff people.
	*
	* @return the phone of this staff people
	*/
	@Override
	public java.lang.String getPhone() {
		return _staffPeople.getPhone();
	}

	/**
	* Returns the title of this staff people.
	*
	* @return the title of this staff people
	*/
	@Override
	public java.lang.String getTitle() {
		return _staffPeople.getTitle();
	}

	/**
	* Returns the unit of this staff people.
	*
	* @return the unit of this staff people
	*/
	@Override
	public java.lang.String getUnit() {
		return _staffPeople.getUnit();
	}

	@Override
	public java.lang.String toString() {
		return _staffPeople.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _staffPeople.toXmlString();
	}

	/**
	* Returns the tstamp of this staff people.
	*
	* @return the tstamp of this staff people
	*/
	@Override
	public Date getTstamp() {
		return _staffPeople.getTstamp();
	}

	/**
	* Returns the ID of this staff people.
	*
	* @return the ID of this staff people
	*/
	@Override
	public long getId() {
		return _staffPeople.getId();
	}

	/**
	* Returns the primary key of this staff people.
	*
	* @return the primary key of this staff people
	*/
	@Override
	public long getPrimaryKey() {
		return _staffPeople.getPrimaryKey();
	}

	@Override
	public void persist() {
		_staffPeople.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_staffPeople.setCachedModel(cachedModel);
	}

	/**
	* Sets the department of this staff people.
	*
	* @param department the department of this staff people
	*/
	@Override
	public void setDepartment(java.lang.String department) {
		_staffPeople.setDepartment(department);
	}

	/**
	* Sets the division of this staff people.
	*
	* @param division the division of this staff people
	*/
	@Override
	public void setDivision(java.lang.String division) {
		_staffPeople.setDivision(division);
	}

	/**
	* Sets the email of this staff people.
	*
	* @param email the email of this staff people
	*/
	@Override
	public void setEmail(java.lang.String email) {
		_staffPeople.setEmail(email);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_staffPeople.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_staffPeople.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_staffPeople.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fax of this staff people.
	*
	* @param fax the fax of this staff people
	*/
	@Override
	public void setFax(java.lang.String fax) {
		_staffPeople.setFax(fax);
	}

	/**
	* Sets the first of this staff people.
	*
	* @param first the first of this staff people
	*/
	@Override
	public void setFirst(java.lang.String first) {
		_staffPeople.setFirst(first);
	}

	/**
	* Sets the group of this staff people.
	*
	* @param group the group of this staff people
	*/
	@Override
	public void setGroup(java.lang.String group) {
		_staffPeople.setGroup(group);
	}

	/**
	* Sets the ID of this staff people.
	*
	* @param id the ID of this staff people
	*/
	@Override
	public void setId(long id) {
		_staffPeople.setId(id);
	}

	/**
	* Sets the last of this staff people.
	*
	* @param last the last of this staff people
	*/
	@Override
	public void setLast(java.lang.String last) {
		_staffPeople.setLast(last);
	}

	/**
	* Sets the name of this staff people.
	*
	* @param name the name of this staff people
	*/
	@Override
	public void setName(java.lang.String name) {
		_staffPeople.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_staffPeople.setNew(n);
	}

	/**
	* Sets the phone of this staff people.
	*
	* @param phone the phone of this staff people
	*/
	@Override
	public void setPhone(java.lang.String phone) {
		_staffPeople.setPhone(phone);
	}

	/**
	* Sets the primary key of this staff people.
	*
	* @param primaryKey the primary key of this staff people
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_staffPeople.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_staffPeople.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the title of this staff people.
	*
	* @param title the title of this staff people
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_staffPeople.setTitle(title);
	}

	/**
	* Sets the tstamp of this staff people.
	*
	* @param tstamp the tstamp of this staff people
	*/
	@Override
	public void setTstamp(Date tstamp) {
		_staffPeople.setTstamp(tstamp);
	}

	/**
	* Sets the unit of this staff people.
	*
	* @param unit the unit of this staff people
	*/
	@Override
	public void setUnit(java.lang.String unit) {
		_staffPeople.setUnit(unit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StaffPeopleWrapper)) {
			return false;
		}

		StaffPeopleWrapper staffPeopleWrapper = (StaffPeopleWrapper)obj;

		if (Objects.equals(_staffPeople, staffPeopleWrapper._staffPeople)) {
			return true;
		}

		return false;
	}

	@Override
	public StaffPeople getWrappedModel() {
		return _staffPeople;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _staffPeople.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _staffPeople.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_staffPeople.resetOriginalValues();
	}

	private final StaffPeople _staffPeople;
}