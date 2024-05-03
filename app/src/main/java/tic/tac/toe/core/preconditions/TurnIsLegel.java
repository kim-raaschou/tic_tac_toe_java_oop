package tic.tac.toe.core.preconditions;

import tic.tac.toe.core.Precondition;
import tic.tac.toe.core.TicTacToeGameState;

public class TurnIsLegel implements Precondition {

    private final int turn;
    private final String message = """
            Invalid turn!
            Turn must be a number between 1 and 9.
            """;

    public TurnIsLegel(int turn) {
        this.turn = turn;
    }

    @Override
    public TicTacToeGameState Verify() {
        return turn < 1 || turn > 9
                ? TicTacToeGameState.SomethingWentWrong(message)
                : null;
    }

}
