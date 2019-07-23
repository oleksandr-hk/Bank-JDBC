package com.solvd.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {

    private Long id;
    private double balance;
    private Long idCard;
    private Long idCurrency;
    private Long idCustomer;

}
