package tic.tac.toe.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SomethingWentWrong {

    private final List<WhenSomethingWentWrong> optionals = new ArrayList<>();

    public static SomethingWentWrong when(WhenSomethingWentWrong whenSomethingWentWrong) {
        var instance = new SomethingWentWrong();
        instance.optionals.add(whenSomethingWentWrong);
        return instance;
    }

    public SomethingWentWrong or(WhenSomethingWentWrong whenSomethingWentWrong) {
        optionals.add(whenSomethingWentWrong);
        return this;
    }

    public Optional<TicTacToeGameState> asOptional() {
        return optionals.stream()                
                .map(WhenSomethingWentWrong::Assert)
                .filter(Objects::nonNull)
                .findFirst();
    }
}
