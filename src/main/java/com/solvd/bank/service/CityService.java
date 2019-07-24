package com.solvd.bank.service;

import com.solvd.bank.dao.IAddress;
import com.solvd.bank.dao.ICity;
import com.solvd.bank.dao.ICountry;
import com.solvd.bank.dao.dao_jdbc.AddressDAO;
import com.solvd.bank.dao.dao_jdbc.CityDAO;
import com.solvd.bank.dao.dao_jdbc.CountryDAO;
import com.solvd.bank.entity.branch_with_fixed_relationship.City;

public class CityService {

    private ICountry iCountry = new CountryDAO();
    private ICity iCity = new CityDAO();
    private IAddress iAddress = new AddressDAO();

    public City getCity(Long id){

        City city = new City();
        city = iCity.get(id);
        city.setCountry(iCountry.get(city.getCountry().getId()));
        city.setAddressList(iAddress.getAllForCity(id));

        return city;

    }

    public City save (City city){

        return iCity.save(city);
    }

    public void remove(Long id){

        iCity.delete(id);
    }



}
