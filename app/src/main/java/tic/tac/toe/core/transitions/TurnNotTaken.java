package tic.tac.toe.core.transitions;

import java.util.List;

import tic.tac.toe.core.GameStateTransition;
import tic.tac.toe.core.Player;
import tic.tac.toe.core.GameState;

public class TurnNotTaken implements GameStateTransition {

    private final String[] scores;
    private final Player player;
    private final int turn;

    public TurnNotTaken(int turn, Player player, String[] scores) {
        this.turn = turn;
        this.player = player;
        this.scores = scores;
    }

    @Override
    public GameState Execute() {
        return List.of("X", "O").contains(scores[turn - 1])
                ? GameState.TurnAlreadyTaken(player.getName())
                : null;
    }

}
