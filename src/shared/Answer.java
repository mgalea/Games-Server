
package shared;

public class Answer implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7225771747825859384L;
	public boolean didIWin;
    public String message;

    public Answer(boolean didIWin, String message) {
        this.didIWin = didIWin;
        this.message = message;
    }
}
