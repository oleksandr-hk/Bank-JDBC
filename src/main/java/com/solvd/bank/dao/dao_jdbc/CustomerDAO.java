package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.ICustomer;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.branch_with_fixed_relationship.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomer {

    private final String getById = "SELECT * FROM customers WHERE id = ?";
    private final String getAll  = "SELECT * FROM customers";
    private final String getAllByIdAddr  = "SELECT * FROM customers WHERE Addresses_id = ?";
    private final String getAllByIdBank  = "SELECT * FROM customers WHERE banks_id_bank = ?";
    private final String save    = "INSERT INTO customers VALUES (?,?,?,?,?,?)";
    private final String delete  = "DELETE FROM customers WHERE id =?";
    private final String update  = "UPDATE customers SET customer_name = ?, customer_surname = ?,customer_phone =?, customer_email = ?, banks_id_bank = ?, Addresses_id = ? WHERE id =?";

    @Override
    public Customer get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                Customer customer = new Customer();
                customer.setId(rs.getLong("id") );
                customer.setName( rs.getString("customer_name"));
                customer.setSurname(rs.getString("customer_surname"));
                customer.setPhone(rs.getString("customer_phone"));
                customer.setEmail(rs.getString("customer_email"));
                customer.getBank().setId(rs.getLong("banks_id_bank"));
                customer.getAddress().setId(rs.getLong("Address_id"));

                return customer;
            }
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<Customer> customers = new ArrayList<>();

            while(rs.next())
            {
                Customer customer = new Customer();
                customer.setId(rs.getLong("id") );
                customer.setName( rs.getString("customer_name"));
                customer.setSurname(rs.getString("customer_surname"));
                customer.setPhone(rs.getString("customer_phone"));
                customer.setEmail(rs.getString("customer_email"));
                customer.getBank().setId(rs.getLong("banks_id_bank"));
                customer.getAddress().setId(rs.getLong("Address_id"));
                customers.add(customer);

            }
            return customers;
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Customer> getAllWithId(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAllByIdAddr);
            ResultSet rs = preparedStatement.executeQuery();
            List<Customer> customers = new ArrayList<>();

            while(rs.next())
            {
                Customer customer = new Customer();
                customer.setId(rs.getLong("id") );
                customer.setName( rs.getString("customer_name"));
                customer.setSurname(rs.getString("customer_surname"));
                customer.setPhone(rs.getString("customer_phone"));
                customer.setEmail(rs.getString("customer_email"));
                customer.getBank().setId(rs.getLong("banks_id_bank"));
                customer.getAddress().setId(rs.getLong("Address_id"));
                customers.add(customer);

            }
            return customers;
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Customer> getAllByIdBank(Long id) {
        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAllByIdBank);
            ResultSet rs = preparedStatement.executeQuery();
            List<Customer> customers = new ArrayList<>();

            while(rs.next())
            {
                Customer customer = new Customer();
                customer.setId(rs.getLong("id") );
                customer.setName( rs.getString("customer_name"));
                customer.setSurname(rs.getString("customer_surname"));
                customer.setPhone(rs.getString("customer_phone"));
                customer.setEmail(rs.getString("customer_email"));
                customer.getBank().setId(rs.getLong("banks_id_bank"));
                customer.getAddress().setId(rs.getLong("Address_id"));
                customers.add(customer);

            }
            return customers;
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public Customer save(Customer customer) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getSurname());
            preparedStatement.setString(3,customer.getPhone());
            preparedStatement.setString(4,customer.getEmail());
            preparedStatement.setLong(5,customer.getBank().getId());
            preparedStatement.setLong(6,customer.getAddress().getId());


            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {

        }
        return customer;

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
    public void update(Customer customer) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getSurname());
            preparedStatement.setString(3,customer.getPhone());
            preparedStatement.setString(4,customer.getEmail());
            preparedStatement.setLong(5,customer.getBank().getId());
            preparedStatement.setLong(6,customer.getAddress().getId());
            preparedStatement.setLong(7,customer.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

    }
}
