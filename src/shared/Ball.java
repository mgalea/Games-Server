package shared;

/** An object of class card represents one of the Balls in a Drawing Machine 
 * Each Ball must have a number and optionally a Letter or color
 * Can be a simple sequence of numbers or a set of numbers grouped in subsets of SYMBOLS such as 
 * bingo (American): 5 subsets of 15 numbers  non-repetitive (BINGO)
 * Deck of cards:    4 subsets of 13 numbers, repetitive (HSDC)
 * 
 * @author Mario
 *
 */

public class Ball implements Constants  {
    public int number;		// must be greater than 1 and less than 75
    private char letter=' ';
    
    public boolean starBall=false;
    

  public Ball(byte[] b) {
	this(b[0]);
    }

     
    public Ball(int n) {
    	if (n >= MIN_NUM_BALL && n <= MAX_NUM_BALL) {
    	    number = n;
    	} else if (n == GAME_OVER) {
    	    number = GAME_OVER;
    	    letter = ' ';
    	} else {
    	    number = STAR_BALL;
    	    letter = 'S';
    	    starBall=true;
    	}
        }    
    
    public Ball(int n, int range, String Symbols, boolean repetitive) {
    	
    	if(Symbols!=null){
    	char[] symbolArray = Symbols.toCharArray();
		number = GAME_OVER;
	    letter = ' ';
	      		
    	int j=MIN_NUM_BALL;
    	    
    	for(char a : symbolArray){

    		if (n>=j && n<=j+range){
    		    if(repetitive){
    		    	number=n-j+1;
    		    		
    		    }else{ 
    		    	number=n;}
    		    
    		   letter=a;
    		   
    			}
    			j=j+range;
    		}
    	
    	}else{ 
    		number=n;}
    	   	  	   	
    }   	



	public boolean equals(Object obj) {
	if (!(obj instanceof Ball))
	    return false;
	return ((Ball)obj).number == number;
    }

    public int hashCode() {
	return number;
    }

    public byte[] getBytes() {
	byte[] answer = {(byte)number };
	return answer;
    }
    public int getNumber() {
	return number;
    }
    public char getLetter() {
	return letter;
    }

    public String toString() {
	return new StringBuffer().append(letter).append(number).toString();
    }
}
