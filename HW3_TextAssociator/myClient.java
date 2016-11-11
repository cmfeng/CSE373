// cmf927@uw.edu, Changming Feng
// hsiaop@uw.edu, Preston Hsiao
// Programming Assignment #3: TextAssociator CSE373, Autumn 2015
// 
// This myClient class is a client of TextAssociator, and does a spell check on 
// the words, "hello", "how", "are", "you", "doing", and "today".

import java.util.Scanner;
import java.util.Set;

public class myClient {
	public static void main(String[] args) {
		TextAssociator ta = new TextAssociator();
		
		// array of correct words
		String[] s = { "hello", "how", "are", "you", "doing", "today" };
		
		// array of misspelled words
		String[][] a = { { "hllo", "ehllo", "hallo", "heoll" }, { "hw", "hwo", "hpw", "owh" },
				{ "aer", "rea", "arq", "r" }, { "u", "yuo", "yoe", "yow" }, { "deing", "dong", "ding", "doind" },
				{ "teday", "todya", "taday", "todat" } };
		
		// adds each misspelled word with its correct associated word
		for (int i = 0; i < a.length; i++) {
			for (String ss : a[i]) {
				ta.addNewWord(ss);
				ta.addAssociation(ss, s[i]);
			}

		}
		
		System.out.println("This is a spell checker for any of the words in the sentence, \"hello how are you doing today\".");
		System.out.print("Input a sentence, phrase, or word that contains any incorrect spelling of these words above: ");
		
		// scans the input to be spell checked
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		String[] tokens = input.split(" ");
		String result = "";

		// spell checks the words in the input 
		for (String token : tokens) {
			Set<String> words = ta.getAssociations(token.toLowerCase());
			if (words == null) {
				result += " " + token;
			} else {
				result += " " + words.toArray()[0];
			}
		}
		
		// prints the resulting spell checked sentence
		System.out.println(result.trim());
		System.out.println();

	}
}