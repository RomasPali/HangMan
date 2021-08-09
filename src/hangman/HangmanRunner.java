package hangman;

public class HangmanRunner {

	public static void main(String[] args) {
		String[] words = { "Hello", "House", "Java", "Angular", "Spring", "Javascript" };
		
		HangmanGame game = new HangmanGame();
		game.run(words);

	}

}
