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
 * Handles main game logic, and runs the game
 * itself.
 * ****************************************
 */
package org.mastermind;

public class Board {
    /** Stores number of guesses made, and the amount remaining */
    private int maxGuesses, guessCount;

    /** Integer array used to store each guess as individual elements */
    private int[] guess;

    /** CodeMaker object to be used throughout the game */
    private final CodeMaker cm;

    /**
     * Initializes a new board, with zero guesses.
     * @param codeMaker CodeMaker object used throughout
     * @param totalGuesses maximum number of guesses before
     *                     result is revealed
     * @author sso005 & lmb042
     */
    public Board(CodeMaker codeMaker, int totalGuesses) {
        this.cm = codeMaker;
        this.maxGuesses = totalGuesses;
        this.guessCount = 0;

    }

    /**
     * Main game logic
     * Creates a new code to guess, then while eligible to
     * guess, prompts the user to enter a guess. Guess is then
     * checked by the CodeMaker, and results are reported.
     * @author sso005
     */
    public void runGame() {
        cm.generateNewCode();

        while (!cm.gameWon() && getGuessesRemaining() > 0) {
            guess = promptForGuess();
            String result = cm.checkGuess(guess);
            System.out.println(formatResults(guess, result));
        }
    }

    /**
     * Prompts the user to guess a String of x numbers, with
     * lower and upper bounds. Retries until a valid guess is
     * entered.
     * @return result- an integer array containing each individual digit
     */
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
     * Converts a string to an array of individual integers
     * @param str A string containing n integers
     * @return An array containing n individual integers
     * @author sso005 & lmb042
     */
    int[] stringToIntArray(String str) {
        char digitChar;
        int[] result = new int[GameManager.CODE_LENGTH];
        for (int i = 0; i < GameManager.CODE_LENGTH; i++) {
            digitChar = str.charAt(i);
            result[i] = Character.getNumericValue(digitChar);
        }
        return result;
    }

    /**
     * Checks if a String containing digits is within set bounds
     * @param str String to check
     * @param lowerBound minimum possible digit
     * @param upperBound maximum possible digit
     * @return true if digits are within bounds
     * @author sso005
     */
    boolean digitsWithinBounds(String str, int lowerBound, int upperBound) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) || c < (char) (lowerBound + '0') || c > (char) (upperBound + '0')) {
                return false;
            }
        }
        return true;
    }

    /**
     * Takes in a guess and a known result, then formats for
     * user output.
     * @param guess A string containing the user's guess
     * @param result A string containing the stars, pluses
     *               and minuses representing the guesses'
     *               result.
     * @return A string containing the guess, result, and
     * information about the rest of the game's state.
     * @author sso005 & lmb042
     */
    String formatResults(int[] guess, String result) {
        String formattedResult = "";

        for (int i = 0; i < guess.length; i++) {
            formattedResult += guess[i];
        }

        formattedResult += " --> ";

        formattedResult += result;

        formattedResult += "    ";

        if (cm.gameWon()) {
            formattedResult += "YOU WON! You guessed the code in " + getGuessCount() + " moves!";
        } else if (getGuessesRemaining() > 0) {
            formattedResult += "Try again. " + getGuessesRemaining() + " guesses left.";
        } else {
            formattedResult = formattedResult + "YOU LOST! The code was " + cm.revealAnswer() + ".";
        }


        return formattedResult;
    }

    /**
     * Returns the remaining number of guesses available to the user.
     * @return guessesRemaining
     * @author lmb042
     */
    private int getGuessesRemaining() {
        return maxGuesses - guessCount;
    }

    /**
     * Returns the number of guesses made.
     * @return guessCount
     * @author lmb042
     */
    private int getGuessCount() {
        return guessCount;
    }
}
