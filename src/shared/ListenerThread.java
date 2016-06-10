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

	
	public Object bytestoObj(byte [] rcvd){
			ByteArrayInputStream bis = new ByteArrayInputStream(rcvd);
			
				ObjectInput in = null;
				Object o = null;
				
				try {
					in = new ObjectInputStream(bis);
					o = in.readObject();					
					System.out.println(o.toString());
						
				} catch (IOException e) {
					Messages.error("Cannot Read Object for Listener Thread.");
	
					} catch (ClassNotFoundException e) {
						Messages.error("This is not an Object.");
					} 
					finally {
						try {
							bis.close();
							} catch (IOException ex) {
									// ignore close exception
							}
						try {
							if (in != null) {
								in.close();
							}
						} catch (IOException ex) {
								// ignore close exception
						}
					}
				return o;
			}
}
