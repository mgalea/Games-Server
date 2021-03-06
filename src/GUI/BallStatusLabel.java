package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;

import javax.swing.*;

import shared.*;
import game.*;


// Used by the game keeper to show its current status.
// Not thread-safe, so call updateStatus from the AWT thread. XXX
public class BallStatusLabel extends JLabel implements GameListener {
//public class GameStatusLabel extends Label implements GameListener {
    static protected String initialStatusString = 
	    "Watch this space for game status information.";

    public BallStatusLabel() {
	super(initialStatusString, LEFT);

        try {
            new GameListenerThread(this).start();
        } catch (java.io.IOException e) {
	    //XXX: what to do?
	    System.err.println("IOException when starting GameListenerThread.");
        }
    }

    public void updateStatus(String message) {
	setText("OK:"+message);
    }

	public void updateStatus(byte[] message) {
		// TODO Auto-generated method stub
		
	}



		


}
