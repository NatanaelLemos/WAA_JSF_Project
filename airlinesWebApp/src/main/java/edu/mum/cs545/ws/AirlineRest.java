package edu.mum.cs545.ws;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Named
@Path("airline")
@Produces("application/json")
@Consumes("application/json")
public class AirlineRest {

    @Inject
    private AirlineService airlineService;

    @GET
    public List<Airline> get() {
        return airlineService.findAll();
    }

    @GET
    @Path("{id}")
    public Airline get(@PathParam("id") long id) {
        Airline airline = new Airline();
        airline.setId(id);
        return airlineService.find(airline);
    }

    @POST
    public Airline post(Airline airline) {
        airlineService.create(airline);
        return airline;
    }

    @PUT
    @Path("{id}")
    public Airline put(@PathParam("id") long id, Airline airline) {
        airline.setId(id);
        return airlineService.update(airline);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        Airline airline = new Airline();
        airline.setId(id);
        airline = airlineService.find(airline);
        airlineService.delete(airline);
    }
}
