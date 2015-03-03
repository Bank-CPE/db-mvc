/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectoop.primefaces.service;


import com.projectoop.primefaces.model.Employee;
import java.util.List;

/**
 *
 * @author anonymous
 */
public interface EmployeeSearchService {

    List<Employee> search(String keyword);
}
