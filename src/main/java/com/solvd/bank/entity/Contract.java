package com.solvd.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Contract {

    private Long id;
    private String description;
    private Date date;
    private Long idCustomer;
    private Long idEmployee;
    private Long idBank;



}
