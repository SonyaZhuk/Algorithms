package algo.dp.lakman;


import java.util.*;

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
        else if (arr[mid] > mid) return magicIndexBS(arr, low, mid - 1);
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
     * Gets all subsets. O(N*2^N), 2^N subsets.
     * <p>
     * See Lakman p. 362
     */
    public ArrayList<ArrayList<String>> getAllSubset(ArrayList<String> set) {
        // {}
        // {},{a1}
        // {}, {a1}, {a2}, {a1, a2}

        final ArrayList<ArrayList<String>> subsets = new ArrayList<>();
//        if (set.size() == size) {
//            subsets = new ArrayList<>();
//            subsets.add(new ArrayList<>());
//        } else {
//            subsets = getAllSubset(set,size + 1);
//            final String str = set.get(size);
//            ArrayList<ArrayList<String>> more = new ArrayList<>();
//            for (ArrayList<String> subset: subsets) {
//                ArrayList<String> currSubset = new ArrayList<>();
//                currSubset.addAll(subset);
//                currSubset.add(str);
//                more.add(currSubset);
//            }
//            subsets.addAll(more);
//        }

        return subsets;
    }

    /**
     * Multiple two digits without '*'.
     * <p>
     * See Lakman p. 364
     */
    public int multiplyDigits(int a, int b) {
        if (a == 0 || b == 0) return 0;
        if (a == 1) return b;
        if (b == 1) return a;
        return a + multiplyDigits(a, b - 1);
    }

    public int multiplyDigits1(int a, int b) {
        int smaller = (a < b) ? a : b;
        int bigger = (a < b) ? b : a;
        return multiplyDigitsHelper1(smaller, bigger);
    }

    private int multiplyDigitsHelper(int smaller, int bigger) {
        if (smaller == 0) return 0;
        if (smaller == 1) return bigger;

        int s = smaller >> 1;
        int side1 = multiplyDigits1(s, bigger);
        int side2 = side1;
        if (smaller % 2 == 1) {
            side2 = multiplyDigitsHelper(smaller - s, bigger);
        }
        return side1 + side2;
    }

    /**
     * Multiple two digits without '*'. O(S), where S = min(smaller, bigger)
     * <p>
     * See Lakman p. 364
     */
    private int multiplyDigitsHelper1(int smaller, int bigger) {
        if (smaller == 0) return 0;
        if (smaller == 1) return bigger;

        int s = smaller >> 1;
        int half = multiplyDigitsHelper1(s, bigger);

        return (smaller % 2 == 0) ? half + half : half + half + bigger;
    }

    /**
     * Hanoi tower.
     * <p>
     * See Lakman p. 369
     */
    public void towerTest() {
        int n = 3;
        Tower[] towers = new Tower[n];
        for (int i = 0; i < n; i++) {
            towers[i] = new Tower(i);
        }

        for (int i = n - 1; i >= 0; i--) {
            towers[0].add(i);
        }
        towers[0].moveDisks(n, towers[2], towers[1]);
    }

    private static class Tower {
        private Stack<Integer> disks;
        private int index;

        public Tower(int index) {
            this.index = index;
            disks = new Stack<>();
        }

        public void add(int disk) {
            if (!this.disks.isEmpty() && disks.peek() <= disk)
                throw new UnsupportedOperationException();
            this.disks.push(disk);
        }

        public void moveTop(Tower tower) {
            int top = this.disks.pop();
            tower.add(top);
        }

        public void moveDisks(int n, Tower destination, Tower buffer) {
            if (n > 0) {
                moveDisks(n - 1, buffer, destination);
                moveTop(destination);
                buffer.moveDisks(n - 1, destination, this);
            }
        }
    }

    /**
     * String permutation.
     * <p>
     * See Lakman p. 370
     */
    public ArrayList<String> getPerms(String str) {
        if (str == null) return null;

        ArrayList<String> perm = new ArrayList<>();
        if (str.length() == 0) {
            perm.add("");
            return perm;
        }

        char firstChar = str.charAt(0);
        String sub = str.substring(1);
        ArrayList<String> words = getPerms(sub);

        for (String word : words) {
            for (int i = 0; i <= word.length(); i++) {
                String p = insertCharAt(word, firstChar, i);
                perm.add(p);
            }
        }
        return perm;
    }

    private String insertCharAt(String s, char ch, int i) {
        final String start = s.substring(0, i);
        final String end = s.substring(i);
        return start + ch + end;
    }

    /**
     * String permutation, O(n!).
     * <p>
     * See Lakman p. 370
     */
    public ArrayList<String> getPerms1(String str) {
        if (str == null) return null;

        ArrayList<String> perm = new ArrayList<>();
        if (str.length() == 0) {
            perm.add("");
            return perm;
        }

        int len = str.length();

        for (int i = 0; i < len; i++) {
            final String before = str.substring(0, i);
            final String after = str.substring(i + 1, len);
            ArrayList<String> part = getPerms1(before + after);

            for (String p : part) {
                perm.add(str.charAt(i) + p);
            }
        }
        return perm;
    }

    /**
     * String permutation, O(n!).
     * <p>
     * See Lakman p. 372
     */
    public ArrayList<String> getPerms2(String str) {
        ArrayList<String> res = new ArrayList<>();
        getPerms("", str, res);
        return res;
    }

    private void getPerms(String prefix, String str, ArrayList<String> res) {
        if (str.length() == 0) res.add(prefix);

        int len = str.length();
        for (int i = 0; i < len; i++) {
            final String before = str.substring(0, i);
            final String after = str.substring(i + 1);
            char c = str.charAt(i);
            getPerms(prefix + c, before + after, res);
        }
    }

    /**
     * String permutation with duplications, O(n!).
     * <p>
     * See Lakman p. 374
     */
    public ArrayList<String> getPermsWithDup(String str) {
        final ArrayList<String> res = new ArrayList<>();
        final Map<Character, Integer> map = buildMap(str);
        getPerms(map, "", str.length(), res);
        return res;
    }

    private Map<Character, Integer> buildMap(String str) {
        final Map<Character, Integer> map = new HashMap<>();
        final char[] arr = str.toCharArray();
        for (char ch : arr) {
            if (!map.containsKey(ch)) {
                map.put(ch, 0);
            }
            map.put(ch, map.get(ch) + 1);
        }
        return map;
    }

    private void getPerms(Map<Character, Integer> map, String prefix,
                          int index, ArrayList<String> res) {
        if (index == 0) {
            res.add(prefix);
            return;
        }

        for (char ch : map.keySet()) {
            int c = map.get(ch);
            if (c > 0) {
                map.put(ch, c - 1);
                getPerms(map, prefix + ch, index - 1, res);
                map.put(ch, c);
            }
        }
    }

    /**
     * Unit tests.
     */
    public static void main(String[] args) {
        DynamicProg dynamicProg = new DynamicProg();
        System.out.println(dynamicProg.countStepsWithMemo(5));
        ArrayList<String> set = new ArrayList<>();
        set.add("a1");
        set.add("a2");
        //dynamicProg.getAllSubset(set);
        int b = -3;
        int c = dynamicProg.multiplyDigits(2, Math.abs(b));
        System.out.println((b < 0) ? -c : c);
        System.out.println(dynamicProg.multiplyDigits1(5, 6));
        dynamicProg.towerTest();
        dynamicProg.getPermsWithDup("aabc");
    }
}
