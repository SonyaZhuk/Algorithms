package algo.lakman.hard.wordslocations;

import java.util.List;

public class Trie {
    private TrieNode root = new TrieNode();

    public Trie(String s) {
        insertString(s, 0);
    }

    public Trie() {
    }

    public List<Integer> search(String s) {
        return root.search(s);
    }

    void insertString(String str, int location) {
        root.insertString(str, location);
    }

    public TrieNode getRoot() {
        return root;
    }
}
