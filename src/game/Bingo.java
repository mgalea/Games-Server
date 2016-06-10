package game;

import java.io.IOException;
import java.util.Vector;

import GUI.BingoStatusWindow;
import shared.*;

 

public class Bingo extends Game implements Runnable {
	Vector<BallRecord> ballRecords;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8505348926003334577L;

	/* GAME DEFUALTS   */

	
	public Bingo(GAME_TYPE type, GameSettings GameSettings) {
		super(type,GameSettings);
	
        switch (type){
        
        case BINGO75:  
        	bagOfBalls = new BagofBalls("BINGO",15,75,false); /* SYMBOLS ARE BINGO with 15 numbers under each symbol, non repeating ranges */
            ballRecords = new Vector<BallRecord>(75);
        	break;
        	
        case BINGO90:  
        	bagOfBalls = new BagofBalls(null,15,90,false); /* SYMBOLS ARE BINGO with 15 numbers under each symbol, non repeating ranges */
        	ballRecords = new Vector<BallRecord>(90);
        	break;
        	        	
		default:
			break;
		
        }
		
		// TODO Auto-generated constructor stub
	}


public synchronized void run() {	    	

try {
	new BingoStatusWindow(this);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

if(gameState==READY) {
	switch(gametype){
	
	
		
	default:	
		ballAnnounce=new BallAnnouncer(this);
		ballAnnounce.start();
		break;
	}
}}




}
