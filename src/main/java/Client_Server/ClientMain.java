package Client_Server;

import DAOs.GamesDaoInterface;
import DAOs.MySqlGamesDao;
import DTOs.Game;
import Exceptions.DaoException;
import JSON.JsonConverter;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static java.lang.System.in;


public class ClientMain {
    public static void main(String[] args) {
        ClientMain client = new ClientMain();
        client.start(8888); // start client to connect to the port number that the server uses
    }

    public void start(int portNumber) {

        try (   // create socket in client to connect to the server
                Socket socket = new Socket("localhost", 8888);
                // get the socket's output stream
                OutputStream outputStream = socket.getOutputStream();
        ) {

            boolean menu_open = true;


            PrintWriter out = new PrintWriter(outputStream, true);
            System.out.println("The client is running and has connected to the server.");

            while (menu_open) {

                System.out.println("[1] Press 1 to Display Entity by ID");
                System.out.println("[2] Press 2 to other");
                System.out.println("[3] Press 3 to Delete Entity by ID");


                //input
                Scanner menu = new Scanner(in);
                int answer = menu.nextInt();

                //Ben
                if(answer==1) {
                    System.out.println("Enter ID: ");

                    Scanner options = new Scanner(in);
                    int newID = options.nextInt();

                    System.out.println(newID);
                    out.println(newID);


                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String response = in.readLine();    // wait, and receive a return message from the server
                    System.out.println("In client: The server response was : " + response);

                }

                if(answer==2) {
                    menu_open = false;
                }

                //Jiri - Feature 12
                //(maybe switch number of answers)
                if(answer == 3){
                    System.out.println("Enter ID to delete: ");
                    int deleteID = menu.nextInt();

                    //Prepare delete command as JSON
                    JsonObject deleteCommand = new JsonObject();
                    deleteCommand.addProperty("command", "delete");
                    deleteCommand.addProperty("id", deleteID);

                    //Send delete command to server
                    out.println(deleteCommand.toString());

                    //Receive and print response from server
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String response = in.readLine();
                    System.out.println("Server response: " + response);
                }
            }

            // send greeting message to server via socket

            // Next, deal with the response from the server:
            // - get the input stream from the socket, and wrap in a reader
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String response = in.readLine();    // wait, and receive a return message from the server
            System.out.println("In client: The server response was : " + response);
            System.out.println("Finished! - client is exiting.");

        } catch (UnknownHostException e) {
            System.out.println(e);  // print out the exception
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
