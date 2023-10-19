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
 * Class: Board
 *
 * Description:
 * Handles main game logic, and runs the game
 * itself.
 * ****************************************
 */
package org.mastermind;

public class Board {
    /** Stores number of guesses made */
    private int guessCount;

    /** Integer array used to store each guess as individual elements */
    private int[] guess;

    /** CodeMaker object to be used throughout the game */
    private final CodeMaker cm;

    /** CodeBreaker object to be used throughout the game */
    private final CodeBreakerInterface cb;

    /** Total time in seconds of game */
    public double gameTime;

    /** Stores guess result */
    String result;

    /**
     * Initializes a new board, with zero guesses.
     * @param codeMaker CodeMaker object used throughout
     * @param codeBreaker CodeBreaker object used throughout
     * @author sso005 & lmb042
     */
    public Board(CodeMaker codeMaker, CodeBreakerInterface codeBreaker) {
        this.cm = codeMaker;
        this.cb = codeBreaker;
        this.guessCount = 0;
    }

    /**
     * Main game logic
     * Creates a new code to guess, then while eligible to
     * guess, prompts the user to enter a guess. Guess is then
     * checked by the CodeMaker, and results are reported.
     * @author sso005
     */
    public void runGame() {
        cm.generateNewCode();
        long startTime = System.nanoTime();
        while (!cm.gameWon() && cb.hasRemainingGuesses()) {
            guess = cb.getGuess();
            result = cm.checkGuess(guess);
            cb.receiveResult(result);
        }
        long endTime = System.nanoTime();
        this.gameTime = ((double) endTime - (double) startTime)/1000000;
//        endGame();
    }

    private void endGame() {
        if (cb.getClass() == HumanPlayer.class) {
            System.out.println("GAME OVER! RESULTS:");

        } else {
            System.out.println("RESULTS:");
            System.out.println(cb.getClass().getName() + " - Statistics:");

        }
    }
}
