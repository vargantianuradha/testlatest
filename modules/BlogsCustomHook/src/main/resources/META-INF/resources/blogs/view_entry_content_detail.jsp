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

<%@ include file="/blogs/init.jsp" %>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%
SearchContainer searchContainer = (SearchContainer)request.getAttribute("view_entry_content.jsp-searchContainer");

BlogsEntry entry = (BlogsEntry)request.getAttribute("view_entry_content.jsp-entry");
BlogsPortletInstanceConfiguration blogsPortletInstanceConfiguration = BlogsPortletInstanceConfigurationUtil.getBlogsPortletInstanceConfiguration(themeDisplay);
%>

<c:choose>
	<c:when	test="<%=BlogsEntryPermission.contains(permissionChecker, entry, ActionKeys.VIEW)
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

		<div class=" widget-mode-detail-header">
			<liferay-asset:asset-categories-available
				className="<%= BlogsEntry.class.getName() %>"
				classPK="<%= entry.getEntryId() %>"
			>
				<div class="">
					<div class="categories mx-auto ">
						<liferay-asset:asset-categories-summary
							className="<%= BlogsEntry.class.getName() %>"
							classPK="<%= entry.getEntryId() %>"
							displayStyle="simple-category"
							portletURL="<%= renderResponse.createRenderURL() %>"
						/>
					</div>
				</div>
			</liferay-asset:asset-categories-available>

			<div class="">
				<div class="mx-auto">
					<div class="autofit-row">
						<div class="autofit-col autofit-col-expand">
						<div class="taglib-header ">
								<span class="header-back-to"> <a
									href="#">« Back</a>
								</span> 
							<h1 class="header-title">
							<span> <%= HtmlUtil.escape(BlogsEntryUtil.getDisplayTitle(resourceBundle, entry)) %></span>
							</h1>
							</div>
							<%
							String subtitle = entry.getSubtitle();
							%>

							<c:if test="<%= Validator.isNotNull(subtitle) %>">
								<h4 class="sub-title"><%= HtmlUtil.escape(subtitle) %></h4>
							</c:if>
						</div>
						<div class="edit-content-data autofit-col visible-interaction">
							<div class="dropdown dropdown-action">
								<liferay-util:include page="/blogs/entry_action.jsp"
									servletContext="<%=application%>" />
							</div>
					   </div>
				</div>
					</div>

					<div class="autofit-row widget-metadata">
						<div class="blog-entry entry approved">
							<div class="entry-content">
								<div class="entry-author"><liferay-ui:message	key="written-by" />&nbsp;
									<a href = "<%= viewEntryURL %>" ><%=HtmlUtil.escape(entry.getUserName())%> </a>
								</div>
								<div class="entry-date">
								<% SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
								String strDate= formatter.format(entry.getStatusDate()); %>
								<%=strDate%>
								</div>
							</div>
							<div class="entry-body">
							 
										<c:if test="<%= blogsPortletInstanceConfiguration.enableReadingTime() %>">
											- <liferay-reading-time:reading-time displayStyle="descriptive" model="<%= entry %>" />
										</c:if>

										<c:if test="<%= blogsPortletInstanceConfiguration.enableViewCount() %>">

											<%
											AssetEntry assetEntry = BlogsEntryAssetEntryUtil.getAssetEntry(request, entry);
											%>

											- <liferay-ui:message
										arguments="<%=assetEntry.getViewCount()%>"
										key='<%=(assetEntry.getViewCount() == 1) ? "x-view" : "x-views"%>' />
								</c:if>
									
							</div>
						</div>
					</div>
				</div>
			</div>
		
		<%
		String coverImageURL = entry.getCoverImageURL(themeDisplay);
		%>

		<c:if test="<%= Validator.isNotNull(coverImageURL) %>">
			<div class="aspect-ratio aspect-ratio-bg-cover cover-image" style="background-image: url(<%= coverImageURL %>)"></div>
		</c:if>

		<!-- text resume -->

		<div class="widget-mode-detail-text" id="<portlet:namespace /><%= entry.getEntryId() %>">
			<c:if test="<%= Validator.isNotNull(coverImageURL) %>">

				<%
				String coverImageCaption = entry.getCoverImageCaption();
				%>

				<c:if test="<%= Validator.isNotNull(coverImageCaption) %>">
					
							<div class="cover-image-caption">
								<small><%= entry.getCoverImageCaption() %></small>
							</div>
						
				</c:if>
			</c:if>

			<div class="">
				<div class="mx-auto blog-view">
					<%=entry.getContent()%>
				</div>
			</div>

			<liferay-expando:custom-attributes-available
				className="<%= BlogsEntry.class.getName() %>"
			>
				<div class="">
					<div class="mx-auto widget-mode-detail">
						<liferay-expando:custom-attribute-list
							className="<%= BlogsEntry.class.getName() %>"
							classPK="<%= entry.getEntryId() %>"
							editable="<%= true %>"
							label="<%= true %>"
						/>
					</div>
				</div>
			</liferay-expando:custom-attributes-available>

			<liferay-asset:asset-tags-available
				className="<%= BlogsEntry.class.getName() %>"
				classPK="<%= entry.getEntryId() %>"
			>
				<div class="">
					<div class="mx-auto widget-mode-detail">
						<div class="entry-tags">
							<liferay-asset:asset-tags-summary
								className="<%= BlogsEntry.class.getName() %>"
								classPK="<%= entry.getEntryId() %>"
								portletURL="<%= renderResponse.createRenderURL() %>"
							/>
						</div>
					</div>
				</div>
			</liferay-asset:asset-tags-available>
		</div>

		<div class=" ">
			<div class="">
				<div class="mx-auto widget-mode-detail">

					<%
					request.setAttribute("entry_toolbar.jsp-entry", entry);
					%>

					<liferay-util:include page="/blogs/entry_toolbar.jsp" servletContext="<%= application %>">
						<liferay-util:param name="showFlags" value="<%= Boolean.TRUE.toString() %>" />
					</liferay-util:include>
				</div>
			</div>

			<c:if test="<%= blogsPortletInstanceConfiguration.enableRelatedAssets() %>">
				<div class="">
					<div class="mx-auto widget-mode-detail">

						<%
						AssetEntry assetEntry = _getAssetEntry(request, entry);
						%>

						<div class="entry-links">
							<liferay-asset:asset-links
								assetEntryId="<%= (assetEntry != null) ? assetEntry.getEntryId() : 0 %>"
								className="<%= BlogsEntry.class.getName() %>"
								classPK="<%= entry.getEntryId() %>"
							/>
						</div>
					</div>
				</div>
			</c:if>
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
<%!private AssetEntry _getAssetEntry(HttpServletRequest request, BlogsEntry entry)
			throws PortalException, SystemException {
		AssetEntry assetEntry = (AssetEntry) request.getAttribute("view_entry_content.jsp-assetEntry");

		if (assetEntry == null) {
			assetEntry = AssetEntryLocalServiceUtil.getEntry(BlogsEntry.class.getName(), entry.getEntryId());

			request.setAttribute("view_entry_content.jsp-assetEntry", assetEntry);
		}

		return assetEntry;
	}%>
<script>
	$('.header-back-to a').attr('href',window.location.href.split('/-/')[0])
	</script>
<style> 
.portlet-blogs .entry-content { margin:0;}
  .entry-date {
    background: 0;
    float: left;
    padding: 0 5px 0 0;
    color: #999;
}
.portlet-blogs .entry-author {
max-width: inherit;
}
 .entry-author {
    background: 0;
    margin-right: 10px !important;
    border: 0;
    color: #0059a3;
    float: none;
    margin: 0;
    padding: 0;
}
.taglib-header .header-back-to {
	float: right;
}
.edit-content-data .entry-options.inline { 
top: 15px;
}

.taglib-header .header-title {
	margin: .1em;
	line-height: 1.266em;
	letter-spacing: -0.02em;
	white-space: normal;
}
.widget-mode-detail .widget-mode-detail-text p {
   font-size: inherit; 
}
.widget-mode-detail .widget-mode-detail-text {
margin-top: 0;
}
.taglib-header {
	border-bottom: 1px solid #c8c9ca;
	color: #555;
	margin-bottom: 1em;
}
</style>
<script>
$('.blog-view > h3').remove()
</script>