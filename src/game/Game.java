package game;

import java.io.IOException;

import shared.*;

 

public class Game extends Thread implements Constants,States {
	
	private volatile boolean gameInProgress=true;  // DECLARED VOLATILE but not needed at thread is synchronized.
	
	public long gameID=0;
	public int gameState=INITIALIZING;
	public long delay = FIVE_SECONDS;		//DEFAULT delay between ball draws.
	public String name="No Name";
	public double stake=1.0;
	public long countDown=20;
	public int maxPlayers=100;
	public int maxCards=4;
		
	SocketGate serverSockets=null;
	BagofBalls bagOfBalls=null;
	Deck deck=null;
	EventLog gameLog;
	Ball balls;
	
	public Game(GAME_TYPE type) {
	
	    GameEventSettings gameEventSetting= new GameEventSettings(); 

	    if(!Start.noMoreGames){                   // The noMoreGames is set to false when the server is to be turned down
		gameID=gameEventSetting.setNewGameID();  // Give Me a Game Number.
		Start.AddGame();
	    }
	    
		if (gameID>0){
			
        	Messages.info(this.getName(), "OPEN: " +" ID:"+Long.toString(gameID));
        	gameLog=new EventLog(type.toString()+".log");
        	gameLog.save(Utilities.dateTimeStamp()+ " " + type.toString() + " OPEN ID:"+Long.toString(gameID));
        	Start.AddGame();
			
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
		
   
		gameState = READY;
		
		}/*READY FOR GAMING */
          
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

    
/* ************* THE GETTERS  ***************/

		
		
	     
		public void setGameName(String nameField) {
			this.name = nameField;
			
		}
		public void setStake(double stakeField) {
			this.stake=stakeField;

		}
		
		public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;

	    }
	    
		public void setBallDelay(long delay) {
		this.delay = delay;

	    }
	    
		public void setMaxCards(int maxCards) {
		this.maxCards = maxCards;

	    }
	    
		public void setCountDown(long countDown) {
		this.countDown = countDown;

	    }

		
				
		public void setGameOver() {
			Messages.info("GAME OVER ID:"+Long.toString(gameID));
	  		gameLog.save(Utilities.dateTimeStamp()+ " GAME OVER ID:"+Long.toString(gameID));
	  		serverSockets.sendGameStatusMessage("GAME OVER ID:"+gameID);
	  		this.gameInProgress=false;
			
		}



		/* *********** GETTERS    ***************/  
		
		
		public Object getGameParameters() {
			return this;
		}

		public String getGameName() {
			return name;
		}
		    
		public long getBallDelay() {
				return delay;
			}
		    		    		    
		public long getCountDown() {
			return countDown;
		    }

		public int getMaxPlayers() {
			return maxPlayers;
		    }


		public int getMaxCards() {
			return maxCards;
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





}
