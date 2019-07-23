package com.solvd.bank.entity;

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
    private Long idBank;
    private Long idCustomerType;
    private Long idAddress;

}
