package ca.cityofkingston.portlet.airport.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.List;

import ca.cityofkingston.portlet.airport.model.Flight;
import ca.cityofkingston.portlet.airport.model.Flight.TYPE;
import ca.cityofkingston.portlet.airport.model.Schedule;

public class ScheduleCacheItem implements WebCacheItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String iFidsUrl = "";
	
	public ScheduleCacheItem(String inUrl) {
		iFidsUrl = inUrl;
	}

	public Object convert(String key) throws WebCacheException {
		Schedule schedule = new Schedule();

		try {
			logger.info("YGKAirport calling " + iFidsUrl );
			String xml = HttpUtil.URLtoString(iFidsUrl);
			
			Document doc = SAXReaderUtil.read(xml);
			logger.info("Response:\n"+doc.asXML());

			List<Node> nodes = doc.selectNodes("/YGKFlights/Flight");
			logger.info("# of flight nodes = "+nodes.size());
			for (Node node : nodes) {	
				Flight flight = new Flight();
				//-- This fields isn't currently being published. So setting default to be always be "AC"
				String airlineCode = getSingleNodeText(node, "CarrierIATA");
				if (airlineCode == null || airlineCode.isEmpty())
					airlineCode = "AC";
				flight.setAirlineCode(airlineCode);
				flight.setEstimatedTime(getSingleNodeText(node,"RevisedTime"));
				flight.setFlightDate(getSingleNodeText(node,"SchedDate"));
				flight.setFlightNumber(getSingleNodeText(node,"FlightNumber"));
	//			flight.setHostAirportCity(node.selectSingleNode("HostAirportCity").getText());
	//			flight.setHostAirportCode(node.selectSingleNode("HostAirportCode").getText());
				flight.setScheduleTime(getSingleNodeText(node,"SchedTime"));
				flight.setStatus(getSingleNodeText(node,"Status"));
				flight.setViaAirportCode(getSingleNodeText(node,"CitiesIATA"));
				flight.setViaAirportCity(getSingleNodeText(node,"Cities"));
				//flight.setAircraftType(node.selectSingleNode("Aircraft").getText());
				//-- default type is Arrival
				if (getSingleNodeText(node,"ArrDep").equals("D"))
					flight.setType(TYPE.DEPARTURE);
				
				schedule.addFlight(flight);
			}
			
		}
		catch (Exception e) {
			logger.error("failed trying to build schedule from " + iFidsUrl + "\n" + e );
			throw new WebCacheException();
		}

		return schedule;
	}

	private String getSingleNodeText(Node node, String label) {
		String result = "";
		Node actualNode = node.selectSingleNode(label);
		if (actualNode != null)
			result = actualNode.getText();
		else
			logger.error("Expected a value for " + label);
		
		return result;
	}

	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.MINUTE * 2;
	private static final Log logger = LogFactoryUtil.getLog(ScheduleCacheItem.class);

}
