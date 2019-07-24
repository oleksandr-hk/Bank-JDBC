package com.solvd.bank.dao;

import com.solvd.bank.entity.branch_with_fixed_relationship.Branch;
import java.util.List;

public interface IBranch {

    Branch get(Long id);

    Branch getForAddrId(Long id);

    List<Branch> getAllForOneBank(Long id);

    List<Branch> getAll();

    Branch save(Branch t);

    void delete(Long id);

    void update(Branch t);

}
