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

package ca.cityofkingston.kingstonUtils.service.persistence;

import aQute.bnd.annotation.ProviderType;

import ca.cityofkingston.kingstonUtils.exception.NoSuchKingstonLayoutException;
import ca.cityofkingston.kingstonUtils.model.KingstonLayout;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the kingston layout service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ca.cityofkingston.kingstonUtils.service.persistence.impl.KingstonLayoutPersistenceImpl
 * @see KingstonLayoutUtil
 * @generated
 */
@ProviderType
public interface KingstonLayoutPersistence extends BasePersistence<KingstonLayout> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KingstonLayoutUtil} to access the kingston layout persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the kingston layout in the entity cache if it is enabled.
	*
	* @param kingstonLayout the kingston layout
	*/
	public void cacheResult(KingstonLayout kingstonLayout);

	/**
	* Caches the kingston layouts in the entity cache if it is enabled.
	*
	* @param kingstonLayouts the kingston layouts
	*/
	public void cacheResult(java.util.List<KingstonLayout> kingstonLayouts);

	/**
	* Creates a new kingston layout with the primary key. Does not add the kingston layout to the database.
	*
	* @param kId the primary key for the new kingston layout
	* @return the new kingston layout
	*/
	public KingstonLayout create(long kId);

	/**
	* Removes the kingston layout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kId the primary key of the kingston layout
	* @return the kingston layout that was removed
	* @throws NoSuchKingstonLayoutException if a kingston layout with the primary key could not be found
	*/
	public KingstonLayout remove(long kId) throws NoSuchKingstonLayoutException;

	public KingstonLayout updateImpl(KingstonLayout kingstonLayout);

	/**
	* Returns the kingston layout with the primary key or throws a {@link NoSuchKingstonLayoutException} if it could not be found.
	*
	* @param kId the primary key of the kingston layout
	* @return the kingston layout
	* @throws NoSuchKingstonLayoutException if a kingston layout with the primary key could not be found
	*/
	public KingstonLayout findByPrimaryKey(long kId)
		throws NoSuchKingstonLayoutException;

	/**
	* Returns the kingston layout with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kId the primary key of the kingston layout
	* @return the kingston layout, or <code>null</code> if a kingston layout with the primary key could not be found
	*/
	public KingstonLayout fetchByPrimaryKey(long kId);

	@Override
	public java.util.Map<java.io.Serializable, KingstonLayout> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the kingston layouts.
	*
	* @return the kingston layouts
	*/
	public java.util.List<KingstonLayout> findAll();

	/**
	* Returns a range of all the kingston layouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KingstonLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kingston layouts
	* @param end the upper bound of the range of kingston layouts (not inclusive)
	* @return the range of kingston layouts
	*/
	public java.util.List<KingstonLayout> findAll(int start, int end);

	/**
	* Returns an ordered range of all the kingston layouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KingstonLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kingston layouts
	* @param end the upper bound of the range of kingston layouts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kingston layouts
	*/
	public java.util.List<KingstonLayout> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KingstonLayout> orderByComparator);

	/**
	* Returns an ordered range of all the kingston layouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KingstonLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kingston layouts
	* @param end the upper bound of the range of kingston layouts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of kingston layouts
	*/
	public java.util.List<KingstonLayout> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<KingstonLayout> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the kingston layouts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of kingston layouts.
	*
	* @return the number of kingston layouts
	*/
	public int countAll();
}