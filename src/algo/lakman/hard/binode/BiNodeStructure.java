package algo.lakman.hard.binode;

/**
 * Converts binary tree search to doubly linked list.
 * <p>
 * Псевдокод выглядит примерно так:
 * BiNode convert(BiNode node) {
 * BiNode left = convert(node.left);
 * BiNode right = convert(node.right);
 * mergeLists(left, node, right);
 * return left;
 * }
 * <p>
 * See Lakman p. 593
 */
public class BiNodeStructure {
    private static class BiNode {
        BiNode node1, node2;
        int data;
    }


    //O(N^2), N - count(nodes) time complexity.
    public BiNode convert(BiNode root) {
        if (root == null) return null;

        final BiNode part1 = convert(root.node1);
        final BiNode part2 = convert(root.node2);

        if (part1 != null) {
            concat(getTail(part1), root);
        }

        if (part2 != null) {
            concat(root, part2);
        }

        return part1 == null ? root : part1;
    }

    private static void concat(BiNode x, BiNode y) {
        x.node2 = y;
        y.node1 = x;
    }

    private BiNode getTail(BiNode node) {
        if (node == null) return null;

        while (node.node2 != null) {
            node = node.node2;
        }
        return node;
    }

    //O(N) time complexity.
    public BiNode convertI(BiNode root) {
        BiNode head = convertToCircular(root);
        head.node1.node2 = null;
        head.node1 = null;
        return head;
    }

    private BiNode convertToCircular(BiNode root) {
        if (root == null) return null;

        final BiNode part1 = convertToCircular(root.node1);
        final BiNode part2 = convertToCircular(root.node2);

        if (part1 == null && part2 == null) {
            root.node1 = root;
            root.node2 = root;
            return root;
        }

        final BiNode tail = (part2 == null) ? null : part2.node1;

        /* Соединение левой части с корнем */
        if (part1 == null) {
            concat(part2.node1, root);
        } else {
            concat(part1.node1, root);
        }

        /* Соединение правой части с корнем */
        if (part2 == null) {
            concat(root, part1);
        } else {
            concat(root, part2);
        }

        /* Соединение правой части с левой */
        if (part1 != null && part2 != null) {
            concat(tail, part1);
        }

        return part1 == null ? root : part1;
    }
}
