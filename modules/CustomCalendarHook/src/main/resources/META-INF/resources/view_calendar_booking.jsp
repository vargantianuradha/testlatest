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


<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="javax.portlet.PortletResponse"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="com.liferay.portal.kernel.util.JavaConstants"%>
<%@page import="com.liferay.asset.kernel.model.AssetLink" %>
<%@page import="com.liferay.asset.kernel.service.AssetLinkLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.service.AssetEntryServiceUtil"%>
<%@page import="com.liferay.journal.model.*" %>
<%@page import="com.liferay.journal.service.JournalArticleLocalServiceUtil"%>
<%@page import="com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil"%>
<%@page import="com.liferay.asset.kernel.model.AssetRendererFactory"%>
<%@page import="com.liferay.asset.kernel.model.AssetRenderer"%>
<%@page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@page import="com.liferay.portal.kernel.util.LocaleUtil"%>
<%@page import="com.liferay.journal.service.JournalArticleServiceUtil"%>
<%@page import="java.text.Format" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%@page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil" %>
<%@page import="com.liferay.asset.kernel.model.*" %>
<%@page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil" %>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletURL"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil"%>
<%@page import="javax.portlet.WindowState"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletRequest"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletResponse"%>
<%@page import="com.liferay.document.library.kernel.model.DLFileEntry"%>
<%@page import="com.liferay.document.library.kernel.service.DLFileEntryServiceUtil"%>
<%@page import="com.liferay.document.library.kernel.model.DLFileVersion"%>
<%@page import="com.liferay.portal.kernel.util.TextFormatter"%>
<%@page import ="com.liferay.portal.kernel.model.Layout"%>
<%@page import ="com.liferay.portal.kernel.model.LayoutTypePortlet"%>
<%@page import ="com.liferay.portal.kernel.service.LayoutLocalServiceUtil"%>



<%@ include file="/init.jsp" %>

<liferay-util:dynamic-include key="com.liferay.calendar.web#/view_calendar_booking.jsp#pre" />

<%
PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);

PortletResponse portletResponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);


String backURL = ParamUtil.getString(request, "backURL");

int instanceIndex = BeanParamUtil.getInteger(calendarBooking, request, "instanceIndex");

//calendarBooking = RecurrenceUtil.getCalendarBookingInstance(calendarBooking, instanceIndex);

Calendar calendar = calendarBooking.getCalendar();

long startTime = calendarBooking.getStartTime();

String location = calendarBooking.getLocation();
java.util.Calendar startTimeJCalendar = JCalendarUtil.getJCalendar(startTime, userTimeZone);

long endTime = calendarBooking.getEndTime();

java.util.Calendar endTimeJCalendar = JCalendarUtil.getJCalendar(endTime, userTimeZone);

AssetEntry layoutAssetEntry = AssetEntryLocalServiceUtil.getEntry(CalendarBooking.class.getName(), calendarBooking.getCalendarBookingId());

SimpleDateFormat f = new SimpleDateFormat("MM/dd/yy");
f.setTimeZone(userTimeZone);
%>

<div class="container-fluid-1280">
<div class="panel panel-default">
<liferay-ui:header
backURL="<%= backURL %>"
cssClass="panel-heading"
title="<%= calendarBooking.getTitle(locale) %>"
/>

<aui:fieldset markupView="lexicon">
<dl class="property-list">
<dt>
<liferay-ui:message key="Start Date " />:
</dt>
<dd>
<%-- <%= dateFormatLongDate.format(startTimeJCalendar.getTime()) + ", " + dateFormatTime.format(startTimeJCalendar.getTime()) %> --%>
<%= f.format(startTimeJCalendar.getTime()) %>
</dd>
<dt>
<liferay-ui:message key="End Date" />:
</dt>
 
<dd>
<%-- <%= dateFormatLongDate.format(endTimeJCalendar.getTime()) + ", " + dateFormatTime.format(endTimeJCalendar.getTime()) %> --%>
<%= f.format(endTimeJCalendar.getTime())%>
</dd>
<dt>

