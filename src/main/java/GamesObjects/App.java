package GamesObjects;

import java.util.List;

import DAOs.MySqlGamesDao;
import DAOs.GamesDaoInterface;
import DTOs.Games;
import Exceptions.DaoException;


public class App
{
    public static void main(String[] args)
    {
        GamesDaoInterface IUserDao = new MySqlGamesDao();

        try
        {
            System.out.println("\nCall findAllGames()");
            List<Games> games = IUserDao.findAllGames();

            if( games.isEmpty() )
                System.out.println("There are no Games");
            else {
                for (Games game : games)
                    System.out.println("User: " + game.toString());
            }

        }
        catch(DaoException e )
        {
            e.printStackTrace();
        }
    }
}

