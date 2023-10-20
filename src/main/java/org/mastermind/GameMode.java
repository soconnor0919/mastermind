/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King
 *
 * Name: O'Connor, Sean and Brackett, Lyman
 * Section: 09:00
 * Date: 10/19/23
 *
 * Project: csci205_hw
 * Package: org.mastermind
 * Class: GameMode
 *
 * Description:
 * Stores enumerated types for gameMode.
 * ****************************************
 */

package org.mastermind;

/**
 * Contains GameMode variables, each with a
 * String containing a name.
 */
public enum GameMode {
    HUMAN_PLAYER("HumanPlayer"),
    RANDOM_SOLVER("RandomSolver"),
    MINIMAX_SOLVER("MiniMaxSolver"),
    EXTRA_SOLVER("ExtraSolver");

    /** Name of gameMode */
    String name;

    /** Defines the gameMode */
    GameMode(String name) {
        this.name = name;
    }

    /** Returns game mode's name */
    public String getName() {
        return this.name;
    }
}
