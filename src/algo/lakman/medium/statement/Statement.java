package algo.lakman.medium.statement;

import java.util.Stack;

/**
 * Computes an arithmetic operation. O(N) time.
 * <p>
 * See Lakman p. 555
 */
public class Statement {
    public double compute(String sequence) {
        Stack<Double> numberStack = new Stack<>();
        Stack<Operator> operatorStack = new Stack<>();

        for (int i = 0; i < sequence.length(); i++) {
            int value = parseNumber(sequence, i);
            numberStack.push((double) value);

            i += Integer.toString(value).length();
            if (i >= sequence.length()) break;

            Operator op = parseOperator(sequence, i);
            collapseTop(op, numberStack, operatorStack);
            operatorStack.push(op);
        }
        collapseTop(Operator.BLANK, numberStack, operatorStack);
        return (numberStack.size() == 1 && operatorStack.size() == 0) ? numberStack.pop() : 0;
    }

    private void collapseTop(Operator futureTop, Stack<Double> numberStack, Stack<Operator> operatorStack) {
        while (operatorStack.size() >= 1 && numberStack.size() >= 2) {
            if (futureTop.priority() <= operatorStack.peek().priority()) {
                double second = numberStack.pop();
                double first = numberStack.pop();
                Operator op = operatorStack.pop();
                double collapsed = applyOp(first, op, second);
                numberStack.push(collapsed);
            } else break;
        }
    }

    private double applyOp(double left, Operator op, double right) {
        if (op == Operator.ADD) return left + right;
        else if (op == Operator.SUBTRACT) return left - right;
        else if (op == Operator.MULTIPLY) return left * right;
        else if (op == Operator.DIVIDE) return left / right;
        else return right;
    }

    private int parseNumber(String seq, int offset) {
        final StringBuilder sb = new StringBuilder();

        while (offset < seq.length() && Character.isDigit(seq.charAt(offset))) {
            sb.append(seq.charAt(offset));
            offset++;
        }

        return Integer.parseInt(sb.toString());
    }

    private Operator parseOperator(String sequence, int offset) {
        if (offset < sequence.length()) {
            char op = sequence.charAt(offset);
            switch (op) {
                case '+':
                    return Operator.ADD;
                case '-':
                    return Operator.SUBTRACT;
                case '*':
                    return Operator.MULTIPLY;
                case '/':
                    return Operator.DIVIDE;
            }
        }
        return Operator.BLANK;
    }

    public static void main(String[] args) {
        Statement task = new Statement();
        System.out.println(task.compute("2*3+5/6*3+15"));
    }
}
