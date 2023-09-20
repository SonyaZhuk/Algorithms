package structures.lists.lakman.stack;

/**
 * Implementation Multi Stack based on the single array.
 * <p>
 * See Lakman p. 235
 */
public class MultiStack {
    private int numberOfStacks = 3; //for tree stacks
    private int stackCapacity;
    private int[] values;
    private int[] sizes;

    public MultiStack(int stackCapacity) {
        this.stackCapacity = stackCapacity;
        values = new int[this.stackCapacity * numberOfStacks];
        sizes = new int[numberOfStacks];
    }

    /* Занесение значения в стек. */
    public void push(int stackNum, int value) {
        validationStackNumber(stackNum);
        if (isFull(stackNum))
            throw new UnsupportedOperationException();
        values[indexOfTop(stackNum)] = value;
        sizes[stackNum - 1]++;
    }

    /* Извлечение элемента с вершины стека. */
    public int pop(int stackNum) {
        validationStackNumber(stackNum);
        if (isEmpty(stackNum))
            throw new UnsupportedOperationException();

        int topIndex = indexOfTop(stackNum) - 1;
        int value = values[topIndex]; // Получение вершины
        values[topIndex] = 0; //Очистка
        sizes[stackNum - 1]--;    // Сокращение
        return value;
    }

    /* Получение элемента с вершины стека. */
    public int peek(int stackNum) {
        validationStackNumber(stackNum);
        if (isEmpty(stackNum))
            throw new UnsupportedOperationException();

        int topIndex = indexOfTop(stackNum) - 1;
        return values[topIndex];
    }


    /* Проверка пустого стека. */
    public boolean isEmpty(int stackNum) {
        return sizes[stackNum - 1] == 0;
    }

    /* Проверка заполненного стека. */
    public boolean isFull(int stackNum) {
        return sizes[stackNum - 1] == stackCapacity;
    }

    public void validationStackNumber(int stackNum) {
        if (stackNum < 1 || stackNum > numberOfStacks)
            throw new IndexOutOfBoundsException();
    }

    /* Получение индекса вершины стека. */
    private int indexOfTop(int stackNum) {
        int offset = stackNum * stackCapacity;
        int size = sizes[stackNum - 1];
        return offset + size - 1;
    }

    public static void main(String[] args) {
        MultiStack stack = new MultiStack(3);
        stack.push(3, 5);
        stack.push(1, 6);
        stack.push(2, 3);
        stack.pop(3);
        stack.pop(2);
        stack.pop(1);
        System.out.println("");
    }
}
