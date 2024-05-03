package tic.tac.toe.core;

public record GameState(String message, GameStateEnum state) {

    public static final GameState Empty() {
        return new GameState("", GameStateEnum.Unknown);
    }

    public static GameState TakeATurn() {
        return new GameState("Next turn", GameStateEnum.TakeAnotherTuren);
    }

    public static GameState Winner(String player) {
        String message = String.format("We have a winner !!!! Player %s wins", player);
        return new GameState(message, GameStateEnum.GameHasAWinner);
    }

    public static GameState TurnAlreadyTaken(String player) {
        final String message = "The turn is already taken.";
        return new GameState(message, GameStateEnum.TurnAlreadyTaken);
    }

    public static GameState Gameover() {
        return new GameState("Game over. It's a draw", GameStateEnum.GameIsDraw);
    }

    public static GameState SomethingWentWrong(String message) {
        return new GameState(message, GameStateEnum.SomeThingWentWrong);
    }
}