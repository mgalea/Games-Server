package game;

import java.io.IOException;
import java.util.Scanner;

import shared.*;

public class Start implements Constants {
	
	public static boolean canWeHaveGames=false;
	public static int RunningGames=0;
	public static boolean noMoreGames=true;
	
/* ******* GAME MANAGER PROPERTIES  *******/	
	
	GameServerSettings gameServerSettings = new GameServerSettings();
	

	static Scanner scan;
	
	
		public static void main(String args[]) throws IOException {
			
			System.out.print(Utilities.getTail("game.events")+"\r\n");
			
/*			
			for (int i = 0; i<1 ; i++){
				
				bingoLauncher=new GameEngineLauncher(GAME_TYPE.BINGO90,i);			
				bingoLauncher.start();			
			}				
		
			for (int i = 0; i<1 ; i++){
				
				lottoLauncher=new GameEngineLauncher(GAME_TYPE.LOTTO649,i);			
				lottoLauncher.start();			
			}	
*/
			BackOffice backOffice = new BackOffice();
							
			
			noMoreGames=false;
			
			try{
		    scan = new Scanner(System.in);
		    String text = scan.nextLine();
		    System.out.println(text);
		    int num = scan.nextInt();

		    System.out.println(num);
			}finally {
				scan.close();
			}			
		    
		}
		
		public static int AddGame(){
			return ++RunningGames;
		}
		
		public static int RemoveGame(){
			RunningGames=--RunningGames>0?RunningGames:0;
			
			return RunningGames;
		}
		
		public static int getRunningGames(){
			
			return RunningGames;
		}

		public static void SetNoMoreGames() {
			noMoreGames=true;
			
		}
		
		public static boolean NoMoreGames() {
			return noMoreGames;
			
		}

}