<liferay-ui:message key="duration" />:
</dt>
<dd>
<%
boolean allDay = calendarBooking.getAllDay();
%>
<c:choose>
<c:when test="<%= allDay %>">
<span class="duration" title="<liferay-ui:message key="all-day" />">
<liferay-ui:message key="all-day" />
</span>
</c:when>
<c:otherwise>
<%=dateFormatTime.format(startTimeJCalendar.getTime()) + " - " + dateFormatTime.format(endTimeJCalendar.getTime()) %>
</c:otherwise>
</c:choose>
</dd>
<%
List<CalendarBooking> childCalendarBookings = calendarDisplayContext.getChildCalendarBookings(calendarBooking);

%>


<c:if test="<%= !childCalendarBookings.isEmpty() %>">
<!-- <dt> -->
<!-- <liferay-ui:message key="resources" />: -->
<!-- </dt> -->
<!-- <dd> -->

<%
// List<String> calendarResourcesNames = new ArrayList<String>();

// for (CalendarBooking childCalendarBooking : childCalendarBookings) {
// CalendarResource calendarResource = childCalendarBooking.getCalendarResource();

// calendarResourcesNames.add(calendarResource.getName(locale));
// }
%>

<%-- <%= HtmlUtil.escape(StringUtil.merge(calendarResourcesNames, ", ")) %> --%>
<!-- </dd> -->
</c:if>


<dt>
<liferay-ui:message key="type" />:
</dt>
<%
long assetId = layoutAssetEntry.getEntryId();
long VocabularyId = (AssetVocabularyLocalServiceUtil.getGroupVocabulary(themeDisplay.getLayout().getGroupId(),"Calendar Event Types")).getVocabularyId();

long[] asset = AssetEntryLocalServiceUtil.getAssetCategoryPrimaryKeys(assetId);
for(int i=0;i<asset.length;i++){
long categotyId = asset[i];
AssetCategory assetCategory= AssetCategoryLocalServiceUtil.getAssetCategory(categotyId);
if(assetCategory.getVocabularyId() == VocabularyId){
%>
<%= HtmlUtil.escape(assetCategory.getName()) %>

<%
}

}
%>
<%-- <liferay-ui:message key="location" />: Location.....  <%=calendarBooking.getLocation()%> --%>
<c:if test="<%= Validator.isNotNull(calendarBooking.getLocation()) %>">
<dt>
<liferay-ui:message key="location" />:
</dt>
<dd>
<span class="location"><%= HtmlUtil.escape(calendarBooking.getLocation()) %></span>
</dd>
</c:if>

<c:if test="<%= Validator.isNotNull(calendarBooking.getRecurrence()) %>">
<!-- <dt> -->
<!-- <liferay-ui:message key="repeat" />: -->
<!-- </dt> -->
<!-- <dd> -->
<%-- <span id="<portlet:namespace />recurrenceSummary"></span> --%>
<!-- </dd> -->
</c:if>
</dl>

<liferay-expando:custom-attributes-available
className="<%= CalendarBooking.class.getName() %>"
>
<liferay-expando:custom-attribute-list
className="<%= CalendarBooking.class.getName() %>"
classPK="<%= calendarBooking.getCalendarBookingId() %>"
editable="<%= false %>"
label="<%= true %>"
/>
</liferay-expando:custom-attributes-available>

<p>
<%= calendarBooking.getDescription(locale) %>
</p>




<div class="entry-tags">
<liferay-asset:asset-tags-summary
className="<%= CalendarBooking.class.getName() %>"
classPK="<%= calendarBooking.getCalendarBookingId() %>"
message="tags"
/>
</div>

