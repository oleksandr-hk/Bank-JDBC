package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.IEmployee;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IEmployee {

    private final String getById = "SELECT * FROM employees WHERE id = ?";
    private final String getAll  = "SELECT * FROM employees";
    private final String save    = "INSERT INTO employees VALUES (?,?,?,?,?)";
    private final String delete  = "DELETE FROM employees WHERE id =?";
    private final String update  = "UPDATE cities SET employee_name = ?, employee_salary = ?, employee_salary = ?, employee_rating = ?, departments_id_department = ? WHERE id =?";

    private final String getBestEmployeeFromDepartment = "SELECT * FROM employees GROUP BY (departments_id_department) HAVING MAX(employee_rating)";


    @Override
    public Employee get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                Employee employee = new Employee();
                employee.setId(rs.getLong("idEmployee") );
                employee.setName(rs.getString("employee_name"));
                employee.setSurname(rs.getString("employee_surname"));
                employee.setSalary(rs.getDouble("employee_salary"));
                employee.setRating(rs.getDouble("employee_rating"));
                employee.setIdDepartment(rs.getLong("departments_id_department"));

                return employee;
            }
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    public List<Employee> getBestEmployeeFromDepartment(){

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getBestEmployeeFromDepartment);
            ResultSet rs = preparedStatement.executeQuery();
            List<Employee> employees = new ArrayList<>();

            while(rs.next())
            {
                Employee employee = new Employee();
                employee.setId(rs.getLong("idEmployee") );
                employee.setName(rs.getString("employee_name"));
                employee.setSurname(rs.getString("employee_surname"));
                employee.setSalary(rs.getDouble("employee_salary"));
                employee.setRating(rs.getDouble("employee_rating"));
                employee.setIdDepartment(rs.getLong("departments_id_department"));

                employees.add(employee);

            }
            return employees;
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Employee> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<Employee> employees = new ArrayList<>();

            while(rs.next())
            {
                Employee employee = new Employee();
                employee.setId(rs.getLong("idEmployee") );
                employee.setName(rs.getString("employee_name"));
                employee.setSurname(rs.getString("employee_surname"));
                employee.setSalary(rs.getDouble("employee_salary"));
                employee.setRating(rs.getDouble("employee_rating"));
                employee.setIdDepartment(rs.getLong("departments_id_department"));

                employees.add(employee);

            }
            return employees;
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public Employee save(Employee employee) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,employee.getName());
            preparedStatement.setString(2,employee.getSurname());
            preparedStatement.setDouble(3,employee.getSalary());
            preparedStatement.setDouble(4,employee.getRating());
            preparedStatement.setDouble(5,employee.getIdDepartment());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

        return employee;

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
    public void update(Employee employee) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,employee.getName());
            preparedStatement.setString(2,employee.getSurname());
            preparedStatement.setDouble(3,employee.getSalary());
            preparedStatement.setDouble(4,employee.getRating());
            preparedStatement.setDouble(5,employee.getIdDepartment());
            preparedStatement.setLong(6, employee.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
    }
}
