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
public class InsertService {

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUsername(),
                DBConfig.getPassword()
        );
    }

    public void insertRow(Employee employee) throws ClassNotFoundException, SQLException {

        Integer index = employee.getId();
        employee.setId(index + 1);

        System.out.println("Employee_id : " + employee.getId());
        System.out.println("First Name : " + employee.getFirstName());
        System.out.println("Last Name : " + employee.getLastName());
        System.out.println("Email : " + employee.getEmail());
        System.out.println("Phone Number : " + employee.getPhoneNumber());
        System.out.println("Job ID :" + employee.getJobId());
        System.out.println("Salary : " + employee.getSalary());
        System.out.println("Commission PCT : " + employee.getCommissionPct());
        System.out.println("Manager ID : " + employee.getManagerId());
        System.out.println("Department ID : " + employee.getDepartmentId());

        Class.forName(DBConfig.getDriver());

        Connection connection = null;

        try {

            connection = getConnection();

            String sql = "INSERT INTO Employees"
                    + "(employee_id,first_name,last_name,email,phone_number,hire_date,job_id,salary,commission_pct,manager_id,department_id)"
                    + "VALUES(?,?,?,?,?,SYSDATE,?,?,?,?,?)";

            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, employee.getId());
            prep.setString(2, employee.getFirstName());
            prep.setString(3, employee.getLastName());
            prep.setString(4, employee.getEmail());
            prep.setString(5, employee.getPhoneNumber());
            prep.setString(6, employee.getJobId());
            prep.setDouble(7, employee.getSalary());
            prep.setFloat(8, employee.getCommissionPct());
            prep.setInt(9, employee.getManagerId());
            prep.setInt(10, employee.getDepartmentId());
            prep.executeUpdate();

            System.out.println("Record Inserted Successfully");
  
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }
}