<div class="entry-links">
<%
long assetEntryId = layoutAssetEntry.getEntryId();
List<AssetLink> assetLinks = null;
List<AssetEntry> nonWebContentAssetEntries = new ArrayList<AssetEntry>();
if (assetEntryId > 0) {
assetLinks = AssetLinkLocalServiceUtil.getDirectLinks(assetEntryId);
for (AssetLink assetLink : assetLinks) {
AssetEntry assetLinkEntry = null;
if (assetLink.getEntryId1() == assetEntryId) {
assetLinkEntry = AssetEntryServiceUtil.getEntry(assetLink.getEntryId2());
}
else {
assetLinkEntry = AssetEntryServiceUtil.getEntry(assetLink.getEntryId1());
}
if (!assetLinkEntry.isVisible()) {
continue;
}
assetLinkEntry = assetLinkEntry.toEscapedModel();
if (JournalArticle.class.getName().equals(PortalUtil.getClassName(assetLinkEntry.getClassNameId()))){
JournalArticle webContent = JournalArticleLocalServiceUtil.getLatestArticle(assetLinkEntry.getClassPK());
AssetRendererFactory assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(PortalUtil.getClassName(assetLinkEntry.getClassNameId()));
AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(assetLinkEntry.getClassPK());
%>
<c:if test="<%= assetRenderer.hasEditPermission(permissionChecker) %>">
<div class="asset-actions">

<liferay-portlet:renderURL  var="editPortletURLString" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
<portlet:param name="mvcRenderCommandName" value="/journal/edit_article" />
<portlet:param name="redirect" value="<%= currentURL %>" />
<portlet:param name="groupId" value="<%= String.valueOf(webContent.getGroupId()) %>" />
<portlet:param name="articleId" value="<%= webContent.getArticleId() %>" />
<portlet:param name="version" value="<%= String.valueOf(webContent.getVersion()) %>" />
</liferay-portlet:renderURL>
<%
boolean showIconLabel = true;

editPortletURLString = HttpUtil.addParameter(editPortletURLString, "doAsGroupId", assetRenderer.getGroupId());
editPortletURLString = HttpUtil.addParameter(editPortletURLString, "refererPlid", plid);

%>
</div>
</c:if>
<%if(themeDisplay.isSignedIn()){ %>
<%
Layout l=LayoutLocalServiceUtil.getFriendlyURLLayout(10180, false, "/residents/city-calendar-events");
LayoutTypePortlet articleLayoutTypePortlet = (LayoutTypePortlet)l.getLayoutType(); 
List<String> allPortlets = articleLayoutTypePortlet.getPortletIds();
String instanceId="";
for (String p: allPortlets) 
{ 
	if(p.contains("CalendarPortlet_INSTANCE")){
				String a[] = p.split("CalendarPortlet_INSTANCE_");
				instanceId=  a[1]; 
	}
}
String calendarLayoutFriendlyURL=themeDisplay.getURLPortal()+"/residents/city-calendar-events";
String ppstate = "maximized";
String ppmode = "view";
String ppid="/group/guest/~/control_panel/manage?p_p_id=com_liferay_journal_web_portlet_JournalPortlet";
String editUrl =themeDisplay.getURLPortal()+ppid+"&p_p_state="+ppstate+"&p_p_mode="+ppmode+"&_com_liferay_journal_web_portlet_JournalPortlet_mvcPath=%2Fedit_article.jsp&_com_liferay_journal_web_portlet_JournalPortlet_articleId="+webContent.getArticleId()+"&_com_liferay_journal_web_portlet_JournalPortlet_redirect="+calendarLayoutFriendlyURL+ "/-/calendar/"+instanceId+"/event/"+calendarBooking.getCalendarBookingId();
%>
<c:if test="<%= permissionChecker.hasPermission(themeDisplay.getScopeGroupId(), JournalArticle.class.getName(), webContent.getResourcePrimKey(), ActionKeys.UPDATE)%>">
<div class="editCal" style="text-align:right;"> <a href="<%=editUrl %>" primary="<%= true %>" id="a" value="edit-calendar" ><button value="Edit Web Content">Edit Web Content</button></a> </div>
</c:if>
<% }%>
<div>
<%=
JournalArticleLocalServiceUtil.getArticleContent(webContent, webContent.getDDMTemplateKey(), "maximized", "en_US", themeDisplay)
//JournalArticleServiceUtil.getArticleContent(webContent.getGroupId(), webContent.getArticleId(), webContent.getVersion(), LocaleUtil.getDefault().toString(), themeDisplay)
%>
</div>

<%

}
else {
nonWebContentAssetEntries.add(assetLinkEntry);
}

}
if (!nonWebContentAssetEntries.isEmpty()){
%>
<div class="kingston-portlet-title-blue">
<div class="portlet-title">
Related Documents
</div>
</div>
<%
}
for (AssetEntry assetLinkEntry : nonWebContentAssetEntries){

AssetRendererFactory assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(PortalUtil.getClassName(assetLinkEntry.getClassNameId()));
AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(assetLinkEntry.getClassPK());

if (assetRenderer.hasViewPermission(permissionChecker)) {
String asseLinktEntryTitle = assetLinkEntry.getTitle(locale);
LiferayPortletURL assetPublisherURL = PortletURLFactoryUtil.create(request ,"com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet", plid ,PortletRequest.RENDER_PHASE);
assetPublisherURL.setWindowState(WindowState.MAXIMIZED);

assetPublisherURL.setParameter("mvcRenderCommandName", "/asset_publisher/view_content");
assetPublisherURL.setParameter("assetEntryId", String.valueOf(assetLinkEntry.getEntryId()));
assetPublisherURL.setParameter("type", assetRendererFactory.getType());
if (Validator.isNotNull(assetRenderer.getUrlTitle())) {
if (assetRenderer.getGroupId() != scopeGroupId) {
assetPublisherURL.setParameter("groupId", String.valueOf(assetRenderer.getGroupId()));
}

assetPublisherURL.setParameter("urlTitle", assetRenderer.getUrlTitle());
}
String viewFullContentURLString = assetPublisherURL.toString();

viewFullContentURLString = HttpUtil.setParameter(viewFullContentURLString, "redirect", currentURL);

String urlViewInContext = assetRenderer.getURLViewInContext((LiferayPortletRequest)portletRequest, (LiferayPortletResponse)portletResponse, viewFullContentURLString);

String urlText = "View";
%>
<div class="document-asset">
<h3 class="document-asset-title emphasized_label">
<%= assetLinkEntry.getTitle()  %>
</h3>
<%
if (DLFileEntry.class.getName().equals(PortalUtil.getClassName(assetLinkEntry.getClassNameId()))){
DLFileEntry fileEntry = DLFileEntryServiceUtil.getFileEntry(assetLinkEntry.getClassPK());
DLFileVersion fileVersion = fileEntry.getFileVersion();

StringBundler sb = new StringBundler(15);
sb.append(themeDisplay.getPortalURL());
sb.append("/documents/");
sb.append(fileEntry.getRepositoryId());
sb.append("/");
sb.append(fileEntry.getFolderId());
sb.append("/");
sb.append(HttpUtil.encodeURL(HtmlUtil.unescape(fileEntry.getTitle())));

urlViewInContext = sb.toString();

urlText = "Download (" + TextFormatter.formatStorageSize((int) fileEntry.getSize() , LocaleUtil.getDefault())  + "kB)";
}
%>

<div class="asset-resource-info">
<a href="<%=urlViewInContext%>" class="kingston-link">
<%=urlText %>
</a>
<%-- <img class="icon" src="<%=assetRenderer.getIconPath(portletRequest)%>"> --%>
</div>
<p class="asset-description">
<%= assetLinkEntry.getDescription() %>
</p>
</div>
<%
}
}
}
%>
</div>

