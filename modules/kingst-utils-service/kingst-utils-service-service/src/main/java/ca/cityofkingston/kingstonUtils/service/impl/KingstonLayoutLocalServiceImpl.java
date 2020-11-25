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

package ca.cityofkingston.kingstonUtils.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import ca.cityofkingston.kingstonUtils.service.base.KingstonLayoutLocalServiceBaseImpl;
import ca.cityofkingston.kingstonUtils.util.LayoutMetaData;
import ca.cityofkingston.kingstonUtils.util.LayoutMetaDataFactory;

/**
 * The implementation of the kingston layout local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link ca.cityofkingston.kingstonUtils.service.KingstonLayoutLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KingstonLayoutLocalServiceBaseImpl
 * @see ca.cityofkingston.kingstonUtils.service.KingstonLayoutLocalServiceUtil
 */
public class KingstonLayoutLocalServiceImpl
	extends KingstonLayoutLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link ca.cityofkingston.kingstonUtils.service.KingstonLayoutLocalServiceUtil} to access the kingston layout local service.
	 */
	public JSONObject getPageJSONLD(ThemeDisplay themeDisplay )	{
		
		TimeZone tz = TimeZone.getTimeZone("UTC");
		SimpleDateFormat dateFormatISO8061 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		dateFormatISO8061.setTimeZone(tz);
		
		LayoutMetaData metaData = LayoutMetaDataFactory.getLayoutMetaData(themeDisplay);
		//-- Build JSON output
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
		jsonObj.put("@context", "http://schema.org");
		jsonObj.put("@type", metaData.getType());
		jsonObj.put("url", metaData.getCanonicalURL());
		jsonObj.put("datePublished", dateFormatISO8061.format(metaData.getPublishedDate() ) );
		jsonObj.put("dateModified", dateFormatISO8061.format(metaData.getLastModifiedDate() ));
		jsonObj.put("category", metaData.getCategory());
		
		//-- Add headline if this is a news article
		if (metaData.getType().equals(LayoutMetaData.TYPE_ARTICLE)) {
			
			jsonObj.put("headline", metaData.getHeadline());
			
			JSONObject publisher = JSONFactoryUtil.createJSONObject();
			publisher.put("@type", "Organization");
			publisher.put("name", metaData.getPublisher());
			
			JSONObject logo = JSONFactoryUtil.createJSONObject();
			logo.put("@type","ImageObject");
			logo.put("url", themeDisplay.getPathThemeImages() + "/social/cofk-log-blue-120px.png" );
			publisher.put("logo", logo);
			
			JSONObject author = JSONFactoryUtil.createJSONObject();
			author.put("@type", "Organization");
			author.put("name", metaData.getAuthor());
			
			
			jsonObj.put("author", author);
			jsonObj.put("publisher", publisher);
			
			JSONObject image = JSONFactoryUtil.createJSONObject();
			image.put("@type", "ImageObject");
			image.put("url", metaData.getImageURL());
			image.put("height", metaData.getImageHeight());
			image.put("width", metaData.getImageWidth());
			jsonObj.put("image", image);
		}
		
		return jsonObj;	
	}
	
	public Date getLastModifiedDate(ThemeDisplay themeDisplay)	{
		
		LayoutMetaData metaData = LayoutMetaDataFactory.getLayoutMetaData(themeDisplay);
		return metaData.getLastModifiedDate();
	}
	
	private static final Log log = LogFactoryUtil.getLog(KingstonLayoutLocalServiceImpl.class);
}