package structures.trees.lakman;

public class Trie {
    public static class TrieNode {
        TrieNode[] children = new TrieNode[128];
        boolean leaf;
    }

    public void insertString(TrieNode root, String s) {
        TrieNode v = root;
        for (char ch : s.toCharArray()) {
            TrieNode next = v.children[ch];
            if (next == null)
                v.children[ch] = next = new TrieNode();
            v = next;
        }
        v.leaf = true;
    }

    public void printSorted(TrieNode node, String s) {
        for (char ch = 0; ch < node.children.length; ch++) {
            TrieNode child = node.children[ch];
            if (child != null)
                printSorted(child, s + ch);
        }
        if (node.leaf) {
            System.out.println(s);
        }
    }

    /**
     * Unit tests the <tt>Trie tree</tt> data type.
     */
    public static void main(String[] args) {
        Trie trie = new Trie();
        TrieNode node = new TrieNode();
        trie.insertString(node, "hello");
        trie.insertString(node, "world");
        trie.insertString(node, "hi");
        trie.printSorted(node, "");
    }
}
