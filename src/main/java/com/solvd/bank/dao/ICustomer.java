package com.solvd.bank.dao;

import com.solvd.bank.entity.branch_with_fixed_relationship.Customer;
import java.util.List;

public interface ICustomer {

    Customer get(Long id);

    List<Customer> getAll();

    List<Customer> getAllWithId(Long id);

    List<Customer> getAllByIdBank(Long id);

    Customer save(Customer t);

    void delete(Long id);

    void update(Customer t);


}
