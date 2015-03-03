/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectoop.primefaces.service;


import com.projectoop.primefaces.connectdb.query.QueryBuilder;
import com.projectoop.primefaces.connectdb.util.SqlUtils;
import com.projectoop.primefaces.model.Employee;
import java.util.List;

/**
 *
 * @author anonymous
 */
public class EmployeeSearchByEmailServiceImpl implements EmployeeSearchService {

    @Override
    public List<Employee> search(String keyword) {
        keyword = SqlUtils.wrapKeywordLike(keyword);
        
        return QueryBuilder.fromSQL("SELECT * FROM Employees WHERE LOWER(email) LIKE ?")
                .addParam(keyword)
                .executeforList(Employee.class);
    }

}
