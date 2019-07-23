package com.solvd.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Branch {

    private Long id;
    private String name;
    private Long idBank;
    private Long idAddress;

}
