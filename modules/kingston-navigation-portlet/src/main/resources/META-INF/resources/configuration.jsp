<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="ca.cityofkingston.navigation.Configuration"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil"%>
<%@page import="ca.cityofkingston.navigation.ConfigurationActionImpl"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="/init.jsp"%>
 
<%
String redirect = ParamUtil.getString(request, "redirect");

String portletResource = ParamUtil.getString(request, ConfigurationActionImpl.PORTLET_RESOURCE);

PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);

Configuration configuration = Configuration.make(preferences);

String mainNavigationLevel = ""+configuration.getMainNavigationLevel();
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm"  style="width:95%; margin:auto;">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<aui:fieldset>
		<aui:select style="width:auto;"
			label="<%=ConfigurationActionImpl.MAIN_NAVIGATION_LEVEL%>"
			name="<%=ConfigurationActionImpl.MAIN_NAVIGATION_LEVEL%>">
			<% for (String level: ConfigurationActionImpl.LEVELS) {	%>
			<aui:option label="<%=level%>" selected="<%=level.equals(mainNavigationLevel)%>"  />
			<% } %>
		</aui:select>
		<aui:input
			label="<%=ConfigurationActionImpl.DISPLAY_MAIN_NAVIGATION%>"
			name="<%=ConfigurationActionImpl.DISPLAY_MAIN_NAVIGATION%>"
			type="checkbox"
			value="<%=configuration.isDisplayMainNavigation()%>"/>
		<aui:input
		label="<%=ConfigurationActionImpl.DISPLAY_CURRENT_NAVIGATION%>"
			name="<%=ConfigurationActionImpl.DISPLAY_CURRENT_NAVIGATION%>"
			type="checkbox"
			value="<%=configuration.isDisplayCurrentNavigation()%>"/>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>