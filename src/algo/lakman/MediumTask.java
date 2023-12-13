package algo.lakman;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * Medium level tasks.
 * <p>
 * See Lakman p. 489
 */
public class MediumTask {

    /**
     * Exchange values without a temp variable.
     * <p>
     * See Lakman p. 489
     */
    public void exchange(int x, int y) {
        x = x - y;
        y = x + y;
        x = y - x;
        System.out.println(x + " " + y);
    }

    /**
     * Exchange values without a temp variable. Advantages: can use it with another data types.
     * <p>
     * See Lakman p. 489
     */
    public void exchangeI(int x, int y) {
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println(x + " " + y);
    }

    /**
     * Count a word frequency.
     * <p>
     * See Lakman p. 490
     */
    public int getFrequency(String[] words, String word) {
        if (words == null || word == null) return -1;
        final Map<String, Integer> dict = fillDict(words);
        final String s = word.trim().toLowerCase();
        return dict.getOrDefault(s, 0);
    }

    private Map<String, Integer> fillDict(String[] words) {
        final Map<String, Integer> dict = new HashMap<>();
        for (String word : words) {
            final String s = word.trim().toLowerCase();
            dict.put(s, dict.getOrDefault(s, 1) + 1);
        }
        return dict;
    }

    /**
     * Finds intersection point for two line segments.
     * <p>
     * See Lakman p. 491
     */
    public Point intersection(Point startI, Point endI, Point startII, Point endII) {

        if (startI.getX() > endI.getX()) swap(startI, endI);
        if (startII.getX() > endII.getX()) swap(startII, endII);

        if (startI.getX() > startII.getX()) {
            swap(startI, startII);
            swap(endI, endII);
        }

        final Line line1 = new Line(startI, endI);
        final Line line2 = new Line(startII, endII);

        //in case parallel lines
        if (line1.getSlope() == line2.getSlope()
                && line1.getYintercept() == line2.getYintercept()) {
            return (isBetween(startI, startII, endII)) ? startII : null;
        }

        //intersection
        double x = (line2.getYintercept() - line1.getYintercept()) / (line1.getSlope() - line2.getSlope());
        double y = x * line1.getSlope() + line1.getYintercept();
        Point intersection = new Point(x, y);

        return (isBetween(startI, intersection, endI) &&
                isBetween(startII, intersection, endII)) ? intersection : null;
    }

    private boolean isBetween(Point start, Point middle, Point end) {
        return isBetween(start.getX(), middle.getX(), end.getX()) &&
                isBetween(start.getY(), middle.getY(), end.getY());
    }

    private boolean isBetween(double start, double middle, double end) {
        return (start > end) ?
                end <= middle && middle <= start :
                start <= middle && middle <= end;
    }

    private void swap(Point one, Point two) {
        double x = one.getX();
        double y = one.getY();
        one.setLocation(two.getX(), two.getY());
        two.setLocation(x, y);
    }


    /**
     * Finds ending zeros for n!.
     * <p>
     * See Lakman p. 500
     */
    public int countFactZeros(int num) {
        int count = 0;
        for (int i = 2; i <= num; i++) {
            count += factorsOfFive(i);
        }

        return count;
    }

    private int factorsOfFive(int i) {
        int count = 0;
        while (i % 5 == 0) {
            count++;
            i /= 5;

        }
        return count;
    }

    /**
     * Finds ending zeros for n!. Optimization.
     * <p>
     * See Lakman p. 500
     */
    public int countFactZerosI(int num) {
        int count = 0;
        if (num < 0)
            return -1;

        for (int i = 5; num / i > 0; i *= 5) {
            count += num / i;
        }

        return count;
    }

