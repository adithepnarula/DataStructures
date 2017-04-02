import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an AVL tree class. It is used as the underlying structure
 * for storing dictionary words. It also contains an inner iterator class used for traversal. 
 * 
 * @author adithepnarula
 *
 * @param <T> T can be any type of object that is comparable
 */
public class BST <T extends Comparable <T>> implements Iterable<T>{
	
	//the root of the tree
	//protected because BSTString, the child class, needs access to the root
	protected BSTNode<T> root;
	
	/**
	 * Constructor that creates an empty tree
	 */
	public BST(){
		this.root = null;
	}
	
	/**
	 * This method returns the size of the tree (ie. the number of words)
	 * @return an int that represents the size of the tree
	 */
	public int size(){
		int size = recSize(this.root);
		return size;
	}
	
	/**
	 * Recursive method that does all the work for size. 
	 * It traverses through all the node and adds one each time it visits a new node.
	 * @param node node object that is visited at that instant
	 * @return returns size of the tree to the public method caller
	 */
	private int recSize(BSTNode<T> node) {
		//only add one if root is not null
		if (root != null) {
			return recSize(node.getLeft()) + recSize(node.getRight()) + 1;
		}
		//if node is null return 0
		else {
			return 0;
		}
	}
	
	
	/**
	 * This method creates a node with the argument "item" and adds it to the tree
	 * @param item item that will be added to the tree
	 */
	public void add(T item){
		this.root = recAdd(item, this.root);
	
	}
	
	/**
	 * Recursive add method that does all the work. 
	 * This method compares item value in question with current node's item.
	 * If item is less than or equal to node's item, the method is called again 
	 * with node's left child as an argument, otherwise node right child is called.
	 * Method ends recursive call when null is reached. Furthermore, this method also updates the height of 
	 * the node and balances the subtree/tree if an imbalance occurs. 
	 * @param item item to be added to the tree
	 * @param node node object that is visited at that instant, its item will be compared with "item"
	 * @return returns the node object, so it can be connected to the tree
	 */
	private BSTNode<T> recAdd(T item, BSTNode<T> node) {
		
		if(node == null){
			node = new BSTNode<T>(item);

			
		}
		else if(item.compareTo(node.getData()) <= 0){
			node.setLeft(recAdd(item, node.getLeft()));
		}
		else {
			node.setRight(recAdd(item, node.getRight()));
		}
		
		updateHeight(node);
	
		
		//if balance factor <= -2 it means tree is left heavy
		//since its height of right subtree - left subtree
		if(balanceFactor(node) <= -2) {
			
			//check balance factor of the node's left child
			//if balance factor returns 0 or negative
			//it means left heavy
			if (balanceFactor(node.getLeft()) <= 0) {
				node = balanceLL(node);
			}
			
			else {
				node = balanceLR(node);
			}
			
			
		}
		
		//if balance factor >=2 it means tree is right heavy
		//since its height of right subtree - left subtree
		if (balanceFactor(node) >=2){
			//check balance factor of node's right child
			//if balance factor returns 0 or positive
			//it means right heavy

			if(balanceFactor(node.getRight()) >= 0){
				node = balanceRR(node);
			}
			
			else{
				node = balanceRL(node);
			}
		}
		
	
		return node;	
	}
	
	/**
	 * This method calls a private recContains method to determine whether the argument item is contained in the tree
	 * @param item the item that is checked if its in the tree or not
	 * @return returns true if item is in the tree, false otherwise
	 */
	public boolean contains(T item) {
		return recContains(item, this.root);
	}
	
