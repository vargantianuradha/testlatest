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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for StaffPeople. This utility wraps
 * {@link ca.cityofkingston.service.impl.StaffPeopleLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see StaffPeopleLocalService
 * @see ca.cityofkingston.service.base.StaffPeopleLocalServiceBaseImpl
 * @see ca.cityofkingston.service.impl.StaffPeopleLocalServiceImpl
 * @generated
 */
@ProviderType
public class StaffPeopleLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link ca.cityofkingston.service.impl.StaffPeopleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the staff people to the database. Also notifies the appropriate model listeners.
	*
	* @param staffPeople the staff people
	* @return the staff people that was added
	*/
	public static ca.cityofkingston.model.StaffPeople addStaffPeople(
		ca.cityofkingston.model.StaffPeople staffPeople) {
		return getService().addStaffPeople(staffPeople);
	}

	/**
	* Creates a new staff people with the primary key. Does not add the staff people to the database.
	*
	* @param id the primary key for the new staff people
	* @return the new staff people
	*/
	public static ca.cityofkingston.model.StaffPeople createStaffPeople(long id) {
		return getService().createStaffPeople(id);
	}

	/**
	* Deletes the staff people from the database. Also notifies the appropriate model listeners.
	*
	* @param staffPeople the staff people
	* @return the staff people that was removed
	*/
	public static ca.cityofkingston.model.StaffPeople deleteStaffPeople(
		ca.cityofkingston.model.StaffPeople staffPeople) {
		return getService().deleteStaffPeople(staffPeople);
	}

	/**
	* Deletes the staff people with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the staff people
	* @return the staff people that was removed
	* @throws PortalException if a staff people with the primary key could not be found
	*/
	public static ca.cityofkingston.model.StaffPeople deleteStaffPeople(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteStaffPeople(id);
	}

	public static ca.cityofkingston.model.StaffPeople fetchStaffPeople(long id) {
		return getService().fetchStaffPeople(id);
	}

	/**
	* Returns the staff people with the primary key.
	*
	* @param id the primary key of the staff people
	* @return the staff people
	* @throws PortalException if a staff people with the primary key could not be found
	*/
	public static ca.cityofkingston.model.StaffPeople getStaffPeople(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStaffPeople(id);
	}

	/**
	* Updates the staff people in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param staffPeople the staff people
	* @return the staff people that was updated
	*/
	public static ca.cityofkingston.model.StaffPeople updateStaffPeople(
		ca.cityofkingston.model.StaffPeople staffPeople) {
		return getService().updateStaffPeople(staffPeople);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of staff peoples.
	*
	* @return the number of staff peoples
	*/
	public static int getStaffPeoplesCount() {
		return getService().getStaffPeoplesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<ca.cityofkingston.model.StaffPeople> findByLastnameAndFirstname(
		java.lang.String last, java.lang.String first)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().findByLastnameAndFirstname(last, first);
	}

	public static java.util.List<ca.cityofkingston.model.StaffPeople> getAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAll();
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
	public static java.util.List<ca.cityofkingston.model.StaffPeople> getStaffPeoples(
		int start, int end) {
		return getService().getStaffPeoples(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static StaffPeopleLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StaffPeopleLocalService, StaffPeopleLocalService> _serviceTracker =
		ServiceTrackerFactory.open(StaffPeopleLocalService.class);
}