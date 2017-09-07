/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem;

/**
 *
 * @author 91028358
 */
public abstract class BaseImporter implements Runnable {
    
    String fileName;
    ImportResult results;
    
    public abstract void run();
    
    public BaseImporter(String fileName)
    {
        this.fileName= fileName;
    }
    
    public void setResults(ImportResult results)
    {
        this.results = results;
    }
    
    public ImportResult getResults()
    {
        return results;
    }
}
