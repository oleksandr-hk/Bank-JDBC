package com.solvd.bank.entity.branch_with_fixed_relationship;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Country {

    private Long id;
    private String name;
    private List<City> cityList;

}
