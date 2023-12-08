package algo.lakman;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
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


    /**
     * Finds ending zeros for n!.
     * <p>
     * See Lakman p. 500
     */
    public int countFactZeros(int num) {
        int count = 0;
        for (int i = 2; i <= num; i++) {
            count += factorsOfFive(i);
        }

        return count;
    }

    private int factorsOfFive(int i) {
        int count = 0;
        while (i % 5 == 0) {
            count++;
            i /= 5;

        }
        return count;
    }

    /**
     * Finds ending zeros for n!. Optimization.
     * <p>
     * See Lakman p. 500
     */
    public int countFactZerosI(int num) {
        int count = 0;
        if (num < 0)
            return -1;

        for (int i = 5; num / i > 0; i *= 5) {
            count += num / i;
        }

        return count;
    }

    /**
     * Finds Math.min(elem - elem > 0). Brute force, O(NM) time.
     * <p>
     * See Lakman p. 501
     */
    public int findSmallestDiff(int[] arr1, int[] arr2) {
        if (arr1.length == 0 || arr2.length == 0) return -1;

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                int c = Math.abs(arr1[i] - arr2[j]);
                if (c > 0) {
                    min = Math.min(min, c);
                }
            }
        }
        return min;
    }

    /**
     * Finds Math.min(elem - elem > 0). O(NlogN + MlogM) time.
     * <p>
     * See Lakman p. 503
     */
    public int findSmallestDiffI(int[] arr1, int[] arr2) {
        if (arr1.length == 0 || arr2.length == 0) return -1;

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int i = 0;
        int j = 0;
        int min = Integer.MAX_VALUE;

        while (i < arr1.length && j < arr2.length) {
            int c = Math.abs(arr1[i] - arr2[j]);
            if (c < min) {
                min = c;
            }

            if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return min;
    }

    /**
     * Finds Math.max(a, b) without any comparisons.
     * <p>
     * See Lakman p. 503
     */
    public int getMax(int a, int b) {
        int k = sign(a - b);
        int q = flip(k);
        return a * k + b * q;
    }

    /* 1, a > 0, 0, a < 0 */
    private int sign(int a) {
        return flip((a >> 31) & 0x1);
    }

    /* 1 -> 0, 0 -> 1 */
    private int flip(int bit) {
        return 1 ^ bit;
    }

    private String[] smalls = {"Zero", "Оnе", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Теn", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    private String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private String[] bigs = {"", "Thousand", "Million", "Billion"};
    private String hundred = "Hundred";
    private String negative = "Negative";

    /**
     * Converts number to text.
     * <p>
     * See Lakman p. 504
     */
    public String convert(int num) {
        if (num == 0) {
            return smalls[0];
        } else if (num < 0) {
            return negative + " " + convert(-1 * num);
        }

        final LinkedList<String> parts = new LinkedList<>();
        int chunkCount = 0;

        while (num > 0) {
            if (num % 1000 != 0) {
                String chunk = convertChunk(num % 1000) + " " + bigs[chunkCount];
                parts.addFirst(chunk);
            }
            num /= 1000;
            chunkCount++;
        }

        return listToString(parts);
    }

    private String convertChunk(int number) {
        LinkedList<String> parts = new LinkedList<>();
        // сотни
        if (number >= 100) {
            parts.addLast(smalls[number / 100]);
            parts.addLast(hundred);
            number %= 100;
        }
        //десятки
        if (number >= 10 && number <= 19) {
            parts.addLast(smalls[number]);
        } else if (number >= 20) {
            parts.addLast(tens[number / 10]);
            number %= 10;
        }

        // единицы
        if (number >= 1 && number <= 9) {
            parts.addLast(smalls[number]);
        }

        return listToString(parts);
    }

    private String listToString(LinkedList<String> parts) {
        StringBuilder sb = new StringBuilder();
        while (parts.size() > 1) {
            sb.append(parts.pop());
            sb.append(" ");
        }
        sb.append(parts.pop());
        return sb.toString();
    }


    public static void main(String[] args) {
        MediumTask task = new MediumTask();
        int[] arr1 = {1, 3, 15, 11, 2};
        int[] arr2 = {23, 127, 235, 19, 8};
        System.out.println(task.convert(193));
    }
}
