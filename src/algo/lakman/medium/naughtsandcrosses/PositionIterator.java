package algo.lakman.medium.naughtsandcrosses;

import java.util.Iterator;

public class PositionIterator implements Iterator<Position> {
    private int rowIncrement, colIncrement, size;
    private Position current;

    public PositionIterator(Position pos, int rowIncrement, int colIncrement, int size) {
        this.rowIncrement = rowIncrement;
        this.colIncrement = colIncrement;
        this.size = size;
        current = new Position(pos.row - rowIncrement, pos.column - colIncrement);
    }

    @Override
    public boolean hasNext() {
        return current.row + rowIncrement < size &&
                current.column + colIncrement < size;
    }

    @Override
    public Position next() {
        current = new Position(current.row + rowIncrement,
                current.column + colIncrement);
        return current;
    }
}
