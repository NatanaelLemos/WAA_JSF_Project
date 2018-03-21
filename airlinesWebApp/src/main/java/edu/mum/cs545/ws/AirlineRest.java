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
public class AirlineRest extends WsBase {

    @Inject
    private AirlineService airlineService;

    @GET
    public List<Airline> get() {
        List<Airline> airlines = airlineService.findAll();

        for (Airline airline : airlines) {
            removeFlights(airline);
        }

        return airlines;
    }

    @GET
    @Path("{id}")
    public Airline get(@PathParam("id") long id) {
        Airline airline = new Airline();
        airline.setId(id);
        airline = airlineService.find(airline);
        return removeFlights(airline);
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
        airlineService.update(airline);
        return removeFlights(airline);
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
