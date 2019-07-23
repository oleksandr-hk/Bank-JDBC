package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.ICustomerType;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.CustomerType;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerTypeDAO implements ICustomerType {

    private final String getById = "SELECT * FROM customer_types WHERE id = ?";
    private final String getAll  = "SELECT * customer_types cities";
    private final String save    = "INSERT INTO customer_types VALUES (?)";
    private final String delete  = "DELETE FROM customer_types WHERE id =?";
    private final String update  = "UPDATE customer_types SET customer_type = ? WHERE id =?";

    @Override
    public CustomerType get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                CustomerType customerType = new CustomerType();
                customerType.setId(rs.getLong("id") );
                customerType.setCustomerType(rs.getString("customer_type"));

                return customerType;
            }
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<CustomerType> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<CustomerType> customerTypes  = new ArrayList<>();

            while(rs.next())
            {
                CustomerType customerType = new CustomerType();
                customerType.setId(rs.getLong("id") );
                customerType.setCustomerType(rs.getString("customer_type"));
                customerTypes.add(customerType);

            }
            return customerTypes;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public CustomerType save(CustomerType customerType) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,customerType.getCustomerType());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return customerType;
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
    public void update(CustomerType customerType) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,customerType.getCustomerType());
            preparedStatement.setLong(1, customerType.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
    }
}
