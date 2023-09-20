package structures.lists;


import java.util.NoSuchElementException;

/**
 * Java program to implement a Singly Linked List.
 * <p>
 * based on https://devwithus.com/implement-linked-list-java/
 */
public class SinglyLinkedList<T> {
    private Node<T> head;

    public SinglyLinkedList() {
        this.head = null;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        // Next is by default initialized as null
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

    }

    /**
     * Add a new item at the beginning of the list.
     */
    public void addFirst(T data) {
        head = new Node<T>(data, head);

//        Node newNode = new Node(data);
//        // current head becomes this newNode's next
//        newNode.next = head;
//
//        // changing head to this newly created node
//        head = newNode;
//
//        return head;
    }

    /**
     * Delete an item at the beginning of the list.
     */
    public void deleteFirst() {
        if (head == null) throw new NoSuchElementException();

        // move head to next node
        head = head.next;
    }

    /**
     * Add a new item to the end of the list.
     */
    public void addLast(T data) {

        //Checks if the list is empty
        if (head == null) {
            addFirst(data);
        } else {
            Node<T> tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = new Node<T>(data, null);
        }
    }

//    /**
//     * Delete an item at the end of the list.
//     */
//    public void deleteLast() {
//        if (head == null) throw new UnsupportedOperationException();
//
//        // move head to next node
//        head = head.next;
//    }

    /**
     * Removes the first occurrence of the specified element.
     */
    public void remove(T key) {
        if (head == null) throw new NoSuchElementException();

        if (head.data.equals(key)) {
            head = head.next;
            return;
        }

        Node<T> cur = head;
        Node<T> prev = null;
        while (cur != null && !cur.data.equals(key)) {
            prev = cur;
            cur = cur.next;
        }

        if (cur == null) throw new UnsupportedOperationException();

        prev.next = cur.next;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean contains(T key) {
        if (head == null) throw new NoSuchElementException();

        if (head.data.equals(key)) return true;

        Node<T> tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
            if (tmp.data.equals(key)) return true;
        }
        return false;
    }

    /**
     * Returns the first element of the linked list.
     */
    public T getFirst() {
        if (head == null) throw new NoSuchElementException();

        return head.data;
    }

    /**
     * Returns the last element of the linked list.
     */
    public T getLast() {
        if (head == null) throw new NoSuchElementException();

        Node<T> tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        return tmp.data;
    }

    /**
     * Returns the element at the specified position.
     */
    public T get(int pos) {
        if (head == null) throw new NoSuchElementException();

        Node<T> tmp = head;
        for (int k = 0; k < pos; k++) {
            tmp = tmp.next;
        }

        if (tmp == null) throw new IndexOutOfBoundsException();

        return tmp.data;
    }

    /**
     * Add a new element after the element containing the specified key.
     */
    public void insertAfter(T key, T toInsert) {
        Node<T> tmp = head;
        while (tmp != null && !tmp.data.equals(key)) {
            tmp = tmp.next;
        }

        if (tmp != null) {
            tmp.next = new Node<T>(toInsert, tmp.next);
        }
    }

    /**
     * Add a new element before the element containing the given key.
     */
    public void insertBefore(T key, T toInsert) {
        if (head == null) return;

        if (head.data.equals(key)) {
            addFirst(toInsert);
            return;
        }
        Node<T> prev = null;
        Node<T> cur = head;
        while (cur != null && !cur.data.equals(key)) {
            prev = cur;
            cur = cur.next;
        }

        if (cur != null) {
            prev.next = new Node<T>(toInsert, cur);
        }
    }

    /**
     * Reverse the list.
     */
    public void reverseList() {
        Node<T> current = head;
        Node<T> previous = null;
        Node<T> enext = null;
        while (current != null) {
            enext = current.next;
            current.next = previous;
            previous = current;
            current = enext;
        }
        head = previous;
    }

    /**
     * Clone the list.
     */
    public SinglyLinkedList<T> clone() {
        SinglyLinkedList<T> clonedLinkedList = new SinglyLinkedList<T>();
        Node<T> tmp = head;
        while (tmp != null) {
            clonedLinkedList.addLast(tmp.data);
            tmp = tmp.next;
        }
        return clonedLinkedList;
    }

    public void display() {
        Node node = head;
        //as linked list will end when Node reaches Null
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }


/////////////////////////////////////////////////////////////

// Lakman 85-216

///////////////////////////////////////////////////////////

    /**
     * Get k-th element from the tail.
     * <p>
     * See Lakman p. 216
     */
    public T printKthToLast(int k) {
        reverseList();
        Node<T> node = head;

        for (int i = 0; i < k; i++) {
            node = node.next;
        }
        return node.data;
    }

    /**
     * Get index of k-th element from the tail,  O(N) memory.
     * <p>
     * See Lakman p. 216
     */
    public int printKthToLast(Node<T> head, int k) {
        if (head == null) return 0;

        int index = printKthToLast(head.next, k) + 1;

        if (index == k) {
            System.out.println(k + "th to last node is " + head.data);
        }
        return index;
    }

    /**
     * Get index of k-th element from the tail , O(N) memory.
     * <p>
     * See Lakman p. 216
     */
    class Index {
        int value = 0;
    }

    public Node<T> kthTolast(Node<T> head, int k) {
        Index idx = new Index();
        return kthTolast(head, k, idx);
    }

    private Node<T> kthTolast(Node<T> head, int k, Index idx) {
        if (head == null) return null;
        Node<T> node = kthTolast(head.next, k, idx);
        idx.value = idx.value + 1;
        if (idx.value == k) {
            return head;
        }
        return node;
    }

