/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King
 *
 * Name: Sean O'Connor and Lyman Brackett
 * Section: 09:00
 * Date: 10/12/23
 *
 * Project: csci205_hw
 * Package: mastermind
 * Class: Board
 *
 * Description:
 *
 * ****************************************
 */
package mastermind;

public class Board {
    /** Stores amount of guesses made, and the amount remaining */
    private int maxGuesses, guessCount;

    private int[] guess;

    private final CodeMaker cm;

    public Board(CodeMaker codeMaker, int totalGuesses) {
        this.cm = codeMaker;
        this.maxGuesses = totalGuesses;
        this.guessCount = 0;

    }

    public void runGame() {
        cm.generateNewCode();

        while (getGuessesRemaining() > 0) {
            guess = promptForGuess();
        }
    }

    public int[] promptForGuess() {
        guessCount++;
        int[] result = null;
        String input;

        while(result == null) {
            System.out.printf("Guess %d: ", guessCount);
            if (GameManager.scnr.hasNext()) {
                input = GameManager.scnr.next();
                if (input.length() != GameManager.CODE_LENGTH) {
                    System.out.println("Invalid amount of characters. Try again.");
                } else if (!digitsWithinBounds(input, cm.getLowerBound(), cm.getUpperBound())) {
                    System.out.println("Invalid digits. Try again.");
                } else {
                    result = stringToIntArray(input);
                }
            }
        }

        return result;
    }

    /**
     *
     * @param str
     * @return
     */
    private int[] stringToIntArray(String str) {
        char digitChar;
        int[] result = new int[GameManager.CODE_LENGTH];
        for (int i = 0; i < GameManager.CODE_LENGTH; i++) {
            digitChar = str.charAt(i);
            result[i] = Character.getNumericValue(digitChar);
        }
        return result;
    }

    private boolean digitsWithinBounds(String str, int lowerBound, int upperBound) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) || c < (char) (lowerBound + '0') || c > (char) (upperBound + '0')) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns remaining amount of guesses available to the user.
     * @return guessesRemaining
     */
    private int getGuessesRemaining() {
        return maxGuesses - guessCount;
    }
}
