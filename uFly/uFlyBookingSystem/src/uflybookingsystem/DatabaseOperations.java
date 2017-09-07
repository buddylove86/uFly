/*
 * This class contains all the common operations that involve retreiving data from the database, 
 * saving data to the database or updating existing database data 
 * for all three tables (Location, Flight and Booking)
 */
package uflybookingsystem;
import java.sql.*;
import java.util.ArrayList;
import uflybookingsystem.BusinessObjects.*;

import java.text.SimpleDateFormat;




public class DatabaseOperations {
    
    
   
    
    //method that gets all the information from the Location table
    public static ArrayList<Location> GetAllLocations(){
        
        ArrayList<Location> locationArray = new ArrayList<>();
        
        try (Connection connection = DbConnector.connectToDb();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM location"))
        {
             
            while (resultSet.next()) 
            {
            	Location location = new Location();
                location.setCity(resultSet.getString("City"));
            	location.setAirportCode(resultSet.getString("AirportCode"));
                locationArray.add(location);
            }
            
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
        } 
         return  locationArray;
    }
    
    // this method returns all the data from the Flight table in the uFly database
    public static ArrayList<Flight> GetAllFlights(String dep, String dest){
        Flight flight;
        ArrayList<Flight> flightArray = new ArrayList<>();
            try (Connection connection = DbConnector.connectToDb(); 
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM flight WHERE (DepartureAirport = '" + dep + 
                        "' AND DestinationAirport = '" + dest + "')"))
            {
             
                while (resultSet.next()) 
                {
                    flight = new Flight();
                    flight.setFlightNumber(resultSet.getString("FlightNumber"));
                    flight.setDepartureAirport(resultSet.getString("DepartureAirport"));
                    flight.setDestinationAirport(resultSet.getString("DestinationAirport"));
                    flight.setPrice(resultSet.getDouble("Price"));
                    flight.setDateTime(resultSet.getDate("DateTime"));
                    flight.setPlane(resultSet.getString("Plane"));
                    flight.setSeatsTaken(resultSet.getInt("SeatsTaken"));
                    flightArray.add(flight);
                }
            
            }
            catch(SQLException sqle)
            {
                System.out.println(sqle.toString());
            } 
       return flightArray;
    }
    
    //this method obtains all the information from the Flight table based on the departure and destination airports as well as travel date
    public static ArrayList<Flight> GetAllFlightsForLocation(String dep, String dest, String date){
       
       ArrayList<Flight> flightArray = new ArrayList<>();
       Flight flight;
        try (Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM flight WHERE (DepartureAirport = '" + dep + "' AND DestinationAirport = '" + dest + "' AND DateTime = '" + date + "')"))
        {
            
            while (resultSet.next()) 
            {
            	flight = new Flight();
                flight.setFlightNumber(resultSet.getString("FlightNumber"));
            	flight.setDepartureAirport(resultSet.getString("DepartureAirport"));
                flight.setDestinationAirport(resultSet.getString("DestinationAirport"));
                flight.setPrice(resultSet.getDouble("Price"));
                flight.setDateTime(resultSet.getDate("DateTime"));
                flight.setPlane(resultSet.getString("Plane"));
                flight.setSeatsTaken(resultSet.getInt("SeatsTaken"));
                flightArray.add(flight);

            }
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
        }  
        return flightArray;
  
    }
    
    
    
    //this method obtains the flight based on the flightNumber parameter
    public static Flight getFlightByFlightNumber(String flightNumber){
        Flight flight = new Flight();
        try (Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM flight WHERE FlightNumber = '"+ flightNumber +"'"))
        {
            if (!resultSet.next() ) 
            {
                System.out.println("\nNo data");
                flight = null;
                return flight;
                
            }
            else
            {
                while (resultSet.next()) 
                {   
                    flight.setFlightNumber(resultSet.getString("FlightNumber"));
                    flight.setDepartureAirport(resultSet.getString("DepartureAirport"));
                    flight.setDestinationAirport(resultSet.getString("DestinationAirport"));
                    flight.setPrice(resultSet.getDouble("Price"));
                    flight.setDateTime(resultSet.getDate("DateTime"));
                    flight.setPlane(resultSet.getString("Plane"));
                    flight.setSeatsTaken(resultSet.getInt("SeatsTaken"));
                }
            }
            
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
        }  
        return flight;
    }
    
     //this method obtains the flight based on the flightNumber parameter
   public static Location getLocationByAirportCode(String airportCode){
       Location location = new Location();
	try (Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM location WHERE AirportCode = '" + airportCode + "'"))
        {
            if (!resultSet.next() ) 
            {
                
                location = null;
                return location;
                
            }
            else
            {
                 while (resultSet.next()) 
                {	
                    location.setCity(resultSet.getString("City"));
                    location.setAirportCode(resultSet.getString("AirportCode"));
                }
            }
           
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());   
        }  
       return location;
    }
    
    //this method adds location passed as a parameter to the Location table in the uFly database
    public static void AddLocation(Location location){
        
	try (Connection connection = DbConnector.connectToDb();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement.executeQuery("SELECT * FROM location"))
        {
            
            resultSet.moveToInsertRow();
            resultSet.updateString("City", location.getCity());
            resultSet.updateString("AirportCode", location.getAirportCode());
            resultSet.insertRow();
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
        } 
    }
    
    //this method adds booking passed as a parameter to the Booking table in the uFly database
    //note that Booking number is set as an incrementing field, so it doesn't need to be set
    public static void AddBooking(Booking booking){
        try (Connection connection = DbConnector.connectToDb();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement.executeQuery("SELECT * FROM booking"))
        {
            
            resultSet.moveToInsertRow();
            resultSet.updateInt("BookingNumber", booking.getBookingNumber());
            resultSet.updateString("FlightNumber", booking.getFlightNumber());
            resultSet.updateString("CabinClass", booking.getCabinClass());
            resultSet.updateDouble("Price", booking.getPrice());
            resultSet.updateInt("Quantity",booking.getQuantity());
            resultSet.updateBoolean("Insurance", booking.isInsurance());
            resultSet.insertRow();
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
        } 
    
    }
    //this method adds a flight passed as a parameter to the Flight table in the uFly database
    public static void AddFlight(Flight flight){
        
        try (Connection connection = DbConnector.connectToDb();
             
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement.executeQuery("SELECT * FROM flight"))
        {
            
            resultSet.moveToInsertRow();
            resultSet.updateString("FlightNumber", flight.getFlightNumber());
            resultSet.updateString("DepartureAirport", flight.getDepartureAirport());
            resultSet.updateString("DestinationAirport", flight.getDestinationAirport());
            resultSet.updateDouble("Price", flight.getPrice());
            resultSet.updateDate("DateTime",new java.sql.Date(flight.getDateTime().getTime()));
            resultSet.updateString("Plane", flight.getPlane());
            resultSet.updateInt("SeatsTaken",flight.getSeatsTaken());
            resultSet.insertRow();
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
        } 
    }

    
    //this method updates the location to the one passed to it as a parameter where the airport codes are matching
    public static void UpdateLocation(Location location){
        try (Connection connection = DbConnector.connectToDb();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement.executeQuery("SELECT * FROM location WHERE City = '" + location.getCity() + "'"))
        {
            
            resultSet.absolute(1);
            resultSet.updateString("AirportCode", location.getAirportCode());
            resultSet.updateRow();
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
        } 
    }
    
    //this method updates the flight to the one passed to it as a parameter where the flight numbers are matching
    public static void UpdateFlight(Flight flight){
        try (Connection connection = DbConnector.connectToDb();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement.executeQuery("SELECT * FROM flight WHERE FlightNumber = '" + flight.getFlightNumber() + "'"))
        {
            
            resultSet.absolute(1);
            resultSet.updateString("DepartureAirport", flight.getDepartureAirport());
            resultSet.updateString("DestinationAirport", flight.getDestinationAirport());
            resultSet.updateDouble("Price", flight.getPrice());
            resultSet.updateDate("DateTime", new java.sql.Date(flight.getDateTime().getTime()));
            resultSet.updateString("Plane", flight.getPlane());
            resultSet.updateInt("SeatsTaken",flight.getSeatsTaken());
            resultSet.updateRow();
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
        } 
    }
    
    public static Flight getSeatsTakenByFlightNumber(String flightNumber){
        Flight flight = new Flight();
        try (Connection connection = DbConnector.connectToDb();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM flight WHERE FlightNumber = '"+ flightNumber +"'"))
        {
           
           
                while (resultSet.next()) 
                {   
                    flight.setFlightNumber(resultSet.getString("FlightNumber"));
                    flight.setDepartureAirport(resultSet.getString("DepartureAirport"));
                    flight.setDestinationAirport(resultSet.getString("DestinationAirport"));
                    flight.setPrice(resultSet.getDouble("Price"));
                    flight.setDateTime(resultSet.getDate("DateTime"));
                    flight.setPlane(resultSet.getString("Plane"));
                    flight.setSeatsTaken(resultSet.getInt("SeatsTaken"));
                }
            
            
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle.toString());
        }  
        return flight;
    }
}
