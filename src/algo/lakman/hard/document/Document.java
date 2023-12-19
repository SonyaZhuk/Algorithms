package algo.lakman.hard.document;

import java.util.Set;

/**
 * Splits document (string sentence) by whitespace with using dictionary words. O(N^2) time complexity.
 * <p>
 * See Lakman p. 600
 */
public class Document {

    public String bestSplit(Set<String> dictionary, String sentence) {
        ParseResult[] memo = new ParseResult[sentence.length()];
        ParseResult result = split(dictionary, sentence, 0, memo);
        return result == null ? null : result.getParsedData();
    }

    private ParseResult split(Set<String> dictionary, String sentence, int start, ParseResult[] memo) {
        if (start >= sentence.length())
            return new ParseResult(0, "");
        if (memo[start] != null)
            return memo[start];

        int bestInvalidSymbols = Integer.MAX_VALUE;
        String bestParsing = null;
        String partial = "";
        int index = start;


        while (index < sentence.length()) {
            char c = sentence.charAt(index);
            partial += c;
            int invalidSymbols = dictionary.contains(partial) ? 0 : partial.length();
            if (invalidSymbols < bestInvalidSymbols) {
                /* Вставить пробел и выполнить рекурсию. Если вариант лучше текущего, заменить текущий лучший вариант. */
                ParseResult result = split(dictionary, sentence, index + 1, memo);
                if (invalidSymbols + result.getCountInvalidSymbols() < bestInvalidSymbols) {
                    bestInvalidSymbols = invalidSymbols + result.getCountInvalidSymbols();
                    bestParsing = partial + " " + result.getParsedData();
                    if (bestInvalidSymbols == 0) break; //Optimization
                }
            }
            index++;
        }

        memo[start] = new ParseResult(bestInvalidSymbols, bestParsing);
        return memo[start];
    }
}
