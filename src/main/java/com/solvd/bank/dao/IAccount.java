package com.solvd.bank.dao;

import com.solvd.bank.entity.Account;
import java.util.List;


public interface IAccount  {

    Account get(Long id);

    List<Account> getAll();

    Account save(Account account);

    void delete(Long id);

    void update(Account account);

    Account biggestAccount();

    Double avgBalance();

}
