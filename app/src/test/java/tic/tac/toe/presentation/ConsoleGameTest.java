package tic.tac.toe.presentation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ConsoleGameTest {
 
    private String createAndDrawGame(String[] scores) {
        
        var messageBuilder = new StringBuilder();
        
        final ConsoleGame board = new ConsoleGame(
                () -> 0,
                message -> messageBuilder.append(message));

        board.draw(scores);
        return messageBuilder.toString();
    }

    @Test
    public void should_print_empty_board() {
        // Arrange
        var scores = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
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
        var scores = new String[] {"X", "O", "X", "4", "O", "X", "O", "8", "9"};

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
        var scores = new String[] {"X", "O", "X", "O", "O", "X", "O", "X", "O"};

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