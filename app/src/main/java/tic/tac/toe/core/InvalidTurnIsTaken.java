package tic.tac.toe.core;

public class InvalidTurnIsTaken implements WhenSomethingWentWrong {

    private final int turn;
    private final String message = """
            Invalid turn!
            Turn must be a number between 1 and 9.
            """;

    public InvalidTurnIsTaken(int turn) {
        this.turn = turn;
    }

    @Override
    public TicTacToeGameState Assert() {
        return turn < 1 || turn > 9
                ? TicTacToeGameState.SomethingWentWrong(message)
                : null;
    }

}
