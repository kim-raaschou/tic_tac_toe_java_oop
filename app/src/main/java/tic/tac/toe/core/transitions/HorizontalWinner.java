package tic.tac.toe.core.transitions;

import tic.tac.toe.core.GameState;
import tic.tac.toe.core.GameStateTransition;

public class HorizontalWinner implements GameStateTransition{

    private final String[] scores;
    private final String player;

    public HorizontalWinner(String[] scores, String player) {
        this.scores = scores;
        this.player = player;
    }
    @Override
    public GameState Execute() {
        if (scores[0] == scores[1] && scores[0] == scores[2])
            return GameState.Winner(player);

        if (scores[3] == scores[4] && scores[3] == scores[5])
            return GameState.Winner(player);

        if (scores[6] == scores[7] && scores[6] == scores[8])
            return GameState.Winner(player);

        return null;
    }
}
