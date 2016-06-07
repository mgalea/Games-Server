package game;

/**
 * This class extends the Properties JAVA class to create an application specific properties file
 */

import java.util.Properties;

import shared.Settings;
import shared.Constants;


public class GameServerSettings extends Settings implements Constants {



/* ********** DEFAULT  VALUES  ***************** */

    private int maxPlayers = MAX_PLAYERS;
    private int maxGames = MAX_GAMES;
    public long gameNumber;

/* *********** PROPERTY KEYS    ************** */    

    private String maxPlayersName = "max_players";
    private String maxGamesName = "max_games";
    private String lastGameNumber = "last_game_number";

    
/* *********** CONSTRUCTORS    ************** */   
    GameServerSettings() {
        super("server.cfg", "Server Parameters Default Settings");
        getSettings(); 
    }

/* ***********  METHODS    ************** */ 
    
    protected void setDefaults(Properties defaults) {


	defaults.put(maxPlayersName, new Integer(maxPlayers).toString());
	defaults.put(maxGamesName, new Integer(maxGames).toString());
	defaults.put(lastGameNumber, Long.toString(gameNumber));
    }

    protected void updateSettingsFromProperties() {
	try {

	    gameNumber = Long.parseLong(properties.getProperty(lastGameNumber));
	    maxPlayers = Integer.parseInt(properties.getProperty(maxPlayersName));
	    maxGames = Integer.parseInt(properties.getProperty(maxGamesName));
	} catch (NumberFormatException e) {
	    // we don't care if the property was of the wrong format,
	    // they've all got default values. So catch the exception
	    // and keep going.
	}
    }

    protected void updatePropertiesFromSettings() {

	properties.put(lastGameNumber, new Long(gameNumber).toString());
	properties.put(maxGamesName, new Integer(maxGames).toString());
	properties.put(maxPlayersName, new Integer(maxPlayers).toString());
	
    }

    public String toString() {
        return "["

               + "maxGames=" + maxGames + ","
               + "maxPlayers=" + maxPlayers + ","
               + "gameNumber=" + gameNumber + "]";
    }

/* *********** SETTERS    ***************/    
  
 
    public void setMaxPlayers(int maxPlayers) {
	this.maxPlayers = maxPlayers;
	saveSettings();
    }
 
    public void setMaxGames(int maxGames) {
	this.maxGames=maxGames;
	saveSettings();
    }
    
    void setGameNumber(long gameNumber) {
	this.gameNumber = gameNumber;
	saveSettings();
    }
    
 
/* *********** GETTERS ************** */     
   int getMaxPlayers() {
	return maxPlayers;
    }


    int getMaxGames() {
	return maxGames;
    }

    public long getLastGame() {
 		return gameNumber;
     }








}