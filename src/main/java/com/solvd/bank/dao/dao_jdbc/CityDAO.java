package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.ICity;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements ICity {

    private final String getById = "SELECT * FROM cities WHERE id = ?";
    private final String getAll  = "SELECT * FROM cities";
    private final String save    = "INSERT INTO cities VALUES (?,?)";
    private final String delete  = "DELETE FROM cities WHERE id =?";
    private final String update  = "UPDATE cities SET name = ?, Countries_id = ? WHERE id =?";

    @Override
    public City get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                City city = new City();
                city.setId(rs.getLong("id") );
                city.setName( rs.getString("name"));
                city.setCountryId(rs.getLong("Countries_id"));
                return city;
            }
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<City> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<City> cities = new ArrayList<>();

            while(rs.next())
            {
                City city = new City();
                city.setId(rs.getLong("id") );
                city.setName( rs.getString("name"));
                city.setCountryId(rs.getLong("Countries_id"));
                cities.add(city);

            }
            return cities;
        } catch (SQLException ex) {

        }
        finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public City save(City city) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,city.getName());
            preparedStatement.setLong(2,city.getCountryId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return city;

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
    public void update(City city) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,city.getName());
            preparedStatement.setLong(2,city.getCountryId());
            preparedStatement.setLong(3,city.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
    }
}
