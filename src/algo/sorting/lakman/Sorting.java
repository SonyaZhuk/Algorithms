package algo.sorting.lakman;

import java.util.Arrays;

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
        while(indexB >= 0) {
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
     * Unit tests.
     */
    public static void main(String[] args) {
        int[] arr = new int[] {3,2,8,5,6,3,9,1};
        int[] A = new int[] {1,2,3,5,6,8,9,0,0,0};
        int[] B = new int[] {2,3,8};
        Sorting sort = new Sorting();
        System.out.println(Arrays.toString(sort.mergeArrays(A, B)));
    }
}
