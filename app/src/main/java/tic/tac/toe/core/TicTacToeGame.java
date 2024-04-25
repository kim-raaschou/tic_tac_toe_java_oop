package tic.tac.toe.core;

public class TicTacToeGame {

    final private TicTacToeScore scores = new TicTacToeScore();
    final private TicTacToeBoard board;
    private TicTacToeGameScore gameScore;

    public TicTacToeGame(TicTacToeBoard board) {
        this.board = board;
    }

    public TicTacToeScore getScores() {
        return scores;
    }

    public TicTacToeBoard getBoard() {
        return board;
    }

    public TicTacToeGameScore takeTurn(String player, int turn) {
        gameScore = scores.takeTurn(player, turn);

        if (gameScore.state() == TicTacToeGameState.SomeThingWentWrong) {
            return gameScore;
        }

        if (gameScore.state() == TicTacToeGameState.GameHasAWinner) {
            return gameScore;
        }

        board.draw(scores);
        return gameScore;
    }
}