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

package ca.cityofkingston.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import ca.cityofkingston.exception.NoSuchStaffPeopleException;

import ca.cityofkingston.model.StaffPeople;
import ca.cityofkingston.model.impl.StaffPeopleImpl;
import ca.cityofkingston.model.impl.StaffPeopleModelImpl;

import ca.cityofkingston.service.persistence.StaffPeoplePersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the staff people service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StaffPeoplePersistence
 * @see ca.cityofkingston.service.persistence.StaffPeopleUtil
 * @generated
 */
@ProviderType
public class StaffPeoplePersistenceImpl extends BasePersistenceImpl<StaffPeople>
	implements StaffPeoplePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StaffPeopleUtil} to access the staff people persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StaffPeopleImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
			StaffPeopleModelImpl.FINDER_CACHE_ENABLED, StaffPeopleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
			StaffPeopleModelImpl.FINDER_CACHE_ENABLED, StaffPeopleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
			StaffPeopleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LASTNAMEANDFIRSTNAME =
		new FinderPath(StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
			StaffPeopleModelImpl.FINDER_CACHE_ENABLED, StaffPeopleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLastnameAndFirstname",
			new String[] {
				String.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LASTNAMEANDFIRSTNAME =
		new FinderPath(StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
			StaffPeopleModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"countByLastnameAndFirstname",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns all the staff peoples where last LIKE &#63; and first LIKE &#63;.
	 *
	 * @param last the last
	 * @param first the first
	 * @return the matching staff peoples
	 */
	@Override
	public List<StaffPeople> findByLastnameAndFirstname(String last,
		String first) {
		return findByLastnameAndFirstname(last, first, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<StaffPeople> findByLastnameAndFirstname(String last,
		String first, int start, int end) {
		return findByLastnameAndFirstname(last, first, start, end, null);
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
	@Override
	public List<StaffPeople> findByLastnameAndFirstname(String last,
		String first, int start, int end,
		OrderByComparator<StaffPeople> orderByComparator) {
		return findByLastnameAndFirstname(last, first, start, end,
			orderByComparator, true);
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
	@Override
	public List<StaffPeople> findByLastnameAndFirstname(String last,
		String first, int start, int end,
		OrderByComparator<StaffPeople> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LASTNAMEANDFIRSTNAME;
		finderArgs = new Object[] { last, first, start, end, orderByComparator };

		List<StaffPeople> list = null;

		if (retrieveFromCache) {
			list = (List<StaffPeople>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (StaffPeople staffPeople : list) {
					if (!StringUtil.wildcardMatches(staffPeople.getLast(),
								last, CharPool.UNDERLINE, CharPool.PERCENT,
								CharPool.BACK_SLASH, false) ||
							!StringUtil.wildcardMatches(
								staffPeople.getFirst(), first,
								CharPool.UNDERLINE, CharPool.PERCENT,
								CharPool.BACK_SLASH, false)) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_STAFFPEOPLE_WHERE);

			boolean bindLast = false;

			if (last == null) {
				query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_LAST_1);
			}
			else if (last.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_LAST_3);
			}
			else {
				bindLast = true;

				query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_LAST_2);
			}

			boolean bindFirst = false;

			if (first == null) {
				query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_FIRST_1);
			}
			else if (first.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_FIRST_3);
			}
			else {
				bindFirst = true;

				query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_FIRST_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(StaffPeopleModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLast) {
					qPos.add(StringUtil.toLowerCase(last));
				}

				if (bindFirst) {
					qPos.add(StringUtil.toLowerCase(first));
				}

				if (!pagination) {
					list = (List<StaffPeople>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StaffPeople>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first staff people in the ordered set where last LIKE &#63; and first LIKE &#63;.
	 *
	 * @param last the last
	 * @param first the first
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching staff people
	 * @throws NoSuchStaffPeopleException if a matching staff people could not be found
	 */
	@Override
	public StaffPeople findByLastnameAndFirstname_First(String last,
		String first, OrderByComparator<StaffPeople> orderByComparator)
		throws NoSuchStaffPeopleException {
		StaffPeople staffPeople = fetchByLastnameAndFirstname_First(last,
				first, orderByComparator);

		if (staffPeople != null) {
			return staffPeople;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("last=");
		msg.append(last);

		msg.append(", first=");
		msg.append(first);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStaffPeopleException(msg.toString());
	}

	/**
	 * Returns the first staff people in the ordered set where last LIKE &#63; and first LIKE &#63;.
	 *
	 * @param last the last
	 * @param first the first
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching staff people, or <code>null</code> if a matching staff people could not be found
	 */
	@Override
	public StaffPeople fetchByLastnameAndFirstname_First(String last,
		String first, OrderByComparator<StaffPeople> orderByComparator) {
		List<StaffPeople> list = findByLastnameAndFirstname(last, first, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public StaffPeople findByLastnameAndFirstname_Last(String last,
		String first, OrderByComparator<StaffPeople> orderByComparator)
		throws NoSuchStaffPeopleException {
		StaffPeople staffPeople = fetchByLastnameAndFirstname_Last(last, first,
				orderByComparator);

		if (staffPeople != null) {
			return staffPeople;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("last=");
		msg.append(last);

		msg.append(", first=");
		msg.append(first);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchStaffPeopleException(msg.toString());
	}

	/**
	 * Returns the last staff people in the ordered set where last LIKE &#63; and first LIKE &#63;.
	 *
	 * @param last the last
	 * @param first the first
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching staff people, or <code>null</code> if a matching staff people could not be found
	 */
	@Override
	public StaffPeople fetchByLastnameAndFirstname_Last(String last,
		String first, OrderByComparator<StaffPeople> orderByComparator) {
		int count = countByLastnameAndFirstname(last, first);

		if (count == 0) {
			return null;
		}

		List<StaffPeople> list = findByLastnameAndFirstname(last, first,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public StaffPeople[] findByLastnameAndFirstname_PrevAndNext(long id,
		String last, String first,
		OrderByComparator<StaffPeople> orderByComparator)
		throws NoSuchStaffPeopleException {
		StaffPeople staffPeople = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			StaffPeople[] array = new StaffPeopleImpl[3];

			array[0] = getByLastnameAndFirstname_PrevAndNext(session,
					staffPeople, last, first, orderByComparator, true);

			array[1] = staffPeople;

			array[2] = getByLastnameAndFirstname_PrevAndNext(session,
					staffPeople, last, first, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected StaffPeople getByLastnameAndFirstname_PrevAndNext(
		Session session, StaffPeople staffPeople, String last, String first,
		OrderByComparator<StaffPeople> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_STAFFPEOPLE_WHERE);

		boolean bindLast = false;

		if (last == null) {
			query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_LAST_1);
		}
		else if (last.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_LAST_3);
		}
		else {
			bindLast = true;

			query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_LAST_2);
		}

		boolean bindFirst = false;

		if (first == null) {
			query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_FIRST_1);
		}
		else if (first.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_FIRST_3);
		}
		else {
			bindFirst = true;

			query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_FIRST_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(StaffPeopleModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindLast) {
			qPos.add(StringUtil.toLowerCase(last));
		}

		if (bindFirst) {
			qPos.add(StringUtil.toLowerCase(first));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(staffPeople);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<StaffPeople> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the staff peoples where last LIKE &#63; and first LIKE &#63; from the database.
	 *
	 * @param last the last
	 * @param first the first
	 */
	@Override
	public void removeByLastnameAndFirstname(String last, String first) {
		for (StaffPeople staffPeople : findByLastnameAndFirstname(last, first,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(staffPeople);
		}
	}

	/**
	 * Returns the number of staff peoples where last LIKE &#63; and first LIKE &#63;.
	 *
	 * @param last the last
	 * @param first the first
	 * @return the number of matching staff peoples
	 */
	@Override
	public int countByLastnameAndFirstname(String last, String first) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_LASTNAMEANDFIRSTNAME;

		Object[] finderArgs = new Object[] { last, first };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_STAFFPEOPLE_WHERE);

			boolean bindLast = false;

			if (last == null) {
				query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_LAST_1);
			}
			else if (last.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_LAST_3);
			}
			else {
				bindLast = true;

				query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_LAST_2);
			}

			boolean bindFirst = false;

			if (first == null) {
				query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_FIRST_1);
			}
			else if (first.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_FIRST_3);
			}
			else {
				bindFirst = true;

				query.append(_FINDER_COLUMN_LASTNAMEANDFIRSTNAME_FIRST_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLast) {
					qPos.add(StringUtil.toLowerCase(last));
				}

				if (bindFirst) {
					qPos.add(StringUtil.toLowerCase(first));
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_LASTNAMEANDFIRSTNAME_LAST_1 = "staffPeople.last IS NULL AND ";
	private static final String _FINDER_COLUMN_LASTNAMEANDFIRSTNAME_LAST_2 = "lower(staffPeople.last) LIKE ? AND ";
	private static final String _FINDER_COLUMN_LASTNAMEANDFIRSTNAME_LAST_3 = "(staffPeople.last IS NULL OR staffPeople.last LIKE '') AND ";
	private static final String _FINDER_COLUMN_LASTNAMEANDFIRSTNAME_FIRST_1 = "staffPeople.first IS NULL";
	private static final String _FINDER_COLUMN_LASTNAMEANDFIRSTNAME_FIRST_2 = "lower(staffPeople.first) LIKE ?";
	private static final String _FINDER_COLUMN_LASTNAMEANDFIRSTNAME_FIRST_3 = "(staffPeople.first IS NULL OR staffPeople.first LIKE '')";

	public StaffPeoplePersistenceImpl() {
		setModelClass(StaffPeople.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("id", "id_");
			dbColumnNames.put("group", "group_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the staff people in the entity cache if it is enabled.
	 *
	 * @param staffPeople the staff people
	 */
	@Override
	public void cacheResult(StaffPeople staffPeople) {
		entityCache.putResult(StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
			StaffPeopleImpl.class, staffPeople.getPrimaryKey(), staffPeople);

		staffPeople.resetOriginalValues();
	}

	/**
	 * Caches the staff peoples in the entity cache if it is enabled.
	 *
	 * @param staffPeoples the staff peoples
	 */
	@Override
	public void cacheResult(List<StaffPeople> staffPeoples) {
		for (StaffPeople staffPeople : staffPeoples) {
			if (entityCache.getResult(
						StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
						StaffPeopleImpl.class, staffPeople.getPrimaryKey()) == null) {
				cacheResult(staffPeople);
			}
			else {
				staffPeople.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all staff peoples.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StaffPeopleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the staff people.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StaffPeople staffPeople) {
		entityCache.removeResult(StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
			StaffPeopleImpl.class, staffPeople.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StaffPeople> staffPeoples) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StaffPeople staffPeople : staffPeoples) {
			entityCache.removeResult(StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
				StaffPeopleImpl.class, staffPeople.getPrimaryKey());
		}
	}

	/**
	 * Creates a new staff people with the primary key. Does not add the staff people to the database.
	 *
	 * @param id the primary key for the new staff people
	 * @return the new staff people
	 */
	@Override
	public StaffPeople create(long id) {
		StaffPeople staffPeople = new StaffPeopleImpl();

		staffPeople.setNew(true);
		staffPeople.setPrimaryKey(id);

		return staffPeople;
	}

	/**
	 * Removes the staff people with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the staff people
	 * @return the staff people that was removed
	 * @throws NoSuchStaffPeopleException if a staff people with the primary key could not be found
	 */
	@Override
	public StaffPeople remove(long id) throws NoSuchStaffPeopleException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the staff people with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the staff people
	 * @return the staff people that was removed
	 * @throws NoSuchStaffPeopleException if a staff people with the primary key could not be found
	 */
	@Override
	public StaffPeople remove(Serializable primaryKey)
		throws NoSuchStaffPeopleException {
		Session session = null;

		try {
			session = openSession();

			StaffPeople staffPeople = (StaffPeople)session.get(StaffPeopleImpl.class,
					primaryKey);

			if (staffPeople == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStaffPeopleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(staffPeople);
		}
		catch (NoSuchStaffPeopleException nsee) {
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
	protected StaffPeople removeImpl(StaffPeople staffPeople) {
		staffPeople = toUnwrappedModel(staffPeople);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(staffPeople)) {
				staffPeople = (StaffPeople)session.get(StaffPeopleImpl.class,
						staffPeople.getPrimaryKeyObj());
			}

			if (staffPeople != null) {
				session.delete(staffPeople);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (staffPeople != null) {
			clearCache(staffPeople);
		}

		return staffPeople;
	}

	@Override
	public StaffPeople updateImpl(StaffPeople staffPeople) {
		staffPeople = toUnwrappedModel(staffPeople);

		boolean isNew = staffPeople.isNew();

		Session session = null;

		try {
			session = openSession();

			if (staffPeople.isNew()) {
				session.save(staffPeople);

				staffPeople.setNew(false);
			}
			else {
				staffPeople = (StaffPeople)session.merge(staffPeople);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!StaffPeopleModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
			StaffPeopleImpl.class, staffPeople.getPrimaryKey(), staffPeople,
			false);

		staffPeople.resetOriginalValues();

		return staffPeople;
	}

	protected StaffPeople toUnwrappedModel(StaffPeople staffPeople) {
		if (staffPeople instanceof StaffPeopleImpl) {
			return staffPeople;
		}

		StaffPeopleImpl staffPeopleImpl = new StaffPeopleImpl();

		staffPeopleImpl.setNew(staffPeople.isNew());
		staffPeopleImpl.setPrimaryKey(staffPeople.getPrimaryKey());

		staffPeopleImpl.setId(staffPeople.getId());
		staffPeopleImpl.setGroup(staffPeople.getGroup());
		staffPeopleImpl.setDepartment(staffPeople.getDepartment());
		staffPeopleImpl.setDivision(staffPeople.getDivision());
		staffPeopleImpl.setUnit(staffPeople.getUnit());
		staffPeopleImpl.setTitle(staffPeople.getTitle());
		staffPeopleImpl.setLast(staffPeople.getLast());
		staffPeopleImpl.setFirst(staffPeople.getFirst());
		staffPeopleImpl.setName(staffPeople.getName());
		staffPeopleImpl.setPhone(staffPeople.getPhone());
		staffPeopleImpl.setFax(staffPeople.getFax());
		staffPeopleImpl.setEmail(staffPeople.getEmail());
		staffPeopleImpl.setTstamp(staffPeople.getTstamp());

		return staffPeopleImpl;
	}

	/**
	 * Returns the staff people with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the staff people
	 * @return the staff people
	 * @throws NoSuchStaffPeopleException if a staff people with the primary key could not be found
	 */
	@Override
	public StaffPeople findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStaffPeopleException {
		StaffPeople staffPeople = fetchByPrimaryKey(primaryKey);

		if (staffPeople == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStaffPeopleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return staffPeople;
	}

	/**
	 * Returns the staff people with the primary key or throws a {@link NoSuchStaffPeopleException} if it could not be found.
	 *
	 * @param id the primary key of the staff people
	 * @return the staff people
	 * @throws NoSuchStaffPeopleException if a staff people with the primary key could not be found
	 */
	@Override
	public StaffPeople findByPrimaryKey(long id)
		throws NoSuchStaffPeopleException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the staff people with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the staff people
	 * @return the staff people, or <code>null</code> if a staff people with the primary key could not be found
	 */
	@Override
	public StaffPeople fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
				StaffPeopleImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StaffPeople staffPeople = (StaffPeople)serializable;

		if (staffPeople == null) {
			Session session = null;

			try {
				session = openSession();

				staffPeople = (StaffPeople)session.get(StaffPeopleImpl.class,
						primaryKey);

				if (staffPeople != null) {
					cacheResult(staffPeople);
				}
				else {
					entityCache.putResult(StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
						StaffPeopleImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
					StaffPeopleImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return staffPeople;
	}

	/**
	 * Returns the staff people with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the staff people
	 * @return the staff people, or <code>null</code> if a staff people with the primary key could not be found
	 */
	@Override
	public StaffPeople fetchByPrimaryKey(long id) {
		return fetchByPrimaryKey((Serializable)id);
	}

	@Override
	public Map<Serializable, StaffPeople> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StaffPeople> map = new HashMap<Serializable, StaffPeople>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			StaffPeople staffPeople = fetchByPrimaryKey(primaryKey);

			if (staffPeople != null) {
				map.put(primaryKey, staffPeople);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
					StaffPeopleImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (StaffPeople)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_STAFFPEOPLE_WHERE_PKS_IN);

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

			for (StaffPeople staffPeople : (List<StaffPeople>)q.list()) {
				map.put(staffPeople.getPrimaryKeyObj(), staffPeople);

				cacheResult(staffPeople);

				uncachedPrimaryKeys.remove(staffPeople.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(StaffPeopleModelImpl.ENTITY_CACHE_ENABLED,
					StaffPeopleImpl.class, primaryKey, nullModel);
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
	 * Returns all the staff peoples.
	 *
	 * @return the staff peoples
	 */
	@Override
	public List<StaffPeople> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<StaffPeople> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<StaffPeople> findAll(int start, int end,
		OrderByComparator<StaffPeople> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<StaffPeople> findAll(int start, int end,
		OrderByComparator<StaffPeople> orderByComparator,
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

		List<StaffPeople> list = null;

		if (retrieveFromCache) {
			list = (List<StaffPeople>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STAFFPEOPLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STAFFPEOPLE;

				if (pagination) {
					sql = sql.concat(StaffPeopleModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StaffPeople>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StaffPeople>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the staff peoples from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StaffPeople staffPeople : findAll()) {
			remove(staffPeople);
		}
	}

	/**
	 * Returns the number of staff peoples.
	 *
	 * @return the number of staff peoples
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STAFFPEOPLE);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return StaffPeopleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the staff people persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StaffPeopleImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STAFFPEOPLE = "SELECT staffPeople FROM StaffPeople staffPeople";
	private static final String _SQL_SELECT_STAFFPEOPLE_WHERE_PKS_IN = "SELECT staffPeople FROM StaffPeople staffPeople WHERE id_ IN (";
	private static final String _SQL_SELECT_STAFFPEOPLE_WHERE = "SELECT staffPeople FROM StaffPeople staffPeople WHERE ";
	private static final String _SQL_COUNT_STAFFPEOPLE = "SELECT COUNT(staffPeople) FROM StaffPeople staffPeople";
	private static final String _SQL_COUNT_STAFFPEOPLE_WHERE = "SELECT COUNT(staffPeople) FROM StaffPeople staffPeople WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "staffPeople.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StaffPeople exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No StaffPeople exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(StaffPeoplePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"id", "group"
			});
}