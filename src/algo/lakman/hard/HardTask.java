package algo.lakman.hard;

/**
 * Hard level tasks.
 * <p>
 * See Lakman p. 561
 */
public class HardTask {

    /**
     * Sum operation without any arithmetic signs.
     * <p>
     * See Lakman p. 561
     */
    public int add(int a, int b) {
        if (a == 0) return b;
        int sum = a ^ b;
        int c = (a & b) << 1;
        return add(sum, c);
    }

    public int addIterative(int a, int b) {
        if (a == 0) return b;
        while (b != 0) {
            int sum = a ^ b;
            int c = (a & b) << 1;
            a = sum;
            b = c;
        }
        return a;
    }
}
