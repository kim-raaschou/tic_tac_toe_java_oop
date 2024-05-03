package tic.tac.toe.core.preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import tic.tac.toe.core.GameStateCondition;
import tic.tac.toe.core.TicTacToeGameState;

public class TurnPreconditions {

    private final List<GameStateCondition> conditions = new ArrayList<>();

    public static TurnPreconditions that(GameStateCondition whenSomethingWentWrong) {
        var instance = new TurnPreconditions();
        instance.conditions.add(whenSomethingWentWrong);
        return instance;
    }

    public TurnPreconditions verify(GameStateCondition whenSomethingWentWrong) {
        conditions.add(whenSomethingWentWrong);
        return this;
    }

    public Optional<TicTacToeGameState> verifyAll() {
        return conditions.stream()                
                .map(GameStateCondition::Verify)
                .filter(Objects::nonNull)
                .findFirst();
    }
}
