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


 
<%@include file="/init.jsp"%> 
<div style="clear:both;padding-top:5px"> 
   <!-- <liferay-util:include page="/olp-support.jsp">
        </liferay-util:include> 
        -->
  <jsp:include page="/olp-support.jsp" /> 
</div>

<hr>

  <jsp:useBean id="view" scope="request" class="ca.cityofkingston.payments.View"/>  





<h2>Recreation Programs</h2>
<c:choose>
	<c:when test="${view.recreationProgramsOnline}">
		<div class="recreation-program-link">
			<a href="<%= view.getPaymentConfiguration().getRecURL() %>" class="product-item" title="Register for a recreation program">Register for a recreation program</a>
		</div>
	</c:when>
	<c:otherwise>
		<p class="unavailable-service recreation-program-link"><em>The Recreational program registration is currently unavailable</em></p>		
	</c:otherwise>
</c:choose>
<h2>Transit Passes</h2>
<c:choose>
	<c:when test="${view.transitPassOnline}">
		<portlet:renderURL var="transitPassesURL" >
			<portlet:param name="render" value="payments" />
			<portlet:param name="ProductId" value="transitsearch"/>
		</portlet:renderURL>	
		<div class="transit-pass-renewal-link">
			<a href="<%=transitPassesURL%>" class="product-item" title="Renew your kingston transit monthly pass">Renew Kingston Transit Monthly Pass</a>
		</div>
	</c:when>
	<c:otherwise>
		<p class="unavailable-service transit-pass-renewal-link"><em>The Kingston Transit Pass Renewal is currently unavailable</em></p>		
	</c:otherwise>
</c:choose>

<h2>Parking Tickets</h2>
<c:choose>
	<c:when test="${view.parkingTicketOnline}">
		<portlet:renderURL var="parkingTicketURL" >
			<portlet:param name="render" value="payments" />
			<portlet:param name="ProductId" value="ptp2001"/>
		</portlet:renderURL>
		<div class="parking-ticket-link">
			<a href="<%=parkingTicketURL%>" class="product-item" title="Pay your parking ticket">Pay a Parking Ticket Online</a>
		</div>
	</c:when>
	<c:otherwise>
		<p class="unavailable-service parking-ticket-link"><em>The Parking Ticket Service is currently unavailable</em></p>	
	</c:otherwise>
</c:choose>


<h2>Parking Permits</h2>
<c:choose>
	<c:when test="${view.parkingPermitOnline}">
		<portlet:renderURL var="parkingPermitURL" >
			<portlet:param name="render" value="payments" />
			<portlet:param name="ProductId" value="ppr2001"/>
		</portlet:renderURL>
		<div class="parking-permit-link">
			<a href="<%=parkingPermitURL%>" class="product-item" title="Renew your parking permit">Renew Your Current Parking Permit</a>
		</div>
	</c:when>
	<c:otherwise>
		<p class="unavailable-service parking-permit-link"><em>The Parking Permit Service is currently unavailable</em></p>	
	</c:otherwise>
</c:choose>


<h2>Pet Licenses</h2>
<c:choose>
	<c:when test="${view.licensePetTagsOnline}">
		<div class="license-pet-licenses-link">
			<a onclick="_gaq.push(['_trackEvent', 'App-Button', 'clicked', 'DocuPet',, 'true']);" href="https://kingston.docupet.com/signup/info" target="docupet" class="product-item" title="License your pet">License your pet</a>
		</div>
	</c:when>
	<c:otherwise>
		<p class="unavailable-service license-pet-licenses-link"><em>Online Pet Tag registration is currently unavailable</em></p>	
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${view.renewPetTagsOnline}">
		<div class="renew-pet-licenses-link" >
			<a onclick="_gaq.push(['_trackEvent', 'App-Button', 'clicked', 'DocuPet-Renew',, 'true']);" href="https://kingston.docupet.com/renew-now" target="docupet" class="product-item" title="Renew your pet tag online">Renew your pet tags</a>
		</div>
	</c:when>
	<c:otherwise>
		<p class="unavailable-service renew-pet-licenses-link"><em>Online Pet Tag renewal is currently unavailable</em></p>	
	</c:otherwise>
