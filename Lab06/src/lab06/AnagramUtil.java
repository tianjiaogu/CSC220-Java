package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

public class AnagramUtil {

	// Reads words from a file (assumed to contain one word per line)
	// Returns the words as an array of strings.

	public static String[] readFile(String filename) {
		ArrayList<String> results = new ArrayList<String>();
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while (input.ready()) {
				results.add(input.readLine());
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] retval = new String[1];
		return results.toArray(retval);
	}

	public static String[] getLargestAnagramGroup(String filename) {
		if (filename.equals(null))
			return null;
		String[] inputString = readFile(filename);
		return getLargestAnagramGroup(inputString);

	}
	
	

	public static String[] getLargestAnagramGroup(String[] stringList) {
		
		if (stringList.equals(null))
			return null;
		
		
		insertionSort(stringList);

		
		int maxlength = 0;
		int length = 1;
		int end = 0;

		for (int i = 0; i < stringList.length - 1; i++) {

			if (areAnagrams(stringList[i], stringList[i + 1])) {
				length++;
			} else {
				if (length > maxlength) {
					maxlength = length;
					end = i;
				}
				length = 1;
			}

		}
		
		String[] newstringList = new String[maxlength];
		int i = end;
		for(int j = maxlength-1; j <= 0; j--) {
			
			newstringList[j] = stringList[i]; 
			i--;
		}
		return newstringList;
	}
	
	

	public static boolean areAnagrams(String inputString1, String inputString2) {

		String m = sort(inputString1);
		String n = sort(inputString2);
		if (m.equals(n)) {
			return true;
		} else
			return false;

	}

	public static String sort(String inputString) {

		if (inputString.equals(null)) {
			return null;

		}
		char[] arr = inputString.toLowerCase().toCharArray();
		for (int i = 1; i < arr.length; i++) {
			char index = arr[i];
			int j = i;
			while (j > 0 && arr[j - 1] > index) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = index;
		}

		return new String(arr);

	}

	public static void insertionSort(String[] inputList) {

		OrderStrings c = new OrderStrings();

		for (int i = 0; i < inputList.length; i++) {
			String index = inputList[i];
			int j = i;
			while (j > 0 && c.compare(sort(inputList[j - 1]), sort(index)) > 0) {
				inputList[j] = inputList[j - 1];
				j--;
			}
			inputList[j] = index;
		}

	}

}
