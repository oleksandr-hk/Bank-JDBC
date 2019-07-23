package com.solvd.bank.dao;
import com.solvd.bank.entity.Deal;
import java.util.List;

public interface IDeal {

    Deal get(Long id);

    List<Deal> getAll();

    Deal save(Deal t);

    void delete(Long id);

    void update(Deal t);

}
