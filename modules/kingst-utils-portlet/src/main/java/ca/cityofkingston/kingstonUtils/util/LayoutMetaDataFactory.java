package ca.cityofkingston.kingstonUtils.util;

import java.util.List;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;
import com.liferay.journal.constants.JournalWebKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;

public class LayoutMetaDataFactory {

	private static final Log log = LogFactoryUtil.getLog(LayoutMetaDataFactory.class);
	private	static final String CalEventClassName 	= CalendarBooking.class.getName();
	private static final String JournalClassName 	= JournalArticle.class.getName();


	public static LayoutMetaData getLayoutMetaData(ThemeDisplay themeDisplay) {
		
		LayoutMetaData layoutMetaData = new LayoutMetaData();
		
		//-- CANONICAL URL
		try {
			//-- COK Special
			//-- Update canonical URL for viewing Calendar events
			String completeURL = themeDisplay.getURLCurrent();
			
			if (completeURL.contains("p_p_id=8") && 
				completeURL.contains("_8_struts_action=%2Fcalendar%2Fview_event") &&
				completeURL.contains("_8_eventId=") )
			{
				String eventId = "";
				int pos = completeURL.indexOf("_8_eventId=");
				if (pos > 0) {
					int end = completeURL.indexOf("&",pos);
					
					if (end > 0) {
						eventId = completeURL.substring(pos+"_8_eventId=".length(),end);
					} else {
						eventId = completeURL.substring(pos+"_8_eventId=".length());
					}
					
					String strippedURL = completeURL.substring(0, completeURL.indexOf(StringPool.QUESTION));
					
					//-- FORM <page url>?p_p_id=8&_8_struts_action=/calendar/view_event&_8_eventId=<eventId>
					completeURL = strippedURL + StringPool.QUESTION + "p_p_id=8&_8_struts_action=/calendar/view_event&_8_eventId="+eventId;
				}
			}

			layoutMetaData.setCanonicalURL( PortalUtil.getCanonicalURL(completeURL, themeDisplay, themeDisplay.getLayout()) );
		} catch (Exception e) {
			layoutMetaData.setCanonicalURL( themeDisplay.getURLCurrent() );
		}
	
		//-- Extract inferred data
		layoutMetaData = getInferredLayoutMetaData(themeDisplay,layoutMetaData );
		
		return layoutMetaData;
	}
	
