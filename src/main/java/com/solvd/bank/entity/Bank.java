package com.solvd.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Bank {

    private Long id;
    private String name;
    private Long idLicense;
    private Long idAddress;

}
