package tic.tac.toe.core;

public enum GameStateEnum {
    Unknown(false, true),
    GameHasAWinner(true, false),
    GameIsDraw(true, false),
    TurnAlreadyTaken(false, false),
    TakeAnotherTuren(false, true),
    SomeThingWentWrong(false, false);

    private final boolean gameHasEnded;
    private final boolean switchTurn;

    private GameStateEnum(boolean gameHasEnded, boolean switchPlayer) {
        this.gameHasEnded = gameHasEnded;
        this.switchTurn = switchPlayer;
    }

    public boolean switchPlayer() {
        return switchTurn;
    }

    public boolean isGameContinuable() {
        return !gameHasEnded
                && this != GameStateEnum.TurnAlreadyTaken
                && this != GameStateEnum.SomeThingWentWrong;
    }
}