package tic.tac.toe.core;

public interface TicTacToeGame {
    void draw(TicTacToeScore scores);
    public int getNextInput();
    public void output(String message); 
}
