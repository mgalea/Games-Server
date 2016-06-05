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

public class JSONLog {

    private String logFilename;
	private String folder = System.getProperty("user.dir");
    private String filesep = System.getProperty("file.separator");    
       
/* CONSTRUCTORS  */
    public JSONLog (String logFilename) {	   
	
	    this.logFilename = logFilename;
    }

/*  SETTERS  */
/* These methods are declared abstract so they can be applied specific to the application */
    
       
    public void saveJSON(String record) {
    
	    //Messages.info(this.getClass().getName(), "Setting Log file: " + logDescription);
	    //Messages.info(toString());
		     
    	 RandomAccessFile fileHandler = null;
		    try {
		        fileHandler = new RandomAccessFile( folder+filesep+logFilename+".json", "rw" );
		        long fileLength = fileHandler.length();
		        
		        fileHandler.seek(fileLength > 0 ? fileLength-2 : fileLength);
		        Messages.info("file length is 0");
		        
		        record = fileLength> 0 ? ",\r\n" + record +"]}":"{\""+logFilename+"\":\r\n[" + record + "]}";
		        		        
		        fileHandler.write(record.getBytes());
		        
	    
	} catch (java.io.IOException e) {
	    Messages.error("Can't save JSON File. ");
	} finally {
	    if (fileHandler != null) {
		try { fileHandler.close(); } catch (java.io.IOException e) { }
		fileHandler = null;
	    }
	}}
    
    
}





