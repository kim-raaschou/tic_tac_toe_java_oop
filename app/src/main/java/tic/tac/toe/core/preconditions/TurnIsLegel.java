package tic.tac.toe.core.preconditions;

import tic.tac.toe.core.GameStateCondition;
import tic.tac.toe.core.GameState;

public class TurnIsLegel implements GameStateCondition {

    private final int turn;
    private final String message = """
            Invalid turn!
            Turn must be a number between 1 and 9.
            """;

    public TurnIsLegel(int turn) {
        this.turn = turn;
    }

    @Override
    public GameState Verify() {
        return turn < 1 || turn > 9
                ? GameState.SomethingWentWrong(message)
                : null;
    }

}
