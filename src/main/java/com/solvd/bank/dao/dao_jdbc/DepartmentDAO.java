package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.IDepartment;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements IDepartment {

    private final String getById = "SELECT * FROM departments WHERE id = ?";
    private final String getAll  = "SELECT * FROM departments";
    private final String save    = "INSERT INTO departments VALUES (?,?)";
    private final String delete  = "DELETE FROM departments WHERE id =?";
    private final String update  = "UPDATE departments SET department_name = ?, branches_id_branch = ? WHERE id =?";


    @Override
    public Department get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                Department department = new Department();
                department.setId(rs.getLong("id_department"));
                department.setName( rs.getString("department_name"));
                department.setIdBranch(rs.getLong("branches_id_branch"));
                return department;
            }
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Department> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<Department> departments = new ArrayList<>();

            while(rs.next())
            {
                Department department = new Department();
                department.setId(rs.getLong("id_department"));
                department.setName( rs.getString("department_name"));
                department.setIdBranch(rs.getLong("branches_id_branch"));

                departments.add(department);

            }
            return departments;
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public Department save(Department department) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,department.getName());
            preparedStatement.setLong(2,department.getIdBranch());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return department;
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
    public void update(Department department) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,department.getName());
            preparedStatement.setLong(2,department.getIdBranch());
            preparedStatement.setLong(3,department.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
    }
}
