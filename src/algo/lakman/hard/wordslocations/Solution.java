package algo.lakman.hard.wordslocations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    /**
     * Finds all locations of small strings in a big. O(N) time complexity.
     * <p>
     * See Lakman p. 612
     */
    public Map<String, List<Integer>> searchAll(String big, String[] smalls) {
        Map<String, List<Integer>> res = new HashMap<>();
        for (String small : smalls) {
            ArrayList<Integer> locations = search(big, small);
            res.put(small, locations);
        }
        return res;
    }

    /* Поиск всех вхождений меньшей строки в большей строке. */
    private ArrayList<Integer> search(String big, String small) {
        ArrayList<Integer> locations = new ArrayList<>();

        for (int i = 0; i < big.length() - small.length() + 1; i++) {
            if (isSubstringAtLocation(big, small, i)) {
                locations.add(i);
            }
        }
        return locations;
    }

    /* Проверка нахождения small в строке big со смещением offset. */
    private boolean isSubstringAtLocation(String big, String small, int offset) {
        for (int i = 0; i < small.length(); i++) {
            if (big.charAt(offset + i) != small.charAt(i))
                return false;
        }
        return true;
    }

    /**
     * Finds all locations of small strings in a big. Trie tree. O(N) time complexity.
     * <p>
     * See Lakman p. 612
     */
    public Map<String, List<Integer>> searchAllI(String big, String[] smalls) {
        Map<String, List<Integer>> res = new HashMap<>();
        Trie tree = createTrieFromString(big);
        for (String str : smalls) {
            List<Integer> locations = tree.search(str);
            subtractValue(locations, str.length());
            res.put(str, locations);
        }
        return res;
    }

    private Trie createTrieFromString(String s) {
        Trie trie = new Trie();
        for (int i = 0; i < s.length(); i++) {
            String suffix = s.substring(i);
            trie.insertString(suffix, 1);
        }
        return trie;
    }

    private void subtractValue(List<Integer> locations, int delta) {
        if (locations == null) return;
        for (int i = 0; i < locations.size(); i++) {
            locations.set(i, locations.get(i) - delta);
        }
    }


    /**
     * Finds all locations of small strings in a big. Trie tree. O(N) time complexity, faster than prev.
     * <p>
     * See Lakman p. 617
     */
    public Map<String, List<Integer>> searchAllII(String big, String[] smalls) {
        Map<String, List<Integer>> res = new HashMap<>();
        int maxLen = big.length();
        TrieNode root = createTreeFromStrings(smalls, maxLen).getRoot();

        for (int i = 0; i < big.length(); i++) {
            List<String> strings = findStringsAtLoc(root, big, i);
            //insertIntoHashMap(strings, res, i);
        }

        return res;
    }


    /* Вставка строк в нагруженное дерево (длина строки <= maxLen). */
    private Trie createTreeFromStrings(String[] smalls, int maxLen) {
        Trie tree = new Trie("");

        for (String s : smalls) {
            if (s.length() <= maxLen) {
                tree.insertString(s, 0);
            }
        }
        return tree;
    }


    /* Поиск в нагруженном дереве строк, начинающихся с индекса start. */
    private List<String> findStringsAtLoc(TrieNode root, String big, int start) {
        List<String> strings = new ArrayList<>();
        int index = start;
        while (index < big.length()) {
            root = root.getChild(big.charAt(index));
            if (root == null) break;
            if (root.terminates()) { // Полная строка, добавить в список
                strings.add(big.substring(start, index + 1));
            }
            index++;
        }
        return strings;
    }
}
