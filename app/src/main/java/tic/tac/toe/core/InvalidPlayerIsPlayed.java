package tic.tac.toe.core;

import java.util.List;

public class InvalidPlayerIsPlayed implements WhenSomethingWentWrong {

    private final String message = """
            Invalid player %s took turn - try again
            """;
    private final String player;

    public InvalidPlayerIsPlayed(String player) {
        this.player = player;
    }

    @Override
    public TicTacToeGameState Assert() {
        return List.of("X", "O").contains(player)
                ? null
                : invalidPlayerGameState();
    }

    private TicTacToeGameState invalidPlayerGameState() {
        String message = String.format(this.message, player);
        return TicTacToeGameState.SomethingWentWrong(message);
    }

}
