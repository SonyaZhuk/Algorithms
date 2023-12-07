package structures.trees.lakman.randomnode;

import java.util.Random;

/**
 * Generate a random node. O(logN) in case of balanced tree
 * <p>
 * See Lakman p. 278
 */
public class TreeNode {
    private int data;
    public TreeNode left;
    public TreeNode right;
    private int size = 0;

    public TreeNode(int d) {
        data = d;
        size = 1;
    }

    public TreeNode getRandomNode() {
        int leftSize = left == null ? 0 : left.size();
        Random random = new Random();
        int index = random.nextInt(size);

        if (index == leftSize) {
            return this;
        } else if (index < leftSize) {
            return left.getRandomNode();
        }
        return right.getRandomNode();
    }

    public void insertInOrder(int d) {
        if (d <= data) {
            if (left == null) {
                left = new TreeNode(d);
            } else {
                left.insertInOrder(d);
            }
        } else {
            if (right == null) {
                right = new TreeNode(d);
            } else {
                right.insertInOrder(d);
            }
        }
        size++;
    }

    public int size() {
        return size;
    }

    public int data() {
        return data;
    }

    public TreeNode find(int d) {
        if (d == data) {
            return this;
        } else if (d <= data) {
            return left != null ? left.find(d) : null;
        }
        return right != null ? right.find(d) : null;
    }
}
