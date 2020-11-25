<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />

<jsp:useBean id="view" scope="request" class="ca.cityofkingston.payments.View"/>

<%
	if(view.isShoppingCartViewable()) {
%>
	<portlet:renderURL var="basketURL" >
		<portlet:param name="wNext" value="Basket"/>
		<portlet:param name="render" value="payments" />
	</portlet:renderURL>
	<a href="<%=basketURL%>" id="shopping-cart"><img style="cursor:pointer;width: 18px;" src="<%=renderRequest.getContextPath()%>/gBizStore/images/cart.png"/>
		<%= (view.isFrench()) ? "Panier" : "Shopping Cart" %>
	</a>
<% 
	} 
%>
<%= view.getHTML()  %>

<jsp:include page="/olp-support.jsp" />

 