package tic.tac.toe.presentation;

import java.util.Optional;
import java.util.function.Consumer;

import tic.tac.toe.core.TicTacToeBoard;
import tic.tac.toe.core.TicTacToeScore;

public class TicTacToeConsoleBoard implements TicTacToeBoard {

    private static final String lineSeparator = System.lineSeparator();
    private Consumer<String> writer;

    public TicTacToeConsoleBoard() {
        this(s -> System.console().printf(s));
    }

    public TicTacToeConsoleBoard(Consumer<String> writer) {
        this.writer = writer;
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

    public void draw(TicTacToeScore scores_) {
        final String boarderLine = "+---+---+---+" + lineSeparator;
        var scores = scores_.toArray();

        var board = new StringBuilder()
                .append(boarderLine)
                .append(new TicTacToeRow(scores[0], scores[1], scores[2]))
                .append(boarderLine)
                .append(new TicTacToeRow(scores[3], scores[4], scores[5]))
                .append(boarderLine)
                .append(new TicTacToeRow(scores[6], scores[7], scores[8]))
                .append(boarderLine)
                .toString();

        writer.accept(board);
    }
}
