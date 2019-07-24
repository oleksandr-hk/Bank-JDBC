package com.solvd.bank.service;

import com.solvd.bank.dao.IAddress;
import com.solvd.bank.dao.IBranch;
import com.solvd.bank.dao.ICity;
import com.solvd.bank.dao.ICustomer;
import com.solvd.bank.dao.dao_jdbc.AddressDAO;
import com.solvd.bank.dao.dao_jdbc.BranchDAO;
import com.solvd.bank.dao.dao_jdbc.CityDAO;
import com.solvd.bank.dao.dao_jdbc.CustomerDAO;
import com.solvd.bank.entity.branch_with_fixed_relationship.Address;



public class AddressService {

    private IAddress iAddress = new AddressDAO();
    private ICity iCity =  new CityDAO();
    private ICustomer iCustomer = new CustomerDAO();
    private IBranch iBranch = new BranchDAO();


    public Address addAddress(Address address, Long cityId){
        address.setCity(iCity.get(cityId));
        return iAddress.save(address);
    }

    public Address getAddress(Long id){

        Address address = iAddress.get(id);
        address.setCity(iCity.get(address.getCity().getId()));
        address.setCustomerList(iCustomer.getAllWithId(id));
        address.setBranch(iBranch.getForAddrId(id));

        return address;

    }




}
