package shared;



import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;


public class Utilities {
	

	  public static String dateTimeStamp(long millisecs) {

		    SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		    Date resultdate = new Date(millisecs);
		    return date_format.format(resultdate);

	  }
	  
	  public static String dateTimeStamp() {

	    SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	    Date resultdate = new Date();
	    return date_format.format(resultdate);

	  }

	  public static String getTail(String Filename) {
	    	String folder = System.getProperty("user.dir");
		    String filesep = System.getProperty("file.separator");    	
		    String FullFilename = folder+ filesep+Filename;;
		    String lastLine=null;

		  boolean eol=false;
		  
		    RandomAccessFile fileHandler = null;
		    try {
		        fileHandler = new RandomAccessFile( FullFilename, "r" );
		        long fileLength = fileHandler.length() - 1;
		        long filePointer = fileLength;
		        
		        StringBuilder sb = new StringBuilder();
		        
		        do{
		            fileHandler.seek( filePointer );
		            int readByte = fileHandler.readByte();
		            
		                if(!((filePointer == fileLength)|| (filePointer == fileLength-1))) {
		                	eol = (readByte == 0xD || readByte ==0xA) ? true: false;	       

		            sb.append( (char) readByte );
		        }
		        } while (filePointer-- > 0 && !eol);

		        lastLine = sb.reverse().toString();
		        return lastLine;
		    } catch( java.io.FileNotFoundException e ) {
		        
		        return null;
		    } catch( java.io.IOException e ) {
		        e.printStackTrace();
		        return null;
		    } finally {
		        if (fileHandler != null )
		            try {
		                fileHandler.close();
		            } catch (IOException e) {
		                /* ignore */
		            }
		    }
		}

	  // this method is called just after the handler using this

	  // formatter is created

	  public String getHTMLHead(Handler h) {

	      return "<!DOCTYPE html>\n<html>\n<head>\n"
	          + "</head>\n"
	          + "<body>\n";
	    }

	  // this method is called just after the handler using this
	  // formatter is closed

	  public String getHTMLtail(Handler h) {

	    return "</body>\n</html>";

	  }
	  
	   /**
	     * Create a horizontal Box and add a group of evenly spaced
	     * JComponents to it.
	     */
	    public static Box makeEvenlySpacedBox(JComponent compList[]) {
		Box box = Box.createHorizontalBox();
		int numComponents = compList.length;
	        int i = 0;

	        while (i < numComponents) {
	            box.add(Box.createGlue());
	            box.add(compList[i++]);
	        }
	        box.add(Box.createGlue());
		return box;
	    }

	    /**
	     * Add a label-value pair to a container that uses
	     * GridBagLayout.
	     */
	    public static void addParameterRow(Container container,
	                                       JLabel label,
	                                       Component component) {
	        GridBagLayout gridbag = null;
	        try {
	            gridbag = (GridBagLayout)(container.getLayout());
	        } catch (Exception e) {
	            System.err.println("Hey!  You called addRow with"
	                               + " a container that doesn't "
	                               + " use GridBagLayout!");
	            return;
	        }

	        GridBagConstraints c = new GridBagConstraints();
	        c.fill = GridBagConstraints.HORIZONTAL;
	        //c.weighty = 1.0;
	        c.insets = new Insets(0, 5, 0, 5);

	        gridbag.setConstraints(label, c);
	        container.add(label);
	        c.gridwidth = GridBagConstraints.REMAINDER;
	        c.weightx = 1.0;
	        gridbag.setConstraints(component, c);
	        container.add(component);
	    }


	  
} 

