package algo.lakman.medium.statement;

public enum Operator {

    ADD {
        @Override
        int priority() {
            return 1;
        }
    },
    SUBTRACT {
        @Override
        int priority() {
            return 1;
        }
    },
    MULTIPLY {
        @Override
        int priority() {
            return 2;
        }
    },
    DIVIDE {
        @Override
        int priority() {
            return 2;
        }
    },
    BLANK {
        @Override
        int priority() {
            return 0;
        }
    };

    abstract int priority();
}
