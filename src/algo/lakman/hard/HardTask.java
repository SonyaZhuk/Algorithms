package algo.lakman.hard;

import java.math.BigInteger;
import java.util.*;

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

    /**
     * Finds k-th number, where 3^a * 5^b * 7^c. Brute force, О(k ^ 3 * log k) time complexity.
     * <p>
     * See Lakman p. 582
     */
    public int getKthMagicNumber(int k) {
        List<Integer> seq = allPossibleKFactors(k);
        Collections.sort(seq);
        return seq.get(k);
    }

    private List<Integer> allPossibleKFactors(int k) {
        List<Integer> values = new ArrayList<>();
        for (int a = 0; a <= k; a++) {
            int powA = (int) Math.pow(a, 3);
            for (int b = 0; b <= k; b++) {
                int powB = (int) Math.pow(b, 5);
                for (int c = 0; c <= k; c++) {
                    int powC = (int) Math.pow(c, 7);
                    int value = powA * powB * powC;
                    if (value < 0 || powA == Integer.MAX_VALUE ||
                            powB == Integer.MAX_VALUE || powC == Integer.MAX_VALUE) {
                        value = Integer.MAX_VALUE;
                    }
                    values.add(value);
                }
            }
        }
        return values;
    }

    /**
     * Finds k-th number, where 3^a * 5^b * 7^c.
     * <p>
     * See Lakman p. 584
     */
    public int getKthMagicNumberI(int k) {
        if (k < 0) return 0;

        int val = 1;
        final Queue<Integer> queue = new LinkedList<>();
        addProducts(queue, 1);
        for (int i = 0; i < k; i++) {
            val = removeMin(queue);
            addProducts(queue, val);
        }
        return val;
    }

    private void addProducts(Queue<Integer> q, int v) {
        q.add(v * 3);
        q.add(v * 5);
        q.add(v * 7);
    }

    private int removeMin(Queue<Integer> queue) {
        int min = queue.peek();
        for (Integer v : queue) {
            if (min > v) {
                min = v;
            }
        }
        while (queue.contains(min)) {
            queue.remove(min);
        }
        return min;
    }

    /**
     * Finds k-th number, where 3^a * 5^b * 7^c.
     * <p>
     * See Lakman p. 584
     */
    public int getKthMagicNumberII(int k) {
        if (k < 0) return 0;

        int value = 0;

        final Queue<Integer> queue3 = new LinkedList<>();
        final Queue<Integer> queue5 = new LinkedList<>();
        final Queue<Integer> queue7 = new LinkedList<>();
        queue3.add(1);

        /* итерации с 0-й по k-ю. */
        for (int i = 0; i <= k; i++) {
            int v3 = queue3.size() > 0 ? queue3.peek() : Integer.MAX_VALUE;
            int v5 = queue5.size() > 0 ? queue5.peek() : Integer.MAX_VALUE;
            int v7 = queue7.size() > 0 ? queue7.peek() : Integer.MAX_VALUE;

            if (value == v3) { // in queue 3, 5, 7
                queue3.remove();
                queue3.add(3 * value);
                queue5.add(5 * value);
            } else if (value == v5) { // in queue 5, 7
                queue5.remove();
                queue5.add(5 * value);
            } else if (value == v7) { // in queue 7
                queue7.remove();
            }
            queue7.add(7 * value); // Всегда в Q7
        }
        return value;
    }


    /**
     * Finds a majority element (count(element) > arr.size/2). O(N^2) time complexity, O(1) memory.
     * <p>
     * See Lakman p. 587
     */
    public int findMajorityElement(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (isMajority(arr, arr[i])) {
                return arr[i];
            }
        }
        return -1;
    }

    private boolean isMajority(int[] arr, int el) {
        int count = 0;
        for (int e : arr) {
            if (e == el) count++;
        }
        return count > arr.length / 2;
    }


    /**
     * Finds a majority element (count(element) > arr.size/2). O(N) time complexity, O(1) memory.
     * <p>
     * See Lakman p. 590
     */
    public int findMajorityElementI(int[] arr) {
        int candidate = getCandidate(arr);
        return isMajority(arr, candidate) ? candidate : -1;
    }

    private int getCandidate(int[] arr) {
        int majority = 0;
        int count = 0;
        for (int el : arr) {
            if (count == 0) {
                majority = el;
            }
            if (el == majority) {
                count++;
            } else {
                count--;
            }
        }
        return majority;
    }

    /**
     * Finds the longest word that consist in list of words.
     * <p>
     * See Lakman p. 607
     */
    public String findLongestWord(String[] arr) {
        final Map<String, Boolean> map = new HashMap<>();
        for (String str : arr) {
            map.put(str, true);
        }
        Arrays.sort(arr, new Comparator() {
            public int compare(Object o1, Object o2) {
                String s1 = (String) o1;
                String s2 = (String) o2;
                if (s1.length() < s2.length()) return 1;
                else if (s1.length() > s2.length()) return -1;
                return 0;
            }
        });

        for (String str : arr) {
            if (canBuildWord(str, true, map)) {
                return str;
            }
        }
        return "";
    }

    private boolean canBuildWord(String str, boolean isOriginalWord, Map<String, Boolean> map) {
        if (map.containsKey(str) && !isOriginalWord)
            return map.get(str);

        for (int i = 1; i < str.length(); i++) {
            String left = str.substring(0, i);
            String right = str.substring(i);
            if (map.containsKey(left) && map.get(left) && canBuildWord(right, false, map)) {
                return true;
            }
        }
        map.put(str, false);
        return false;
    }


    /**
     * Builds optimal work schedule for a masseur. Recursion with memo. O(N) time complexity, O(N) memory.
     * <p>
     * See Lakman p. 610
     */
    public int maxMinutes(int[] massages) {
        int[] memo = new int[massages.length];
        return maxMinutes(massages, 0, memo);
    }

    private int maxMinutes(int[] massages, int index, int[] memo) {
        if (index >= massages.length)
            return 0;

        if (memo[index] == 0) {
            int bestWith = massages[index] + maxMinutes(massages, index + 2, memo);
            int bestWithout = maxMinutes(massages, index + 1, memo);
            memo[index] = Math.max(bestWith, bestWithout);
        }

        return memo[index];
    }

    /**
     * Builds optimal work schedule for a masseur. Iterative. O(N) time complexity, O(1) memory.
     * <p>
     * See Lakman p. 612
     */
    public int maxMinutesI(int[] massages) {
        int oneAway = 0;
        int twoAway = 0;

        for (int i = massages.length - 1; i <= 0; i--) {
            int bestWith = massages[i] + twoAway;
            int bestWithout = oneAway;
            int current = Math.max(bestWith, bestWithout);
            twoAway = oneAway;
            oneAway = current;
        }

        return oneAway;
    }


    /**
     * Finds indexes of smallest sub-array in a big array that contains all small array numbers. Brute force.
     * O(N^2*M) time complexity, N - bigArray.length, M  - smallArray.length.
     * <p>
     * See Lakman p. 618
     */
    public int[] shortestSuperSequence(int[] bigArray, int[] smallArray) {
        int bestStart = -1;
        int bestEnd = -1;
        for (int i = 0; i < bigArray.length; i++) {
            int end = findClosure(bigArray, smallArray, i);
            if (end == -1) break;
            if (bestStart == -1 || end - i < bestEnd - bestStart) {
                bestStart = i;
                bestEnd = end;
            }
        }
        return new int[]{bestStart, bestEnd};
    }


    /* Поиск замыкания (то есть элемента, завершающего nодмассив со всеми
       элементами smallArray) для заданного индекса. Значение равно
       максимуму среди следующих позиций каждого элемента из smallArray. */
    private int findClosure(int[] bigArray, int[] smallArray, int index) {
        int max = -1;
        for (int i = 0; i < smallArray.length; i++) {
            int next = findNextInstance(bigArray, smallArray[i], index);
            if (next == -1) {
                return -1;
            }
            max = Math.max(next, max);
        }
        return max;
    }

    /* Поиск следующего вхождения element, начиная с index */
    private int findNextInstance(int[] array, int element, int index) {
        for (int i = index; 1 < array.length; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds indexes of smallest sub-array in a big array that contains all small array numbers.
     * O(N*M) time complexity, N - bigArray.length, M  - smallArray.length. O(N) - memory.
     * <p>
     * See Lakman p. 622
     */
    public int[] shortestSuperSequenceI(int[] bigArray, int[] smallArray) {
        int[] closures = getClosures(bigArray, smallArray);
        return getShortestClosure(closures);
    }

    private int[] getClosures(int[] big, int[] small) {
        int[] closure = new int[big.length];
        for (int i = 0; i < small.length; i++) {
            sweepForClosure(big, closure, small[i]);
        }
        return closure;
    }

    /* Обратный перебор с обновлением списка замыканий следующим вхождением,
       если оно находится дальше текущего. */
    private void sweepForClosure(int[] big, int[] closures, int value) {
        int next = -1;
        for (int i = big.length - 1; i >= 0; i--) {
            if (big[i] == value) {
                next = i;
            }
            if ((next == -1 || closures[i] < next) && (closures[i] != -1)) {
                closures[i] = next;
            }
        }
    }


    /* Получение кратчайшего замыкания. */
    private int[] getShortestClosure(int[] closures) {
        int[] shortest = new int[]{0, closures[0]};
        for (int i = 1; i < closures.length; i++) {
            if (closures[i] == -1) break;

            int[] range = new int[]{i, closures[i]};

            int lenS = shortest[1] - shortest[0] + 1;
            int lenR = range[1] - range[0] + 1;
            if (!(lenS < lenR)) {
                shortest = range;
            }
        }
        return shortest;
    }


    /**
     * Finds indexes of smallest sub-array in a big array that contains all small array numbers. Min-heap.
     * O(N*logM) time complexity, N - bigArray.length, M  - smallArray.length. O(N) - memory.
     * <p>
     * See Lakman p. 624
     */
    public int[] shortestSuperSequenceII(int[] bigArray, int[] smallArray) {
        List<Queue<Integer>> locations = getLocationsForElements(bigArray, smallArray);
        if (locations == null) return null;
        return getShortestClosure(locations);
    }


    /* Получение списка очередей (связанных списков) с индексами, с которыми
       каждый элемент из smallArray хранится в bigArray. */
    private List<Queue<Integer>> getLocationsForElements(int[] big, int[] small) {
        final List<Queue<Integer>> allLocations = new ArrayList<>();
        for (int el : small) {
            final Queue<Integer> locations = getLocations(big, el);
            if (locations.size() == 0) return null;
            allLocations.add(locations);
        }
        return allLocations;
    }


    /* Получение очереди (связного списка) индексов, под которыми элемент встречается в большом массиве. */
    private Queue<Integer> getLocations(int[] big, int small) {
        final Queue<Integer> locations = new LinkedList<>();
        for (int i = 0; i < big.length; i++) {
            if (big[i] == small) {
                locations.add(i);
            }
        }
        return locations;
    }

    private int[] getShortestClosure(List<Queue<Integer>> lists) {

//        PriorityQueue<HeapNode> minHeap = new PriorityQueue<HeapNode>();
//        int max = Integer.MIN_VALUE;
//
//        /* Вставка минимального элемента из каждого списка. */
//        for (int i = 0; i < lists.size(); i++) {
//            int head = lists.get(i).remove();
//            minHeap.add(new HeapNode(head, i));
//            max = Math.max(max, head);
//        }
//
//        int min = minHeap.peek().locationWithinList;
//        int bestRangeMin = min;
//        int bestRangeMax = max;
//
//
//        while (true) {
//            /* Удаление минимального узла. */
//            HeapNode n = minHeap.poll();
//
//            Queue<Integer> list = lists.get(n.listid);
//
//            /* Сравнение диапазона с лучшим диапазоном. */
//            min = n.locationWithinList;
//            if (max - min < bestRangeMax - bestRangeMin) {
//                bestRangeMax = max;
//                bestRangeMin = min;
//            }
//
//            /* Если элементов не осталось, то подпоследовательностей не осталось, и выполнение можно прервать. */
//            if (list.size() == 0) {
//                break;
//            }
//
//            /* Добавление нового начала списка в кучу. */
//            n.locationWithinList = list.remove();
//            minHeap.add(n);
//            max = Math.max(max, n.locationWithinList);
//
//        }
//        return new int[]{bestRangeMin, bestRangeMax};
        return new int[]{0, 0};
    }

    /**
     * Finds a missing value from 1...N. O(N) time complexity, O(1) memory.
     * <p>
     * See Lakman p. 626
     */
    public int missingOne(int[] array) {
        int sum = Arrays.stream(array).sum();
        int len = array.length + 1;
        return ((len * (len + 1)) / 2 - sum);
    }

    public int missingOneI(int[] array) {
        BigInteger arraySum = new BigInteger("0");
        for (int i = 0; i < array.length; i++) {
            BigInteger value = new BigInteger(array[i] + "");
            arraySum = arraySum.add(value);
        }

        int len = array.length + 1;
        BigInteger fullSum = new BigInteger(((len * (len + 1)) / 2 + "0"));
        return (fullSum.subtract(arraySum)).intValue();
    }

    /**
     * Finds a missing two values from 1...N. O(N) time complexity, O(1) memory.
     * <p>
     * See Lakman p. 628
     */
    public int[] missingTwoII(int[] array) {
        int maxValue = array.length + 2;
        int remSquare = squareSumToN(maxValue, 2);
        int remOne = maxValue * (maxValue + 1) / 2;

        for (int i = 0; i < array.length; i++) {
            remSquare -= array[i] * array[i];
            remOne -= array[i];
        }
        return solveQuadEquation(remSquare, remOne);
    }

    private int[] solveQuadEquation(int r1, int r2) {
        int a = 2;
        int b = -2 * r1;
        int c = r1 * r1 - r2;

        double part1 = -1 * b;
        double part2 = Math.sqrt(b * b - 4 * a * c);
        double part3 = 2 * a;

        int solutionX = (int) ((part1 + part2) / part3);
        int solutionY = r1 - solutionX;

        return new int[]{solutionX, solutionY};
    }

    private int squareSumToN(int n, int power) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += (int) Math.pow(i, power);
        }
        return sum;
    }

    /**
     * Finds a median for randomly generator of numbers. O(1) time complexity, update - O(logN) time complexity.
     * <p>
     * Max-heap, min-heap.
     * if maxHeap.size() > min .Неар. size() => median = maxHeap.top();
     * if maxHeap.size() == minHeap.size() => median = (maxHeap.top() + minHeap.top()) / 2.
     * <p>
     * See Lakman p. 630
     */

    private Comparator<Integer> maxHeapComparator, minHeapComparator;
    private PriorityQueue<Integer> maxHeap, minHeap;

    public void addNewNumber(int randomNumber) {
        if (maxHeap.size() == minHeap.size()) {
            if ((minHeap.peek() != null) &&
                    randomNumber > minHeap.peek()) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(randomNumber);
            } else {
                maxHeap.offer(randomNumber);
            }
        } else {
            if (randomNumber < maxHeap.peek()) {
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(randomNumber);
            } else {
                minHeap.offer(randomNumber);
            }
        }
    }

    private double getMedian() {
        if (maxHeap.isEmpty()) {
            return 0;
        }
        return (maxHeap.size() == minHeap.size()) ?
                ((double) minHeap.peek() + (double) maxHeap.peek()) / 2 : maxHeap.peek();
    }

    public static void main(String[] args) {
        HardTask task = new HardTask();
        int[] arr = {1, 2, 3, 5};
        var res = task.missingOne(arr);
        System.out.println(res);
    }
}
