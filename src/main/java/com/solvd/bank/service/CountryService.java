package com.solvd.jdbc.service;


import com.solvd.jdbc.dao_jdbc.CountryDAO;
import com.solvd.jdbc.entity.Country;
import com.solvd.jdbc.interfaces.ICountry;

import java.util.List;


public class CountryService {


    private ICountry countryDAO = new CountryDAO();


    public Country getById(Long id) {

        return countryDAO.get(id);
    }

    public List<Country> getAll() {

        return countryDAO.getAll();
    }

    public void  save (Country country) {

        countryDAO.save(country);
    }

    public void deleteById(Long id) {

        countryDAO.delete(id);
    }

}
