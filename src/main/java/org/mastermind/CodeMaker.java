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
    private final int[] gameCode;

    /** Random object used to generate numbers */
    private final Random random;

    /** integers storing code length, upper bound, and lower bound */
    private static int length, upperBound, lowerBound;

    /**
     * CodeMaker Instance
     * Takes in length and bounds, then instantiates a
     * new array to store the game code.
     */
    public CodeMaker(int len, int lBound, int uBound) {
        random = new Random();
        length = len;
        lowerBound = lBound;
        upperBound = uBound;
        gameCode = new int[length];
    }

    /**
     * Generates a new game code using the constraints
     * given at initialization.
     */
    public void generateNewCode() {
        for (int i = 0; i < length - 1; i++) {
            gameCode[i] = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        }
    }

    /**
     * Returns the smallest valid digit
     * of a possible code.
     * @return lowerBound
     */
    public int getLowerBound() {
        return lowerBound;
    }

    /**
     * Returns the largest valid digit
     * of a possible code.
     * @return upperBound
     */
    public int getUpperBound() {
        return upperBound;
    }
}
