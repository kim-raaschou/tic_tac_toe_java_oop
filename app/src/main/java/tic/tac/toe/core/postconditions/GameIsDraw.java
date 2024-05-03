package tic.tac.toe.core.postconditions;

import java.util.Arrays;
import java.util.List;

import tic.tac.toe.core.GameScore;
import tic.tac.toe.core.GameState;
import tic.tac.toe.core.GameStateTransition;

public class GameIsDraw implements GameStateTransition {

    private final String[] scores;

    public GameIsDraw(GameScore scores) {
        this.scores = scores.toArray();
    }

    @Override
    public GameState Execute() {
        return gameIsDRaw() ? GameState.Gameover() : null;
    }

    private boolean gameIsDRaw() {
        return Arrays.stream(scores).noneMatch(score -> {
            return !List.of("X", "O").contains(score);
        });
    }
}
