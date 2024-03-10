package DAOs;

import java.util.List;

import DTOs.Games;
        import Exceptions.DaoException;

public interface GamesDaoInterface
{
    public List<Games> findAllGames() throws DaoException;
}
