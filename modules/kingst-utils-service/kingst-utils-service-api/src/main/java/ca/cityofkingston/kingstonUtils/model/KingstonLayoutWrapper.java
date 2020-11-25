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

package ca.cityofkingston.kingstonUtils.model;

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
 * This class is a wrapper for {@link KingstonLayout}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KingstonLayout
 * @generated
 */
@ProviderType
public class KingstonLayoutWrapper implements KingstonLayout,
	ModelWrapper<KingstonLayout> {
	public KingstonLayoutWrapper(KingstonLayout kingstonLayout) {
		_kingstonLayout = kingstonLayout;
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
	public boolean isCachedModel() {
		return _kingstonLayout.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _kingstonLayout.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _kingstonLayout.isNew();
	}

	@Override
	public ca.cityofkingston.kingstonUtils.model.KingstonLayout toEscapedModel() {
		return new KingstonLayoutWrapper(_kingstonLayout.toEscapedModel());
	}

	@Override
	public ca.cityofkingston.kingstonUtils.model.KingstonLayout toUnescapedModel() {
		return new KingstonLayoutWrapper(_kingstonLayout.toUnescapedModel());
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _kingstonLayout.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ca.cityofkingston.kingstonUtils.model.KingstonLayout> toCacheModel() {
		return _kingstonLayout.toCacheModel();
	}

	@Override
	public int compareTo(
		ca.cityofkingston.kingstonUtils.model.KingstonLayout kingstonLayout) {
		return _kingstonLayout.compareTo(kingstonLayout);
	}

	@Override
	public int hashCode() {
		return _kingstonLayout.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kingstonLayout.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new KingstonLayoutWrapper((KingstonLayout)_kingstonLayout.clone());
	}

	/**
	* Returns the user name of this kingston layout.
	*
	* @return the user name of this kingston layout
	*/
	@Override
	public java.lang.String getUserName() {
		return _kingstonLayout.getUserName();
	}

	/**
	* Returns the user uuid of this kingston layout.
	*
	* @return the user uuid of this kingston layout
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _kingstonLayout.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _kingstonLayout.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kingstonLayout.toXmlString();
	}

	/**
	* Returns the create date of this kingston layout.
	*
	* @return the create date of this kingston layout
	*/
	@Override
	public Date getCreateDate() {
		return _kingstonLayout.getCreateDate();
	}

	/**
	* Returns the modified date of this kingston layout.
	*
	* @return the modified date of this kingston layout
	*/
	@Override
	public Date getModifiedDate() {
		return _kingstonLayout.getModifiedDate();
	}

	/**
	* Returns the company ID of this kingston layout.
	*
	* @return the company ID of this kingston layout
	*/
	@Override
	public long getCompanyId() {
		return _kingstonLayout.getCompanyId();
	}

	/**
	* Returns the group ID of this kingston layout.
	*
	* @return the group ID of this kingston layout
	*/
	@Override
	public long getGroupId() {
		return _kingstonLayout.getGroupId();
	}

	/**
	* Returns the k ID of this kingston layout.
	*
	* @return the k ID of this kingston layout
	*/
	@Override
	public long getKId() {
		return _kingstonLayout.getKId();
	}

	/**
	* Returns the primary key of this kingston layout.
	*
	* @return the primary key of this kingston layout
	*/
	@Override
	public long getPrimaryKey() {
		return _kingstonLayout.getPrimaryKey();
	}

	/**
	* Returns the user ID of this kingston layout.
	*
	* @return the user ID of this kingston layout
	*/
	@Override
	public long getUserId() {
		return _kingstonLayout.getUserId();
	}

	@Override
	public void persist() {
		_kingstonLayout.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kingstonLayout.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this kingston layout.
	*
	* @param companyId the company ID of this kingston layout
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kingstonLayout.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this kingston layout.
	*
	* @param createDate the create date of this kingston layout
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_kingstonLayout.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_kingstonLayout.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_kingstonLayout.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_kingstonLayout.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this kingston layout.
	*
	* @param groupId the group ID of this kingston layout
	*/
	@Override
	public void setGroupId(long groupId) {
		_kingstonLayout.setGroupId(groupId);
	}

	/**
	* Sets the k ID of this kingston layout.
	*
	* @param kId the k ID of this kingston layout
	*/
	@Override
	public void setKId(long kId) {
		_kingstonLayout.setKId(kId);
	}

	/**
	* Sets the modified date of this kingston layout.
	*
	* @param modifiedDate the modified date of this kingston layout
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_kingstonLayout.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_kingstonLayout.setNew(n);
	}

	/**
	* Sets the primary key of this kingston layout.
	*
	* @param primaryKey the primary key of this kingston layout
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kingstonLayout.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_kingstonLayout.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this kingston layout.
	*
	* @param userId the user ID of this kingston layout
	*/
	@Override
	public void setUserId(long userId) {
		_kingstonLayout.setUserId(userId);
	}

	/**
	* Sets the user name of this kingston layout.
	*
	* @param userName the user name of this kingston layout
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kingstonLayout.setUserName(userName);
	}

	/**
	* Sets the user uuid of this kingston layout.
	*
	* @param userUuid the user uuid of this kingston layout
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kingstonLayout.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KingstonLayoutWrapper)) {
			return false;
		}

		KingstonLayoutWrapper kingstonLayoutWrapper = (KingstonLayoutWrapper)obj;

		if (Objects.equals(_kingstonLayout,
					kingstonLayoutWrapper._kingstonLayout)) {
			return true;
		}

		return false;
	}

	@Override
	public KingstonLayout getWrappedModel() {
		return _kingstonLayout;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kingstonLayout.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kingstonLayout.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kingstonLayout.resetOriginalValues();
	}

	private final KingstonLayout _kingstonLayout;
}