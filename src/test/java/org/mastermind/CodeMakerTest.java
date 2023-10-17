package org.mastermind;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CodeMakerTest {
    private CodeMaker codeMaker;

    @BeforeEach
    public void setUp() {
        codeMaker = new CodeMaker(4, 1, 6); // Example values for length, lowerBound, and upperBound
    }

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

        int[] guess1 = gameCode;
        assertEquals("****", codeMaker.checkGuess(guess1));

        int[] guess2 = {gameCode[0], gameCode[1], notUsed, notUsed};
        assertEquals("**--", codeMaker.checkGuess(guess2));
    }

    @Test
    public void testIntArrayContains() {
        int[] arr = {1, 2, 3, 4};
        assertTrue(codeMaker.intArrayContains(2, arr));
        assertTrue(codeMaker.intArrayContains(4, arr));
        assertFalse(codeMaker.intArrayContains(5, arr));
    }
}