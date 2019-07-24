package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.IBranch;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.branch_with_fixed_relationship.Branch;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchDAO implements IBranch {

    private final String getById = "SELECT * FROM branches WHERE id = ?";
    private final String getAll  = "SELECT * FROM branches";
    private final String getAllForAddId  = "SELECT * FROM branches WHERE Addresses_id = ?";
    private final String getAllForBankId = "SELECT * FROM branches WHERE banks_id_bank = ?";
    private final String save    = "INSERT INTO branches VALUES (?,?,?)";
    private final String delete  = "DELETE FROM branches WHERE id =?";
    private final String update  = "UPDATE branches SET branch_name = ?, banks_id_bank = ?,Addresses_id = ? WHERE id =?";

    @Override
    public Branch get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                Branch branch = new Branch();
                branch.setId(rs.getLong("id") );
                branch.setName( rs.getString("branch_name"));
                branch.getBank().setId(rs.getLong("banks_id_bank"));
                branch.getAddress().setId(rs.getLong("Addresses_id"));
                return branch;
            }
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public Branch getForAddrId(Long id) {
        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAllForAddId);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                Branch branch = new Branch();
                branch.setId(rs.getLong("id") );
                branch.setName( rs.getString("branch_name"));
                branch.getBank().setId(rs.getLong("banks_id_bank"));
                branch.getAddress().setId(rs.getLong("Addresses_id"));
                return branch;
            }
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Branch> getAllForOneBank(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAllForBankId);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            List<Branch> branches = new ArrayList<>();

            while(rs.next())
            {
                Branch branch = new Branch();
                branch.setId(rs.getLong("id") );
                branch.setName( rs.getString("branch_name"));
                branch.getBank().setId(rs.getLong("banks_id_bank"));
                branch.getAddress().setId(rs.getLong("Addresses_id"));
                branches.add(branch);

            }
            return branches;
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Branch> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<Branch> branches = new ArrayList<>();

            while(rs.next())
            {
                Branch branch = new Branch();
                branch.setId(rs.getLong("id") );
                branch.setName( rs.getString("branch_name"));
                branch.getBank().setId(rs.getLong("banks_id_bank"));
                branch.getAddress().setId(rs.getLong("Addresses_id"));
                branches.add(branch);

            }
            return branches;
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public Branch save(Branch branch) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,branch.getName());
            preparedStatement.setLong(2,branch.getBank().getId());
            preparedStatement.setLong(3,branch.getAddress().getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

        return branch;

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
    public void update(Branch branch) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,branch.getName());
            preparedStatement.setLong(2,branch.getBank().getId());
            preparedStatement.setLong(3,branch.getAddress().getId());
            preparedStatement.setLong(4,branch.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

    }
}
