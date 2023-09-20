package structures.trees;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T extends Comparable<T>> {

    private Node root;

     private class Node {
        T value;
        Node left;
        Node right;

        Node(T value) {
            this.value = value;
            right = null;
            left = null;
        }
    }

    public void add(T value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, T value) {
        if (current == null) {
            return new Node(value);
        }

        int cmp = value.compareTo(current.value);
        if      (cmp < 0) current.left = addRecursive(current.left, value);
        else if (cmp > 0) current.right = addRecursive(current.right, value);
        else              return current;

//        if (value < current.value) {
//            current.left = addRecursive(current.left, value);
//        } else if (value > current.value) {
//            current.right = addRecursive(current.right, value);
//        } else {
//            // value already exists
//            return current;
//        }

        return current;
    }

    public void delete(T value) {
        root = deleteRecursive(root, value);
    }

    //recursive delete function
    private Node deleteRecursive(Node current, T value)  {
        //tree is empty
        if (current == null) return null;

        int cmp = value.compareTo(current.value);

        if (cmp < 0) {
            current.left = deleteRecursive(current.left, value);
            return current;
        } else if (cmp > 0) {
            current.right = deleteRecursive(current.right, value);
            return current;
        } else {
            if (current.right == null) return current.left;
            if (current.left  == null) return current.right;
            Node t = current;
            current = min(t.right);
            current.right = deleteMin(t.right);
            current.left = t.left;
        }
        return current;
    }

    private Node deleteMin(Node current) {
        if (current.left == null) return current.right;
        current.left = deleteMin(current.left);
        return current;
    }

    private Node min(Node current) {
        if (current.left == null) return current;
        else                return min(current.left);
    }

    public T minValue(Node root)  {
        return root.left == null ? root.value : minValue(root.left);
    }

    public boolean containsNode(T value) {
        return containsNodeRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node current, T value) {
        if (current == null) {
            return false;
        }

        int cmp = value.compareTo(current.value);
        if (cmp == 0) {
            return true;
        }
        return cmp < 0
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    //обход дерева
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
    }

    // поиск в ширину (тип обхода)
    // посещает все узлы уровня перед переходом на следующий уровень.
    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {

            Node node = nodes.remove();

            System.out.print(" " + node.value);

            if (node.left != null) {
                nodes.add(node.left);
            }

            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }

    //https://www.baeldung.com/java-binary-tree
    public static void main(String[] args){

        BinaryTree<Integer> bt = new BinaryTree();
        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);

        System.out.println();
       System.out.println(bt.minValue(bt.root));
       //System.out.println(bt.containsNode(2));
       bt.traverseInOrder(bt.root);
       System.out.println();
      // bt.delete(6);
      //  bt.traverseInOrder(bt.root);
       // System.out.println();
       //bt.traverseLevelOrder();
    }
}
