package tic.tac.toe;

import java.util.Scanner;

import tic.tac.toe.core.TicTacToeGame;
import tic.tac.toe.core.TicTacToeGameState;
import tic.tac.toe.presentation.TicTacToeConsoleBoard;

public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        var currentPlayer = "X";
        var game = new TicTacToeGame(new TicTacToeConsoleBoard());

        System.out.println("Player X takes the first turn");

        var state = game.takeTurn(currentPlayer, scanner.nextInt());
        System.out.println(state);

        while (state.state() == TicTacToeGameState.TakeAnotherTuren) {
            currentPlayer = switchPlayer(currentPlayer);
            System.out.printf("Player %s takes the first turn", currentPlayer);
            state = game.takeTurn(currentPlayer, scanner.nextInt());
            System.out.println(state);
        }

        // System.out.println(new App().getGreeting());
    }

    private static String switchPlayer(String currentPlayer) {
        return currentPlayer.equals("X") ? "O" : "X";
    }
}