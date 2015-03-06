/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectoop.primefaces.controller;

import com.projectoop.primefaces.model.Employee;
import com.projectoop.primefaces.service.DeleteService;
import com.projectoop.primefaces.service.EmployeeSearchService;
import com.projectoop.primefaces.service.InsertService;
import com.projectoop.primefaces.service.SearchServiceUtils;
import com.projectoop.primefaces.service.UpdateService;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Santi
 */
@ManagedBean
@ViewScoped
public class EmployeeCtrl implements Serializable {

    private List<Employee> employees;
    private String query;
    private String searchBy;
    private Employee employee;

    @PostConstruct
    public void postConstruct() {
        onSearch();
    }

    public void onSearch() {
        EmployeeSearchService service = SearchServiceUtils.findServiceByName(searchBy);
        employees = service.search(query);
    }

    public void onClear() {
        query = null;
        searchBy = null;
        onSearch();
    }

    public List<Employee> getEmployees() {
        if (employees == null) {
            employees = new LinkedList<>();
        }

        return employees;
    }

    public void onInsert() throws ClassNotFoundException, SQLException {
        Employee emp = new Employee();
        emp.setId(employees.get(employees.size() - 1).getId());
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setEmail(employee.getEmail().toUpperCase());
        emp.setPhoneNumber(employee.getPhoneNumber());
        emp.setJobId(employee.getJobId().toUpperCase());
        emp.setSalary(employee.getSalary());
        emp.setCommissionPct(employee.getCommissionPct());
        emp.setManagerId(employee.getManagerId());
        emp.setDepartmentId(employee.getDepartmentId());

        IntoRow(emp);
        notifyMessageInsert(emp);
        onClear();
        onSearch();
        employee = null;

    }

    public void IntoRow(Employee e) throws ClassNotFoundException, SQLException {
        InsertService service = new InsertService();
        service.insertRow(e);
    }

    public void onDelete() throws ClassNotFoundException, SQLException {
        notifyMessageDelete();
        DeleteRow(employee);
        onClear();
        onSearch();

    }

    public void DeleteRow(Employee e) throws ClassNotFoundException, SQLException {
        DeleteService service = new DeleteService();
        service.deleteRow(e);
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public void notifyMessageDelete() {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(
                                FacesMessage.SEVERITY_INFO,
                                "Delete Employee",
                                "success (id " + employee.getId() + ")"
                        ));
    }

    public void notifyMessageInsert(Employee e) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(
                                FacesMessage.SEVERITY_INFO,
                                "Insert Employee",
                                "success (id " + e.getId() + ")"
                        ));
    }

    private Object request(String param) {
        return FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get(param);
    }

    public void onSelect() {
        Integer id = Integer.valueOf((String) request("employeeId"));
        Employee emp = new Employee();
        emp.setId(id);
        int index = getEmployees().indexOf(emp);
        employee = getEmployees().get(index);
    }

    public Employee getEmployee() {
        if (employee == null) {
            employee = new Employee();
        }

        return employee;
    }

    public void onUpdate() throws SQLException {
        updateRow(employee);
    }

    public void updateRow(Employee e) throws SQLException {
        UpdateService service = new UpdateService();
        service.updateRow(e);
    }
}
