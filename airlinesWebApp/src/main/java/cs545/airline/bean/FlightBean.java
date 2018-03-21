package cs545.airline.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.FlightService;

@RequestScoped
@Named
public class FlightBean {
	private Date date;
	private Date time;
	private String departure;
	private String destination;
	private String airlineName;
	

	@Inject
	private FlightService flightService;
	@Inject
	private AirlineService airlineSerivce;
	
	private List<Flight> flights = new ArrayList<>();
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
		flights = flightService.findAll();
		return flights;
	}
	
	public void filterFlights() {
		flights = flightService.findByFilters(date, time,airlineName, departure, destination );

	}
}
