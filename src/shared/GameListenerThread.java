package shared;

import java.net.*;
import java.util.Arrays;
import java.io.*;

public class GameListenerThread extends ListenerThread {

    private GameListener notifyee;

    public GameListenerThread(GameListener notifyee) throws IOException {

	super(Constants.GameListeningGroup);
	this.notifyee = notifyee;
    }

    public synchronized void run() {
    
   
	DatagramPacket packet;
    byte[] buf = new byte[256];
    byte[] rcvd =null , oldbuf = null;
    
        packet = new DatagramPacket(buf, 256);
	
        while (stopListening == false) {
        
	    try {
                socket.receive(packet);
                  
	    		rcvd = packet.getData();
                String dataString=null;

       if(!Arrays.equals(oldbuf,rcvd)){
    	   Object obj = bytestoObj(rcvd);
    	   
		if (obj instanceof BallRecord  ){
			
			System.out.println("Test: "+Arrays.equals(oldbuf,rcvd));
			dataString = new String(obj.toString());
			}
		else 
		{
			dataString = new String(rcvd);}
		
       }else{oldbuf=rcvd;}
       
       
		notifyee.updateStatus(dataString);
		
	    } catch (IOException e) {
		    // PENDING: what goes in here?
	    }
        }
    }
    
    

}


