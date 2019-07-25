package com.solvd.bank.service;

import com.solvd.bank.dao.IAddress;
import com.solvd.bank.dao.ICity;
import com.solvd.bank.dao.ICountry;
import com.solvd.bank.dao.dao_jdbc.AddressDAO;
import com.solvd.bank.dao.dao_jdbc.CityDAO;
import com.solvd.bank.dao.dao_jdbc.CountryDAO;
import com.solvd.bank.entity.branch_with_fixed_relationship.City;

public class CityService {

    private ICountry countryDAO = new CountryDAO();
    private ICity cityDAO = new CityDAO();
    private IAddress addressDAO = new AddressDAO();

    public City getCity(Long id){

        City city = new City();
        city = cityDAO.get(id);
        city.setCountry(countryDAO.get(city.getCountry().getId()));
        city.setAddressList(addressDAO.getAllForCity(id));

        return city;

    }

    public City save (City city){

        return cityDAO.save(city);
    }

    public void remove(Long id){

        cityDAO.delete(id);
    }



}
