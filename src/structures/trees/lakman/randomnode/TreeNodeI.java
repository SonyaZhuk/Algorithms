package structures.trees.lakman.randomnode;

/**
 * Generate a random node with random optimization.
 * O(logN) time in case of balanced tree, O(D) time, D - max depth tree.
 * <p>
 * See Lakman p. 281
 */
public class TreeNodeI {
    private int data;
    public TreeNodeI left;
    public TreeNodeI right;
    private int size = 0;

    public TreeNodeI(int d) {
        data = d;
        size = 1;
    }

    public TreeNodeI getIthNode(int i) {
        int leftSize = left == null ? 0 : left.size();
        if (i == leftSize) {
            return this;
        } else if (i < leftSize) {
            return left.getIthNode(i);
        }

        /* Пропускаются leftSize + 1 узлов, вычитаем их. */
        return right.getIthNode(i - (leftSize + 1));
    }

    public void insertInOrder(int d) {
        if (d <= data) {
            if (left == null) {
                left = new TreeNodeI(d);
            } else {
                left.insertInOrder(d);
            }
        } else {
            if (right == null) {
                right = new TreeNodeI(d);
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
}
