package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.IAccount;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.Account;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountDAO implements IAccount {

    private final String getById =  "SELECT * FROM accounts WHERE id = ?";
    private final String getAll  =  "SELECT * FROM accounts";
    private final String save    =  "INSERT INTO accounts VALUES (?,?,?,?)";
    private final String delete  =  "DELETE FROM accounts WHERE id =?";
    private final String update  =  "UPDATE accounts SET balance = ?, Cards_idCard = ?,currencies_id_currencies = ?, customer_id_customer = ? WHERE id =?";

    final String findBiggestAccount    = "SELECT * FROM accounts WHERE balance = (SELECT MAX(balance) FROM accounts)";
    final String findAvgFromAllAccount = "SELECT AVG(balance) FROM accounts";


    @Override
    public Account get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getLong("id"));
                account.setBalance(rs.getDouble("balance"));
                account.setIdCard(rs.getLong("Cards_idCard"));
                account.setIdCurrency(rs.getLong("currencies_id_currencies"));
                account.setIdCustomer(rs.getLong("customer_id_customer"));
                return account;
            }
        } catch (SQLException ex) {

        } finally {

                ConnectionPoolQueue.getInstance().releaseConnection(connection);

        }
        return null;
    }

    @Override
    public List<Account> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<Account> accounts = new ArrayList<>();

            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getLong("id"));
                account.setBalance(rs.getDouble("balance"));
                account.setIdCard(rs.getLong("Cards_idCard"));
                account.setIdCurrency(rs.getLong("currencies_id_currencies"));
                account.setIdCustomer(rs.getLong("customer_id_customer"));
                accounts.add(account);

            }
            return accounts;
        } catch (SQLException ex) {

        } finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);

        }
        return null;


    }

    @Override
    public Account save(Account account) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();


        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setLong(2, account.getIdCard());
            preparedStatement.setLong(3, account.getIdCurrency());
            preparedStatement.setLong(4, account.getIdCustomer());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        } finally {
                ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

        return account;

    }

    @Override
    public void delete(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate(delete);

        } catch (SQLException ex) {

        } finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

    }

    @Override
    public void update(Account account) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setLong(2, account.getIdCard());
            preparedStatement.setLong(3, account.getIdCurrency());
            preparedStatement.setLong(4, account.getIdCustomer());
            preparedStatement.setLong(5, account.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        } finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public Account biggestAccount() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findBiggestAccount);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                Account account = new Account();
                account.setId(rs.getLong("id"));
                account.setBalance(rs.getDouble("balance"));
                account.setIdCard(rs.getLong("Cards_idCard"));
                account.setIdCurrency(rs.getLong("currencies_id_currencies"));
                account.setIdCustomer(rs.getLong("customer_id_customer"));

                return account;
            }
        } catch (SQLException ex) {

        } finally {

                ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    public Double avgBalance(){

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findAvgFromAllAccount);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                return rs.getDouble(1);
            }
        } catch (SQLException ex) {

        } finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

}

