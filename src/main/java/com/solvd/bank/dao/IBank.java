package com.solvd.bank.dao;

import com.solvd.bank.entity.branch_with_fixed_relationship.Bank;


import java.util.List;

public interface IBank  {

    Bank get(Long id);

    List<Bank> getAll();


    Bank save(Bank bank);

    void delete(Long id);

    void update(Bank bank);


}
