package tic.tac.toe.core;

import tic.tac.toe.core.transitions.NextPlayerIsLegal;
import tic.tac.toe.core.transitions.DiagonalsWinner;
import tic.tac.toe.core.transitions.GameIsDraw;
import tic.tac.toe.core.transitions.HorizontalWinner;
import tic.tac.toe.core.transitions.TurnIsLegel;
import tic.tac.toe.core.transitions.TurnIsNotAlreadyTaken;
import tic.tac.toe.core.transitions.VerticalWinner;

public class GameEngine {

    private String[] scores = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    //private final GameScore scores = new GameScore();
    private final Game game;

    private String currentPlayer;

    public GameEngine(Game game) {
        this.game = game;
    }

    public String[] getScores() {
        return scores;
        //return scores.toArray();
    }

    public GameState takeTurn(String player) {
        game.output(String.format("Player %s. It´s your turn.", player));
        final var turn = game.getNextInput();

        var gameState = new GameStatemachine()
                .addTransition(new TurnIsLegel(turn))
                .addTransition(new NextPlayerIsLegal(player))
                .addTransition(new TurnIsNotAlreadyTaken(turn, player, scores))
                .addTransition(() -> {
                    scores[turn - 1] = player;
                    //scores.takeTurn(player, turn);
                    game.draw(scores);
                    return GameState.TakeATurn();
                })
                .addTransition(new DiagonalsWinner(scores, player))
                .addTransition(new HorizontalWinner(scores, player))
                .addTransition(new VerticalWinner(scores, player))
                .addTransition(new GameIsDraw(scores))
                .getGameState();

        return gameState;
    }

    public GameState startGame() {
        var gameScore = GameState.Empty();

        while (gameScore.state().isGameContinuable()) {
            currentPlayer = gameScore.state().switchPlayer()
                    ? switchPlayer(currentPlayer)
                    : currentPlayer;

            gameScore = takeTurn(currentPlayer);
            game.output(gameScore.message());
        }

        return gameScore;
    }

    private static String switchPlayer(String currentPlayer) {
        return currentPlayer == null || currentPlayer.equals("O")
                ? "X"
                : "O";
    }
}