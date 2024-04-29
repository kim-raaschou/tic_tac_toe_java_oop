package tic.tac.toe;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import tic.tac.toe.core.TicTacToeGame;
import tic.tac.toe.core.TicTacToeGameScore.TicTacToeGameStateEnum;
import tic.tac.toe.core.TicTacToeGameEngine;
import tic.tac.toe.core.TicTacToeScore;

public class TicTacToeGameTest {

    private final TicTacToeGameStub gameStub;
    private final TicTacToeGameEngine gameEngine;
    class TicTacToeGameStub implements TicTacToeGame {

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

        @Override
        public void draw(TicTacToeScore scores) {
        }

    }
    
    public TicTacToeGameTest() {
        gameStub = new TicTacToeGameStub();
        gameEngine = new TicTacToeGameEngine(gameStub);
    }

    @Test
    public void game_start_new_game_writes_to_output() {
        // Arrange;
        gameStub.setNextTurn(1);

        // Act
        var state = gameEngine.takeTurn("X");

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameStateEnum.TakeAnotherTuren, state.state()),
                () -> assertEquals("X", gameEngine.getScores()[0]));
    }

    @Test
    public void takeAnotherTurn_when_turn_is_accepted() {
        // Arrange;
        gameStub.setNextTurn(1);
        
        // Act
        var state = gameEngine.takeTurn("X");

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameStateEnum.TakeAnotherTuren, state.state()),
                () -> assertEquals("X", gameEngine.getScores()[0]));
    }

    @Test
    public void somethingWentWronghave_when_turn_is_invalid_0() {
        // Arrange;
        gameStub.setNextTurn(0);
        var previousScore = gameEngine.getScores();

        // Act
        var state = gameEngine.takeTurn("X");

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameStateEnum.SomeThingWentWrong, state.state()),
                () -> assertArrayEquals(previousScore, gameEngine.getScores()));
    }

    @Test
    public void somethingWentWronghave_when_turn_is_invalid_10() {
        // Arrange;
        gameStub.setNextTurn(10);
        var previousScore = gameEngine.getScores();

        // Act
        var state = gameEngine.takeTurn("X");

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameStateEnum.SomeThingWentWrong, state.state()),
                () -> assertArrayEquals(previousScore, gameEngine.getScores()));
    }

    @Test
    public void turnIsAlreadsTaken_when_player_already_has_takend_the_turn() {
        // Arrange;
        gameStub.setNextTurn(5);        
        gameEngine.takeTurn("O");
        var previousScore = gameEngine.getScores();

        // Act
        var state = gameEngine.takeTurn("X");

        // Assert
        assertAll(
                () -> assertEquals(TicTacToeGameStateEnum.TurnAlreadyTaken, state.state()),
                () -> assertArrayEquals(previousScore, gameEngine.getScores()));
    }

    @Test
    public void someThingWentWrong_when_player_is_not_valid() {
        // Arrange;
        gameStub.setNextTurn(5);
        
        // Act
        var state = gameEngine.takeTurn("Y");

        // Assert
        assertEquals(TicTacToeGameStateEnum.SomeThingWentWrong, state.state());
    }

    @Test
    public void gameHasAWinner_when_row_1_is_filled_by_one_player() {
        // Arrange;
        gameStub.setNextTurn(1);
        gameEngine.takeTurn("X");
        
        gameStub.setNextTurn(2);
        gameEngine.takeTurn("X");

        gameStub.setNextTurn(3);

        // Act
        var state = gameEngine.takeTurn("X");

        // Assert
        assertEquals(TicTacToeGameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_row_2_is_filled_by_one_player() {
        // Arrange;
        gameStub.setNextTurn(4);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(5);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(6);
        
        // Act
        var state = gameEngine.takeTurn("O");

        // Assert
        assertEquals(TicTacToeGameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_row_3_is_filled_by_one_player() {
        // Arrange;
        gameStub.setNextTurn(7);
        gameEngine.takeTurn("X");

        gameStub.setNextTurn(8);
        gameEngine.takeTurn("X");

        gameStub.setNextTurn(9);
 
        // Act
        var state = gameEngine.takeTurn("X");

        // Assert
        assertEquals(TicTacToeGameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_column_1_is_filled_by_one_player() {
        // Arrange;
        gameStub.setNextTurn(1);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(4);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(7);
        // Act
        var state = gameEngine.takeTurn("O");

        // Assert
        assertEquals(TicTacToeGameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_column_2_is_filled_by_one_player() {
        // Arrange;
        gameStub.setNextTurn(2);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(5);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(8);

        // Act
        var state = gameEngine.takeTurn("O");

        // Assert
        assertEquals(TicTacToeGameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_column_3_is_filled_by_one_player() {
        // Arrange;
        gameStub.setNextTurn(3);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(6);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(9);

        // Act
        var state = gameEngine.takeTurn("O");

        // Assert
        assertEquals(TicTacToeGameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_diagonal_left_to_right_is_filled_by_one_player() {
        // Arrange;
        gameStub.setNextTurn(1);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(5);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(9);

        // Act
        var state = gameEngine.takeTurn("O");

        // Assert
        assertEquals(TicTacToeGameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_diagonal_rith_to_left_is_filled_by_one_player() {
        // Arrange
        gameStub.setNextTurn(3);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(5);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(7);

        // Act
        var state = gameEngine.takeTurn("O");

        // Assert
        assertEquals(TicTacToeGameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameIsDraw_when_all_fields_is_taken_and_there_is_no_winner() {
        // Arrange;
        gameStub.setNextTurn(5);
        gameEngine.takeTurn("O");
        
        gameStub.setNextTurn(1);
        gameEngine.takeTurn("X");

        gameStub.setNextTurn(7);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(3);
        gameEngine.takeTurn("X");

        gameStub.setNextTurn(2);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(8);
        gameEngine.takeTurn("X");

        gameStub.setNextTurn(4);
        gameEngine.takeTurn("O");

        gameStub.setNextTurn(6);
        gameEngine.takeTurn("X");

        gameStub.setNextTurn(9);
        // Act
        var state = gameEngine.takeTurn("O");

        // Assert
        assertEquals(TicTacToeGameStateEnum.GameIsDraw, state.state());
    }

    @Test
    public void gameNotIsDraw_when_there_are_still_turns_to_take() {
        // Arrange;
        gameStub.setNextTurn(5);

        // Act
        var state = gameEngine.takeTurn("O");

        // Assert
        assertNotEquals(TicTacToeGameStateEnum.GameIsDraw, state.state());
    }

    @Test
    public void game_start_write_message() {
        // Arrange;
        gameStub.setNextTurn(5);

        // Act
        var state = gameEngine.takeTurn("O");

        // Assert
        assertNotEquals(TicTacToeGameStateEnum.GameIsDraw, state.state());
    }
}