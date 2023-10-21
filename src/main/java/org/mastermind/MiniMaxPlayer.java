/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Lyman Brackett
 * Section: 9 AM
 * Date: 10/20/2023
 * Time: 6:10 PM
 *
 * Project: csci205_hw
 * Package: org.mastermind
 * Class: MiniMaxPlayer
 *
 * Description:
 *
 * ****************************************
 */
package org.mastermind;

import java.util.ArrayList;
import java.util.List;

public class MiniMaxPlayer implements CodeBreakerInterface {
    /** list of possible answers */
    private List<int[]> possible = new ArrayList<>();

    /** lastGuess made by MiniMaxPlayer */
    private int[] lastGuess;

    /** total guesses made by MiniMaxPlayer */
    private int guessCount = 0;

    /**
     * sets up all possible guesses
     * @author lmb042
     */
    public MiniMaxPlayer(){
        for (int thousand = GameManager.LOWER_BOUND; thousand < GameManager.UPPER_BOUND + 1; thousand++){
            for (int hundred = GameManager.LOWER_BOUND; hundred < GameManager.UPPER_BOUND + 1; hundred++){
                for (int ten = GameManager.LOWER_BOUND; ten < GameManager.UPPER_BOUND + 1; ten++) {
                    for (int one = GameManager.LOWER_BOUND; one < GameManager.UPPER_BOUND + 1; one++) {
                        int[] tempNum = {thousand, hundred, ten, one};
                        possible.add(tempNum);
                    }
                }
            }
        }
    }

    /**
     * Takes guess from list of possible guesses
     * @return int[] with digits representing guess
     * @author lmb042
     */
    @Override
    public int[] getGuess(){
        if (guessCount == 0){
            lastGuess = possible.get(7);
            possible.remove(7);
        }
        else {
            lastGuess = possible.get(0);
            possible.remove(0);
        }
        guessCount += 1;
        return lastGuess;
    }

    /**
     *
     * @return {@link #guessCount}
     * @author lmb042
     */
    @Override
    public int getGuessCount() {
        return guessCount;
    }

    /**
     * takes in answer and deletes codes from {@link #possible} that are now
     * impossible given new information
     * @param answer a string containing
     *               result symbols.
     * @author lmb042
     */
    @Override
    public void receiveResult(String answer){
        if (answer.contains("+") || answer.contains("*")){
            for (int t = 0; t < possible.size(); t++){
                if (!intArrayContains(lastGuess[0], possible.get(t)) && !intArrayContains(lastGuess[1], possible.get(t)) && !intArrayContains(lastGuess[2], possible.get(t)) && !intArrayContains(lastGuess[3], possible.get(t))) {
                    possible.remove(t);
                    break;
                }

            }
        }
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
}