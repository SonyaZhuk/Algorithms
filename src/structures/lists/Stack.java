package structures.lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {
    private int N;             // size of the stack
    private Node<T> head;     // top of stack

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
     * Initializes an empty stack.
     */
    public Stack() {
        head = null;
        N = 0;
    }

    /**
     * Is this stack empty?
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of items in the stack.
     * @return the number of items in the stack
     */
    public int size() {
        return N;
    }

    /**
     * Adds the item to this stack.
     * @param item the item to add
     */
    public void push(T item) {
//        first = new Node<E>(item, first);
        Node<T> oldFirst = head;
        head = new Node<T>();
        head.item = item;
        head.next = oldFirst;
        N++;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public T pop() {
        if (isEmpty()) throw new NoSuchElementException("lists.Stack underflow");
        T item = head.item;        // save item to return
        head = head.next;            // delete first node
        N--;
        return item;                   // return the saved item
    }


    /**
     * Returns (but does not remove) the item most recently added to this stack.
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("lists.Stack underflow");
        return head.item;
    }

    /**
     * Returns a string representation of this stack.
     * @return the sequence of items in the stack in LIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this)
            s.append(item + " ");
        return s.toString();
    }


    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     * @return an iterator to this stack that iterates through the items in LIFO order.
     */
    public Iterator<T> iterator() {
        return new ListIterator<T>(head);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<E> implements Iterator<E> {
        private Node<E> current;

        public ListIterator(Node<E> first) {
            current = first;
        }
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * Unit tests the <tt>lists.Stack</tt> data type.
     */
    public static void main(String[] args) {
        Stack<String> s = new Stack();
        s.push("a");
        s.push("t");
        s.push("y");
        s.pop();
        Iterator<String> it = s.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
