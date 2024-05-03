package tic.tac.toe.presentation;

import java.util.Optional;
import java.util.function.Consumer;

import com.google.common.base.Supplier;

import tic.tac.toe.core.Game;

public class ConsoleGame implements Game {

    private static final String lineSeparator = System.lineSeparator();
    private final Supplier<Integer> reader;
    private final Consumer<String> writer;

    public ConsoleGame(Supplier<Integer> getNextTurn, Consumer<String> writeMessage) {
        this.reader = getNextTurn;
        this.writer = writeMessage;
    }

    record TicTacToeRow(String cell1, String cell2, String cell3) {

        @Override
        public final String toString() {
            return String.format(
                    "| %s | %s | %s |%s",
                    formatCell(cell1),
                    formatCell(cell2),
                    formatCell(cell3),
                    lineSeparator);
        }

        private String formatCell(String cell) {
            return Optional
                    .ofNullable(cell)
                    .map(Object::toString)
                    .orElse(" ");
        }
    }

    public void draw(String[] scores) {
        final String boarderLine = "+---+---+---+" + lineSeparator;
        
        var board = new StringBuilder()
                .append(boarderLine)
                .append(new TicTacToeRow(scores[0], scores[1], scores[2]))
                .append(boarderLine)
                .append(new TicTacToeRow(scores[3], scores[4], scores[5]))
                .append(boarderLine)
                .append(new TicTacToeRow(scores[6], scores[7], scores[8]))
                .append(boarderLine)
                .toString();

        output(board);
    }

    @Override
    public int getNextInput() {
        return reader.get();
    }

    @Override
    public void output(String message) {
        writer.accept(message);
    }
}
