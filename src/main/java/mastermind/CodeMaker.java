/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King
 *
 * Name: O'Connor, Sean
 * Section: 09:00
 * Date: 10/12/23
 *
 * Project: csci205_hw
 * Package: mastermind
 * Class: CodeMaker
 *
 * Description:
 *
 * ****************************************
 */
package mastermind;

import java.util.Random;

public class CodeMaker {


    private final int[] gameCode;
    private final Random random;

    private static int length, upperBound, lowerBound;

    public CodeMaker(int len, int lBound, int uBound) {
        random = new Random();
        length = len;
        lowerBound = lBound;
        upperBound = uBound;
        gameCode = new int[length];
    }

    public void generateNewCode() {
        for (int i = 0; i < length - 1; i++) {
            gameCode[i] = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        }
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }
}
