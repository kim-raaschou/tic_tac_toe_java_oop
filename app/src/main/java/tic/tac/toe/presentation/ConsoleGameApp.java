package tic.tac.toe.presentation;

import java.util.Scanner;

import tic.tac.toe.core.GameEngine;

public class ConsoleGameApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var board = new ConsoleGameBoard(ConsoleGameApp::nextInt,ConsoleGameApp::write);
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