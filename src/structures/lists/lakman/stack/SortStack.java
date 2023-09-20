package structures.lists.lakman.stack;

import structures.lists.Stack;

/**
 * Sort a stack using additional stack, O(N^2) time, O(N) memory.
 * <p>
 * See Lakman p. 244
 */
public class SortStack {

    public Stack<Integer> sort(Stack<Integer> stack) {
        Stack<Integer> buffer = new Stack<>();
        while(!stack.isEmpty()) {
            int tmp = stack.pop();
            while(!buffer.isEmpty() && buffer.peek() < tmp)
                stack.push(buffer.pop());
            buffer.push(tmp);
        }
        return buffer;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(3);
        stack.push(8);
        stack.push(12);
        stack.push(7);
        stack.push(10);
        stack.push(5);
        SortStack ss = new SortStack();
        Stack<Integer> sortedStack = ss.sort(stack);
        System.out.println();
    }
}
