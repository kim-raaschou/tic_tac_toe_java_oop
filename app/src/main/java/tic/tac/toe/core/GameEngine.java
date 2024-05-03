package tic.tac.toe.core;

import tic.tac.toe.core.postconditions.DiagonalsWinner;
import tic.tac.toe.core.postconditions.GameIsDraw;
import tic.tac.toe.core.postconditions.HorizontalWinner;
import tic.tac.toe.core.postconditions.VerticalWinner;
import tic.tac.toe.core.preconditions.NextPlayerIsLegal;
import tic.tac.toe.core.preconditions.TurnIsNotAlreadyTaken;
import tic.tac.toe.core.preconditions.TurnIsLegel;

public class GameEngine {

    private final GameScore scores = new GameScore();
    private final Game game;

    private String currentPlayer;

    public GameEngine(Game game) {
        this.game = game;
    }
    public String[] getScores() {
        return scores.toArray();
    }

    public Game getGame() {
        return game;
    }

    public GameState takeTurn(String player) {
        game.output(String.format("Player %s. It´s your turn.", player));
        final var turn = game.getNextInput();

        var gameState = new GameStatemachine()
                .addTransition(new TurnIsLegel(turn))
                .addTransition(new NextPlayerIsLegal(player))
                .addTransition(new TurnIsNotAlreadyTaken(turn, player, scores.toArray()))
                .addTransition(() -> {
                    scores.takeTurn(player, turn);
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

        while (true) {
            currentPlayer = gameScore.state().switchPlayer()
                    ? switchPlayer(currentPlayer)
                    : currentPlayer;

            gameScore = takeTurn(currentPlayer);
            game.output(gameScore.message());

            if (gameScore.state().gameHasEnded()) {
                return gameScore;
            }
        }
    }

    private static String switchPlayer(String currentPlayer) {
        return currentPlayer == null || currentPlayer.equals("O")
                ? "X"
                : "O";
    }
}