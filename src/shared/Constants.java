package shared;

public interface Constants {

	// various constants for the game

	
    public static final long ONE_SECOND = 1000;
    public static final long HALF_SECOND = ONE_SECOND/2;
    public static final long TWO_SECONDS = ONE_SECOND * 2;
    public static final long FIVE_SECONDS = ONE_SECOND * 5;
    public static final long SEVEN_SECONDS = ONE_SECOND * 7;
    public static final long TEN_SECONDS = ONE_SECOND * 10;
    
    public static final long TWENTY_SECONDS = ONE_SECOND * 20;
    public static final long ONE_MINUTE = ONE_SECOND * 60;
    public static final long FIVE_MINUTES = ONE_MINUTE * 5;
    
    public static final int FREE_SPACE = -1;
    public static final int ACE_BALL = -1;
    public static final int STAR_BALL = 0;
    
    public static final int GAME_OVER = -2;
    
    public static int MIN_NUM_BALL = 1;
    public static int MAX_NUM_BALL = 75  ;

    public static final int MAX_WOLF_CRIES = 3;
    
    static double MAX_STAKE = 1.00;
    static String DEFAULT_NAME = "SUPER MARIO" ;
    
    public static final int MAX_GAMES = 100;
    public final int MAX_PLAYERS = 100;
    public final int MAX_CARDS = 4;
    
    public final boolean DEBUG=true;
    

	// this is the port number to which all information
	// is sent. this is an arbitrarily chosen number
	// based on my kid's birthday (5/25/96)
    public static final int portNumber = 52596;

	// when a message is sent to a multicast socket, it's
	// sent to a particular group on that socket.
	// a group is identified by an InetAddress which can be
	// specified by a string of the form %d.%d.%d.%d
	//
	// the bingo game sends information to three groups.
	// these inetaddresses are arbitrarily chosen based on nothing
    public static final String BallListeningGroup = "230.0.0.1";
    public static final String CardListeningGroup = "230.0.0.2";
    public static final String GameListeningGroup = "230.0.0.3";
    public static final String PlayerListeningGroup = "230.0.0.4";
    public static final String ChatListeningGroup = "230.0.0.5";
    

}
