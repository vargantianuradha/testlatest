<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="java.util.Collections"%>
<%@page import="ca.cityofkingston.portlet.airport.model.Flight"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ca.cityofkingston.portlet.airport.util.ScheduleUtil"%>
<%@page import="ca.cityofkingston.portlet.airport.model.Schedule"%>
<%@include file="/html/schedule/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />

<% 
String iFidsURL 		= portletPreferences.getValue("iFidsUrl","");
String checkFlightsURL	= portletPreferences.getValue("checkFlightsUrl","");
int numDisplayEntries 	= GetterUtil.getInteger(portletPreferences.getValue("numDisplayEntries","0"));

Schedule schedule = ScheduleUtil.getSchedule(iFidsURL);
ArrayList<Flight> allFlights = schedule.getFlights();
ArrayList<Flight> arrivals = new ArrayList<Flight>();
ArrayList<Flight> departures = new ArrayList<Flight>();

for (Flight flight : allFlights) {
	if (flight.getType() == Flight.TYPE.ARRIVAL)
		arrivals.add(flight);
	else
		departures.add(flight);
}

Collections.sort(arrivals,Flight.flightComparator );
Collections.sort(departures,Flight.flightComparator );

%>

<div class="tab">
  <button class="tablinks active" onclick="openCity(event, 'arrivals')">Arrivals</button>
  <button class="tablinks" onclick="openCity(event, 'departures')">Departures</button>
</div>

<div id="arrivals" class="tabcontent" style="display: block;">

<%
if (arrivals.isEmpty()) {		
%>	
<div class="error-message">No flights are available</div>
<%
} else {
%>

<table class="schedule">
<tr>
	<th>Flight</th>
	<th>Number</th>
	<th class="hide-when-mobile">Origin</th>
	<th>Schedule</th>
	<th>Estimated</th>
	<th>Status</th>
</tr>
<%	
	int counter = 0;
	
	Calendar currentCal = CalendarFactoryUtil.getCalendar(currentUser.getTimeZone());
	
	for (Flight flight : arrivals) {
	
		if (numDisplayEntries > 0 && counter >= numDisplayEntries )
			break;
		
		Calendar flightCal = flight.getFlightDate(TimeZoneUtil.getTimeZone("America/Toronto"));
		flightCal.setTimeZone(currentUser.getTimeZone());
		
		if (flightCal.get(Calendar.DAY_OF_YEAR) != currentCal.get(Calendar.DAY_OF_YEAR) ) {
			out.print("<tr><td class=\"next-day\" colspan=\"6\"><hr><span>Next Day</span><hr></td></tr>");
		}		
		currentCal = flightCal; 
%>
	<tr>
		<td>
			<c:choose>
				<c:when test="<%= ScheduleUtil.isKnownAirline(flight.getAirlineCode()) %>">		
					<img src="<%= request.getContextPath()%>/images/airline/<%=flight.getAirlineCode() %>.png" />
				</c:when>
				<c:otherwise>
					<%= flight.getAirlineCode() %>
				</c:otherwise>
			</c:choose>
		</td>
		<td><%=flight.getFlightNumber() %></td>
		<td class="hide-when-mobile"><%=flight.getViaAirportCity() %></td>
		<td><%=ScheduleUtil.formatScheduleTime(flight.getScheduleTime()) %></td>
		<td><%=ScheduleUtil.formatEstimatedArrival(flight.getEstimatedTime()) %></td>
		<td><%=flight.getStatus() %></td>
	</tr>
<%
		counter++;
	}
}
%>
</table>

</div>

<div id="departures" class="tabcontent">

<%
if (departures.isEmpty()) {		
%>	
<p>No flights are available</p>
<%
} else {
%>

<table class="schedule">
<tr>
	<th>Flight</th>
	<th>Number</th>
	<th class="hide-when-mobile">Origin</th>
	<th>Schedule</th>
	<th>Estimated</th>
	<th>Status</th>
</tr>
<%	
	int counter = 0;
	Calendar currentCal = CalendarFactoryUtil.getCalendar(currentUser.getTimeZone());
	
	for (Flight flight : departures) {
	
		if (numDisplayEntries > 0 && counter >= numDisplayEntries )
			break;

		Calendar flightCal = flight.getFlightDate(TimeZoneUtil.getTimeZone("America/Toronto"));
		flightCal.setTimeZone(currentUser.getTimeZone());
		
		if (flightCal.get(Calendar.DAY_OF_YEAR) != currentCal.get(Calendar.DAY_OF_YEAR) ) {
			out.print("<tr><td class=\"next-day\" colspan=\"6\"><hr><span>Next Day</span><hr></td></tr>");
		}		
		currentCal = flightCal; 

%>
	<tr>
		<td>
			<c:choose>
				<c:when test="<%= ScheduleUtil.isKnownAirline(flight.getAirlineCode()) %>">		
					<img src="<%= request.getContextPath()%>/images/airline/<%=flight.getAirlineCode() %>.png" />
				</c:when>
				<c:otherwise>
					<%= flight.getAirlineCode() %>
				</c:otherwise>
			</c:choose>
		</td>
		<td><%=flight.getFlightNumber() %></td>
		<td class="hide-when-mobile"><%=flight.getViaAirportCity() %></td>
		<td><%=ScheduleUtil.formatScheduleTime(flight.getScheduleTime()) %></td>
		<td><%=ScheduleUtil.formatEstimatedArrival(flight.getEstimatedTime()) %></td>
		<td><%=flight.getStatus() %></td>
	</tr>
<%
		counter++;
	}
}
%>
</table>

</div>
<c:if test="<%=Validator.isNotNull(checkFlightsURL) %>">
	<div class="tab checkFlights">
	  <button class="tablinks active" onclick="window.open('<%=checkFlightsURL %>','flights')">Connecting Flights</button>
	</div>
</c:if>

<script type="text/javascript">
function openCity(evt, cityName) {
	  // Declare all variables
	  var i, tabcontent, tablinks;

	  // Get all elements with class="tabcontent" and hide them
	  tabcontent = document.getElementsByClassName("tabcontent");
	  for (i = 0; i < tabcontent.length; i++) {
	    tabcontent[i].style.display = "none";
	  }

	  // Get all elements with class="tablinks" and remove the class "active"
	  tablinks = document.getElementsByClassName("tablinks");
	  for (i = 0; i < tablinks.length; i++) {
	    tablinks[i].className = tablinks[i].className.replace(" active", "");
	  }

	  // Show the current tab, and add an "active" class to the button that opened the tab
	  document.getElementById(cityName).style.display = "block";
	  evt.currentTarget.className += " active";
	}
</script>
