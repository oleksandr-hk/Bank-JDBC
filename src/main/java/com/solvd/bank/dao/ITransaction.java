package com.solvd.bank.dao;

import com.solvd.bank.entity.Transaction;
import java.util.List;

public interface ITransaction {

    Transaction get(Long id);

    List<Transaction> getAll();

    Transaction save(Transaction t);

    void delete(Long id);

    void update(Transaction t);

    Double findAvgTransactionSum();
}
