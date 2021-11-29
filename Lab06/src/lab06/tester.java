package lab06;

import java.util.Arrays;

public class tester {
	
	
	public static void main(String args[]) {
		
		AnagramUtil m = new AnagramUtil();
		
		String name = "Tianjiao Gu";
		String reverse = "Gu Tianjiao";
		
		System.out.println(m.sort(name));
		System.out.println(m.areAnagrams(name,reverse));
		
		
		String t1 = "oiashdio";
		String t2 = "uiagdvou";
		
		OrderStrings t = new OrderStrings();
		System.out.println(t.compare(m.sort(t1), m.sort(t2)));
		
		

		String[] h = {"joy","ski","fed","cat"};
		
		System.out.println(Arrays.toString(h));
		m.insertionSort(h);
		System.out.println(Arrays.toString(h));
		
		
		String[] words_copy = m.readFile("sample_word_list.txt");
		String[] s3 = m.getLargestAnagramGroup(words_copy);
		System.out.println(Arrays.toString(s3));
		
		String[] s4 = m.getLargestAnagramGroup("sample_word_list.txt");
		System.out.println(Arrays.toString(s4));


}
}