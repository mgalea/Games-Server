package game;

import shared.*;
import shared.GAME_TYPE;

/** Test Class to launch a game every so many seconds and take it through its life cycle. **/

public class GameEngineLauncher extends Thread implements Constants,States{

	Game game;
	GAME_TYPE gametype;
	BallAnnouncer ballAnnounce;
	ShowCard showCard;

	    GameEngineLauncher(GAME_TYPE game) {
	        super(game.toString());
	        this.gametype=game;
	    }
	    
	    GameEngineLauncher(GAME_TYPE game,int i) {
	        super(game.toString()+": "+Integer.toString(i));
	        this.gametype=game;
	     
	    }
	    	    
	    public synchronized void run() {	    	

	    	for(;;){
	    		
	    		switch(gametype){
	    		
	    		case BINGO90:
		    		game=new Game(GAME_TYPE.BINGO90);
		    		break;
		    		
	    		case BINGO75:
		    		game=new Game(GAME_TYPE.BINGO75);
		    		break;
		    		
	    		case LOTTO590:
	    			game=new Game(GAME_TYPE.LOTTO590);
		    		break;
		    		
	    		case LOTTO649:
	    			game=new Game(GAME_TYPE.LOTTO649);
		    		break;

	    		case DECK:
	    			game=new Game(GAME_TYPE.DECK);
		    		break;
	    		
				default:
					break;
	    		
	    		}
    		
	    		if(game.gameState==READY) {
	    			switch(gametype){
		    		
		    		case DECK:
		    			showCard=new ShowCard(game);
		    			showCard.start();
			    		break;
			    		
			    	default:	
		    			ballAnnounce=new BallAnnouncer(game);
		    			ballAnnounce.start();
		    			break;
	    			}
	    			

	    			
	    		}else game=null;
	    		
		
	    	//System.out.println(gameEventSetting.toString());
	    	
		    try {
			wait(SEVEN_SECONDS);
		    } catch (InterruptedException e) { }
	    	}
	    }
	    }

	
