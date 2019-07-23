package com.solvd.bank.dao;

import com.solvd.bank.entity.Country;
import java.util.List;

public interface ICountry {

    Country get(Long id);

    List<Country> getAll();

    Country save(Country t);

    void delete(Long id);

    void update(Country country);
}
