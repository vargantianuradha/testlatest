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

import ca.cityofkingston.exception.NoSuchStaffPeopleException;

import ca.cityofkingston.model.StaffPeople;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the staff people service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ca.cityofkingston.service.persistence.impl.StaffPeoplePersistenceImpl
 * @see StaffPeopleUtil
 * @generated
 */
@ProviderType
public interface StaffPeoplePersistence extends BasePersistence<StaffPeople> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StaffPeopleUtil} to access the staff people persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the staff peoples where last LIKE &#63; and first LIKE &#63;.
	*
	* @param last the last
	* @param first the first
	* @return the matching staff peoples
	*/
	public java.util.List<StaffPeople> findByLastnameAndFirstname(
		java.lang.String last, java.lang.String first);

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
	public java.util.List<StaffPeople> findByLastnameAndFirstname(
		java.lang.String last, java.lang.String first, int start, int end);

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
	public java.util.List<StaffPeople> findByLastnameAndFirstname(
		java.lang.String last, java.lang.String first, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StaffPeople> orderByComparator);

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
	public java.util.List<StaffPeople> findByLastnameAndFirstname(
		java.lang.String last, java.lang.String first, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StaffPeople> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first staff people in the ordered set where last LIKE &#63; and first LIKE &#63;.
	*
	* @param last the last
	* @param first the first
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching staff people
	* @throws NoSuchStaffPeopleException if a matching staff people could not be found
	*/
	public StaffPeople findByLastnameAndFirstname_First(java.lang.String last,
		java.lang.String first,
		com.liferay.portal.kernel.util.OrderByComparator<StaffPeople> orderByComparator)
		throws NoSuchStaffPeopleException;

	/**
	* Returns the first staff people in the ordered set where last LIKE &#63; and first LIKE &#63;.
	*
	* @param last the last
	* @param first the first
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching staff people, or <code>null</code> if a matching staff people could not be found
	*/
	public StaffPeople fetchByLastnameAndFirstname_First(
		java.lang.String last, java.lang.String first,
		com.liferay.portal.kernel.util.OrderByComparator<StaffPeople> orderByComparator);

	/**
	* Returns the last staff people in the ordered set where last LIKE &#63; and first LIKE &#63;.
	*
	* @param last the last
	* @param first the first
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching staff people
	* @throws NoSuchStaffPeopleException if a matching staff people could not be found
	*/
	public StaffPeople findByLastnameAndFirstname_Last(java.lang.String last,
		java.lang.String first,
		com.liferay.portal.kernel.util.OrderByComparator<StaffPeople> orderByComparator)
		throws NoSuchStaffPeopleException;

	/**
	* Returns the last staff people in the ordered set where last LIKE &#63; and first LIKE &#63;.
	*
	* @param last the last
	* @param first the first
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching staff people, or <code>null</code> if a matching staff people could not be found
	*/
	public StaffPeople fetchByLastnameAndFirstname_Last(java.lang.String last,
		java.lang.String first,
		com.liferay.portal.kernel.util.OrderByComparator<StaffPeople> orderByComparator);

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
	public StaffPeople[] findByLastnameAndFirstname_PrevAndNext(long id,
		java.lang.String last, java.lang.String first,
		com.liferay.portal.kernel.util.OrderByComparator<StaffPeople> orderByComparator)
		throws NoSuchStaffPeopleException;

	/**
	* Removes all the staff peoples where last LIKE &#63; and first LIKE &#63; from the database.
	*
	* @param last the last
	* @param first the first
	*/
	public void removeByLastnameAndFirstname(java.lang.String last,
		java.lang.String first);

	/**
	* Returns the number of staff peoples where last LIKE &#63; and first LIKE &#63;.
	*
	* @param last the last
	* @param first the first
	* @return the number of matching staff peoples
	*/
	public int countByLastnameAndFirstname(java.lang.String last,
		java.lang.String first);

	/**
	* Caches the staff people in the entity cache if it is enabled.
	*
	* @param staffPeople the staff people
	*/
	public void cacheResult(StaffPeople staffPeople);

	/**
	* Caches the staff peoples in the entity cache if it is enabled.
	*
	* @param staffPeoples the staff peoples
	*/
	public void cacheResult(java.util.List<StaffPeople> staffPeoples);

	/**
	* Creates a new staff people with the primary key. Does not add the staff people to the database.
	*
	* @param id the primary key for the new staff people
	* @return the new staff people
	*/
	public StaffPeople create(long id);

	/**
	* Removes the staff people with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the staff people
	* @return the staff people that was removed
	* @throws NoSuchStaffPeopleException if a staff people with the primary key could not be found
	*/
	public StaffPeople remove(long id) throws NoSuchStaffPeopleException;

	public StaffPeople updateImpl(StaffPeople staffPeople);

	/**
	* Returns the staff people with the primary key or throws a {@link NoSuchStaffPeopleException} if it could not be found.
	*
	* @param id the primary key of the staff people
	* @return the staff people
	* @throws NoSuchStaffPeopleException if a staff people with the primary key could not be found
	*/
	public StaffPeople findByPrimaryKey(long id)
		throws NoSuchStaffPeopleException;

	/**
	* Returns the staff people with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the staff people
	* @return the staff people, or <code>null</code> if a staff people with the primary key could not be found
	*/
	public StaffPeople fetchByPrimaryKey(long id);

	@Override
	public java.util.Map<java.io.Serializable, StaffPeople> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the staff peoples.
	*
	* @return the staff peoples
	*/
	public java.util.List<StaffPeople> findAll();

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
	public java.util.List<StaffPeople> findAll(int start, int end);

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
	public java.util.List<StaffPeople> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StaffPeople> orderByComparator);

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
	public java.util.List<StaffPeople> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StaffPeople> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the staff peoples from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of staff peoples.
	*
	* @return the number of staff peoples
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}