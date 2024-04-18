package Client_Server;


import DTOs.Game;
import JSON.JsonConverter;
import DAOs.MySqlGamesDao;
import DAOs.GamesDaoInterface;
import Exceptions.DaoException;
import JSON.JsonConverter;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    final int PORT_NUMBER = 8888;  // could be any port from 1024 to 49151 (that doesn't clash with other Apps)

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {


        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
             Socket clientSocket = serverSocket.accept();       // start server to listen on port number 8888
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            System.out.println("The server has started and is waiting for a client to connect.");

            GamesDaoInterface IUserDao = new MySqlGamesDao();

            int message = in.read();

            System.out.println("The GreetingServer has received this message from a client: " + message);
            System.out.println("The server is replying to the client.");

            Game game = IUserDao.findGameByID(message);

            String result = JsonConverter.gameToJson(game);
            if( game != null ) { // null returned if userid and password not valid
                out.println(result);
            }
            else
                out.println("Game with that ID not found");

            //Jiri - Feature 12
            String command = in.readLine();

            if(command != null && !command.isEmpty()){
                //Parse command to JSON
                JsonObject jsonCommand = JsonConverter.jsonToJsonObject(command);

                //Check if the command is for deletion
                if(jsonCommand.has("command") && jsonCommand.get("command").getAsString().equals("delete")){
                    //Retrieve ID from the command
                    int id = jsonCommand.get("id").getAsInt();
                    //Attempt to delete the game
                    Game deletedGame = IUserDao.deleteGameByID(id);

                    //Send response to the client
                    if(deletedGame != null){
                        out.println("Game " + deletedGame + " was deleted");
                    } else{
                        out.println("No game found with ID: " + id);
                    }
                }
            } else{
                //Handle invalid or empty command
                out.println("Invalid command");
            }

            System.out.println("The GreetingServer is finished, and is exiting. Goodbye!");

        } catch (IOException exception) {
            System.out.println(exception);
        }
        catch(DaoException e )
        {
            e.printStackTrace();
        }
    }
}
