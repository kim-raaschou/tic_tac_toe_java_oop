package tic.tac.toe.core.transitions;

import tic.tac.toe.core.GameState;
import tic.tac.toe.core.GameStateTransition;
import tic.tac.toe.core.Player;

public class VerticalWinner implements GameStateTransition {

    private final String[] scores;
    private Player player;

    public VerticalWinner(String[] scores, Player player) {
        this.scores = scores;
        this.player = player;
    }

    @Override
    public GameState Execute() {
        if (scores[0] == scores[3] && scores[0] == scores[6])
            return GameState.Winner(player.getName());

        if (scores[1] == scores[4] && scores[1] == scores[7])
            return GameState.Winner(player.getName());

        if (scores[2] == scores[5] && scores[2] == scores[8])
            return GameState.Winner(player.getName());

        return null;
    }

}
