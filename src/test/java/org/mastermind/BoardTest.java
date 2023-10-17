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
 * Class: BoardTest
 *
 * Description:
 * Tests the important functions of the
 * Board class.
 * ****************************************
 */
package org.mastermind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A set of tests for the Board class.
 * @author lmb042
 */
public class BoardTest {
    private CodeMaker codeMaker;
    private Board board;

    @BeforeEach
    public void setUp() {
        codeMaker = new CodeMaker(4, 1, 6);
        board = new Board(codeMaker, 1);
    }

    /**
     * tests stringToInt Array method
     * which is important b/c we use it to process input
     * @author lmb042
     */
    @Test
    public void testStringToIntArray() {
        String input = "1234";
        int[] result = board.stringToIntArray(input);
        assertNotNull(result);
        assertEquals(4, result.length);
        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
        assertEquals(3, result[2]);
        assertEquals(4, result[3]);
    }

    /**
     * tests digitsWithinBounds method because
     * we use that to error check input
     * @author lmb042
     */
    @Test
    public void testDigitsWithinBounds() {
        assertTrue(board.digitsWithinBounds("1234", 1, 6));
        assertFalse(board.digitsWithinBounds("1234", 5, 6));
        assertFalse(board.digitsWithinBounds("abc", 1, 6));
    }
}