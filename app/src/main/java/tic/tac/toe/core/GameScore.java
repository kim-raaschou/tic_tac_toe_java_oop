package tic.tac.toe.core;

public class GameScore {
    private String[] scores = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    public GameScore withScoreX(int turn) {
        scores[turn - 1] = "X";
        return this;
    }

    public GameScore withScoreO(int turn) {
        scores[turn - 1] = "O";
        return this;
    }

    public String[] toArray() {
        return scores;
    }

    public void takeTurn(String player, int turn) {
        scores[turn - 1] = player;
    }
}
