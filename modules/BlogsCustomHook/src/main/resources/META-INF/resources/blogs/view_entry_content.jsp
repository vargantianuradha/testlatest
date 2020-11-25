<%--
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
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@ include file="/blogs/init.jsp"%>

<%
SearchContainer searchContainer = (SearchContainer)request.getAttribute("view_entry_content.jsp-searchContainer");

BlogsEntry entry = (BlogsEntry)request.getAttribute("view_entry_content.jsp-entry");

BlogsPortletInstanceConfiguration blogsPortletInstanceConfiguration = BlogsPortletInstanceConfigurationUtil.getBlogsPortletInstanceConfiguration(themeDisplay);
 
%>

<div class="portlet-blogs">
<c:choose>

	<c:when
		test="<%=BlogsEntryPermission.contains(permissionChecker, entry, ActionKeys.VIEW)
							&& (entry.isVisible() || (entry.getUserId() == user.getUserId())
									|| BlogsEntryPermission.contains(permissionChecker, entry, ActionKeys.UPDATE))%>">
		<portlet:renderURL var="viewEntryURL">
			<portlet:param name="mvcRenderCommandName" value="/blogs/view_entry" />
			<portlet:param name="redirect" value="<%= currentURL %>" />

			<c:choose>
				<c:when test="<%= Validator.isNotNull(entry.getUrlTitle()) %>">
					<portlet:param name="urlTitle" value="<%= entry.getUrlTitle() %>" />
				</c:when>
				<c:otherwise>
					<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
				</c:otherwise>
			</c:choose>
		</portlet:renderURL>

		<div class="widget-mode-simple">
			<div class="widget-mode-simple-entry">
			<div class="autofit-row widget-topbar">
					<div class="autofit-col autofit-col-expand">
					<h3 class="title">
						<aui:a cssClass="title-link" href="<%= viewEntryURL %>"><%= HtmlUtil.escape(BlogsEntryUtil.getDisplayTitle(resourceBundle, entry)) %></aui:a>
					</h3>

					<%
					String subtitle = entry.getSubtitle();
					%>

					<c:if test="<%= blogsPortletInstanceConfiguration.displayStyle().equals(BlogsUtil.DISPLAY_STYLE_FULL_CONTENT) && Validator.isNotNull(subtitle) %>">
						<h4 class="sub-title"><%= HtmlUtil.escape(subtitle) %></h4>
					</c:if>
				</div>

				<div class="autofit-col visible-interaction">
						<div class="dropdown dropdown-action">
							<liferay-util:include page="/blogs/entry_action.jsp"
								servletContext="<%=application%>" />
						</div>
					</div>
				</div>

			

					<%
					User entryUser = UserLocalServiceUtil.fetchUser(entry.getUserId());

					String entryUserURL = StringPool.BLANK;

					if ((entryUser != null) && !entryUser.isDefaultUser()) {
						entryUserURL = entryUser.getDisplayURL(themeDisplay);
					}
					%>

					<div class="autofit-row widget-metadata">
				<div class="text-secondary">
					<%
							SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
									String strDate = formatter.format(entry.getStatusDate());
						%>
						<%=strDate%>
				</div>
				<div class="text-truncate-inline"> 
					<liferay-ui:message	key="written-by" />&nbsp;
						<a class="text-truncate username" href="<%=entryUserURL%>"><%=HtmlUtil.escape(entry.getUserName())%></a>
					</div>
					</div> 
			</div>
		</div>

		 

			<div class="entry-body">
			<div class="widget-content"
				id="<portlet:namespace /><%=entry.getEntryId()%>">

				<%
				String coverImageURL = entry.getCoverImageURL(themeDisplay);
				%>

				<c:if test="<%= Validator.isNotNull(coverImageURL) %>">
					<a href="<%= viewEntryURL.toString() %>">
						<div class="aspect-ratio aspect-ratio-8-to-3 aspect-ratio-bg-cover cover-image" style="background-image: url(<%= coverImageURL %>)"></div>
					</a>
				</c:if>

				<c:choose>
					<c:when test="<%= blogsPortletInstanceConfiguration.displayStyle().equals(BlogsUtil.DISPLAY_STYLE_ABSTRACT) %>">

						<%
						String summary = entry.getDescription();

						if (Validator.isNull(summary)) {
							summary = entry.getContent();
						}
						%>

						 
							<%=StringUtil.shorten(HtmlUtil.stripHtml(summary), 400 )%>
							<br> <a href="<%= viewEntryURL %>"> More </a>
						 
					</c:when>
					<c:when test="<%= blogsPortletInstanceConfiguration.displayStyle().equals(BlogsUtil.DISPLAY_STYLE_FULL_CONTENT) %>">
						<%= entry.getContent() %>
					</c:when>
				</c:choose>

				<%
				request.setAttribute("entry_toolbar.jsp-entry", entry);
				%>

				<liferay-util:include page="/blogs/entry_toolbar.jsp" servletContext="<%= application %>" />
			</div>
		</div>
	</c:when>
	<c:otherwise>

		<%
			if (searchContainer != null) {
						searchContainer.setTotal(searchContainer.getTotal() - 1);
					}
		%>

	</c:otherwise>
