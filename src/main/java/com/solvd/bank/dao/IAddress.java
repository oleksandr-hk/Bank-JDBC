package com.solvd.bank.dao;

import com.solvd.bank.entity.Address;
import java.util.List;

public interface IAddress {

    Address get(Long id);

    List<Address> getAll();

    Address save(Address address);

    void delete(Long id);

    void update(Address address);
}
