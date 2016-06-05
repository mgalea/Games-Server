package game;

import java.util.*;

import shared.*;

class Deck implements Constants {
		
    Vector<Card> cards = new Vector<Card>(52);


    Deck () {
	for (byte j = 1; j <= 52; j++)
	    cards.addElement(new Card(j));
    }
    
  
    // PENDING: the compiler wanted this public...why?
    
    public Card getNext() throws NoMoreCardsException { 
    	
       Random generator;
    synchronized(getClass()){ 
    	if (cards.size() > 0) {
    		generator= new Random(System.currentTimeMillis());
    		int num = (int)(generator.nextDouble() * cards.size());
    		Card returnThis = (Card)cards.elementAt(num);
    		cards.removeElementAt(num);
    		return returnThis;
    	} else {
			throw new NoMoreCardsException();
    	}
    }
    }
}