	/**
	 * This recursive method does all the work for public contains method. It tests whether
	 * the item in question is in the tree. If item value is less than current node's item,
	 * the method recursively calls itself, thus comparing the item to node's left child. If item value is
	 * more than current node's item, the method recursively calls itself, but now it compares the item
	 * to the node's right child. Recursion only stops when item is equal to node's item or when node is null, which only occurs
	 * if the item is not in the tree
	 * 
	 * @param item item that is checked whether it is in the tree or not
	 * @param node the node object whose item is compared to "item"
	 * @return returns true if item is in the tree, false otherwise
	 */
	private boolean recContains(T item, BSTNode<T> node) {
		if (node == null) {
			return false;
		}
		else if (item.compareTo(node.getData()) < 0) {
			return recContains(item, node.getLeft());
		}
		else if (item.compareTo(node.getData()) > 0) {
			return recContains(item, node.getRight());
		}
		
		else {
			return true;
		}
		
	}
	
	/**
	 * public method that traverses the entire tree in an "in oder" fashion, 
	 * printing out item from smallest to greatest
	 */
	public void inOrderTraversal() {
		recInOrderTraversal(root);
	}
	
	/**
	 * private recursive method that does all the work for inOrderTraversal. 
	 * It traverses the list in an "in order" fashion, starting from left node (smallest value),
	 * parent node (middle value), right node (greatest value). Recursion only stops once 
	 * every node is visited. 
	 * @param node node that contains item that will be printed out (only if node is not null)
	 */
	private void recInOrderTraversal(BSTNode<T> node){

		if (node != null) {
			recInOrderTraversal(node.getLeft());
			System.out.println(node.getData());
			recInOrderTraversal(node.getRight());
		}
		
		
	}
	
	/**
	 * private balance factor method that is called in the recAdd and recRemove methods
	 * It returns the balance factor of a node. This information will be used to decide
	 * whether the node's subtree requires re-balancing. 
	 * @param node
	 * @return
	 */
	private int balanceFactor(BSTNode<T> node) {
		//if balance factor is 2 or -2, re-balacing is needed
		
		if (node.getRight() == null) {
			return (-node.getHeight());
						
		}
		if (node.getLeft() == null) {
			return node.getHeight();
		
		}
		
		return (node.getRight().getHeight() - node.getLeft().getHeight());
		
	}
	
	/**
	 * This method is called in recAdd and recRemove methods. It updates the height of the current
	 * node.
	 * @param node
	 */
	void updateHeight(BSTNode<T> node) {
		
		// case 1: node is a leaf, so height is 0
		if (node.getLeft() == null && node.getRight() == null) {
			node.setHeight(0);
		}
		
		//case 2: node has only right child, so height is right's child height + 1
		else if (node.getLeft() == null) {
			int height = 1 + node.getRight().getHeight();
			node.setHeight(height);
		}

		//case 3: node has only left child, so height is left's child height + 1
		else if (node.getRight() == null) {
			int height = 1 + node.getLeft().getHeight();
			node.setHeight(height);
		}
		
		// case 4: node has two children, get the max height between the two children and add one
		else {
			int leftHeight = node.getLeft().getHeight();
			int rightHeight = node.getRight().getHeight();
			if (leftHeight >= rightHeight) {
				int height = 1 + leftHeight;
				node.setHeight(height);
			}
			else {
				int height = 1 + rightHeight;
				node.setHeight(height);
			}
		}
		
	}
	
	/**
	 * Imbalance in the left subtree. Left subtree's left subtree is the "heaviest", meaning that it has nodes with greatest depth that is generating
	 * the imbalance. This method re-balances the subtree and updates the height appropriately. 
	 * @param A the node whose subtree has an imbalance
	 * @return return a reference to the re-balance subtree
	 */
	BSTNode<T> balanceLL(BSTNode<T> A) {
		BSTNode<T> B = A.getLeft();
		A.setLeft(B.getRight());
		B.setRight(A);
		updateHeight(A);
		updateHeight(B);
		return B;
	}
	
	/**
	 * Imbalance in the right subtree. Right subtree's right subtree is the "heaviest", meaning that it has nodes with greatest depth that is generating
	 * the imbalance. This method re-balances the subtree and updates the height appropriately. 
	 * @param A the node whose subtree has an imbalance
	 * @return return a reference to the re-balance subtree
	 */
	BSTNode<T> balanceRR(BSTNode<T> A) {
		BSTNode<T> B = A.getRight();
		A.setRight(B.getLeft());
		B.setLeft(A);
		updateHeight(A);
		updateHeight(B);
		return B;
	}
	
