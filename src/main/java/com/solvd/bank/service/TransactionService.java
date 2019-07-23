package com.solvd.jdbc.service;

import com.solvd.jdbc.dao_jdbc.TransactionDAO;
import com.solvd.jdbc.entity.Transaction;
import com.solvd.jdbc.interfaces.ITransaction;

import java.util.List;

public class TransactionService {

    ITransaction transactionDAO = new TransactionDAO();

    public Transaction getById(Long id) {

        return transactionDAO.get(id);
    }

    public List<Transaction> getAll() {

        return transactionDAO.getAll();
    }

    public void  save (Transaction transaction) {

        transactionDAO.save(transaction);
    }

    public void deleteById(Long id) {

        transactionDAO.delete(id);
    }

    public double findAVGForAllTransaction(){

        return transactionDAO.findAvgTransactionSum();
    }

    public void update (Transaction transaction){

        transactionDAO.update(transaction);
    }

}
