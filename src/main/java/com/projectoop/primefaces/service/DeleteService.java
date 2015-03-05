/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectoop.primefaces.service;

import com.projectoop.primefaces.connectdb.DBConfig;
import com.projectoop.primefaces.model.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Santi
 */
public class DeleteService {
    
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUsername(),
                DBConfig.getPassword()
        );
    }
    
    public void deleteRow(Employee employee) throws ClassNotFoundException, SQLException{
        
        System.out.println("Employee_id : "+employee.getId());
        
        Class.forName(DBConfig.getDriver());
        
        Connection connection = null;
        Statement statement = null;
        
        try{
            
            connection = getConnection();

            String sql = "DELETE FROM Employees WHERE employee_id = ?";
                        
            
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, employee.getId());
            prep.executeUpdate();
            System.out.println("Record Delete Successfully");
            
        }finally{    
            if (connection != null) {
                connection.close();
            }
        }
    }
}
