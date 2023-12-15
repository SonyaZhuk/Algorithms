package algo.lakman.medium.ant.grid;

import algo.lakman.medium.ant.Ant;
import algo.lakman.medium.ant.Position;

/**
 * Ant moving.
 * <p>
 * See Lakman p. 541
 */
public class Grid {
    private boolean[][] grid;

    private Ant ant = new Ant();

    public Grid() {
        grid = new boolean[1][1];
    }

    /* Перемещение муравья. */
    public void move() {
        ant.turn(grid[ant.getPosition().getRow()][ant.getPosition().getColumn()]);
        flip(ant.getPosition());
        ant.move();
        ensureFit(ant.getPosition()); // с увеличением
    }

    /* Вывод сетки. */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (r == ant.getPosition().getRow() && c == ant.getPosition().getColumn()) {
                    sb.append(ant.getOrientation());
                } else if (grid[r][c]) {
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
        int row = position.getRow();
        int col = position.getColumn();
        grid[row][col] = !grid[row][col];
    }

    /* Убедиться в том, что заданная позиция помещается в массиве.
       При необходимости удвоить размер матрицы, скопировать старые
       значения и перевести позицию муравья в положительный диапазон. */
    private void ensureFit(Position position) {
        int shiftRow = 0;
        int shiftColumn = 0;

        /* Вычисление нового количества строк. */
        int numRows = grid.length;
        if (position.getRow() < 0) {
            shiftRow = numRows;
            numRows *= 2;
        } else if (position.getRow() >= numRows) {
            numRows *= 2;
        }

        /* Вычисление нового количества столбцов. */
        int numColumns = grid[0].length;
        if (position.getColumn() < 0) {
            shiftColumn = numColumns;
            numColumns *= 2;
        } else if (position.getColumn() >= numColumns) {
            numColumns *= 2;
        }

        /* При необходимости увеличить массив и изменить позицию муравья. */
        if (numRows != grid.length || numColumns != grid[0].length) {
            boolean[][] newGrid = new boolean[numRows][numColumns];
            copyWithShift(grid, newGrid, shiftRow, shiftColumn);
            ant.adjustPosition(shiftRow, shiftColumn);
            grid = newGrid;
        }
    }


    /* Скопировать старые значения в новый массив, с применением смещений к строкам/столбцам. */
    private void copyWithShift(boolean[][] oldGrid, boolean[][] newGrid, int shiftRow, int shiftColumn) {
        for (int r = 0; r < oldGrid.length; r++) {
            for (int c = 0; c < oldGrid[0].length; c++) {
                newGrid[r + shiftRow][c + shiftColumn] = oldGrid[r][c];
            }
        }
    }
}
