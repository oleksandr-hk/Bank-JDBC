package com.solvd.bank.dao;

import com.solvd.bank.entity.branch_with_fixed_relationship.Address;
import java.util.List;

public interface IAddress {

    Address get(Long id);

    List<Address> getAll();

    List<Address> getAllForCity(Long id);

    Address save(Address address);

    void delete(Long id);

    void update(Address address);
}
