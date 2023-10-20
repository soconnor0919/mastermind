/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King
 *
 * Name: O'Connor, Sean and Brackett, Lyman
 * Section: 09:00
 * Date: 10/20/23
 *
 * Project: csci205_hw
 * Package: org.mastermind
 * Class: BruteForcePlayer
 *
 * Description: Attempts to brute-force the
 * code, by checking each possible integer
 * individually, storing quantities of good
 * integers, and then creating sets of
 * possible solutions.
 * ****************************************
 */
package org.mastermind;

import java.util.*;

public class BruteForcePlayer implements CodeBreakerInterface {
    /** codelength */
    int length;

    /** largest int in code range */
    int upperBound;

    /** smallest int in code range */
    int lowerBound;

    /** counter for number of guesses */
    int totalGuesses;

    /** true if all sets of distinct integers have been guessed */
    boolean distinctIntegersGuessed;

    /** Last distinct integer guessed */
    int lastDistinct;

    /** Array containing occurrences of each distinct digit */
    int[] distinctOccurrence;

    /** Random instance to be used throughout */
    Random random;

    /**
     * Creates a new BruteForcePlayer
     * @author sso005
     */
    public BruteForcePlayer() {
        random = new Random();
        length = GameManager.CODE_LENGTH;
        upperBound = GameManager.UPPER_BOUND;
        lowerBound = GameManager.LOWER_BOUND;
        totalGuesses = 0;
        distinctIntegersGuessed = false;
        lastDistinct = lowerBound - 1;
        distinctOccurrence = new int[upperBound - lowerBound + 1];
    }

    /**
     * Returns an integer array containing a guess.
     * If the initial tests haven't been run, they
     * are run individually. Then, randomized
     * arrays of known good integers are returned
     * @return int[] containing a guess
     * @author sso005 & lmb042
     */
    @Override
    public int[] getGuess() {
        int[] result = new int[length];
        if (!distinctIntegersGuessed) {
            lastDistinct++;
            for (int i = 0; i < GameManager.CODE_LENGTH; i++) {
                result[i] = lastDistinct;
            }

            return result;
        } else {
            return generateRandomCode();
        }
    }

    /**
     * Behavior ran after a result is received.
     * If the results haven't been tested yet,
     * the result is iterated through and data
     * is stored. Otherwise, only the guess
     * count is incremented.
     * @param result a string containing
     *               result symbols.
     * @author sso005
     */
    @Override
    public void receiveResult(String result) {
        if (!distinctIntegersGuessed) {
            // Iterate over each character in the string
            for (int i = 0; i < result.length(); i++) {
                char currentChar = result.charAt(i);
                // If the character is '*', increment occurrences
                if (currentChar == '*') {
                    distinctOccurrence[lastDistinct - 1]++;
                }
            }
            // When done making all preliminary guesses, update boolean.
            if (lastDistinct >= upperBound) {
                distinctIntegersGuessed = true;
            }
        }
        // Increase guess count
        totalGuesses++;
    }

    /**
     * Uses a list of known good integers and their
     * quantities to create a randomized set of
     * the integers known to be part of the solution.
     * @return int[] containing guess
     * @author sso005 & lmb042
     */
    public int[] generateRandomCode() {
        List<Integer> elements = new ArrayList<>();

        // Add each element based on its quantity
        for (int i = 0; i < distinctOccurrence.length; i++) {
            int quantity = distinctOccurrence[i];
            for (int j = 0; j < quantity; j++) {
                elements.add(lowerBound + i);
            }
        }

        // Shuffle the elements
        long seed = System.nanoTime(); // Use current time as seed
        Collections.shuffle(elements, new Random(seed));

        // Convert the list to an array
        int[] result = new int[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            result[i] = elements.get(i);
        }

        return result;
    }

    /**
     * Returns the number of guesses made
     * @return guessCount
     * @author lmb042
     */
    @Override
    public int getGuessCount() {
        return totalGuesses;
    }
}
