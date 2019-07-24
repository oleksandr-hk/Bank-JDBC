package com.solvd.bank.entity.branch_with_fixed_relationship;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Address {

    private Long id;
    private String address;
    private String addressPostalCode;
    private City city;

    private List<Customer> customerList;
    private Branch branch;


}
