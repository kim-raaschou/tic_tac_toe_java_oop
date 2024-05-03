package tic.tac.toe.core.preconditions;

import java.util.List;

import tic.tac.toe.core.GameStateCondition;
import tic.tac.toe.core.TicTacToeGameState;

public class TurnIsNotAlreadyTaken implements GameStateCondition {

    private final String[] scores;
    private final String player;
    private final int turn;

    public TurnIsNotAlreadyTaken(int turn, String player, String[] scores) {
        this.turn = turn;
        this.player = player;
        this.scores = scores;
    }

    @Override
    public TicTacToeGameState Verify() {
        return List.of("X", "O").contains(scores[turn - 1])
                ? TicTacToeGameState.TurnAlreadyTaken(player)
                : null;
    }

}
