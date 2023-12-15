package algo.lakman.medium;

public class Line {
    private double slope;
    private double yintercept;

    public Line(Point start, Point end) {
        double deltaY = end.getY() - start.getY();
        double deltaX = end.getX() - start.getX();
        this.slope = deltaY / deltaX;
        this.yintercept = end.getY() + slope * end.getX();
    }

    public double getSlope() {
        return slope;
    }

    public double getYintercept() {
        return yintercept;
    }
}