    /**
     * Get k-th element from the tail O(1) memory, O(N) time.
     * <p>
     * See Lakman p. 216
     */
    public T printKthLast1(int k) {
        Node<T> p1 = head;
        Node<T> p2 = head;
        for (int i = 0; i < k; i++) {
            if (p1 == null) return null;
            p1 = p1.next;
        }

        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2.data;
    }

    /**
     * Removes element from the middle. Access only to the current element O(1) memory, O(N) time.
     * <p>
     * See Lakman p. 218
     */
    public boolean deleteNode(Node<T> n) {
        if (n == null || n.next == null) return false;

        Node<T> next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }

    /**
     * Split elements from the value x. :(
     * <p>
     * See Lakman p. 219
     */
    public void partition(Node<Integer> node, int x) {
        Node<Integer> head = node;
        Node<Integer> tail = node;

        while (node != null) {
            Node<Integer> next = node.next;
            if (node.data < x) {
                node.next = head;
                head = node;
            } else {
                tail.next = node;
                tail = node;
            }
            node = next;
        }
        tail.next = null;
    }

    /**
     * Count sum of elements for two lists O(A) time, O(1).
     * <p>
     * See Lakman p. 221
     */
    public static SinglyLinkedList<Integer> sum(SinglyLinkedList<Integer> l1,
                                                SinglyLinkedList<Integer> l2) {

        //without checking of equals sizes for lists

        Node<Integer> node1 = l1.head;
        Node<Integer> node2 = l2.head;
        boolean flag = false;
        int val = 0;
        Node<Integer> node = null;
        while (node1 != null) {
            val = node1.data + node2.data;
            if (flag) val = val + 1;
            if (val >= 10) {
                flag = true;
                node2.data = val % 10;
            } else {
                flag = false;
                node2.data = val;
            }
            node = node2;
            node1 = node1.next;
            node2 = node2.next;
        }
        if (val >= 10) {
            node.next = new Node(val / 10, null);
        }

        return l2;
    }


    /**
     * Checks is list a palindrom.
     * <p>
     * See Lakman p. 88, 224
     */
    public boolean isPalindrome() {
        Node<Integer> p1 = (Node<Integer>) head;
        // Node<Integer> p2 = (Node<Integer>) head;

        Stack<Integer> stack = new Stack<>();
        int size = 0;
        while (p1 != null) {
            stack.push(p1.data);
            p1 = p1.next;
            size++;
        }

        p1 = (Node<Integer>) head;

        for (int i = 0; i < size / 2; i++) {
            if (p1.data != stack.pop()) return false;
            p1 = p1.next;
        }

        return true;
    }

    /**
     * Checks is list a palindrom.
     * <p>
     * See Lakman p. 88, 224
     */
    public boolean isPalindrome1(int k) {
        Node<Integer> p1 = (Node<Integer>) head;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < k; i++) {
            stack.push(p1.data);
            p1 = p1.next;
        }

        if (stack.size() % 2 != 0) stack.pop();

        while (p1 != null) {
            if (p1.data != stack.pop()) return false;
            p1 = p1.next;
        }
        return true;
    }

    /**
     * Get the node of intersection of two list.
     * <p>
     * See Lakman p. 88, 229
     */
    public Node<T> findIntersection(SinglyLinkedList<T> l1,
                                    SinglyLinkedList<T> l2) {

        //simple implementation, without comparing sizes of two lists and cutting if l1.size != l2.size

        Node<T> node1 = l1.head;
        Node<T> node2 = l2.head;

        while (node1 != null) {
            if (node1 == node2) return node1;
            node1 = node1.next;
            node2 = node2.next;
        }

        return null;
    }

    /**
     * Get the start of loop for list, O(2N) runtime, O(1).
     * <p>
     * See Lakman p. 88, 234
     */
    public Node<T> findBeginning() {
        Node<T> node = head;
        Node<T> last = null;
        int size = 1;
        while (node.next != null) {
            node = node.next;
            last = node;
            size++;
        }
        node = head;
        while (size != 0) {
            if (last.data.equals(node.data)) return node;
            node = node.next;
            size--;
        }
        return null;
    }

    /**
     * Get the start of loop for list :(.
     * <p>
     * See Lakman p. 88, 234
     */
    public Node<T> findBeginning1() {
        Node<T> slow = head;
        Node<T> fast = head;

        /*Поиск точки встречи (РАЗМЕР_ПЕТЛИ - k шагов в связном сnиске) */
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { // Встреча
                break;
            }
        }

        /* Проверка ошибок - точки встречи нет, а значит, нет и петли */
        if (fast == null || fast.next == null) {
            return null;
        }

         /* Slow перемещается в начало, fast остается в точке встречи. Если
20 * они будут двигаться в одном темпе, то встретятся в начале петли. */
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        /* Оба указателя находятся в начале петли. */
        return fast;
    }


    public static void main(String[] args) {
        //something like Stack
        SinglyLinkedList list = new SinglyLinkedList();
        SinglyLinkedList list1 = new SinglyLinkedList();
//        list.addFirst(1);
//        list.addFirst(5);
//        list.addFirst(8);
//        list.addFirst(3);
//        list.deleteFirst();
        list.addLast('A');
        list.addLast('B');
        list.addLast('C');
        list.addLast('D');
        list.addLast('E');
        list.addLast('C');
//        list1.addLast(5);
//        list1.addLast(5);
//        list1.addLast(5);
//        list.addLast(5);
//        list.addLast(10);
//        list.addLast(2);
//        list.addLast(1);
//        list.remove(1);
        list.display();
        System.out.println(list.contains(8));
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.get(1));
        System.out.println("-------------");
//        list.reverseList();
//        list.display();
//        System.out.println("-------------");
//        System.out.println(list.printKthToLast(list.head, 2));
//        list.partition(list.head, 5);
//        sum(list, list1).display();
        System.out.println(list.findBeginning().data);
    }
}
