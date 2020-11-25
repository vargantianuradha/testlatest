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

package ca.cityofkingston.kingstonUtils.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import ca.cityofkingston.kingstonUtils.exception.NoSuchKingstonLayoutException;
import ca.cityofkingston.kingstonUtils.model.KingstonLayout;
import ca.cityofkingston.kingstonUtils.model.impl.KingstonLayoutImpl;
import ca.cityofkingston.kingstonUtils.model.impl.KingstonLayoutModelImpl;
import ca.cityofkingston.kingstonUtils.service.persistence.KingstonLayoutPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the kingston layout service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KingstonLayoutPersistence
 * @see ca.cityofkingston.kingstonUtils.service.persistence.KingstonLayoutUtil
 * @generated
 */
@ProviderType
public class KingstonLayoutPersistenceImpl extends BasePersistenceImpl<KingstonLayout>
	implements KingstonLayoutPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KingstonLayoutUtil} to access the kingston layout persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KingstonLayoutImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KingstonLayoutModelImpl.ENTITY_CACHE_ENABLED,
			KingstonLayoutModelImpl.FINDER_CACHE_ENABLED,
			KingstonLayoutImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KingstonLayoutModelImpl.ENTITY_CACHE_ENABLED,
			KingstonLayoutModelImpl.FINDER_CACHE_ENABLED,
			KingstonLayoutImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KingstonLayoutModelImpl.ENTITY_CACHE_ENABLED,
			KingstonLayoutModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public KingstonLayoutPersistenceImpl() {
		setModelClass(KingstonLayout.class);
	}

	/**
	 * Caches the kingston layout in the entity cache if it is enabled.
	 *
	 * @param kingstonLayout the kingston layout
	 */
	@Override
	public void cacheResult(KingstonLayout kingstonLayout) {
		entityCache.putResult(KingstonLayoutModelImpl.ENTITY_CACHE_ENABLED,
			KingstonLayoutImpl.class, kingstonLayout.getPrimaryKey(),
			kingstonLayout);

		kingstonLayout.resetOriginalValues();
	}

	/**
	 * Caches the kingston layouts in the entity cache if it is enabled.
	 *
	 * @param kingstonLayouts the kingston layouts
	 */
	@Override
	public void cacheResult(List<KingstonLayout> kingstonLayouts) {
		for (KingstonLayout kingstonLayout : kingstonLayouts) {
			if (entityCache.getResult(
						KingstonLayoutModelImpl.ENTITY_CACHE_ENABLED,
						KingstonLayoutImpl.class, kingstonLayout.getPrimaryKey()) == null) {
				cacheResult(kingstonLayout);
			}
			else {
				kingstonLayout.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kingston layouts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(KingstonLayoutImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kingston layout.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KingstonLayout kingstonLayout) {
		entityCache.removeResult(KingstonLayoutModelImpl.ENTITY_CACHE_ENABLED,
			KingstonLayoutImpl.class, kingstonLayout.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<KingstonLayout> kingstonLayouts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KingstonLayout kingstonLayout : kingstonLayouts) {
			entityCache.removeResult(KingstonLayoutModelImpl.ENTITY_CACHE_ENABLED,
				KingstonLayoutImpl.class, kingstonLayout.getPrimaryKey());
		}
	}

	/**
	 * Creates a new kingston layout with the primary key. Does not add the kingston layout to the database.
	 *
	 * @param kId the primary key for the new kingston layout
	 * @return the new kingston layout
	 */
	@Override
	public KingstonLayout create(long kId) {
		KingstonLayout kingstonLayout = new KingstonLayoutImpl();

		kingstonLayout.setNew(true);
		kingstonLayout.setPrimaryKey(kId);

		kingstonLayout.setCompanyId(companyProvider.getCompanyId());

		return kingstonLayout;
	}

	/**
	 * Removes the kingston layout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kId the primary key of the kingston layout
	 * @return the kingston layout that was removed
	 * @throws NoSuchKingstonLayoutException if a kingston layout with the primary key could not be found
	 */
	@Override
	public KingstonLayout remove(long kId) throws NoSuchKingstonLayoutException {
		return remove((Serializable)kId);
	}

	/**
	 * Removes the kingston layout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kingston layout
	 * @return the kingston layout that was removed
	 * @throws NoSuchKingstonLayoutException if a kingston layout with the primary key could not be found
	 */
	@Override
	public KingstonLayout remove(Serializable primaryKey)
		throws NoSuchKingstonLayoutException {
		Session session = null;

		try {
			session = openSession();

			KingstonLayout kingstonLayout = (KingstonLayout)session.get(KingstonLayoutImpl.class,
					primaryKey);

			if (kingstonLayout == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchKingstonLayoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kingstonLayout);
		}
		catch (NoSuchKingstonLayoutException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected KingstonLayout removeImpl(KingstonLayout kingstonLayout) {
		kingstonLayout = toUnwrappedModel(kingstonLayout);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kingstonLayout)) {
				kingstonLayout = (KingstonLayout)session.get(KingstonLayoutImpl.class,
						kingstonLayout.getPrimaryKeyObj());
			}

			if (kingstonLayout != null) {
				session.delete(kingstonLayout);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kingstonLayout != null) {
			clearCache(kingstonLayout);
		}

		return kingstonLayout;
	}

	@Override
	public KingstonLayout updateImpl(KingstonLayout kingstonLayout) {
		kingstonLayout = toUnwrappedModel(kingstonLayout);

		boolean isNew = kingstonLayout.isNew();

		KingstonLayoutModelImpl kingstonLayoutModelImpl = (KingstonLayoutModelImpl)kingstonLayout;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (kingstonLayout.getCreateDate() == null)) {
			if (serviceContext == null) {
				kingstonLayout.setCreateDate(now);
			}
			else {
				kingstonLayout.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!kingstonLayoutModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				kingstonLayout.setModifiedDate(now);
			}
			else {
				kingstonLayout.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (kingstonLayout.isNew()) {
				session.save(kingstonLayout);

				kingstonLayout.setNew(false);
			}
			else {
				kingstonLayout = (KingstonLayout)session.merge(kingstonLayout);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(KingstonLayoutModelImpl.ENTITY_CACHE_ENABLED,
			KingstonLayoutImpl.class, kingstonLayout.getPrimaryKey(),
			kingstonLayout, false);

		kingstonLayout.resetOriginalValues();

		return kingstonLayout;
	}

	protected KingstonLayout toUnwrappedModel(KingstonLayout kingstonLayout) {
		if (kingstonLayout instanceof KingstonLayoutImpl) {
			return kingstonLayout;
		}

		KingstonLayoutImpl kingstonLayoutImpl = new KingstonLayoutImpl();

		kingstonLayoutImpl.setNew(kingstonLayout.isNew());
		kingstonLayoutImpl.setPrimaryKey(kingstonLayout.getPrimaryKey());

		kingstonLayoutImpl.setKId(kingstonLayout.getKId());
		kingstonLayoutImpl.setGroupId(kingstonLayout.getGroupId());
		kingstonLayoutImpl.setCompanyId(kingstonLayout.getCompanyId());
		kingstonLayoutImpl.setUserId(kingstonLayout.getUserId());
		kingstonLayoutImpl.setUserName(kingstonLayout.getUserName());
		kingstonLayoutImpl.setCreateDate(kingstonLayout.getCreateDate());
		kingstonLayoutImpl.setModifiedDate(kingstonLayout.getModifiedDate());

		return kingstonLayoutImpl;
	}

	/**
	 * Returns the kingston layout with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kingston layout
	 * @return the kingston layout
	 * @throws NoSuchKingstonLayoutException if a kingston layout with the primary key could not be found
	 */
	@Override
	public KingstonLayout findByPrimaryKey(Serializable primaryKey)
		throws NoSuchKingstonLayoutException {
		KingstonLayout kingstonLayout = fetchByPrimaryKey(primaryKey);

		if (kingstonLayout == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchKingstonLayoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kingstonLayout;
	}

	/**
	 * Returns the kingston layout with the primary key or throws a {@link NoSuchKingstonLayoutException} if it could not be found.
	 *
	 * @param kId the primary key of the kingston layout
	 * @return the kingston layout
	 * @throws NoSuchKingstonLayoutException if a kingston layout with the primary key could not be found
	 */
	@Override
	public KingstonLayout findByPrimaryKey(long kId)
		throws NoSuchKingstonLayoutException {
		return findByPrimaryKey((Serializable)kId);
	}

	/**
	 * Returns the kingston layout with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kingston layout
	 * @return the kingston layout, or <code>null</code> if a kingston layout with the primary key could not be found
	 */
	@Override
	public KingstonLayout fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(KingstonLayoutModelImpl.ENTITY_CACHE_ENABLED,
				KingstonLayoutImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		KingstonLayout kingstonLayout = (KingstonLayout)serializable;

		if (kingstonLayout == null) {
			Session session = null;

			try {
				session = openSession();

				kingstonLayout = (KingstonLayout)session.get(KingstonLayoutImpl.class,
						primaryKey);

				if (kingstonLayout != null) {
					cacheResult(kingstonLayout);
				}
				else {
					entityCache.putResult(KingstonLayoutModelImpl.ENTITY_CACHE_ENABLED,
						KingstonLayoutImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(KingstonLayoutModelImpl.ENTITY_CACHE_ENABLED,
					KingstonLayoutImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kingstonLayout;
	}

	/**
	 * Returns the kingston layout with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kId the primary key of the kingston layout
	 * @return the kingston layout, or <code>null</code> if a kingston layout with the primary key could not be found
	 */
	@Override
	public KingstonLayout fetchByPrimaryKey(long kId) {
		return fetchByPrimaryKey((Serializable)kId);
	}

	@Override
	public Map<Serializable, KingstonLayout> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KingstonLayout> map = new HashMap<Serializable, KingstonLayout>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KingstonLayout kingstonLayout = fetchByPrimaryKey(primaryKey);

			if (kingstonLayout != null) {
				map.put(primaryKey, kingstonLayout);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(KingstonLayoutModelImpl.ENTITY_CACHE_ENABLED,
					KingstonLayoutImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (KingstonLayout)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KINGSTONLAYOUT_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (KingstonLayout kingstonLayout : (List<KingstonLayout>)q.list()) {
				map.put(kingstonLayout.getPrimaryKeyObj(), kingstonLayout);

				cacheResult(kingstonLayout);

				uncachedPrimaryKeys.remove(kingstonLayout.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(KingstonLayoutModelImpl.ENTITY_CACHE_ENABLED,
					KingstonLayoutImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the kingston layouts.
	 *
	 * @return the kingston layouts
	 */
	@Override
	public List<KingstonLayout> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<KingstonLayout> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<KingstonLayout> findAll(int start, int end,
		OrderByComparator<KingstonLayout> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<KingstonLayout> findAll(int start, int end,
		OrderByComparator<KingstonLayout> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<KingstonLayout> list = null;

		if (retrieveFromCache) {
			list = (List<KingstonLayout>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_KINGSTONLAYOUT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KINGSTONLAYOUT;

				if (pagination) {
					sql = sql.concat(KingstonLayoutModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KingstonLayout>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KingstonLayout>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the kingston layouts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KingstonLayout kingstonLayout : findAll()) {
			remove(kingstonLayout);
		}
	}

	/**
	 * Returns the number of kingston layouts.
	 *
	 * @return the number of kingston layouts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KINGSTONLAYOUT);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return KingstonLayoutModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the kingston layout persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(KingstonLayoutImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_KINGSTONLAYOUT = "SELECT kingstonLayout FROM KingstonLayout kingstonLayout";
	private static final String _SQL_SELECT_KINGSTONLAYOUT_WHERE_PKS_IN = "SELECT kingstonLayout FROM KingstonLayout kingstonLayout WHERE kId IN (";
	private static final String _SQL_COUNT_KINGSTONLAYOUT = "SELECT COUNT(kingstonLayout) FROM KingstonLayout kingstonLayout";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kingstonLayout.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KingstonLayout exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(KingstonLayoutPersistenceImpl.class);
}