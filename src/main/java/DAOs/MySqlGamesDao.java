package DAOs;

import DTOs.Game;
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
        //Jiri Uhlir
        @Override
        public List<Game> findAllGames() throws DaoException {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                List<Game> gameList = new ArrayList<>();

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

                                Game g = new Game(gameId, gameTitle, developer, price, gbOfSpace, releaseDate);
                                gameList.add(g);
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
                return gameList;     // may be empty
        }

        //Ben Arrowsmith
        @Override
        public Game findGameByID(int game_ID) throws DaoException {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                Game game = null;
                try {
                        connection = this.getConnection();

                        String query = "SELECT * FROM GAMES WHERE id = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, game_ID);

                        resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                                int gameId = resultSet.getInt("id");
                                String gameTitle = resultSet.getString("gameTitle");
                                String developer = resultSet.getString("developer");
                                int price = resultSet.getInt("price");
                                float gbOfSpace = resultSet.getFloat("gbOfSpace");
                                Date releaseDate = resultSet.getDate("releaseDate");

                                game = new Game(gameId, gameTitle, developer, price, gbOfSpace, releaseDate);
                        }
                } catch (SQLException e) {
                        throw new DaoException("findGameByID() " + e.getMessage());
                } finally {
                        try {
                                if (resultSet != null) {
                                        resultSet.close();
                                }
                                if (preparedStatement != null) {
                                        preparedStatement.close();
                                }
                                if (connection != null) {
                                        freeConnection(connection);
                                }
                        } catch (SQLException e) {
                                throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
                        }
                }
                return game;     // reference to User object, or null value
        }
        //John Nally
        @Override
        public Game deleteGameByID (int game_id) throws DaoException {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                Game deletedGame = null;

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
        @Override
        public void insertGame (Game game) throws DaoException {
                Connection connection = null;
                PreparedStatement preparedStatement = null;

                connection = this.getConnection();

                String query = "INSERT INTO games (id, gameTitle, developer, price, gbOfSpace, releaseDate) VALUES (?, ?, ?, ?, ?, ?)";

                try {
                        preparedStatement = connection.prepareStatement(query);

                        preparedStatement.setInt(1, game.getId());
                        preparedStatement.setString(2, game.getGameTitle());
                        preparedStatement.setString(3, game.getDeveloper());
                        preparedStatement.setInt(4, game.getPrice());
                        preparedStatement.setFloat(5, game.getGbOfSpace());
                        java.util.Date utilDate = game.getRealeaseDate();
                        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                        preparedStatement.setDate(6, sqlDate);
                        preparedStatement.executeUpdate();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }
        //Jiri
        @Override
        public Game findById(int id){
                return null;
        }

        //Jiri
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