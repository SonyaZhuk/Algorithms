package algo.lakman.hard.submatrix;

public class Range {
    private int start, end, sum;

    public Range(int start, int end, int sum) {
        this.start = start;
        this.end = end;
        this.sum = sum;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getSum() {
        return sum;
    }
}