	private static LayoutMetaData getInferredLayoutMetaData(ThemeDisplay themeDisplay, LayoutMetaData layoutMetaData )
	{
		Layout layout = themeDisplay.getLayout();
		String url = themeDisplay.getURLCurrent();
		
		layoutMetaData.setLastModifiedDate(layout.getModifiedDate());
		layoutMetaData.setPublishedDate(layout.getCreateDate());
		layoutMetaData.setCategory(getLayoutCategory(layout));
		
		log.debug("LayoutMetaDataFactory::getInferredLayoutMetaData: layoutModifiedDate=" + layoutMetaData.getLastModifiedDate() + " layoutPublishedDate="+ layoutMetaData.getPublishedDate() );
		
		if (layout.isTypePortlet()) {
			
			List<String> portletIdList = themeDisplay.getLayoutTypePortlet().getPortletIds();
			log.debug("portlet ids:" + java.util.Arrays.toString(portletIdList.toArray() ));
			
			for (String fQPID : portletIdList ) {
				
				String portletId = fQPID.split("_")[0];
				
				//-- IF Web Display Portlet
				//-- THEN
				//--    Check the displayed web content for details like modified date 
				if (portletId.equals(JournalWebKeys.JOURNAL_CONTENT)) {
					log.debug("web content display portlet:" + portletId);
					try {
						log.debug("trying to find prefs for:" + layout.getCompanyId() + "," + themeDisplay.getScopeGroupId() + "," + ResourceConstants.SCOPE_GROUP_TEMPLATE + "," + layout.getPlid() + "," + fQPID);
						javax.portlet.PortletPreferences portletPreferences =	PortletPreferencesFactoryUtil.getPortletSetup( themeDisplay.getScopeGroupId(), layout, fQPID, null);
						
						String articleId = portletPreferences.getValue("articleId","");
			            if (Validator.isNotNull(articleId) )
			            {
			            	JournalArticle article = JournalArticleLocalServiceUtil.getArticle(layout.getGroupId(), articleId);
			            	log.debug("web content display portlet:" + portletId +" has article=" + article.getTitle() + " date="+article.getModifiedDate());
			            	if (article.getModifiedDate().after(layoutMetaData.getLastModifiedDate()))
			            		layoutMetaData.setLastModifiedDate(article.getModifiedDate());
			            }
				
					} catch (Exception e) {
						//-- ignore
						log.error("LayoutMetaDataFactory::getInferredLayoutMetaData() throws exception:"+e.getMessage());
						
					}
				}
				//-- IF Calendar Portlet
				//-- THEN
				//--    Check to see if it is displaying a specific event 
				else if (portletId.equals(PortletKeys.CALENDAR)) {
					log.debug("calendar portlet:" + portletId);
					try {
						
						log.debug("trying to find eventId..." );
						String eventId = getParamValue(url, portletId,"eventId");
						log.debug("Found eventId=" + eventId );
						CalendarBooking calEvent = CalendarBookingLocalServiceUtil.getCalendarBooking(Long.valueOf(eventId));
						if(calEvent != null){
							log.debug("calEvent("+ eventId + ") modifiedDate=" + calEvent.getModifiedDate() );
		            		layoutMetaData.setLastModifiedDate(calEvent.getModifiedDate());
		            		layoutMetaData.setPublishedDate(calEvent.getCreateDate());
		            		layoutMetaData.setCategory(getCalendarCategory(calEvent));
						}
			            /*if ( Validator.isNotNull(eventId) ) {
			            	CalEvent calEvent  = CalEventLocalServiceUtil.getEvent(Long.valueOf(eventId));
			            	if (calEvent != null)
			            	{
			            		log.debug("calEvent("+ eventId + ") modifiedDate=" + calEvent.getModifiedDate() );
			            		layoutMetaData.setLastModifiedDate(calEvent.getModifiedDate());
			            		layoutMetaData.setPublishedDate(calEvent.getCreateDate());
			            		layoutMetaData.setCategory(getCalendarCategory(calEvent));
			            	}
			            }*/
				
					} catch (Exception e) {
						//-- ignore
						log.error("LayoutMetaDataFactory::getInferredLayoutMetaData() throws exception:"+e.getMessage());
						
					}
				}
				//-- IF Blog Portlet
				//-- THEN
				//--    Check to see if it is displaying a specific blog post 
				else if (portletId.equals(PortletKeys.BLOGS)) {
					log.debug("blog portlet:" + portletId);
					log.debug("checking: " + url);
					String urlTitle = extractUrlTitle(url,"blogs/","");
					log.debug("found urlTitle=" + urlTitle);
					
					if (!urlTitle.isEmpty())
					{
						try {
							BlogsEntry blogEntry = BlogsEntryLocalServiceUtil.getEntry(themeDisplay.getScopeGroupId(), urlTitle);
							
							if (blogEntry != null)
							{
								log.debug("blog portlet: display entry=" + blogEntry.getEntryId());
								layoutMetaData.setLastModifiedDate(blogEntry.getModifiedDate());
								layoutMetaData.setPublishedDate(blogEntry.getDisplayDate());
								//-- this takes precedence so stop here.
								break;
							}
						} catch (Exception e) {
						
							log.error("LayoutMetaDataFactory::getInferredLayoutMetaData() throws exception:"+e.getMessage());
						}
					}
				}
			}
			
			//-- IF showing a single web content in an asset publisher 
			//-- THEN
			//--    Extract NewsArticle meta data 
			if (layout.isContentDisplayPage() && url.contains(Portal.FRIENDLY_URL_SEPARATOR))
			{
				//-- look for /-/<urlTitle>
				log.debug("isContentDisplayPage(): " + url);
				String urlTitle = extractUrlTitle(url,"asset_publisher/","/content/");
				log.debug("isContentDisplayPage(): urlTitle=" + urlTitle);
				
				if (!urlTitle.isEmpty())
				{
					try {
						JournalArticle article = JournalArticleLocalServiceUtil.getArticleByUrlTitle(layout.getGroupId(), urlTitle);
						
						if (article != null)
						{
							log.debug("isContentDisplayPage(): found artcile=" + article.getArticleId());
							layoutMetaData.setLastModifiedDate(article.getModifiedDate());
							layoutMetaData.setPublishedDate(article.getDisplayDate());
							
							//-- From 
							if (article.getStructureId().equals("NEWS_TEMPLATE") ) {
								layoutMetaData.setType("Article");
								layoutMetaData.setHeadline(article.getTitle(themeDisplay.getLocale()));
								layoutMetaData.setCategory(getJournalCategory(article));
								
								//-- IF using smallImageURL then use its image
								if(article.getSmallImage()) {
									String imageURL = "";
									if (Validator.isNotNull(article.getSmallImageURL())) {
										imageURL = article.getSmallImageURL();
										layoutMetaData.setImageHeight(0);
										layoutMetaData.setImageWidth(0);
									}
									else {
										imageURL = themeDisplay.getPortalURL() + themeDisplay.getPathImage() + "/journal/article?img_id=" + article.getSmallImageId() + "&t=" + WebServerServletTokenUtil.getToken(article.getSmallImageId()) ;
										layoutMetaData.setImageHeight(83);
										layoutMetaData.setImageWidth(165);
										
									}

									layoutMetaData.setImageURL( imageURL );
								} else {
									layoutMetaData.setImageURL( themeDisplay.getPathThemeImages() + "/social/cityofkingston-default-news.png");
									layoutMetaData.setImageHeight(132);
									layoutMetaData.setImageWidth(120);
								}
							}
						}
					} catch (Exception e) {
					
						//-- ignor and continue
					}
				}
			}
		}
			
		
		return layoutMetaData;
	}

