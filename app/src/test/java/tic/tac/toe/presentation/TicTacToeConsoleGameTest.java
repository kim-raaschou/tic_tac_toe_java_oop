package tic.tac.toe.presentation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import tic.tac.toe.core.GameScore;

public class TicTacToeConsoleGameTest {
 
    private String createAndDrawGame(GameScore scores) {
        
        var messageBuilder = new StringBuilder();
        
        final TicTacToeConsoleGame board = new TicTacToeConsoleGame(
                () -> 0,
                message -> messageBuilder.append(message));

        board.draw(scores);
        return messageBuilder.toString();
    }

    @Test
    public void should_print_empty_board() {
        // Arrange
        var scores = new GameScore();
        var expected = """
                +---+---+---+
                | 1 | 2 | 3 |
                +---+---+---+
                | 4 | 5 | 6 |
                +---+---+---+
                | 7 | 8 | 9 |
                +---+---+---+
                """;

        // Act
        var board = createAndDrawGame(scores);

        // Assert
        assertEquals(expected, board);
    }

    @Test
    public void should_print_board_with_scores() {
        // Arrange
        var scores = new GameScore()
                .withScoreX(1)
                .withScoreO(2)
                .withScoreX(3)
                .withScoreO(5)
                .withScoreX(6)
                .withScoreO(7);

        var expected = """
                +---+---+---+
                | X | O | X |
                +---+---+---+
                | 4 | O | X |
                +---+---+---+
                | O | 8 | 9 |
                +---+---+---+
                """;

        // Act
        var board = createAndDrawGame(scores);

        // Assert
        assertEquals(expected, board);
    }

    @Test
    public void should_print_board_where_game_is_drawn() {
        // Arrange
        var scores = new GameScore()
                .withScoreO(5)
                .withScoreX(1)
                .withScoreO(7)
                .withScoreX(3)
                .withScoreO(2)
                .withScoreX(8)
                .withScoreO(4)
                .withScoreX(6)
                .withScoreO(9);

        var expected = """
                +---+---+---+
                | X | O | X |
                +---+---+---+
                | O | O | X |
                +---+---+---+
                | O | X | O |
                +---+---+---+
                """;

        // Act
        var board = createAndDrawGame(scores);

        // Assert
        assertEquals(expected, board);
    }
}