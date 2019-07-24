package com.solvd.bank.dao;

import com.solvd.bank.entity.branch_with_fixed_relationship.Country;
import java.util.List;

public interface ICountry {

    Country get(Long id);

    List<Country> getAll();

    Country save(Country t);

    void delete(Long id);

    void update(Country country);
}
