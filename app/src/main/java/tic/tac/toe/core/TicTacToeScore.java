package tic.tac.toe.core;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TicTacToeScore {
    private String[] scores = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    public TicTacToeScore withScoreX(int score) {
        scores[score - 1] = "X";
        return this;
    }

    public TicTacToeScore withScoreO(int turn) {
        scores[turn - 1] = "O";
        return this;
    }

    public String[] toArray() {
        return scores;
    }

    public TicTacToeGameScore takeTurn(String player, int turn) {
        final Optional<TicTacToeGameScore> validationError = invalidTurn(turn)
                .or(() -> invalidPlayer(player))
                .or(() -> turnIsTaken(scores[turn - 1]));

        if (validationError.isPresent()) {
            return validationError.get();
        }

        scores[turn - 1] = player;

        String haswinner = hasLineWinner();
        if (haswinner != null) {
            return TicTacToeGameScore.Winner(player);
        }

        haswinner = hasColumnWinner();
        if (haswinner != null) {
            return TicTacToeGameScore.Winner(player);
        }

        haswinner = hasDiagonalWinner();
        if (haswinner != null) {
            return TicTacToeGameScore.Winner(player);
        }

        if (gameIsDraw()) {
            return TicTacToeGameScore.Gameover();
        }
        return TicTacToeGameScore.TakeATurn();
    }

    private final boolean gameIsDraw() {
        
        return Arrays.stream(scores).noneMatch(score -> {
            return !List.of("X", "O").contains(score);
        });
    }

    private String hasDiagonalWinner() {
        if (scores[0] == scores[4] && scores[0] == scores[8])
            return scores[0];

        if (scores[2] == scores[4] && scores[2] == scores[6])
            return scores[2];

        return null;
    }

    private String hasLineWinner() {
        if (scores[0] == scores[1] && scores[0] == scores[2])
            return scores[1];

        if (scores[3] == scores[4] && scores[3] == scores[5])
            return scores[4];

        if (scores[6] == scores[7] && scores[6] == scores[8])
            return scores[7];

        return null;
    }

    private String hasColumnWinner() {
        if (scores[0] == scores[3] && scores[0] == scores[6])
            return scores[1];

        if (scores[1] == scores[4] && scores[1] == scores[7])
            return scores[4];

        if (scores[2] == scores[5] && scores[2] == scores[8])
            return scores[7];

        return null;
    }

    private Optional<TicTacToeGameScore> invalidTurn(int turn) {
        final var message = """
                Invalid turn!
                Turn must be a number between 1 and 9.
                """;

        return turn < 1 || turn > 9
                ? Optional.of(TicTacToeGameScore.SomethingWentWrong(message))
                : Optional.empty();
    }

    private Optional<TicTacToeGameScore> invalidPlayer(String player) {
        return List.of("X", "O").contains(player)
                ? Optional.empty()
                : Optional.of(TicTacToeGameScore.SomethingWentWrong(""));
    }

    private Optional<TicTacToeGameScore> turnIsTaken(String turn) {
        return List.of("X", "O").contains(turn)
                ? Optional.of(TicTacToeGameScore.TurnAlreadyTaken())
                : Optional.empty();
    }
}
