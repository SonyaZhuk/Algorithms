package algo.lakman.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * The "Great kombinator" game.
 * <p>
 * See Lakman p. 522
 */
public class GreatKombinator {
    private int hits;
    private int pseudoHits;

    public GreatKombinator() {
        this.hits = 0;
        this.pseudoHits = 0;
    }
    public String estimate(String guess, String solution) {
        if (guess.length() != solution.length())
            throw new IllegalArgumentException();

        final Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < guess.length(); i++) {
            char ch = solution.charAt(i);
            if (guess.charAt(i) == ch) {
                hits++;
            } else {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            char ch = guess.charAt(i);
            if (map.containsKey(ch) && map.get(ch) > 0 && solution.charAt(i) != ch) {
                pseudoHits++;
                map.put(ch, map.get(ch) - 1);
            }
        }

        return "(" + this.hits + ", " + this.pseudoHits + ")";
    }
}
