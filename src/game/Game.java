package game;

import java.io.IOException;

import shared.*;

 

public class Game extends Thread implements Constants,States {
	
	private volatile boolean gameInProgress=true;  // DECLARED VOLATILE but not needed at thread is synchronized.
	long gameID=0;
	public int gameState=INITIALIZING;
	private long delay = HALF_SECOND;		//DEFAULT delay between ball draws.
	
	SocketGate serverSockets=null;
	BagofBalls bagOfBalls=null;
	Deck deck=null;
	EventLog gameLog;
	Ball balls;
	
	
	public Game(GAME_TYPE type) {
	
	    GameEventSettings gameEventSetting;
        gameEventSetting = new GameEventSettings(); 
        
		gameID=gameEventSetting.setNewGameID();  // Give Me a Game Number.
       
		if (gameID>0){
			
        	Messages.info(this.getName(), "OPEN: " +" ID:"+Long.toString(gameID));
        	gameLog=new EventLog(type.toString()+".log");
        	gameLog.save(Utilities.dateTimeStamp()+ " " + type.toString() + " OPEN ID:"+Long.toString(gameID));
			
        switch (type){
        
        case BINGO75:  
        	bagOfBalls = new BagofBalls("BINGO",15,75,false); /* SYMBOLS ARE BINGO with 15 numbers under each symbol, non repeating ranges */
        	break;
        	
        case BINGO90:  
        	bagOfBalls = new BagofBalls(null,15,90,false); /* SYMBOLS ARE BINGO with 15 numbers under each symbol, non repeating ranges */	    
        	break;	    
        	
        case LOTTO590:
           	bagOfBalls = new BagofBalls(null,18,90,false); /* SYMBOLS ARE BINGO with 15 numbers under each symbol, non repeating ranges */
        	break;
        	
        case LOTTO649:
           	bagOfBalls = new BagofBalls(null,10,49,false); /* SYMBOLS ARE BINGO with 15 numbers under each symbol, non repeating ranges */
           	break;
           	
        case DECK:
    	    deck = new Deck(); /* SYMBOLS ARE Hearts Spades Clubs and Diamonds with 13 numbers under each symbol, non repeating ranges */
    	    break;

		default:
			break;

        }       

		try{
			serverSockets=new SocketGate(Constants.GameListeningGroup);
		} catch(IOException e){
			Messages.fatalError("Cannot create sockets.", e);
		}
			
		serverSockets.sendGameStatusMessage("Starting Game ID:"+gameID);
		
		serverSockets=null;
   
		gameState = READY;
		
		}/*READY FOR GAMING */
          
	}


    
/* ************* THE GETTERS  ***************/
		
		public Object getGameParameters() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public long getBallDelay() {
			// TODO Auto-generated method stub
			return delay;
		}
		
		public long setBallDelay() {
			// TODO Auto-generated method stub
			return delay;
		}
		
		public boolean isCheckingForWinner() {
			//Messages.info("Is there a winner out there?");
			return false;
		}
		
		public boolean gameInProgress() {
			// TODO Auto-generated method stub
			return this.gameInProgress;
		}
		
/* *************** THE SETTERS  ****************/
		
		public void setGameOver() {
			Messages.info("GAME OVER ID:"+Long.toString(gameID));
	  		gameLog.save(Utilities.dateTimeStamp()+ " GAME OVER ID:"+Long.toString(gameID));
			this.gameInProgress=false;
			
		}



}
