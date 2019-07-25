package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.IDeal;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.Deal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealDAO  implements IDeal {

    private final String getById = "SELECT * FROM deals WHERE id = ?";
    private final String getAll  = "SELECT * FROM deals";
    private final String save    = "INSERT INTO deals VALUES (?,?,?,?,?)";
    private final String delete  = "DELETE FROM deals WHERE id =?";
    private final String update  = "UPDATE deals SET date = ?, employees_idEmployee = ?, customers_id_customer = ? , transactions_id_transactions = ?,service_id_service = ? WHERE id =?";

    @Override
    public Deal get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                Deal deal = new Deal();
                deal.setId(rs.getLong("id") );
                deal.setDate(rs.getDate("date"));
                deal.setIdEmployee(rs.getLong("employees_idEmployee"));
                deal.setIdCustomer(rs.getLong("customers_id_customer"));
                deal.setIdTransaction(rs.getLong("transactions_id_transactions"));
                deal.setIdService(rs.getLong("services_id_service"));
                return deal;
            }
        } catch (SQLException ex) {

        }
        finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Deal> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<Deal> deals = new ArrayList<>();

            while(rs.next())
            {
                Deal deal = new Deal();
                deal.setId(rs.getLong("id") );
                deal.setDate(rs.getDate("date"));
                deal.setIdEmployee(rs.getLong("employees_idEmployee"));
                deal.setIdCustomer(rs.getLong("customers_id_customer"));
                deal.setIdTransaction(rs.getLong("transactions_id_transactions"));
                deal.setIdService(rs.getLong("services_id_service"));
                deals.add(deal);

            }
            return deals;
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public Deal save(Deal deal) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setDate(1, (Date) deal.getDate());
            preparedStatement.setLong(2, deal.getIdEmployee());
            preparedStatement.setLong(3, deal.getIdCustomer());
            preparedStatement.setLong(4, deal.getIdTransaction());
            preparedStatement.setLong(5,deal.getIdService());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return deal;

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
    public void update(Deal deal) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setDate(1, (Date) deal.getDate());
            preparedStatement.setLong(2, deal.getIdEmployee());
            preparedStatement.setLong(3, deal.getIdCustomer());
            preparedStatement.setLong(4, deal.getIdTransaction());
            preparedStatement.setLong(5, deal.getIdService());
            preparedStatement.setLong(6, deal.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
    }
}
