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

    /** Number of games for solver methods */
    public static final int GAME_NUM = 1000;

    /** Instance variable for amount of games */
    public static int gameNum;

    /** State boolean confirming game should be in progress */
    public static boolean done = false;

    /** Board object to be used throughout play */
    public static Board board;

    /** CodeMaker object to be used throughout play */
    public static CodeMaker codeMaker;

    /** CodeBreaker object to be used throughout play */
    public static CodeBreakerInterface codeBreaker;

    /** Public Scanner instance to be used throughout the program */
    public static Scanner scnr;

    /** gameMode object storing type of game */
    public static GameMode gameMode;

    /** Total time running (for stats) */
    private static double totalTime;

    /**
     * Main game run logic-
     * Creates a new terminal scanner, then sets up a game and runs it.
     * After the game ends, GameManager prompts user for retry.
     * @author sso005
     * */
    public static void main(String[] args) {
        scnr = new Scanner(System.in);
        gameMode = promptForGameMode();
        totalTime = 0;

        if (gameMode == GameMode.HUMAN_PLAYER) {
            gameNum = 1;
        } else {
            gameNum = GAME_NUM;
        }

        for (int i = 0; i < gameNum; i++) {
            switch(gameMode) {
                case RANDOM_SOLVER:
                    codeBreaker = new RandomPlayer();
                    break;
                case MINIMAX_SOLVER:
                    //MiniMaxPlayer cb = new MiniMaxPlayer();
                    break;
                case EXTRA_SOLVER:
                    //codeBreaker = new RandomPlayer();
                    break;
                case HUMAN_PLAYER:
                default:
                    System.out.printf("Guess my code, using numbers between %d and %d. You have %d guesses.\n", LOWER_BOUND, UPPER_BOUND, MAX_GUESSES);
                    codeBreaker = new HumanPlayer();
                    break;
            }

            codeMaker = new CodeMaker(CODE_LENGTH, LOWER_BOUND, UPPER_BOUND);
            board = new Board(codeMaker, codeBreaker);
            board.runGame();
            totalTime += board.gameTime;
        }

        scnr.close();
    }

    /**
     * Asks user if they want to play interactively,
     * or to use the solver functions. Sanitizes /
     * 9checks for bad input and re-prompts if necessary.
     * @author sso005 & lmb042
     */
    private static GameMode promptForGameMode() {
        GameMode gm = GameMode.HUMAN_PLAYER;
        System.out.println("How would you like to play?");
        System.out.print("Interactive mode or solver mode? [I|S]: ");
        String sResult = scnr.nextLine().strip();
        if (sResult.matches("[iIsS]")) {
            if (!sResult.matches("[iI]")) {
                gm = promptForSolverType();
            }
        } else {
            System.out.print("Invalid input. ");
        }
        return gm;
    }

    /**
     * Asks user which type of solver to use.
     * @author sso005 & lmb042
     */
    private static GameMode promptForSolverType() {
        GameMode gm = GameMode.RANDOM_SOLVER;
        System.out.print("Random solver, MiniMax solver, or Untitled solver? [R|M|U]: ");
        String sResult = scnr.nextLine().strip();
        if (sResult.matches("[rRmMuU]")) {
            if (sResult.matches("[uU]")) {
                gm = GameMode.EXTRA_SOLVER;
            } else if (sResult.matches("[mM]")){
                gm = GameMode.MINIMAX_SOLVER;
            }
        } else {
            System.out.print("Invalid input. ");
        }
        return gm;
    }

    /**
     * Returns user-provided GameMode
     * @return gameMode
     */
    private static GameMode getGameMode() {
        return gameMode;
    }
}
