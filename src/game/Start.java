package game;

import java.io.IOException;
import java.util.Scanner;

import GUI.*;
import shared.*;

public class Start implements Constants {
	
	public static boolean canWeHaveGames=false;
	public static boolean noMoreGames=false;
	public static int ConcurrentGames=0;
	
/* ******* GAME MANAGER PROPERTIES  *******/	
	
	/* GAME SERVER SETTINGS esrablish global setting for the game server. These are
	 * 
	 * maxGames:    The Maximum Number of Games that the server can handle concurrently.
	 * maxPlayers: 	The Maximum Number of Players allowed per Server
	 * gameNumber:	The last gameID issued.
	 * 
	 */
	
	static GameServerSettings gameServerSettings = new GameServerSettings();
		
	
	static Scanner scan;
	
	
		public static void main(String args[]) throws IOException {
						
			Messages.info (gameServerSettings.toString());
			System.out.print(Utilities.getTail("game.events")+"\r\n");
			

			new BackOffice();

		    
		}
		
		public static int getRunningGames(){		
			return ConcurrentGames;
		}
		
		public static void SetNoMoreGames() {
			noMoreGames=true;
			
		}
		
		public static boolean NoMoreGames() {
			return noMoreGames;
			
		}

}
