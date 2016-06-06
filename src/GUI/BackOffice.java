package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

import game.ControlPane;
import game.GameServerSettings;
import shared.ErrorMessages;

public class BackOffice {
	
    protected static String windowName = "BINGO Server";
    protected static String controlPaneTitle = "Game Default Parameters";
    protected static String statusPaneTitle = "Game Status";
    
    
	private boolean DEBUG=true;
	
	
public BackOffice() throws IOException{
    
    String hostname = null;
	

        JFrame frame = new JFrame(windowName);

        
	if (DEBUG) {
	    System.out.println("Created JFrame.");
	}
	
	Container container = frame.getContentPane();
	
	if (DEBUG) {
	    System.out.println("Got content pane.");
	}
	//b.setHgap(5); //use rigid area instead.
	//b.setVgap(5); //use rigid area instead.
	
	try {
		
	    hostname = InetAddress.getLocalHost().getHostName();
	    System.out.println("HOST:"+hostname);

	} catch (IOException e) {
	    ErrorMessages.error("Problems starting the BINGO server.", e);
	}    
	
/*
	OverallStatusPane statusPane = new OverallStatusPane();
	if (DEBUG) {
	    System.out.println("Created status pane.");
	}
*/
	
	GameServerSettings settings = new GameServerSettings();  
	
	ControlPane controlPane = new ControlPane(hostname, settings);
	
	if (DEBUG) {
	    System.out.println("Created control pane.");
	}
	
	OverallStatusPane statusPane = new OverallStatusPane();
	
	statusPane.setBorder(BorderFactory.createTitledBorder(
				statusPaneTitle));

        frame.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
		    System.exit(0);
		} 
	    });
       
        
	if (DEBUG) {
	    System.out.println("About to call pack.");
	}

	
	container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
	controlPane.setAlignmentX(0.5f);
	container.add(controlPane); 
	
	
    frame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
	    System.exit(0);
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

