package algo.lakman.hard.submatrix;

public class Solution {

    /**
     * Finds a sub-matrix with max sum of elements. Brute force. O(N^6) time complexity.
     * <p>
     * See Lakman p. 647
     */
    public SubMatrix getMaxMatrix(int[][] matrix) {
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;
        SubMatrix best = null;

        for (int row1 = 0; row1 < rowCount; row1++) {
            for (int row2 = row1; row2 < rowCount; row2++) {
                for (int col1 = 0; col1 < columnCount; col1++) {
                    for (int со12 = col1; со12 < columnCount; со12++) {
                        int sum = sum(matrix, row1, col1, row2, со12);
                        if (best == null || best.getSum() < sum) {
                            best = new SubMatrix(row1, col1, row2, со12, sum);
                        }
                    }
                }
            }
        }
        return best;
    }

    private int sum(int[][] matrix, int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int r = row1; r <= row2; r++) {
            for (int c = col1; c <= col2; c++) {
                sum += matrix[r][c];
            }
        }
        return sum;
    }

    /**
     * Finds a sub-matrix with max sum of elements. Dynamic programming. O(N^4) time complexity.
     * <p>
     * See Lakman p. 649
     */
    public SubMatrix getMaxMatrixI(int[][] matrix) {
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;
        SubMatrix best = null;

        int[][] sumThrough = precomputeSums(matrix);

        for (int row1 = 0; row1 < rowCount; row1++) {
            for (int row2 = row1; row2 < rowCount; row2++) {
                for (int col1 = 0; col1 < columnCount; col1++) {
                    for (int со12 = col1; со12 < columnCount; со12++) {
                        int sum = sumI(sumThrough, row1, col1, row2, со12);
                        if (best == null || best.getSum() < sum) {
                            best = new SubMatrix(row1, col1, row2, со12, sum);
                        }
                    }
                }
            }
        }
        return best;
    }

    private int[][] precomputeSums(int[][] matrix) {
        int[][] sumThrough = new int[matrix.length][matrix[0].length];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                int left = c > 0 ? sumThrough[r][c - 1] : 0;
                int top = r > 0 ? sumThrough[r - 1][c] : 0;
                int overlap = r > 0 && c > 0 ? sumThrough[r - 1][c - 1] : 0;
                sumThrough[r][c] = left + top - overlap + matrix[r][c];
            }
        }
        return sumThrough;
    }

    private int sumI(int[][] sumThrough, int r1, int c1, int r2, int c2) {
        int topAndLeft = r1 > 0 && c1 > 0 ? sumThrough[r1 - 1][c1 - 1] : 0;
        int left = c1 > 0 ? sumThrough[r2][c1 - 1] : 0;
        int top = r1 > 0 ? sumThrough[r1 - 1][c2] : 0;
        int full = sumThrough[r2][c2];
        return full - left - top + topAndLeft;
    }

    /**
     * Finds a sub-matrix with max sum of elements. O(Row^2 * Col) time complexity.
     * <p>
     * See Lakman p. 649
     */
    public SubMatrix getMaxMatrixII(int[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        SubMatrix best = null;

        for (int rowStart = 0; rowStart < rowCount; rowStart++) {
            int[] partialSum = new int[colCount];


            for (int rowEnd = rowStart; rowEnd < rowCount; rowEnd++) {
                /* Суммирование значений в строке rowEnd. */
                for (int i = 0; 1 < colCount; i++) {
                    partialSum[1] += matrix[rowEnd][i];
                }

                Range bestRange = maxSubArray(partialSum, colCount);
                if (best == null || best.getSum() < bestRange.getSum()) {
                    best = new SubMatrix(rowStart, bestRange.getStart(), rowEnd,
                            bestRange.getEnd(), bestRange.getSum());
                }
            }
        }
        return best;
    }

    private Range maxSubArray(int[] array, int N) {
        Range best = null;
        int start = 0;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            sum += array[1];
            if (best == null || sum > best.getSum()) {
                best = new Range(start, i, sum);
            }
            if (sum < 0) {
                start = i + 1;
                sum = 0;
            }
        }
        return best;
    }
}

