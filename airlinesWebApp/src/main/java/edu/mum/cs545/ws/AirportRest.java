package edu.mum.cs545.ws;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;
import edu.mum.cs545.dto.Departure;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import java.util.List;

@Named
@Path("airport")
@Produces("application/json")
@Consumes("application/json")
public class AirportRest extends WsBase {

    @Inject
    private AirportService airportService;
    @Inject
    private AirlineService airlineService;
    @Inject
    private AirplaneService airplaneService;
    @Inject
    private FlightService flightService;

    @GET
    public List<Airport> get() {
        List<Airport> airports = airportService.findAll();

        for (Airport airport : airports) {
            removeFlights(airport);
        }

        return airports;
    }

    @GET
    @Path("{id}")
    public Airport get(@PathParam("id") long id) {
        Airport airport = new Airport();
        airport.setId(id);
        airport = airportService.find(airport);
        return removeFlights(airport);
    }

    @POST
    public Airport post(Airport airport) {
        airportService.create(airport);
        return airport;
    }

    @PUT
    @Path("{id}")
    public Airport put(@PathParam("id") long id, Airport airport) {
        airport.setId(id);
        airportService.update(airport);
        return airport;
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        Airport airport = new Airport();
        airport.setId(id);
        airport = airportService.find(airport);
        airportService.delete(airport);
    }

    @GET
    @Path("{id}/departure")
    public List<Flight> getDepartures(@PathParam("id") long id) {
        Airport airport = new Airport();
        airport.setId(id);
        airport = airportService.find(airport);

        List<Flight> departures = flightService.findByOrigin(airport);
        for (Flight flight : departures) {
            removeRelations(flight);
        }

        return departures;
    }

    @POST
    @Path("{id}/departure")
    public Flight postDeparture(@PathParam("id") long id, Departure departure) {
        Airport airport = new Airport();
        airport.setId(id);
        airport = airportService.find(airport);

        Airline airline = new Airline();
        airline.setId(departure.getAirlineId());
        airline = airlineService.find(airline);

        Airport destination = new Airport();
        destination.setId(departure.getDestinationId());
        destination = airportService.find(destination);

        Airplane airplane = new Airplane();
        airplane.setId(departure.getAirplaneId());
        airplane = airplaneService.find(airplane);

        Flight flight = new Flight(
                departure.getFlightnr(),
                departure.getDepartureDate(),
                departure.getDepartureTime(),
                departure.getArrivalDate(),
                departure.getArrivalTime(),
                airline,
                airport,
                destination,
                airplane
        );

        airport.setName(airport.getName() + "a");
        airport.addDeparture(flight);
        Airport s = airportService.update(airport);
        System.out.println("Updated airport name " + s.getName());
        //flightService.create(flight);

        return removeRelations(flight);
    }
}
