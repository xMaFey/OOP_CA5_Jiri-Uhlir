
package DAOs;

import java.util.List;

import DTOs.Games;
import Exceptions.DaoException;

public interface GamesDaoInterface
{
    //Jiri
    public List<Games> findAllGames() throws DaoException;

    //Ben
    public Games findGameByID(int game_ID) throws DaoException;

    //John
    public Games deleteGameByID(int game_id) throws DaoException;
    //public void insertGame(Games games) throws DaoException;

    //Jiri
    Games findById(int id);
    void updatePriceById(int id, int newPrice);

    //Jiri
    public Games findGameByTitle(String gameTitle) throws DaoException;
}
