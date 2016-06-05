package shared;

public class Messages {
	
    public static boolean DEBUG = false;
    public static boolean VERBOSE = true;

    //XXX Standard Message
    	public static void info(String message) {
    	if(VERBOSE){
    		System.out.print(Utilities.dateTimeStamp()+ " INFO ");
    		System.out.println(message);
        }
    	}
    	
        //Standard Message with Class Name
    	public static void info(String Class, String message) {
    	if (VERBOSE){	
    		System.out.print(Utilities.dateTimeStamp()+ " INFO "+Class+" ");
    		System.out.println(message);
    	}
        }
   
    
    public static void error(String message) {
	//XXX Do we really want to print non-fatal messages to stderr?
	System.err.print(Utilities.dateTimeStamp()+ " ERROR ");
	System.err.println(message);
    }
   
   
    public static void error(String message, Exception e) {
	if (DEBUG) {
	    if (e != null) {
	        e.printStackTrace();
	    }
	}
	
	//XXX Do we really want to print non-fatal messages to stderr?
	System.err.print(" ERROR ");
	System.err.println(message);
    }
    
    public static void fatalError(String message, Exception e) {
	if (DEBUG) {
	    if (e != null) {
	        e.printStackTrace();
	    }
	}
	
	System.err.print(Utilities.dateTimeStamp()+ " FATAL ");
	System.err.println(message);
	System.err.println("Exiting.....");
	System.exit(-1);
    }
}
