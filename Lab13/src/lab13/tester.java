package lab13;

public class tester {
	public static void main(String[] args) {
		WordFisher x1 = new WordFisher("carroll-alice.txt","stopwords.txt");
		WordFisher x2 = new WordFisher("moby-dick.txt","stopwords.txt");
		System.out.println("1  "+x1.getWordCount());
		System.out.println("2  "+x2.getWordCount());
		
		System.out.println("3  "+x1.getNumUniqueWords());
		System.out.println("4  "+x2.getNumUniqueWords());
		
		System.out.println("5  "+x1.getFrequency("whale"));
		System.out.println("6  "+x2.getFrequency("whale"));
		
		x1.pruneVocabulary();
		x2.pruneVocabulary();
		System.out.println("7  "+x2.getTopWords(10));
		System.out.println("8  "+x1.getTopWords(10));
		
		System.out.println("9  "+x1.commonPopularWords(20, x2));
		
		
	
		System.out.println("10  "+x1.getWordCount());
		x2.pruneVocabulary();
		System.out.println("11  "+x2.getWordCount());
		
		
	}
}
