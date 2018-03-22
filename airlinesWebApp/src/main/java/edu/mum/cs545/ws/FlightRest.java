package edu.mum.cs545.ws;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;
import edu.mum.cs545.dto.AirlineFlight;
import edu.mum.cs545.dto.FlightEdit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import java.util.List;

@Named
@Path("flight")
public class FlightRest {

    @Inject
    private FlightService flightService;
    @Inject
    private AirlineService airlineService;
    @Inject
    private AirportService airportService;
    @Inject
    private AirplaneService airplaneService;

    @GET
    public List<Flight>  get() {
        return flightService.findAll();
    }

    @GET
    @Path("{id}")
    public Flight get(@PathParam("id") long id) {
        Flight flight = new Flight();
        flight.setId(id);
        return flightService.find(flight);
    }

    @PUT
    @Path("{id}")
    public Flight put(@PathParam("id") long id, FlightEdit flightEdit){
        Flight flight = new Flight();
        flight.setId(id);
        flight = flightService.find(flight);

        Airplane airplane = new Airplane();
        airplane.setId(flightEdit.getAirplaneId());
        flight.setAirplane(airplaneService.find(airplane));

        Airline airline = new Airline();
        airline.setId(flightEdit.getAirlineId());
        flight.setAirline(airlineService.find(airline));

        Airport origin = new Airport();
        origin.setId(flightEdit.getOriginId());
        flight.setOrigin(airportService.find(origin));

        Airport destination = new Airport();
        destination.setId(flightEdit.getDestinationId());
        flight.setDestination(airportService.find(destination));

        flight.setArrivalTime(flightEdit.getArrivalTime());
        flight.setArrivalDate(flightEdit.getArrivalDate());
        flight.setDepartureDate(flightEdit.getDepartureDate());
        flight.setDepartureTime(flightEdit.getDepartureTime());
        flight.setFlightnr(flightEdit.getFlightnr());

        return flightService.update(flight);
    }

    @DELETE
    @Path("{id}")
    public void Delete(@PathParam("id") long id){
        Flight flight = new Flight();
        flight.setId(id);
        flight = flightService.find(flight);
        flightService.delete(flight);
    }
}
