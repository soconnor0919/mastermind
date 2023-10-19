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
 * Class: RandomPlayer
 *
 * Description:
 *
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

    public RandomPlayer(){
        length = GameManager.CODE_LENGTH;
        upperBound = GameManager.UPPER_BOUND;
        lowerBound = GameManager.LOWER_BOUND;
        totalGuesses = 0;
        random = new Random();
    }
    @Override
    public int[] getGuess() {
        int[] guess = new int[length];
        for (int i = 0; i < length; i++) {
            guess[i] = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        }

        return guess;
    }

    @Override
    public void receiveResult(String result) {
        totalGuesses += 1;
    }
}
