package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;


import game.*;


public class BingoStatusWindow {
	
    protected static String windowName = "BINGO: ";
    protected static String controlPaneTitle = "Game Parameters";
    protected static String statusPaneTitle = "Game Status";
    	
	private boolean DEBUG=true;
	
	
public BingoStatusWindow(Bingo bingo) throws IOException{
    
    JFrame frame = new JFrame(windowName+bingo.getGameName());
	if (DEBUG) {
	    System.out.println("Created Game Window.");
	}
	
	Container container = frame.getContentPane();
	
	if (DEBUG) {
	    System.out.println("Got GAME content pane.");
	}
	//b.setHgap(5); //use rigid area instead.
	//b.setVgap(5); //use rigid area instead.
	
	
	
	GameParametersPane gameParamtersPane = new GameParametersPane(bingo);
	
	if (DEBUG) {
	    System.out.println("Created game paramters  pane."+bingo.toString());
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

