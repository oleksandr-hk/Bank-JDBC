package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.IContract;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.Contract;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContractDAO implements IContract {

    private final String getById = "SELECT * FROM contracts WHERE id = ?";
    private final String getAll  = "SELECT * FROM contracts";
    private final String save    = "INSERT INTO contracts VALUES (?,?,?,?,?)";
    private final String delete  = "DELETE FROM contracts WHERE id =?";
    private final String update  = "UPDATE contracts SET description = ?, date = ?,banks_id_bank = ?, Employees_idEmployee = ?, Customer_id = ? WHERE id =?";

    @Override
    public Contract get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                Contract contract = new Contract();
                contract.setId(rs.getLong("id") );
                contract.setDescription( rs.getString("description"));
                contract.setDate(rs.getDate("date"));
                contract.setIdBank(rs.getLong("banks_id_bank"));
                contract.setIdEmployee(rs.getLong("Employee_idEmployee"));
                contract.setIdCustomer(rs.getLong("Customer_id"));
                return contract;
            }
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;

    }

    @Override
    public List<Contract> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<Contract> contracts = new ArrayList<>();

            while(rs.next())
            {
                Contract contract = new Contract();
                contract.setId(rs.getLong("id") );
                contract.setDescription( rs.getString("description"));
                contract.setDate(rs.getDate("date"));
                contract.setIdBank(rs.getLong("banks_id_bank"));
                contract.setIdEmployee(rs.getLong("Employee_idEmployee"));
                contract.setIdCustomer(rs.getLong("Customer_id"));
                contracts.add(contract);

            }
            return contracts;
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public Contract save(Contract contract) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,contract.getDescription());
            preparedStatement.setDate(2, (Date) contract.getDate());
            preparedStatement.setLong(3,contract.getIdBank());
            preparedStatement.setLong(4,contract.getIdEmployee());
            preparedStatement.setLong(5,contract.getIdCustomer());


            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

        return contract;

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
    public void update(Contract contract) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,contract.getDescription());
            preparedStatement.setDate(2, (Date) contract.getDate());
            preparedStatement.setLong(3,contract.getIdBank());
            preparedStatement.setLong(4,contract.getIdEmployee());
            preparedStatement.setLong(5,contract.getIdCustomer());
            preparedStatement.setLong(6,contract.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
    }
}
