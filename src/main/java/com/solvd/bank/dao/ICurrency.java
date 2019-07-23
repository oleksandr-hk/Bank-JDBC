package com.solvd.bank.dao;

import com.solvd.bank.entity.Currency;
import java.util.List;

public interface ICurrency {

    Currency get(Long id);

    List<Currency> getAll();

    Currency save(Currency t);

    void delete(Long id);

    void update(Currency t);
}
