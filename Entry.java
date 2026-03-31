import java.util.Objects;

/**
 * represents a dictionary entry with 2 attributes, the word and the frequency
 * of that word.
 */
class Entry {
	String word;
	int frequency;

	/**
	 * 
	 * @param word
	 */
	public Entry(String word) {
		this.word = word;
		this.frequency = 1;
	}

	/**
	 * 
	 * @return word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * 
	 * @return frequency
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * 
	 * @param word
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * increments frequency
	 */
	public void increaseFrequency() {
		frequency++;
	}

}