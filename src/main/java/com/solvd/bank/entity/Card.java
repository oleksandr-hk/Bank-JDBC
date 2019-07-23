package com.solvd.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Card {

    private Long id;
    private String number;
    private Date expDate;
    private String cvv;
    private Long idCustomer;
    private Long cardType;
    private Long idBank;

}
