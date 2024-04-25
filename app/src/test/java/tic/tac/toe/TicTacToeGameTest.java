package tic.tac.toe;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import tic.tac.toe.core.TicTacToeBoard;
import tic.tac.toe.core.TicTacToeGame;
import tic.tac.toe.core.TicTacToeScore;
import tic.tac.toe.core.TicTacToeGameState;

public class TicTacToeGameTest {

    class TicTacToeBoardStub implements TicTacToeBoard {
        @Override
        public void draw(TicTacToeScore scores) {
        }
    }

    private TicTacToeGame newGame() {
        return new TicTacToeGame(new TicTacToeBoardStub());
    }

    @Test
    public void game_start_new_game_writes_to_output() {
        // Arrange
        var game = newGame();
        
        // Act
        var state = game.takeTurn("X", 1);

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameState.TakeAnotherTuren, state.state()),
                () -> assertEquals("X", game.getScores().toArray()[0]));
    }

    @Test
    public void takeAnotherTurn_when_turn_is_accepted() {
        // Arrange
        var game = newGame();

        // Act
        var state = game.takeTurn("X", 1);

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameState.TakeAnotherTuren, state.state()),
                () -> assertEquals("X", game.getScores().toArray()[0]));
    }

    @Test
    public void somethingWentWronghave_when_turn_is_invalid_0() {
        // Arrange
        var game = newGame();
        var previousScore = game.getScores().toArray();

        // Act
        var state = game.takeTurn("X", 0);

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameState.SomeThingWentWrong, state.state()),
                () -> assertArrayEquals(previousScore, game.getScores().toArray()));
    }

    @Test
    public void somethingWentWronghave_when_turn_is_invalid_10() {
        // Arrange
        var game = newGame();
        var previousScore = game.getScores().toArray();

        // Act
        var state = game.takeTurn("X", 10);

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameState.SomeThingWentWrong, state.state()),
                () -> assertArrayEquals(previousScore, game.getScores().toArray()));
    }

    @Test
    public void turnIsAlreadsTaken_when_player_already_has_takend_the_turn() {
        // Arrange
        var game = newGame();
        game.takeTurn("O", 5);
        var previousScore = game.getScores().toArray();

        // Act
        var state = game.takeTurn("X", 5);

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameState.TurnAlreadyTaken, state.state()),
                () -> assertArrayEquals(previousScore, game.getScores().toArray()));
    }

    @Test
    public void someThingWentWrong_when_player_is_not_valid() {
        // Arrange
        var game = newGame();

        // Act
        var state = game.takeTurn("Y", 5);

        // Assert
        assertEquals(TicTacToeGameState.SomeThingWentWrong, state.state());
    }

    @Test
    public void gameHasAWinner_when_row_1_is_filled_by_one_player() {
        // Arrange
        var game = newGame();
        game.takeTurn("X", 1);
        game.takeTurn("X", 2);

        // Act
        var state = game.takeTurn("X", 3);

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_row_2_is_filled_by_one_player() {
        // Arrange
        var game = newGame();
        game.takeTurn("O", 4);
        game.takeTurn("O", 5);

        // Act
        var state = game.takeTurn("O", 6);

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_row_3_is_filled_by_one_player() {
        // Arrange
        var game = newGame();
        game.takeTurn("X", 7);
        game.takeTurn("X", 8);

        // Act
        var state = game.takeTurn("X", 9);

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_column_1_is_filled_by_one_player() {
        // Arrange
        var game = newGame();
        game.takeTurn("O", 1);
        game.takeTurn("O", 4);

        // Act
        var state = game.takeTurn("O", 7);

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_column_2_is_filled_by_one_player() {
        // Arrange
        var game = newGame();
        game.takeTurn("O", 2);
        game.takeTurn("O", 5);

        // Act
        var state = game.takeTurn("O", 8);

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_column_3_is_filled_by_one_player() {
        // Arrange
        var game = newGame();
        game.takeTurn("O", 3);
        game.takeTurn("O", 6);

        // Act
        var state = game.takeTurn("O", 9);

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_diagonal_left_to_right_is_filled_by_one_player() {
        // Arrange
        var game = newGame();
        game.takeTurn("O", 1);
        game.takeTurn("O", 5);

        // Act
        var state = game.takeTurn("O", 9);

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_diagonal_rith_to_left_is_filled_by_one_player() {
        // Arrange
        var game = newGame();
        game.takeTurn("O", 3);
        game.takeTurn("O", 5);

        // Act
        var state = game.takeTurn("O", 7);

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameIsDraw_when_all_fields_is_taken_and_there_is_no_winner() {
        // Arrange
        var game = newGame();
        game.takeTurn("O", 5);
        game.takeTurn("X", 1);
        game.takeTurn("O", 7);
        game.takeTurn("X", 3);
        game.takeTurn("O", 2);
        game.takeTurn("X", 8);
        game.takeTurn("O", 4);
        game.takeTurn("X", 6);

        // Act
        var state = game.takeTurn("O", 9);

        // Assert
        assertEquals(TicTacToeGameState.GameIsDraw, state.state());
    }

    @Test
    public void gameNotIsDraw_when_there_are_still_turns_to_take() {
        // Arrange
        var game = newGame();
        
        // Act
        var state = game.takeTurn("O", 5);

        // Assert
        assertNotEquals(TicTacToeGameState.GameIsDraw, state.state());
    }
}