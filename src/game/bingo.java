package game;

import java.io.IOException;

import GUI.GameStatusWindow;
import shared.*;

 

public class bingo extends Game implements Runnable {
	
	private volatile boolean gameInProgress=true;  // DECLARED VOLATILE but not needed at thread is synchronized.
	
	
	/* GAME DEFUALTS   */

	
	public bingo(GAME_TYPE type, GameSettings GameSettings) {
		super(type,GameSettings);
	
        switch (type){
        
        case BINGO75:  
        	bagOfBalls = new BagofBalls("BINGO",15,75,false); /* SYMBOLS ARE BINGO with 15 numbers under each symbol, non repeating ranges */
        	break;
        	
        case BINGO90:  
        	bagOfBalls = new BagofBalls(null,15,90,false); /* SYMBOLS ARE BINGO with 15 numbers under each symbol, non repeating ranges */	    
        	Messages.info(this.getClass().getName()+" GAME ID:"+Long.toString(gameID)+" OPEN");
        	break;
        	
        	
		default:
			break;
		
        }
		
		// TODO Auto-generated constructor stub
	}


public synchronized void run() {	    	

try {
	GameStatusWindow gameWindow=new GameStatusWindow(gameSettings);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

if(gameState==READY) {
	switch(gametype){
	
	case DECK:
		showCard=new ShowCard(this);
		showCard.start();
		break;
		
		
	default:	
		ballAnnounce=new BallAnnouncer(this);
		ballAnnounce.start();
		break;
	}
}}




}
