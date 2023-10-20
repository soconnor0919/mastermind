/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King
 *
 * Name: O'Connor, Sean and Brackett, Lyman
 * Section: 09:00
 * Date: 10/19/23
 *
 * Project: csci205_hw
 * Package: org.mastermind
 * Class: RandomPlayer
 *
 * Description:
 * Randomly generates guesses, and reports
 * them to board.
 * ****************************************
 */
package org.mastermind;

import java.util.Random;

public class RandomPlayer implements CodeBreakerInterface {
    /** codelength */
    int length;
    
    /** largest int in code range */
    int upperBound;
    
    /** smallest int in code range */
    int lowerBound;
    
    /** counter for number of guesses */
    int totalGuesses;

    /** Random instance to be used throughout */
    Random random;

    /**
     * Sets up the {@link #random} variable to generate
     * random code, along with preseting code length, {@link #length},
     * upper bound, {@link #upperBound}, and lower bound, {@link #lowerBound}
     */
    public RandomPlayer(){
        length = GameManager.CODE_LENGTH;
        upperBound = GameManager.UPPER_BOUND;
        lowerBound = GameManager.LOWER_BOUND;
        totalGuesses = 0;
        random = new Random();
    }

    /**
     * gets random guess
     * @return {@link #guess}. which is an int[] object
     */
    @Override
    public int[] getGuess() {
        int[] guess = new int[length];
        for (int i = 0; i < length; i++) {
            guess[i] = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        }

        return guess;
    }

    /**
     * We've adapted this function from the interface
     * to just increment total guesses b/c we don't
     * really need to recieve a result
     * @param result a string containing
     *               result symbols.
     */
    @Override
    public void receiveResult(String result) {
        totalGuesses += 1;
    }

    /**
     * Function to show total guesses
     * @return {@link #totalGuesses}
     */
    @Override
    public int getGuessCount(){
        return totalGuesses;
    }
}
