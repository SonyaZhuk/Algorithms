package structures.trees;

public class RedBlackTree<T extends Comparable<T>> {


    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node root;     // root of the BST

    // BST helper node data type
    private class Node {
        private T value;           // data

        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int N;             // subtree count

        public Node(T value, boolean color, int N) {
            this.value = value;
            this.color = color;
            this.N = N;
        }
    }

    /*************************************************************************
     *  Node helper methods
     *************************************************************************/
    // is node x red; false if x is null ?
    private boolean isRed(Node x) {
        if (x == null) return false;
        return (x.color == RED);
    }


    // number of node in subtree rooted at x; 0 if x is null
    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    /*************************************************************************
     *  red-black tree helper functions
     *************************************************************************/

    // make a left-leaning link lean to the right
    private Node rotateRight(Node h) {
        assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    // make a right-leaning link lean to the left
    private Node rotateLeft(Node h) {
        assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        assert (h != null) && (h.left != null) && (h.right != null);
        assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
                || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    /*************************************************************************
     *  Red-black insertion
     *************************************************************************/

    // insert the key-value pair; overwrite the old value with the new value
    // if the key is already present
    public void put(T value) {
        root = put(root, value);
        root.color = BLACK;
       // assert check();
    }

    // insert the key-value pair in the subtree rooted at current
    private Node put(Node current, T value) {
        if (current == null) return new Node(value, RED, 1);

        int cmp = value.compareTo(current.value);
        if      (cmp < 0) current.left  = put(current.left, value);
        else if (cmp > 0) current.right = put(current.right, value);
        else              current.value   = value;

        // fix-up any right-leaning links
        if (isRed(current.right) && !isRed(current.left))      current = rotateLeft(current);
        if (isRed(current.left)  &&  isRed(current.left.left)) current = rotateRight(current);
        if (isRed(current.left)  &&  isRed(current.right))     flipColors(current);
        current.N = size(current.left) + size(current.right) + 1;

        return current;
    }


    /*****************************************************************************
     *  Test client
     *  % java BST < tinyST.txt
     *  *  A 8
     *  *  C 4
     *  *  E 12
     *  *  H 5
     *  *  L 11
     *  *  M 9
     *  *  P 10
     *  *  R 3
     *  *  S 0
     *  *  X 7
     *****************************************************************************/
    public static void main(String[] args) {
        RedBlackTree<Integer> st = new RedBlackTree();
        st.put( 8);
        st.put( 4);
        st.put( 12);
        st.put( 5);
        st.put( 11);
        st.put( 9);
        st.put( 10);
        st.put(3);
        st.put(0);
        st.put(7);

//        for (String s : st.keys())
//            System.out.println(s + " " + st.get(s));
        System.out.println(st.size(st.root));
    }
}
