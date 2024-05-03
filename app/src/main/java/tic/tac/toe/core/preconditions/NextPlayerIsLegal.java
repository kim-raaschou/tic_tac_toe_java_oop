package tic.tac.toe.core.preconditions;

import java.util.List;

import tic.tac.toe.core.GameStateCondition;
import tic.tac.toe.core.TicTacToeGameState;

public class NextPlayerIsLegal implements GameStateCondition {

    private final String message = """
            Invalid player %s took turn - try again
            """;
    private final String player;

    public NextPlayerIsLegal(String player) {
        this.player = player;
    }

    @Override
    public TicTacToeGameState Verify() {
        return List.of("X", "O").contains(player)
                ? null
                : invalidPlayerGameState();
    }

    private TicTacToeGameState invalidPlayerGameState() {
        String message = String.format(this.message, player);
        return TicTacToeGameState.SomethingWentWrong(message);
    }

}