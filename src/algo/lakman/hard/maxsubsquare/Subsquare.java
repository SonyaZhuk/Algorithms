package algo.lakman.hard.maxsubsquare;

public class Subsquare {
    private int row;
    private int col;
    private int squareSize;

    public Subsquare(int row, int col, int squareSize) {
        this.row = row;
        this.col = col;
        this.squareSize = squareSize;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getSquareSize() {
        return squareSize;
    }
}
