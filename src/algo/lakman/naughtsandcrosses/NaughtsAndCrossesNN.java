package algo.lakman.naughtsandcrosses;

import java.util.ArrayList;
import java.util.List;

/**
 * Naughts and Crosses, N * N.
 * <p>
 * See Lakman p. 498
 */
public class NaughtsAndCrossesNN {

    public Piece hasWon(Piece[][] board) {
        if (board.length != board[0].length) return Piece.EMPTY;
        int size = board.length;

        /* Построение списка Д/1Я проверки. */
        List<Check> instructions = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            instructions.add(new Check(0, i, 1, 0));
            instructions.add(new Check(i, 0, 0, 1));
        }
        instructions.add(new Check(0, 0, 1, 1));
        instructions.add(new Check(0, size - 1, 1, -1));

        /* Проверка. */
        for (Check instr : instructions) {
            Piece winner = hasWon(board, instr);
            if (winner != Piece.EMPTY) {
                return winner;
            }
        }
        return Piece.EMPTY;
    }

    private Piece hasWon(Piece[][] board, Check instr) {
        Piece first = board[instr.getRow()][instr.getColumn()];
        while (instr.inBounds(board.length)) {
            if (board[instr.getRow()][instr.getColumn()] != first) {
                return Piece.EMPTY;
            }
            instr.increment();
        }
        return first;
    }
}
