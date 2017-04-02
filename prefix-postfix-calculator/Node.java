/**
 * This class is the blueprint for a node object.
 * @author adithepnarula
 *
 * @param <E>
 */
public class Node<E> {
	
	E item;
	Node next;
	
	/**
	 * Constructor that creates a node object with specified item
	 * @param item item to be added to the node
	 */
	public Node(E item) {
		this.item = item;
		
	}

	/**
	 * Method that sets the address to the next node
	 * @param next
	 */
	void setNext(Node next) {
		this.next = next;
	}
	
	/**
	 * Method that gets the address to the next node
	 * @return
	 */
	Node<E> getNext() {
		return next;
	}
	
	/**
	 * Method that sets the Item in the node 
	 * @param item item to be set in the node
	 */
	void setItem(E item){
		this.item = item;
	}
	
	/**
	 * Method that returns the item in the node
	 * @return item in the node
	 */
	E getItem(){
		return item;
	}
	

}
