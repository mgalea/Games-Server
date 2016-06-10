package game;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import shared.*;


/** A standard Bingo/lottery Ball announcer for traditional type of games **/


class BallAnnouncer extends Thread  {

    private Bingo game;    
	SocketGate serverSockets=null;
    EventLog ballLog=new EventLog("ball.log");
    private long delay;
    private byte round;
    BallRecord ballRecord;
	
    //BallRandomizer bagOfBalls = new BallRandomizer();

    BallAnnouncer(Bingo new_game) {
        super("Ball Announcer for Game Event "+new_game.gameID);
        this.game = new_game;
        
        
        delay=game.getBallDelay();
        
        try
        {
        	serverSockets=new SocketGate(Constants.BallListeningGroup);
        }catch (IOException e){
        	e.printStackTrace();
        }            
    }

    public synchronized void run() {

    	round =1;
        while (game.gameInProgress()) {
	    if (!game.isCheckingForWinner()) {
	        try {

	        		announceBall(game.bagOfBalls.getNext(),round++);

	        } catch (NoMoreBallsException e) {
	        	game.setGameOver(); 
	        	Game.RemoveGame();
	        }
	    }
	    
	    try {
		wait(delay);
		
	    } catch (InterruptedException e) { }
        }
    }

     
	
/* THE DOERS */
	
	public void announceBall(Ball ball, byte Round) {
		synchronized (getClass()){
			ballRecord=new BallRecord(game.gameID, Round, ball);
			Messages.info("GAME ID:"+Long.toString(game.gameID)+" BALL: "+ball.getLetter()+ball.getNumber());
			ballLog.save(Utilities.dateTimeStamp()+","+Long.toString(game.gameID)+ ","+Round+"," + ball.getLetter() + ball.getNumber() );
			//serverSockets.sendGameStatusMessage("ID:"+Long.toString(game.gameID)+" BALL:"+ next.getNumber());			
			serverSockets.sendByteRecord(serverSockets.objtoBytes(ballRecord));
		
		}
			}
	


	public void announceBall(Ball next) {
		synchronized (getClass()){
			Messages.info("GAME ID:"+Long.toString(game.gameID)+" BALL: "+next.getLetter()+next.getNumber());
			ballLog.save(Utilities.dateTimeStamp()+","+Long.toString(game.gameID)+ ","+ next.getLetter()+next.getNumber() );
			serverSockets.sendGameStatusMessage("ID:"+Long.toString(game.gameID)+" BALL:"+ next.getNumber());
		}

	}
	

}
 