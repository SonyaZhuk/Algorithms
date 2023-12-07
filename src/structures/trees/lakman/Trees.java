package structures.trees.lakman;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Trees and Graphs.
 * <p>
 * See Lakman p. 250
 */
public class Trees {

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

    /**
     * Checks that tree is balanced search. Symmetric (in-order) traversal approach.
     * <p>
     * See Lakman p. 254
     */
    private Integer lastPrinted = null;

    public boolean checkBST(TreeNode n) {
        if (n == null) return true;

        // recursion for left tree
        if (!checkBST(n.left)) return false;
        // checks the current node
        if (lastPrinted != null && n.data <= lastPrinted) {
            return false;
        }
        lastPrinted = n.data;

        // recursion for right tree
        if (!checkBST(n.right)) return false;

        return true;
    }

    /**
     * Checks that tree is balanced search. Using the property left <= current < right (min/max),
     * O(N) time, O(logN) memory.
     * <p>
     * See Lakman p. 255
     */
    public boolean checkBSTI(TreeNode n) {
        return checkBST(n, null, null);
    }

    private boolean checkBST(TreeNode n, Integer min, Integer max) {
        if (n == null) {
            return true;
        }
        if ((min != null && n.data <= min) || (max != null && n.data > max)) {
            return false;
        }
        if (!checkBST(n.left, min, n.data) || !checkBST(n.right, n.data, max)) {
            return false;
        }
        return true;
    }

    /**
     * Finds the next node for current node.
     * <p>
     * See Lakman p. 257
     */
    public TreeNode nextNode(TreeNode n) {
        if (n == null) return null;

        /* Found the right child node -> return the far left node of right subtree */
        if (n.right != null) {
            return leftMostChild(n.right);
        } else {
            TreeNode q = n;
            TreeNode x = q.parent;
            // Move above while we will stay to the left
            while (x != null && x.left != q) {
                q = x;
                x = x.parent;

            }
            return x;
        }
    }

    private TreeNode leftMostChild(TreeNode n) {
        if (n == null) {
            return null;
        }
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    /**
     * Finds the first common ancestor for two nodes. O(d), d - the depth a deeper node.
     * <p>
     * See Lakman p. 265
     */
    public TreeNode commonAncestor(TreeNode p, TreeNode q) {
        int delta = depth(p) - depth(q); // Вычисление разности глубин
        TreeNode first = delta > 0 ? q : p; // Узел с меньшей глубиной
        TreeNode second = delta > 0 ? p : q; // Узел с большей глубиной
        second = goUpBy(second, Math.abs(delta)); // Глубокий узел поднимается

        /* Find an paths intersection. */
        while (first != second && first != null && second != null) {
            first = first.parent;
            second = second.parent;
        }
        return first == null || second == null ? null : first;
    }

    private TreeNode goUpBy(TreeNode node, int delta) {
        while (delta > 0 && node != null) {
            node = node.parent;
            delta--;
        }
        return node;
    }

    private int depth(TreeNode node) {
        int depth = 0;
        while (node != null) {
            node = node.parent;
            depth++;
        }
        return depth;
    }

    /**
     * Finds the first common ancestor for two nodes. O(t), t - a size of the first subtree of the first common ancestor.
     * <p>
     * See Lakman p. 266
     */
    public TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /* Проверяет, что узел либо отсутствует в дереве, либо накрывает другой. */
        if (!covers(root, p) || !covers(root, q)) {
            return null;
        } else if (covers(p, q)) {
            return p;
        } else if (covers(q, p)) {
            return q;
        }

        /* Перемещаться вверх, пока не будет найден узел, накрывающий q. */
        TreeNode sibling = getSibling(p);
        TreeNode parent = p.parent;
        while (!covers(sibling, q)) {
            sibling = getSibling(parent);
            parent = parent.parent;
        }
        return parent;
    }

    private boolean covers(TreeNode root, TreeNode p) {
        if (root == null) return false;
        if (root == p) return true;
        return covers(root.left, p) || covers(root.right, p);
    }

