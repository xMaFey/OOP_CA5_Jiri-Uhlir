package DAOs;

import DTOs.Games;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlGamesDao extends MySqlDao implements GamesDaoInterface
{
        @Override
        public List<Games> findAllGames() throws DaoException {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                List<Games> gamesList = new ArrayList<>();

                try
                {
                        //Get connection object using the getConnection() method inherited
                        // from the super class (MySqlDao.java)
                        connection = this.getConnection();

                        String query = "SELECT * FROM GAMES";
                        preparedStatement = connection.prepareStatement(query);

                        //Using a PreparedStatement to execute SQL...
                        resultSet = preparedStatement.executeQuery();
                        while (resultSet.next())
                        {
                                int gameId = resultSet.getInt("id");
                                String gameTitle = resultSet.getString("gameTitle");
                                String developer = resultSet.getString("developer");
                                int price = resultSet.getInt("price");
                                float gbOfSpace = resultSet.getFloat("gbOfSpace");
                                Date releaseDate = resultSet.getDate("releaseDate");

                                Games g = new Games(gameId, gameTitle, developer, price, gbOfSpace, releaseDate);
                                gamesList.add(g);
                        }
                } catch (SQLException e)
                {
                        throw new DaoException("findAllGamesResultSet() " + e.getMessage());
                } finally
                {
                        try
                        {
                                if (resultSet != null)
                                {
                                        resultSet.close();
                                }
                                if (preparedStatement != null)
                                {
                                        preparedStatement.close();
                                }
                                if (connection != null)
                                {
                                        freeConnection(connection);
                                }
                        } catch (SQLException e)
                        {
                                throw new DaoException("findAllGames() " + e.getMessage());
                        }
                }
                return gamesList;     // may be empty
        }

        @Override
        public Games findGameByID(int game_ID) throws DaoException
        {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                Games game = null;
                try
                {
                        connection = this.getConnection();

                        String query = "SELECT * FROM GAMES WHERE id = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1,game_ID);

                        resultSet = preparedStatement.executeQuery();
                        if (resultSet.next())
                        {
                                int gameId = resultSet.getInt("id");
                                String gameTitle = resultSet.getString("gameTitle");
                                String developer = resultSet.getString("developer");
                                int price = resultSet.getInt("price");
                                float gbOfSpace = resultSet.getFloat("gbOfSpace");
                                Date releaseDate = resultSet.getDate("releaseDate");

                                game = new Games(gameId, gameTitle, developer, price, gbOfSpace, releaseDate);
                        }
                } catch (SQLException e)
                {
                        throw new DaoException("findGameByID() " + e.getMessage());
                } finally
                {
                        try
                        {
                                if (resultSet != null)
                                {
                                        resultSet.close();
                                }
                                if (preparedStatement != null)
                                {
                                        preparedStatement.close();
                                }
                                if (connection != null)
                                {
                                        freeConnection(connection);
                                }
                        } catch (SQLException e)
                        {
                                throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
                        }
                }
                return game;     // reference to User object, or null value
        }

        //John Nally
        @Override
        public Games deleteGameByID ( int game_id) throws DaoException {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                Games deletedGame = null;

                try {
                        connection = this.getConnection();
                        String query = "DELETE FROM GAMES WHERE id = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, game_id);

                        deletedGame = findGameByID(game_id);

                        preparedStatement.executeUpdate();

                } catch (SQLException e) {
                        throw new DaoException("deleteGameByID() " + e.getMessage());
                } finally {
                        try {
                                if (resultSet != null) {
                                        resultSet.close();
                                }
                                if (preparedStatement != null) {
                                        preparedStatement.close();
                                }

                                if (connection != null) {
                                        connection.close();
                                }
                        } catch (SQLException e) {
                                throw new DaoException("deleteGameByID() " + e.getMessage());
                        }
                }
                return deletedGame;
        }

        //John Nally
//        @Override
//        public void insertGame (Games games) throws DaoException {
//                Connection connection = null;
//                PreparedStatement preparedStatement = null;
//
//                connection = this.getConnection();
//
//                String query = "INSERT INTO games (id, gameTitle, developer, price, gbOfSpace, releaseDate) VALUES (?, ?, ?, ?, ?, ?)";
//
//                try {
//                        preparedStatement = connection.prepareStatement(query);
//
//                        preparedStatement.setInt(1, games.getId());
//                        preparedStatement.setString(2, games.getGameTitle());
//                        preparedStatement.setString(3, games.getDeveloper());
//                        preparedStatement.setInt(4, games.getPrice());
//                        preparedStatement.setFloat(5, games.getGbOfSpace());
//                        java.util.Date utilDate = games.getRealeaseDate();
//                        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//                        preparedStatement.setDate(6, sqlDate);
//                        preparedStatement.executeUpdate();
//                } catch (SQLException e) {
//                        throw new RuntimeException(e);
//                }
//        }

        @Override
        public Games findById(int id){
                return null;
        }

        @Override
        public void updatePriceById(int id, int newPrice){
                Connection connection = null;

                try{
                        connection = this.getConnection();
                        String sq1 = "UPDATE games SET price=? WHERE id=?";
                        PreparedStatement statement = connection.prepareStatement(sq1);
                        statement.setInt(1, newPrice);
                        statement.setInt(2, id);
                        statement.executeUpdate();
                        System.out.println("Price updated successfully for game with ID: " + id);

                } catch(SQLException e){
                        e.printStackTrace();
                }

        }

}