package tic.tac.toe.core;

public record GameState(String message, TicTacToeGameStateEnum state) {

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

        public boolean isGameContinuable() {
            return !gameHasEnded
                    && this != TicTacToeGameStateEnum.TurnAlreadyTaken
                    && this != TicTacToeGameStateEnum.SomeThingWentWrong;
        }
    }

    public static final GameState Empty() {
        return new GameState("", TicTacToeGameStateEnum.Unknown);
    }

    public static GameState TakeATurn() {
        return new GameState("Next turn", TicTacToeGameStateEnum.TakeAnotherTuren);
    }

    public static GameState Winner(String player) {
        String message = String.format("We have a winner !!!! Player %s wins", player);
        return new GameState(message, TicTacToeGameStateEnum.GameHasAWinner);
    }

    public static GameState TurnAlreadyTaken(String player) {
        final String message = "The turn is already taken.";
        return new GameState(message, TicTacToeGameStateEnum.TurnAlreadyTaken);
    }

    public static GameState Gameover() {
        return new GameState("Game over. It's a draw", TicTacToeGameStateEnum.GameIsDraw);
    }

    public static GameState SomethingWentWrong(String message) {
        return new GameState(message, TicTacToeGameStateEnum.SomeThingWentWrong);
    }
}