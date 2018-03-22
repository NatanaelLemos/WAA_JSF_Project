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

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import java.util.List;

@Named
@Path("airline")
public class CreateFlightRest {

    @Inject
    private FlightService flightService;
    @Inject
    private AirlineService airlineService;
    @Inject
    private AirportService airportService;
    @Inject
    private AirplaneService airplaneService;

    @GET
    @Path("{id}/flight")
    public List<Flight> getFlights(@PathParam("id") long id) {
        Airline airline = new Airline();
        airline.setId(id);
        return flightService.findByAirline(airline);
    }

    @POST
    @Path("{id}/flight")
    public Flight postFlight(@PathParam("id") long id, AirlineFlight departure) {
        List<Flight> flights = flightService.findAll();
        Airline airline = new Airline();
        airline.setId(id);
        airline = airlineService.find(airline);

        Flight flight = new Flight(
                departure.getFlightnr(),
                departure.getDepartureDate(),
                departure.getDepartureTime(),
                departure.getArrivalDate(),
                departure.getArrivalTime()
        );

        Airport origin = new Airport();
        origin.setId(departure.getOriginId());
        flight.setOrigin(airportService.find(origin));

        Airport destination = new Airport();
        destination.setId(departure.getDestinationId());
        flight.setDestination(airportService.find(destination));

        Airplane airplane = new Airplane();
        airplane.setId(departure.getAirplaneId());
        flight.setAirplane(airplaneService.find(airplane));

        airline.addFlight(flight);
        flightService.create(flight);
        return flight;
    }
}