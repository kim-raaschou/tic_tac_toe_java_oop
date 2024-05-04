package tic.tac.toe;

import java.util.Scanner;

import tic.tac.toe.core.GameEngine;
import tic.tac.toe.presentation.ConsoleGameBoard;

public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var board = new ConsoleGameBoard(App::nextInt,App::write);
        var game = new GameEngine(board);
        game.startGame();
    }

    private static Integer nextInt(){
        return scanner.nextInt();
    }

    private static void write(String message){
        System.out.println(message);
    }
}