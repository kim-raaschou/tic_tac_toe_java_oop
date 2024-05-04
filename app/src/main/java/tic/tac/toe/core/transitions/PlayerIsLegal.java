// package tic.tac.toe.core.transitions;

// import java.util.List;

// import tic.tac.toe.core.GameState;
// import tic.tac.toe.core.GameStateTransition;
// import tic.tac.toe.core.Player;

// public class PlayerIsLegal implements GameStateTransition {

//     private final String message = """
//             Invalid player %s took turn - try again
//             """;
//     private final Player player;

//     public PlayerIsLegal(Player player) {
//         this.player = player;
//     }

//     @Override
//     public GameState Execute() {
//         return List.of("X", "O").contains(player)
//                 ? null
//                 : invalidPlayerGameState();
//     }

//     private GameState invalidPlayerGameState() {
//         String message = String.format(this.message, player);
//         return GameState.SomethingWentWrong(message);
//     }

// }
