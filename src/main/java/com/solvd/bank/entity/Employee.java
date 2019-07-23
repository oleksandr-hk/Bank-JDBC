package com.solvd.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {

    private Long id;
    private String name;
    private String surname;
    private double salary;
    private double rating;
    private Long idDepartment;

}
