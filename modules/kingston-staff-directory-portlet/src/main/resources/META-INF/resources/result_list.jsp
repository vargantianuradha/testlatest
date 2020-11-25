<%@page import="ca.cityofkingston.staffdirectory.StaffDirectoryPortlet"%>
<%@page import="ca.cityofkingston.model.StaffPeople"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<portlet:defineObjects />


<%@page import="java.util.List"%>
<%
List<StaffPeople> results = (List<StaffPeople>) request.getAttribute(StaffDirectoryPortlet.RESULT_LIST_ATTRIBUTE);
if (results != null) {
%>
	<h3 class="kingston-title">Your Search Results</h3>

	<c:choose>
		<c:when test="${empty results}">
			<p class="portlet-msg-alert">Sorry ... No Matching Results</p>
			<p>If you are conducting a name search, try using just the first or last name or using an alternative spelling.</p>
			<p>Partial names can help if you're not sure of the proper spelling. Example: J  SM  would match "Joe Smith".</p>
		</c:when>
		<c:otherwise>
			<p>Below are the results matching your staff-directory query</p>
		</c:otherwise>
	</c:choose>

<%
	for(StaffPeople staffPeople: results){
%>
		<div class="staff-container">
			<div class="staff-row staff-row-first staff-result-title uppercase">
				<%= staffPeople.getLast() %>, <%= staffPeople.getFirst() %>
			</div>
			<div class="staff-row kingston-even-row">
				<div class="staff-column staff-result-row-header">
					<span class="emphasized_label">Title</span>
					<spam>/ Division:</span>
				</div>
				<div class="staff-column staff-result-row-content">
					<span class="emphasized_label"><%= staffPeople.getTitle() %></span>
					<span>/ <%= staffPeople.getDivision() %></span>
				</div>
			</div>
			<div class="staff-row kingston-odd-row">
				<div class="staff-column staff-result-row-header">
					<span class="emphasized_label">Group</span>
					<span>/ Department:</span>
				</div>
				<div class="staff-column staff-result-row-content">
					<span class="emphasized_label"><%=staffPeople.getGroup() %></span>
					<span>/ <%=staffPeople.getDepartment() %></span>
					</div>
			</div>
			<div class="staff-row staff-row-last kingston-even-row">
				<div class="staff-mobile-column">
					<div class="staff-column staff-result-row-header emphasized_label">Phone:</div>
					<div class="staff-column staff-result-row-content"><%= staffPeople.getPhone()%></div>
				</div>
				<div class="staff-mobile-column">
					<div class="staff-column emphasized_label">Fax:</div>
					<div class="staff-column staff-result-row-content"><%=staffPeople.getFax() %></div>
				</div>
			</div>
		</div>
<%	}
}
%>

