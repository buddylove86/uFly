/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uflybookingsystem;

import java.sql.DriverManager;
import java.sql.*;

/**
 *
 * @author 91028358
 */
public class DbConnector {
    
    
    public static Connection connectToDb() throws SQLException {
        
        String url = "jdbc:mysql://localhost:3306/ufly";
        String user = "root";
        String password ="password";
        return DriverManager.getConnection(url, user, password);
    }    
}
