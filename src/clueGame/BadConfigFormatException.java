package clueGame;
/*
 * Jordan Newport
 * Nicholas Carnival
 */
public class BadConfigFormatException extends Exception {
	public BadConfigFormatException() {
		super("Bad Config Format Exception Thrown");
	}

	public BadConfigFormatException(String message)
	  {
	    super(message);
	  }
}
