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
 * Class: GameManager
 *
 * Description:
 * Creates and manages new game instances,
 * and asks user about following retries.
 * ****************************************
 */
package org.mastermind;

import java.util.Scanner;

public class GameManager {
    /** Maximum length of code to generate/guess */
    public static final int CODE_LENGTH = 4;

    /** The smallest number that one can guess */
    public static final int LOWER_BOUND = 1;

    /** The largest number that one can guess */
    public static final int UPPER_BOUND = 6;

    /** Number of possible guesses */
    public static final int MAX_GUESSES = 12;

    /** State boolean confirming game should be in progress */
    public static boolean done = false;

    /** Board object to be used throughout play */
    public static Board board;

    /** CodeMaker object to be used throughout play */
    public static CodeMaker codeMaker;

    /** Public Scanner instance to be used throughout the program */
    public static Scanner scnr;

    /**
     * Main game run logic-
     * Creates a new terminal scanner, then sets up a game and runs it.
     * After the game ends, GameManager prompts user for retry.
     * @author sso005
     * */
    public static void main(String[] args) {
        scnr = new Scanner(System.in);

        while (!done) {
            setUpGame();
            board.runGame();
            promptForRetry();
        }

        scnr.close();
    }

    /**
     * Sets up a new game, instantiates new Board
     * and CodeMaker objects, then greets
     * user with instructions and bounds.
     * @author lmb042
     */
    public static void setUpGame() {
        // Create new CodeMaker and Board instances
        codeMaker = new CodeMaker(CODE_LENGTH, LOWER_BOUND, UPPER_BOUND);
        board = new Board(codeMaker, MAX_GUESSES);
        // Greet user, and give instructions.
        System.out.printf("Guess my code, using numbers between %d and %d. You have %d guesses.\n", LOWER_BOUND, UPPER_BOUND, MAX_GUESSES);
    }

    /**
     * Asks user if they want to play again.
     * Sanitizes / checks for bad input and re-prompts if necessary.
     * @author sso005 & lmb042
     */
    public static void promptForRetry() {
        // Clear the input buffer
        scnr.nextLine();
        System.out.print("Would you like to play again? [Y|N] ");
        String sResult = scnr.nextLine().strip();
        if (sResult.matches("[yYnN]")) {
            if (sResult.matches("[nN]")) {
                done = true;
                System.out.println("Goodbye!");
            }
        } else {
            System.out.print("Invalid input. ");
        }
    }
}
