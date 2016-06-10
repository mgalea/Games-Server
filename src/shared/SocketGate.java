package shared;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.*;

import game.PlayerRecord;

public class SocketGate extends ListenerThread {

   

	private InetAddress ballListeningGroup;
    private InetAddress playerListeningGroup;
    private InetAddress gameListeningGroup;
    private InetAddress chatListeningGroup;
    private InetAddress cardListeningGroup;

    private MulticastSocket socket;

    
    public SocketGate(String groupString) throws UnknownHostException, IOException {
		super(groupString);
        socket = new MulticastSocket(Constants.portNumber);
        ballListeningGroup = InetAddress.getByName(Constants.BallListeningGroup);
        playerListeningGroup = InetAddress.getByName(Constants.PlayerListeningGroup);
        gameListeningGroup = InetAddress.getByName(Constants.GameListeningGroup);
        chatListeningGroup = InetAddress.getByName(Constants.ChatListeningGroup);
        cardListeningGroup = InetAddress.getByName(Constants.CardListeningGroup);
	}


    public void sendByteRecord(byte[] record) {
        sendBytes(record, gameListeningGroup);
    }
    
    public void sendCard(Card c) {
        sendBytes(c.getBytes(), ballListeningGroup);
    }
    
    public void sendCardMessage(String msg) {
        sendBytes(msg.getBytes(), cardListeningGroup);
    }

    public void sendPlayerStatusMessage(PlayerRecord p) {
        sendBytes(p.getBytes(), playerListeningGroup);
    }

    public void sendGameStatusMessage(String msg) {
        sendBytes(msg.getBytes(), gameListeningGroup);
    }

    public void sendChatMessage(String msg){
    	sendBytes(msg.getBytes(),chatListeningGroup);
    }
    
    
    public void sendBytes(byte[] data, InetAddress group) {
        DatagramPacket packet = new DatagramPacket(data, data.length, group,
                                                   Constants.portNumber);
        try {
            socket.send(packet);
        } catch (java.io.IOException e) {
            Messages.error("Game Server Socket Error.", e);
        }
    }

    
	ByteArrayOutputStream bos = null;
	ObjectOutput out = null;

		public byte[] objtoBytes(Object obj){
	   
  byte[] classbytes=null;
  bos=new ByteArrayOutputStream();
  
	try {
	  try {
		out = new ObjectOutputStream(bos);
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}   
	  out.writeObject(obj);

		classbytes = bos.toByteArray();
		return classbytes;
	  
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		  try {
			    if (out != null) {
			      out.close();
			    }
			  } catch (IOException ex) {
			    // ignore close exception
			  }
	}
	return classbytes;
   }

	
}
