package structures.lists;

/**
 * Java program to implement a Stack based on array.
 */
public class ArrayStack<T> {
    private T[] arr;
    private int tailIndex;

    public ArrayStack(int capacity) {
        if (capacity < 1)
            throw new IllegalArgumentException("Capacity must be 1 or higher");

        arr = (T[]) new Object[capacity];
        tailIndex = -1;
    }

    public void push(T item) {
        if (isFull())
            throw new IndexOutOfBoundsException();

        tailIndex++;
        arr[tailIndex] = item;
    }

    public T pop() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();

        int oldTail = tailIndex;
        tailIndex--;
        arr[oldTail] = null;
        return arr[oldTail];
    }

    public Object peek() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();

        return arr[tailIndex];
    }

    public boolean isEmpty() {
        return (tailIndex == -1);
    }

    public boolean isFull() {
        return tailIndex == arr.length;
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(7);
        stack.push(9);
        stack.push(10);
        System.out.println("Peek element: "+stack.peek());
        System.out.println("Deleted element: " + stack.pop());
        System.out.println("Deleted element: " + stack.pop());
        System.out.println("Peek element: "+stack.peek());
        System.out.println("Deleted element: " + stack.pop());
    }
}
