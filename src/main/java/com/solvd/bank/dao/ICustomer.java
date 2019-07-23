package com.solvd.bank.dao;

import com.solvd.bank.entity.Customer;
import java.util.List;

public interface ICustomer {

    Customer get(Long id);

    List<Customer> getAll();

    Customer save(Customer t);

    void delete(Long id);

    void update(Customer t);
}
