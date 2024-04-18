package DAOs;

import java.util.List;

import DTOs.Game;
import Exceptions.DaoException;

public interface GamesDaoInterface
{
    //Jiri
    public List<Game> findAllGames() throws DaoException;
    //Ben
    public Game findGameByID(int game_ID) throws DaoException;
    //John
    public Game deleteGameByID(int game_id) throws DaoException;
    //John
    public void insertGame(Game game) throws DaoException;

    //Jiri
    Game findById(int id);
    void updatePriceById(int id, int newPrice);
}