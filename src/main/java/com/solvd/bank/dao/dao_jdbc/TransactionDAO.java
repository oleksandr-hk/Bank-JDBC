package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.ITransaction;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO implements ITransaction {

    private final String getById = "SELECT * FROM transactions WHERE id = ?";
    private final String getAll  = "SELECT * transactions cities";
    private final String save    = "INSERT INTO transactions VALUES (?,?,?,?,?)";
    private final String delete  = "DELETE FROM transactions WHERE id =?";
    private final String update  = "UPDATE transactions SET date = ?, description = ?, amount_of_money = ?, currencies_id_currencies = ?, account_id_account = ? WHERE id =?";

    final String findAvgTransactionSum = "SELECT AVG(amount_of_money) from transactions";


    @Override
    public Transaction get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getLong("id") );
                transaction.setDate(rs.getDate("date"));
                transaction.setDescription(rs.getString("description"));
                transaction.setAmountOfMoney(rs.getDouble("amount_of_money"));
                transaction.setIdCurrency(rs.getLong("accountIdAccount"));

                return transaction;
            }
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Transaction> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<Transaction> transactions = new ArrayList<>();

            while(rs.next())
            {
                Transaction transaction = new Transaction();

                transaction.setId(rs.getLong("id") );
                transaction.setDate(rs.getDate("date"));
                transaction.setDescription(rs.getString("description"));
                transaction.setAmountOfMoney(rs.getDouble("amount_of_money"));
                transaction.setIdCurrency(rs.getLong("accountIdAccount"));

                transactions.add(transaction);

            }
            return transactions;
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public Transaction save(Transaction transaction) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setDate(1, (Date) transaction.getDate());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmountOfMoney());
            preparedStatement.setLong(4, transaction.getIdCurrency());
            preparedStatement.setLong(5, transaction.getIdAccount());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

        return transaction;

    }

    @Override
    public void delete(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate(delete);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

    }

    @Override
    public void update(Transaction transaction) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setDate(1, (Date) transaction.getDate());
            preparedStatement.setString(2, transaction.getDescription());
            preparedStatement.setDouble(3, transaction.getAmountOfMoney());
            preparedStatement.setLong(4, transaction.getIdCurrency());
            preparedStatement.setLong(5, transaction.getIdAccount());
            preparedStatement.setLong(6, transaction.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {


        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
    }

    public Double findAvgTransactionSum(){

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findAvgTransactionSum);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                return rs.getDouble(1);
            }
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }
}
