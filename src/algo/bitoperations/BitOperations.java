package algo.bitoperations;

/**
 * Operations with bits.
 * <p>
 * See Lakman p. 106, 287
 */
public class BitOperations {

    /**
     * Unit tests.
     */
    public static void main(String[] args) {
        //+, -, *, >>,<<, ^,~, &, >>>, <<<

        BitOperations operations = new BitOperations();
        operations.arithmeticShift();
        operations.logicalShift();
    }

    private void arithmeticShift() {
        int x = 10;
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toBinaryString(x >> 1));
    }
    private void logicalShift() {
        int x = 10;
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toBinaryString(x >>> 1));
    }

    private int repeatedArithmeticShift(int x, int count) {
        x = -93242;
        count = 40;
        for (int i = 0; i < count; i++){
            x >>= 1; // Arithmetic shift Ьу 1
        }
        return x;
    }

    private int repeatedLogicalShift(int x, int count) {
        for (int i = 0; i < count; i++) {
            x >>>= 1; // Logical shift Ьу 1
        }
        return x;
    }
}
