<%@ include file="/init.jsp" %>

<%@page import="ca.cityofkingston.model.MediaMetadata"%>
<%@page import="ca.cityofkingston.portlet.MediaGalleryHTMLUtil"%>
<%@page import="java.io.Serializable"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="java.util.List"%>
<%@page import="ca.cityofkingston.model.MediaItem"%>
<%@page import="ca.cityofkingston.portlet.MediaGalleryPortlet"%>

 <link rel="stylesheet" type="text/css"  
 	href="<%=request.getContextPath()%>/css/elastislide.css" />  

 <link rel="stylesheet" type="text/css" 
  	href="<%=request.getContextPath()%>/css/fancybox.css" />  

 <link rel="stylesheet" type="text/css"  
  	href="<%=request.getContextPath()%>/css/main.css" />		  
   
 
	 

<portlet:defineObjects />
<%
List<MediaItem> mediaItemList1 = (List<MediaItem>) request.getAttribute(MediaGalleryPortlet.MEDIA_ITEM_LIST_ATTR);
String portletInstanceId1 = (String) request.getAttribute(MediaGalleryPortlet.PORTLET_INSTANCE_ID);

if(mediaItemList1!=null && mediaItemList1.size()>0) { %>
	<div class="kingston-media-gallery">
		<div class="kingston-media-gallery-wrapper">
				<ul class="elastislide-list">
						<% for(MediaItem mediaItem: mediaItemList1){ 
						   
						   %>
						    
							<li class="carousel-image">
								<a class="thumbnail fancybox" rel="<%= portletInstanceId1 %>" title="<%=mediaItem.getTitle()%>" href="<%=mediaItem.getMediaPath()%>">
									<img src="<%=mediaItem.getThumbnailPath()%>"  alt="" />
									<span class="thumbnail-info">
										<%= MediaGalleryHTMLUtil.getHTMLMetadata(mediaItem, MediaGalleryHTMLUtil.THUMBNAIL_DATE, MediaMetadata.ORIGINAL_DATE) %>
									</span>
								</a>
								<span class="thumbnail-metadata">
									<%= MediaGalleryHTMLUtil.getHTMLDetails(mediaItem) %>
								</span>
							</li>
						<% } %>
				</ul>
		</div>
	</div>
<% } else { %>
	<p class="portlet-msg-alert">No images found</p>
<% } %> 
  
 