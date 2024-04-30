package tic.tac.toe.core;

import tic.tac.toe.core.TicTacToeGameState.TicTacToeGameStateEnum;

public class TicTacToeGameEngine {

    private final TicTacToeScore scores = new TicTacToeScore();
    private final TicTacToeGame game;

    private TicTacToeGameState gameScore;
    private String currentPlayer;

    public TicTacToeGameEngine(TicTacToeGame game) {
        this.game = game;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String[] getScores() {
        return scores.toArray();
    }

    public TicTacToeGameStateEnum getState(){
        return gameScore.state();
    }

    public TicTacToeGame getGame() {
        return game;
    }

    public TicTacToeGameState takeTurn(String player) {
        game.output(String.format("Player %s. It´s your turn.", player));
        gameScore = scores.takeTurn(player, game.getNextInput());
        game.draw(scores);

        return gameScore;
    }

    public void startGame() {
        TicTacToeGameState gameScore = TicTacToeGameState.Empty();

        while (true) {
            currentPlayer = gameScore.state().switchPlayer()
                    ? switchPlayer(currentPlayer)
                    : currentPlayer;

            gameScore = takeTurn(currentPlayer);
            game.output(gameScore.message());

            if (gameScore.state().gameHasEnded()) {
                break;
            }
        }
    }

    private static String switchPlayer(String currentPlayer) {
        return currentPlayer == null || currentPlayer.equals("O")
                ? "X"
                : "O";
    }
}