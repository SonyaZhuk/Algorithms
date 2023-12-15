package algo.lakman.medium.ant.board;

import algo.lakman.medium.ant.Ant;
import algo.lakman.medium.ant.Position;

import java.util.HashSet;

/**
 * Ant moving.
 * <p>
 * See Lakman p. 546
 */
public class Board {
    private HashSet<Position> whites;
    private Ant ant;
    private Position topLeftCorner;
    private Position bottomRightCorner;

    public Board() {
        this.whites = new HashSet<>();
        this.ant = new Ant();
        this.topLeftCorner = new Position(0, 0);
        this.bottomRightCorner = new Position(0, 0);
    }

    public void move() {
        ant.turn(isWhite(ant.getPosition())); // Поворот
        flip(ant.getPosition()); // Изменение цвета
        ant.move(); // move
        ensureFit(ant.getPosition());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int rowMin = topLeftCorner.getRow();
        int rowMax = bottomRightCorner.getRow();
        int colMin = topLeftCorner.getColumn();
        int colMax = bottomRightCorner.getColumn();
        for (int r = rowMin; r <= rowMax; r++) {
            for (int c = colMin; c <= colMax; c++) {
                if (r == ant.getPosition().getRow() && c == ant.getPosition().getColumn()) {
                    sb.append(ant.getOrientation());
                } else if (isWhite(r, c)) {
                    sb.append("X");
                } else {
                    sb.append("_");
                }
            }
            sb.append("\n");
        }
        sb.append("Ant: " + ant.getOrientation() + "\n");
        return sb.toString();
    }

    /* Изменение цвета ячеек. */
    private void flip(Position position) {
        if (whites.contains(position)) {
            whites.remove(position);
        } else {
            whites.add(position.clone());
        }
    }

    /*Проверка границ (левый верхний и правый нижний угол).*/
    private void ensureFit(Position position) {
        int row = position.getRow();
        int column = position.getColumn();

        topLeftCorner.setRow(Math.min(topLeftCorner.getRow(), row));
        topLeftCorner.setColumn(Math.min(topLeftCorner.getColumn(), column));

        bottomRightCorner.setRow(Math.max(bottomRightCorner.getRow(), row));
        bottomRightCorner.setColumn(Math.max(bottomRightCorner.getColumn(), column));
    }

    /* Проверяет, является ли ячейка белой. */
    private boolean isWhite(Position pos) {
        return whites.contains(pos);
    }

    private boolean isWhite(int row, int column) {
        return whites.contains(new Position(row, column));
    }
}