<c:if test="<%= calendar.isEnableRatings() %>">
<div class="entry-ratings">
<liferay-ui:ratings
className="<%= CalendarBooking.class.getName() %>"
classPK="<%= calendarBooking.getCalendarBookingId() %>"
inTrash="<%= calendarBooking.isInTrash() %>"
/>
</div>
</c:if>
</aui:fieldset>

<c:if test="<%= calendar.isEnableComments() %>">
<liferay-comment:discussion
className="<%= CalendarBooking.class.getName() %>"
classPK="<%= calendarBooking.getCalendarBookingId() %>"
formName="fm2"
ratingsEnabled="<%= true %>"
redirect="<%= currentURL %>"
userId="<%= calendarBooking.getUserId() %>"
/>
</c:if>

<portlet:actionURL name="invokeTransition" var="invokeTransitionURL" />

<c:if test="<%= Validator.isNotNull(calendarBooking.getRecurrence()) %>">
<%@ include file="/calendar_booking_recurrence_language_keys.jspf" %>

<aui:script use="liferay-calendar-recurrence-util">
var summaryNode = A.one('#<portlet:namespace />recurrenceSummary');

var endValue = 'never';
var untilDate = null;

<%
Recurrence recurrence = calendarBooking.getRecurrenceObj();

java.util.Calendar untilJCalendar = recurrence.getUntilJCalendar();
%>

<c:choose>
<c:when test="<%= untilJCalendar != null %>">
endValue = 'on';

untilDate = new Date(<%= untilJCalendar.get(java.util.Calendar.YEAR) %>, <%= untilJCalendar.get(java.util.Calendar.MONTH) %>, <%= untilJCalendar.get(java.util.Calendar.DATE) %>);
</c:when>
<c:when test="<%= recurrence.getCount() > 0 %>">
endValue = 'after';
</c:when>
</c:choose>

<%
Frequency frequency = recurrence.getFrequency();

