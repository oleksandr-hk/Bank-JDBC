package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.IBank;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.Bank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankDAO implements IBank {

    private final String getById = "SELECT * FROM banks WHERE id_bank = ?";
    private final String getAll  = "SELECT * FROM banks";
    private final String save    = "INSERT INTO banks VALUES (?,?,?)";
    private final String delete  = "DELETE FROM banks WHERE id_bank =?";
    private final String update  = "UPDATE banks SET bank_name = ?, id_license = ?,Address_id = ? WHERE id_bank =?";


    @Override
    public Bank get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                Bank bank = new Bank();
                bank.setId(rs.getLong("id") );
                bank.setName(rs.getString("bank_name"));
                bank.setIdLicense(rs.getLong("id_license"));
                bank.setIdAddress(rs.getLong("Addresses_id"));
                return bank;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Bank> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<Bank> banks = new ArrayList<>();

            while(rs.next())
            {
                Bank bank = new Bank();
                bank.setId(rs.getLong("id") );
                bank.setName(rs.getString("bank_name"));
                bank.setIdLicense(rs.getLong("id_license"));
                bank.setIdAddress(rs.getLong("Addresses_id"));
                banks.add(bank);

            }
            return banks;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;

    }

    @Override
    public Bank save(Bank bank) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,bank.getName());
            preparedStatement.setLong(2,bank.getIdLicense());
            preparedStatement.setLong(3,bank.getIdAddress());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return bank;
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
    public void update(Bank bank) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,bank.getName());
            preparedStatement.setLong(2,bank.getIdLicense());
            preparedStatement.setLong(3,bank.getIdAddress());
            preparedStatement.setLong(4,bank.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
    }


}
