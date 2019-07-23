package com.solvd.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TypeOfCard {

    private Long id;
    private String description;
    private String name;
}
