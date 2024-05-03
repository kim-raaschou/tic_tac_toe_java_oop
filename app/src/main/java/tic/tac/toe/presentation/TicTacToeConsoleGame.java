package tic.tac.toe.presentation;

import java.util.Optional;
import java.util.function.Consumer;

import com.google.common.base.Supplier;

import tic.tac.toe.core.Game;
import tic.tac.toe.core.GameScore;

public class TicTacToeConsoleGame implements Game {

    private static final String lineSeparator = System.lineSeparator();
    private final Supplier<Integer> reader;
    private final Consumer<String> writer;

    public TicTacToeConsoleGame(Supplier<Integer> getNextTurn, Consumer<String> writeMessage) {
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

    public void draw(GameScore scores) {
        final String boarderLine = "+---+---+---+" + lineSeparator;
        var scoresArr = scores.toArray();

        var board = new StringBuilder()
                .append(boarderLine)
                .append(new TicTacToeRow(scoresArr[0], scoresArr[1], scoresArr[2]))
                .append(boarderLine)
                .append(new TicTacToeRow(scoresArr[3], scoresArr[4], scoresArr[5]))
                .append(boarderLine)
                .append(new TicTacToeRow(scoresArr[6], scoresArr[7], scoresArr[8]))
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