	private static String getJournalCategory(JournalArticle article) {
		String category = CludoUtil.CATEGORY_NEWS;
		AssetVocabulary committeeVocab = null;
		AssetVocabulary projectVocab = null;
		
		//-- Committee Vocab
		try {
			committeeVocab = AssetVocabularyLocalServiceUtil.getGroupVocabulary(article.getGroupId(), "Committee");
		} catch (Exception e) {
			//-- Ignore
		} 
		
		log.debug("getJournalCategory(): article.getGroupId()="+article.getGroupId()+" committeeVocab="+committeeVocab);

		//-- Project Vocab
		try {
			projectVocab = AssetVocabularyLocalServiceUtil.getGroupVocabulary(article.getGroupId(), "Projects");
		} catch (Exception e) {
			//-- Ignore
		} 

		try {
			List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getCategories(JournalClassName, article.getResourcePrimKey());
			
			for(AssetCategory cat : categories ) {
				log.debug("getJournalCategory(): checking cat=" + cat.getName() + " cat-parent=" + cat.getVocabularyId());

				if (cat.getName().equals("City Council"))
					return CludoUtil.CATEGORY_COUNCIL;			

				if (committeeVocab != null && committeeVocab.getVocabularyId() == cat.getVocabularyId())
					return CludoUtil.CATEGORY_COMMITTEE;
				
				if (projectVocab != null && projectVocab.getVocabularyId() == cat.getVocabularyId())
					return CludoUtil.CATEGORY_PROJECTS;
				
			}
		} catch (SystemException e) {
			//-- ignore
		}
		
		
		
		return category;
	}

