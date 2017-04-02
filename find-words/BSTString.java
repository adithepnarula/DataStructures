
/**
 * This class extends the BST class. It contains all the same methods, wit the addition 
 * of a method that checks whether the item in question is a prefix of a word in the dictionary.
 * @author adithepnarula
 *
 */
public class BSTString extends BST<String>{

	/**
	 * no-arg constructor that creates BST object
	 */
	public BSTString() {
		
	}
	
	/**
	 * This method calls a private recContainsPrefix method to determine whether the argument item is contained in the tree
	 * @param item the item that is checked if its a prefix in the tree
	 * @return returns true if item is a prefix in the tree, false otherwise
	 */
	public boolean containsPrefix(String item) {
		
		return recContainsPrefix(item, root);
	}
	
	/**
	 * This recursive method does all the work for public containsPredix method. It tests whether
	 * the item in question is a prefix in the tree. If item value is less than current node's item,
	 * the method recursively calls itself, thus comparing the item to node's left child. If item value is
	 * more than current node's item, the method recursively calls itself, but now it compares the item
	 * to the node's right child. Recursion only stops when item is a prefix to node's item or when node is null, which only occurs
	 * if the item is not a prefix in the tree
	 * 
	 * @param item item that is checked whether it is a prefix in the tree or not
	 * @param node the node object whose item is compared to "item"
	 * @return returns true if item is a prefix in the tree, false otherwise
	 */
	private boolean recContainsPrefix(String item, BSTNode<String> node) {
	
		
		if (node == null) {
			return false;
		}
		
		else if(node.getData().startsWith(item)){
			return true;
		}
		
		else if (item.compareTo(node.getData()) < 0) {
			return recContainsPrefix(item, node.getLeft());
		}
		else if (item.compareTo(node.getData()) > 0) {
			return recContainsPrefix(item, node.getRight());
		}
		
		else {
			return true;
		}
		
	}
	

}
