package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.IAddress;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.branch_with_fixed_relationship.Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AddressDAO  implements IAddress {

    private final String getById = "SELECT * FROM addresses WHERE id = ?";
    private final String getAll  = "SELECT * FROM addresses";
    private final String getAllAddressForCity = "SELECT * FROM addresses WHERE Cities_id = ?";
    private final String save    = "INSERT INTO addresses VALUES (?,?,?)";
    private final String delete  = "DELETE FROM addresses WHERE id =?";
    private final String update  = "UPDATE addresses SET address = ?, adress_postalcode = ?, Cities_id = ? WHERE id =?";

    @Override
    public Address get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                Address address = new Address();
                address.setId(rs.getLong("id") );
                address.setAddress( rs.getString("address"));
                address.setAddressPostalCode(rs.getString("adress_postalcode"));
                address.getCity().setId(rs.getLong("Cities_id"));
                return address;
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
    public List<Address> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<Address> addresses = new ArrayList<>();

            while(rs.next())
            {
                Address address = new Address();
                address.setId(rs.getLong("id") );
                address.setAddress( rs.getString("address"));
                address.setAddressPostalCode(rs.getString("adress_postalcode"));
                address.getCity().setId(rs.getLong("Cities_id"));
                addresses.add(address);

            }
            return addresses;
        } catch (SQLException ex) {

        }
        finally {

                ConnectionPoolQueue.getInstance().releaseConnection(connection);
            }

        return null;


    }

    @Override
    public List<Address> getAllForCity(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAllAddressForCity);
            ResultSet rs = preparedStatement.executeQuery();
            List<Address> addresses = new ArrayList<>();

            while(rs.next())
            {
                Address address = new Address();
                address.setId(rs.getLong("id") );
                address.setAddress( rs.getString("address"));
                address.setAddressPostalCode(rs.getString("adress_postalcode"));
                address.getCity().setId(rs.getLong("Cities_id"));
                addresses.add(address);

            }
            return addresses;
        } catch (SQLException ex) {

        }
        finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

        return null;
    }

    @Override
    public Address save(Address address) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,address.getAddress());
            preparedStatement.setString(2,address.getAddressPostalCode());
            preparedStatement.setLong(3,address.getCity().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);

        }

        return address;
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
    public void update(Address adr) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,adr.getAddress());
            preparedStatement.setString(2,adr.getAddressPostalCode());
            preparedStatement.setLong(3,adr.getCity().getId());
            preparedStatement.setLong(4,adr.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }



    }
}