</c:choose>

<h2>Garbage Bag Tags</h2>
<c:choose>
	<c:when test="${view.garbageBagTagsOnline}">
		<portlet:renderURL var="garbageBagTags" >
			<portlet:param name="render" value="payments" />
			<portlet:param name="ProductId" value="gbt1001"/>
		</portlet:renderURL>
		<div class="garbage-bag-tags-link">
			<a href="<%=garbageBagTags%>" class="product-item" title="Purchase Gargbage Bag Tags">Purchase Garbage Bag Tags</a>
		</div>
	</c:when>
	<c:otherwise>
		<p class="unavailable-service garbage-bag-tags-link"><em>The Garbage Tag Service is currently unavailable</em></p>		
	</c:otherwise>
</c:choose>

<%--
	/* POA Service URLs (English/French) */
--%>
<portlet:renderURL var="poaOffenceNoticeURL" >
	<portlet:param name="render" value="payments" />
	<portlet:param name="ProductId" value="poa2101"/>
	<portlet:param name="l" value="en"/>
</portlet:renderURL>
<portlet:renderURL var="poaOffenceNoticeURL_fr" >
	<portlet:param name="render" value="payments" />
	<portlet:param name="ProductId" value="poa2101"/>
	<portlet:param name="l" value="fr"/>
</portlet:renderURL>

<portlet:renderURL var="poaNoticeOfTrialURL" >
	<portlet:param name="render" value="payments" />
	<portlet:param name="ProductId" value="poa2105"/>
	<portlet:param name="l" value="en"/>
</portlet:renderURL>
<portlet:renderURL var="poaNoticeOfTrialURL_fr" >
	<portlet:param name="render" value="payments" />
	<portlet:param name="ProductId" value="poa2105"/>
	<portlet:param name="l" value="fr"/>
</portlet:renderURL>

<portlet:renderURL var="poaNoticeOfFineAndDueDateURL" >
	<portlet:param name="render" value="payments" />
	<portlet:param name="ProductId" value="poa2103"/>
	<portlet:param name="l" value="en"/>
</portlet:renderURL>
<portlet:renderURL var="poaNoticeOfFineAndDueDateURL_fr" >
	<portlet:param name="render" value="payments" />
	<portlet:param name="ProductId" value="poa2103"/>
	<portlet:param name="l" value="fr"/>
</portlet:renderURL>

<portlet:renderURL var="poaCollectionsNoticeURL" >
	<portlet:param name="render" value="payments" />
	<portlet:param name="ProductId" value="poa2107"/>
	<portlet:param name="l" value="en"/>
</portlet:renderURL>
<portlet:renderURL var="poaCollectionsNoticeURL_fr" >
	<portlet:param name="render" value="payments" />
	<portlet:param name="ProductId" value="poa2107"/>
	<portlet:param name="l" value="fr"/>
</portlet:renderURL>

<%--
/* Display Bilingual Notices
 * Enligsh
 * French
 */
--%>

