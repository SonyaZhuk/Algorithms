package algo.lakman.hard.smallestk;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class Solution {

    /**
     * Finds k smallest elements in array. O(N* logN) time complexity.
     * <p>
     * See Lakman p. 601
     */
    public int[] smallestK(int[] arr, int k) {
        if (arr == null || arr.length < k || k <= 0)
            throw new IllegalArgumentException();

        Arrays.sort(arr);

        int[] res = new int[k];
        //System.arraycopy(arr, 0, res, 0, k);
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    /**
     * Finds k smallest elements in array. Min-heap -> Max heap. O(N* logM) time complexity.
     * <p>
     * See Lakman p. 601
     */
    public int[] smallestKI(int[] arr, int k) {
        if (k <= 0 || k > arr.length)
            throw new IllegalArgumentException();

        PriorityQueue<Integer> heap = getKMaxHeap(arr, k);
        return heapToIntArray(heap);
    }

    private PriorityQueue<Integer> getKMaxHeap(int[] array, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, (a, b) -> b - a);
        for (int a : array) {
            if (heap.size() < k) { // Если осталось место
                heap.add(a);
            } else if (a < heap.peek()) { // Куча заполнена, элемент больше
                heap.poll(); // Удалить корень
                heap.add(a); // Вставить новый элемент
            }
        }
        return heap;
    }

    private int[] heapToIntArray(PriorityQueue<Integer> heap) {
        int[] array = new int[heap.size()];
        while (!heap.isEmpty()) {
            array[heap.size() - 1] = heap.poll();
        }
        return array;
    }


    /**
     * Finds k smallest elements in array. Алгоритм ранжированного выбора. O(N) time complexity.
     * <p>
     * See Lakman p. 603
     */
    public int[] smallestKII(int[] arr, int k) {
        if (k <= 0 || k > arr.length)
            throw new IllegalArgumentException();

        int threshold = rank(arr, k - 1);

        /* Копирование элементов, меньших опорного значения. */
        int[] smallest = new int[k];
        int count = 0;
        for (int a : arr) {
            if (a < threshold) {
                smallest[count] = a;
                count++;
            }
        }

        /* Если еще осталось место, оно предназначено для элементов, равных пороговому. Скопировать их. */
        while (count < k) {
            smallest[count] = threshold;
            count++;
        }
        return smallest;
    }

    /* Поиск значения с рангом k в массиве. */
    private int rank(int[] array, int k) {
        if (k >= array.length) {
            throw new IllegalArgumentException();
        }
        return rank(array, k, 0, array.length - 1);
    }

    /* Поиск значения с рангом k в подмассиве от start до епd. */
    private int rank(int[] array, int k, int start, int end) {

        /* Перегруппировка массива вокруг произвольного порогового значения. */
        int pivot = array[randomIntInRange(start, end)];
        PartitionResult partition = partition(array, start, end, pivot);
        int leftSize = partition.leftSize;
        int middleSize = partition.middleSize;


        /* Поиск в части массива. */
        if (k < leftSize) { // Ранг k в левой половине
            return rank(array, k, start, start + leftSize - 1);
        } else if (k < leftSize + middleSize) { // Ранг k в середине
            return pivot; // Середина состоит из пороговых значений
        }
        // Ранг k в правой половине
        return rank(array, k - leftSize - middleSize, start + leftSize + middleSize, end);
    }

    /* Перегруппировка результата на < pivot, = pivot -> большие pivot. */
    private PartitionResult partition(int[] array, int start, int end, int pivot) {
        int left = start; /* Остается у (правого) края левой части. */
        int right = end; /* Остается у (левого) края правой стороны. */
        int middle = start; /* Остается у (правого) края средней части. */

        while (middle <= right) {
            if (array[middle] < pivot) {
            /* Середина меньше pivot. Левая часть меньше либо равна pivot.
            В любом случае поменять их местами, после чего переместить middle и left на 1. */
                swap(array, middle, left);
                middle++;
                left++;
            } else if (array[middle] > pivot) {
            /* Середина больше pivot. Правая часть может содержать любое значение. Поменять их местами.
            Теперь мы знаем, что новая правая часть больше pivot. Переместить right на 1.*/
                swap(array, middle, right);
                right--;
            } else if (array[middle] == pivot) {
                /* Середина равна pivot. Переместить на 1. */
                middle++;
            }
        }
        /* Вернуть размеры left и middle. */
        return new PartitionResult(left - start, right - left + 1);
    }

    private int randomIntInRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max + 1 - min) + min;
    }

    private void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}
