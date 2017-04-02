/**
 * This is the stack class. Elements are added to the stack object in Last in first out manner.
 * This class uses the linked list implementation.
 * @author adithepnarula
 *
 * @param <E> the type of Item 
 */
public class StackObject<E> implements Stack<E>{
	
	private Node head;

	/**
	 * Push the item onto top of stack
	 * @param item the item to be pushed in stack
	 */
	public void push(E item) {
		if(item != null) {
			Node newNode = new Node(item);
			newNode.setNext(head);
			head = newNode;
		}
	
	}

	/**
	 * remove and then return the top element of the stack
	 * @return returns the top element of the stack
	 */
	public E pop() {
		if (head == null) {
			return null;
		}
		else{
			@SuppressWarnings("unchecked")
			E item = (E) head.getItem();
			head = head.getNext();
			return item;
		}
	}

	/**
	 * this method returns the top element of the stack
	 * @return returns the top element of the stack
	 * 
	 */
	
	public E peek() {
		if (head == null) {
			return null;
		}
		else{
			@SuppressWarnings("unchecked")
			E item = (E) head.getItem();
			return item;
		}}
	

	/**
	 * This method returns the elements of stack in order.
	 * Elements will be printed in order they were added in
	 * @return returns the string of elements to caller
	 */
	public String toString(){
		return toStringRecur(head);
		
	}
	

	/**
	 * Helper method for toString. It recursively calls itself until all elements are printed out.
	 * @param current
	 * @return returns the string of elements to toString method
	 */
	private String toStringRecur(Node current){
		if(head == null) {
			return null;
		}
		if (current.getNext() != null){
			return toStringRecur(current.getNext()) + " " + current.getItem();
		}
		else{
			return ""+current.getItem();
		}
		
	}
	
	/**
	 * Method that checks whether the stack is empty
	 * @return returns true if stack is empty, false otherwise
	 */
	public boolean isEmpty(){
		if (head == null) {
			return true;
		}
		else {
			return false;
		
		}
	}

	
	
	
	
	
}
