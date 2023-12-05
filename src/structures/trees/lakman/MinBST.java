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
}
