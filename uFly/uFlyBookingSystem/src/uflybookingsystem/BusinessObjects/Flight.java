/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem.BusinessObjects;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 91028358
 */
public class Flight {
    private String flightNumber;
    private String departureAirport;
    private String destinationAirport;
    private double price;
    private Date dateTime;
    private String plane;
    private int seatsTaken;
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    @Override 
    public String toString()
    {
        return formatter.format(dateTime);
    }

    /**
     * @return the flightNumber
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @param flightNumber the flightNumber to set
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * @return the departureAirport
     */
    public String getDepartureAirport() {
        return departureAirport;
    }

    /**
     * @param departureAirport the departureAirport to set
     */
    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    /**
     * @return the destinationAirport
     */
    public String getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * @param destinationAirport the destinationAirport to set
     */
    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the plane
     */
    public String getPlane() {
        return plane;
    }

    /**
     * @param plane the plane to set
     */
    public void setPlane(String plane) {
        this.plane = plane;
    }

    /**
     * @return the seatsTaken
     */
    public int getSeatsTaken() {
        return seatsTaken;
    }

    /**
     * @param seatsTaken the seatsTaken to set
     */
    public void setSeatsTaken(int seatsTaken) {
        this.seatsTaken = seatsTaken;
    }
    
    
}
