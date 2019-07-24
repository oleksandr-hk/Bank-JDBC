package com.solvd.bank.dao;

import com.solvd.bank.entity.branch_with_fixed_relationship.City;
import java.util.List;

public interface ICity {

    City get(Long id);

    List<City> getAll();

    City save(City t);

    void delete(Long id);

    void update(City t);
}
