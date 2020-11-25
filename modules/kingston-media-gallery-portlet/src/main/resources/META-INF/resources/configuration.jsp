<%@page import="ca.cityofkingston.portlet.MediaGalleryConfigurationActionImpl"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil"%>
<%@page import="ca.cityofkingston.portlet.MediaGalleryConfiguration"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@page import="com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil"%>
<%@page import="com.liferay.document.library.kernel.model.DLFolder"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>


<portlet:defineObjects />
<liferay-theme:defineObjects />

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
<%

ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
String portletResource = ParamUtil.getString(request, MediaGalleryConfiguration.PORTLET_RESOURCE);
PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
MediaGalleryConfiguration configuration = MediaGalleryConfiguration.make(preferences, themeDisplay);
 %>
<liferay-ui:success key="success" message="Configuration has been saved successfully." />
<liferay-ui:error key="error" message="An error has occurred please see logs for details." />

<aui:form action="<%= configurationURL %>" method="post" name="fm">

	<aui:fieldset>
		<aui:select name="<%=MediaGalleryConfiguration.FOLDER_ID %>" label="Folder">
			<aui:option selected="<%=configuration.isFolderSelected(0) %>"  name="<%=MediaGalleryConfiguration.FOLDER_ID %>" value="0" label="Root"/>
			<%
				List<DLFolder> list =  DLFolderLocalServiceUtil.getDLFolders(0, DLFolderLocalServiceUtil.getDLFoldersCount());
				for (DLFolder folder: list){
			%>
					<aui:option selected="<%=configuration.isFolderSelected(folder.getFolderId()) %>" name="<%=MediaGalleryConfiguration.FOLDER_ID %>" value="<%= folder.getFolderId()%>" label="<%= folder.getPath()%>"/>
			<%
				}
			%>
		</aui:select>
	</aui:fieldset>
	<aui:fieldset>
		<aui:select label="Upload Status" name="<%=MediaGalleryConfiguration.MEDIA_STATUS_KEY %>">
			<% for (String status: MediaGalleryConfiguration.MEDIA_STATUS) {%>
				<aui:option label="<%=status%>" selected="<%=configuration.isStatusSelected(status)%>" />
			<% } %>
		</aui:select>

	</aui:fieldset>

	<aui:fieldset>
		<aui:input value="<%=configuration.isShowUserImagesSelected()%>" name="<%=MediaGalleryConfiguration.SHOW_CURRENT_USER_IMAGES %>" label="Show Only Current User's Images" type="checkbox"/>
	</aui:fieldset>

	<aui:fieldset>
		<aui:input value="<%=configuration.getMaxNumImages()%>" name="<%=MediaGalleryConfiguration.MAX_NUM_IMAGES %>" label="The maximum number of images to display" type="text" />
	</aui:fieldset>


	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>

</aui:form>


