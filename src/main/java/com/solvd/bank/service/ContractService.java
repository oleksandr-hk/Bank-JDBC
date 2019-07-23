package com.solvd.jdbc.service;

import com.solvd.jdbc.dao_jdbc.ContractDAO;
import com.solvd.jdbc.entity.Contract;
import com.solvd.jdbc.interfaces.IContract;


public class ContractService {

   private IContract contractR = new ContractDAO();


    public void signContract(Contract contract){
        contractR.save(contract);
    }


}
