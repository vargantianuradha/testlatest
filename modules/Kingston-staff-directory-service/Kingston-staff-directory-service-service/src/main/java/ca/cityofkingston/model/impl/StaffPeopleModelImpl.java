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
import ca.cityofkingston.model.StaffPeopleModel;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the StaffPeople service. Represents a row in the &quot;dir_people&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link StaffPeopleModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StaffPeopleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StaffPeopleImpl
 * @see StaffPeople
 * @see StaffPeopleModel
 * @generated
 */
@ProviderType
public class StaffPeopleModelImpl extends BaseModelImpl<StaffPeople>
	implements StaffPeopleModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a staff people model instance should use the {@link StaffPeople} interface instead.
	 */
	public static final String TABLE_NAME = "dir_people";
	public static final Object[][] TABLE_COLUMNS = {
			{ "id_", Types.BIGINT },
			{ "group_", Types.VARCHAR },
			{ "department", Types.VARCHAR },
			{ "division", Types.VARCHAR },
			{ "unit", Types.VARCHAR },
			{ "title", Types.VARCHAR },
			{ "last", Types.VARCHAR },
			{ "first", Types.VARCHAR },
			{ "name", Types.VARCHAR },
			{ "phone", Types.VARCHAR },
			{ "fax", Types.VARCHAR },
			{ "email", Types.VARCHAR },
			{ "tstamp", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("id_", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("group_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("department", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("division", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("unit", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("last", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("first", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("phone", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fax", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("email", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("tstamp", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table dir_people (id_ LONG not null primary key,group_ VARCHAR(75) null,department VARCHAR(75) null,division VARCHAR(75) null,unit VARCHAR(75) null,title VARCHAR(75) null,last VARCHAR(75) null,first VARCHAR(75) null,name VARCHAR(75) null,phone VARCHAR(75) null,fax VARCHAR(75) null,email VARCHAR(75) null,tstamp DATE null)";
	public static final String TABLE_SQL_DROP = "drop table dir_people";
	public static final String ORDER_BY_JPQL = " ORDER BY staffPeople.last ASC, staffPeople.first ASC";
	public static final String ORDER_BY_SQL = " ORDER BY dir_people.last ASC, dir_people.first ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(ca.cityofkingston.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.ca.cityofkingston.model.StaffPeople"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(ca.cityofkingston.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.ca.cityofkingston.model.StaffPeople"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(ca.cityofkingston.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.ca.cityofkingston.model.StaffPeople"),
			true);
	public static final long FIRST_COLUMN_BITMASK = 1L;
	public static final long LAST_COLUMN_BITMASK = 2L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(ca.cityofkingston.service.util.ServiceProps.get(
				"lock.expiration.time.ca.cityofkingston.model.StaffPeople"));

	public StaffPeopleModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _id;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _id;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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
	public long getId() {
		return _id;
	}

	@Override
	public void setId(long id) {
		_id = id;
	}

	@Override
	public String getGroup() {
		if (_group == null) {
			return StringPool.BLANK;
		}
		else {
			return _group;
		}
	}

	@Override
	public void setGroup(String group) {
		_group = group;
	}

	@Override
	public String getDepartment() {
		if (_department == null) {
			return StringPool.BLANK;
		}
		else {
			return _department;
		}
	}

	@Override
	public void setDepartment(String department) {
		_department = department;
	}

	@Override
	public String getDivision() {
		if (_division == null) {
			return StringPool.BLANK;
		}
		else {
			return _division;
		}
	}

	@Override
	public void setDivision(String division) {
		_division = division;
	}

	@Override
	public String getUnit() {
		if (_unit == null) {
			return StringPool.BLANK;
		}
		else {
			return _unit;
		}
	}

	@Override
	public void setUnit(String unit) {
		_unit = unit;
	}

	@Override
	public String getTitle() {
		if (_title == null) {
			return StringPool.BLANK;
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		_title = title;
	}

	@Override
	public String getLast() {
		if (_last == null) {
			return StringPool.BLANK;
		}
		else {
			return _last;
		}
	}

	@Override
	public void setLast(String last) {
		_columnBitmask = -1L;

		if (_originalLast == null) {
			_originalLast = _last;
		}

		_last = last;
	}

	public String getOriginalLast() {
		return GetterUtil.getString(_originalLast);
	}

	@Override
	public String getFirst() {
		if (_first == null) {
			return StringPool.BLANK;
		}
		else {
			return _first;
		}
	}

	@Override
	public void setFirst(String first) {
		_columnBitmask = -1L;

		if (_originalFirst == null) {
			_originalFirst = _first;
		}

		_first = first;
	}

	public String getOriginalFirst() {
		return GetterUtil.getString(_originalFirst);
	}

	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public String getPhone() {
		if (_phone == null) {
			return StringPool.BLANK;
		}
		else {
			return _phone;
		}
	}

	@Override
	public void setPhone(String phone) {
		_phone = phone;
	}

	@Override
	public String getFax() {
		if (_fax == null) {
			return StringPool.BLANK;
		}
		else {
			return _fax;
		}
	}

	@Override
	public void setFax(String fax) {
		_fax = fax;
	}

	@Override
	public String getEmail() {
		if (_email == null) {
			return StringPool.BLANK;
		}
		else {
			return _email;
		}
	}

	@Override
	public void setEmail(String email) {
		_email = email;
	}

	@Override
	public Date getTstamp() {
		return _tstamp;
	}

	@Override
	public void setTstamp(Date tstamp) {
		_tstamp = tstamp;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			StaffPeople.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public StaffPeople toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (StaffPeople)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		StaffPeopleImpl staffPeopleImpl = new StaffPeopleImpl();

		staffPeopleImpl.setId(getId());
		staffPeopleImpl.setGroup(getGroup());
		staffPeopleImpl.setDepartment(getDepartment());
		staffPeopleImpl.setDivision(getDivision());
		staffPeopleImpl.setUnit(getUnit());
		staffPeopleImpl.setTitle(getTitle());
		staffPeopleImpl.setLast(getLast());
		staffPeopleImpl.setFirst(getFirst());
		staffPeopleImpl.setName(getName());
		staffPeopleImpl.setPhone(getPhone());
		staffPeopleImpl.setFax(getFax());
		staffPeopleImpl.setEmail(getEmail());
		staffPeopleImpl.setTstamp(getTstamp());

		staffPeopleImpl.resetOriginalValues();

		return staffPeopleImpl;
	}

	@Override
	public int compareTo(StaffPeople staffPeople) {
		int value = 0;

		value = getLast().compareTo(staffPeople.getLast());

		if (value != 0) {
			return value;
		}

		value = getFirst().compareTo(staffPeople.getFirst());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StaffPeople)) {
			return false;
		}

		StaffPeople staffPeople = (StaffPeople)obj;

		long primaryKey = staffPeople.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		StaffPeopleModelImpl staffPeopleModelImpl = this;

		staffPeopleModelImpl._originalLast = staffPeopleModelImpl._last;

		staffPeopleModelImpl._originalFirst = staffPeopleModelImpl._first;

		staffPeopleModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<StaffPeople> toCacheModel() {
		StaffPeopleCacheModel staffPeopleCacheModel = new StaffPeopleCacheModel();

		staffPeopleCacheModel.id = getId();

		staffPeopleCacheModel.group = getGroup();

		String group = staffPeopleCacheModel.group;

		if ((group != null) && (group.length() == 0)) {
			staffPeopleCacheModel.group = null;
		}

		staffPeopleCacheModel.department = getDepartment();

		String department = staffPeopleCacheModel.department;

		if ((department != null) && (department.length() == 0)) {
			staffPeopleCacheModel.department = null;
		}

		staffPeopleCacheModel.division = getDivision();

		String division = staffPeopleCacheModel.division;

		if ((division != null) && (division.length() == 0)) {
			staffPeopleCacheModel.division = null;
		}

		staffPeopleCacheModel.unit = getUnit();

		String unit = staffPeopleCacheModel.unit;

		if ((unit != null) && (unit.length() == 0)) {
			staffPeopleCacheModel.unit = null;
		}

		staffPeopleCacheModel.title = getTitle();

		String title = staffPeopleCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			staffPeopleCacheModel.title = null;
		}

		staffPeopleCacheModel.last = getLast();

		String last = staffPeopleCacheModel.last;

		if ((last != null) && (last.length() == 0)) {
			staffPeopleCacheModel.last = null;
		}

		staffPeopleCacheModel.first = getFirst();

		String first = staffPeopleCacheModel.first;

		if ((first != null) && (first.length() == 0)) {
			staffPeopleCacheModel.first = null;
		}

		staffPeopleCacheModel.name = getName();

		String name = staffPeopleCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			staffPeopleCacheModel.name = null;
		}

		staffPeopleCacheModel.phone = getPhone();

		String phone = staffPeopleCacheModel.phone;

		if ((phone != null) && (phone.length() == 0)) {
			staffPeopleCacheModel.phone = null;
		}

		staffPeopleCacheModel.fax = getFax();

		String fax = staffPeopleCacheModel.fax;

		if ((fax != null) && (fax.length() == 0)) {
			staffPeopleCacheModel.fax = null;
		}

		staffPeopleCacheModel.email = getEmail();

		String email = staffPeopleCacheModel.email;

		if ((email != null) && (email.length() == 0)) {
			staffPeopleCacheModel.email = null;
		}

		Date tstamp = getTstamp();

		if (tstamp != null) {
			staffPeopleCacheModel.tstamp = tstamp.getTime();
		}
		else {
			staffPeopleCacheModel.tstamp = Long.MIN_VALUE;
		}

		return staffPeopleCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{id=");
		sb.append(getId());
		sb.append(", group=");
		sb.append(getGroup());
		sb.append(", department=");
		sb.append(getDepartment());
		sb.append(", division=");
		sb.append(getDivision());
		sb.append(", unit=");
		sb.append(getUnit());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", last=");
		sb.append(getLast());
		sb.append(", first=");
		sb.append(getFirst());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", phone=");
		sb.append(getPhone());
		sb.append(", fax=");
		sb.append(getFax());
		sb.append(", email=");
		sb.append(getEmail());
		sb.append(", tstamp=");
		sb.append(getTstamp());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("ca.cityofkingston.model.StaffPeople");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>id</column-name><column-value><![CDATA[");
		sb.append(getId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>group</column-name><column-value><![CDATA[");
		sb.append(getGroup());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>department</column-name><column-value><![CDATA[");
		sb.append(getDepartment());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>division</column-name><column-value><![CDATA[");
		sb.append(getDivision());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>unit</column-name><column-value><![CDATA[");
		sb.append(getUnit());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>last</column-name><column-value><![CDATA[");
		sb.append(getLast());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>first</column-name><column-value><![CDATA[");
		sb.append(getFirst());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>phone</column-name><column-value><![CDATA[");
		sb.append(getPhone());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fax</column-name><column-value><![CDATA[");
		sb.append(getFax());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>email</column-name><column-value><![CDATA[");
		sb.append(getEmail());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>tstamp</column-name><column-value><![CDATA[");
		sb.append(getTstamp());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = StaffPeople.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			StaffPeople.class
		};
	private long _id;
	private String _group;
	private String _department;
	private String _division;
	private String _unit;
	private String _title;
	private String _last;
	private String _originalLast;
	private String _first;
	private String _originalFirst;
	private String _name;
	private String _phone;
	private String _fax;
	private String _email;
	private Date _tstamp;
	private long _columnBitmask;
	private StaffPeople _escapedModel;
}