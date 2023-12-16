package algo.lakman.hard;

/**
 * Hard level tasks.
 * <p>
 * See Lakman p. 561
 */
public class HardTask {

    /**
     * Sum operation without any arithmetic signs.
     * <p>
     * See Lakman p. 561
     */
    public int add(int a, int b) {
        if (a == 0) return b;
        int sum = a ^ b;
        int c = (a & b) << 1;
        return add(sum, c);
    }

    public int addIterative(int a, int b) {
        if (a == 0) return b;
        while (b != 0) {
            int sum = a ^ b;
            int c = (a & b) << 1;
            a = sum;
            b = c;
        }
        return a;
    }

    /**
     * Shuffle 52 cards.
     * <p>
     * See Lakman p. 562
     */
    public int[] shuffleArrayRecursively(int[] cards, int i) {
        if (i == 0) return cards;
        shuffleArrayRecursively(cards, i - 1); // Перетасовать предыдущую часть
        int k = rand(0, i); // Выбрать случайный индекс
        /* Переставить местами элементы k и i */
        int temp = cards[k];
        cards[k] = cards[i];
        cards[i] = temp;
        return cards;
    }

    /* Случайное число в диапазоне от lower до higher включительно */
    private int rand(int lower, int higher) {
        return lower + (int) (Math.random() * (higher - lower + 1));
    }

    public void shuffleIteratively(int[] cards) {
        for (int i = 0; i < cards.length; i++) {
            int k = rand(0, i);
            int temp = cards[k];
            cards[k] = cards[i];
            cards[i] = temp;
        }
    }
}