	/**
	 * Imbalance in the left subtree. Left subtree's right subtree is the "heaviest", meaning that it has nodes with greatest depth that is generating
	 * the imbalance. This method re-balances the subtree and updates the height appropriately. 
	 * @param A the node whose subtree has an imbalance
	 * @return return a reference to the re-balance subtree
	 */
	
	BSTNode<T> balanceLR(BSTNode<T> A) {
		 BSTNode<T> B = A.getLeft();
		 BSTNode<T> C = B.getRight();
		 A.setLeft(C.getRight());
		 B.setRight(C.getLeft());
		 C.setLeft(B);
		 C.setRight(A);
		 updateHeight(A);
		 updateHeight(B);
		 updateHeight(C);
		 return C;
	}
	
	/**
	 * Imbalance in the right subtree. Right subtree's left subtree is the "heaviest", meaning that it has nodes with greatest depth that is generating
	 * the imbalance. This method re-balances the subtree and updates the height appropriately. 
	 * @param A the node whose subtree has an imbalance
	 * @return return a reference to the re-balance subtree
	 */
	
	BSTNode<T> balanceRL(BSTNode<T> A) {
		 BSTNode<T> B = A.getRight();
		 BSTNode<T> C = B.getLeft();
		 A.setRight(C.getLeft());
		 B.setLeft(C.getRight());
		 C.setLeft(A);
		 C.setRight(B);
		 updateHeight(A);
		 updateHeight(B);
		 updateHeight(C);
		 return C;
	}
	
