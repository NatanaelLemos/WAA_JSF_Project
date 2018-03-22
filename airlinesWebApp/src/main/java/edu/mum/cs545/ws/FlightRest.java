package edu.mum.cs545.ws;

import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import java.util.List;

@Named
@Path("flight")
public class FlightRest {

    @Inject
    private FlightService flightService;

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
}
