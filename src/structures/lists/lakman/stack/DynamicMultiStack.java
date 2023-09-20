package structures.lists.lakman.stack;

import java.util.EmptyStackException;

/**
 * Implementation Multi Stack based on the single array. TODO
 * <p>
 * See Lakman p. 237
 */
public class DynamicMultiStack {

    /* StackInfo - простой класс для хранения информации о каждом стеке.
     * То же самое можно сделать при помощи отдельных переменных, но такое
     * решение получается громоздким и ничего реально не дает. */
    private class StackInfo {
        public int start, size, capacity;

        public StackInfo(int start, int capacity) {
            this.start = start;
            this.capacity = capacity;
        }

        /* Проверить, лежит ли индекс в границах стека.
         * Стек может продолжаться от начала массива. */
        public boolean isWithinStackCapacity(int index) {
            /* Если индекс выходит за границы массива, вернуть false. */
            if (index < 0 || index >= values.length) {
                return false;
            }

            /* При циклическом переносе индекса внести поправку. */
            int contiguousindex = index < start ? index + values.length : index;
            int end = start + capacity;
            return start <= contiguousindex && contiguousindex < end;
        }

        public int lastCapacityIndex() {
            return adjustIndex(start + capacity - 1);
        }
        public int lastElementIndex() {
            return adjustIndex(start + size - 1);
        }
        public boolean isFull() {
            return size == capacity;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    private StackInfo[] info;
    private int[] values;

    public DynamicMultiStack(int numberOfStacks, int defaultSize) {
        /* Создание метаданных для всех стеков. */
        info = new StackInfo[numberOfStacks];
        for (int i = 0; i < numberOfStacks; i++) {
            info[i] = new StackInfo(defaultSize * i, defaultSize);
        }
        values = new int[numberOfStacks * defaultSize];
    }

    /* Занесение значения в стек со сдвигом/расширением стеков по мере
     * необходимости. Если все стеки заполнены, выдается исключение. */
    public void push(int stackNum, int value) {
        if (allStacksAreFull())
            throw new IndexOutOfBoundsException();


        /* Если стек заполнен, расширить его. */
        StackInfo stack = info[stackNum];
        if (stack.isFull()) {
            expand(stackNum);
        }

        /* Определение индекса верхнего элемента в массиве + 1
         * и увеличение указателя стека */
        stack.size++;
        values[stack.lastElementIndex()] = value;
    }

    /* Извлечение из стека. */
    public int pop(int stackNum) throws Exception {
        StackInfo stack = info[stackNum];
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }

        /* Удаление последнего элемента. */
        int value = values[stack.lastElementIndex()];
        values[stack.lastElementIndex()] = 0; // Очистка
        stack.size--; // Уменьшение размера
        return value;
    }

    /* Получение верхнего элемента стека.*/
    public int peek(int stackNum) {
        StackInfo stack = info[stackNum];
        return values[stack.lastElementIndex()];
    }

    /* Сдвиг элементов в стеке на один элемент. При наличии свободного места
     * стек уменьшится на один элемент. Если свободного места нет,
     * также нужно будет выполнить сдвиг в следующем стеке. */
    private void shift(int stackNum) {
        System.out.println("/// Shifting " + stackNum);
        StackInfo stack = info[stackNum];

        /* Если стек заполнен, следующий стек необходимо сдвинуть на один
         * элемент. Текущий стек занимает освободившийся индекс. */
        if (stack.size >= stack.capacity) {
            int nextStack = (stackNum + 1) % info.length;
            shift(nextStack);
            stack.capacity++; // Захват индекса из следующего стека
        }

        /* Сдвиг всех элементов в стеке. */
        int index = stack.lastCapacityIndex();
        while (stack.isWithinStackCapacity(index)) {
            values[index] = values[previousIndex(index)];
            index = previousIndex(index);
        }

        /* Изменение данных стека. */
        values[stack.start] = 0; // Очистка
        stack.start = nextIndex(stack.start); // Перемещение start
        stack.capacity--; //Уменьшение емкости
    }

    /* Расширение стека посредством сдвига других стеков. */
    private void expand(int stackNum) {
        shift((stackNum + 1) % info.length);
        info[stackNum].capacity++;
    }

    /* Возвращается фактическое количество элементов в стеке. */
    public int numberOfElements() {
        int size = 0;
        for (StackInfo sd : info) {
            size += sd.size;
        }
        return size;
    }

    /* Возвращает true, если все стеки заполнены. */
    public boolean allStacksAreFull() {
        return numberOfElements() == values.length;
    }

    /* Индекс переводится в диапазон 0 -> leпgth - 1. */
    private int adjustIndex(int index) {
        /* оператор Java mod может возвращать отрицательные значения.
         * Например, (-11 % 5) вернет -1, а не 4, как требуется
         * (из-за переноса индекса). */
        int max = values.length;
        return ((index % max) + max) % max;
    }

    /* Получение следуего индекса с поправкой на перенос. */
    private int nextIndex(int index) {
        return adjustIndex(index + 1);
    }

    /* Получение предыдущего индекса с поправкой на перенос. */
    private int previousIndex(int index) {
        return adjustIndex(index - 1);
    }

    public static void main(String[] args) {
        DynamicMultiStack stack = new DynamicMultiStack(3, 3);
    }
}
