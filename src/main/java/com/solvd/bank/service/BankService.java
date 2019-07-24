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

    private IBank iBank = new BankDAO();
    private IBranch iBranch = new BranchDAO();
    private IAddress iAddress = new AddressDAO();
    private ICustomer iCustomer = new CustomerDAO();

    public Bank getBank(Long id){

        Bank bank = iBank.get(id);
        bank.setAddress(iAddress.get(id));
        bank.setBranches(iBranch.getAllForOneBank(id));
        bank.setCustomers(iCustomer.getAllByIdBank(id));

        return bank;

    }


    public Bank save(Bank bank){
        return iBank.save(bank);
    }

}
