package tic.tac.toe.core.transitions;

import java.util.Arrays;
import java.util.List;

import tic.tac.toe.core.GameState;
import tic.tac.toe.core.GameStateTransition;

public class GameIsDraw implements GameStateTransition {

    private final String[] scores;

    public GameIsDraw(String[] scores) {
        this.scores = scores;
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
