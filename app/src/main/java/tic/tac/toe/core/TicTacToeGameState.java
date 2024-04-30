package tic.tac.toe.core;

public record TicTacToeGameState(String message, TicTacToeGameStateEnum state) {

    public enum TicTacToeGameStateEnum {
        Unknown(false, true),
        GameHasAWinner(true, false),
        GameIsDraw(true, false),
        TurnAlreadyTaken(false, false),
        TakeAnotherTuren(false, true),
        SomeThingWentWrong(false, false);

        private final boolean gameHasEnded;
        private final boolean switchTurn;

        private TicTacToeGameStateEnum(boolean gameHasEnded, boolean switchPlayer) {
            this.gameHasEnded = gameHasEnded;
            this.switchTurn = switchPlayer;
        }

        public boolean gameHasEnded() {
            return gameHasEnded;
        }

        public boolean switchPlayer() {
            return switchTurn;
        }
    }

    public static final TicTacToeGameState Empty() {
        return new TicTacToeGameState("", TicTacToeGameStateEnum.Unknown);
    }

    public static TicTacToeGameState TakeATurn() {
        return new TicTacToeGameState("Next turn", TicTacToeGameStateEnum.TakeAnotherTuren);
    }

    public static TicTacToeGameState Winner(String player) {
        String message = String.format("We have a winner !!!! Player %s wins", player);
        return new TicTacToeGameState(message, TicTacToeGameStateEnum.GameHasAWinner);
    }

    public static TicTacToeGameState TurnAlreadyTaken(String player) {
        final String message = "The turn is already taken.";
        return new TicTacToeGameState(message, TicTacToeGameStateEnum.TurnAlreadyTaken);
    }

    public static TicTacToeGameState Gameover() {
        return new TicTacToeGameState("Game over. It's a draw", TicTacToeGameStateEnum.GameIsDraw);
    }

    public static TicTacToeGameState SomethingWentWrong(String message) {
        return new TicTacToeGameState(message, TicTacToeGameStateEnum.SomeThingWentWrong);
    }
}