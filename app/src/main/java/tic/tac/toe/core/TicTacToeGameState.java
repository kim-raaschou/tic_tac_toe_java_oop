package tic.tac.toe.core;

public enum TicTacToeGameState {
    Unknown(false, true),
    GameHasAWinner(true, false),
    GameIsDraw(true, false),
    TurnAlreadyTaken(false, false),
    TakeAnotherTuren(false, true),
    SomeThingWentWrong(false, false);

    private final boolean gameHasEnded;
    private final boolean switchTurn;

    private TicTacToeGameState(boolean gameHasEnded, boolean switchPlayer) {
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