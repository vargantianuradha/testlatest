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

package ca.cityofkingston.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StaffPeopleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see StaffPeopleLocalService
 * @generated
 */
@ProviderType
public class StaffPeopleLocalServiceWrapper implements StaffPeopleLocalService,
	ServiceWrapper<StaffPeopleLocalService> {
	public StaffPeopleLocalServiceWrapper(
		StaffPeopleLocalService staffPeopleLocalService) {
		_staffPeopleLocalService = staffPeopleLocalService;
	}

	/**
	* Adds the staff people to the database. Also notifies the appropriate model listeners.
	*
	* @param staffPeople the staff people
	* @return the staff people that was added
	*/
	@Override
	public ca.cityofkingston.model.StaffPeople addStaffPeople(
		ca.cityofkingston.model.StaffPeople staffPeople) {
		return _staffPeopleLocalService.addStaffPeople(staffPeople);
	}

	/**
	* Creates a new staff people with the primary key. Does not add the staff people to the database.
	*
	* @param id the primary key for the new staff people
	* @return the new staff people
	*/
	@Override
	public ca.cityofkingston.model.StaffPeople createStaffPeople(long id) {
		return _staffPeopleLocalService.createStaffPeople(id);
	}

	/**
	* Deletes the staff people from the database. Also notifies the appropriate model listeners.
	*
	* @param staffPeople the staff people
	* @return the staff people that was removed
	*/
	@Override
	public ca.cityofkingston.model.StaffPeople deleteStaffPeople(
		ca.cityofkingston.model.StaffPeople staffPeople) {
		return _staffPeopleLocalService.deleteStaffPeople(staffPeople);
	}

	/**
	* Deletes the staff people with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the staff people
	* @return the staff people that was removed
	* @throws PortalException if a staff people with the primary key could not be found
	*/
	@Override
	public ca.cityofkingston.model.StaffPeople deleteStaffPeople(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _staffPeopleLocalService.deleteStaffPeople(id);
	}

	@Override
	public ca.cityofkingston.model.StaffPeople fetchStaffPeople(long id) {
		return _staffPeopleLocalService.fetchStaffPeople(id);
	}

	/**
	* Returns the staff people with the primary key.
	*
	* @param id the primary key of the staff people
	* @return the staff people
	* @throws PortalException if a staff people with the primary key could not be found
	*/
	@Override
	public ca.cityofkingston.model.StaffPeople getStaffPeople(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _staffPeopleLocalService.getStaffPeople(id);
	}

	/**
	* Updates the staff people in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param staffPeople the staff people
	* @return the staff people that was updated
	*/
	@Override
	public ca.cityofkingston.model.StaffPeople updateStaffPeople(
		ca.cityofkingston.model.StaffPeople staffPeople) {
		return _staffPeopleLocalService.updateStaffPeople(staffPeople);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _staffPeopleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _staffPeopleLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _staffPeopleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _staffPeopleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _staffPeopleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of staff peoples.
	*
	* @return the number of staff peoples
	*/
	@Override
	public int getStaffPeoplesCount() {
		return _staffPeopleLocalService.getStaffPeoplesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _staffPeopleLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _staffPeopleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ca.cityofkingston.model.impl.StaffPeopleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _staffPeopleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ca.cityofkingston.model.impl.StaffPeopleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _staffPeopleLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<ca.cityofkingston.model.StaffPeople> findByLastnameAndFirstname(
		java.lang.String last, java.lang.String first)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _staffPeopleLocalService.findByLastnameAndFirstname(last, first);
	}

	@Override
	public java.util.List<ca.cityofkingston.model.StaffPeople> getAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _staffPeopleLocalService.getAll();
	}

	/**
	* Returns a range of all the staff peoples.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ca.cityofkingston.model.impl.StaffPeopleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of staff peoples
	* @param end the upper bound of the range of staff peoples (not inclusive)
	* @return the range of staff peoples
	*/
	@Override
	public java.util.List<ca.cityofkingston.model.StaffPeople> getStaffPeoples(
		int start, int end) {
		return _staffPeopleLocalService.getStaffPeoples(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _staffPeopleLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _staffPeopleLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public StaffPeopleLocalService getWrappedService() {
		return _staffPeopleLocalService;
	}

	@Override
	public void setWrappedService(
		StaffPeopleLocalService staffPeopleLocalService) {
		_staffPeopleLocalService = staffPeopleLocalService;
	}

	private StaffPeopleLocalService _staffPeopleLocalService;
}