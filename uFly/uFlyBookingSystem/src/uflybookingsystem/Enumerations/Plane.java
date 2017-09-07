/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem.Enumerations;

/**
 *
 * @author 91028358
 */
public enum Plane {
    AIRBUSA350(270), AIRBUSA280(500), BOEING737(215),BOEING747(460);
    
    int passengerCapacity;
    
    Plane(int passengerCapacity)
    {
        this.passengerCapacity = passengerCapacity;
    }

    /**
     * @return the passengerCapcity
     */
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    
    
}
