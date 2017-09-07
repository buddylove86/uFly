/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import uflybookingsystem.Enumerations.Plane;
import uflybookingsystem.BusinessObjects.Flight;


/**
 *
 * @author 91028358
 */
public class FlightImporter extends BaseImporter{
    
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    public FlightImporter(String fileName)
    {
        super(fileName);
    }
    
    public void run()
    {
        String fileData = "";
        int lineNumber;
        int ch;
        String lines[];
        
        super.results = new ImportResult();
        String errorMessage = "";
        String flightNumber = "";
        String departureAirport = "";
        String destinationAirport = "";
        double price = 0;
        int seatsTaken = 0;
        Date dateTime;
        String plane = "";
       
        try(FileReader fr = new FileReader(fileName))
        {
            while((ch = fr.read())!= -1) 
            {
                fileData += String.valueOf((char)ch);
            }
        }
        
        catch(FileNotFoundException fnfe)
        {   
            errorMessage = fnfe.getMessage();
        }
        catch(IOException ioe)
        {
            errorMessage = ioe.getMessage();
        }
        catch(Exception e)
        {
            errorMessage = e.getMessage();
        }  
                
        lines = fileData.replace("\r\n","\n").replace("\r","\n").split("\n");
        System.out.println(fileData);
        String firstLine = lines[0];
        String columns[] = firstLine.trim().split(",");
                
        if(columns.length == 7)
            {
                if(columns[0].toLowerCase().endsWith("number")||(columns[1].toLowerCase().endsWith("airport")))
                   {
                        int arraySize = lines.length;
                        String lines2[] = new String[arraySize-1];
                        System.arraycopy(lines, 1, lines2, 0, arraySize-1);
                        lines = lines2;
                    }
            }
            
            int lineNum =1;
            int row = 0;
            for(String s: lines)
                {
                    row ++;
                    try
                    {
                            if(s.equals(""))
                            {
                                continue;
                            }
                            
                            super.results.setTotalRows(row);
                            columns = s.trim().split(",");
                            
                            if(columns.length !=7)
                            {
                                errorMessage = "error: must be seven columns ";
                                results.setFailedRows(row);
                                results.getErrorMessages().add(errorMessage +  "for line number: " + lineNum);
                                continue;
                            }
                            flightNumber = columns[0];
                            departureAirport = columns[1];
                            destinationAirport = columns[2];
                            
                            
                            if((flightNumber.equals(""))||(departureAirport.equals(""))||(destinationAirport.equals("")))
                            {
                                errorMessage = "error: flightNumber OR airport elements are empty ";
                                results.setFailedRows(row);
                                results.getErrorMessages().add(errorMessage +  "for line number: " + lineNum); 
                                continue;
                            }
                            if(!Pattern.matches("\\w{2}\\d{3}", flightNumber))
                            {
                                errorMessage = "error: flight number element is not in correct format of 2 letters followed by 3 numbers ";
                                results.setFailedRows(row);
                                results.getErrorMessages().add(errorMessage +  "for line number: " + lineNum); 
                                continue;
                            }
                            if((!Pattern.matches("[a-zA-Z]{3}", departureAirport))||(!Pattern.matches("[a-zA-Z]{3}", destinationAirport)))
                            {
                                errorMessage = "error: airport codes are not in correct format of 3 letters ";
                                results.setFailedRows(row);
                                results.getErrorMessages().add(errorMessage +  "for line number: " + lineNum); 
                                continue;
                            }
                            System.out.print("\nFly No is: " + flightNumber);
                            System.out.print("\nDEP. is: " + departureAirport);
                            System.out.print("\nDEST.  is: " + destinationAirport);
                                    
                            price  = Double.valueOf(columns[3]);
                            System.out.print("\nPrice  is: " + price);
                            System.out.print("\nprice is: " + columns[3]);
                            
                            seatsTaken =  Integer.valueOf(columns[6]);
                            System.out.print("\nseats are: " + seatsTaken);
                            System.out.print("\nSeats Taken is: " + columns[6]);
                            
                            dateTime = formatter.parse(columns[4]);
                            System.out.print("\ndate is: " + dateTime);
                            System.out.print("\ndate is: "+ columns[4]);
                            
                            plane = columns[5];
                            System.out.print("\nplane is: "+ plane);
                            System.out.print("\nplane is: "+ columns[5]);
                            
                            
                            Plane planeType;
                            planeType =  Plane.BOEING747;
                            
                                for(Plane p:Plane.values())
                                {
                                    if(plane.equals(Plane.values().toString()))
                                    {
                                        planeType = Plane.valueOf(plane);
                                    }
                                    else
                                    {
                                        errorMessage = "error: plane type does not match valid enum types ";
                                        results.setFailedRows(row);
                                        results.getErrorMessages().add(errorMessage +  "for line number: " + lineNum); 
                                        continue;
                                    }
                                
                                }
                            
                            
                            if(seatsTaken > planeType.getPassengerCapacity())
                            {
                                errorMessage = "error: seats booked exceeds plane types capacity ";
                                results.setFailedRows(row);
                                results.getErrorMessages().add(errorMessage +  "for line number: " + lineNum); 
                                continue;
                            }
                            
                            Flight flightToUpdate = new Flight();
                            Flight flightToAdd = new Flight(); 
                            flightToUpdate = DatabaseOperations.getFlightByFlightNumber(flightNumber);
                            
                                
                                        if(flightToUpdate == null)
                                        {
                                            
                                            flightToAdd.setFlightNumber(flightNumber);
                                            flightToAdd.setDepartureAirport(departureAirport);
                                            flightToAdd.setDestinationAirport(destinationAirport);
                                            flightToAdd.setPrice(price);
                                            flightToAdd.setPlane(plane);
                                            flightToAdd.setDateTime(dateTime);
                                            flightToAdd.setSeatsTaken(seatsTaken);
                                            DatabaseOperations.AddFlight(flightToAdd);
                                            BookingForm.flightList.add(flightToAdd);
                                        }
                                        else
                                        {
                                            flightToUpdate.setFlightNumber(flightNumber);
                                            flightToUpdate.setDepartureAirport(departureAirport);
                                            flightToUpdate.setDestinationAirport(destinationAirport);
                                            flightToUpdate.setDateTime(dateTime);
                                            flightToUpdate.setPlane(plane);
                                            flightToUpdate.setPrice(price);
                                            flightToUpdate.setSeatsTaken(seatsTaken);
                                            DatabaseOperations.UpdateFlight(flightToUpdate);
                                        }
                            
                            super.results.setImportedRows(5);
                            
                            
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                            lineNum++;
                            super.setResults(results); 
                    }
                }        
        }
        
    }
