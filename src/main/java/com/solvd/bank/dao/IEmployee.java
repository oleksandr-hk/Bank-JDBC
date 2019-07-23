package com.solvd.bank.dao;

import com.solvd.bank.entity.Employee;
import java.util.List;

public interface IEmployee {

    Employee get(Long id);

    List<Employee> getBestEmployeeFromDepartment();

    List<Employee> getAll();

    Employee save(Employee t);

    void delete(Long id);

    void update(Employee t);


}