<div style="width:100%; margin:10px auto;" >
	<div style="float:left; width: 300px" >
	<h2>Pay a Provincial Offence After...</h2>
	
	<% if(view.isProvincialOffenceContentDisplayable()) { %>
		<p><strong>Please Note:</strong> We recommend you pay your Provincial Offence fine three to five days before its due date. Electronic payments may take three business days to process.</p>
	<% } %>

	<c:choose>
		<c:when test="${view.poaOffencNoticeOnline}">
			<a href="<%=poaOffenceNoticeURL%>" class="product-item" title="Pay a provincial offence notice">An Offence Notice</a>
			<br />
		</c:when>
		<c:otherwise>
			<p class="unavailable-service"><em>The Provincial Offence After an Offence Notice Service is currently unavailable</em></p>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${view.poaNoticeOfTrialOnline}">
			<a href="<%=poaNoticeOfTrialURL%>" class="product-item" title="Pay a provincial offence notice after receiving a notice of trial">A Notice of Trial</a>
			<br />
		</c:when>
		<c:otherwise>
			<p class="unavailable-service"><em>Paying Provincial Notice of Trial Service is currently unavailable</em></p>	
		</c:otherwise>	
	</c:choose>
	<c:choose>
		<c:when test="${view.poaNoticeOfFineAndDueDateOnline}">
			<a href="<%=poaNoticeOfFineAndDueDateURL%>" class="product-item" title="Pay a provincial offence notice after receiving a notice of fine and due date">A Notice of Fine and Due Date</a>
			<br />
		</c:when>
		<c:otherwise>
			<p class="unavailable-service"><em>The Provincial Notice of Fine and Due Date Service is currently unavailable</em></p>	
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${view.poaCollectionsNoticeService}">
			<a href="<%=poaCollectionsNoticeURL%>" class="product-item" title="Pay a provincial offence notice after a collections notice">A Collections Notice</a>
			<br />
		</c:when>
		<c:otherwise>
			<p class="unavailable-service"><em>The Provincial Collections Notice Service is currently unavailable</em></p>	
		</c:otherwise>
	</c:choose>
	</div>

	<div style="float:left; width: 350px">
	<h2>Payer une infraction provinciale apr�s...</h2>

	<% if(view.isProvincialOffenceContentDisplayable()) { %>
		<p><strong>Remarque:</strong> Nous vous recommandons de payer l’amende qui vous a été imposée en vertu de la Loi sur les infractions provinciales de trois à cinq jours avant sa date d’échéance, car le traitement d’une amende payée par voie électronique peut prendre jusqu’à trois jours ouvrables.
	<% } %>

	<c:choose>
		<c:when test="${view.poaOffencNoticeOnline}">
			<a href="<%=poaOffenceNoticeURL_fr%>" class="product-item" title="Payer un avis d'infraction provinciale">Un avis d'infraction</a>
			<br />
		</c:when>
		<c:otherwise>
			<p class="unavailable-service"><em>Le service de paiement en ligne d’une infraction provinciale après avoir reçu un avis d’infraction n’est pas accessible pour l’instant.</em></p>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${view.poaNoticeOfTrialOnline}">
			<a href="<%=poaNoticeOfTrialURL_fr%>" class="product-item" title="Payer un avis de procès">Un avis de procès</a>
			<br />
		</c:when>
		<c:otherwise>
			<p class="unavailable-service"><em>Le service de paiement en ligne d’une infraction provinciale après avoir reçu un avis de procès n’est pas accessible pour l’instant.</em></p>	
		</c:otherwise>	
	</c:choose>
	<c:choose>
		<c:when test="${view.poaNoticeOfFineAndDueDateOnline}">
			<a href="<%=poaNoticeOfFineAndDueDateURL_fr%>" class="product-item" title="Payer un avis d'amende et d'échéance">Un avis d'amende et d'échéance</a>
			<br />
		</c:when>
		<c:otherwise>
			<p class="unavailable-service"><em>Le service de paiement en ligne d’une infraction provinciale après avoir reçu un avis d’amende et date d'échéance n’est pas accessible pour l’instant.</em></p>	
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${view.poaCollectionsNoticeService}">
			<a href="<%=poaCollectionsNoticeURL_fr%>" class="product-item" title="Payer un avis de recouvrement d'une amende">Avis de recouvrement d'une amende</a>
			<br />
		</c:when>
		<c:otherwise>
			<p class="unavailable-service"><em>Le service de paiement en ligne d’une infraction provinciale après avoir reçu un avis de recouvrement n’est pas accessible pour l’instant.</em></p>	
		</c:otherwise>
	</c:choose>
	</div>
</div>
<div style="clear:both;padding-top:5px"></div>



