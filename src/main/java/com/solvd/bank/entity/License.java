package com.solvd.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class License {

    private Long id;
    private String name;
    private String description;
    private Long idBank;
}
