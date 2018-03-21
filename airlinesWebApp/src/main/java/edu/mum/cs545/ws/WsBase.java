package edu.mum.cs545.ws;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;

public abstract class WsBase {
    protected Airline removeFlights(Airline airline) {
        for (int i = airline.getFlights().size() - 1; i >= 0; i--) {
            airline.removeFlight(airline.getFlights().get(i));
        }

        return airline;
    }

    protected Airplane removeFlights(Airplane airplane) {
        for (int i = airplane.getFlights().size() - 1; i >= 0; i--) {
            airplane.removeFlight(airplane.getFlights().get(i));
        }

        return airplane;
    }

    protected Airport removeFlights(Airport airplane) {
        for (int i = airplane.getArrivals().size() - 1; i >= 0; i--) {
            airplane.removeArrival(airplane.getArrivals().get(i));
        }

        for (int i = airplane.getDepartures().size() - 1; i >= 0; i--) {
            airplane.removeDeparture(airplane.getDepartures().get(i));
        }

        return airplane;
    }

    protected Flight removeRelations(Flight flight) {
        if(flight.getAirline() != null) removeFlights(flight.getAirline());
        if(flight.getAirplane() != null) removeFlights(flight.getAirplane());
        if(flight.getDestination() != null) removeFlights(flight.getDestination());
        if(flight.getOrigin() != null) removeFlights(flight.getOrigin());
        return flight;
    }
}
