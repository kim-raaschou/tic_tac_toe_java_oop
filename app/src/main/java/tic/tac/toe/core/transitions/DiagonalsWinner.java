package tic.tac.toe.core.transitions;

import tic.tac.toe.core.GameState;
import tic.tac.toe.core.GameStateTransition;

public class DiagonalsWinner implements GameStateTransition {

    private final String[] scores;
    private String player;

    public DiagonalsWinner(String[] scores, String player) {
        this.scores = scores;
        this.player = player;
    }

    @Override
    public GameState Execute() {
        if (scores[0] == scores[4] && scores[0] == scores[8])
            return GameState.Winner(player);

        if (scores[2] == scores[4] && scores[2] == scores[6])
            return GameState.Winner(player);

        return null;
    }

}
