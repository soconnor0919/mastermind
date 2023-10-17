package org.mastermind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private CodeMaker codeMaker;
    private Board board;

    @BeforeEach
    public void setUp() {
        codeMaker = new CodeMaker(4, 1, 6); // Example values for length, lowerBound, and upperBound
        board = new Board(codeMaker, 1); // Example value for totalGuesses

        // Mock GameManager.scnr for promptForGuess method
        GameManager.scnr = new Scanner(System.in);
    }

    /**
     * tests stringToInt Array method
     * which is important b/c we use it to process input
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
     */
    @Test
    public void testDigitsWithinBounds() {
        assertTrue(board.digitsWithinBounds("1234", 1, 6));
        assertFalse(board.digitsWithinBounds("1234", 5, 6));
        assertFalse(board.digitsWithinBounds("abc", 1, 6));
    }
}