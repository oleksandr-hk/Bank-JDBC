package com.solvd.bank.dao;

import com.solvd.bank.entity.CustomerType;
import java.util.List;

public interface ICustomerType {

    CustomerType get(Long id);

    List<CustomerType> getAll();

    CustomerType save(CustomerType t);

    void delete(Long id);

    void update(CustomerType t);
}
