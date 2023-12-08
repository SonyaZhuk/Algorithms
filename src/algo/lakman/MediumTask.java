package algo.lakman;

/**
 * Medium level tasks.
 * <p>
 * See Lakman p. 489
 */
public class MediumTask {

    /**
     * Exchange values without a temp variable.
     * <p>
     * See Lakman p. 489
     */
    public void exchange(int x, int y) {
        x = x - y;
        y = x + y;
        x = y - x;
        System.out.println(x + " " + y);
    }

    /**
     * Exchange values without a temp variable. Advantages: can use it with another data types.
     * <p>
     * See Lakman p. 489
     */
    public void exchangeI(int x, int y) {
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println(x + " " + y);
    }

    public static void main(String[] args) {
        MediumTask task = new MediumTask();
        task.exchangeI(5, 8);
    }
}
