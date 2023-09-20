package structures.lists;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Java program to implement a Doubly Linked List.
 * <p>
 * based on https://devwithus.com/doubly-linked-list-java/
 */
public class DoublyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> previous;

        public Node(T data, Node<T> next, Node<T> previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }

        public Node(T data) {
            this(data, null, null);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * Add a new item at the beginning of the list.
     */
    public void addFirst(T data) {
        Node<T> newNode = new Node<T>(data, head, null);
        if (head != null) {
            head.previous = newNode;
        }
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        size++;
    }

    /**
     * Add a new item to the end of the list.
     */
    public void addLast(T data) {
        Node<T> newNode = new Node<T>(data, null, tail);
        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;
        if (head == null) {
            head = newNode;
        }
        size++;
    }

    /**
     * Insert a new node at a specific position.
     */
    public void addAtPos(int index, T data) {
        if (index < 0 || index > this.size()) {
            throw new IllegalArgumentException("Invalid Index");
        } else if (index == 0) {
            this.addFirst(data);
        } else if (index == this.size()) {
            this.addLast(data);
        } else {
            Node<T> newNode = new Node<T>(data);
            Node<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode;
            newNode.previous = currentNode.previous;
            currentNode.previous = newNode;
            newNode.previous.next = newNode;
        }
        size++;
    }

    /**
     * Delete an item at the beginning of the list.
     */
    public void deleteFirst() {
        if (this.isEmpty()) throw new NoSuchElementException();

        if (head.next == null) {
            tail = null;
        } else {
            head.next.previous = null;
        }
        head = head.next;
        size--;
    }

    /**
     * Delete remove a node at specific index.
     */
    public void deleteLast() {
        if (this.isEmpty()) throw new NoSuchElementException();

        if (head.next == null) {
            head = null;
        } else {
            tail.previous.next = null;
        }
        tail = tail.previous;
        size--;
    }

    /**
     * Delete a node at specific index.
     */
    public void removeAtPos(int index) {
        if (index < 0 || index > this.size()) {
            throw new IllegalArgumentException("Invalid Index");
        } else if (index == 0) {
            this.deleteFirst();
        } else if (index == this.size() - 1) {
            this.deleteLast();
        } else {
            Node<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            currentNode.previous.next = currentNode.next;
            currentNode.next.previous = currentNode.previous;
        }
        size--;
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
        if (tail == null) throw new NoSuchElementException();

        return tail.data;
    }

    /**
     * Returns an element by its value.
     */
    public Node<T> getByValue(T value) {
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.data.equals(value)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    /**
     * Returns an element by its position.
     */
    public Node<T> getByIndex(int index) {
        if (index < 0 || index > this.size()) {
            throw new IllegalArgumentException("Invalid Index");
        }
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public void displayForward() {
        if (this.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        Node<T> currentNode = head;
        while (currentNode != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
        }
    }

    public void displayBackward() {
        if (this.isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        Node<T> currentNode = tail;
        while (currentNode != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.previous;
        }
    }

/////////////////////////////////////////////////////////////

// Lakman 85-215

///////////////////////////////////////////////////////////

    /**
     * Delete duplication O(N) time.
     * <p>
     * See Lakman p. 215
     */
    public void deleteDuplication() {
        Set<T> table = new HashSet<>();

        Node<T> previous = null;
        Node<T> curr = head;
        while (curr != null) {
            if (table.contains(curr.data)) {
                previous.next = curr.next;
            } else {
                table.add(curr.data);
                previous = curr;
            }
            curr = curr.next;
        }
    }

    /**
     * Delete duplication O(1) memory, O(N^2) time.
     * <p>
     * See Lakman p. 215
     */
    public void deleteDuplication1() {
        Node<T> curr = head;

        while (curr != null) {
            Node<T> tmp = curr;
            while (tmp.next != null) {
                if (tmp.next.data == curr.data) {
                    tmp.next = tmp.next.next;
                } else {
                    tmp = tmp.next;
                }
            }
            curr = curr.next;
        }
    }

    /**
     * Checks is list a palindrom.
     * <p>
     * See Lakman p. 88, 224
     */
    public boolean isPalindrom() {
        if (this.isEmpty() || this.size == 1) return true;

        StringBuilder builder = new StringBuilder();
        Node<T> curr = head;
        while (curr != null) {
            builder.append(curr.data);
            curr = curr.next;
        }
        for (int i = 0; i < builder.length() / 2; i++) {
            if (builder.charAt(i) != builder.charAt(builder.length() - 1)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.addFirst(5);
        list.addFirst(8);
        list.addFirst(6);
        list.addFirst(5);
        list.addFirst(6);
        list.deleteDuplication1();
        list.displayForward();

//        DoublyLinkedList<String> l = new DoublyLinkedList<>();
//        l.addLast("a");
//        l.addLast("b");
//        l.addLast("a");
//        System.out.println(l.isPalindrom());
    }
}
