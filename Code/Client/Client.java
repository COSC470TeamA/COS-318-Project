/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.Socket;

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
    InetAddress _SERVER_ADDR;
    int _SERVER_PORT;
    int _SEGMENT_SIZE = 254;
    String line;
    boolean updateFromServer = false, updateFromClient = false;
    byte[] updateBuffer = new byte[_BUF_SIZE];
    byte[] buffer = new byte[_BUF_SIZE];
    FileInputStream fromFile = null;
    BufferedOutputStream nOut = null;
    BufferedInputStream nIn = null;
    
    private Socket server;
    
    private GameUI game;
    
    
    
    public static void main (String[] args){
        
        
        
        
        
        
    }
    
    
    
}
