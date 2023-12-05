package structures.trees;

/**
 * Binary search tree.
 * <p>
 * Lakman p. 685
 */
public class TreeNode {
    private int data; //public
    private TreeNode left, right, parent; //public
    private int size = 0;

    public TreeNode(int d) {
        data = d;
        size = 1;
    }

    public void insertInOrder(int d) {
        if (d <= data) {
            if (left == null) {
                setLeftChild(new TreeNode(d));
            } else {
                left.insertInOrder(d);
            }
        } else {
            if (right == null) {
                setRightChild(new TreeNode(d));
            } else {
                right.insertInOrder(d);
            }
        }
        size++;
    }

    int size() {
        return size;
    }

    public TreeNode find(int d) {
        if (d == data) {
            return this;
        } else if (d <= data) {
            return left != null ? left.find(d) : null;
        }
        return right != null ? right.find(d) : null;
    }

    public void setLeftChild(TreeNode left) {
        this.left = left;
        if (left != null)
            left.parent = this;
    }

    public void setRightChild(TreeNode right) {
        this.right = right;
        if (right != null)
            right.parent = this;
    }
}



