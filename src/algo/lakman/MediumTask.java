package algo.lakman;

import java.util.HashMap;
import java.util.Map;

/**
 * Medium level tasks.
 * <p>
 * See Lakman p. 489
 */
public class MediumTask {

    /**
     * Exchange values without a temp variable.
     * <p>
     * See Lakman p. 489
     */
    public void exchange(int x, int y) {
        x = x - y;
        y = x + y;
        x = y - x;
        System.out.println(x + " " + y);
    }

    /**
     * Exchange values without a temp variable. Advantages: can use it with another data types.
     * <p>
     * See Lakman p. 489
     */
    public void exchangeI(int x, int y) {
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println(x + " " + y);
    }

    /**
     * Count a word frequency.
     * <p>
     * See Lakman p. 490
     */
    public int getFrequency(String[] words, String word) {
        if (words == null || word == null) return -1;
        final Map<String, Integer> dict = fillDict(words);
        final String s = word.trim().toLowerCase();
        return dict.getOrDefault(s, 0);
    }

    private Map<String, Integer> fillDict(String[] words) {
        final Map<String, Integer> dict = new HashMap<>();
        for (String word : words) {
            final String s = word.trim().toLowerCase();
            dict.put(s, dict.getOrDefault(s, 1) + 1);
        }
        return dict;
    }

    /**
     * Finds intersection point for two line segments.
     * <p>
     * See Lakman p. 491
     */
    public Point intersection(Point startI, Point endI, Point startII, Point endII) {

        if (startI.getX() > endI.getX()) swap(startI, endI);
        if (startII.getX() > endII.getX()) swap(startII, endII);

        if (startI.getX() > startII.getX()) {
            swap(startI, startII);
            swap(endI, endII);
        }

        final Line line1 = new Line(startI, endI);
        final Line line2 = new Line(startII, endII);

        //in case parallel lines
        if (line1.getSlope() == line2.getSlope()
                && line1.getYintercept() == line2.getYintercept()) {
            return (isBetween(startI, startII, endII)) ? startII : null;
        }

        //intersection
        double x = (line2.getYintercept() - line1.getYintercept()) / (line1.getSlope() - line2.getSlope());
        double y = x * line1.getSlope() + line1.getYintercept();
        Point intersection = new Point(x, y);

        return (isBetween(startI, intersection, endI) &&
                isBetween(startII, intersection, endII)) ? intersection : null;
    }

    private boolean isBetween(Point start, Point middle, Point end) {
        return isBetween(start.getX(), middle.getX(), end.getX()) &&
                isBetween(start.getY(), middle.getY(), end.getY());
    }

    private boolean isBetween(double start, double middle, double end) {
        return (start > end) ?
                end <= middle && middle <= start :
                start <= middle && middle <= end;
    }

    private void swap(Point one, Point two) {
        double x = one.getX();
        double y = one.getY();
        one.setLocation(two.getX(), two.getY());
        two.setLocation(x, y);
    }

    public static void main(String[] args) {
        MediumTask task = new MediumTask();
        task.exchangeI(5, 8);
    }
}
