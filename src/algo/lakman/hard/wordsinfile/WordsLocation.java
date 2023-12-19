package algo.lakman.hard.wordsinfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Finds the min location between two words in file.
 * <p>
 * See Lakman p. 591
 */
public class WordsLocation {

    public LocationPair findClosest(String[] words, String word1, String word2) {
        final LocationPair best = new LocationPair(-1, -1);
        final LocationPair current = new LocationPair(-1, -1);
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            if (word.equals(word1)) {
                current.setLoc1(i);
                best.updateWithMin(current);
            } else if (word.equals(word2)) {
                current.setLoc2(i);
                best.updateWithMin(current);
            }
        }
        return best;
    }

    //using cache map
    public LocationPair findClosestI(String[] words, String word1, String word2) {
        final Map<String, List<Integer>> locations = getWordLocations(words);

        final List<Integer> locations1 = locations.get(word1);
        final List<Integer> locations2 = locations.get(word2);

        return findMinDistancePair(locations1, locations2);
    }

    //O(A + B), A - count(word1), B - count(word2).
    private LocationPair findMinDistancePair(List<Integer> arr1, List<Integer> arr2) {
        if (arr1 == null || arr2 == null || arr1.size() == 0 || arr2.size() == 0)
            return null;

        int index1 = 0;
        int index2 = 0;

        final LocationPair best = new LocationPair(arr1.get(0), arr2.get(0));
        final LocationPair current = new LocationPair(arr1.get(0), arr2.get(0));

        while (index1 < arr1.size() && index2 < arr2.size()) {
            current.setLocations(arr1.get(index1), arr2.get(index2));
            best.updateWithMin(current); // Если расстояние меньше, обновить
            if (current.getLoc1() < current.getLoc2()) {
                index1++;
            } else {
                index2++;
            }
        }
        return best;
    }

    //O(N) time
    private Map<String, List<Integer>> getWordLocations(String[] words) {
        final Map<String, List<Integer>> locations = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            final List<Integer> currList = (locations.containsKey(words[i])) ?
                    locations.get(words[i]) : new ArrayList<>();
            currList.add(i);
            locations.put(words[i], currList);
        }
        return locations;
    }
}
