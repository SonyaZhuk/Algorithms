package algo.sorting.lakman;

import java.util.*;

/**
 * Sorting and Search.
 * <p>
 * See Lakman p. 416
 */
public class Sorting {

    public int[] mergeArrays(int[] A, int[] B) {
        int indexB = B.length - 1;
        int indexA = A.length - B.length - 1;
        int indexMerged = A.length - 1;
        while (indexB >= 0) {
            if (indexA > 0 && A[indexA] > B[indexB]) {
                A[indexMerged] = A[indexA];
                indexA--;
            } else {
                A[indexMerged] = B[indexB];
                indexB--;
            }
            indexMerged--;
        }

        return A;
    }


    /**
     * Sorts strings as anagram, O(N*logN).
     * <p>
     * See Lakman p. 416
     */
    public String[] sortAnagram1(String[] arr) {
        Arrays.sort(arr, new AnagramComparator());
        return arr;
    }

    public class AnagramComparator implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return sortChars(s1).compareTo(sortChars(s2));
        }

    }

    /**
     * Sorts strings as anagram, O(2*N).
     * <p>
     * See Lakman p. 416
     */
    public String[] sortAnagram2(String[] arr) {
        final Map<String, List<String>> anagrams = new HashMap<>();
        for (String s : arr) {
            final String sortedS = sortChars(s);
            final List<String> currList = (anagrams.containsKey(sortedS)) ?
                    anagrams.get(sortedS) : new ArrayList<>();
            currList.add(s);
            anagrams.put(sortedS, currList);
        }

        int index = 0;
        for (String key : anagrams.keySet()) {
            final List<String> currList = anagrams.get(key);
            for (String s : currList) {
                arr[index] = s;
                index++;
            }
        }

        return arr;
    }

    private String sortChars(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    /**
     * Search index for the given element in arrays with shifts, O(log), in the worst case O(N).
     * <p>
     * See Lakman p. 418
     */
    public int searchElement(int[] arr, int x, int low, int high) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (arr[mid] == x) return mid;

        if (arr[mid] > arr[low]) { //left side has order
            if (x > arr[low] && x < arr[mid]) {
                searchElement(arr, x, low, mid - 1); //search in left
            } else {
                searchElement(arr, x, mid + 1, high); //search in right
            }
        } else if (arr[mid] < arr[high]) {// right side has order
            if (x > arr[mid] && x < arr[high]) {
                searchElement(arr, x, mid + 1, high); //search in right
            } else {
                searchElement(arr, x, low, mid - 1); //search in left
            }
        } else if (arr[mid] == arr[low]) {
            if (arr[mid] != arr[high]) {
                searchElement(arr, x, mid + 1, high);
            } else { //search in both parts
                int res = searchElement(arr, x, low, mid - 1);
                if (res == -1) {
                    return searchElement(arr, x, mid + 1, high);
                }
                return res;
            }

        }

        return -1;
    }

    /**
     * Search index for the given element in the Listy structure, O(log).
     * <p>
     * See Lakman p. 421
     */
    public int searchElement(Listy list, int x) {
        //get size with O(logN)
        int size = 1;
        while (list.elementAt(size) != -1 && list.elementAt(size) < x) {
            size *= 2;
        }

        return binarySearchListy(list, x, 0, size);

    }

    private int binarySearchListy(Listy listy, int x, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midEl = listy.elementAt(mid);

            if (midEl == x) return mid;
            if (midEl > x || midEl == -1) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public class Listy {
        public int elementAt(int index) {
            return 0;
        }
    }

    /**
     * Search index for the given element in the string array with empty arr.
     * <p>
     * See Lakman p. 422
     */
    public int search(String[] arr, String str) {
        if (arr == null || str == null || str.isEmpty()) {
            return -1;
        }
        return binarySearchStr(arr, str, 0, arr.length - 1);
    }

    private int binarySearchStr(String[] arr, String str, int low, int high) {
        int mid = low + (high - low) / 2;

        //find non-empty string
        if (arr[mid].isEmpty()) {
            int left = mid - 1;
            int right = mid + 1;
            while (true) {
                if (left < low && right > high) return -1;

                if (!arr[left].isEmpty() && left >= low) {
                    mid = left;
                    break;
                } else if (!arr[right].isEmpty() && right <= high) {
                    mid = right;
                    break;
                }
                left--;
                right++;
            }

        }

        if (str.equals(arr[mid])) return mid;

        if (arr[mid].compareTo(str) < 0) {
            return binarySearchStr(arr, str, low, mid - 1);
        } else {
            return binarySearchStr(arr, str, mid + 1, high);
        }
    }

    /**
     * Unit tests.
     */
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 8, 5, 6, 3, 9, 1};
        int[] A = new int[]{1, 2, 3, 5, 6, 8, 9, 0, 0, 0};
        int[] B = new int[]{2, 3, 8};
        final Sorting sort = new Sorting();
        System.out.println(Arrays.toString(sort.mergeArrays(A, B)));

        String[] arrS = new String[]{"acre", "", "race", "lo", ""};
        System.out.println(sort.search(arrS, "lo"));
        System.out.println(Arrays.toString(sort.sortAnagram2(arrS)));
    }
}
