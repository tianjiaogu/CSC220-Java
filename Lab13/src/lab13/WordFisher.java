package lab13;

import java.util.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WordFisher {

	public HashMap<String, Integer> vocabulary;
	private List<String> stopwords;
	private String inputTextFile;
	private String stopwordsFile;

	public WordFisher(String inputTextFile, String stopwordsFile) {
		this.inputTextFile = inputTextFile;
		this.stopwordsFile = stopwordsFile;
		getStopwords();
		buildVocabulary();

	}

	private void getStopwords() {
		try {
			stopwords = new ArrayList<String>();
			BufferedReader input = new BufferedReader(new FileReader(stopwordsFile));
			String word = input.readLine();
			int i = 0;
			while (word != null) {
				stopwords.add(word);
				word = input.readLine();
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void buildVocabulary() {
		try {
			vocabulary = new HashMap<String, Integer>();
			String reader = new String(Files.readAllBytes(Paths.get(inputTextFile)));
			String[] allwords = reader.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").split("\\s+");

			int value;
			for (int i = 0; i < allwords.length; i++) {
				if (!vocabulary.containsKey(allwords[i])) {
					vocabulary.put(allwords[i], 1);
				} else {
					value = vocabulary.get(allwords[i]);
					vocabulary.put(allwords[i], value + 1);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getWordCount() {
		int sum = 0;
		for (int f : vocabulary.values()) {
			sum += f;

		}
		return sum;
	}

	public int getNumUniqueWords() {
		return vocabulary.size();
	}

	public int getFrequency(String word) {

		if (vocabulary.containsKey(word)) {
			return vocabulary.get(word);
		}

		else
			return -1;

	}

	public void pruneVocabulary() {
		vocabulary.keySet().removeAll(stopwords);
	}

	public ArrayList<String> getTopWords(int n) {
		W comparator = new W();
		PriorityQueue<WordNode> pQueue = new PriorityQueue<WordNode>(vocabulary.size(), comparator);
		for (Map.Entry<String,Integer> mapElement : vocabulary.entrySet()) {
			String word = mapElement.getKey();
			int frequency = mapElement.getValue();
			WordNode current = new WordNode(word, frequency);
			pQueue.add(current);
		}
		ArrayList<String> topwords = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			if (pQueue.peek() != null) {
				topwords.add(pQueue.poll().word);
			}
		}
		return topwords;
	}

	public class WordNode {
		public String word;
		public int frequency;

		public WordNode(String key, int value) {
			word = key;
			frequency = value;
		}

		public int getFreq() {
			return frequency;
		}
	}

	protected class W implements Comparator<WordNode> {

		public int compare(WordNode m, WordNode n) {
			return n.getFreq() - m.getFreq();
		}
	}

	public ArrayList<String> commonPopularWords(int n, WordFisher other) {

		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> list1 = this.getTopWords(n);
		ArrayList<String> list2 = other.getTopWords(n);
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j < list2.size(); j++) {
				if (list1.get(i).equals(list2.get(j))) {
					list.add(list1.get(i));
				}
			}
		}
		return list;
	}

}
