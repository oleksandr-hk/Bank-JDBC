package com.solvd.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Transaction {

    private Long id;
    private Date date;
    private String description;
    private Double amountOfMoney;
    private Long idCurrency;
    private Long idAccount;
}
