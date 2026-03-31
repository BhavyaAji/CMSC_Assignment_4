import java.util.*;
import java.io.*;

public class DictionaryBuilder {

	// array of linked lists
	private GenericLinkedList<Entry>[] hashTable;
	private int size;
	private int total, unique;

	/**
	 * 
	 * @param estimatedEntries, sets up attributes when an estimated number of
	 *                          enteries are enterd
	 */
	public DictionaryBuilder(int estimatedEntries) {
		size = (int) (estimatedEntries / 0.6) + 1;
		total = 0;
		unique = 0;

		while (true) {
			if (isPrime(size) && size % 4 == 3) {
				break;
			}
			size++;
		}

		hashTable = new GenericLinkedList[size];
		for (int i = 0; i < size; i++) {
			hashTable[i] = new GenericLinkedList<>();
		}

	}

	/**
	 * sets up when a file is the parameter, prepares the words from the file
	 * 
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public DictionaryBuilder(String filename) throws FileNotFoundException {
		this((int) (new File(filename).length() / 100.0));

		Scanner fileRead = new Scanner(new File(filename));

		String line = "";
		String temp = "";
		while (fileRead.hasNextLine()) {

			line = fileRead.nextLine();
			line = line.trim();

			if (line.isEmpty()) {
				continue;
			}
			while (line.contains(" ")) {
				temp = line.substring(0, line.indexOf(' '));
				addWord(temp);
				line = line.substring(line.indexOf(' ') + 1).trim();
			}
			if (line.isEmpty() == false) {
				addWord(line);
			}

		}

	}

	/**
	 * cleans the words - enters only alphabetical characters and makes it all lower
	 * case adds the word if it isn't a dupe, otherwise increases frequency
	 * 
	 * @param word
	 */
	public void addWord(String word) {
		String temp = "";
		word = word.toLowerCase();

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) >= 'a' && word.charAt(i) <= 'z') {
				temp += word.charAt(i);
			}
		}

		word = temp;
		if (word.isEmpty()) {
			return;
		}

		int index = Math.abs(word.hashCode()) % size;

		GenericLinkedList<Entry> traversalList = hashTable[index];
		Iterator<Entry> iterator = traversalList.iterator();

		Entry e;
		boolean dupe = false;
		while (iterator.hasNext()) {

			e = iterator.next();

			if (word.equals(e.getWord())) {
				total++;
				e.increaseFrequency();
				dupe = true;
				break;
			}
		}
		if (!dupe) {
			traversalList.addLast(new Entry(word));
			unique++;
			total++;
		}

	}

	/**
	 * cleans word then searches through to find frequency
	 * 
	 * @param word
	 * @return
	 */
	public int getFrequency(String word) {
		if (word.isEmpty()) {
			return 0;
		}
		String temp = "";
		word = word.toLowerCase();

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) >= 'a' && word.charAt(i) <= 'z') {
				temp += word.charAt(i);
			}
		}

		word = temp;
		if (word.isEmpty()) {
			return 0;
		}
		int index = Math.abs(word.hashCode()) % size;

		GenericLinkedList<Entry> traversalList = hashTable[index];
		Iterator<Entry> iterator = traversalList.iterator();

		Entry e;
		while (iterator.hasNext()) {

			e = iterator.next();

			if (word.equals(e.getWord())) {

				return e.getFrequency();
			}
		}
		return 0;
	}

	/**
	 * cleans word parameter then searches through to remove the word if it exits,
	 * otherwise does nothing
	 * 
	 * @param word
	 * @throws DictionaryEntryNotFoundException
	 */
	public void removeWord(String word) throws DictionaryEntryNotFoundException {
		if (word.isEmpty()) {
			throw new DictionaryEntryNotFoundException();
		}
		String temp = "";
		word = word.toLowerCase();

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) >= 'a' && word.charAt(i) <= 'z') {
				temp += word.charAt(i);
			}
		}

		word = temp;
		if (word.isEmpty()) {
			throw new DictionaryEntryNotFoundException();
		}
		int index = Math.abs(word.hashCode()) % size;

		GenericLinkedList<Entry> traversalList = hashTable[index];
		Iterator<Entry> iterator = traversalList.iterator();

		Entry e;
		boolean found = false;
		while (iterator.hasNext()) {

			e = iterator.next();

			if (word.equals(e.getWord())) {
				iterator.remove();
				found = true;
				unique--;
				total -= e.getFrequency();
				break;
			}
		}
		if (!found) {
			throw new DictionaryEntryNotFoundException();
		}
	}

	/**
	 * sorts and then returns an alphabetically sorted string arrylist of words
	 * 
	 * @return
	 */
	public ArrayList<String> getAllWords() {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < hashTable.length; i++) {
			GenericLinkedList<Entry> temp = hashTable[i];
			Iterator<Entry> iterator = temp.iterator();
			while (iterator.hasNext()) {
				list.add(iterator.next().getWord());
			}
		}

		int e = list.size();
		// bubble sort
		for (int i = 0; i < e - 1; i++) {
			for (int j = 0; j < e - i - 1; j++) {
				if (list.get(j).compareTo(list.get(j + 1)) > 0) {
					String temp = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, temp);
				}
			}
		}
		return list;
	}

	/**
	 * Provided method to check if number is prime
	 */
	public static boolean isPrime(int n) {
		if (n <= 1) {
			return false;
		}

		if (n == 2 || n == 3) {
			return true;
		}
		if (n % 2 == 0 || n % 3 == 0) {
			return false;
		}

		for (int i = 5; i <= Math.sqrt(n); i = i + 6) {
			if (n % i == 0 || n % (i + 2) == 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @return total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * 
	 * @return unique
	 */
	public int getUnique() {
		return unique;
	}

}
