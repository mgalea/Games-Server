package shared;

import java.io.Serializable;
import java.io.Writer;

public interface Record extends Serializable{
	
	public boolean equals(Object obj);
	
	public byte[] getBytes();
	
	public byte[] toBytes();
	
	public String toString();

}
