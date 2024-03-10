package DAOs;

import java.util.List;

import DTOs.Games;
import Exceptions.DaoException;

public interface GamesDaoInterface
{
    public List<Games> findAllGames() throws DaoException;
    public Games findGameByID(int game_ID) throws DaoException;
}