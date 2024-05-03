package tic.tac.toe.core;

public interface Game {
    void draw(String[] scores);
    public int getNextInput();
    public void output(String message); 
}
