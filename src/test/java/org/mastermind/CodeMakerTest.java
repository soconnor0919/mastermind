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
 * Class: CodeMakerTest
 *
 * Description:
 * Tests the important functions of the
 * CodeMaker class.
 * ****************************************
 */

package org.mastermind;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A set of tests for the CodeMaker class.
 */
public class CodeMakerTest {
    private CodeMaker codeMaker;

    @BeforeEach
    public void setUp() {
        codeMaker = new CodeMaker(4, 1, 6); // Example values for length, lowerBound, and upperBound
    }

    /**
     * tests generateNewCode which is important b/c
     * we create semi-unique new games with it
     * @author lmb042
     */
    @Test
    public void testGenerateNewCode() {
        codeMaker.generateNewCode();
        int[] gameCode = codeMaker.gameCode;
        assertNotNull(gameCode);
        assertEquals(4, gameCode.length);
        for (int code : gameCode) {
            assertTrue(code >= codeMaker.getLowerBound() && code <= codeMaker.getUpperBound());
        }
    }

    /**
     * tests checkGuess method which is
     * important b/c it creates the series of
     * +, -, * that guide the user through the game
     * @author lmb042
     */
    @Test
    public void testCheckGuess() {
        int[] gameCode = codeMaker.gameCode;
        int notUsed = 1;
        boolean notUsedFound = false;

        while (!notUsedFound){
            if(codeMaker.intArrayContains(notUsed, gameCode)){
                notUsed += 1;
            }
            else{
                notUsedFound = true;
            }
        }

        assertEquals("****", codeMaker.checkGuess(gameCode));

        int[] guess2 = {gameCode[0], gameCode[1], notUsed, notUsed};
        assertEquals("**--", codeMaker.checkGuess(guess2));
    }

    /**
     * tests intArrayContains which is important
     * b/c we use it in our checkGuess method
     * @author lmb042
     */
    @Test
    public void testIntArrayContains() {
        int[] arr = {1, 2, 3, 4};
        assertTrue(codeMaker.intArrayContains(2, arr));
        assertTrue(codeMaker.intArrayContains(4, arr));
        assertFalse(codeMaker.intArrayContains(5, arr));
    }
}