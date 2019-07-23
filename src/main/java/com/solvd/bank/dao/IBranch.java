package com.solvd.bank.dao;

import com.solvd.bank.entity.Branch;
import java.util.List;

public interface IBranch {

    Branch get(Long id);

    List<Branch> getAll();

    Branch save(Branch t);

    void delete(Long id);

    void update(Branch t);

}
