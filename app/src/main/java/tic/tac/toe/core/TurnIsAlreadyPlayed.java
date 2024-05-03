package tic.tac.toe.core;

import java.util.List;

public class TurnIsAlreadyPlayed implements WhenSomethingWentWrong {

    private final String[] scores;
    private final String player;
    private final int turn;

    public TurnIsAlreadyPlayed(String[] scores, int turn, String player) {
        this.scores = scores;
        this.turn = turn;
        this.player = player;
    }

    @Override
    public TicTacToeGameState Assert() {
        return List.of("X", "O").contains(scores[turn - 1])
                ? TicTacToeGameState.TurnAlreadyTaken(player)
                : null;
    }

}
