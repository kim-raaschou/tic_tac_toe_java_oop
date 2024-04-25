package tic.tac.toe;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import tic.tac.toe.core.TicTacToeBoard;
import tic.tac.toe.core.TicTacToeBoardManager;
import tic.tac.toe.core.TicTacToeGame;
import tic.tac.toe.core.TicTacToeScore;
import tic.tac.toe.core.TicTacToeGameState;

public class TicTacToeGameTest {

    private final TicTacToeBoardStub board;
    private final TicTacToeBoardManagerStub manager;
    private final TicTacToeGame game;

    class TicTacToeBoardStub implements TicTacToeBoard {
        @Override
        public void draw(TicTacToeScore scores) {
        }
    }

    class TicTacToeBoardManagerStub implements TicTacToeBoardManager {

        public String message;
        private int turn = 0;

        @Override
        public int getNextInput() {
            return turn;
        }

        public void setNextTurn(int turn){
            this.turn = turn;

        }

        @Override
        public void output(String message) {
            this.message = message;
        }

        public String getLastMessage(){
            return message;
        }

    }
    
    public TicTacToeGameTest() {
        board = new TicTacToeBoardStub();
        manager = new TicTacToeBoardManagerStub();
        game = new TicTacToeGame(board, manager);
    }

    @Test
    public void game_start_new_game_writes_to_output() {
        // Arrange;
        manager.setNextTurn(1);

        // Act
        var state = game.takeTurn("X");

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameState.TakeAnotherTuren, state.state()),
                () -> assertEquals("X", game.getScores()[0]));
    }

    @Test
    public void takeAnotherTurn_when_turn_is_accepted() {
        // Arrange;
        manager.setNextTurn(1);
        
        // Act
        var state = game.takeTurn("X");

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameState.TakeAnotherTuren, state.state()),
                () -> assertEquals("X", game.getScores()[0]));
    }

    @Test
    public void somethingWentWronghave_when_turn_is_invalid_0() {
        // Arrange;
        manager.setNextTurn(0);
        var previousScore = game.getScores();

        // Act
        var state = game.takeTurn("X");

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameState.SomeThingWentWrong, state.state()),
                () -> assertArrayEquals(previousScore, game.getScores()));
    }

    @Test
    public void somethingWentWronghave_when_turn_is_invalid_10() {
        // Arrange;
        manager.setNextTurn(10);
        var previousScore = game.getScores();

        // Act
        var state = game.takeTurn("X");

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameState.SomeThingWentWrong, state.state()),
                () -> assertArrayEquals(previousScore, game.getScores()));
    }

    @Test
    public void turnIsAlreadsTaken_when_player_already_has_takend_the_turn() {
        // Arrange;
        manager.setNextTurn(5);        
        game.takeTurn("O");
        var previousScore = game.getScores();

        // Act
        var state = game.takeTurn("X");

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameState.TurnAlreadyTaken, state.state()),
                () -> assertArrayEquals(previousScore, game.getScores()));
    }

    @Test
    public void someThingWentWrong_when_player_is_not_valid() {
        // Arrange;
        manager.setNextTurn(5);
        
        // Act
        var state = game.takeTurn("Y");

        // Assert
        assertEquals(TicTacToeGameState.SomeThingWentWrong, state.state());
    }

    @Test
    public void gameHasAWinner_when_row_1_is_filled_by_one_player() {
        // Arrange;
        manager.setNextTurn(1);
        game.takeTurn("X");
        
        manager.setNextTurn(2);
        game.takeTurn("X");

        manager.setNextTurn(3);

        // Act
        var state = game.takeTurn("X");

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_row_2_is_filled_by_one_player() {
        // Arrange;
        manager.setNextTurn(4);
        game.takeTurn("O");

        manager.setNextTurn(5);
        game.takeTurn("O");

        manager.setNextTurn(6);
        
        // Act
        var state = game.takeTurn("O");

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_row_3_is_filled_by_one_player() {
        // Arrange;
        manager.setNextTurn(7);
        game.takeTurn("X");

        manager.setNextTurn(8);
        game.takeTurn("X");

        manager.setNextTurn(9);
 
        // Act
        var state = game.takeTurn("X");

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_column_1_is_filled_by_one_player() {
        // Arrange;
        manager.setNextTurn(1);
        game.takeTurn("O");

        manager.setNextTurn(4);
        game.takeTurn("O");

        manager.setNextTurn(7);
        // Act
        var state = game.takeTurn("O");

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_column_2_is_filled_by_one_player() {
        // Arrange;
        manager.setNextTurn(2);
        game.takeTurn("O");

        manager.setNextTurn(5);
        game.takeTurn("O");

        manager.setNextTurn(8);

        // Act
        var state = game.takeTurn("O");

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_column_3_is_filled_by_one_player() {
        // Arrange;
        manager.setNextTurn(3);
        game.takeTurn("O");

        manager.setNextTurn(6);
        game.takeTurn("O");

        manager.setNextTurn(9);

        // Act
        var state = game.takeTurn("O");

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_diagonal_left_to_right_is_filled_by_one_player() {
        // Arrange;
        manager.setNextTurn(1);
        game.takeTurn("O");

        manager.setNextTurn(5);
        game.takeTurn("O");

        manager.setNextTurn(9);

        // Act
        var state = game.takeTurn("O");

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_diagonal_rith_to_left_is_filled_by_one_player() {
        // Arrange
        manager.setNextTurn(3);
        game.takeTurn("O");

        manager.setNextTurn(5);
        game.takeTurn("O");

        manager.setNextTurn(7);

        // Act
        var state = game.takeTurn("O");

        // Assert
        assertEquals(TicTacToeGameState.GameHasAWinner, state.state());
    }

    @Test
    public void gameIsDraw_when_all_fields_is_taken_and_there_is_no_winner() {
        // Arrange;
        manager.setNextTurn(5);
        game.takeTurn("O");
        
        manager.setNextTurn(1);
        game.takeTurn("X");

        manager.setNextTurn(7);
        game.takeTurn("O");

        manager.setNextTurn(3);
        game.takeTurn("X");

        manager.setNextTurn(2);
        game.takeTurn("O");

        manager.setNextTurn(8);
        game.takeTurn("X");

        manager.setNextTurn(4);
        game.takeTurn("O");

        manager.setNextTurn(6);
        game.takeTurn("X");

        manager.setNextTurn(9);
        // Act
        var state = game.takeTurn("O");

        // Assert
        assertEquals(TicTacToeGameState.GameIsDraw, state.state());
    }

    @Test
    public void gameNotIsDraw_when_there_are_still_turns_to_take() {
        // Arrange;
        manager.setNextTurn(5);

        // Act
        var state = game.takeTurn("O");

        // Assert
        assertNotEquals(TicTacToeGameState.GameIsDraw, state.state());
    }

    @Test
    public void game_start_write_message() {
        // Arrange;
        manager.setNextTurn(5);

        // Act
        var state = game.takeTurn("O");

        // Assert
        assertNotEquals(TicTacToeGameState.GameIsDraw, state.state());
    }
}