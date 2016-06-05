package game;

import shared.*;
/**
 * This class is CRITICAL. 
 * Generates a UNIQUE Game Event ID and stores it in a Text File Called Events. Because of the high frequency of the IO. The file dumped separately.
 * 
 */

import java.util.Properties;
import shared.Settings;

class GameEventSettings extends Settings {
	
	public long GameID=0;
    public long lastGameID=0;  /*DEFAULT*/

/* *********** PROPERTY KEYS    ************** */    
    
    private String GameIDString = "GameID";  

    
/* *********** CONSTRUCTORS    ************** */   
    GameEventSettings() {
        super("game.events", "Game Events");
	getSettings(); 
    }

/* ***********  METHODS    ************** */ 
    
    protected void setDefaults(Properties defaults) {
	defaults.put(GameIDString, new Long(lastGameID).toString());

    }

    protected void updateSettingsFromProperties() {
	try {
	    lastGameID = Long.parseLong(properties.getProperty(GameIDString));
	} catch (NumberFormatException e) {
			Messages.fatalError("CANNOT create or Access the game Events",e);
	}
    }

    protected void updatePropertiesFromSettings() {
	properties.put(GameIDString, new Long(lastGameID).toString());

    }

    public String toString() {
        return "["+ "GameID=" + lastGameID + "]";
    }

/* *********** SETTERS    ***************/   
    
    /* These should be thread safe, but not tested yet   */
    
     synchronized long setNewGameID() {
    	synchronized(getClass()) {	
    		this.lastGameID=getLastGameID();
    		this.lastGameID++;
    		saveSettings();
    	}
    		return this.lastGameID;
    
    }
    
    long setNewGameID(long gameID) {
    	synchronized(getClass()){
    		this.lastGameID=gameID;
    		saveSettings();
    		return this.lastGameID;
    	}
    }
    
   
/* *********** GETTERS    ************** */     
     long getLastGameID() {
    	synchronized(getClass()) 
    	{	getSettings();
    		return this.lastGameID;}
    }
    
}
