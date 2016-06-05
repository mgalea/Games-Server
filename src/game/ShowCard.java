package game;

import java.io.IOException;

/** A standard Card turner for traditional type of games **/

import shared.*;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


class ShowCard extends Thread implements Constants {

    private Game game;
	SocketGate serverSockets=null;
    EventLog cardLog=new EventLog("CARD.log");
    JSONLog jsonLog= new JSONLog("Cards");
    JsonObject deck, card;
    JsonArray datasets;


    ShowCard(Game new_game) {
        super("Card Announcer for Game Event "+new_game.gameID);
        this.game = new_game;
        
        deck=new JsonObject();
        deck.addProperty("GameID", new_game.gameID);
        datasets = new JsonArray();
        

             
        try
        {
        	serverSockets=new SocketGate(Constants.CardListeningGroup);
        }catch (IOException e){
        	e.printStackTrace();
        }            
    }

    public synchronized void run() {

    	int round =1;
        while (game.gameInProgress()) {
	    if (!game.isCheckingForWinner()) {
	        try {

	        		announceCard(game.deck.getNext(),round++);

	        } catch (NoMoreCardsException e) {
	        	game.setGameOver();
	        	deck.add("outcome", datasets);
	            Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
	            System.out.println(gson.toJson(deck));
				jsonLog.saveJSON(gson.toJson(deck));

	        }
	    }
	    try {
		wait(FIVE_SECONDS);
	    } catch (InterruptedException e) { }
        }
    }

	
/* THE DOERS */
    

	
	public void announceCard(Card next,int round) {
		synchronized (getClass()){
			//Messages.info("GAME ID:"+Long.toString(game.gameID)+" CARD: "+next.toString());
			cardLog.save(Utilities.dateTimeStamp()+","+Long.toString(game.gameID)+ ","+round+","+ next.toString() );
			serverSockets.sendGameStatusMessage("ID:"+Long.toString(game.gameID)+" CARD:"+ next.toString());
			
		     // create  card dataset
	        card = new JsonObject();
	        card.addProperty("round", round);
	        card.addProperty("Time", Utilities.dateTimeStamp());
			card.addProperty("value", next.getValue());
			card.addProperty("suit", next.getSuit());
			datasets.add(card);

		}
	}
	public void announceCard(Card next) {
		synchronized (getClass()){
			Messages.info("GAME ID:"+Long.toString(game.gameID)+" CARD: "+next.toString());
			cardLog.save(Utilities.dateTimeStamp()+","+Long.toString(game.gameID)+ ","+ next.toString());
			serverSockets.sendGameStatusMessage("ID:"+Long.toString(game.gameID)+" CARD:"+ next.toString());
		}

	}
}
 