package algo.lakman.hard.document;

public class ParseResult {
    private int countInvalidSymbols;
    private String parsedData;

    public ParseResult(int countInvalidSymbols, String parsedData) {
        this.countInvalidSymbols = countInvalidSymbols;
        this.parsedData = parsedData;
    }

    public int getCountInvalidSymbols() {
        return countInvalidSymbols;
    }

    public String getParsedData() {
        return parsedData;
    }
}
