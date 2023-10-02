package algo.search;

public class BinarySearch {

    // поиск в упорядоченном массиве
    // не устойчивый алгоритм
    // гарантированное lgN время поиска
    private static int binarySearch(int[] arr, int key) {
        int i = 0;
        int j = arr.length - 1;
        while(i <= j) {
            int m = i + (j - i)/2;
            if(key < arr[m]) {
                j = m - 1;
            } else {
                if (key > arr[m]) {
                    i = m + 1;
                } else return m;
            }
        }
        return -1;
    }
    private static int recursionBinarySearch(int key, int[] arr, int i, int j) {
        if (i > j) return -1;
        int m = i + (j - i)/2;
        if (key < arr[m]) {
            return recursionBinarySearch(key, arr, i, m-1);
        } else if (key > arr[m]) {
            return recursionBinarySearch(key, arr, m + 1, j);
        } else {
            return m;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {3,2,8,5,6,3,9,1};
    }
}