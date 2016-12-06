/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Chris Mazur
 */
public class GameState {
    public int _SEGMENT_SIZE;
    public int _GAME_ID = 0;
    public int lobbyNumber;
    public byte[][] _PLAYERS_IN_LOBBY = new byte[8][256];
    //public Game _THIS_GAME;

    
    public GameState(int lobNo)  {
        this.lobbyNumber = lobNo;
        this._GAME_ID = Server._GAMES_PLAYED;
        //_THIS_GAME = games[lobNo];
    }
    
    
    
}
