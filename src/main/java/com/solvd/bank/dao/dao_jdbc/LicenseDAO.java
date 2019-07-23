package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.ILicense;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.License;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LicenseDAO implements ILicense {

    private final String getById = "SELECT * FROM licensies WHERE id = ?";
    private final String getAll  = "SELECT * FROM licensies";
    private final String save    = "INSERT INTO licensies VALUES (?,?,?)";
    private final String delete  = "DELETE FROM licensies WHERE id =?";
    private final String update  = "UPDATE licensies SET name_license = ?, description_license = ? , banks_id_bank = ?  WHERE id =?";



    @Override
    public License get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                License license = new License();
                license.setId(rs.getLong("id") );
                license.setName(rs.getString("name_license"));
                license.setDescription(rs.getString("description_license"));
                license.setIdBank(rs.getLong("banks_id_bank"));

                return license;
            }
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<License> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<License> licensies = new ArrayList<>();

            while(rs.next())
            {
                License license = new License();
                license.setId(rs.getLong("id") );
                license.setName( rs.getString("name_license"));
                license.setDescription(rs.getString("description_license"));
                license.setIdBank(rs.getLong("banks_id_bank"));
                licensies.add(license);

            }

            return licensies;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public License save(License license) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,license.getName());
            preparedStatement.setString(2,license.getDescription());
            preparedStatement.setLong (3,license.getIdBank());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

        return license;
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
    public void update(License license) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,license.getName());
            preparedStatement.setString(2,license.getName());
            preparedStatement.setLong(3,license.getIdBank());
            preparedStatement.setLong(4,license.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
    }
}
