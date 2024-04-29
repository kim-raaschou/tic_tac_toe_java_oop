package tic.tac.toe.core;

public class TicTacToeGameEngine {

    private final TicTacToeScore scores = new TicTacToeScore();
    private final TicTacToeGame game;

    private TicTacToeGameScore gameScore;
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

    public TicTacToeGame getGame() {
        return game;
    }

    public TicTacToeGameScore takeTurn(String player) {
        game.output(String.format("Player %s. It´s your turn.", player));
        gameScore = scores.takeTurn(player, game.getNextInput());
        game.draw(scores);

        return gameScore;
    }

    public void startGame() {
        TicTacToeGameScore gameState = TicTacToeGameScore.Empty();

        while (true) {
            currentPlayer = gameState.state().switchPlayer()
                    ? switchPlayer(currentPlayer)
                    : currentPlayer;

            gameState = takeTurn(currentPlayer);
            game.output(gameState.message());

            if (gameState.state().gameHasEnded()) {
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