package tic.tac.toe.core;

public record TicTacToeGameScore(
        String message,
        TicTacToeGameState state) {

    private TicTacToeGameScore() {
        this("", TicTacToeGameState.Unknown);
    }

    public static final TicTacToeGameScore Empty(){
        return new TicTacToeGameScore();
    }

    public static TicTacToeGameScore TakeATurn() {
        return new TicTacToeGameScore("Next turn", TicTacToeGameState.TakeAnotherTuren);
    }

    public static TicTacToeGameScore Winner(String player) {
        String message = String.format("We have a winner !!!! Player %s wins", player);
        return new TicTacToeGameScore(message, TicTacToeGameState.GameHasAWinner);
    }

    public static TicTacToeGameScore TurnAlreadyTaken() {
        final String message = "The turn is already taken.";
        return new TicTacToeGameScore(message, TicTacToeGameState.TurnAlreadyTaken);
    }

    public static TicTacToeGameScore Gameover() {
        return new TicTacToeGameScore("Game over. It's a draw", TicTacToeGameState.GameIsDraw);
    }

    public static TicTacToeGameScore SomethingWentWrong(String message) {
        return new TicTacToeGameScore(message, TicTacToeGameState.SomeThingWentWrong);
    }
}