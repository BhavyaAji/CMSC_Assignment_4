import java.util.*;
import java.io.*;

public class DictionaryShell {
	private static final int DEFAULT_NUM = 20;

	/**
	 * 
	 * @param args allows user to select options of whether to add delete, search,
	 *             list , or see stats in an organized manner
	 */
	public static void main(String[] args) {
		DictionaryBuilder dictionary;
		Scanner input;

		if (args.length == 0) {

			System.out.println("No command-line arguments provided.");
			dictionary = new DictionaryBuilder(DEFAULT_NUM);

		} else {

			System.out.println("Command-line arguments:");
			for (int i = 0; i < args.length; i++) {
				System.out.println("args[" + i + "]: " + args[i]);
			}

			System.out.println();

			try {
				dictionary = new DictionaryBuilder(args[0]);
			} catch (FileNotFoundException e) {
				System.out.println("Sorry this file does not work. A default load shall be used");
				dictionary = new DictionaryBuilder(DEFAULT_NUM);
			}

		}
		System.out.println("Welcome to the Dictionary Builder CLI.");
		System.out.println("Available commands: add <word>, delete <word>, search <word>, list, stats, exit");

		String choice = "";
		input = new Scanner(System.in);
		while (!choice.equals("exit")) {
			String word = "";
			// System.out.print("> ");
			choice = input.nextLine();
			if (choice.indexOf(' ') != -1) {
				word = choice.substring(choice.indexOf(' ') + 1);
				word = word.trim();
				choice = choice.substring(0, choice.indexOf(' '));
			}

			switch (choice) {
			case "search":
				if (dictionary.getFrequency(word) > 0) {
					System.out.println(dictionary.getFrequency(word) + " instance(s) of \"" + word + "\" found");
				} else {
					System.out.println("\"" + word + "\" not found");
				}
				break;
			case "add":
				dictionary.addWord(word);
				System.out.println("\"" + word + "\" added");
				break;
			case "delete":
				try {
					dictionary.removeWord(word);
				} catch (DictionaryEntryNotFoundException e) {
					System.out.println("Invalid word");
					break;
				}
				System.out.println("\"" + word + "\" removed");
				break;
			case "list":
				ArrayList<String> list = dictionary.getAllWords();
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
				break;
			case "stats":
				System.out.println("Total words: " + dictionary.getTotal());
				System.out.println("Total unique words: " + dictionary.getUnique());
				System.out.println("Estimated load factor: " + (double) dictionary.getUnique() / dictionary.getSize());
				break;
			case "exit":
				break;
			default:
				System.out.println("Please enter a valid choice.");
				break;
			}

		}
		input.close();
	}
}
