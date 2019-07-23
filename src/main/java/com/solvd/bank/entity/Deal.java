package com.solvd.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Deal {

    private Long id;
    private Date date;
    private Long idEmployee;
    private Long idService;
    private Long idCustomer;
    private Long idTransaction;
}
