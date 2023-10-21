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

    private int[] lastGuess;

    private int guessCount = 0;

    /**
     * sets up all possible guesses
     */
    public void main(){
        int[] tempNum = new int[4];
        for (int thousand = GameManager.LOWER_BOUND; thousand < GameManager.UPPER_BOUND + 1; thousand++){
            tempNum[0] = thousand;
            for (int hundred = GameManager.LOWER_BOUND; hundred < GameManager.UPPER_BOUND + 1; hundred++){
                tempNum[1] = hundred;
                for (int ten = GameManager.LOWER_BOUND; ten < GameManager.UPPER_BOUND + 1; ten++) {
                    tempNum[2] = ten;
                    for (int one = GameManager.LOWER_BOUND; one < GameManager.UPPER_BOUND + 1; one++) {
                        tempNum[3] = one;
                        possible.add(tempNum);
                    }
                }
            }
        }
    }

    @Override
    public int[] getGuess(){
        lastGuess = possible.get(0);
        possible.remove(0);
        guessCount += 1;
        return lastGuess;
    }

    @Override
    public int getGuessCount() {
        return guessCount;
    }

    public void receiveResult(String answer){
        if (answer.contains("+") || answer.contains("*")){
            for (int t = 0; t < possible.size(); t++){
                for (int i = 0; i < GameManager.CODE_LENGTH; i++){
                    if (!intArrayContains(lastGuess[i], possible.get(t))){
                        possible.remove(t);
                        break;
                    }
                }
            }
        }
    }

    public boolean intArrayContains(int intToCheckFor, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == intToCheckFor) {
                return true;
            }
        }
        return false;
    }
}