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

            System.out.println("\nCall findGameByID");
            int gameId = 110;

            Games game = IUserDao.findGameByID(gameId);

            if( game != null ) // null returned if userid and password not valid
                System.out.println("Game found: " + game);
            else
                System.out.println("Game with that ID not found");

        }
        catch(DaoException e )
        {
            e.printStackTrace();
        }
    }
}

