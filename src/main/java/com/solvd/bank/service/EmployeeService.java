package com.solvd.jdbc.service;

import com.solvd.jdbc.dao_jdbc.EmployeeDAO;
import com.solvd.jdbc.entity.Employee;
import com.solvd.jdbc.interfaces.IEmployee;

import java.util.List;

public class EmployeeService {

    public IEmployee iEmployee = new EmployeeDAO();


    public Employee getById(Long id) {

        return iEmployee.get(id);
    }

    public List<Employee> getBestFromDep(){

        return iEmployee.getBestEmployeeFromDepartment();
    }

    public List<Employee> getAll() {

        return iEmployee.getAll();
    }

    public void  save (Employee employee) {

        iEmployee.save(employee);
    }

    public void deleteById(Long id) {

        iEmployee.delete(id);
    }

    public void update (Employee employee){

        iEmployee.update(employee);
    }

}
