package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.ICurrency;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDAO implements ICurrency {

    private final String getById = "SELECT * FROM currencies WHERE id = ?";
    private final String getAll  = "SELECT * FROM currencies";
    private final String save    = "INSERT INTO currencies VALUES (?)";
    private final String delete  = "DELETE FROM currencies WHERE id =?";
    private final String update  = "UPDATE currencies SET currency_name = ? WHERE id =?";

    @Override
    public Currency get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                Currency currency = new Currency();
                currency.setId(rs.getLong("id") );
                currency.setName( rs.getString("currency_name"));
                return currency;
            }
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Currency> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<Currency> currencies = new ArrayList<>();

            while(rs.next())
            {
                Currency currency = new Currency();
                currency.setId(rs.getLong("id") );
                currency.setName( rs.getString("currency_name"));
                currencies.add(currency);

            }
            return currencies;
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;

    }

    @Override
    public Currency save(Currency currency) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,currency.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

        return currency;
    }

    @Override
    public void delete(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate(delete);

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void update(Currency adr) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,adr.getName());
            preparedStatement.setLong(2,adr.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
    }
}
