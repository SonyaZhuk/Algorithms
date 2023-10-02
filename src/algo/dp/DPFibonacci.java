package algo.dp;

/**
 * Dynamic Programming.
 * <p>
 * See Lakman p. 126
 */
public class DPFibonacci {

    /**
     * Standard fibonacci ,O(2^N) time, O(N) memory.
     * <p>
     * See Lakman p. 128
     */
    public int fibonacci(int i) {
        if (i == 0) return 0;
        if (i == 1) return 1;
        return fibonacci(i - 1) + fibonacci(i - 2);
    }

    /**
     * Fibonacci with memoization (cashing) ,O(N) time, O(N) memory.
     * <p>
     * See Lakman p. 129
     */
    public int fibonacciMemo(int n) {
        return fibonacci(n, new int[n + 1]);
    }

    private int fibonacci(int i, int[] memo) {
        if (i == 0 || i == 1) return i;

        if (memo[i] == 0) {
            memo[i] = fibonacci(i - 1, memo) + fibonacci(i - 2, memo);
        }
        return memo[i];
    }

    /**
     * Fibonacci with uprising DP,O(N) time, O(N) memory.
     * <p>
     * See Lakman p. 130
     */
    public int fibonacciUPMemo(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;

        int[] memo = new int[n];
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i < n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n - 1] + memo[n - 2];
    }

    /**
     * Fibonacci with uprising DP (without memo arr),O(N) time.
     * <p>
     * See Lakman p. 131
     */
    public int fibonacciUP(int n) {
        if (n == 0) return 0;
        int а = 0;
        int b = 1;
        for (int i = 2; i < n; i++) {
            int с = а + b;
            а = b;
            b = с;
        }
        return а + b;
    }

    /**
     * Unit tests.
     */
    public static void main(String[] args) {
        DPFibonacci dp = new DPFibonacci();
        System.out.println(dp.fibonacci(7));
        System.out.println(dp.fibonacciMemo(7));
    }
}
