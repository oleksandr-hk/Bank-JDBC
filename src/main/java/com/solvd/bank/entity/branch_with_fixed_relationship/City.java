package com.solvd.bank.entity.branch_with_fixed_relationship;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class City {

   private Long id;
   private String name;
   private Country country;
   private List<Address> addressList;

}
