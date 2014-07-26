import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;


public class WordJumble {
	
	public Hashtable<String, Boolean> wordList;
	private ArrayList<String> wordsPrinted; //so not to print duplicates (e.g. sass returns 6 permutations of sass)
	
	public WordJumble() {
		wordList = new Hashtable<String, Boolean>();
		wordsPrinted = new ArrayList<String>();
	}
	
	private void permutations(String prefix, String s) {
	    if (s.length() == 0) {
	    	if (wordList.containsKey(prefix) && !wordsPrinted.contains(prefix)) {
	    		System.out.println(prefix);
	    		wordsPrinted.add(prefix);
	    	}
	    }
	    else {
	        for (int i = 0; i < s.length(); i++)
	            permutations(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, s.length()));
	    }
}

	
	public static void main(String[] args) {
		WordJumble wj = new WordJumble();
		BufferedReader br;
		String fileName = "englishDict.txt";
		try {
			br = new BufferedReader(new FileReader(fileName));
			String word;
			while ((word = br.readLine()) != null) {
				if (word.contains("/")) { // words in englishDict.txt have slashes to denote categories by default
					int index = word.indexOf("/");
					word = word.substring(0, index);
				}
				wj.wordList.put(word, true);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Make sure word list file englishDict.txt is in this directory.");
		}
		wj.permutations("", args[0]);
		

	}

}
