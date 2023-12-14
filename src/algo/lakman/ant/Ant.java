package algo.lakman.ant;

public class Ant {
    private Position position;

    public Orientation getOrientation() {
        return orientation;
    }

    private Orientation orientation;

    public Ant() {
        this.position = new Position(0, 0);
        this.orientation = Orientation.RIGHT;
    }

    public void turn(boolean clockwise) {
        orientation = orientation.getTurn(clockwise);
    }

    public void move() {
        if (orientation == Orientation.LEFT) {
            position.setColumn(position.getColumn() - 1);
        } else if (orientation == Orientation.RIGHT) {
            position.setColumn(position.getColumn() + 1);
        } else if (orientation == Orientation.UP) {
            position.setRow(position.getRow() - 1);
        } else if (orientation == Orientation.DOWN) {
            position.setRow(position.getRow() + 1);
        }
    }

    public void adjustPosition(int shiftRow, int shiftColumn) {
        position.setRow(position.getRow() + shiftRow);
        position.setColumn(position.getColumn() + shiftColumn);
    }

    public Position getPosition() {
        return position;
    }
}
