package algo.lakman.hard.maxsubsquare;

public class SquareCell {
    private int zerosRight;
    private int zerosBelow;

    public SquareCell(int zerosRight, int zerosBelow) {
        this.zerosRight = zerosRight;
        this.zerosBelow = zerosBelow;
    }
    public SquareCell() {
        this.zerosRight = 0;
        this.zerosBelow = 0;
    }

    public int getZerosRight() {
        return zerosRight;
    }

    public int getZerosBelow() {
        return zerosBelow;
    }
}
