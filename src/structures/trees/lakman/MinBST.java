package structures.trees.lakman;

/**
 * Trees and Graphs.
 * <p>
 * See Lakman p. 250
 */
public class MinBST {

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
}
