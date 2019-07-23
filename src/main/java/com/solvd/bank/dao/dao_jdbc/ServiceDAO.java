package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.IService;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO implements IService {

    private final String getById = "SELECT * FROM services WHERE id = ?";
    private final String getAll  = "SELECT * FROM services";
    private final String save    = "INSERT INTO services VALUES (?,?)";
    private final String delete  = "DELETE FROM services WHERE id =?";
    private final String update  = "UPDATE services SET name = ?, description = ? WHERE id =?";


    @Override
    public Service get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                Service service = new Service();
                service.setId(rs.getLong("id") );
                service.setName( rs.getString("name"));
                service.setDescription(rs.getString("description"));

                return service;
            }
        } catch (SQLException ex) {

        }
        finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Service> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<Service> services = new ArrayList<>();

            while(rs.next())
            {
                Service service = new Service();
                service.setId(rs.getLong("id") );
                service.setName( rs.getString("name"));
                service.setDescription(rs.getString("description"));
                services.add(service);

            }
            return services;

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;

    }

    @Override
    public Service save(Service service) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,service.getName());
            preparedStatement.setString(2, service.getDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

        return service;

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
    public void update(Service service) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,service.getName());
            preparedStatement.setString(2,service.getDescription());
            preparedStatement.setLong(3,service.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
    }
}