	private static String getCalendarCategory(CalendarBooking calEvent) {
		String category = CludoUtil.CATEGORY_EVENTS;
		AssetVocabulary committeeVocab = null;
		
		try {
			committeeVocab = AssetVocabularyLocalServiceUtil.getGroupVocabulary(calEvent.getGroupId(), "Committee");
		} catch (Exception e1) {
			//-- Ignore
		} 
		try {
			List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getCategories(CalEventClassName, calEvent.getCalendarBookingId());
			
			for(AssetCategory cat : categories ) {
				log.debug("getCalendarCategory(): checking cat=" + cat.getName() + " cat-parent=" + cat.getVocabularyId());

				if (cat.getName().equals("City Council"))
					return CludoUtil.CATEGORY_COUNCIL;			

				if (committeeVocab != null && committeeVocab.getVocabularyId() == cat.getVocabularyId())
					return CludoUtil.CATEGORY_COMMITTEE;
				
				
			}
		} catch (SystemException e) {
			//-- ignore
		}
		
		
		
		return category;
	}

	private static String getLayoutCategory(Layout layout) {
		
		String category = ExpandoUtil.getSingleton(layout, ExpandoUtil.EXPANDO_CATEGORY);
		
		//-- IF category isn't set for this layout
		//-- THEN
		//--   Recursively walk up the layout heirarchy looking for the first value set.
		if ((category == null || category.isEmpty()) && layout.getParentLayoutId() != LayoutConstants.DEFAULT_PARENT_LAYOUT_ID )
		{
			
			try {
				category = getLayoutCategory( LayoutLocalServiceUtil.getLayout(layout.getParentPlid()));
			} catch (Exception e) {
				//-- ignore
			}
		}
		
		return category;
	}

	private static String extractUrlTitle(String url, String prefix, String postfix) {
		String urlTitle = "";

		int pos = url.indexOf(Portal.FRIENDLY_URL_SEPARATOR);
		
		//-- IF has friendly URL pattern /-/<urlTitle>
		if (pos >= 0)
		{
			urlTitle = url.substring(pos+Portal.FRIENDLY_URL_SEPARATOR.length());
			
			//-- IF the the urlTitle starts with "prefix/"
			//-- THEN remove the prefix
			if (Validator.isNotNull(prefix) && urlTitle.startsWith(prefix))
			{
				log.debug("extractUrlTitle(): starts with AP=" + urlTitle);
				urlTitle = urlTitle.substring(prefix.length());
				log.debug("extractUrlTitle(): after AP=" + urlTitle);
				
				if (Validator.isNotNull(postfix)) 
				{
					pos = urlTitle.indexOf(postfix);
					
					//-- remove postfix
					if (pos >= 0)
					{
						urlTitle = urlTitle.substring(pos + postfix.length());
						log.debug("extractUrlTitle(): after content=" + urlTitle);
					}
				}
			}
			
			//-- stip ? and everything to the right.
			pos = urlTitle.indexOf("?");
			if (pos >= 0)
			{
				urlTitle = urlTitle.substring(0,pos);
			}
			
			pos = urlTitle.indexOf("/");
			if (pos >= 0)
			{
				urlTitle = urlTitle.substring(0,pos);
			}
			
		}
		return urlTitle;
	}

	//-- This returns value of first param
	private static String getParamValue(String url, String portletId, String paramName ) {
		
		String result = "";
		String fullParamName = "_" + portletId + "_" + paramName;
		String queryString = url.substring(url.indexOf("?")+1);
		
		int pos = queryString.indexOf(fullParamName + "=");
		if (pos >= 0)
		{
			result = queryString.substring(pos+fullParamName.length()+1);
			
			//-- remove any additional &
			pos = result.indexOf("&");
			if (pos >= 0 )
			{
				result = result.substring(0,pos);
			}
		}
		return result;
	}
	
}
