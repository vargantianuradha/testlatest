<%@page import="ca.cityofkingston.calendarevent.Configuration"%>
<%@page import="ca.cityofkingston.calendarevent.ConfigurationActionImpl"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />
<%@ include file="/init.jsp" %>
<%
Configuration configuration = Configuration.make(portletPreferences);
%>

<div class="paginator-content">


	 <%  
	 	if( ConfigurationActionImpl.DISPLAY_TYPE_WITH_ACCORDION.equals(configuration.getDisplayType()) ) {  %>
		<%@ include file="/view_accordion.jspf" %>
	<% } else if (ConfigurationActionImpl.DISPLAY_TYPE_PLAIN_LIST.equals(configuration.getDisplayType()) ) { %>
		<%@ include file="/view_text_list.jspf" %>
	<% } else {  %>
		<%@ include file="/view_list_only.jspf" %>
	<% }%> 

</div>
<div class="calendarevent-footer no-print clearfix">
    <div id="calendar-event-pagination" class="pagination pagination-small"></div>
</div>