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
    final int _NAME_SIZE = 254;
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
    int _SEGMENT_SIZE = 254;
    String line;
    boolean updateFromServer = false, updateFromClient = false;
    byte[] updateBuffer = new byte[_BUF_SIZE];
    byte[] buffer = new byte[_BUF_SIZE];
    FileInputStream fromFile = null;
    BufferedOutputStream nOut = null;
    BufferedInputStream nIn = null;
    private Socket client;
    private GameLobby gs;
    private Game game;
    int _BOARD_SIZE = 6;

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
        System.out.println("Client " + _CLIENT_ADDR + " connected on thread " + _THIS_CONNECTION);
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
                //update the internal game
                signalUpdate(buffer);

                clearBuf();
                updateBufferFromServerPacketHeader(buffer);

                sendGameData();

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void signalUpdate(byte[] buf) {
        boolean updating = true;
        while (updating) {
            //not our turn, sit and spin
            while (Server.canUpdate[_LOBBY_SELECTION] != (byte) _THIS_CONNECTION) {
                //notify need of turn
                if (Server.notify[_LOBBY_SELECTION] != 1) {
                    Server.canUpdate[_LOBBY_SELECTION] = (byte) _THIS_CONNECTION;
                }
            }
            //claim mutex
            if (Server.notify[_LOBBY_SELECTION] != 1) {
                Server.notify[_LOBBY_SELECTION] = 1;

                //Perform changes on gamestate
                Server.xCoord[_LOBBY_SELECTION] = buffer[_OFFSET]; //byte after header = x
                Server.yCoord[_LOBBY_SELECTION] = buffer[_OFFSET + 1]; //byte after x = y;
                Server.charVal[_LOBBY_SELECTION] = Byte.toString(buffer[_OFFSET + 3]).charAt(0); //charvalue

                //update the game from the server's gamestate
                game.insertChar(Server.xCoord[_LOBBY_SELECTION],
                        Server.yCoord[_LOBBY_SELECTION],
                        Server.charVal[_LOBBY_SELECTION]);

                //release mutex
                Server.notify[_LOBBY_SELECTION] = 0;
            }
            updating = false;
        }
    }

    private void loadLobbies() {
        //load the lobbies into the packet data for the client
        for (int i = 0; i < _OFFSET; i++) {
            buffer[i + _OFFSET] = Server._LOBBY_STATE[i];
        }
    }

    private void sendLobbyPrompt() {
        _SERVER_PACKET_HEADER[0] = (byte) 10;
        _SERVER_PACKET_HEADER[1] = (byte) _THIS_CONNECTION;
        updateBufferFromServerPacketHeader(buffer);
        //tell the client you want them to select a _PLAYERS_IN_LOBBY prompt
        //write the packet to the client
        //packet has the information on the lobbies 
        //in the first 8 bytes after the server header

        for (int i = 0; i < Server.gs.length; i++) {
            buffer[_OFFSET + i] = Server._LOBBY_STATE[i];
        }
        try {

            System.out.println("Server sending Lobby Info to client " + _THIS_CONNECTION);
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
        
        if (_CLIENT_PACKET_HEADER[0] == 10) System.out.println("Client has sent lobby selection of: " + _CLIENT_PACKET_HEADER[1]);
        
        //assign to usable variables:
        //buffer[8] = selected _PLAYERS_IN_LOBBY
        _LOBBY_SELECTION = buffer[_OFFSET];

        //assigns to global _PLAYERS_IN_LOBBY tracker
        //TODO: Mutex
        //TODO: Client side handles no-touchy full lobbies for now?
        if (Server._LOBBY_STATE[_LOBBY_SELECTION] == 0) //make a new game if there is none.
        {
            Server.newGame(_LOBBY_SELECTION);
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
        this.gs = Server.gs[_LOBBY_SELECTION];
        this.game = Server.games[_LOBBY_SELECTION];

        //add the player's name to the gamestate
        this.gs._PLAYERS_IN_LOBBY[_SERVER_PACKET_HEADER[5]] = _PLAYER_NAME;
    }

    private void sendGameData() {
        String mem_posString;

        clearBuf();

        _SERVER_PACKET_HEADER[0] = 30; //sending game data
        updateBufferFromServerPacketHeader(buffer);

        byte[] buf = new byte[_SEGMENT_SIZE];

        //From 0 to the beginning of player name, in SIZE-sized segments
        for (int i = 0; i < ((_NAME_OFFSET - _OFFSET) / _BOARD_SIZE); i++) { //defaulted to 5 right now
            buf = new byte[_SEGMENT_SIZE]; //clear buf
            mem_posString = new String(game.mem_pos[i].toString()); //get row i as string
            buf = mem_posString.getBytes(); //insert into appropriate area as byte[]
            int z = (_OFFSET + (i * _SEGMENT_SIZE)); //current segment byte; 

            //load the buf-string into the buffer at the current segment 
            for (int j = 0; j < buf.length; j++) {
                buffer[z] = buf[j]; //load into buffer beginning at offset
                z++; //increment current segment byte
            }
        }

        //Send to Client
        try {
            nOut.write(buffer);
        } catch (IOException ex) {
            Logger.getLogger(GameConnection.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    private void updateBufferFromServerPacketHeader(byte[] buf) {
        //before we send a packet, canUpdate it with our server packet header
        for (int i = 0; i < _OFFSET; i++) {
            buf[i] = _SERVER_PACKET_HEADER[i];
        }
    }

    private void updateClientPacketHeaderFromBuffer(byte[] buf) {
        //when we get a packet in, canUpdate our client packet header from it
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