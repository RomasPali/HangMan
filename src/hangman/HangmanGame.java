package hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
	private static final int MAX_ERRORS = 6;
	private static final int CORRECT_SUM = 0;
	
	private static final char CHAR_EMPTY = '\0';
	private static final String CHAR_SECRET_SYMBOL = " *";

	private Random random;
	private char[] secretWordAsCharArray;
	private int errorCount;
	private List<Character> guessedLetters;
	private String secretWord;

	public HangmanGame() {

		random = new Random();
		guessedLetters = new ArrayList<>();

	}

	public void run(String[] words) {

		secretWord = words[random.nextInt(words.length)];
		secretWordAsCharArray = new char[secretWord.length()];

		System.out.println("Guess one letter");
		print();

		while (!winConditions()) {

			Scanner scan = new Scanner(System.in);
			char guessLetter = scan.nextLine().charAt(0);

			if (!guessedLetters.contains(guessLetter)) {

				makeGuess(guessLetter);
				winConditions();
				print();
				if (errorCount == MAX_ERRORS) {

					System.out.println("Secret word was " + secretWord);
					break;
				}

				guessedLetters.add(guessLetter);

			} else {

				System.out.println("Letters wich u alreadey guess : ");
				yourGuessedLetters();
			}
		}
	}

	private void print() {

		for (int i = 0; i < secretWordAsCharArray.length; i++) {
			if (secretWordAsCharArray[i] == CHAR_EMPTY) {
				System.out.print(CHAR_SECRET_SYMBOL);
			} else {
				System.out.print(" " + secretWordAsCharArray[i]);
			}
		}
		System.out.println();
	}

	private void yourGuessedLetters() {

		for (int i = 0; i < guessedLetters.size(); i++) {
			System.out.print(" " + guessedLetters.get(i));
		}
		System.out.println();
	}

	private boolean winConditions() {

		for (int i = 0; i < secretWordAsCharArray.length; i++) {
			if (secretWordAsCharArray[i] == CHAR_EMPTY) {
				return false;
			}
		}
		System.out.println("Congratulations you won");
		return true;
	}

	private int makeGuess(char guessLetter) {

		int correctSum = CORRECT_SUM;
		for (int i = 0; i < secretWord.length(); i++) {
			char secretWordLetter = secretWord.charAt(i);
			if (secretWordLetter == guessLetter) {
				secretWordAsCharArray[i] = secretWordLetter;
				correctSum = correctSum + 1;
			}
		}

		if (correctSum != CORRECT_SUM) {
			return 0;
		}

		System.out.println("You have only :" + (MAX_ERRORS - 1 - errorCount) + " tries");
		return errorCount++;
	}

}
