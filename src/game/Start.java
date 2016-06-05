package game;

import java.io.IOException;
import java.util.Scanner;

import shared.*;

public class Start implements Constants {
	
/* ******* GAME MANAGER PROPERTIES  *******/	
	
	GameServerSettings gameServerSettings = new GameServerSettings();
	
	static GameEngineLauncher bingoLauncher,lottoLauncher,cardLauncher;
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
			
			for (int i = 0; i<1 ; i++){
				
				cardLauncher=new GameEngineLauncher(GAME_TYPE.DECK,i);			
				cardLauncher.start();			
			}	
			
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

}
