package ca.cityofkingston.kingstonUtils.util;

import java.util.List;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
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
	public static final String EXPANDO_CATEGORY = "category";
	 public static final String JOURNAL_CONTENT = "56";
	 //private static final String CalEventClassName 	= CalEvent.class.getName();
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
			layoutMetaData = getInferredLayoutMetaData(themeDisplay,layoutMetaData );
		} catch (Exception e) {
			layoutMetaData.setCanonicalURL( themeDisplay.getURLCurrent() );
		}
	
		//-- Extract inferred data
		
		
		return layoutMetaData;
	}
	
	private static LayoutMetaData getInferredLayoutMetaData(ThemeDisplay themeDisplay, LayoutMetaData layoutMetaData )
	{
		Layout layout = themeDisplay.getLayout();
		String url = themeDisplay.getURLCurrent();
		String defaultPreferences = null;
		long scopeGroupId = themeDisplay.getScopeGroupId();
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
				if (portletId.equals(JOURNAL_CONTENT)) {
					log.debug("web content display portlet:" + portletId);
					try {
						log.debug("trying to find prefs for:" + layout.getCompanyId() + "," + themeDisplay.getScopeGroupId() + "," + ResourceConstants.SCOPE_GROUP_TEMPLATE + "," + layout.getPlid() + "," + fQPID);
						javax.portlet.PortletPreferences portletPreferences = PortletPreferencesFactoryUtil.getPortletSetup(scopeGroupId, layout, fQPID, defaultPreferences);
						
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
						
			            if ( Validator.isNotNull(eventId) )
			            {/*
			            	CalEvent calEvent  = CalEventLocalServiceUtil.getEvent(Long.valueOf(eventId));
			            	if (calEvent != null)
			            	{
			            		log.debug("calEvent("+ eventId + ") modifiedDate=" + calEvent.getModifiedDate() );
			            		layoutMetaData.setLastModifiedDate(calEvent.getModifiedDate());
			            		layoutMetaData.setPublishedDate(calEvent.getCreateDate());
			            		layoutMetaData.setCategory(getCalendarCategory(calEvent));
			            	}
			            */}
				
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
					// comment			layoutMetaData.setCategory(getJournalCategory(article));
								
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
	
	private static String getLayoutCategory(Layout layout) {
		
		String category = getSingleton(layout, EXPANDO_CATEGORY);
		//-- IF category isn't set for this layout
		//-- THEN
		//--   Recursively walk up the layout heirarchy looking for the first value set.
		if ((category == null || category.isEmpty()) && layout.getParentLayoutId() != LayoutConstants.DEFAULT_PARENT_LAYOUT_ID )
		{
			
			try {
				category = getLayoutCategory( LayoutLocalServiceUtil.getLayout(layout.getParentPlid()));
			} catch (Exception e) {
				log.info("Exception Throwing getLayoutCategory "+e);
			}
		}
		
		return category;
	}
	
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
	
	@SuppressWarnings("rawtypes")
	public static String getSingleton(BaseModel obj, String fieldName)
	{
		String result = null;
		
		try {
			String vals[] = (String []) (obj).getExpandoBridge().getAttribute( fieldName );

			if (vals == null) {
				log.error("ExpandoUtil.getSingleton("+obj+","+fieldName+") returns NULL - please check to ensure it exists and permissions are correct");
			}
			else if (vals.length > 0) {
				result = vals[0];
			}
			
		}
		catch (Exception e)
		{
			//-- ignore
			log.info("ExpandoUtil.getSingleton("+obj+","+fieldName+") returns NULL: " +e);
		}
		return result;
	}

}
