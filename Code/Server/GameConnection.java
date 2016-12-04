/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris Mazur
 */
public class GameConnection implements Runnable {

    final int _BUF_SIZE = 1024;
    final int _NAME_SIZE = 256;
    final int _NAME_OFFSET = (_BUF_SIZE - _NAME_SIZE);
    final int _OFFSET = 8;
    int _THIS_CONNECTION = 0;
    int _LOBBY_SELECTION = -1;
    byte[] _CLIENT_PACKET_HEADER = new byte[_OFFSET];
    byte[] _SERVER_PACKET_HEADER = new byte[_OFFSET];
    byte[] _CURRENT_LOBBIES_HEADER = new byte[_OFFSET];
    byte[] _PLAYER_NAME = new byte[_NAME_SIZE];
    InetAddress _CLIENT_ADDR;
    int _CLIENT_PORT;
    int _SEGMENT_SIZE = 0;
    String line;
    boolean updateFromServer = false, updateFromClient = false;
    byte[] updateBuffer = new byte[_BUF_SIZE];
    byte[] buffer = new byte[_BUF_SIZE];
    FileInputStream fromFile = null;
    BufferedOutputStream nOut = null;
    BufferedInputStream nIn = null;
    private Socket client;
    private GameState gs;
    //private GAME game;

    public GameConnection(Socket client, int currentConnections) {
        this.client = client;

        _THIS_CONNECTION = currentConnections;
        _CLIENT_ADDR = client.getInetAddress();
        _CLIENT_PORT = client.getPort();

        _CLIENT_PACKET_HEADER[7] = (byte) _THIS_CONNECTION;
        _SERVER_PACKET_HEADER[7] = (byte) _THIS_CONNECTION;

        try {
            nOut = new BufferedOutputStream(client.getOutputStream());
            nIn = new BufferedInputStream(client.getInputStream());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void run() {
        loadLobbies();
        sendLobbyPrompt();
        try {
            addToGame();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GameConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        sendGameData();

        try {
            //Loop
            while (nIn.read(buffer) > 0 /*connected*/) {

                //Notify all connected something has come in and 
                //that it needs to mutex
                    //this.signalWait

                //Update the Game
                    //mapClientActionToGame
                
                //Notify all that the thing has been updated
                    //this.signalDone
                
                //Update the client
                sendGameData();

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /*public void onReceive(Signal signal){
     *  if signal == wait
     *      wait
     * 
     *  else if signal == done
     *      wake up
     * }
     * */
     
    
    private void loadLobbies() {
        //load the lobbies into the packet data for the client
        for (int i = 0; i < _OFFSET; i++) {
            buffer[i + _OFFSET] = Server._LOBBY_STATE[i];
        }
    }

    private void sendLobbyPrompt() {
        _SERVER_PACKET_HEADER[0] = 10;
        updateBufferFromServerPacketHeader(buffer);
        //tell the client you want them to select a _PLAYERS_IN_LOBBY prompt
        //write the packet to the client
        //packet has the information on the lobbies 
        //in the first 8 bytes after the server header

        try {
            nOut.write(buffer);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void addToGame() throws UnsupportedEncodingException {
        //check which _PLAYERS_IN_LOBBY the client has selected
        clearBuf();
        try {
            nIn.read(buffer);
            updateClientPacketHeaderFromBuffer(buffer);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        //assign to usable variables:
        //buffer[8] = selected _PLAYERS_IN_LOBBY
        _LOBBY_SELECTION = buffer[_OFFSET];

        //assigns to global _PLAYERS_IN_LOBBY tracker
        //TODO: Mutex
        //TODO: Client side handles no-touchy full lobbies for now?
        if (Server._LOBBY_STATE[_LOBBY_SELECTION] == 0) //make a new game if there is none.
        {
            Server.newGame();
        }

        //Increment number of players in the lobby
        Server._LOBBY_STATE[_LOBBY_SELECTION] += 1;

        //update the server's packet header with the lobby selection
        _SERVER_PACKET_HEADER[6] = (byte) _LOBBY_SELECTION;

        //Do not repeat this line - adds this player as the current player. If doen again will change player # in _PLAYERS_IN_LOBBY
        //Todo: fix underlying issue later
        _SERVER_PACKET_HEADER[5] = (byte) Server._LOBBY_STATE[_LOBBY_SELECTION]; //player # in _PLAYERS_IN_LOBBY

        //get the player name if any
        if (buffer[_NAME_OFFSET] != 0) {
            line = new String(buffer, _NAME_OFFSET, _NAME_SIZE, "UTF-8");
        } else {
            line = "PLAYER " + _THIS_CONNECTION;
        }

        _PLAYER_NAME = line.getBytes("UTF-8");


        //Give the connection a hadle to the lobby's gamestate
        gs = Server.gs[_LOBBY_SELECTION];
        //game = Server.games[_LOBBY_SELECTION];

        //add the player's name to the gamestate
        gs._PLAYERS_IN_LOBBY[_SERVER_PACKET_HEADER[5]] = _PLAYER_NAME;
    }

    private void sendGameData() {
        clearBuf();
        _SERVER_PACKET_HEADER[0] = 30; //sending game data
        updateBufferFromServerPacketHeader(buffer);


        //From 0 to the beginning of player name, in SIZE-sized segments
        for (int i = 0; i < ((_NAME_OFFSET - _OFFSET) / _SEGMENT_SIZE); i++) {
            int curSegment = _OFFSET + (i * _SEGMENT_SIZE);
            for (int j = 0; j < _SEGMENT_SIZE; j++) {
                //load into the buffer the current row or whatever with 1 space in between each delineation
                //buffer[curSegment + j] = (byte) gameData[curSegment + j]
                //Might need mapping? That's fine.
            }
        }
    }

    private void updateBufferFromServerPacketHeader(byte[] buf) {
        //before we send a packet, update it with our server packet header
        for (int i = 0; i < _OFFSET; i++) {
            buf[i] = _SERVER_PACKET_HEADER[i];
        }
    }

    private void updateClientPacketHeaderFromBuffer(byte[] buf) {
        //when we get a packet in, update our client packet header from it
        for (int i = 0; i < _OFFSET; i++) {
            _CLIENT_PACKET_HEADER[i] = buf[i];
        }
    }

    private void update() {
        //map gamestate
    }

    private void clearBuf() {
        buffer = new byte[_BUF_SIZE];
    }

    public void onClose() {
    }
}