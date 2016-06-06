package GUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import game.Game;
import shared.ErrorMessages;

public class GameStatusWindow {
	
    protected static String windowName = "BINGO Game: ";
    protected static String controlPaneTitle = "Game Parameters";
    protected static String statusPaneTitle = "Game Status";
    
	
	private boolean DEBUG=true;
	
	
public GameStatusWindow(Game game) throws IOException{
    

    JFrame frame = new JFrame(windowName+game.gameID);
	if (DEBUG) {
	    System.out.println("Created Game Window.");
	}
	
	Container container = frame.getContentPane();
	
	if (DEBUG) {
	    System.out.println("Got GAME content pane.");
	}
	//b.setHgap(5); //use rigid area instead.
	//b.setVgap(5); //use rigid area instead.
	
	
	
	GameParametersPane gameParamtersPane = new GameParametersPane(game);
	
	if (DEBUG) {
	    System.out.println("Created game paramters  pane.");
	}
	
	OverallStatusPane statusPane = new OverallStatusPane();
	
	statusPane.setBorder(BorderFactory.createTitledBorder(
				statusPaneTitle));

        frame.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
		    frame.setVisible(false); //you can't see me!
		    frame.dispose(); //Destroy the JFrame object
		} 
	    });
       
        
	if (DEBUG) {
	    System.out.println("About to call pack.");
	}

	
	container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
	gameParamtersPane.setAlignmentX(0.5f);
	container.add(gameParamtersPane); 
	
	
    frame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
	    frame.setVisible(false);
	    frame.dispose();
	} 
    });
	
	statusPane.setAlignmentX(0.5f);

	//Does the following work?  We want the table to be able
	//to take up as much space as it can.*/

	statusPane.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
	container.add(statusPane);
	
        frame.pack(); 
        
	
	if (DEBUG) {
	    System.out.println("About to call show.");
	}
        frame.setVisible(true);
	if (DEBUG) {
	    System.out.println("At end of BINGO.main.");
	}
    }
}