    /**
     * Finds Math.min(elem - elem > 0). Brute force, O(NM) time.
     * <p>
     * See Lakman p. 501
     */
    public int findSmallestDiff(int[] arr1, int[] arr2) {
        if (arr1.length == 0 || arr2.length == 0) return -1;

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                int c = Math.abs(arr1[i] - arr2[j]);
                if (c > 0) {
                    min = Math.min(min, c);
                }
            }
        }
        return min;
    }

    /**
     * Finds Math.min(elem - elem > 0). O(NlogN + MlogM) time.
     * <p>
     * See Lakman p. 503
     */
    public int findSmallestDiffI(int[] arr1, int[] arr2) {
        if (arr1.length == 0 || arr2.length == 0) return -1;

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int i = 0;
        int j = 0;
        int min = Integer.MAX_VALUE;

        while (i < arr1.length && j < arr2.length) {
            int c = Math.abs(arr1[i] - arr2[j]);
            if (c < min) {
                min = c;
            }

            if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return min;
    }

    /**
     * Finds Math.max(a, b) without any comparisons.
     * <p>
     * See Lakman p. 503
     */
    public int getMax(int a, int b) {
        int k = sign(a - b);
        int q = flip(k);
        return a * k + b * q;
    }

    /* 1, a > 0, 0, a < 0 */
    private int sign(int a) {
        return flip((a >> 31) & 0x1);
    }

    /* 1 -> 0, 0 -> 1 */
    private int flip(int bit) {
        return 1 ^ bit;
    }

    private String[] smalls = {"Zero", "Оnе", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Теn", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    private String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private String[] bigs = {"", "Thousand", "Million", "Billion"};
    private String hundred = "Hundred";
    private String negative = "Negative";

    /**
     * Converts number to text.
     * <p>
     * See Lakman p. 504
     */
    public String convert(int num) {
        if (num == 0) {
            return smalls[0];
        } else if (num < 0) {
            return negative + " " + convert(-1 * num);
        }

        final LinkedList<String> parts = new LinkedList<>();
        int chunkCount = 0;

        while (num > 0) {
            if (num % 1000 != 0) {
                String chunk = convertChunk(num % 1000) + " " + bigs[chunkCount];
                parts.addFirst(chunk);
            }
            num /= 1000;
            chunkCount++;
        }

        return listToString(parts);
    }

    private String convertChunk(int number) {
        LinkedList<String> parts = new LinkedList<>();
        // сотни
        if (number >= 100) {
            parts.addLast(smalls[number / 100]);
            parts.addLast(hundred);
            number %= 100;
        }
        //десятки
        if (number >= 10 && number <= 19) {
            parts.addLast(smalls[number]);
        } else if (number >= 20) {
            parts.addLast(tens[number / 10]);
            number %= 10;
        }

        // единицы
        if (number >= 1 && number <= 9) {
            parts.addLast(smalls[number]);
        }

        return listToString(parts);
    }

    private String listToString(LinkedList<String> parts) {
        StringBuilder sb = new StringBuilder();
        while (parts.size() > 1) {
            sb.append(parts.pop());
            sb.append(" ");
        }
        sb.append(parts.pop());
        return sb.toString();
    }

    //---------------------------------------------------//

    /**
     * Operations minus using only the plus operation. O(k)
     * <p>
     * See Lakman p. 506
     */
    public int minus(int a, int b) {
        return a + negate(b);
    }

    private int negate(int a) {
        int neg = 0;
        int newSign = (a < 0) ? 1 : -1;
        while (a != 0) {
            neg += newSign;
            a += newSign;
        }

        return neg;
    }

    /**
     * Operations multiply using only the plus operation.
     * <p>
     * See Lakman p. 507
     */
    public int multiply(int a, int b) {
        if (b > a) {
            multiply(b, a);
        }

        int res = -1;
        int index = b;
        if (b < 0) {
            index = negate(b);
        }
        for (int i = 0; i < index; i++) {
            res += a;
        }

        return ((b < 0 && a > 0) || (b < 0 && a < 0)) ?
                negate(res + 1) : res + 1;
    }

    /**
     * Operations divide using only the plus operation.
     * <p>
     * See Lakman p. 508
     */
    public int divide(int a, int b) {
        if (b == 0)
            throw new ArithmeticException();

        int absA = abs(a);
        int absB = abs(b);

        int product = 0;
        int x = 0;

        while (product + absB <= absA) {
            product += absB;
            x++;
        }

        return ((a < 0 && b < 0) || (a > 0 && b > 0)) ? x : negate(x);
    }

    private int abs(int a) {
        return (a < 0) ? negate(a) : a;
    }

    //------------------------------------------------------//

    /**
     * Finds the year when max alive. Brute force, O(N*M) time
     * <p>
     * See Lakman p. 508
     */
    public int maxAliveYear(Person[] people, int min, int max) {
        int maxAlive = 0;
        int maxAliveYear = min;
        for (int year = min; year <= max; year++) {
            int alive = 0;
            for (Person person : people) {
                if (person.getBirth() <= year && year <= person.getDeath()) {
                    alive++;
                }
            }
            if (alive > maxAlive) {
                maxAlive = alive;
                maxAliveYear = year;
            }
        }

        return maxAliveYear;
    }

    /**
     * Finds the year when max alive. Brute force, O(N + M) time
     * <p>
     * See Lakman p. 513
     */
    public int maxAliveYearI(Person[] people, int min, int max) {
        /* Построение массива прироста населения. */
        int[] populationDeltas = getPopulationDeltas(people, min, max);
        int maxAliveYear = getMaxAliveYear(populationDeltas);
        return maxAliveYear + min;
    }

    /* Добавление годов рождения и смерти в массив прироста. */
    private int[] getPopulationDeltas(Person[] people, int min, int max) {
        int[] populationDeltas = new int[max - min + 2];
        for (Person person : people) {
            int birth = person.getBirth() - min;
            populationDeltas[birth]++;
            int death = person.getDeath() - min;
            populationDeltas[death + 1]--;
        }
        return populationDeltas;
    }

    /* Вычисление текущих сумм и возвращение индекса с максимумом. */
    private int getMaxAliveYear(int[] deltas) {
        int maxAliveYear = 0;
        int maxAlive = 0;
        int currentlyAlive = 0;
        for (int year = 0; year < deltas.length; year++) {
            currentlyAlive += deltas[year];
            if (currentlyAlive > maxAlive) {
                maxAliveYear = year;
                maxAlive = currentlyAlive;
            }
        }

        return maxAliveYear;
    }

    //-------------------------------------------------------------------------//
    /**
     * Builds springboard with using two types of planks. O(2^k) times, k - count of planks - without memo.
     * O(k^2) - with memo.
     * <p>
     * See Lakman p. 514
     */
    private HashSet<Integer> lengths;
    //memoization
    private HashSet<String> visited = new HashSet<>();

    public HashSet<Integer> springboard(int k, int shorter, int longer) {
        lengths = new HashSet<>();
        getLengths(k, 0, shorter, longer);
        return lengths;
    }

    private void getLengths(int k, int total, int shorter, int longer) {
        if (k == 0) {
            lengths.add(total);
            return;
        }

        //memoization
        String key = k + " " + total;
        if (visited.contains(key)) {
            return;
        }

        getLengths(k - 1, total + shorter, shorter, longer);
        getLengths(k - 1, total + longer, shorter, longer);
        visited.add(key);
    }

    /**
     * Builds springboard with using two types of planks.
     * <p>
     * See Lakman p. 516
     */
    public HashSet<Integer> springboardI(int k, int shorter, int longer) {
        HashSet<Integer> lengths = new HashSet<>();
        for (int nShorter = 0; nShorter <= k; nShorter++) {
            int nlonger = k - nShorter;
            int length = nShorter * shorter + nlonger * longer;
            lengths.add(length);
        }
        return lengths;
    }

    /**
     * Finds indexes of sub-array that can be sorted for getting the full sorting array.
     * <p>
     * See Lakman p. 524
     */
    public String findUnsortedSequence(int[] array) {

        int endLeft = findEndOfLeftSubsequence(array);
        if (endLeft >= array.length - 1)
            return array[0] + " " + array[array.length - 1];

        int startRight = findStartOfRightSubsequence(array);

        // Определение минимума и максимума
        int maxIndex = endLeft; //Максимум левой стороны
        int minIndex = startRight; //Минимум правой стороны
        for (int i = endLeft + 1; i < startRight; i++) {
            if (array[i] < array[minIndex]) minIndex = i;
            if (array[i] > array[maxIndex]) maxIndex = i;
        }

        //Двигаться влево, пока не дойдем до array[miпIndex]
        int leftIndex = shrinkLeft(array, minIndex, endLeft);

        //Двигаться вправо, пока не дойдем до array[maxIndex]
        int rightIndex = shrinkRight(array, maxIndex, startRight);

        return leftIndex + " " + rightIndex;
    }

    private int findEndOfLeftSubsequence(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) return i - 1;
        }
        return array.length - 1;
    }

    private int findStartOfRightSubsequence(int[] array) {
        for (int i = array.length - 2; i >= 0; i--) {
            if (array[i] > array[i + 1]) return i + 1;
        }
        return 0;
    }

    private int shrinkLeft(int[] array, int minIndex, int start) {
        int comp = array[minIndex];
        for (int i = start - 1; i >= 0; i--) {
            if (array[i] <= comp) return i + 1;
        }
        return 0;
    }

    private int shrinkRight(int[] array, int maxIndex, int start) {
        int comp = array[maxIndex];
        for (int i = start; i < array.length; i++) {
            if (array[i] >= comp) return i - 1;
        }
        return array.length - 1;
    }

    /**
     * Finds sub-array with max sum.
     * <p>
     * See Lakman p. 526
     */
    public int getMaxSum(int[] arr) {
        int maxSum = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (maxSum < sum) {
                maxSum = sum;
            } else if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }


    /**
     * Checks if value matches on pattern. Brute force, O(N^4) time.
     * <p>
     * See Lakman p. 528
     */
    public boolean doesMatch(String pattern, String value) {
        if (pattern.length() == 0) return value.length() == 0;

        int size = value.length();
        for (int i = 0; i < size; i++) {
            final String main = value.substring(0, i);
            for (int j = i; j <= size; j++) {
                for (int k = j; k <= size; k++) {
                    final String alt = value.substring(j, k);
                    final String candidate = buildFromPattern(pattern, main, alt);
                    if (candidate.equals(value)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private String buildFromPattern(String pattern, String main, String alt) {
        final StringBuffer sb = new StringBuffer();
        char first = pattern.charAt(0);
        for (char с : pattern.toCharArray()) {
            if (с == first) {
                sb.append(main);
            } else {
                sb.append(alt);
            }
        }
        return sb.toString();
    }

    /**
     * Checks if value matches on pattern. O(N^2)
     * <p>
     * See Lakman p. 530
     */
    public boolean doesMatchI(String pattern, String value) {
        if (pattern.length() == 0) return value.length() == 0;

        char mainChar = pattern.charAt(0);
        char altChar = mainChar == 'а' ? 'b' : 'a';
        int size = value.length();

        int countOfMain = countOf(pattern, mainChar);
        int countOfAlt = pattern.length() - countOfMain;

        int firstAlt = pattern.indexOf(altChar);
        int maxMainSize = size / countOfMain;


        for (int mainSize = 0; mainSize <= maxMainSize; mainSize++) {
            int remainingLength = size - mainSize * countOfMain;
            if (countOfAlt == 0 || remainingLength % countOfAlt == 0) {
                int altIndex = firstAlt * mainSize;
                int altSize = countOfAlt == 0 ? 0 : remainingLength / countOfAlt;
                if (matches(pattern, value, mainSize, altSize, altIndex)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int countOf(String pattern, char c) {
        int count = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    /* Перебор по шаблону и значению. Для каждого символа в шаблоне
       проверяем, является ли он основным или альтернативным. Затем
       проверяем, соответствует ли следующая группа символов value
       исходному набору символов (основному или альтернативному). */
    private boolean matches(String pattern, String value, int mainSize, int altSize, int firstAlt) {
        int stringIndex = mainSize;
        for (int i = 1; i < pattern.length(); i++) {
            boolean bool = pattern.charAt(i) == pattern.charAt(0);
            int size = bool ? mainSize : altSize;
            int offset = bool ? 0 : firstAlt;
            if (!isEqual(value, offset, stringIndex, size)) {
                return false;
            }
            stringIndex += size;
        }
        return true;
    }

    /* Проверка равенства двух подстрок с заданными смещениями и размером size. */
    private boolean isEqual(String sl, int offset1, int offset2, int size) {
        for (int i = 0; i < size; i++) {
            if (sl.charAt(offset1 + i) != sl.charAt(offset2 + i)) {
                return false;
            }
        }
        return true;
    }

    //---------------------------------------------------------------------------//

    /**
     * Computes sizes for all lands. O(W*H) time.
     * <p>
     * See Lakman p. 531
     */
    public List<Integer> computePondSizes(int[][] land) {
        final List<Integer> pondSizes = new ArrayList<>();
        for (int row = 0; row < land.length; row++) {
            for (int col = 0; col < land[row].length; col++) {
                if (land[row][col] == 0) {
                    int size = computeSize(land, row, col);
                    pondSizes.add(size);
                }
            }
        }

        return pondSizes;
    }

    private int computeSize(int[][] land, int row, int col) {
        if (row < 0 || col < 0 || row >= land.length || col >= land[row].length || land[row][col] != 0) {
            return 0;
        }

        int size = 1;
        land[row][col] = -1;  //marks as visited

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                size += computeSize(land, row + dr, col + dc);
            }
        }

        return size;
    }

    //----------------------------------------------------------------------//

    /**
     * Finds the list of words for mobile digits. O(N) time.
     * <p>
     * See Lakman p. 534
     */
    private char[][] t9Letters = {null, null, {'a', 'b', 'с'}, {'d', 'e', 'f'},
            {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'}, {'w', 'х', 'у', 'z'}};

    public List<String> getValidT9Words(String numbers, String[] words) {
        return initializeDictionary(words).get(numbers);
    }

    /* Построение хеш-таблицы, связывающей последовательность цифр
       со всеми словами, имеющими такое представление. */
    private Map<String, List<String>> initializeDictionary(String[] words) {

        /* Построение хеш-таблицы, связывающей буквы с цифрами */
        final Map<Character, Character> letterToNumberMap = createLetterToNumberMap();

        /* Создание отображения "слово-> последовательность цифр". */
        final Map<String, List<String>> wordsToNumbers = new HashMap<>();
        for (String word : words) {
            final String numbers = convertToT9(word, letterToNumberMap);
            final List<String> curr = wordsToNumbers.containsKey(numbers) ?
                    wordsToNumbers.get(numbers) : new ArrayList<>();
            curr.add(word);
            wordsToNumbers.put(numbers, curr);
        }
        return wordsToNumbers;
    }

    /*Переход к отображению "буква-> цифра". */
    private Map<Character, Character> createLetterToNumberMap() {
        final Map<Character, Character> letterToNumberMap = new HashMap<>();
        for (int i = 0; i < t9Letters.length; i++) {
            char[] letters = t9Letters[i];
            if (letters != null) {
                for (char letter : letters) {
                    char с = Character.forDigit(i, 10);
                    letterToNumberMap.put(letter, с);
                }
            }
        }
        return letterToNumberMap;
    }

    private String convertToT9(String word, Map<Character, Character> letterToNumberMap) {
        final StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (letterToNumberMap.containsKey(c)) {
                char digit = letterToNumberMap.get(c);
                sb.append(digit);
            }
        }
        return sb.toString();
    }

    //----------------------------------------------------------------------//

    /**
     * Finds two elements that can be swept and sums of two arrays will be equal. O(N*M) time.
     * <p>
     * See Lakman p. 538
     */
    public int[] findSwapValues(int[] arr1, int[] arr2) {
        int target = getTarget(arr1, arr2);

        for (int i : arr1) {
            for (int j : arr2) {
                if (i - j == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private int getTarget(int[] arr1, int[] arr2) {
        int sum1 = Arrays.stream(arr1).sum();
        int sum2 = Arrays.stream(arr2).sum();
        if ((sum1 - sum2) % 2 != 0)
            throw new IllegalArgumentException();
        return (sum1 - sum2) / 2;
    }

    /**
     * Finds two elements that can be swept and sums of two arrays will be equal. O(N + M) time.
     * <p>
     * See Lakman p. 540
     */
    public int[] findSwapValuesI(int[] arr1, int[] arr2) {
        int target = getTarget(arr1, arr2);
        return findDifference(arr1, arr2, target);
    }

    private int[] findDifference(int[] arr1, int[] arr2, int target) {
        final Set<Integer> contents2 = Arrays.stream(arr2).boxed().collect(Collectors.toSet());
        for (int i : arr1) {
            int j = i - target;
            if (contents2.contains(j)) {
                return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Finds two elements that can be swept and sums of two arrays will be equal. For sorted arrays case.
     * O(N + M) time.
     * <p>
     * See Lakman p. 540
     */
    public int[] findSwapValuesII(int[] arr1, int[] arr2) {
        Integer target = getTarget(arr1, arr2);
        return findDifferenceI(arr1, arr2, target);
    }

    private int[] findDifferenceI(int[] arr1, int[] arr2, int target) {
        int i = 0;
        int j = 0;

        while (i < arr1.length && j < arr2.length) {
            int diff = arr1[i] - arr2[j];
            if (diff == target) {
                return new int[]{i, j};
            } else if (diff < target) {
                i++;
            } else {
                j++;
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * Generates Random(7) with using Random(5). Uniform distribution.
     * <p>
     * See Lakman p. 548
     */
    public int rand7() {
        final Random random = new Random();
        while (true) {
            //ровно один способ получения каждого числа из диапазона от О до 24.
            //Это гарантирует, что все значения равновероятны.
            int num = 5 * random.nextInt(5) + random.nextInt(5);
            if (num < 21) {
                return num % 7;
            }
        }
    }


        public static void main (String[]args){
            MediumTask task = new MediumTask();
            int[] arr1 = {1, 3, 15, 11, 2};
            int[] arr2 = {23, 127, 235, 19, 8};
            var res = task.getValidT9Words("8733", new String[]{"tree", "appl", "used", "nuts"});
            System.out.println();
        }
    }
