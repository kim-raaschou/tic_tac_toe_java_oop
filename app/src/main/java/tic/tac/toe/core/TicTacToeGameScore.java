package tic.tac.toe.core;

public record TicTacToeGameScore(
        String message,
        TicTacToeGameStateEnum state) {

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

    private TicTacToeGameScore() {
        this("", TicTacToeGameStateEnum.Unknown);
    }

    public static final TicTacToeGameScore Empty() {
        return new TicTacToeGameScore();
    }

    public static TicTacToeGameScore TakeATurn() {
        return new TicTacToeGameScore("Next turn", TicTacToeGameStateEnum.TakeAnotherTuren);
    }

    public static TicTacToeGameScore Winner(String player) {
        String message = String.format("We have a winner !!!! Player %s wins", player);
        return new TicTacToeGameScore(message, TicTacToeGameStateEnum.GameHasAWinner);
    }

    public static TicTacToeGameScore TurnAlreadyTaken() {
        final String message = "The turn is already taken.";
        return new TicTacToeGameScore(message, TicTacToeGameStateEnum.TurnAlreadyTaken);
    }

    public static TicTacToeGameScore Gameover() {
        return new TicTacToeGameScore("Game over. It's a draw", TicTacToeGameStateEnum.GameIsDraw);
    }

    public static TicTacToeGameScore SomethingWentWrong(String message) {
        return new TicTacToeGameScore(message, TicTacToeGameStateEnum.SomeThingWentWrong);
    }
}