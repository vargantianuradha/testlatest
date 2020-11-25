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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class StaffPeopleSoap implements Serializable {
	public static StaffPeopleSoap toSoapModel(StaffPeople model) {
		StaffPeopleSoap soapModel = new StaffPeopleSoap();

		soapModel.setId(model.getId());
		soapModel.setGroup(model.getGroup());
		soapModel.setDepartment(model.getDepartment());
		soapModel.setDivision(model.getDivision());
		soapModel.setUnit(model.getUnit());
		soapModel.setTitle(model.getTitle());
		soapModel.setLast(model.getLast());
		soapModel.setFirst(model.getFirst());
		soapModel.setName(model.getName());
		soapModel.setPhone(model.getPhone());
		soapModel.setFax(model.getFax());
		soapModel.setEmail(model.getEmail());
		soapModel.setTstamp(model.getTstamp());

		return soapModel;
	}

	public static StaffPeopleSoap[] toSoapModels(StaffPeople[] models) {
		StaffPeopleSoap[] soapModels = new StaffPeopleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StaffPeopleSoap[][] toSoapModels(StaffPeople[][] models) {
		StaffPeopleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StaffPeopleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StaffPeopleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StaffPeopleSoap[] toSoapModels(List<StaffPeople> models) {
		List<StaffPeopleSoap> soapModels = new ArrayList<StaffPeopleSoap>(models.size());

		for (StaffPeople model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StaffPeopleSoap[soapModels.size()]);
	}

	public StaffPeopleSoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public String getGroup() {
		return _group;
	}

	public void setGroup(String group) {
		_group = group;
	}

	public String getDepartment() {
		return _department;
	}

	public void setDepartment(String department) {
		_department = department;
	}

	public String getDivision() {
		return _division;
	}

	public void setDivision(String division) {
		_division = division;
	}

	public String getUnit() {
		return _unit;
	}

	public void setUnit(String unit) {
		_unit = unit;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getLast() {
		return _last;
	}

	public void setLast(String last) {
		_last = last;
	}

	public String getFirst() {
		return _first;
	}

	public void setFirst(String first) {
		_first = first;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPhone() {
		return _phone;
	}

	public void setPhone(String phone) {
		_phone = phone;
	}

	public String getFax() {
		return _fax;
	}

	public void setFax(String fax) {
		_fax = fax;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public Date getTstamp() {
		return _tstamp;
	}

	public void setTstamp(Date tstamp) {
		_tstamp = tstamp;
	}

	private long _id;
	private String _group;
	private String _department;
	private String _division;
	private String _unit;
	private String _title;
	private String _last;
	private String _first;
	private String _name;
	private String _phone;
	private String _fax;
	private String _email;
	private Date _tstamp;
}