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

<portlet:renderURL var="recoverShoppingBasketURL" >
	<portlet:param name="render" value="view.jsp" />
	<portlet:param name="wNext" value="Basket" />
</portlet:renderURL>

<portlet:renderURL var="startOverURL" >
	<portlet:param name="render" value="view.jsp" />
</portlet:renderURL>

<% if(view.isDisplayCommunicationErrorContent()) { %>
	
	<h2>System Communication Error</h2>
	<p>We&apos;re sorry. A network communication error has occurred that prevents this server from connecting to online payment support systems.</p>

<% } else { %>

	<h2>System Error Or Session Time-Out</h2>
	<p>We're sorry. A system error has occurred. This could be because your online-payment session has timed out.</p>

<% } %>

<p>If you have placed items <a href="<%=recoverShoppingBasketURL%>">in your shopping basket</a>, you may still be able to recover them.
Otherwise, you can <a href="<%=startOverURL%>">start again</a>.</p>

<hr>

<% if(view.isDisplayCommunicationErrorContent()) { %>
	
	<h2>Erreur de transmission du système</h2>
	<p>Nous sommes désolés. Une erreur de communication de réseau s'est produite qui empêche ce serveur de se connecter à des systèmes de soutien de paiement en ligne.</p>

<% } else { %>

	<h2>Erreur de système ou expiration de la session</h2>
	<p>Nous sommes désolés. Une erreur de système s'est produite, laquelle est peut-être attribuable à l’expiration de votre session de paiement en ligne.</p>

<% } %>

<p>Si vous avez mis des articles <a href="<%=recoverShoppingBasketURL%>">dans votre panier</a>, vous pouvez peut-être les récupérer. Sinon, vous pouvez <a href="<%=startOverURL%>">recommencer</a>.</p>
