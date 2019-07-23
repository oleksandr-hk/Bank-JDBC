package com.solvd.jdbc.service;

import com.solvd.jdbc.dao_jdbc.AccountDAO;
import com.solvd.jdbc.entity.Account;
import com.solvd.jdbc.interfaces.IAccount;
import java.util.List;


public class AccountService {

    private IAccount iAccount = new AccountDAO();

    public Account getById(Long id) {

        return iAccount.get(id);
    }

    public List<Account> getAll() {

        return iAccount.getAll();
    }

    public void  save (Account account) {

        iAccount.save(account);
    }

    public void deleteById(Long id) {

        iAccount.delete(id);
    }

    Account biggestAccount(){

       return iAccount.biggestAccount();
    }

    Double avgBalance(){

        return iAccount.avgBalance();
    }




}
