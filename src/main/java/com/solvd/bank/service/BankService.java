package com.solvd.bank.service;

import com.solvd.bank.dao.IAddress;
import com.solvd.bank.dao.IBank;
import com.solvd.bank.dao.IBranch;
import com.solvd.bank.dao.ICustomer;
import com.solvd.bank.dao.dao_jdbc.AddressDAO;
import com.solvd.bank.dao.dao_jdbc.BankDAO;
import com.solvd.bank.dao.dao_jdbc.BranchDAO;
import com.solvd.bank.dao.dao_jdbc.CustomerDAO;
import com.solvd.bank.entity.branch_with_fixed_relationship.Bank;

public class BankService {

    private IBank bankDAO = new BankDAO();
    private IBranch branchDAO = new BranchDAO();
    private IAddress addressDAO = new AddressDAO();
    private ICustomer customerDAO = new CustomerDAO();

    public Bank getBank(Long id){

        Bank bank = bankDAO.get(id);
        bank.setAddress(addressDAO.get(id));
        bank.setBranches(branchDAO.getAllForOneBank(id));
        bank.setCustomers(customerDAO.getAllByIdBank(id));

        return bank;

    }


}
