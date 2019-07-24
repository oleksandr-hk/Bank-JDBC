package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.ICountry;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.branch_with_fixed_relationship.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO implements ICountry {

    private final String getById = "SELECT * FROM countries WHERE id = ?";
    private final String getAll  = "SELECT * FROM countries ";
    private final String save    = "INSERT INTO countries (country_name) VALUES (?)";
    private final String delete  = "DELETE FROM countries WHERE id = ?";
    private final String update  = "UPDATE countries SET country_name = ? WHERE id =?";

    @Override
    public Country get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                Country country = new Country();
                country.setId(rs.getLong("id") );
                country.setName( rs.getString("country_name") );
                return country;
            }
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;

    }

    @Override
    public List<Country> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<Country> countries = new ArrayList<>();

            while(rs.next())
            {
                Country country = new Country();
                country.setId(rs.getLong("id"));
                country.setName(rs.getString("country_name"));
                countries.add(country);

            }
            return countries;
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public Country save(Country country) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();


        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,country.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

        return country;

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
    public void update(Country country) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,country.getName());
            preparedStatement.setLong(2,country.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }


    }
}
