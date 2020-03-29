package connectFour9000;

public class InvalidActionException extends Exception {
	 public InvalidActionException() { super(); }
	  public InvalidActionException(String message) { super(message); }
	  public InvalidActionException(String message, Throwable cause) { super(message, cause); }
	  public InvalidActionException(Throwable cause) { super(cause); }
}
