/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris Mazur
 */
public class Client {

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
    String _SERVER_ADDR;
    int _SERVER_PORT = 4445;
    int _SEGMENT_SIZE = 254;
    String line;
    boolean updateFromServer = false, updateFromClient = false;
    byte[] updateBuffer = new byte[_BUF_SIZE];
    byte[] buffer = new byte[_BUF_SIZE];
    byte state;
    BufferedOutputStream nOut = null;
    BufferedInputStream nIn = null;
    static Socket server;
    private GameUI game;
    static int xCoord, yCoord;
    static char charVal;
    static char[] rowVal = new char[6];
    

    public Client() {
    }

    //connect to the server
    public void connect() {
        try {
            server = new Socket(_SERVER_ADDR, _SERVER_PORT);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        
    public void updateBufferFromClientPacketHeader(byte[] buf) {
        //before we send a packet, canUpdate it with our server packet header
        for (int i = 0; i < _OFFSET; i++) {
            buf[i] = _CLIENT_PACKET_HEADER[i];
        }
    }
}
