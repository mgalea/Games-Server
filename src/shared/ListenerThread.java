package shared;

import java.net.*;
import java.io.*;

public abstract class ListenerThread extends Thread implements Constants{

    boolean stopListening = false;
    MulticastSocket socket;

    private InetAddress group;
    private String groupString;

    public ListenerThread(String groupString) throws UnknownHostException, IOException
    
    {
	super("Socket Listener:"+groupString);

	this.setGroupString(groupString); 
        this.group = InetAddress.getByName(groupString);
        socket = new MulticastSocket(Constants.portNumber);
	socket.joinGroup(group);
    }

    public void stopListening() throws IOException{
	stopListening = true;
	socket.leaveGroup(group);
	socket.close();
    }

	public String getGroupString() {
		return groupString;
	}

	public void setGroupString(String groupString) {
		this.groupString = groupString;
	}
}
