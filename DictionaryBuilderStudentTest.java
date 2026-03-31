import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.*;

public class DictionaryBuilderStudentTest {

	private DictionaryBuilder db;

	@BeforeEach
	public void setUp() {
		db = new DictionaryBuilder(10);
	}

	@Test
	public void testConstructor() {
		assertNotNull(db);
	}

	@Test
	public void testAddWord_and_getFrequency() {
		db.addWord("elmo");
		assertEquals(1, db.getFrequency("elmo"));
	}

	@Test
	public void testAddWord_duplicate() {
		db.addWord("barney");
		db.addWord("barney");
		assertEquals(2, db.getFrequency("barney"));
	}

	@Test
	public void testGetFrequency_wordNotFound() {
		assertEquals(0, db.getFrequency("cookies"));
	}

	@Test
	public void testRemoveWord() throws Exception {
		db.addWord("apple");
		db.removeWord("apple");
		assertEquals(0, db.getFrequency("apple"));
	}

	@Test
	public void testRemoveWord_notFound() {
		assertThrows(Exception.class, () -> db.removeWord("poppy"));
	}

	@Test
	public void testGetAllWords() {
		db.addWord("poppy");
		db.addWord("branch");
		ArrayList<String> words = db.getAllWords();
		assertTrue(words.contains("poppy"));
		assertTrue(words.contains("branch"));
	}

	@Test
	public void testIsPrime() {
		assertTrue(DictionaryBuilder.isPrime(7));
		assertFalse(DictionaryBuilder.isPrime(8));
	}

	@Test
	public void testGetters() {
		db.addWord("apple");
		assertEquals(1, db.getTotal());
		assertEquals(1, db.getUnique());
		assertTrue(db.getSize() > 0);
	}
}