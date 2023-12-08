package algo.lakman;

import java.util.HashMap;
import java.util.Map;

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
        for (String word: words) {
            final String s = word.trim().toLowerCase();
            dict.put(s, dict.getOrDefault(s, 1) + 1);
        }
        return dict;
    }

    public static void main(String[] args) {
        MediumTask task = new MediumTask();
        task.exchangeI(5, 8);
    }
}
