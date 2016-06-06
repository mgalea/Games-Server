package game;

/**
 * This class extends the Properties JAVA class to create an application specific properties file
 */

import java.util.Properties;

import javax.swing.JTextField;

import shared.Settings;
import shared.Constants;


class GameServerSettings extends Settings implements Constants {


/* ********** DEFAULT  VALUES  ***************** */
    private long delay = FIVE_SECONDS;
    private long countDown = ONE_MINUTE;
    private int maxPlayers = MAX_PLAYERS;
    private int maxCards = MAX_CARDS;
    private double stake=MAX_STAKE;
    private String name=DEFAULT_NAME;    
    public long gameNumber;

/* *********** PROPERTY KEYS    ************** */    
    private String gameName = "name";
    private String stakeName= "stake";    
    private String delayName = "ball_delay";
    private String countDownName = "count_down";
    private String maxPlayersName = "max_players";
    private String maxCardsName = "max_cards";
    private String lastGameNumber = "last_game_number";

    
/* *********** CONSTRUCTORS    ************** */   
    GameServerSettings() {
        super("game.cfg", "Game Parameters Default Settings");
	getSettings(); 
    }

/* ***********  METHODS    ************** */ 
    
    protected void setDefaults(Properties defaults) {
    defaults.put(gameName, name);
    defaults.put(stakeName, new Double(stake).toString());
    defaults.put(countDownName, new Long(countDown).toString());
	defaults.put(delayName, new Long(delay).toString());
	defaults.put(countDownName, new Long(countDown).toString());
	defaults.put(maxPlayersName, new Integer(maxPlayers).toString());
	defaults.put(maxCardsName, new Integer(maxCards).toString());
	defaults.put(lastGameNumber, Long.toString(gameNumber));
    }

    protected void updateSettingsFromProperties() {
	try {
		name = properties.getProperty(gameName);
		stake = Double.parseDouble(properties.getProperty(stakeName));
	    delay = Long.parseLong(properties.getProperty(delayName));
	    countDown = Long.parseLong(properties.getProperty(countDownName));
	    maxPlayers = Integer.parseInt(properties.getProperty(maxPlayersName));
	    maxCards = Integer.parseInt(properties.getProperty(maxCardsName));
	} catch (NumberFormatException e) {
	    // we don't care if the property was of the wrong format,
	    // they've all got default values. So catch the exception
	    // and keep going.
	}
    }

    protected void updatePropertiesFromSettings() {
    properties.put(gameName, name);
    properties.put(stakeName, new Double(stake).toString());
    properties.put(delayName, new Long(delay).toString());
	properties.put(countDownName, new Long(countDown).toString());
	properties.put(maxCardsName, new Integer(maxCards).toString());
	properties.put(maxPlayersName, new Integer(maxPlayers).toString());
	
    }

    public String toString() {
        return "["
        		+ "name=" + name + ","
        	   + "stake=" + stake + ","
               + "delay=" + delay + ","
               + "countDown=" + countDown + ","
               + "maxPlayers=" + maxPlayers + ","
               + "maxCards=" + maxCards + "]";
    }

/* *********** SETTERS    ***************/    
    void setDelay(long delay) {
	this.delay = delay;
	saveSettings();
    }
    
    void setMaxPlayers(int maxPlayers) {
	this.maxPlayers = maxPlayers;
	saveSettings();
    }
    
    void setMaxCards(int maxCards) {
	this.maxCards = maxCards;
	saveSettings();
    }
    
    void setCountDown(long countDown) {
	this.countDown = countDown;
	saveSettings();
    }
    
    void setGameNumber(long gameNumber) {
	this.gameNumber = gameNumber;
	saveSettings();
    }
    
	public void setName(String nameField) {
		this.name = nameField;
		saveSettings();
		
	}
	public void setStake(double stakeField) {
		this.stake=stakeField;
		saveSettings();
	}
    
/* *********** GETTERS ************** */     
    long getDelay() {
	return delay;
    }
    
    long getCountDown() {
	return countDown;
    }

    int getMaxPlayers() {
	return maxPlayers;
    }


    int getMaxCards() {
	return maxCards;
    }

	public String getGameName() {
		return name;
	}

	public double getStake() {
		return stake;
	}







}