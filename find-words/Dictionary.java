import java.util.ArrayList;
import java.util.Iterator;

/**
 * The class represent a dictionary of words. 
 * It provides a way of searching through the dictionary.
 * It also can produce a dictionary in which the words are limited
 * to a particular length. 
 * 
 *
 */
public class Dictionary {
	
	private BSTString words;
	private ArrayList<String> listOfWords;
	
	/**
	 * Creates an empty Dictionary object (no words).
	 */
	public Dictionary(){
		
		words = new BSTString(); 
		
	}
	
	/**
	 * Creates a Dictionary object containing all words from the 
	 * listOfWords passed as a parameter.
	 * 
	 * @param listOfWords the list of words to be stored in the newly created 
	 * Dictionary object
	 */
	public Dictionary( ArrayList < String > listOfWords ) {
	
		
		this.words = new BSTString();
		recInsertWords(listOfWords, 0, listOfWords.size()-1);
		
	
		
	
	}
	
	
	private void recInsertWords(ArrayList<String> listOfWords, int first, int last) {
		if (first == last) {
		
			words.add(listOfWords.get(first));
		}
		
		else if(first + 1 == last) {
		
			words.add(listOfWords.get(first));
			words.add(listOfWords.get(last));
		}
		
		else {
			int mid = (first + last)/2;
			words.add(listOfWords.get(mid));
			recInsertWords(listOfWords, first, mid-1);
			recInsertWords(listOfWords, mid+1, last);
		}
		
		
	}


	
	 
	/**
	 * Performs (binary) search in this Dictionary object for a given word.
	 * @param word  the word to look for in this Dictionary object. 
	 * @return true if the word is in this Dictionary object, false otherwise
	 */
	public boolean isWordInDictionary ( String word ) {
		
		if (words.contains(word)) {
			return true; }
		else {
			return false; }
	}
	
	
	
	/**
	 * Performs (binary) search in this Dictionary object for a given prefix.
	 * @param prefix  the prefix to look for in this Dictionary object. 
	 * @return true if at least one word with the specified prefix exists 
	 * in this Dictionary object, false otherwise
	 */
	public boolean isPrefixInDictionary (String prefix ) {
		if(words.containsPrefix(prefix))
			return true;
		else 
			return false;
		
	}

	
	
}
