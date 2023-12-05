package structures.trees.lakman;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Trees and Graphs.
 * <p>
 * See Lakman p. 250
 */
public class MinBST {

    /**
     * Create min bst
     * <p>
     * See Lakman p. 250
     */
    public TreeNode createMinimalBST(int array[]) {
        return createMinimalBST(array, 0, array.length - 1);
    }

    private TreeNode createMinimalBST(int arr[], int start, int end) {
        if (end < start) return null;

        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(arr[mid]);

        node.left = createMinimalBST(arr, start, mid - 1);
        node.right = createMinimalBST(arr, mid + 1, end);
        return node;
    }

    /**
     * Create linkedlist of nodes for each level, recursion ver, O(N) time, O(N) memory
     * <p>
     * See Lakman p. 251
     */
    public ArrayList<LinkedList<TreeNode>> createLevelLinkedListRec(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> lists = new ArrayList<>();
        createLevelLinkedList(root, lists, 0);
        return lists;
    }

    private void createLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level) {
        if (root == null) return;

        LinkedList<TreeNode> list;
        if (lists.size() == level) { // level is not in the lists
            list = new LinkedList<>();
            /* Уровни всегда обходятся по порядку.
            Следовательно, при первом посещении уровня i уровни с 0 по i-1 уже должны быть просмотрены,
            поэтому новый уровень можно безопасно добавить в конец. */
            lists.add(list);
        } else {
            list = lists.get(level);
        }
        list.add(root);
        createLevelLinkedList(root.left, lists, level + 1);
        createLevelLinkedList(root.right, lists, level + 1);
    }

    public ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> result = new ArrayList<>();

        LinkedList<TreeNode> current = new LinkedList<>();
        if (root != null) {
            current.add(root);
        }

        while (current.size() > 0) {
            result.add(current); //add previous level
            LinkedList<TreeNode> parents = current; // move to the next level
            current = new LinkedList<>();
            for (TreeNode parent : parents) {
                /* visiting child nodes */
                if (parent.left != null) {
                    current.add(parent.left);
                }
                if (parent.right != null) {
                    current.add(parent.right);
                }
            }
        }
        return result;
    }

    /**
     * Checks that a binary tree is balanced, O(NlogN) time
     * <p>
     * See Lakman p. 253
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int heightDiff = getHeight(root.left) - getHeight(root.right);

        if (Math.abs(heightDiff) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * Checks that a binary tree is balanced, O(N) time, O(logN) memory
     * <p>
     * See Lakman p. 253
     */
    private int getHeight(TreeNode root) {
        if (root == null) return -1;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    public boolean isBalancedOptimization(TreeNode root) {
        return checkHeight(root) != Integer.MIN_VALUE;
    }

    public int checkHeight(TreeNode root) {
        if (root == null) return -1;

        int leftHeight = checkHeight(root.left);
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // Error

        int rightHeight = checkHeight(root.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // Error

        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            return Integer.MIN_VALUE; // Find error -> return
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