	/**
	 * returns a string representation of every word in the dictionary. Order of string is in "post order".
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		postOrderPrint(root, 0, s);
		return s.toString();
	}

	/**
	 * Note that the documentation an of this method and the code of this method itself is obtained from prof Joanna.
	 * This method returns a string representation of the tree in "post order" fashion.
	 * @param tree the root of the current subtree
	 * @param level level (depth) of the current recursive call in the tree
	 * to determine the indentation of each item 
	 * @param output the string that accumulated the string representation
	 * of this BST
	 */
	private void postOrderPrint(BSTNode<T> tree, int level, StringBuilder output )
	{
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append(tree.getData());
			postOrderPrint(tree.getLeft(), level + 1, output);
			postOrderPrint(tree.getRight(), level + 1, output);
		}
	}
	
	/**
	 * public remove method that removes the "item" in the tree. 
	 * @param item item to be removed from the tree.
	 */
	public void remove(T item) {
		this.root = recRemove(item, this.root);
		
	}
	
	/**
	 * Recursive remove method that does all the work. 
	 * This method compares item value in question with current node's item.
	 * If item is less than the node's item, the method is called again 
	 * with node's left child as an argument, otherwise node right child is called.
	 * Method ends recursive call when the item is found in "node" or when null is reached, which indicates that the item is not in the tree.
	 * Furthermore, this method also updates the height of the node and balances the subtree/tree if an imbalance occurs. 
	 * @param item item to be removed from the tree
	 * @param node node object that is visited at that instant, its item will be compared with "item"
	 * @return returns reference to the node with item removed, so it can be connected to the tree
	 */
	
	private BSTNode<T> recRemove(T item, BSTNode<T> node) {
		if (node == null) {
			return null;
		}
		
		else if (item.compareTo(node.getData()) < 0) {
			node.setLeft(recRemove(item, node.getLeft()));
		}
		
		else if (item.compareTo(node.getData()) > 0) {
			node.setRight(recRemove(item, node.getRight()));
		}
		
		else {
			node = deleteNode(item, node);
		}
		
		
		if (node != null) {
			updateHeight(node);
			
			
			//if balance factor <= -2 it means tree is left heavy
			//since its height of right subtree - left subtree
			if(balanceFactor(node) <= -2) {
				
				//check balance factor of the node's left child
				//if balance factor returns 0 or negative
				//it means left heavy
				if (balanceFactor(node.getLeft()) <= 0) {
					node = balanceLL(node);
				}
				
				else {
					node = balanceLR(node);
				}
				
				
			}
			
			//if balance factor >=2 it means tree is right heavy
			//since its height of right subtree - left subtree
			if (balanceFactor(node) >=2){
				//check balance factor of node's right child
				//if balance factor returns 0 or positive
				//it means right heavy

				if(balanceFactor(node.getRight()) >= 0){
					node = balanceRR(node);
				}
				
				else{
					node = balanceRL(node);
				}
			}
			
			
		}
		
		return node;
	}
	
	/**
	 * private helper method for recRemove. This method removes the selected node from the tree.
	 * If the node to be removed has one child, the child is returned as a reference and the current node 
	 * to be removed is "skipped". If the node has no child, null  is returned. If the node has two child,
	 * appropriate predecessor is replaced with the node's item, then the node is removed. 
	 * @param item item to be removed
	 * @param node node that contains the item to be removed
	 * @return return a new reference of the subtree with item removed
	 */
	private BSTNode<T> deleteNode(T item, BSTNode<T> node) {
		T replacedItem;
		
		// case 1: node has no left child
		if (node.getLeft() == null) {
			return node.getRight();
		}
		
		//case 2: node has no right child
		else if (node.getRight() == null) {
			return node.getLeft();
		}
		
		//case 3: node has two children
		else {
			replacedItem = logicalPredescessor(node.getLeft());
			node.setData(replacedItem);
			node.setLeft(recRemove(replacedItem, node.getLeft()));
			return node;
		}
		
	}
	
	/**
	 * Private helper method that finds the appropriate predecessor. This
	 * predecessor will replace the node's item.
	 * @param node node's left child, which is the root that is either the predecessor
	 * or contains predecessor in its right subtree.
	 * @return returns logical predecessor. 
	 */
	private T logicalPredescessor(BSTNode<T> node) {
		while (node.getRight() != null) {
			node = node.getRight();
		}
		return node.getData();
	}

	/**
	 * this object is used to traverse through the data structure.
	 *@return returns an object that implements the iterator interface
	 */
	public Iterator<T> iterator() {
		return (new AVLIterator());
		
	}
	
	/**
	 * Inner class of BST. This class implements the Iterator interface
	 * and contains dictionary's word in an arraylist. User can create an object
	 * of this class from the BST class, and traverse through the elemtns. 
	 * @author adithepnarula
	 *
	 */
	private class AVLIterator implements Iterator<T> {
		
		//list that contains words from dictionary
		private ArrayList<T> myList;
		
		//current index position
		private int index;
		
		/**
		 * constructor that initializes the list and fills the list with dictionary words in "in order" fashion.
		 */
		AVLIterator() {
			myList = new ArrayList<T>();
			index = 0;
			addElements(root);
		}
		
		/**
		 * private helper method that fills the list with dictionary words in "in order" fashion
		 * @param node the current node during the recursive call
		 */
		private void addElements(BSTNode<T> node) {
			if(node != null) {
				addElements(node.getLeft());
				myList.add(node.getData());
				addElements(node.getRight());		
			}
			
			
		}

		@Override
		/**
		 *This method indicates whether there is a next element in the list
		 *@return returns true if list has more elements, false otherwise
		 */
		public boolean hasNext() {
			if (index == myList.size()) 
				return false; 
			else 
				return true;
		}

		@Override
		/**
		 * This method returns the next element in the list if one exists. Otherwise
		 * the method throws an exception.
		 * @throw throws noSuchElementException if the end of the list has been reached 
		 * @return returns the next element in the list
		 */
		public T next() {
			if(hasNext() == false) {
				throw new NoSuchElementException();
			}
			else {
				T item = myList.get(index);
				index ++;
				return item;
			}
		}
		
		/**
		 * The remove method is not implemented in this class, so an exception will be thrown
		 * if this method is called. 
		 */
		public void remove() {
			throw new UnsupportedOperationException("Remove method not supported");
		}
		
		
		
	}
	
	


}
