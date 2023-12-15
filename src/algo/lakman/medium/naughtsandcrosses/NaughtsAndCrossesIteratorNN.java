package algo.lakman.medium.naughtsandcrosses;

import java.util.ArrayList;
import java.util.List;

/**
 * Naughts and Crosses, N * N, using Iterator
 * <p>
 * See Lakman p. 499
 */
public class NaughtsAndCrossesIteratorNN {

    public Piece hasWon(Piece[][] board) {
        if (board.length != board[0].length) return Piece.EMPTY;
        int size = board.length;

        final List<PositionIterator> instructions = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            instructions.add(new PositionIterator(new Position(0, 1), 1, 0, size));
            instructions.add(new PositionIterator(new Position(i, 0), 0, 1, size));
        }
        instructions.add(new PositionIterator(new Position(0, 0), 1, 1, size));
        instructions.add(new PositionIterator(new Position(0, size - 1), 1, -1, size));

        for (PositionIterator iterator : instructions) {
            Piece winner = hasWon(board, iterator);
            if (winner != Piece.EMPTY) {
                return winner;
            }
        }
        return Piece.EMPTY;
    }

    public Piece hasWon(Piece[][] board, PositionIterator iterator) {
        Position firstPosition = iterator.next();
        Piece first = board[firstPosition.row][firstPosition.column];
        while (iterator.hasNext()) {
            Position position = iterator.next();
            if (board[position.row][position.column] != first) {
                return Piece.EMPTY;
            }
        }
        return first;
    }
}
