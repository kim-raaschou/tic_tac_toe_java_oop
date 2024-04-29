package tic.tac.toe;

import java.util.Scanner;

import tic.tac.toe.core.TicTacToeGameEngine;
import tic.tac.toe.presentation.TicTacToeConsoleGame;

public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var board = new TicTacToeConsoleGame(App::nextInt,App::write);
        var game = new TicTacToeGameEngine(board);
        game.startGame();
    }

    private static Integer nextInt(){
        return scanner.nextInt();
    }

    private static void write(String message){
        System.out.println(message);
    }
}