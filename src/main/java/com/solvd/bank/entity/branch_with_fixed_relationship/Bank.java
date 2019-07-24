package com.solvd.bank.entity.branch_with_fixed_relationship;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Bank {

    private Long id;
    private String name;
    private Long  license;
    private Address address;

    private List<Customer> customers;
    private List<Branch> branches;

}
