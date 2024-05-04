package tic.tac.toe.core;

public interface Player {

    public String getName();

    public class PlayerX implements Player {
        @Override
        public String getName() {
            return "X";
        }
    }

    public class PlayerO implements Player {
        @Override
        public String getName() {
            return "O";
        }
    }

    public static Player X() {
        return new PlayerX();
    }

    public static Player O() {
        return new PlayerO();
    }

    public static Player Switch(Player player) {
        return switch (player) {
            case final PlayerX x -> new PlayerO();
            case final PlayerO o -> new PlayerX();
            default -> throw new IllegalArgumentException();
        };
    }
}
