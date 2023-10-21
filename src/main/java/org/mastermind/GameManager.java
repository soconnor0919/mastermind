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

import java.util.ArrayList;
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

    /** Instance variable for number of games */
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
    public static long totalTime;

    /** ArrayList containing amounts of turns */
    public static ArrayList<Integer> turnCounts;

    /**
     * Main game run logic-
     * Creates a new terminal scanner, then sets up a game and runs it.
     * After the game ends, GameManager prompts user for retry.
     * @author sso005
     * */
    public static void main(String[] args) {
        scnr = new Scanner(System.in);
        totalTime = 0;
        turnCounts = new ArrayList<>();
        gameMode = promptForGameMode();

        if (gameMode == GameMode.HUMAN_PLAYER) {
            gameNum = 1;
        } else {
            gameNum = promptForIterations();
        }

        for (int i = 0; i < gameNum; i++) {
            switch(gameMode) {
                case RANDOM_SOLVER:
                    codeBreaker = new RandomPlayer();
                    break;
                case MINIMAX_SOLVER:
                    codeBreaker = new MiniMaxPlayer();
                    break;
                case BRUTEFORCE_SOLVER:
                    codeBreaker = new BruteForcePlayer();
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
            updateRunTime(board.getRunTime());
            updateTurnCounts(codeBreaker.getGuessCount());
        }

        if (getGameMode() != GameMode.HUMAN_PLAYER) {
            displayResults();
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
        System.out.print("Random solver, MiniMax solver, or BruteForce solver? [R|M|B]: ");
        String sResult = scnr.nextLine().strip();
        if (sResult.matches("[rRmMbB]")) {
            if (sResult.matches("[bB]")) {
                gm = GameMode.BRUTEFORCE_SOLVER;
            } else if (sResult.matches("[mM]")){
                gm = GameMode.MINIMAX_SOLVER;
            }
        } else {
            System.out.print("Invalid input. ");
        }
        return gm;
    }

    /**
     * Asks user how many iterations to run.
     * @author sso005 & lmb042
     */
    private static int promptForIterations() {
        boolean isDone = false;
        int result = 1;
        while (!isDone) {
            System.out.print("How many iterations would you like to run? ");
            String sResult = scnr.nextLine().strip();
            if (sResult.matches("[0-9]+")) {
                result = Integer.parseInt(sResult);
                isDone = true;
            } else {
                System.out.print("Invalid input. ");
            }
        }
        return result;
    }

    /**
     * Returns user-provided GameMode
     * @return gameMode
     * @author sso005
     */
    private static GameMode getGameMode() {
        return gameMode;
    }

    /**
     * Update's game's runtime
     */
    private static void updateRunTime(long time) {
        totalTime += time;
    }

    /**
     * Returns total time in seconds.
     * @author lmb042
     */
    private static double getTotalTime() {
        return (double)totalTime / 1000000000;
    }

    /**
     * Updates stored turn count based on
     * a game's values.
     * @author sso005
     */
    private static void updateTurnCounts(int turnCount) {
        turnCounts.add(turnCount);
    }

    /**
     * Calculates and returns the number of
     * turns that the game with the maximum
     * number of turns took.
     * @return maxTurns
     * @author sso005
     */
    private static int getMaxTurns() {
        int maxTurns = 0;
        for (int turns : turnCounts) {
            if (turns > maxTurns) {
                maxTurns = turns;
            }
        }
        return maxTurns;
    }

    /**
     * Calculates and returns the number of
     * turns that the game with the minimum
     * number of turns took.
     * @return minTurns
     * @author sso005
     */
    private static int getMinTurns() {
        int minTurns = 10000000;
        for (int turns : turnCounts) {
            if (turns < minTurns) {
                minTurns = turns;
            }
        }
        return minTurns;
    }

    /**
     * Returns the average number of turns
     * from the arraylist.
     * @return avgTurns
     * @author sso005
     */
    private static double getAvgTurns() {
        int sum = 0;
        int count = 0;
        for (int turns : turnCounts) {
            sum += turns;
            count++;
        }
        return (double)sum/(double)count;
    }


    /**
     * Displays statistics for solver methods.
     */
    private static void displayResults() {
        System.out.println("RESULTS:");
        System.out.println(getGameMode().getName() + " - Statistics:");
        System.out.println("Number of games: " + gameNum);
        System.out.printf("Average: %.1f turns%n", getAvgTurns());
        System.out.println("Shortest: " + getMinTurns() + " turns");
        System.out.println("Longest: " + getMaxTurns() + " turns");
        System.out.printf("TOTAL TIME: %.2f seconds%n", getTotalTime());
        System.out.println("Goodbye!");
    }
}
