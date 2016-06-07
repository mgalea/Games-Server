package game;

import java.io.IOException;
import java.io.Serializable;

import shared.*;

 

public class Game implements Constants,States,Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public long gameID=0;
	public int gameState=INITIALIZING;
	public long delay = FIVE_SECONDS;		//DEFAULT delay between ball draws.
	public String name="Nameless";
	public double stake=1.0;
	public long countDown=20;
	public int maxGamePlayers=100;
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
			gameID=gameEvent.setNewGameID(); // Give Me a Game Number.
		
	    /* Get a new GameID and Save it  */
		this.gametype=type;
		gameSettings=GameSettings;
		
			   
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
    			
    		serverSockets.sendGameStatusMessage("Starting Game ID:"+gameID);   		
		
	}

	

	 public String toString() {
	        return "["
	        	   + "gameID=" + gameID  + "]";
	    }

    
       /* ************* THE SETTERS  ***************/
	 
		
public void setGameOver() {
	Messages.info("GAME OVER ID:"+Long.toString(gameID));
	gameLog.save(Utilities.dateTimeStamp()+ " GAME OVER ID:"+Long.toString(gameID));
	serverSockets.sendGameStatusMessage("GAME OVER ID:"+gameID);
	this.gameInProgress=false;
}


		/* *********** GETTERS    ***************/  
		
		
		public Object getGameID() {
			return this;
		}

		public boolean isCheckingForWinner() {
				//Messages.info("Is there a winner out there?");
				return false;
			}
			
		public boolean gameInProgress() {
				// TODO Auto-generated method stub
				return this.gameInProgress;
			}


		public long getBallDelay(){
			return gameSettings.getBallDelay();
			
		}

		public static int AddGame(){
			
			return ++Start.ConcurrentGames;
		}
		
		public static int RemoveGame(){
			Start.ConcurrentGames=--Start.ConcurrentGames>0?Start.ConcurrentGames:0;			
			return Start.ConcurrentGames;
		}
		



}
