package structures.lists.lakman.stack;

import structures.lists.Stack;

/**
 * Creates a stack with min elements.
 * <p>
 * See Lakman p. 240
 */
public class StackWithMinSol1<Integer> extends Stack<Integer> {

    private Stack stack;

    public StackWithMinSol1() {
        stack = new Stack();
    }

    public void push(Integer item) {
        if ((int)item < min())
            stack.push(item);
        super.push(item);
    }

    public Integer pop() {
        Integer val = super.pop();
        if ((int) val == min())
            stack.pop();

        return val;
    }

    public int min() {
        return (stack.isEmpty()) ? java.lang.Integer.MAX_VALUE : (int) stack.peek();
    }

    public static void main(String[] args) {
        StackWithMinSol1 stack = new StackWithMinSol1();
        stack.push(3);
        stack.push(6);
        stack.push(5);
        stack.push(1);
        System.out.println(stack.min());
    }
}
