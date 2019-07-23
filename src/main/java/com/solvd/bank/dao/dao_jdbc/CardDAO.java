package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.ICard;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.Card;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDAO implements ICard {

    private final String getById = "SELECT * FROM cards WHERE id = ?";
    private final String getAll  = "SELECT * FROM cards";
    private final String save    = "INSERT INTO cards VALUES (?,?,?,?,?,?)";
    private final String delete  = "DELETE FROM cards WHERE id =?";
    private final String update  = "UPDATE cards SET number = ?, expiration_date = ?, cvv = ?,customer_id_customer = ?, types_of_card_id_type_Of_card = ?,banks_id_bank = ?  WHERE id =?";

    @Override
    public Card get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                Card card = new Card();
                card.setId(rs.getLong("id") );
                card.setNumber( rs.getString("number"));
                card.setExpDate(rs.getDate("expiration_date"));
                card.setCvv(rs.getString("cvv"));
                card.setIdCustomer(rs.getLong("customer_id_customer"));
                card.setCardType(rs.getLong("types_of_card_id_type_of_card"));
                card.setIdBank(rs.getLong("banks_id_bank"));

                return card;
            }
        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public List<Card> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<Card> cards = new ArrayList<>();

            while(rs.next())
            {
                Card card = new Card();
                card.setId(rs.getLong("id") );
                card.setNumber( rs.getString("number"));
                card.setExpDate(rs.getDate("expiration_date"));
                card.setCvv(rs.getString("cvv"));
                card.setIdCustomer(rs.getLong("customer_id_customer"));
                card.setCardType(rs.getLong("types_of_card_id_type_of_card"));
                card.setIdBank(rs.getLong("banks_id_bank"));

                cards.add(card);
            }
            return cards;
        } catch (SQLException ex) {

        }
        finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;
    }

    @Override
    public Card save(Card card) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,card.getNumber());
            preparedStatement.setDate(2, (Date) card.getExpDate());
            preparedStatement.setString(3,card.getCvv());
            preparedStatement.setLong(4,card.getIdCustomer());
            preparedStatement.setLong(5,card.getCardType());
            preparedStatement.setLong(6,card.getIdBank());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return card;

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
    public void update(Card card) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,card.getNumber());
            preparedStatement.setDate(2, (Date) card.getExpDate());
            preparedStatement.setString(3,card.getCvv());
            preparedStatement.setLong(4,card.getIdCustomer());
            preparedStatement.setLong(5,card.getCardType());
            preparedStatement.setLong(6,card.getIdBank());
            preparedStatement.setLong(7,card.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

    }
}
