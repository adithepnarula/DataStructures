/**
 * This class is the blueprint for postfixexception objects.
 * @author adithepnarula
 *
 */
public class PostFixException extends Exception{

	/**
	 * No-arg constructor to create PostFixException exception object
	 */
	public PostFixException() {
		
	}
	
	/**
	 * Constructor that accepts a string and create PostFixException exception object
	 * @param message message that determines what the exception is
	 */
	public PostFixException(String message){
		super(message);
	}
	
}
