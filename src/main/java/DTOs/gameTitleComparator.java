package DTOs;
import java.util.Comparator;


//Ben Arrowsmith
public class gameTitleComparator implements Comparator<Game>{
    public int compare(Game game1, Game game2)
    {
        return game1.getGameTitle().compareTo(game2.getGameTitle());
    }
}
