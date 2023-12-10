package algo.lakman;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

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

    //---------------------------------------------------//

    /**
     * Operations minus using only the plus operation. O(k)
     * <p>
     * See Lakman p. 506
     */
    public int minus(int a, int b) {
        return a + negate(b);
    }

    private int negate(int a) {
        int neg = 0;
        int newSign = (a < 0) ? 1 : -1;
        while (a != 0) {
            neg += newSign;
            a += newSign;
        }

        return neg;
    }

    /**
     * Operations multiply using only the plus operation.
     * <p>
     * See Lakman p. 507
     */
    public int multiply(int a, int b) {
        if (b > a) {
            multiply(b, a);
        }

        int res = -1;
        int index = b;
        if (b < 0) {
            index = negate(b);
        }
        for (int i = 0; i < index; i++) {
            res += a;
        }

        return ((b < 0 && a > 0) || (b < 0 && a < 0)) ?
                negate(res + 1) : res + 1;
    }

    /**
     * Operations divide using only the plus operation.
     * <p>
     * See Lakman p. 508
     */
    public int divide(int a, int b) {
        if (b == 0)
            throw new ArithmeticException();

        int absA = abs(a);
        int absB = abs(b);

        int product = 0;
        int x = 0;

        while (product + absB <= absA) {
            product += absB;
            x++;
        }

        return ((a < 0 && b < 0) || (a > 0 && b > 0)) ? x : negate(x);
    }

    private int abs(int a) {
        return (a < 0) ? negate(a) : a;
    }

    //------------------------------------------------------//

    /**
     * Finds the year when max alive. Brute force, O(N*M) time
     * <p>
     * See Lakman p. 508
     */
    public int maxAliveYear(Person[] people, int min, int max) {
        int maxAlive = 0;
        int maxAliveYear = min;
        for (int year = min; year <= max; year++) {
            int alive = 0;
            for (Person person : people) {
                if (person.getBirth() <= year && year <= person.getDeath()) {
                    alive++;
                }
            }
            if (alive > maxAlive) {
                maxAlive = alive;
                maxAliveYear = year;
            }
        }

        return maxAliveYear;
    }

    /**
     * Finds the year when max alive. Brute force, O(N + M) time
     * <p>
     * See Lakman p. 513
     */
    public int maxAliveYearI(Person[] people, int min, int max) {
        /* Построение массива прироста населения. */
        int[] populationDeltas = getPopulationDeltas(people, min, max);
        int maxAliveYear = getMaxAliveYear(populationDeltas);
        return maxAliveYear + min;
    }

    /* Добавление годов рождения и смерти в массив прироста. */
    private int[] getPopulationDeltas(Person[] people, int min, int max) {
        int[] populationDeltas = new int[max - min + 2];
        for (Person person : people) {
            int birth = person.getBirth() - min;
            populationDeltas[birth]++;
            int death = person.getDeath() - min;
            populationDeltas[death + 1]--;
        }
        return populationDeltas;
    }

    /* Вычисление текущих сумм и возвращение индекса с максимумом. */
    private int getMaxAliveYear(int[] deltas) {
        int maxAliveYear = 0;
        int maxAlive = 0;
        int currentlyAlive = 0;
        for (int year = 0; year < deltas.length; year++) {
            currentlyAlive += deltas[year];
            if (currentlyAlive > maxAlive) {
                maxAliveYear = year;
                maxAlive = currentlyAlive;
            }
        }

        return maxAliveYear;
    }

    //-------------------------------------------------------------------------//
    /**
     * Builds springboard with using two types of planks. O(2^k) times, k - count of planks - without memo.
     * O(k^2) - with memo.
     * <p>
     * See Lakman p. 514
     */
    private HashSet<Integer> lengths;
    //memoization
    private HashSet<String> visited = new HashSet<>();

    public HashSet<Integer> springboard(int k, int shorter, int longer) {
        lengths = new HashSet<>();
        getLengths(k, 0, shorter, longer);
        return lengths;
    }

    private void getLengths(int k, int total, int shorter, int longer) {
        if (k == 0) {
            lengths.add(total);
            return;
        }

        //memoization
        String key = k + " " + total;
        if (visited.contains(key)) {
            return;
        }

        getLengths(k - 1, total + shorter, shorter, longer);
        getLengths(k - 1, total + longer, shorter, longer);
        visited.add(key);
    }

    /**
     * Builds springboard with using two types of planks.
     * <p>
     * See Lakman p. 516
     */
    public HashSet<Integer> springboardI(int k, int shorter, int longer) {
        HashSet<Integer> lengths = new HashSet<>();
        for (int nShorter = 0; nShorter <= k; nShorter++) {
            int nlonger = k - nShorter;
            int length = nShorter * shorter + nlonger * longer;
            lengths.add(length);
        }
        return lengths;
    }


    public static void main(String[] args) {
        MediumTask task = new MediumTask();
        int[] arr1 = {1, 3, 15, 11, 2};
        int[] arr2 = {23, 127, 235, 19, 8};
        System.out.println(task.multiply(-7, -8));
    }
}
