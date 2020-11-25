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

package ca.cityofkingston.kingstonUtils.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for KingstonLayout. This utility wraps
 * {@link ca.cityofkingston.kingstonUtils.service.impl.KingstonLayoutLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KingstonLayoutLocalService
 * @see ca.cityofkingston.kingstonUtils.service.base.KingstonLayoutLocalServiceBaseImpl
 * @see ca.cityofkingston.kingstonUtils.service.impl.KingstonLayoutLocalServiceImpl
 * @generated
 */
@ProviderType
public class KingstonLayoutLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link ca.cityofkingston.kingstonUtils.service.impl.KingstonLayoutLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the kingston layout to the database. Also notifies the appropriate model listeners.
	*
	* @param kingstonLayout the kingston layout
	* @return the kingston layout that was added
	*/
	public static ca.cityofkingston.kingstonUtils.model.KingstonLayout addKingstonLayout(
		ca.cityofkingston.kingstonUtils.model.KingstonLayout kingstonLayout) {
		return getService().addKingstonLayout(kingstonLayout);
	}

	/**
	* Creates a new kingston layout with the primary key. Does not add the kingston layout to the database.
	*
	* @param kId the primary key for the new kingston layout
	* @return the new kingston layout
	*/
	public static ca.cityofkingston.kingstonUtils.model.KingstonLayout createKingstonLayout(
		long kId) {
		return getService().createKingstonLayout(kId);
	}

	/**
	* Deletes the kingston layout from the database. Also notifies the appropriate model listeners.
	*
	* @param kingstonLayout the kingston layout
	* @return the kingston layout that was removed
	*/
	public static ca.cityofkingston.kingstonUtils.model.KingstonLayout deleteKingstonLayout(
		ca.cityofkingston.kingstonUtils.model.KingstonLayout kingstonLayout) {
		return getService().deleteKingstonLayout(kingstonLayout);
	}

	/**
	* Deletes the kingston layout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kId the primary key of the kingston layout
	* @return the kingston layout that was removed
	* @throws PortalException if a kingston layout with the primary key could not be found
	*/
	public static ca.cityofkingston.kingstonUtils.model.KingstonLayout deleteKingstonLayout(
		long kId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteKingstonLayout(kId);
	}

	public static ca.cityofkingston.kingstonUtils.model.KingstonLayout fetchKingstonLayout(
		long kId) {
		return getService().fetchKingstonLayout(kId);
	}

	/**
	* Returns the kingston layout with the primary key.
	*
	* @param kId the primary key of the kingston layout
	* @return the kingston layout
	* @throws PortalException if a kingston layout with the primary key could not be found
	*/
	public static ca.cityofkingston.kingstonUtils.model.KingstonLayout getKingstonLayout(
		long kId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKingstonLayout(kId);
	}

	/**
	* Updates the kingston layout in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kingstonLayout the kingston layout
	* @return the kingston layout that was updated
	*/
	public static ca.cityofkingston.kingstonUtils.model.KingstonLayout updateKingstonLayout(
		ca.cityofkingston.kingstonUtils.model.KingstonLayout kingstonLayout) {
		return getService().updateKingstonLayout(kingstonLayout);
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

	public static com.liferay.portal.kernel.json.JSONObject getPageJSONLD(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay) {
		return getService().getPageJSONLD(themeDisplay);
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
	* Returns the number of kingston layouts.
	*
	* @return the number of kingston layouts
	*/
	public static int getKingstonLayoutsCount() {
		return getService().getKingstonLayoutsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.Date getLastModifiedDate(
		com.liferay.portal.kernel.theme.ThemeDisplay themeDisplay) {
		return getService().getLastModifiedDate(themeDisplay);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ca.cityofkingston.kingstonUtils.model.impl.KingstonLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ca.cityofkingston.kingstonUtils.model.impl.KingstonLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Returns a range of all the kingston layouts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ca.cityofkingston.kingstonUtils.model.impl.KingstonLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kingston layouts
	* @param end the upper bound of the range of kingston layouts (not inclusive)
	* @return the range of kingston layouts
	*/
	public static java.util.List<ca.cityofkingston.kingstonUtils.model.KingstonLayout> getKingstonLayouts(
		int start, int end) {
		return getService().getKingstonLayouts(start, end);
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

	public static KingstonLayoutLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<KingstonLayoutLocalService, KingstonLayoutLocalService> _serviceTracker =
		ServiceTrackerFactory.open(KingstonLayoutLocalService.class);
}