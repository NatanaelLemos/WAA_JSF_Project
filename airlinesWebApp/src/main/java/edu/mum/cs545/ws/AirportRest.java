package edu.mum.cs545.ws;

import cs545.airline.model.Airport;
import cs545.airline.service.AirportService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import java.util.List;

@Named
@Path("airport")
public class AirportRest {

    @Inject
    private AirportService airportService;

    @GET
    public List<Airport> get() {
        return airportService.findAll();
    }

    @GET
    @Path("{id}")
    public Airport get(@PathParam("id") long id) {
        Airport airport = new Airport();
        airport.setId(id);
        return airportService.find(airport);
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
        return airportService.update(airport);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        Airport airport = new Airport();
        airport.setId(id);
        airport = airportService.find(airport);
        airportService.delete(airport);
    }
}
