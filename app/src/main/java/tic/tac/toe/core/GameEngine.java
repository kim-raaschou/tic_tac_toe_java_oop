package tic.tac.toe.core;

import tic.tac.toe.core.transitions.DiagonalsWinner;
import tic.tac.toe.core.transitions.GameIsDraw;
import tic.tac.toe.core.transitions.HorizontalWinner;
import tic.tac.toe.core.transitions.TurnIsLegel;
import tic.tac.toe.core.transitions.TurnNotTaken;
import tic.tac.toe.core.transitions.VerticalWinner;

public class GameEngine {

    private final GameBoard game;

    private String[] scores = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
    private Player currentPlayer = Player.X();

    public GameEngine(GameBoard game) {
        this.game = game;
    }

    public String[] getScores() {
        return scores;
    }

    public GameState takeTurn(Player player) {
        final var turn = game.getNextInput();

        var gameState = new GameStatemachine()
                .addTransition(new TurnIsLegel(turn))
                .addTransition(new TurnNotTaken(turn, player, scores))
                .addTransition(() -> {
                    scores[turn - 1] = player.getName();
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
        game.output(String.format("Player %s will start.", currentPlayer.getName()));
        var gameScore = GameState.Empty();

        while (gameScore.state().isGameContinuable()) {

            if (gameScore.state().switchPlayer()) {
                currentPlayer = Player.Switch(currentPlayer);
            }
           
            game.output(String.format("Player %s. It´s your turn.", currentPlayer.getName()));

            gameScore = takeTurn(currentPlayer);
            game.output(gameScore.message());
        }

        return gameScore;
    }
}