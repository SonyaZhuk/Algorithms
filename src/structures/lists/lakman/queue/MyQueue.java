package structures.lists.lakman.queue;

import structures.lists.Stack;

/**
 * Creates a queue is based on two stacks.
 * <p>
 * See Lakman p. 244
 */
public class MyQueue<T> {
    private Stack<T> stackNewest, stackOldest;

    public MyQueue() {
        stackNewest = new Stack<>();
        stackOldest = new Stack<>();
    }

    public int size() {
        return stackNewest.size() + stackOldest.size();
    }

    public void add(T value) {
        /* Все новейшие элементы находятся на вершине stackNewest */
        stackNewest.push(value);
    }

    public T peek() {
        shiftStack();
        return stackOldest.peek();
    }

    public T remove(T item) {
        shiftStack();
        return stackOldest.pop();
    }
    private void shiftStack() {
        if (stackOldest.isEmpty()) {
            while(!stackNewest.isEmpty())
                stackOldest.push(stackNewest.pop());
        }
    }
}
