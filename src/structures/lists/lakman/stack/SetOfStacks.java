package structures.lists.lakman.stack;


import structures.lists.Stack;

import java.util.ArrayList;

/**
 * Creates a set of stacks.
 * <p>
 * See Lakman p. 241
 */
public class SetOfStacks {
    private int capacity;
    private ArrayList<Stack> stacks;

    public SetOfStacks(int capacity) {
        this.stacks = new ArrayList<>();
        this.capacity = capacity;
    }

    public void push(int v) {
        final Stack stack = getLastStack();
        if (stack != null && stack.size() < capacity) {
            stack.push(v);
            return;
        }
        final Stack newStack = new Stack<>();
        newStack.push(v);
        stacks.add(newStack);
    }

    public int рор() {
        if (stacks.size() == 0) throw new IndexOutOfBoundsException();
        final Stack stack = getLastStack();
        if (stack.size() != 1) {
            return (int) stack.pop();
        }
        int val = (int) stack.pop();
        stacks.remove(stacks.size() - 1);
        return val;
    }

    //TODO
    public int popAt(int index) {
        return 0;
    }

    private Stack getLastStack() {
        if (stacks.size() == 0) {
            final Stack stack = new Stack();
            stacks.add(stack);
            return stack;
        }
        return stacks.get(stacks.size() - 1);
    }

    public static void main(String[] args) {
        final SetOfStacks stacks = new SetOfStacks(2);
        stacks.push(1);
        stacks.push(2);
        stacks.push(3);
        stacks.push(4);
        stacks.push(5);
        stacks.push(6);
        stacks.push(7);
        stacks.рор();
        System.out.println();
    }
}