    private TreeNode getSibling(TreeNode node) {
        if (node == null || node.parent == null) {
            return null;
        }
        TreeNode parent = node.parent;
        return parent.left == node ? parent.right : parent.left;
    }

    /**
     * Finds the first common ancestor for two nodes. Without links to ancestors approach.
     * O(n), 2n nodes.
     * <p>
     * See Lakman p. 267
     */
    public TreeNode commonAncestorI(TreeNode root, TreeNode p, TreeNode q) {
        /* Проверка ошибки - один узел отсутствует в дереве. */
        if (!covers(root, p) || !covers(root, q)) {
            return null;
        }
        return ancestorHelper(root, p, q);
    }

    private TreeNode ancestorHelper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        boolean pisOnLeft = covers(root.left, p);
        boolean qisOnLeft = covers(root.left, q);
        if (pisOnLeft != qisOnLeft) { // Узлы на разных сторонах
            return root;
        }
        TreeNode childSide = pisOnLeft ? root.left : root.right;
        return ancestorHelper(childSide, p, q);
    }


    /**
     * Finds all sequences for the binary search tree which can create this tree.
     * <p>
     * See Lakman p. 271
     */
    public ArrayList<LinkedList<Integer>> allSequences(TreeNode node) {
        final ArrayList<LinkedList<Integer>> result = new ArrayList<>();

        if (node == null) {
            result.add(new LinkedList<>());
            return result;
        }

        final LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(node.data);
        /* Рекурсия по левому и правому поддереву. */
        final ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
        final ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

        /* Переплетение всех списков с левой и правой стороны. */
        for (LinkedList<Integer> left : leftSeq) {
            for (LinkedList<Integer> right : rightSeq) {
                final ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }
        return result;
    }

    /* Списки переплетаются всеми возможными способами. Алгоритм удаляет начало
       из одного списка, проводит рекурсию, а затем проделывает то же самое с другим списком. */
    private void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second,
                            ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
        /* Один список пуст. Добавить остаток в [клонированный] prefix и сохранить результат. */
        if (first.size() == 0 || second.size() == 0) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        /* Рекурсия с началом first, добавленным в prefix. Удаление начала
           модифицирует first, поэтому в дальнейшем его необходимо вернуть на место. */
        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        /* Проделать то же с second, с модификацией и восстановлением списка.*/
        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headSecond);
    }

    /**
     * Checks that T2 is subtree T1. T1 > T2. O(n + m) memory and time
     * <p>
     * See Lakman p. 274
     */
    public boolean containsTree(TreeNode tl, TreeNode t2) {
        //два дерева идентичны, если они имеют одинаковый порядок префиксного обхода.
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();

        getOrderString(tl, string1);
        getOrderString(t2, string2);
        return string1.indexOf(string2.toString()) != -1;
    }

    private void getOrderString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("Х"); // Добавление индикатора null
            return;
        }
        sb.append(node.data + " "); // Добавление корня
        getOrderString(node.left, sb); //Добавление левого узла
        getOrderString(node.right, sb); //Добавление правого узла
    }


    /**
     * Checks that T2 is subtree T1. T1 > T2. O(log(n) + log(m)) memory, O(nm) time (in worse case).
     * <p>
     * See Lakman p. 276
     */
    public boolean containsTreeI(TreeNode tl, TreeNode t2) {
        if (t2 == null) return true; // Пустое дерево всегда является поддеревом
        return subtree(tl, t2);
    }

    private boolean subtree(TreeNode rl, TreeNode r2) {
        if (rl == null) {
            return false; // Большое дерево пустое, а поддерево так и не найдено.
        } else if (rl.data == r2.data && matchTree(rl, r2)) {
            return true;
        }
        return subtree(rl.left, r2) || subtree(rl.right, r2);
    }

    private boolean matchTree(TreeNode rl, TreeNode r2) {
        if (rl == null && r2 == null) {
            return true; // В поддереве не осталось узлов
        } else if (rl == null || r2 == null) {
            return false; // Ровно одно дерево пусто, поэтому совпадения нет
        } else if (rl.data != r2.data) {
            return false; //Данные не совпадают
        }

        return matchTree(rl.left, r2.left) && matchTree(rl.right, r2.right);
    }
}
