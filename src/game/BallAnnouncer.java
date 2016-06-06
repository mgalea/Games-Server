package game;

import java.io.IOException;


import shared.*;



/** A standard Bingo/lottery Ball announcer for traditional type of games **/


class BallAnnouncer extends Thread  {

    private Game game;
	SocketGate serverSockets=null;
    EventLog ballLog=new EventLog("ball.log");

	
    //BallRandomizer bagOfBalls = new BallRandomizer();

    BallAnnouncer(Game new_game) {
        super("Ball Announcer for Game Event "+new_game.gameID);
        this.game = new_game;
        
        try
        {
        	serverSockets=new SocketGate(Constants.BallListeningGroup);
        }catch (IOException e){
        	e.printStackTrace();
        }            
    }

    public synchronized void run() {

    	int round =1;
        while (game.gameInProgress()) {
	    if (!game.isCheckingForWinner()) {
	        try {

	        		announceBall(game.bagOfBalls.getNext(),round++);

	        } catch (NoMoreBallsException e) {
	        	game.setGameOver(); 
	        	Start.RemoveGame();
	        }
	    }
	    try {
		wait(game.getBallDelay());
	    } catch (InterruptedException e) { }
        }
    }

	
/* THE DOERS */
	
	public void announceBall(Ball next, int Round) {
		synchronized (getClass()){
			Messages.info("GAME ID:"+Long.toString(game.gameID)+" BALL: "+next.getLetter()+next.getNumber());
			ballLog.save(Utilities.dateTimeStamp()+","+Long.toString(game.gameID)+ ","+Round+","+ next.getLetter()+next.getNumber() );
			serverSockets.sendGameStatusMessage("ID:"+Long.toString(game.gameID)+" BALL:"+ next.getNumber());
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
 