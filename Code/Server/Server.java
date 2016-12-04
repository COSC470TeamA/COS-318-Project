package server;

import java.net.*;
import java.io.*;

public class Server {

    static final int _BUF_SIZE = 1024;
    static final int _PORT_NUMBER = 4445;
    protected static boolean[] playerID = new boolean[64];
    
    protected static byte[] _LOBBY_STATE = new byte[8]; //Lobby for each of the lobbies, needsto be its own class one day
    //protected GAME gameArray[8]; 
    protected static GameState[] gs = new GameState[8]; //gamestate for each of the games, mapped to the lobby.
    //protected static Game[] games = new Game[8];
    static int _CURRENT_CONNECTIONS = 0;
    static int _GAMES_PLAYED = 0;


    public static void main(String[] args) {

        ServerSocket server = null;
        Socket client = null;
        boolean finished = false;
        byte[] buffer = new byte[_BUF_SIZE];

        while (finished == false) {

            //Initialize the socket
            try {
                server = new ServerSocket(_PORT_NUMBER);
            } catch (IOException ioe) {
            }

            while (true) {
                try {
                    client = server.accept();
                    _CURRENT_CONNECTIONS++;
                    Thread worker = new Thread(new GameConnection(client, _CURRENT_CONNECTIONS));
                    worker.start();
                    //todo: repopulate workers
                   
                } catch (IOException ioe) {
                }

                
                
                
            }
        }
    }
    protected static void newGame(){
        //if someone enters an empty lobby
        //generate a new game
        //games[_lobby_number] = new Game;
        //gs[_lobby_number] = new GameState(games[_lobby_number])
        _GAMES_PLAYED++;
    }
    
    
}



