<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="ca.cityofkingston.configuration.PaymentConfigurationAction"%>
<%@page import="ca.cityofkingston.configuration.PaymentConfiguration"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@include file="/init.jsp" %>

<%

String redirect = ParamUtil.getString(request, "redirect");

String portletResource = ParamUtil.getString(request, PaymentConfiguration.PORTLET_RESOURCE);

PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);


PaymentConfiguration configuration = PaymentConfiguration.make(request, preferences);
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
<liferay-ui:success key="<%=PaymentConfigurationAction.PAYMENT_CONFIG_SUCCESS_MSG_KEY%>" message="Configuration has been saved successfully." />

<aui:form action="<%= configurationURL %>" method="post" name="fm" style="width:95%; margin:auto;">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	
	<aui:input label="gBiz Server URL" name="<%=PaymentConfiguration.GBIZ_URL_REQ_PARAM %>" value="<%=configuration.getGbizURL()%>" />
	<aui:input label="Help URL" name="<%=PaymentConfiguration.HELP_URL_PARAM %>" value="<%=configuration.getHelpURL()%>" />
	<aui:input label="Rec URL" name="<%=PaymentConfiguration.REC_URL_PARAM %>" value="<%=configuration.getRecURL()%>" />

	<br/>
	<aui:input
			label="Send Email on Errors"
			name="<%=PaymentConfiguration.IS_MAIL_ENABLED_REQ_PARAM%>"
			type="checkbox"
			value="<%=configuration.isEmailEnabled()%>"/>
			
	<aui:fieldset label="Services">
		<aui:input label="Recreation" name="<%=PaymentConfiguration.RECREATION_SVC_PARAM%>" type="checkbox" value="<%=configuration.isRecreationOnline()%>"/>
		<aui:input label="Transit Pass" name="<%=PaymentConfiguration.TRANSIT_PASS_SVC_PARAM%>" type="checkbox" value="<%=configuration.isTransitPassOnline()%>"/>
		<aui:input label="Parking Ticket" name="<%=PaymentConfiguration.PARKING_TICKET_SVC_PARAM%>" type="checkbox" value="<%=configuration.isParkingTicketOnline()%>"/>
		<aui:input label="Parking Permit" name="<%=PaymentConfiguration.PARKING_PERMIT_SVC_PARAM%>" type="checkbox" value="<%=configuration.isParkingPermitOnline()%>"/>
		<aui:input label="Pet Tag Licensing" name="<%=PaymentConfiguration.LICENSE_PET_TAG_SVC_PARAM%>" type="checkbox" value="<%=configuration.isPetTagLicensingOnline()%>"/>
		<aui:input label="Pet Tag Renewal" name="<%=PaymentConfiguration.RENEW_PET_TAG_SVC_PARAM%>" type="checkbox" value="<%=configuration.isPetTagRenewalOnline()%>"/>
		<aui:input label="Garbage Bag Tag" name="<%=PaymentConfiguration.GARBAGE_BAG_TAG_SVC_PARAM%>" type="checkbox" value="<%=configuration.isGarbageTagOnline()%>" />
		<aui:input label="POA Offence Notice" name="<%=PaymentConfiguration.POA_OFFENCE_NOTICES_SVC_PARAM%>" type="checkbox" value="<%=configuration.isPOAOffenceNoticesOnline()%>"/>
		<aui:input label="POA Notice of Trial" name="<%=PaymentConfiguration.POA_NOTICE_OF_TRIAL_SVC_PARAM%>" type="checkbox" value="<%=configuration.isPOANoticeOfTrialOnline()%>"/>
		<aui:input label="POA Notice of Fine and Due Date" name="<%=PaymentConfiguration.POA_NOTICE_OF_FINE_AND_DUE_DATE_SVC_PARAM%>" type="checkbox" value="<%=configuration.isPOANoticeOfFineAndDueDateOnline()%>" />
		<aui:input label="POA Collections Notice" name="<%=PaymentConfiguration.POA_COLLECTIONS_NOTICES_SVC_PARAM%>" type="checkbox" value="<%=configuration.isPOACollectionsNoticesOnline()%>" />		
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>


