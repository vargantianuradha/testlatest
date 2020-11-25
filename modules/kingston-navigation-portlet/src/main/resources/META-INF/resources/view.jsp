<%@include file="/init.jsp" %>

<%@ page import="ca.cityofkingston.navigation.catalogue.NavigationItem" %>
<%@ page import="ca.cityofkingston.navigation.html.NavigationCurrentComponent"%>
<%@ page import="ca.cityofkingston.navigation.html.NavigationMainComponent"%>
<%@ page import="ca.cityofkingston.navigation.NavigationPortlet" %>

<%
NavigationItem currentNavigationItem = (NavigationItem) request.getAttribute(NavigationPortlet.CURRENT_NAVIGATION);
NavigationCurrentComponent navigationCurrentComponent = NavigationCurrentComponent.make(currentNavigationItem);

NavigationItem mainNavigationItem = (NavigationItem) request.getAttribute(NavigationPortlet.MAIN_NAVIGATION);
NavigationMainComponent navigationMainComponent = NavigationMainComponent.make(mainNavigationItem);
%>

<div class="side-nav-menu">
	<%=navigationCurrentComponent.toString()%>
	
	<%=navigationMainComponent.toString()%>
</div>