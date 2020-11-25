<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@include file="/html/schedule/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<% 
String iFidsUrl_cfg 		= GetterUtil.getString(portletPreferences.getValue("iFidsUrl", ""));
String checkFlightsUrl_cfg 	= GetterUtil.getString(portletPreferences.getValue("checkFlightsUrl", ""));
int numDisplayEntries 		= GetterUtil.getInteger(portletPreferences.getValue("numDisplayEntries", "0"));
%>

<aui:form action="<%= configurationURL %>" method="post" name="fm">
    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

    <aui:input cssClass="wide" name="preferences--iFidsUrl--" type="text" value="<%= iFidsUrl_cfg %>" />
    <aui:input cssClass="wide" name="preferences--numDisplayEntries--" type="text" value="<%= numDisplayEntries %>" />
    <aui:input cssClass="wide" name="preferences--checkFlightsUrl--" type="text" value="<%= checkFlightsUrl_cfg %>" />

    <aui:button-row>
       <aui:button type="submit" />
    </aui:button-row>
</aui:form>