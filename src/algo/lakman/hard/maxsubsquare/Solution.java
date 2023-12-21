package algo.lakman.hard.maxsubsquare;

public class Solution {

    /**
     * Finds the max sub-square. O(N^4) time complexity.
     * <p>
     * See Lakman p. 644
     */
    public Subsquare findSquare(int[][] matrix) {
        for (int i = matrix.length; i >= 1; i--) {
            Subsquare square = findSquareWithSize(matrix, i);
            if (square != null) return square;
        }
        return null;
    }

    private Subsquare findSquareWithSize(int[][] matrix, int squareSize) {
        /* На ребре длиной N существуют (N - sz + 1) квадратов длиной sz. */
        int count = matrix.length - squareSize + 1;

        /* Перебор всех квадратов с длиной стороны squareSize. */
        for (int row = 0; row < count; row++) {
            for (int col = 0; col < count; col++) {
                if (isSquare(matrix, row, col, squareSize)) {
                    return new Subsquare(row, col, squareSize);
                }
            }
        }
        return null;
    }

    private boolean isSquare(int[][] matrix, int row, int col, int size) {
        // Проверка верхней и нижней границы
        for (int j = 0; j < size; j++) {
            if (matrix[row][col + j] == 1) {
                return false;
            }
            if (matrix[row + size - 1][col + j] == 1) {
                return false;
            }
        }
        // Проверка левой и правой границы
        for (int i = 1; i < size - 1; i++) {
            if (matrix[row + i][col] == 1) {
                return false;
            }
            if (matrix[row + i][col + size - 1] == 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds the max sub-square. O(N^3) time complexity.
     * <p>
     * See Lakman p. 645
     */
    public Subsquare findSquareI(int[][] matrix) {
        SquareCell[][] processed = processSquare(matrix);
        for (int i = matrix.length; i >= 1; i--) {
            Subsquare square = findSquareWithSize(processed, i);
            if (square != null) return square;
        }
        return null;
    }

    private Subsquare findSquareWithSize(SquareCell[][] processed, int size) {
        int count = processed.length - size + 1;

        /* Перебор всех квадратов с длиной стороны size. */
        for (int row = 0; row < count; row++) {
            for (int col = 0; col < count; col++) {
                if (isSquareI(processed, row, col, size)) {
                    return new Subsquare(row, col, size);
                }
            }
        }
        return null;
    }

    private boolean isSquareI(SquareCell[][] matrix, int row, int col, int sz) {
        SquareCell topLeft = matrix[row][col];
        SquareCell topRight = matrix[row][col + sz - 1];
        SquareCell bottomLeft = matrix[row + sz - 1][col];

        /* Проверка верхней, левой, правой и нижней стороны. */
        if (topLeft.getZerosRight() < sz || topLeft.getZerosBelow() < sz ||
                topRight.getZerosBelow() < sz || bottomLeft.getZerosRight() < sz) {
            return false;
        }
        return true;
    }

    private SquareCell[][] processSquare(int[][] matrix) {
        SquareCell[][] processed = new SquareCell[matrix.length][matrix.length];

        for (int r = matrix.length - 1; r >= 0; r--) {
            for (int c = matrix.length - 1; c >= 0; c--) {
                int rightZeros = 0;
                int belowZeros = 0;
                // Выполняется только для черной ячейки
                if (matrix[r][c] == 0) {
                    rightZeros++;
                    belowZeros++;
                    // Следующий столбец в той же строке
                    if (c + 1 < matrix.length) {
                        SquareCell previous = processed[r][c + 1];
                        rightZeros += previous.getZerosRight();
                    }
                    if (r + 1 < matrix.length) {
                        SquareCell previous = processed[r + 1][c];
                        belowZeros += previous.getZerosBelow();
                    }
                }
                processed[r][c] = new SquareCell(rightZeros, belowZeros);
            }
        }
        return processed;
    }
}