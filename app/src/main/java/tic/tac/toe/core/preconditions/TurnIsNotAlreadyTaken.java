package tic.tac.toe.core.preconditions;

import java.util.List;

import tic.tac.toe.core.GameStateTransition;
import tic.tac.toe.core.GameState;

public class TurnIsNotAlreadyTaken implements GameStateTransition {

    private final String[] scores;
    private final String player;
    private final int turn;

    public TurnIsNotAlreadyTaken(int turn, String player, String[] scores) {
        this.turn = turn;
        this.player = player;
        this.scores = scores;
    }

    @Override
    public GameState Execute() {
        return List.of("X", "O").contains(scores[turn - 1])
                ? GameState.TurnAlreadyTaken(player)
                : null;
    }

}
