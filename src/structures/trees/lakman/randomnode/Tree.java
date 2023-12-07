package structures.trees.lakman.randomnode;

import java.util.Random;

public class Tree {
    private TreeNodeI root = null;

    public int size() {
        return root == null ? 0 : root.size();
    }

    public TreeNodeI getRandomNode() {
        if (root == null) return null;

        Random random = new Random();
        int i = random.nextInt(size());
        return root.getIthNode(i);
    }

    public void insertInOrder(int value) {
        if (root == null) {
            root = new TreeNodeI(value);
        } else {
            root.insertInOrder(value);
        }
    }
}
