package algo.lakman.medium.naughtsandcrosses;

public class Check {
    private int row, column;
    private int rowIncrement, columnIncrement;

    public Check(int row, int column, int rowI, int colI) {
        this.row = row;
        this.column = column;
        this.rowIncrement = rowI;
        this.columnIncrement = colI;
    }

    public void increment() {
        row += rowIncrement;
        column += columnIncrement;

    }

    public boolean inBounds(int size) {
        return row >= 0 && column >= 0 && row < size && column < size;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getRowIncrement() {
        return rowIncrement;
    }

    public int getColumnIncrement() {
        return columnIncrement;
    }
}
