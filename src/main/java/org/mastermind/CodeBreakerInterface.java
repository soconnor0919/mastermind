package org.mastermind;

public interface CodeBreakerInterface {
    /**
     * Gets guess from CodeBreaker
     * @return an integer array containing n integers.
     */
    public int[] getGuess();

    /**
     * Retrieves a result, and processes it.
     * @param result a string containing
     *               result symbols.
     */
    public void receiveResult(String result);

    /**
     * Returns true if the CodeBreaker has remaining
     * guesses. If automated, default to true.
     * @return
     */
    public default boolean hasRemainingGuesses() {
        return true;
    }

    /**
     * Returns total amount of guesses made by player.
     * @return guessCount
     */
    public int getGuessCount();
}
