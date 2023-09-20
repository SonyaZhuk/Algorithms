package structures.lists;

/**
 * Java program to implement a Queue based on array.
 */
public class ArrayQueue<T> {
    private T[] arr;
    private int headIndex;
    private int tailIndex;
    private int size;

    public ArrayQueue(int capacity) {
        if (capacity < 1)
            throw new IllegalArgumentException("Capacity must be 1 or higher");

        arr = (T[]) new Object[capacity];
        headIndex = 0;
        tailIndex = 0;
        size = 0;
    }

    public void enqueue(T item) {
        if (isFull())
            //System.arraycopy
            throw new IndexOutOfBoundsException();

        arr[tailIndex] = item;
        tailIndex++;
        size++;
    }

    public T dequeue() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();

        T item = arr[headIndex];
        arr[headIndex] = null;
        headIndex++;
        size--;
        return item;
    }

    public T peek() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();

        return arr[headIndex];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return tailIndex == arr.length;
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(5);
        queue.enqueue(7);
        queue.enqueue(9);
        queue.enqueue(10);
        System.out.println("Peek element: "+queue.peek());
        System.out.println("Deleted element: " + queue.dequeue());
        System.out.println("Deleted element: " + queue.dequeue());
        System.out.println("Peek element: "+queue.peek());
        System.out.println("Deleted element: " + queue.dequeue());
    }
}
