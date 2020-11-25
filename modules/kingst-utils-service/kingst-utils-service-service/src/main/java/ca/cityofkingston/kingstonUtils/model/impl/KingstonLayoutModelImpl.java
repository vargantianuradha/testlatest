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

package ca.cityofkingston.kingstonUtils.model.impl;

import aQute.bnd.annotation.ProviderType;

import ca.cityofkingston.kingstonUtils.model.KingstonLayout;
import ca.cityofkingston.kingstonUtils.model.KingstonLayoutModel;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
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
 * The base model implementation for the KingstonLayout service. Represents a row in the &quot;kingstonLayout_db&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link KingstonLayoutModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KingstonLayoutImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KingstonLayoutImpl
 * @see KingstonLayout
 * @see KingstonLayoutModel
 * @generated
 */
@ProviderType
public class KingstonLayoutModelImpl extends BaseModelImpl<KingstonLayout>
	implements KingstonLayoutModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kingston layout model instance should use the {@link KingstonLayout} interface instead.
	 */
	public static final String TABLE_NAME = "kingstonLayout_db";
	public static final Object[][] TABLE_COLUMNS = {
			{ "kId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("kId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table kingstonLayout_db (kId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table kingstonLayout_db";
	public static final String ORDER_BY_JPQL = " ORDER BY kingstonLayout.kId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY kingstonLayout_db.kId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(ca.cityofkingston.kingstonUtils.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.ca.cityofkingston.kingstonUtils.model.KingstonLayout"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(ca.cityofkingston.kingstonUtils.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.ca.cityofkingston.kingstonUtils.model.KingstonLayout"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(ca.cityofkingston.kingstonUtils.service.util.ServiceProps.get(
				"lock.expiration.time.ca.cityofkingston.kingstonUtils.model.KingstonLayout"));

	public KingstonLayoutModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KingstonLayout.class;
	}

	@Override
	public String getModelClassName() {
		return KingstonLayout.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kId", getKId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kId = (Long)attributes.get("kId");

		if (kId != null) {
			setKId(kId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public long getKId() {
		return _kId;
	}

	@Override
	public void setKId(long kId) {
		_kId = kId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			KingstonLayout.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KingstonLayout toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (KingstonLayout)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		KingstonLayoutImpl kingstonLayoutImpl = new KingstonLayoutImpl();

		kingstonLayoutImpl.setKId(getKId());
		kingstonLayoutImpl.setGroupId(getGroupId());
		kingstonLayoutImpl.setCompanyId(getCompanyId());
		kingstonLayoutImpl.setUserId(getUserId());
		kingstonLayoutImpl.setUserName(getUserName());
		kingstonLayoutImpl.setCreateDate(getCreateDate());
		kingstonLayoutImpl.setModifiedDate(getModifiedDate());

		kingstonLayoutImpl.resetOriginalValues();

		return kingstonLayoutImpl;
	}

	@Override
	public int compareTo(KingstonLayout kingstonLayout) {
		long primaryKey = kingstonLayout.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KingstonLayout)) {
			return false;
		}

		KingstonLayout kingstonLayout = (KingstonLayout)obj;

		long primaryKey = kingstonLayout.getPrimaryKey();

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
		KingstonLayoutModelImpl kingstonLayoutModelImpl = this;

		kingstonLayoutModelImpl._setModifiedDate = false;
	}

	@Override
	public CacheModel<KingstonLayout> toCacheModel() {
		KingstonLayoutCacheModel kingstonLayoutCacheModel = new KingstonLayoutCacheModel();

		kingstonLayoutCacheModel.kId = getKId();

		kingstonLayoutCacheModel.groupId = getGroupId();

		kingstonLayoutCacheModel.companyId = getCompanyId();

		kingstonLayoutCacheModel.userId = getUserId();

		kingstonLayoutCacheModel.userName = getUserName();

		String userName = kingstonLayoutCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kingstonLayoutCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kingstonLayoutCacheModel.createDate = createDate.getTime();
		}
		else {
			kingstonLayoutCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kingstonLayoutCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kingstonLayoutCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		return kingstonLayoutCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{kId=");
		sb.append(getKId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("ca.cityofkingston.kingstonUtils.model.KingstonLayout");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kId</column-name><column-value><![CDATA[");
		sb.append(getKId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = KingstonLayout.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			KingstonLayout.class
		};
	private long _kId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private KingstonLayout _escapedModel;
}