package edu.mum.cs545.ws;

import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import java.util.List;

@Named
@Path("flight")
@Produces("application/json")
@Consumes("application/json")
public class FlightRest extends WsBase {

    @Inject
    private FlightService flightService;

    @GET
    public List<Flight>  get() {
        List<Flight> flights = flightService.findAll();

        for (Flight flight : flights) {
            removeRelations(flight);
        }

        return flights;
    }

    @GET
    @Path("{id}")
    public Flight get(@PathParam("id") long id) {
        Flight flight = new Flight();
        flight.setId(id);
        flight = flightService.find(flight);
        return removeRelations(flight);
    }
}
