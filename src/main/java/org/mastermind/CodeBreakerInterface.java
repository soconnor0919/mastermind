package org.mastermind;

public interface CodeBreakerInterface {
    public int[] getGuess();

    public void receiveResult(String result);

    public default boolean hasRemainingGuesses() {
        return true;
    }
}
