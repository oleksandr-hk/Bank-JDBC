package com.solvd.bank.dao;

import com.solvd.bank.entity.Department;
import java.util.List;

public interface IDepartment {

    Department get(Long id);

    List<Department> getAll();

    Department save(Department t);

    void delete(Long id);

    void update(Department t);


}
