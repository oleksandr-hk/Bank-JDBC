package com.solvd.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {

    private Long id;
    private String address;
    private String addressPostalCode;
    private Long cityId;

}