PositionalWeekday positionalWeekday = recurrence.getPositionalWeekday();

JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();

List<String> weekdayValues = new ArrayList<>();

for (Weekday weekday : recurrence.getWeekdays()) {
weekdayValues.add(weekday.getValue());
}
%>

var positionalWeekday = null;

<c:if test="<%= (frequency.equals(Frequency.MONTHLY) || frequency.equals(Frequency.YEARLY)) && (positionalWeekday != null) %>">
positionalWeekday = {
month: <%= startTimeJCalendar.get(java.util.Calendar.MONTH) %>,
position: <%= positionalWeekday.getPosition() %>,
weekday: '<%= positionalWeekday.getWeekday() %>'
};
</c:if>

var recurrence = {
count: <%= recurrence.getCount() %>,
endValue: endValue,
frequency: '<%= String.valueOf(frequency) %>',
interval: <%= recurrence.getInterval() %>,
positionalWeekday: positionalWeekday,
untilDate: untilDate,
weekdays: <%= jsonSerializer.serialize(weekdayValues) %>
};

var recurrenceSummary = Liferay.RecurrenceUtil.getSummary(recurrence);

summaryNode.html(recurrenceSummary);
</aui:script>
</c:if>

<liferay-util:dynamic-include key="com.liferay.calendar.web#/view_calendar_booking.jsp#post" />
</div>

<aui:form action="<%= invokeTransitionURL %>" method="post" name="fm">
<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
<aui:input name="calendarBookingId" type="hidden" value="<%= calendarBooking.getCalendarBookingId() %>" />
<aui:input name="startTime" type="hidden" value="<%= calendarBooking.getStartTime() %>" />
<aui:input name="status" type="hidden" />
<aui:input name="updateInstance" type="hidden" value="false" />
<aui:input name="allFollowing" type="hidden" value="false" />

<aui:button-row>

<%
boolean hasManageBookingsPermission = CalendarPermission.contains(permissionChecker, calendar, CalendarActionKeys.MANAGE_BOOKINGS) && !calendarBooking.isDraft();
boolean hasWorkflowInstanceLink = WorkflowInstanceLinkLocalServiceUtil.hasWorkflowInstanceLink(themeDisplay.getCompanyId(), calendarBooking.getGroupId(), CalendarBooking.class.getName(), calendarBooking.getCalendarBookingId());
%>

<c:if test="<%= hasManageBookingsPermission && !hasWorkflowInstanceLink %>">
<c:if test="<%= calendarBooking.getStatus() != WorkflowConstants.STATUS_APPROVED %>">
<aui:button onClick='<%= renderResponse.getNamespace() + "invokeTransition(" + WorkflowConstants.STATUS_APPROVED + ");" %>' value="accept" />
</c:if>

<c:if test="<%= calendarBooking.getStatus() != CalendarBookingWorkflowConstants.STATUS_MAYBE %>">
<aui:button onClick='<%= renderResponse.getNamespace() + "invokeTransition(" + CalendarBookingWorkflowConstants.STATUS_MAYBE + ");" %>' value="maybe" />
</c:if>

<c:if test="<%= calendarBooking.getStatus() != WorkflowConstants.STATUS_DENIED %>">
<aui:button onClick='<%= renderResponse.getNamespace() + "invokeTransition(" + WorkflowConstants.STATUS_DENIED + ");" %>' value="decline" />
</c:if>
</c:if>
</aui:button-row>
</aui:form>

<aui:script>
function <portlet:namespace />invokeTransition(status) {
document.<portlet:namespace />fm.<portlet:namespace />status.value = status;

if (<%= calendarBooking.isRecurring() %>) {
Liferay.RecurrenceUtil.openConfirmationPanel(
'invokeTransition',
function() {
document.<portlet:namespace />fm.<portlet:namespace />updateInstance.value = 'true';
document.<portlet:namespace />fm.<portlet:namespace />allFollowing.value = 'false';
submitForm(document.<portlet:namespace />fm);
},
function() {
document.<portlet:namespace />fm.<portlet:namespace />updateInstance.value = 'true';
document.<portlet:namespace />fm.<portlet:namespace />allFollowing.value = 'true';
submitForm(document.<portlet:namespace />fm);
},
function() {
submitForm(document.<portlet:namespace />fm);
}
);
}
else {
submitForm(document.<portlet:namespace />fm);
}
}
</aui:script>
</div>