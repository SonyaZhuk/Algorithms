package algo.lakman.hard;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * Shuffle 52 cards.
     * <p>
     * See Lakman p. 562
     */
    public int[] shuffleArrayRecursively(int[] cards, int i) {
        if (i == 0) return cards;
        shuffleArrayRecursively(cards, i - 1); // Перетасовать предыдущую часть
        int k = rand(0, i); // Выбрать случайный индекс
        /* Переставить местами элементы k и i */
        int temp = cards[k];
        cards[k] = cards[i];
        cards[i] = temp;
        return cards;
    }

    /* Случайное число в диапазоне от lower до higher включительно */
    private int rand(int lower, int higher) {
        return lower + (int) (Math.random() * (higher - lower + 1));
    }

    public void shuffleIteratively(int[] cards) {
        for (int i = 0; i < cards.length; i++) {
            int k = rand(0, i);
            int temp = cards[k];
            cards[k] = cards[i];
            cards[i] = temp;
        }
    }

    /**
     * Random subset of m elements.
     * <p>
     * See Lakman p. 563
     */
    public int[] pickMRecursively(int[] arr, int m, int i) {
        if (i + 1 == m) {
            int[] subset = new int[m + 1];
            System.arraycopy(arr, 0, subset, 0, m);
            return subset;
        } else if (i + 1 > m) {
            int[] subset = pickMRecursively(arr, m, i - 1);
            int k = rand(0, i);
            if (k < m) {
                subset[k] = arr[i];
            }
            return subset;
        }
        return null;
    }

    public int[] pickMIteratively(int[] arr, int m) {
        int[] subset = new int[m];

        for (int i = 0; 1 < m; i++) {
            subset[i] = arr[i];
        }

        for (int i = m; i < arr.length; i++) {
            int k = rand(0, i);
            if (k < m) {
                subset[k] = arr[i];
            }
        }
        return subset;
    }

    /**
     * Finds a sub array where count(digits) == count(letters). O(N) time.
     * <p>
     * See Lakman p. 567
     */
    public char[] findLongestSubArray(char[] array) {
        /* Вычисление разностей между количествами букв и цифр.*/
        int[] deltas = computeDeltaArray(array);

        /* Поиск пар с равными значениями на наибольшем расстоянии.*/
        int[] match = findLongestMatch(deltas);

        /* Возвращение подмассива.*/
        return extractSubArray(array, match[0] + 1, match[1]);
    }

    /* Вычисление разности между количеством букв и цифр от начала массива до каждого индекса.*/
    private int[] computeDeltaArray(char[] array) {
        int[] deltas = new int[array.length];
        int delta = 0;
        for (int i = 0; i < array.length; i++) {
            if (Character.isLetter(array[i])) {
                delta++;
            } else if (Character.isDigit(array[i])) {
                delta--;
            }
            deltas[i] = delta;
        }
        return deltas;
    }

    /* Поиск в массиве разностей пары одинаковых значений с наибольшей разностью индексов.*/
    private int[] findLongestMatch(int[] deltas) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int[] max = new int[2];
        for (int i = 0; i < deltas.length; i++) {
            if (!map.containsKey(deltas[i])) {
                map.put(deltas[i], i);
            } else {
                int match = map.get(deltas[i]);
                int distance = i - match;
                int longest = max[1] - max[0];
                if (distance > longest) {
                    max[1] = i;
                    max[0] = match;
                }
            }
        }
        return max;
    }

    /* Вернуть подмассив из элементов от start до end (включительно).*/
    private char[] extractSubArray(char[] array, int start, int end) {
        char[] subArray = new char[end - start + 1];
        for (int i = start; i <= end; i++) {
            subArray[i - start] = array[i];
        }
        return subArray;
    }

    /**
     * Finds the count of '2' in seq of numbers.
     * <p>
     * See Lakman p. 570
     */
    public int numberOf2sinRange(int n) {
        int count = 0;
        for (int i = 2; i <= n; i++) {
            count += numberOf2S(i);
        }
        return count;
    }

    private int numberOf2S(int n) {
        int count = 0;
        while (n > 0) {
            if (n % 10 == 2) count++;
            n = n / 10;
        }
        return count;
    }

    /**
     * Finds the count of '2' in seq of numbers.
     * <p>
     * See Lakman p. 572
     */
    public int count2sInRange(int number) {
        int count = 0;
        int len = String.valueOf(number).length();
        for (int digit = 0; digit < len; digit++) {
            count += count2sInRangeAtDigit(number, digit);
        }
        return count;
    }

    private int count2sInRangeAtDigit(int number, int d) {
        int power0f10 = (int) Math.pow(10, d);
        int nextPowerOf10 = power0f10 * 10;
        int right = number % power0f10;

        int roundDown = number - number % nextPowerOf10;
        int roundUp = roundDown + nextPowerOf10;

        int digit = (number / power0f10) % 10;
        if (digit < 2) {
            return roundDown / 10;
        } else if (digit == 2) {
            return roundDown / 10 + right + 1;
        } else {
            return roundUp / 10;
        }
    }
}
