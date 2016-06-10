package shared;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class BallRecord implements Record {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final long timeStamp;
	long gameID;
	byte round;
	int number;
	char letter;
	
	
	public BallRecord(long GameID,byte Round, Ball Ball){
		this.gameID=GameID;
		this.timeStamp=System.currentTimeMillis();
		this.number=Ball.getNumber();
		this.letter=Ball.getLetter();
		this.round=Round;
			}
	
	public BallRecord() {
		// TODO Auto-generated constructor stub
		this.timeStamp=System.currentTimeMillis();
	}

	public boolean equals(Object obj) {
	if (!(obj instanceof BallRecord)){
	    return false;
    }else return true;
	}

	public byte[] getBytes() {
		// TODO CHECK
		byte[] answer = {(byte)number };
		return answer;
	}

	public byte[] toBytes() {
		// TODO CHECK - this wrong as Long is not CAST properly;
		byte[] answer = {'B',(byte)timeStamp, (byte)gameID, (byte)round, (byte)number, (byte)letter};
		return answer;
	}

	 public String toString() {
	        return "["
	        	   + "gameID=" + gameID + ","
	        	   + "timeStamp=" + timeStamp + ","
	        	   + "round=" + round + ","
	        	   + "number=" + number + ","
	        	   + "letter=" + letter
	        	   +"]";
	    }
}
