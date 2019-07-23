package com.solvd.bank.dao;

import com.solvd.bank.entity.Contract;

import java.util.List;

public interface IContract {

    Contract get(Long id);

    List<Contract> getAll();

    Contract save(Contract t);

    void delete(Long id);

    void update(Contract t);
}
