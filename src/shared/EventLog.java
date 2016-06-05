package shared;


/** A general abstract class to hold the Application settings and save/retrieve settings to file in the Key/Value pair in a Properties file format.  
 * 
 *  updatePropertiesFromSettings()+saveSettings():  Settings --->Properties File
 *  updateSettingsFromProperties()+ getSettings():  Settings<---Properties File
 *  
 *  
 *  Currently used by {@link GameServerParamters} to save Game Server Settings.
 * 
 * **/

import java.io.RandomAccessFile;

public class EventLog {

    private String logFilename;
  
       
/* CONSTRUCTORS  */
    public EventLog (String logFilename) {	   
    	String folder = System.getProperty("user.dir");
	    String filesep = System.getProperty("file.separator");    	
	    this.logFilename = folder+ filesep+logFilename;
    }

/*  SETTERS  */
/* These methods are declared abstract so they can be applied specific to the application */
    
       
    public void save(String record) {
    
	    //Messages.info(this.getClass().getName(), "Setting Log file: " + logDescription);
	    //Messages.info(toString());
		     
    	 RandomAccessFile fileHandler = null;
		    try {
		        fileHandler = new RandomAccessFile( logFilename, "rw" );
		        fileHandler.seek((fileHandler.length()));
		        record=record+"\r\n";
		        fileHandler.write(record.getBytes());
	    
	} catch (java.io.IOException e) {
	    Messages.error("Can't save log File. ");
	} finally {
	    if (fileHandler != null) {
		try { fileHandler.close(); } catch (java.io.IOException e) { }
		fileHandler = null;
	    }
	}}
    

    
}





