import java.util.HashSet;
import java.util.Set;


/* WordInfo represents a word and its set of associations.
 * Associations are unlabeled, one directional connections between words
 * 
 * For example, the abstraction for an example WordInfo object may be as follows:
 * 
 * Computer ---->  {"code","hello world", "programming","fun"}
 * 
 */
public class WordInfo {
	
	// Represents the source of the word association
	private String word;
	
	// Represents the collection of associations from the source
	private Set<String> associations;
	
	/* Initializes a word info without any exisiting associations
	 */
	public WordInfo(String word) {
		this.word = word;
		this.associations = new HashSet<String>();
	}
	
	// Returns the source of the WordInfo 
	public String getWord() {
		return this.word;
	}
	
	// Creates a new WordInfo with the given source word and an existing set of associations
	public void WordInfo(String word, Set<String> associations) {
		this.word = word;
		this.associations = associations;
	}
	
	// Adds an association between the source word and the given String
	public boolean addAssociation(String association) {
		return associations.add(association);
	}
	
	// Removes an association between the source and the given string
	public void removeAssociation(String association) {
		associations.remove(association);
	}
	
	// Returns set of all association from the source word
	public Set<String> getAssociations() {
		return associations;
	}
	
	// Returns string representation of this WordInfo
	public String toString() {
		return word + ": " + associations;
	}
}
