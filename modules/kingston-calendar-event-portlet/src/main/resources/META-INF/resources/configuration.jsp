<%@page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil"%>
<%@include file="/init.jsp" %>

<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="com.liferay.portal.kernel.util.Constants" %>
<%@page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@page import="ca.cityofkingston.calendarevent.Configuration"%>
<%@page import="ca.cityofkingston.calendarevent.ConfigurationActionImpl" %>
<%@page import="com.liferay.portal.kernel.servlet.SessionMessages"%>

<%
String redirect = ParamUtil.getString(request, "redirect");
String portletResource= ParamUtil.getString(request, ConfigurationActionImpl.PORTLET_RESOURCE);
PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
Configuration configuration = Configuration.make(preferences);

%>
<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<liferay-ui:success key="success" message="Configuration has been saved successfully." />
<liferay-ui:error key="error" message="An error has occurred please see logs for details." />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />


	<aui:fieldset>

		<aui:input
			label="Landing Calendar Page"
			helpMessage="Enter the friendly url of page that contains your calendar portlet (used to create the 'View Event Details' Links)"
			name="<%=ConfigurationActionImpl.CALENDAR_LAYOUT_KEY%>"
			value='<%=preferences.getValue(ConfigurationActionImpl.CALENDAR_LAYOUT_KEY, "")%>'
		/>

		<aui:select
			label="Display view type"
			name="<%=ConfigurationActionImpl.DISPLAY_TYPE_KEY%>">
			<% for (String displayType: ConfigurationActionImpl.DISPLAY_TYPE_VALUES) {
				boolean isSelected = displayType.equals(preferences.getValue(ConfigurationActionImpl.DISPLAY_TYPE_KEY, ConfigurationActionImpl.DISPLAY_TYPE_DEFAULT));
			%>
				<aui:option label="<%=displayType%>" selected="<%=isSelected%>" />
			<% } %>
		</aui:select>
	</aui:fieldset>


	<aui:fieldset>
		<aui:select
			label="<%=ConfigurationActionImpl.NUM_DAYS_FORWARD_KEY%>"
			name="<%=ConfigurationActionImpl.NUM_DAYS_FORWARD_KEY%>">
			<% for (int days: ConfigurationActionImpl.DAYS_FORWARD) {
				boolean isSelected = (days == Integer.parseInt(preferences.getValue(ConfigurationActionImpl.NUM_DAYS_FORWARD_KEY, (ConfigurationActionImpl.NUM_DAYS_FORWARD_DEFAULT + ""))));
			%>
				<aui:option label="<%=days%>" selected="<%=isSelected%>" />
			<% } %>
		</aui:select>
	</aui:fieldset>

	<aui:fieldset>
		<aui:select
			label="<%=ConfigurationActionImpl.NUM_ITEMS_DISPLAYED_KEY%>"
			name="<%=ConfigurationActionImpl.NUM_ITEMS_DISPLAYED_KEY%>">
			<% for (int items: ConfigurationActionImpl.NUM_ITEMS) {
				boolean isSelected = (items == Integer.parseInt(preferences.getValue(ConfigurationActionImpl.NUM_ITEMS_DISPLAYED_KEY, (ConfigurationActionImpl.NUM_ITEMS_DISPLAYED_DEFAULT + ""))));
			%>
				<aui:option label="<%=items%>" selected="<%=isSelected%>" />
			<% } %>
		</aui:select>
	</aui:fieldset>

	<aui:fieldset>
		<aui:input
			label="<%=ConfigurationActionImpl.TAGS_KEY%>"
			name="<%=ConfigurationActionImpl.TAGS_KEY%>"
			value='<%=preferences.getValue(ConfigurationActionImpl.TAGS_KEY, ConfigurationActionImpl.TAGS_DEFAULT)%>'
			/>
	</aui:fieldset>

	<aui:fieldset>
		<aui:input
			label="<%=ConfigurationActionImpl.CATEGORIES_KEY%>"
			name="<%=ConfigurationActionImpl.CATEGORIES_KEY%>"
			value='<%=preferences.getValue(ConfigurationActionImpl.CATEGORIES_KEY, ConfigurationActionImpl.CATEGORIES_DEFAULT)%>'
			/>
	</aui:fieldset>

	<aui:fieldset>
		<aui:input
			label="Show Past Events"
			helpMessage="Display (or not) all the past events"
			name="<%=ConfigurationActionImpl.SHOW_PAST_EVENTS_KEY%>"
			value='<%=Boolean.parseBoolean(preferences.getValue(ConfigurationActionImpl.SHOW_PAST_EVENTS_KEY, ConfigurationActionImpl.SHOW_PAST_EVENTS_DEFAULT))%>'
			type="checkbox"
		/>

		<aui:input
			label="Show Calendar Event Status"
			name="<%=ConfigurationActionImpl.SHOW_STATUS_KEY%>"
			value='<%=Boolean.parseBoolean(preferences.getValue(ConfigurationActionImpl.SHOW_STATUS_KEY, ConfigurationActionImpl.SHOW_STATUS_DEFAULT))%>'
			type="checkbox"
		/>
	</aui:fieldset>


	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>


