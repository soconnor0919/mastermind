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
 * Class: CodeMaker
 *
 * Description:
 * Creates a code to be guessed, and handles
 * guessing logic
 * ****************************************
 */
package org.mastermind;

import java.util.Random;

public class CodeMaker {
    /** Integer array storing each digit */
    final int[] gameCode;

    /** Random object used to generate numbers */
    private final Random random;

    /** integers storing code length, upper bound, and lower bound */
    private static int length, upperBound, lowerBound;

    /** State boolean storing if code checked has matched. */
    private static boolean matchFound;

    /**
     * CodeMaker Instance
     * Takes in length and bounds, then instantiates a
     * new array to store the game code.
     * @author sso005
     */
    public CodeMaker(int len, int lBound, int uBound) {
        random = new Random();
        length = len;
        lowerBound = lBound;
        upperBound = uBound;
        matchFound = false;
        gameCode = new int[length];
    }

    /**
     * Generates a new game code using the constraints
     * given at initialization.
     * @author sso005 & lmb042
     */
    public void generateNewCode() {
        for (int i = 0; i < length; i++) {
            gameCode[i] = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        }
    }

    /**
     * Returns the smallest valid digit
     * of a possible code.
     * @return lowerBound
     * @author sso005
     */
    public int getLowerBound() {
        return lowerBound;
    }

    /**
     * Returns the largest valid digit
     * of a possible code.
     * @return upperBound
     * @author sso005
     */
    public int getUpperBound() {
        return upperBound;
    }

    /**
     * Returns a string containing pluses, minuses and
     * stars representing the guesses' accuracy.
     * @param guess integer array containing a guess
     * @return string containing result
     * @author sso005 & lmb042
     */
    public String checkGuess(int[] guess){
        String result = "";
        int[] directMatches = new int[length];

        // Check for direct matches
        for (int i = 0; i < gameCode.length; i++){
            if (gameCode[i] == guess[i]){
                result += "*";
                directMatches[i] = 1;
            }
        }

        // Check for close matches
        for (int i =0; i < length; i++){
                if (directMatches[i] != 1){
                    if (intArrayContains(guess[i], gameCode)){
                        result += "+";
                    }
                }
        }

        // Fill in the rest
        int remainingChars = length - result.length();
        for (int i = 0; i < remainingChars; i++) {
            result += "-";
        }

        // Check if perfect match was found
        if (result.equals("****")) {
            matchFound = true;
        }

        return result;
    }

    /**
     * Checks if a given integer is found within
     * an array of integers
     * @param intToCheckFor integer to check for
     * @param arr array to check
     * @return boolean containing result
     * @author sso005 & lmb042
     */
    public boolean intArrayContains(int intToCheckFor, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == intToCheckFor) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reveals the code to the user in the
     * form of a string
     * @return A string containing the n digits
     * of the code.
     * @author lmb042
     */
    public String revealAnswer() {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += gameCode[i];
        }
        return result;
    }

    /**
     * Returns true if match was found,
     * otherwise false.
     * @author lmb042
     */
    public boolean gameWon() {
        return matchFound;
    }
}
