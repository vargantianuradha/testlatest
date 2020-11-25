<%@page import="ca.cityofkingston.staffdirectory.StaffDirectoryPortlet"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />

<%
String lastname = (String)request.getAttribute(StaffDirectoryPortlet.LAST_NAME_ATTRIBUTE);
String firstname = (String)request.getAttribute(StaffDirectoryPortlet.FIRST_NAME_ATTRIBUTE);
%>

<portlet:defineObjects />
<portlet:actionURL var="searchURL" />

<h3 class="kingston-title">Search by Name</h3>
<div class="staff-container">
	<aui:form cssClass="staff-search-form" action="<%= searchURL %>">
		<div class="staff-row staff-row-first kingston-even-row staff-search-inputs">
			<div class="staff-column staff-search-column-input">
				<aui:input cssClass="staff-search-input" inlineLabel="left" inlineField="true" label="Last:" name="lastname" type="text" value="<%=lastname %>" />
			</div>
			<div class="staff-column staff-search-column-input">
				<aui:input cssClass="staff-search-input" inlineLabel="left" inlineField="true" label="First:" name="firstname" type="text" value="<%=firstname %>" ></aui:input>
			</div>
			<div class="staff-column staff-search-column-submit">
				<aui:button cssClass="staff-search-button-submit" type="submit" value="Start Search" />
			</div>
		</div>
		<div class="staff-row staff-row-last kingston-odd-row staff-search-footer">
			Enter a first name and/or a last name ... or just the first few letters.
		</div>
	</aui:form>
</div>

