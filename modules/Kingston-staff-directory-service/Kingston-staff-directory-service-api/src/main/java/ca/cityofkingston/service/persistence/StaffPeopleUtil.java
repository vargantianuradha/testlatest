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

package ca.cityofkingston.service.persistence;

import aQute.bnd.annotation.ProviderType;

import ca.cityofkingston.model.StaffPeople;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the staff people service. This utility wraps {@link ca.cityofkingston.service.persistence.impl.StaffPeoplePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StaffPeoplePersistence
 * @see ca.cityofkingston.service.persistence.impl.StaffPeoplePersistenceImpl
 * @generated
 */
@ProviderType
public class StaffPeopleUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(StaffPeople staffPeople) {
		getPersistence().clearCache(staffPeople);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<StaffPeople> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StaffPeople> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StaffPeople> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StaffPeople> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StaffPeople update(StaffPeople staffPeople) {
		return getPersistence().update(staffPeople);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StaffPeople update(StaffPeople staffPeople,
		ServiceContext serviceContext) {
		return getPersistence().update(staffPeople, serviceContext);
	}

	/**
	* Returns all the staff peoples where last LIKE &#63; and first LIKE &#63;.
	*
	* @param last the last
	* @param first the first
	* @return the matching staff peoples
	*/
	public static List<StaffPeople> findByLastnameAndFirstname(
		java.lang.String last, java.lang.String first) {
		return getPersistence().findByLastnameAndFirstname(last, first);
	}

	/**
	* Returns a range of all the staff peoples where last LIKE &#63; and first LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StaffPeopleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param last the last
	* @param first the first
	* @param start the lower bound of the range of staff peoples
	* @param end the upper bound of the range of staff peoples (not inclusive)
	* @return the range of matching staff peoples
	*/
	public static List<StaffPeople> findByLastnameAndFirstname(
		java.lang.String last, java.lang.String first, int start, int end) {
		return getPersistence()
				   .findByLastnameAndFirstname(last, first, start, end);
	}

	/**
	* Returns an ordered range of all the staff peoples where last LIKE &#63; and first LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StaffPeopleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param last the last
	* @param first the first
	* @param start the lower bound of the range of staff peoples
	* @param end the upper bound of the range of staff peoples (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching staff peoples
	*/
	public static List<StaffPeople> findByLastnameAndFirstname(
		java.lang.String last, java.lang.String first, int start, int end,
		OrderByComparator<StaffPeople> orderByComparator) {
		return getPersistence()
				   .findByLastnameAndFirstname(last, first, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the staff peoples where last LIKE &#63; and first LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StaffPeopleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param last the last
	* @param first the first
	* @param start the lower bound of the range of staff peoples
	* @param end the upper bound of the range of staff peoples (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching staff peoples
	*/
	public static List<StaffPeople> findByLastnameAndFirstname(
		java.lang.String last, java.lang.String first, int start, int end,
		OrderByComparator<StaffPeople> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLastnameAndFirstname(last, first, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first staff people in the ordered set where last LIKE &#63; and first LIKE &#63;.
	*
	* @param last the last
	* @param first the first
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching staff people
	* @throws NoSuchStaffPeopleException if a matching staff people could not be found
	*/
	public static StaffPeople findByLastnameAndFirstname_First(
		java.lang.String last, java.lang.String first,
		OrderByComparator<StaffPeople> orderByComparator)
		throws ca.cityofkingston.exception.NoSuchStaffPeopleException {
		return getPersistence()
				   .findByLastnameAndFirstname_First(last, first,
			orderByComparator);
	}

	/**
	* Returns the first staff people in the ordered set where last LIKE &#63; and first LIKE &#63;.
	*
	* @param last the last
	* @param first the first
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching staff people, or <code>null</code> if a matching staff people could not be found
	*/
	public static StaffPeople fetchByLastnameAndFirstname_First(
		java.lang.String last, java.lang.String first,
		OrderByComparator<StaffPeople> orderByComparator) {
		return getPersistence()
				   .fetchByLastnameAndFirstname_First(last, first,
			orderByComparator);
	}

	/**
	* Returns the last staff people in the ordered set where last LIKE &#63; and first LIKE &#63;.
	*
	* @param last the last
	* @param first the first
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching staff people
	* @throws NoSuchStaffPeopleException if a matching staff people could not be found
	*/
	public static StaffPeople findByLastnameAndFirstname_Last(
		java.lang.String last, java.lang.String first,
		OrderByComparator<StaffPeople> orderByComparator)
		throws ca.cityofkingston.exception.NoSuchStaffPeopleException {
		return getPersistence()
				   .findByLastnameAndFirstname_Last(last, first,
			orderByComparator);
	}

	/**
	* Returns the last staff people in the ordered set where last LIKE &#63; and first LIKE &#63;.
	*
	* @param last the last
	* @param first the first
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching staff people, or <code>null</code> if a matching staff people could not be found
	*/
	public static StaffPeople fetchByLastnameAndFirstname_Last(
		java.lang.String last, java.lang.String first,
		OrderByComparator<StaffPeople> orderByComparator) {
		return getPersistence()
				   .fetchByLastnameAndFirstname_Last(last, first,
			orderByComparator);
	}

	/**
	* Returns the staff peoples before and after the current staff people in the ordered set where last LIKE &#63; and first LIKE &#63;.
	*
	* @param id the primary key of the current staff people
	* @param last the last
	* @param first the first
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next staff people
	* @throws NoSuchStaffPeopleException if a staff people with the primary key could not be found
	*/
	public static StaffPeople[] findByLastnameAndFirstname_PrevAndNext(
		long id, java.lang.String last, java.lang.String first,
		OrderByComparator<StaffPeople> orderByComparator)
		throws ca.cityofkingston.exception.NoSuchStaffPeopleException {
		return getPersistence()
				   .findByLastnameAndFirstname_PrevAndNext(id, last, first,
			orderByComparator);
	}

	/**
	* Removes all the staff peoples where last LIKE &#63; and first LIKE &#63; from the database.
	*
	* @param last the last
	* @param first the first
	*/
	public static void removeByLastnameAndFirstname(java.lang.String last,
		java.lang.String first) {
		getPersistence().removeByLastnameAndFirstname(last, first);
	}

	/**
	* Returns the number of staff peoples where last LIKE &#63; and first LIKE &#63;.
	*
	* @param last the last
	* @param first the first
	* @return the number of matching staff peoples
	*/
	public static int countByLastnameAndFirstname(java.lang.String last,
		java.lang.String first) {
		return getPersistence().countByLastnameAndFirstname(last, first);
	}

	/**
	* Caches the staff people in the entity cache if it is enabled.
	*
	* @param staffPeople the staff people
	*/
	public static void cacheResult(StaffPeople staffPeople) {
		getPersistence().cacheResult(staffPeople);
	}

	/**
	* Caches the staff peoples in the entity cache if it is enabled.
	*
	* @param staffPeoples the staff peoples
	*/
	public static void cacheResult(List<StaffPeople> staffPeoples) {
		getPersistence().cacheResult(staffPeoples);
	}

	/**
	* Creates a new staff people with the primary key. Does not add the staff people to the database.
	*
	* @param id the primary key for the new staff people
	* @return the new staff people
	*/
	public static StaffPeople create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the staff people with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the staff people
	* @return the staff people that was removed
	* @throws NoSuchStaffPeopleException if a staff people with the primary key could not be found
	*/
	public static StaffPeople remove(long id)
		throws ca.cityofkingston.exception.NoSuchStaffPeopleException {
		return getPersistence().remove(id);
	}

	public static StaffPeople updateImpl(StaffPeople staffPeople) {
		return getPersistence().updateImpl(staffPeople);
	}

	/**
	* Returns the staff people with the primary key or throws a {@link NoSuchStaffPeopleException} if it could not be found.
	*
	* @param id the primary key of the staff people
	* @return the staff people
	* @throws NoSuchStaffPeopleException if a staff people with the primary key could not be found
	*/
	public static StaffPeople findByPrimaryKey(long id)
		throws ca.cityofkingston.exception.NoSuchStaffPeopleException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the staff people with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the staff people
	* @return the staff people, or <code>null</code> if a staff people with the primary key could not be found
	*/
	public static StaffPeople fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	public static java.util.Map<java.io.Serializable, StaffPeople> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the staff peoples.
	*
	* @return the staff peoples
	*/
	public static List<StaffPeople> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the staff peoples.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StaffPeopleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of staff peoples
	* @param end the upper bound of the range of staff peoples (not inclusive)
	* @return the range of staff peoples
	*/
	public static List<StaffPeople> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the staff peoples.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StaffPeopleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of staff peoples
	* @param end the upper bound of the range of staff peoples (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of staff peoples
	*/
	public static List<StaffPeople> findAll(int start, int end,
		OrderByComparator<StaffPeople> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the staff peoples.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StaffPeopleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of staff peoples
	* @param end the upper bound of the range of staff peoples (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of staff peoples
	*/
	public static List<StaffPeople> findAll(int start, int end,
		OrderByComparator<StaffPeople> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the staff peoples from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of staff peoples.
	*
	* @return the number of staff peoples
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StaffPeoplePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StaffPeoplePersistence, StaffPeoplePersistence> _serviceTracker =
		ServiceTrackerFactory.open(StaffPeoplePersistence.class);
}