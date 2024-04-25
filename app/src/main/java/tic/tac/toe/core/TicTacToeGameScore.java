package tic.tac.toe.core;

public record TicTacToeGameScore(
        String message,
        TicTacToeGameState state) {

    @SuppressWarnings("unused")
    private TicTacToeGameScore() {
        this(null, null);
    }

    public static TicTacToeGameScore TakeATurn() {
        return new TicTacToeGameScore("Next turn", TicTacToeGameState.TakeAnotherTuren);
    }

    public static TicTacToeGameScore Winner(String player) {
        String message = "Player" + player + " wins";
        return new TicTacToeGameScore(message, TicTacToeGameState.GameHasAWinner);
    }

    public static TicTacToeGameScore TurnAlreadyTaken(String turn) {
        String message = "The turn " + turn + " is already taken.";
        return new TicTacToeGameScore(message, TicTacToeGameState.TurnAlreadyTaken);
    }

    public static TicTacToeGameScore Gameover() {
        return new TicTacToeGameScore("No winner", TicTacToeGameState.GameIsDraw);
    }

    public static TicTacToeGameScore SomethingWentWrong(String message) {
        return new TicTacToeGameScore(message, TicTacToeGameState.SomeThingWentWrong);
    }
}