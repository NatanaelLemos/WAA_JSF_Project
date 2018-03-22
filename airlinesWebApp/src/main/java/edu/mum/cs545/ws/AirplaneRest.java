package edu.mum.cs545.ws;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.service.AirplaneService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import java.util.List;

@Named
@Path("airplane")
public class AirplaneRest {

    @Inject
    private AirplaneService airplaneService;

    @GET
    public List<Airplane> get() {
        return airplaneService.findAll();
    }

    @GET
    @Path("{id}")
    public Airplane get(@PathParam("id") long id) {
        Airplane airplane = new Airplane();
        airplane.setId(id);
        return airplaneService.find(airplane);
    }

    @POST
    public Airplane post(Airplane airplane) {
        airplaneService.create(airplane);
        return airplane;
    }

    @PUT
    @Path("{id}")
    public Airplane put(@PathParam("id") long id, Airplane airplane) {
        airplane.setId(id);
        return airplaneService.update(airplane);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        Airplane airplane = new Airplane();
        airplane.setId(id);
        airplane = airplaneService.find(airplane);
        airplaneService.delete(airplane);
    }
}
