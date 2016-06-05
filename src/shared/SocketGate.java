package shared;

import java.io.IOException;
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


    public void sendBall(Ball b) {
        sendBytes(b.getBytes(), ballListeningGroup);
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
    
    
    private void sendBytes(byte[] data, InetAddress group) {
        DatagramPacket packet = new DatagramPacket(data, data.length, group,
                                                   Constants.portNumber);
        try {
            socket.send(packet);
        } catch (java.io.IOException e) {
            Messages.error("Game Server Socket Error.", e);
        }
    }

	
}
