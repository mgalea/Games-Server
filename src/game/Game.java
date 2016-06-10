package game;

import java.io.IOException;
import java.io.Serializable;

import shared.*;

 

public class Game implements Constants,States,Serializable  {

	private static final long serialVersionUID = 11111L;
	public long gameID=0;
	public int gameState=INITIALIZING;
	public long delay = FIVE_SECONDS;		//DEFAULT delay between ball draws.
	public String name="Nameless";
	public double stake=1.0;
	public long countDown=20;
	public int maxGamePlayers;
	public int maxCards=4;
	
	private volatile boolean gameInProgress=true;  // DECLARED VOLATILE but not needed at thread is synchronized.
	
	SocketGate serverSockets=null;
	EventLog gameLog;
	GameServerSettings serverSettings;
	
	GAME_TYPE gametype;
		
	BallAnnouncer ballAnnounce;
	ShowCard showCard;
	GameSettings gameSettings;		

	BagofBalls bagOfBalls=null;
	Deck deck=null;

	Ball balls;

	
	public Game(GAME_TYPE type,GameSettings GameSettings) {
	
	    /* Get a new GameID and Save it  */
		
		GameEvent gameEvent= new GameEvent();
		
		if(!Start.noMoreGames) // The noMoreGames is set to false when the server is to be turned down
			
		/* Get a new GameID and Save it  */	
		gameID=gameEvent.setNewGameID(); // Give Me a Game Number.
    
		gametype=type;
		delay = GameSettings.getBallDelay();		
		name=GameSettings.getGameName();
		stake=GameSettings.getStake();
		countDown=GameSettings.getCountDown();
		maxGamePlayers=GameSettings.getMaxGamePlayers();
		maxCards=GameSettings.getMaxCards();
		   
				if (gameID>0){
					
		        	Messages.info("GAME ID:"+Long.toString(gameID)+" OPEN");
		        	gameLog=new EventLog(type.toString()+".log");
		        	gameLog.save(Utilities.dateTimeStamp()+ " " + type.toString() + " OPEN ID:"+Long.toString(gameID));
		        	AddGame();

        }    
		gameState = READY;

       
    		try{
    			serverSockets=new SocketGate(Constants.GameListeningGroup);
    		} catch(IOException e){
    			Messages.fatalError("Cannot create sockets.", e);
    		}
    			
    		//serverSockets.sendGameStatusMessage("Starting Game"+gameSettings.getGameName()+" ID:"+gameID);   		
		
	}
	

	 public String toString() {
	        return "["
	        	   + "gameID=" + gameID + ","
	        	   + "name=" + name + ","
	        	   + "stake=" + stake + ","
	        	   + "delay=" + delay + ","
	        	   + "countDown=" + countDown + ","
	        	   + "maxGamePlayers=" + maxGamePlayers + ","
	        	   + "maxCards=" + maxCards
	        	   +"]";
	    }

    
       /* ************* THE SETTERS  ***************/
	 
		
public void setGameOver() {
	Messages.info("GAME OVER ID:"+Long.toString(gameID));
	gameLog.save(Utilities.dateTimeStamp()+ " GAME OVER ID:"+Long.toString(gameID));
	serverSockets.sendGameStatusMessage("GAME OVER ID:"+gameID);
	gameInProgress=false;
}

public boolean StopStartGame() {
	gameInProgress=!gameInProgress;
	return gameInProgress;
}
		/* *********** GETTERS    ***************/  
		
		
		public Object getGameID() {
			return this;
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

		public boolean isCheckingForWinner() {
				//Messages.info("Is there a winner out there?");
				return false;
			}
			
		public boolean gameInProgress() {
				// TODO Auto-generated method stub
				return this.gameInProgress;
			}

		public static int AddGame(){
			
			return ++Start.ConcurrentGames;
		}
		
		public static int RemoveGame(){
			Start.ConcurrentGames=--Start.ConcurrentGames>0?Start.ConcurrentGames:0;			
			return Start.ConcurrentGames;
		}
		



}
