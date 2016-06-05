package game;

import java.util.*;

import shared.*;

class BagofBalls implements Constants {
		

    Vector<Ball> balls = new Vector<Ball>(MAX_NUM_BALL-MIN_NUM_BALL+1);
    private volatile Random generator;

    BagofBalls () {
	for (int j = MIN_NUM_BALL; j <= MAX_NUM_BALL; j++)
	    balls.addElement(new Ball(j));
    }
    
    BagofBalls (String Symbols, int range, int maxBall, boolean repetitive) {
    	
    	for (int j = MIN_NUM_BALL; j <= maxBall; j++)
    	    balls.addElement(new Ball(j,range,Symbols,repetitive));
        }


    // PENDING: the compiler wanted this public...why?
    
    public Ball getNext(long gameID) throws NoMoreBallsException {    	
    synchronized(getClass()){ 
    	if (balls.size() > 0) {
    		generator= new Random(System.currentTimeMillis()+gameID);
    		int num = (int)(generator.nextDouble() * balls.size());
    		Ball returnThis = (Ball)balls.elementAt(num);
    		balls.removeElementAt(num);
    		return returnThis;
    	} else {
			throw new NoMoreBallsException();
    	}
    }
    }
    
    public Ball getNext() throws NoMoreBallsException {    	
    synchronized(getClass()){ 
    	if (balls.size() > 0) {
    		generator= new Random(System.currentTimeMillis());
    		int num = (int)(generator.nextDouble() * balls.size());
    		Ball returnThis = (Ball)balls.elementAt(num);
    		balls.removeElementAt(num);
    		return returnThis;
    	} else {
			throw new NoMoreBallsException();
    	}
    }
    }
}
