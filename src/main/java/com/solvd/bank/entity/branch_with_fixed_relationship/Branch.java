package com.solvd.bank.entity.branch_with_fixed_relationship;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Branch {

    private Long id;
    private String name;
    private Bank bank;
    private Address address;

}
