package com.solvd.bank.entity.branch_with_fixed_relationship;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;

    private Bank bank;
    private Address address;

}
