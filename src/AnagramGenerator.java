import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class AnagramGenerator {

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
        String result = "";
        String inputWord = args[1];
        
        FileInputStream fstream = new FileInputStream(args[0]);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {
            String line;
            while ((line = br.readLine()) != null) {
            	if (isAnagram(inputWord, line)) {
            		result = result + "," + line;
            	}
            }
        }
        
        long stop = System.currentTimeMillis() - startTime;
        System.out.println(stop + result);
	}
	
	static boolean isAnagram(String a, String b) {
		
		a = a.toLowerCase();
		b = b.toLowerCase();
		
		if (a.equals(b) || a.length() != b.length()) {
			return false;
		}
		
		char[] word1 = a.toLowerCase().toCharArray();
		char[] word2 = b.toLowerCase().toCharArray();
		

		Map<Character, Integer> lettersInWord1 = new HashMap<Character, Integer>();

		for (char c : word1) {
		    int count = 1;
		    if (lettersInWord1.containsKey(c)) {
		        count = lettersInWord1.get(c) + 1;
		    }
		    lettersInWord1.put(c, count);
		}

		for (char c : word2) {
		    int count = -1;
		    if (lettersInWord1.containsKey(c)) {
		        count = lettersInWord1.get(c) - 1;
		    }
		    lettersInWord1.put(c, count);
		}

		for (char c : lettersInWord1.keySet()) {
		    if (lettersInWord1.get(c) != 0) {
		        return false;
		    }
		}

		return true;
	}	

}
