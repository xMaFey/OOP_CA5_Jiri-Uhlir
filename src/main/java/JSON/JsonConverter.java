package JSON;

import DTOs.Game;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

//Ben
public class JsonConverter{
    private static final Gson gsonParser = new Gson();

    public static JsonObject jsonToJsonObject(String json){
        return gsonParser.fromJson(json, JsonObject.class);
    }

    public static String gamesToJson(List<Game> game){

//            List<Game> games = new ArrayList<>();
//
//            Game gms = new Game(117, "Journey", "Thatgamecompany", 15, 4, new Date(2012 - 3 - 13));
//
//            games.add(gms);

        Gson gsonParser = new Gson();

        String jsonString = gsonParser.toJson(game);

        return jsonString;
    }
    public static String gameToJson(Game game){
        Gson gsonParser = new Gson();

        String jsonString = gsonParser.toJson(game);

        return jsonString;
    }

    //Jiri - Feature 8
    public static String convertEntityToJson(Game game, String key){
        Gson gsonParser = new Gson();
        String jsonString = gsonParser.toJson(game);

        String jsonEntity = "{" + "\"" + key + "\"" + ":" + "\"" + game.getGameTitle() + "\"" + "}";

        return jsonEntity;
    }
}
