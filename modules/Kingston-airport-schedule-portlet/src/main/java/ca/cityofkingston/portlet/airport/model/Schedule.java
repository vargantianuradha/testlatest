package ca.cityofkingston.portlet.airport.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Schedule implements Serializable {

	public Schedule(){
		_flights = new ArrayList<Flight>();
	}
	
	public ArrayList<Flight> getFlights() {
		return _flights;
	}
	
	public void addFlight(Flight flight) {
		_flights.add(flight);
	}
	
	ArrayList<Flight> _flights = null;
}
