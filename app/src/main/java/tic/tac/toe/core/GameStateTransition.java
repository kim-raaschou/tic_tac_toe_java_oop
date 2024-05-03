package tic.tac.toe.core;

@FunctionalInterface
public interface GameStateTransition{
    public GameState Execute();
}