</c:choose>
</div>
<%!private AssetEntry _getAssetEntry(HttpServletRequest request, BlogsEntry entry)
			throws PortalException, SystemException {
		AssetEntry assetEntry = (AssetEntry) request.getAttribute("view_entry_content.jsp-assetEntry");

		if (assetEntry == null) {
			assetEntry = AssetEntryLocalServiceUtil.getEntry(BlogsEntry.class.getName(), entry.getEntryId());

			request.setAttribute("view_entry_content.jsp-assetEntry", assetEntry);
		}

		return assetEntry;
	}%>
	
	<style>
section[id*="portlet_com_liferay_blogs_web_portlet_BlogsPortlet"] .autofit-col.autofit-col-expand .portlet-title {
  display: none; }

section[id*="portlet_com_liferay_blogs_web_portlet_BlogsPortlet"] .widget-content p {
  margin-bottom: .5rem; }

section[id*="portlet_com_liferay_blogs_web_portlet_BlogsPortlet"] .widget-mode-simple .widget-toolbar {
  margin-top: .5rem;
  margin-bottom: 1.5rem; }

section[id*="portlet_com_liferay_blogs_web_portlet_BlogsPortlet"] .title a {
  font-size: 30px;
  text-decoration: none; }

section[id*="portlet_com_liferay_blogs_web_portlet_BlogsPortlet"] .autofit-row.widget-metadata .inline-item-before {
  display: none; }

section[id*="portlet_com_liferay_blogs_web_portlet_BlogsPortlet"] .text-truncate-inline .text-truncate {
  color: #0059a3; }

section[id*="portlet_com_liferay_blogs_web_portlet_BlogsPortlet"] .autofit-col.autofit-col-expand * {
  display: inline-block; }

section[id*="portlet_com_liferay_blogs_web_portlet_BlogsPortlet"] .text-truncate-inline  {
  float: right;
   margin-left: 5px;  
  color: #0059a3;
  font-weight: bold;
}
section[id*="portlet_com_liferay_blogs_web_portlet_BlogsPortlet"] .text-truncate-inline a {
  color: #0059a3;
  text-decoration: none;
}
section[id*="portlet_com_liferay_blogs_web_portlet_BlogsPortlet"] .autofit-float.widget-toolbar .btn-outline-borderless {
  padding: 0;
  margin-bottom: 10px;
  color: #0059a3;
  text-decoration: none;
  font-weight: normal; }

section[id*="portlet_com_liferay_blogs_web_portlet_BlogsPortlet"] .autofit-float.widget-toolbar .btn-outline-borderless .inline-item-before {
  display: none; }

section[id*='portlet_com_portlet_com_liferay_blogs_web_portlet_BlogsPortlet'] .widget-mode-detail-header h3 {
  border-bottom: 1px solid #c8c9ca;
  color: #555;
  font-size: 2.2em !important;
  font-weight: normal; }
section[id*="portlet_com_liferay_blogs_web_portlet_BlogsPortlet"] .text-truncate-inline a {
}

</style>