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

import ca.cityofkingston.kingstonUtils.model.KingstonLayout;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the kingston layout service. This utility wraps {@link ca.cityofkingston.kingstonUtils.service.persistence.impl.KingstonLayoutPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KingstonLayoutPersistence
 * @see ca.cityofkingston.kingstonUtils.service.persistence.impl.KingstonLayoutPersistenceImpl
 * @generated
 */
@ProviderType
public class KingstonLayoutUtil {
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
	public static void clearCache(KingstonLayout kingstonLayout) {
		getPersistence().clearCache(kingstonLayout);
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
	public static List<KingstonLayout> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KingstonLayout> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KingstonLayout> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<KingstonLayout> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static KingstonLayout update(KingstonLayout kingstonLayout) {
		return getPersistence().update(kingstonLayout);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static KingstonLayout update(KingstonLayout kingstonLayout,
		ServiceContext serviceContext) {
		return getPersistence().update(kingstonLayout, serviceContext);
	}

	/**
	* Caches the kingston layout in the entity cache if it is enabled.
	*
	* @param kingstonLayout the kingston layout
	*/
	public static void cacheResult(KingstonLayout kingstonLayout) {
		getPersistence().cacheResult(kingstonLayout);
	}

	/**
	* Caches the kingston layouts in the entity cache if it is enabled.
	*
	* @param kingstonLayouts the kingston layouts
	*/
	public static void cacheResult(List<KingstonLayout> kingstonLayouts) {
		getPersistence().cacheResult(kingstonLayouts);
	}

	/**
	* Creates a new kingston layout with the primary key. Does not add the kingston layout to the database.
	*
	* @param kId the primary key for the new kingston layout
	* @return the new kingston layout
	*/
	public static KingstonLayout create(long kId) {
		return getPersistence().create(kId);
	}

	/**
	* Removes the kingston layout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kId the primary key of the kingston layout
	* @return the kingston layout that was removed
	* @throws NoSuchKingstonLayoutException if a kingston layout with the primary key could not be found
	*/
	public static KingstonLayout remove(long kId)
		throws ca.cityofkingston.kingstonUtils.exception.NoSuchKingstonLayoutException {
		return getPersistence().remove(kId);
	}

	public static KingstonLayout updateImpl(KingstonLayout kingstonLayout) {
		return getPersistence().updateImpl(kingstonLayout);
	}

	/**
	* Returns the kingston layout with the primary key or throws a {@link NoSuchKingstonLayoutException} if it could not be found.
	*
	* @param kId the primary key of the kingston layout
	* @return the kingston layout
	* @throws NoSuchKingstonLayoutException if a kingston layout with the primary key could not be found
	*/
	public static KingstonLayout findByPrimaryKey(long kId)
		throws ca.cityofkingston.kingstonUtils.exception.NoSuchKingstonLayoutException {
		return getPersistence().findByPrimaryKey(kId);
	}

	/**
	* Returns the kingston layout with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kId the primary key of the kingston layout
	* @return the kingston layout, or <code>null</code> if a kingston layout with the primary key could not be found
	*/
	public static KingstonLayout fetchByPrimaryKey(long kId) {
		return getPersistence().fetchByPrimaryKey(kId);
	}

	public static java.util.Map<java.io.Serializable, KingstonLayout> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the kingston layouts.
	*
	* @return the kingston layouts
	*/
	public static List<KingstonLayout> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<KingstonLayout> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<KingstonLayout> findAll(int start, int end,
		OrderByComparator<KingstonLayout> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<KingstonLayout> findAll(int start, int end,
		OrderByComparator<KingstonLayout> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the kingston layouts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kingston layouts.
	*
	* @return the number of kingston layouts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static KingstonLayoutPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<KingstonLayoutPersistence, KingstonLayoutPersistence> _serviceTracker =
		ServiceTrackerFactory.open(KingstonLayoutPersistence.class);
}