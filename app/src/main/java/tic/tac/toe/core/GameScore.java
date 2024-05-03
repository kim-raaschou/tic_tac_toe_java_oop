package tic.tac.toe.core;

import java.util.Arrays;
import java.util.List;

import tic.tac.toe.core.postconditions.DiagonalsWinner;
import tic.tac.toe.core.postconditions.GameIsDraw;
import tic.tac.toe.core.postconditions.HorizontalWinner;
import tic.tac.toe.core.postconditions.VerticalWinner;

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

       
        // String haswinner = hasLineWinner();
        // if (haswinner != null) {
        // return GameState.Winner(player);
        // }

        // haswinner = hasColumnWinner();
        // if (haswinner != null) {
        // return GameState.Winner(player);
        // }

        // haswinner = hasDiagonalWinner();
        // if (haswinner != null) {
        // return GameState.Winner(player);
        // }

        // if (gameIsDraw()) {
        // return GameState.Gameover();
        // }
        //return GameState.TakeATurn();
    }

    private final boolean gameIsDraw() {

        return Arrays.stream(scores).noneMatch(score -> {
            return !List.of("X", "O").contains(score);
        });
    }

    private String hasDiagonalWinner() {
        if (scores[0] == scores[4] && scores[0] == scores[8])
            return scores[0];

        if (scores[2] == scores[4] && scores[2] == scores[6])
            return scores[2];

        return null;
    }

    private String hasLineWinner() {
        if (scores[0] == scores[1] && scores[0] == scores[2])
            return scores[1];

        if (scores[3] == scores[4] && scores[3] == scores[5])
            return scores[4];

        if (scores[6] == scores[7] && scores[6] == scores[8])
            return scores[7];

        return null;
    }

    private String hasColumnWinner() {
        if (scores[0] == scores[3] && scores[0] == scores[6])
            return scores[1];

        if (scores[1] == scores[4] && scores[1] == scores[7])
            return scores[4];

        if (scores[2] == scores[5] && scores[2] == scores[8])
            return scores[7];

        return null;
    }
}
