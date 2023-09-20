package structures.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T> {

    private int N;               // number of elements on queue
    private Node<T> head;    // beginning of queue
    private Node<T> tail;     // end of queue

    // helper linked list class
    private static class Node<T> {
        private T item;
        private Node<T> next;
        public Node() {};

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        head = null;
        tail = null;
        N = 0;
    }

    /**
     * Is this queue empty?
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of items in this queue.
     * @return the number of items in this queue
     */
    public int size() {
        return N;
    }

    /**
     * Returns the item least recently added to this queue.
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("lists.Queue underflow");
        return head.item;
    }

    /**
     * Adds the item to this queue.
     * @param item the item to add
     */
    public void add(T item) {
        Node<T> oldlast = tail;
        tail = new Node<T>();
        tail.item = item;
        tail.next = null;
        if (isEmpty()) head = tail;
        else oldlast.next = tail;
        N++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public T remove() {
        if (isEmpty()) throw new NoSuchElementException("lists.Queue underflow");
        T item = head.item;
        head = head.next;
        N--;
        if (isEmpty()) tail = null;   // to avoid loitering
        return item;
    }

    /**
     * Returns a string representation of this queue.
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this)
            s.append(item + " ");
        return s.toString();
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<T> iterator()  {
        return new ListIterator<T>(head);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public ListIterator(Node<T> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next;
            return item;
        }
    }


    /**
     * Unit tests the <tt>lists.Queue</tt> data type.
     */
    public static void main(String[] args) {
        Queue<String> q = new Queue();
        q.add("a");
        q.add("t");
        q.add("y");
        q.remove();
        Iterator<String> it = q.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
