package ca.cityofkingston.portlet.airport.model;

import ca.cityofkingston.portlet.airport.util.ScheduleCacheItem;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;

import java.util.Calendar;
import java.util.Comparator;
import java.util.TimeZone;

public class Flight {

	public enum TYPE {
		ARRIVAL, DEPARTURE
	}
	
	public Flight(){
		
	}
	
	public void setHostAirportCode(String hostAirportCode) {
		_hostAirportCode = hostAirportCode;
	}
	
	public String getHostAirportCode() {
		return _hostAirportCode;
	}

	public void setHostAirportCity(String hostAirportCity) {
		_hostAirportCode = hostAirportCity;
	}
	
	public String getHostAirportCity() {
		return _hostAirportCity;
	}

	public void setFlightNumber(String flightNumber) {
		_flightNumber = flightNumber;
	}
	
	public String getFlightNumber() {
		return _flightNumber;
	}

	public void setFlightDate(String flightDate) {
		_flightDate = flightDate;
	}
	
	public String getFlightDate() {
		return _flightDate;
	}

	public Calendar getFlightDate(TimeZone tz) {
		Calendar cal = CalendarFactoryUtil.getCalendar(tz);
		if (_flightDate.length() == 10) {
			int year 	= Integer.parseInt(_flightDate.substring(0,4));
			int month 	= Integer.parseInt(_flightDate.substring(5,7));
			int date 	= Integer.parseInt(_flightDate.substring(8,10));
			
			//-- Remember Month is counted from 0 not 1.
			cal.set(year, month-1, date);
		}
		
		return cal;
	}
	
	public void setAirlineCode(String airlineCode) {
		_airlineCode = airlineCode;
	}
	
	public String getAirlineCode() {
		return _airlineCode;
	}

	public void setType(TYPE type) {
		_type = type;
	}
	
	public TYPE getType() {
		return _type;
	}

	public void setViaAirportCode(String viaAirportCode) {
		_viaAirportCode = viaAirportCode;
	}
	
	public String getViaAirporCode() {
		return _viaAirportCode;
	}

	public String getViaAirportCity() {
		return _viaAirportCity;
		
	}
	public void setViaAirportCity(String viaAirportCity) {
		_viaAirportCity = viaAirportCity;
		
	}

	public void setScheduleTime(String scheduleTime) {
		_scheduleTime = scheduleTime;
	}
	
	public String getScheduleTime() {
		return _scheduleTime;
	}

	public void setEstimatedTime(String estimatedTime) {
		_estimatedTime = estimatedTime;
	}
	
	public String getEstimatedTime() {
		return _estimatedTime;
	}

	public void setStatus(String status) {
		_status = status;
	}
	
	public String getStatus() {
		return _status;
	}

	public String getAircraftType() {
		return _aircraftType;
	}

	public void setAircraftType(String _aircraftType) {
		this._aircraftType = _aircraftType;
	}

	public static Comparator<Flight> flightComparator = new Comparator<Flight>() {         
	    @Override         
	    public int compare(Flight flight1, Flight flight2) {             
	      int result = flight1.getFlightDate().compareTo(flight2.getFlightDate());
	      if ( result == 0) {
	    	  result = flight1.getScheduleTime().compareTo( flight2.getScheduleTime());           
	      }
	      return result;
	    }     
	  };   
	private String _hostAirportCode = "";
	private String _hostAirportCity = "";
	private String _flightNumber	= "";
	private String _flightDate		= "";
	private String _airlineCode		= "";
	private String _aircraftType	= "";
	private TYPE   _type			= TYPE.ARRIVAL;
	private String _viaAirportCode	= "";
	private String _viaAirportCity	= ""; 
	private String _scheduleTime	= "";
	private String _estimatedTime	= "";
	private String _status			= "";

	private static final Log logger = LogFactoryUtil.getLog(Flight.class);

}
