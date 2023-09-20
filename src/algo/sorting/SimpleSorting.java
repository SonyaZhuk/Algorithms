package algo.sorting;

import java.io.*;
import java.util.Arrays;

public class SimpleSorting {

    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("C:\\test.txt");
            int i;
            while((i = reader.read())!=-1)
                System.out.print((char)i);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int[] arr = new int[] {3,2,8,5,6,3,9,1};
        //System.out.println(Arrays.toString(mergeSort(arr)));
        //selectionSort(arr);
        //quickSort(arr, 0, arr.length -1);
        //System.out.println(Arrays.toString(arr));
    }

    private static int[] bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    //N + N-1 + N-2 +... + 2+1 ~ N^2/2(сравнений) + N(перестановок) --> O(N^2)
    //O(N^2)  в лучшем, среднем и худшем случае, не устойчива, эффективна на маленьких массивах <= 10
    private static void selectionSort(int[] arr) {
        int min, temp;
        for(int i = 0; i< arr.length -1; i++) {
            min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) min = j;
                temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }

    // N^2 cравнений и N^2 перестановок (в лучшем случае - N-1 сравнений, 0 перестановок)
    //зависит от входных данных, худший случай - reverse
    //O(N^2) - в среднем и худшем случае, O(N) - в лучшем случае
    //устойчива
    private static void insertSort(int[] arr) {
        for(int i = 0; i< arr.length; i++) {
            int c = arr[i];
            int p = i - 1;
            while(p >= 0 && arr[p] > c) {
                arr[p + 1] = arr[p];
                arr[p] = c;
                p--;
            }
        }
    }

//    private static void shell(int[] arr) {
//        int i, j, k, h, m = 0, b = arr.length;
//        int[] d = {1, 4, 10, 23, 57};
//        while(--m >= 0) {
//            k = d[m];
//            for(i = k; i < b; i++) {
//                j = 1;
//                h = arr[i];
//                while((j >= k) && (arr[j-k] > h)) {
//                    arr[j] = arr[j-k];
//                    j-=k;
//                }
//                arr[j] = h;
//            }
//        }
//    }

    //гарантированное время сортировки любого массива из N за время O(N*logN)
    // недостаток - требование доп памяти объема ~N
    // устойчива, асимптотически оптимальный алгоритм на основе операций сравнения
    private static int[] mergeSort(int[] arr) {
        if(arr.length < 2) return arr;
        int m = arr.length/2;
        int[] arr1 = Arrays.copyOfRange(arr, 0, m);
        int[] arr2 = Arrays.copyOfRange(arr, m, arr.length);
        return merge(mergeSort(arr1), mergeSort(arr2));
    }
    private static int[] merge(int[] arr1, int arr2[]) {
        int n = arr1.length + arr2.length;
        int[]arr = new int[n];
        int i1 = 0;
        int i2 = 0;
        for(int i = 0; i < n; i++) {
            if(i1 == arr1.length) {
                arr[i] = arr2[i2++];
            } else if (i2 == arr2.length) {
                arr[i] = arr1[i1++];
            } else {
                if (arr1[i1] < arr2[i2]) {
                    arr[i] = arr1[i1++];
                } else {
                    arr[i] = arr2[i2++];
                }
            }
        }
        return arr;
    }


    // O(N*logN) в среднем (сравений), в худшем случае - O(N^2)
    // использует только небольшой вспомогательный стек
    // не устойчива
    // для маленьких массивов работает медленнее, чем сортировка вставками
    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;//завершить выполнение, если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = array[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }

            if (i <= j) {//меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }
}
