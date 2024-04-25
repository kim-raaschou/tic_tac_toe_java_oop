package tic.tac.toe.core;

public class TicTacToeGame {

    private final TicTacToeScore scores = new TicTacToeScore();
    private final TicTacToeBoard board;
    private final TicTacToeBoardManager manager;

    private TicTacToeGameScore gameScore;
    private String currentPlayer;

    public TicTacToeGame(TicTacToeBoard board, TicTacToeBoardManager manager) {
        this.board = board;
        this.manager = manager;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String[] getScores() {
        return scores.toArray();
    }

    public TicTacToeBoard getBoard() {
        return board;
    }

    public TicTacToeGameScore takeTurn(String player) {
        manager.output(String.format("Player %s. It´s your turn.", player));
        gameScore = scores.takeTurn(player, manager.getNextInput());
        board.draw(scores);

        return gameScore;
    }

    public void startGame() {
        TicTacToeGameScore gameState = TicTacToeGameScore.Empty();

        while (true) {
            currentPlayer = gameState.state().switchPlayer()
                    ? switchPlayer(currentPlayer)
                    : currentPlayer;

            gameState = takeTurn(currentPlayer);
            manager.output(gameState.message());

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