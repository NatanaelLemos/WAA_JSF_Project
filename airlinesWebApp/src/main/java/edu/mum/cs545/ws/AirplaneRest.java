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
@Produces("application/json")
@Consumes("application/json")
public class AirplaneRest extends WsBase {

    @Inject
    private AirplaneService airplaneService;

    @GET
    public List<Airplane> get() {
        List<Airplane> airplanes = airplaneService.findAll();

        for (Airplane airplane : airplanes) {
            removeFlights(airplane);
        }

        return airplanes;
    }

    @GET
    @Path("{id}")
    public Airplane get(@PathParam("id") long id) {
        Airplane airplane = new Airplane();
        airplane.setId(id);
        airplane = airplaneService.find(airplane);
        return removeFlights(airplane);
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
        airplaneService.update(airplane);
        removeFlights(airplane);
        return airplane;
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
