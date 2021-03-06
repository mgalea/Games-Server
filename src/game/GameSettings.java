package game;

/**
 * This class extends the Properties JAVA class to create an application specific properties file
 */

import java.util.Properties;

import shared.Settings;
import shared.Constants;


public class GameSettings extends Settings implements Constants {


/* ********** DEFAULT  VALUES  ***************** */
    private long delay = FIVE_SECONDS;
    private long countDown = ONE_MINUTE;
    private int maxGamePlayers = MAX_PLAYERS;
    private int maxCards = MAX_CARDS;
    private double stake=MAX_STAKE;
    private String name=DEFAULT_NAME;    
    public long gameNumber;

/* *********** PROPERTY KEYS    ************** */    
    private String gameName = "name";
    private String stakeName= "stake";    
    private String delayName = "ball_delay";
    private String countDownName = "count_down";
    private String maxGamePlayersName = "max_players";
    private String maxCardsName = "max_cards";
    private String lastGameNumber = "last_game_number";


    
    
/* *********** CONSTRUCTORS    ************** */   
    public GameSettings() {
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
	defaults.put(maxGamePlayersName, new Integer(maxGamePlayers).toString());
	defaults.put(maxCardsName, new Integer(maxCards).toString());
	defaults.put(lastGameNumber, Long.toString(gameNumber));
    }

    protected void updateSettingsFromProperties() {
	try {
		name = properties.getProperty(gameName);
		stake = Double.parseDouble(properties.getProperty(stakeName));
	    delay = Long.parseLong(properties.getProperty(delayName));
	    countDown = Long.parseLong(properties.getProperty(countDownName));
	    maxGamePlayers = Integer.parseInt(properties.getProperty(maxGamePlayersName));
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
	properties.put(maxGamePlayersName, new Integer(maxGamePlayers).toString());
	
    }

    public String toString() {
        return "["
        		+ "name=" + name + ","
        	   + "stake=" + stake + ","
               + "delay=" + delay + ","
               + "countDown=" + countDown + ","
               + "maxGamePlayers=" + maxGamePlayers + ","
               + "maxCards=" + maxCards + "]";
    }

/* *********** SETTERS    ***************/    
    public void setDelay(long delay) {
	this.delay = delay;
	saveSettings();
    }
    
    public void setMaxGamePlayers(int maxPlayers) {
	this.maxGamePlayers = maxPlayers;
	saveSettings();
    }
    
    public void setMaxCards(int maxCards) {
	this.maxCards = maxCards;
	saveSettings();
    }
    
    public void setCountDown(long countDown) {
	this.countDown = countDown;
	saveSettings();
    }
/*    
    public void setGameNumber(long gameNumber) {
	this.gameNumber = gameNumber;
	saveSettings();
    }
 */
    
	public void setGameName(String nameField) {
		this.name = nameField;
		saveSettings();
		
	}
	public void setStake(double stakeField) {
		this.stake=stakeField;
		saveSettings();
	}
    
/* *********** GETTERS ************** */     
    public long getBallDelay() {
	return delay;
    }
    
    public long getCountDown() {
	return countDown;
    }

    public int getMaxGamePlayers() {
	return maxGamePlayers;
    }


    public int getMaxCards() {
	return maxCards;
    }

	public String getGameName() {
		return name;
	}

	public double getStake() {
		return stake;
	}




}
