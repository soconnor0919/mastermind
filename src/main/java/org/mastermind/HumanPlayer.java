/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King
 *
 * Name: O'Connor, Sean
 * Section: 09:00
 * Date: 10/19/23
 *
 * Project: csci205_hw
 * Package: org.mastermind
 * Class: HumanPlayer
 *
 * Description:
 *
 * ****************************************
 */
package org.mastermind;

public class HumanPlayer implements CodeBreakerInterface {
    /** Length of code to guess */
    private int codeLength;

    /** Number of possible guesses */
    private int maxGuesses;

    /** Guesses taken */
    private int guessCount;

    public HumanPlayer() {
        this.codeLength = GameManager.CODE_LENGTH;
        this.maxGuesses = GameManager.MAX_GUESSES;
        this.guessCount = 0;
    }

    @Override
    public int[] getGuess() {
        guessCount++;
        int[] result = null;
        String input;

        while(result == null) {
            System.out.printf("Guess %d: ", guessCount);
            if (GameManager.scnr.hasNext()) {
                input = GameManager.scnr.next();
                if (input.length() != GameManager.CODE_LENGTH) {
                    System.out.println("Invalid amount of characters. Try again.");
                } else if (!digitsWithinBounds(input, GameManager.LOWER_BOUND, GameManager.UPPER_BOUND)) {
                    System.out.println("Invalid digits. Try again.");
                } else {
                    result = stringToIntArray(input);
                }
            }
        }

        return result;
    }

    @Override
    public void receiveResult(String result) {
        System.out.print("Result: " + result);
        if (result == "****") {
            System.out.println("    YOU WON! You guessed the code in " + guessCount + " moves!");
        } else if (hasRemainingGuesses()) {
            System.out.println("    Try again. " + getRemainingGuesses() + " guesses left.");
        } else {
            System.out.println("    GAME OVER! You lost!");
        }
    }

    /**
     * Converts a string to an array of individual integers
     * @param str A string containing n integers
     * @return An array containing n individual integers
     * @author sso005 & lmb042
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

    /**
     * Checks if a String containing digits is within set bounds
     * @param str String to check
     * @param lowerBound minimum possible digit
     * @param upperBound maximum possible digit
     * @return true if digits are within bounds
     * @author sso005
     */
    private boolean digitsWithinBounds(String str, int lowerBound, int upperBound) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) || c < (char) (lowerBound + '0') || c > (char) (upperBound + '0')) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns amount of remaining guesses
     */
    private int getRemainingGuesses() {
        return maxGuesses - guessCount;
    }

    /**
     * Returns true if player has guesses left
     */
    @Override
    public boolean hasRemainingGuesses() {
        return (maxGuesses - guessCount) > 0;
    }
}
