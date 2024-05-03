package tic.tac.toe.core.preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import tic.tac.toe.core.Precondition;
import tic.tac.toe.core.TicTacToeGameState;

public class TurnPreconditions {

    private final List<Precondition> conditions = new ArrayList<>();

    public static TurnPreconditions that(Precondition whenSomethingWentWrong) {
        var instance = new TurnPreconditions();
        instance.conditions.add(whenSomethingWentWrong);
        return instance;
    }

    public TurnPreconditions verify(Precondition whenSomethingWentWrong) {
        conditions.add(whenSomethingWentWrong);
        return this;
    }

    public Optional<TicTacToeGameState> verifyAll() {
        return conditions.stream()                
                .map(Precondition::Verify)
                .filter(Objects::nonNull)
                .findFirst();
    }
}
