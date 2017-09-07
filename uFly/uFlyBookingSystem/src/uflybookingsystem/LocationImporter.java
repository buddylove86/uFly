/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import uflybookingsystem.BusinessObjects.Location;



public class LocationImporter extends BaseImporter {
    public LocationImporter(String fileName)
    {
        super(fileName);
    }
   
    public void run()
    {        
        
        String fileData = "";
        int lineNumber;
        int ch;
        super.results = new ImportResult();
        String errorMessage= "";
        String city = "";
        String airport = "";
        String lines[];
       
            try(FileReader fr = new FileReader(fileName))
            {
                while((ch = fr.read())!=-1) 
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
                
                if(columns.length == 2)
                {
                    if(columns[0].toLowerCase().endsWith("city")||(columns[1].toLowerCase().endsWith("code")))
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
                    try
                    {
                            if(s.equals(""))
                            {
                                continue;
                            }
                            
                            row++;
                            super.results.setTotalRows(row);
                            columns = s.trim().split(",");
                        
                            if(columns.length !=2)
                            {
                                errorMessage = "error: must be two columns ";
                                results.setFailedRows(row);
                                results.getErrorMessages().add(errorMessage +  "for line number: " + lineNum);
                                continue;
                            }
                    
                            city = columns[0];
                            airport = columns[1];
                           
                            if(city.equals(""))
                            {
                                errorMessage = "error: city element is empty ";
                                results.setFailedRows(row);
                                results.getErrorMessages().add(errorMessage +  "for line number: " + lineNum); 
                                continue;
                            }
                            if(airport.equals(""))
                            {
                                errorMessage = "error: airport code element is empty ";
                                results.setFailedRows(row);
                                results.getErrorMessages().add(errorMessage +  "for line number: " + lineNum); 
                                continue;
                            }
                            if(!Pattern.matches("[a-zA-Z]{3}", airport))
                            {
                                errorMessage = "error: airport code element is not in correct format of 3 letters ";
                                results.setFailedRows(row);
                                results.getErrorMessages().add(errorMessage +  "for line number: " + lineNum); 
                                continue;
                            }
                            System.out.print("Airport code is: " + airport);
                            System.out.print("\nCity is: " + city);
                            
                            
                            
                            Location locationToUpdate =  new Location();
                            locationToUpdate = DatabaseOperations.getLocationByAirportCode(airport);
                            
                                
                                        if(locationToUpdate == null)
                                            
                                        {
                                            Location locationToAdd = new Location(); 
                                            locationToAdd.setAirportCode(airport);
                                            locationToAdd.setCity(city);
                                            DatabaseOperations.AddLocation(locationToAdd);
                                            BookingForm.locationList.add(locationToAdd);
                                        }
                                        else
                                        {
                                            
                                            locationToUpdate.setCity(city);
                                            locationToUpdate.setAirportCode(airport);
                                            DatabaseOperations.UpdateLocation(locationToUpdate);
                                        }
                            results.setImportedRows(row);
                            
                            
                            
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

    
