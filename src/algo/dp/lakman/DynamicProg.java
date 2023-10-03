package algo.dp.lakman;


/**
 * Dynamic Programming.
 * <p>
 * See Lakman p. 356
 */
public class DynamicProg {

    /**
     * Stairs imitation.
     * <p>
     * See Lakman p. 356
     */
    public int countSteps(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        return countSteps(n - 1) + countSteps(n - 2) + countSteps(n - 3);
    }

    /**
     * Stairs imitation with memorization.
     * <p>
     * See Lakman p. 357
     */
    public int countStepsWithMemo(int n) {
        int[] memo = new int[n + 1];
        return countSteps(n, memo);
    }

    private int countSteps(int n, int[] memo) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        if (memo[n] != 0) return memo[n];
        memo[n] = countSteps(n - 1, memo) + countSteps(n - 2, memo) + countSteps(n - 3, memo);
        return memo[n];
    }

    /**
     * Search index with condition a[i] = i. Brute-force.
     * <p>
     * See Lakman p. 360
     */
    public int magicIndex(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == i) return i;
        }
        return -1;
    }

    /**
     * Search index with condition a[i] = i. Binary Search. It works only for unique elements.
     * <p>
     * See Lakman p. 361
     */
    public int magicIndexBS(int[] arr) {
        return magicIndexBS(arr, 0, arr.length - 1);
    }

    private int magicIndexBS(int[] arr, int low, int high) {
        if (high < low) return -1;
        int mid = low + (high - low) / 2;
        if (arr[mid] == mid) return mid;
        else if(arr[mid] > mid) return magicIndexBS(arr, low, mid - 1);
        else return magicIndexBS(arr, mid + 1, high);
    }

    /**
     * Search index with condition a[i] = i. For non-unique values.
     * <p>
     * See Lakman p. 361
     */
    public int magicFast(int[] arr) {
        return magicFast(arr, 0, arr.length - 1);
    }

    private int magicFast(int[] arr, int low, int high) {
        if (high < low) return -1;

        int mid = (low + high) / 2;

        if (arr[mid] == mid) return mid;

        //search on the left side
        int leftIndex = Math.max(mid - 1, arr[mid]);
        int left = magicFast(arr, low, leftIndex);
        if (left > 0) return left;

        //search on the right part
        int rightIndex = Math.max(mid + 1, arr[mid]);
        int right = magicFast(arr, rightIndex, high);
        return right;
    }

    /**
     * Unit tests.
     */
    public static void main(String[] args) {
        DynamicProg dynamicProg = new DynamicProg();
        System.out.println(dynamicProg.countStepsWithMemo(5));
    }
}
