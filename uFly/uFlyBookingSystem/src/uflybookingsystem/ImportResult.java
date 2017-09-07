/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem;

import java.util.ArrayList;

/**
 *
 * @author 91028358
 */
public class ImportResult {
    private int totalRows, importedRows, failedRows;
    private ArrayList<String>errorMessages = new ArrayList<>();
    
    public ImportResult()
    {
        errorMessages.clear();
        setTotalRows(0);
        setImportedRows(0);
        setFailedRows(0);
    }

    /**
     * @return the totalRows
     */
    public int getTotalRows() {
        return totalRows;
    }

    /**
     * @param totalRows the totalRows to set
     */
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    /**
     * @return the importedRows
     */
    public int getImportedRows() {
        return importedRows;
    }

    /**
     * @param importedRows the importedRows to set
     */
    public void setImportedRows(int importedRows) {
        this.importedRows = importedRows;
    }

    /**
     * @return the failedRows
     */
    public int getFailedRows() {
        return failedRows;
    }

    /**
     * @param failedRows the failedRows to set
     */
    public void setFailedRows(int failedRows) {
        this.failedRows = failedRows;
    }

    /**
     * @return the errorMessages
     */
    public ArrayList<String> getErrorMessages() {
        return errorMessages;
    }
    
}
