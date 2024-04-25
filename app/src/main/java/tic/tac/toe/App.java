package tic.tac.toe;

import java.util.Scanner;

import tic.tac.toe.core.TicTacToeBoardManager;
import tic.tac.toe.core.TicTacToeGame;
import tic.tac.toe.presentation.TicTacToeConsoleBoard;

public class App implements TicTacToeBoardManager {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var board = new TicTacToeConsoleBoard();
        var manager = new App();
        var game = new TicTacToeGame(board, manager);
        
        game.startGame();

    }

    

    @Override
    public int getNextInput() {
        return scanner.nextInt();
    }

    @Override
    public void output(String message) {
        System.out.println(message);
    }
}