package cs545.airline.bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;

@RequestScoped
@Named
public class FlightBean {

	@Inject
	private FlightService flightService;
	private String date;
	private String departure = "";
	private String destination = "";
	private String airlineName = "";

	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
	private List<Flight> flights = new ArrayList<>();

//	@PostConstruct
//	public void init() {
//		this.flights = flightService.findAll();
//		System.out.println("=====================================TEST=================================" +flights.size());
//	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getAirlineName() {
		return airlineName;

	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public FlightService getFlightService() {
		return flightService;
	}

	public void setFlightService(FlightService flightService) {
		this.flightService = flightService;

	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public List<Flight> getFlights() {
		System.out.println("IS POSTBACK:" + FacesContext.getCurrentInstance().isPostback());
		if (!FacesContext.getCurrentInstance().isPostback()) {
			
			flights = flightService.findAll();
		}
		return flights;
//		return internalSearch();

	}
	public List<Flight> internalSearch() {
		Date dateConvert = null;
		if (this.date != null && this.date.isEmpty()!=true) {
			try {
				dateConvert = df.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		return flightService.findByFilters(dateConvert, airlineName, departure, destination);
	}
	public void filterFlights() {
		flights = internalSearch();
	}
}
