package algo.lakman.hard.popularname;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Counts the most freq names. O(N*LogN) time.
 * <p>
 * See Lakman p. 573
 */
public class PopularNames {

    public Map<String, Integer> mostPopular(HashMap<String, Integer> names, String[][] synonyms) {
        HashMap<String, NameSet> groups = constructGroups(names);

        /* Слияние классов эквивалентности. */
        mergeClasses(groups, synonyms);

        return convertToMap(groups);
    }

    private HashMap<String, NameSet> constructGroups(HashMap<String, Integer> names) {
        final HashMap<String, NameSet> groups = new HashMap<>();
        for (Map.Entry<String, Integer> entry : names.entrySet()) {
            String name = entry.getKey();
            NameSet group = new NameSet(entry.getKey(), entry.getValue());
            groups.put(name, group);
        }
        return groups;
    }

    private void mergeClasses(HashMap<String, NameSet> groups, String[][] synonyms) {
        for (String[] entry : synonyms) {
            String name1 = entry[0];
            String name2 = entry[1];

            NameSet set1 = groups.get(name1);
            NameSet set2 = groups.get(name2);

            if (set1 != set2) {
                /* Меньшее множество всегда включается в большее. */
                NameSet smaller = set2.size() < set1.size() ? set2 : set1;
                NameSet bigger = set2.size() < set1.size() ? set1 : set2;

                Set<String> otherNames = smaller.getNames();
                int frequency = smaller.getFrequency();
                bigger.copyNamesWithFrequency(otherNames, frequency);

                /* Обновление отображений */
                for (String name : otherNames) {
                    groups.put(name, bigger);
                }
            }
        }
    }


    private Map<String, Integer> convertToMap(HashMap<String, NameSet> groups) {
        Map<String, Integer> map = new HashMap<>();
        for (NameSet group : groups.values()) {
            map.put(group.getRootName(), group.getFrequency());
        }
        return map;
    }
}
