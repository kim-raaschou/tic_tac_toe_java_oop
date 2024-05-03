package tic.tac.toe.core;

import java.util.ArrayList;
import java.util.List;
import com.google.common.base.Supplier;

public class GameStatemachine {

    private final List<GameStateTransition> transitions = new ArrayList<>();

    public static GameStatemachine that(GameStateTransition gameStateTransition) {
        var instance = new GameStatemachine();
        instance.transitions.add(gameStateTransition);
        return instance;
    }
    
    public GameStatemachine addTransition(GameStateTransition gameStateTransition) {
        transitions.add(gameStateTransition);
        return this;
    }
    
    public GameState getGameState() {
        GameState gameState = GameState.Empty();

        for (var gameStateCondition : transitions) {
            var newGameState = gameStateCondition.Execute();
            gameState = newGameState != null ? newGameState : gameState;

            if (!gameState.state().isGameContinuable()) {
                return gameState;
            }
        }

        return gameState;
   }
}
