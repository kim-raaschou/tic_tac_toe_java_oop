package tic.tac.toe;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Stack;
import org.junit.jupiter.api.Test;

import tic.tac.toe.core.Game;
import tic.tac.toe.core.GameEngine;
import tic.tac.toe.core.GameStateEnum;
import tic.tac.toe.core.Player;

public class GameEngineTest {

    private final TicTacToeGameStub gameStub;
    private final GameEngine gameEngine;

    class TicTacToeGameStub implements Game {

        public String message;
        private Stack<Integer> turns = new Stack<Integer>();

        @Override
        public int getNextInput() {
            return turns.pop();
        }

        public void setTurns(int... turns) {
            for (int turn : turns) {
                this.turns.add(turn);
            }
        }

        @Override
        public void output(String message) {
            this.message = message;
        }

        public String getLastMessage() {
            return message;
        }

        @Override
        public void draw(String[] scores) {
        }

    }

    public GameEngineTest() {
        gameStub = new TicTacToeGameStub();
        gameEngine = new GameEngine(gameStub);
    }

    @Test
    public void game_start_new_game_writes_to_output() {
        // Arrange;
        gameStub.setTurns(1);

        // Act
        var state = gameEngine.takeTurn(Player.X());

        // Assert
        assertAll(
                () -> assertEquals(GameStateEnum.TakeAnotherTuren, state.state()),
                () -> assertEquals(Player.X().getName(), gameEngine.getScores()[0]));
    }

    @Test
    public void takeAnotherTurn_when_turn_is_accepted() {
        // Arrange;
        gameStub.setTurns(1);

        // Act
        var state = gameEngine.takeTurn(Player.X());

        // Assert
        assertAll(
                () -> assertEquals(GameStateEnum.TakeAnotherTuren, state.state()),
                () -> assertEquals(Player.X().getName(), gameEngine.getScores()[0]));
    }

    @Test
    public void somethingWentWronghave_when_turn_is_invalid_0() {
        // Arrange;
        gameStub.setTurns(0);
        var previousScore = gameEngine.getScores();

        // Act
        var state = gameEngine.takeTurn(Player.X());

        // Assert
        assertAll(
                () -> assertEquals(GameStateEnum.SomeThingWentWrong, state.state()),
                () -> assertArrayEquals(previousScore, gameEngine.getScores()));
    }

    @Test
    public void somethingWentWronghave_when_turn_is_invalid_10() {
        // Arrange;
        gameStub.setTurns(10);

        // Act
        var state = gameEngine.takeTurn(Player.X());

        // Assert
        assertEquals(GameStateEnum.SomeThingWentWrong, state.state());
    }

    @Test
    public void turnIsAlreadsTaken_when_player_already_has_takend_the_turn() {
        // Arrange;
        gameStub.setTurns(5);
        gameEngine.takeTurn(Player.O());
        var previousScore = gameEngine.getScores();

        gameStub.setTurns(5);

        // Act
        var state = gameEngine.takeTurn(Player.X());

        // Assert
        assertAll(
                () -> assertEquals(GameStateEnum.TurnAlreadyTaken, state.state()),
                () -> assertArrayEquals(previousScore, gameEngine.getScores()));
    }

    @Test
    public void gameHasAWinner_when_row_1_is_filled_by_one_player() {
        // Arrange;
        gameStub.setTurns(1, 2, 3);
        gameEngine.takeTurn(Player.X());
        gameEngine.takeTurn(Player.X());
        // Act
        var state = gameEngine.takeTurn(Player.X());

        // Assert
        assertEquals(GameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_row_2_is_filled_by_one_player() {
        // Arrange;
        gameStub.setTurns(4, 5, 6);
        gameEngine.takeTurn(Player.O());
        gameEngine.takeTurn(Player.O());

        // Act
        var state = gameEngine.takeTurn(Player.O());

        // Assert
        assertEquals(GameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_row_3_is_filled_by_one_player() {
        // Arrange;
        gameStub.setTurns(7, 8, 9);

        gameEngine.takeTurn(Player.X());
        gameEngine.takeTurn(Player.X());

        // Act
        var state = gameEngine.takeTurn(Player.X());

        // Assert
        assertEquals(GameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_column_1_is_filled_by_one_player() {
        // Arrange;
        gameStub.setTurns(1, 4, 7);

        gameEngine.takeTurn(Player.O());
        gameEngine.takeTurn(Player.O());

        // Act
        var state = gameEngine.takeTurn(Player.O());

        // Assert
        assertEquals(GameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_column_2_is_filled_by_one_player() {
        // Arrange;
        gameStub.setTurns(2);
        gameEngine.takeTurn(Player.O());

        gameStub.setTurns(5);
        gameEngine.takeTurn(Player.O());

        gameStub.setTurns(8);

        // Act
        var state = gameEngine.takeTurn(Player.O());

        // Assert
        assertEquals(GameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_column_3_is_filled_by_one_player() {
        // Arrange;
        gameStub.setTurns(3, 6, 9);

        gameEngine.takeTurn(Player.O());
        gameEngine.takeTurn(Player.O());

        // Act
        var state = gameEngine.takeTurn(Player.O());

        // Assert
        assertEquals(GameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_diagonal_left_to_right_is_filled_by_one_player() {
        // Arrange;
        gameStub.setTurns(1, 5, 9);

        gameEngine.takeTurn(Player.O());
        gameEngine.takeTurn(Player.O());

        // Act
        var state = gameEngine.takeTurn(Player.O());

        // Assert
        assertEquals(GameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameHasAWinner_when_diagonal_rith_to_left_is_filled_by_one_player() {
        // Arrange
        gameStub.setTurns(3, 5, 7);

        gameEngine.takeTurn(Player.O());
        gameEngine.takeTurn(Player.O());

        // Act
        var state = gameEngine.takeTurn(Player.O());

        // Assert
        assertEquals(GameStateEnum.GameHasAWinner, state.state());
    }

    @Test
    public void gameIsDraw_when_all_fields_is_taken_and_there_is_no_winner() {
        // Arrange;
        gameStub.setTurns(5, 1, 7, 3, 2, 8, 4, 6, 9);
        
        // Act
        var gameState = gameEngine.startGame();

        // Assert
        assertEquals(GameStateEnum.GameIsDraw, gameState.state());
    }

    @Test
    public void gameNotIsDraw_when_there_are_still_turns_to_take() {
        // Arrange;
        gameStub.setTurns(5);

        // Act
        var state = gameEngine.takeTurn(Player.O());

        // Assert
        assertNotEquals(GameStateEnum.GameIsDraw, state.state());
    }

    @Test
    public void game_start_write_message() {
        // Arrange;
        gameStub.setTurns(5);

        // Act
        var state = gameEngine.takeTurn(Player.O());

        // Assert
        assertNotEquals(GameStateEnum.GameIsDraw, state.state());
    }
}