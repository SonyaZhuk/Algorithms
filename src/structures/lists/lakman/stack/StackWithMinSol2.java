package structures.lists.lakman.stack;

import structures.lists.Stack;

/**
 * Creates a stack with min elements.
 * <p>
 * See Lakman p. 240
 */
public class StackWithMinSol2 extends Stack<StackWithMinSol2.NodeWithMin> {
     static class NodeWithMin {
        public int value;
        public int min;

        public NodeWithMin(int v, int min) {
            value = v;
            this.min = min;
        }
    }

    public void push(int value) {
        int newMin = Math.min(value, min());
        super.push(new NodeWithMin(value, newMin));
    }
    public NodeWithMin pop() {
         return super.pop();
    }
    public int min() {
        return (this.isEmpty()) ? Integer.MAX_VALUE : peek().min;
    }

    public static void main(String[] args) {
        StackWithMinSol2 stack = new StackWithMinSol2();
        stack.push(3);
        stack.push(6);
        stack.push(5);
        stack.push(1);
        stack.pop();
        System.out.println(stack.min());
    }
}
