package tic.tac.toe.core;

import java.util.ArrayList;
import java.util.List;
import com.google.common.base.Supplier;

public class GameStatemachine {

    private final List<Supplier<GameState>> conditions = new ArrayList<>();

    public static GameStatemachine that(GameStateTransition gameStateTransition) {
        var instance = new GameStatemachine();
        instance.conditions.add(() -> gameStateTransition.Execute());
        return instance;
    }
    
    public GameStatemachine addTransition(GameStateTransition gameStateTransition) {
        conditions.add(() -> gameStateTransition.Execute());
        return this;
    }
    
    public GameState getGameState() {
        GameState gameState = GameState.Empty();

        for (var gameStateCondition : conditions) {
            var newGameState = gameStateCondition.get();
            gameState = newGameState != null ? newGameState : gameState;

            if (!gameState.state().isGameContinuable()) {
                return gameState;
            }
        }

        return gameState;

        // return conditions.stream()
        // .map(GameStateCondition::Verify)
        // .filter(Objects::nonNull)
        // .findFirst();
    }

}
