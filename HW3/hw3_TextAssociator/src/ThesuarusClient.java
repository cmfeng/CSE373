import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


/* CSE 373 Fall 2015 starter code
 * @Author: Kevin Quinn
 * 
 * ThesaurusClient takes a text file of synonyms for words 
 * as specified by THESAURUS_FILE and input from standard.in and
 * outputs the spruced up version of the user's input by randomly selecting
 * synonyms for all input words that have synonyms.
 * 
 * This Client program is dependent on TextAssociator
 */
public class ThesuarusClient {
	
	//Path to desired thesaurus file to read
	public final static String THESAURUS_FILE = "large_thesaurus.txt";
	
	public static void main(String[] args) throws IOException {
		File file = new File(THESAURUS_FILE);
		
		//Create new empty TextAssociator
		TextAssociator sc = new TextAssociator();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String text = null;
		
		while ((text = reader.readLine()) != null) {
			String[] words = text.split(",");
			String currWord = words[0].trim();
			sc.addNewWord(currWord);
			
			for (int i = 1; i < words.length; i++) {
				sc.addAssociation(currWord, words[i].trim());
			}
		}
		Scanner scan= new Scanner(System.in);
		String inputString = "";
		Random rand = new Random();
		while (true) {
			System.out.print("Please input the text you would like to be \"enhanced\"? (enter \"exit\" to exit):");
			inputString = scan.nextLine();
			if (inputString.equals("exit")) {
				break;
			}
			String[] tokens  = inputString.split(" ");
			String result = "";
			for (String token : tokens) {
				Set<String> words = sc.getAssociations(token.toLowerCase());
				if (words == null) {
					result += " " + token;
				} else {
					result += " " + words.toArray()[rand.nextInt(words.size())];
				}
			}
			System.out.println(result.trim());
			System.out.println();	
		}
		reader.close();
	}
}
