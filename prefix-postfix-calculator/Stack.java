/**
 * This is the interface for any stack objects
 * @author adithepnarula
 *
 * @param <E> E is the type of item
 */
public interface Stack <E> {
	
	/**
	 * Push the item onto top of stack
	 * @param item the item to be pushed in stack
	 */
	public void push (E item);
	
	/**
	 * remove and then return the top element of the stack
	 * @return returns the top element of the stack
	 */
	public E pop();
	
	

	/**
	 * this method returns the top element of the stack
	 * @return returns the top element of the stack
	 * 
	 */
	public E peek();
	

	/**
	 * This method returns the elements of stack in order.
	 * Elements will be printed in order they were added in
	 * @return returns the string of elements to caller
	 */
	public String toString();
	



}
