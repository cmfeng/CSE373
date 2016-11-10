/*  cmf927@uw.edu, Changming Feng
 *  hsiaop@uw.edu, Preston Hsiao
 *  Programming Assignment #3: TextAssociator CSE373, Autumn 2015
 *  Programming Assignment #3: TextAssociator 
 * 
 * TextAssociator represents a collection of associations between words.
 * See write-up for implementation details and hints
 * 
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextAssociator {
	private WordInfoSeparateChain[] table;
	private int size;
	
	/* INNER CLASS
	 * Represents a separate chain in your implementation of your hashing
	 * A WordInfoSeparateChain is a list of WordInfo objects that have all
	 * been hashed to the same index of the TextAssociator
	 */
	private class WordInfoSeparateChain {
		private List<WordInfo> chain;
		
		/* Creates an empty WordInfoSeparateChain without any WordInfo
		 */
		public WordInfoSeparateChain() {
			this.chain = new ArrayList<WordInfo>();
		}
		
		/* Adds a WordInfo object to the SeparateCahin
		 * Returns true if the WordInfo was successfully added, false otherwise
		 */
		public boolean add(WordInfo wi) {
			if (!chain.contains(wi)) {
				chain.add(wi);
				return true;
			}
			return false;
		}
		
		/* Removes the given WordInfo object from the separate chain
		 * Returns true if the WordInfo was successfully removed, false otherwise
		 */
		public boolean remove(WordInfo wi) {
			if (chain.contains(wi)) {
				chain.remove(wi);
				return true;
			}
			return false;
		}
		
		// Returns the size of this separate chain
		public int size() {
			return chain.size();
		}
		
		// Returns the String representation of this separate chain
		public String toString() {
			return chain.toString();
		}
		
		// Returns the list of WordInfo objects in this chain
		public List<WordInfo> getElements() {
			return chain;
		}
	}
	
	
	/* Creates a new TextAssociator without any associations 
	 */
	public TextAssociator() {
		this.size = 0;
		this.table = new WordInfoSeparateChain[7];
		// initializes each WordInfoSeparateChain in the table array
		for (int i = 0; i < table.length; i++) {
			table[i] = new WordInfoSeparateChain();
		}	
	}
	
	
	/* Adds a word with no associations to the TextAssociator 
	 * Returns False if this word is already contained in your TextAssociator ,
	 * Returns True if this word is successfully added
	 */
	public boolean addNewWord(String word) {
		WordInfo wi = new WordInfo(word);
		// gets the index of the new word
		int hashNumber=toHash(word, table.length);
		
		for (WordInfo w : table[hashNumber].chain) {
			if (w.getWord().equals(word)) {
				return false;
			}
		}
	    // adds the WordInfo of the new word into its corresponding WordInfoSeparateChain
		table[hashNumber].add(wi);
		// size only increases by one per each new word added, not association
		size++;
		
		// resizes the array if the load factor equals 1
		if (size >= table.length) {
			resize();
			}
		return true;
	}
	
	// private method that resizes the table array to the first prime 
	// greater than double the previous size when the load factor equals 1
	private void resize() {
		int newLen = 2 * table.length + 1;
		// finds the next prime number to be the new length
		while (!isPrime(newLen)) {
			newLen++;
		}
		
		WordInfoSeparateChain[] old = table;
		table = new WordInfoSeparateChain[newLen];
		
		// initializes every element in the new table array
		for (int i = 0; i < table.length; i++) {
			table[i] = new WordInfoSeparateChain();
		}
		
		// recalculates the destination	of each	WordInfo object when 
		// resizing the array and adds each word and its association again
		// according to its new destination
		for (int i = 0; i < old.length; i++) {
			for (WordInfo w : old[i].getElements()) {
				addNewWord(w.getWord());
				for (String s: w.getAssociations()) {
					addAssociation(w.getWord(), s);
				}
			}
		}
	}
	
	// private helper method that returns whether or not a number is prime
	private boolean isPrime(int n){
		if (n == 1|| n % 2 == 0) {
			return false;
		} else if (n == 2|| n == 3) {
			return true;
		}
		for (int i = 3; i * i <= n;i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/* Adds an association between the given words. Returns true if association correctly added, 
	 * returns false if first parameter does not already exist in the SpellChecker or if 
	 * the association between the two words already exists
	 */
	public boolean addAssociation(String word, String association) {
		// the index of the word to which the association should be added
		int hashNumber = toHash(word, table.length);
		
		for (WordInfo w : table[hashNumber].getElements()) {
			if (w.getWord().equals(word)) {
				if (w.getAssociations().contains(association)) {
					return false;
				}
				w.addAssociation(association);
				
				return true;
			}
		}
		
		return false;
	}
	
	// private helper method that gets the table index of the given word 
	private int toHash(String word, int length) {
		int hashNumber = word.hashCode() % length;
		
		if (hashNumber < 0) {
			hashNumber += length;
		}
		
		return hashNumber;
	}
	
	
	/* Remove the given word from the TextAssociator, returns false if word 
	 * was not contained, returns true if the word was successfully removed.
	 * Note that only a source word can be removed by this method, not an association.
	 */
	public boolean remove(String word) {
		// calculates the table index of the word to be removed
		int hashNumber = toHash(word, table.length);
		
		for (WordInfo w : table[hashNumber].chain) {
			if (w.getWord().equals(word)) {
				table[hashNumber].remove(w);
				size--;
				
				return true;
			}
		}
		
		return false;
	}
	
	/* Returns a set of all the words associated with the given String  
	 * Returns null if the given String does not exist in the TextAssociator
	 */
	public Set<String> getAssociations(String word) {
		// calculates the table index of the given word
		int hashNumber = toHash(word,table.length);
	
		for (WordInfo w : table[hashNumber].chain) {
			if (w.getWord().equals(word)) {
				return w.getAssociations();
			}
		}
		
		return null;
	}
	
	
	/* Prints the current associations between words being stored
	 * good example of clients using textassociator/table 
	 * to System.out
	 */
	public void prettyPrint() {
		System.out.println("Current number of elements : " + size);
		System.out.println("Current table size: " + table.length);
		
		//Walk through every possible index in the table
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				WordInfoSeparateChain bucket = table[i];
				
				//For each separate chain, grab each individual WordInfo
				for (WordInfo curr : bucket.getElements()) {
					System.out.println("\tin table index, " + i + ": " + curr);
				}
			}
		}
		System.out.println();
	}
}
