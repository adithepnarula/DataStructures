/**
 * This is the node class that contains all the necessary information.
 * An object is created from this class, which is added to the tree.
 * 
 * @author adithepnarula
 *
 * @param <T>  T can be any type of object that is comparable
 */
public class BSTNode <T extends Comparable<T>> implements Comparable<BSTNode<T>>{
	
	//data stored in node
	private T data;
	
	//reference to node's left child
	private BSTNode<T>left;
	
	//reference to node's right child
	private BSTNode<T> right;
	
	//height of the node
	private int height;
	
	/**
	 * constructor that creates a node with data
	 * @param data the data of the node
	 */
	public BSTNode (T data) {
		this.data = data;
	}
	
	/**
	 * Constructor that creates a node with data, and assigns a left and right reference for the node
	 * @param data the data of the node
	 * @param left reference to node's left child
	 * @param right reference to node's right child
	 */
	public BSTNode (T data, BSTNode<T> left, BSTNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	@Override
	/**
	 * This method returns an int, indicating whether the data of this current node is bigger, smaller or equal to the 
	 * other node's data
	 * @return returns 0 if equal, negative number if smaller, positive number if bigger
	 */
	public int compareTo(BSTNode<T> other) {
		return this.data.compareTo(other.getData());
	}
	
	
	/**
	 * getter method to obtain data
	 * @return returns this node's data
	 */
	public T getData(){
		return this.data;
	}
	
	/**
	 * getter method that returns reference to left child
	 * @return returns reference to node's left child
	 */
	public BSTNode<T> getLeft() {
		return this.left;
	}
	
	
	/**
	 * getter method that returns reference to right child
	 * @return returns reference to node's right child
	 */
	public BSTNode<T> getRight() {
		return this.right;
	}
	
	/**
	 * setter method that sets the node's left child
	 * @param left reference to the left child
	 */
	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}
	
	/**
	 * setter method that sets the node's right child
	 * @param right reference to the right child
	 */
	public void setRight(BSTNode<T> right) {
		this.right = right;
	}
	
	/**
	 * setter method that sets the node's data
	 * @param data reference to the new data
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * getter method that returns height of the node
	 * @return returns int height
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * setter method that sets the node's height
	 * @param height the value of the new height
	 */
	public void setHeight(int height) {
		this.height = height;
		
	}
	


}
