package edu.mum.cs545.dto;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class FlightEdit {
    private String flightnr;
    private Date departureDate;
    private Date departureTime;
    private Date arrivalDate;
    private Date arrivalTime;
    private long airlineId;
    private long originId;
    private long destinationId;
    private long airplaneId;

    private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
            Locale.US);
    private static DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT,
            Locale.US);

    public String getFlightnr() {
        return flightnr;
    }
    public void setFlightnr(String flightnr) {
        this.flightnr = flightnr;
    }

    public String getDepartureDate() {
        return df.format(departureDate);
    }
    public void setDepartureDate(String departureDate) {
        try {
            this.departureDate = df.parse(departureDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDepartureTime() {
        return tf.format(departureTime);
    }
    public void setDepartureTime(String departureTime) {
        try {
            this.departureTime = tf.parse(departureTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getArrivalDate() {
        return df.format(arrivalDate);
    }
    public void setArrivalDate(String arrivalDate) {
        try {
            this.arrivalDate = df.parse(arrivalDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getArrivalTime() {
        return tf.format(arrivalTime);
    }
    public void setArrivalTime(String arrivalTime) {
        try {
            this.arrivalTime = tf.parse(arrivalTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(long airlineId) {
        this.airlineId = airlineId;
    }

    public long getOriginId() {
        return originId;
    }

    public void setOriginId(long originId) {
        this.originId = originId;
    }

    public long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
    }

    public long getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(long airplaneId) {
        this.airplaneId = airplaneId;
    }

}
