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

    private IAddress addressDAO = new AddressDAO();
    private ICity cityDAO =  new CityDAO();
    private ICustomer customerDAO = new CustomerDAO();
    private IBranch branchDAO = new BranchDAO();


    public Address addAddress(Address address, Long cityId){
        address.setCity(cityDAO.get(cityId));
        return addressDAO.save(address);
    }

    public Address getAddress(Long id){

        Address address = addressDAO.get(id);
        address.setCity(cityDAO.get(address.getCity().getId()));
        address.setCustomerList(customerDAO.getAllWithId(id));
        address.setBranch(branchDAO.getForAddrId(id));

        return address;

    }




}
