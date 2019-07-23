package com.solvd.bank.dao.dao_jdbc;

import com.solvd.bank.dao.ITypeOfCard;
import com.solvd.bank.dao.connection_pool.ConnectionPoolQueue;
import com.solvd.bank.entity.TypeOfCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeOfCardDAO implements ITypeOfCard {

    private final String getById = "SELECT * FROM types_of_card WHERE id = ?";
    private final String getAll  = "SELECT * FROM types_of_card";
    private final String save    = "INSERT INTO types_of_card VALUES (?,?)";
    private final String delete  = "DELETE FROM types_of_card WHERE id =?";
    private final String update  = "UPDATE types_of_card SET name = ?, Countries_id = ? WHERE id =?";


    @Override
    public TypeOfCard get(Long id) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                TypeOfCard typeOfCard = new TypeOfCard();
                typeOfCard.setId(rs.getLong("id") );
                typeOfCard.setDescription(rs.getString("type_description"));
                typeOfCard.setName(rs.getString("type_name"));

                return typeOfCard;
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
    public List<TypeOfCard> getAll() {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(getAll);
            ResultSet rs = preparedStatement.executeQuery();
            List<TypeOfCard> types = new ArrayList<>();

            while(rs.next())
            {
                TypeOfCard typeOfCard = new TypeOfCard();
                typeOfCard.setId(rs.getLong("id") );
                typeOfCard.setDescription(rs.getString("type_description"));
                typeOfCard.setName(rs.getString("type_name"));
                types.add(typeOfCard);

            }
            return types;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
        return null;


    }

    @Override
    public TypeOfCard save(TypeOfCard typeOfCard) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1,typeOfCard.getDescription());
            preparedStatement.setString(2,typeOfCard.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }

        return typeOfCard;


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
    public void update(TypeOfCard typeOfCard) {

        Connection connection = ConnectionPoolQueue.getInstance().getConnection();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1,typeOfCard.getDescription());
            preparedStatement.setString(2,typeOfCard.getName());
            preparedStatement.setLong(3,typeOfCard.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

        }
        finally {

            ConnectionPoolQueue.getInstance().releaseConnection(connection);
        }
    }
}
