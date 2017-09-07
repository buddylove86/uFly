/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem.BusinessObjects;

/**
 *
 * @author 91028358
 */
public class Location {
    private String city;
    private String airportCode;
    
    @Override
    public String toString()
    {
        return city + " " + airportCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the AirportCode
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     * @param airportCode the AirportCode to set
     */
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }
    
}
