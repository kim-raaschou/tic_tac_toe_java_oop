package tic.tac.toe.core.postconditions;

import tic.tac.toe.core.GameScore;
import tic.tac.toe.core.GameState;
import tic.tac.toe.core.GameStateTransition;

public class VerticalWinner implements GameStateTransition {

    private final String[] scores;
    private String player;

    public VerticalWinner(GameScore scores, String player) {
        this.scores = scores.toArray();
        this.player = player;
    }

    @Override
    public GameState Execute() {
        if (scores[0] == scores[3] && scores[0] == scores[6])
            return GameState.Winner(player);

        if (scores[1] == scores[4] && scores[1] == scores[7])
            return GameState.Winner(player);

        if (scores[2] == scores[5] && scores[2] == scores[8])
            return GameState.Winner(player);

        return null;
    }

}
