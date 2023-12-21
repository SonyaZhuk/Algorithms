package algo.lakman.hard.transformwords;

import java.util.*;

public class Solution {

    /**
     * Finds a words sequence path that can be obtained
     * from one word to another with changing only the one letter during a one step.
     * Each word for every step have to be present in dictionary.
     * <p>
     * See Lakman p. 638
     */
    public List<String> transform(String start, String stop, Set<String> dictionary) {
        final HashSet<String> visited = new HashSet<>();
        return transform(visited, start, stop, dictionary);
    }

    private LinkedList<String> transform(Set<String> visited, String startWord,
                                         String stopWord, Set<String> dictionary) {

        if (visited.contains(startWord) || !dictionary.contains(startWord)) return null;

        if (startWord.equals(stopWord)) {
            final LinkedList<String> path = new LinkedList<>();
            path.add(startWord);
            return path;
        }

        visited.add(startWord);
        final List<String> words = wordsOneAway(startWord);

        for (String word : words) {
            final LinkedList<String> path = transform(visited, word, stopWord, dictionary);
            if (path != null) {
                path.addFirst(startWord);
                return path;
            }
        }
        return null;
    }

    private List<String> wordsOneAway(String word) {
        final List<String> words = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'а'; c <= 'z'; c++) {
                final String w = word.substring(0, i) + c + word.substring(i + 1);
                words.add(w);
            }
        }
        return words;
    }


    /**
     * Finds a words sequence path that can be obtained
     * from one word to another with changing only the one letter during a one step.
     * Each word for every step have to be present in dictionary.
     * <p>
     * See Lakman p. 638
     */
    public LinkedList<String> transform(String startWord, String stopWord, String[] words) {
        HashMap<String, List<String>> wildcardToWordList = getWildcardToWordList(words);

        BFSData sourceData = new BFSData(startWord);
        BFSData destData = new BFSData(stopWord);

        while (!sourceData.isFinished() && !destData.isFinished()) {
            /* Поиск от начального узла. */
            String collision = searchLevel(wildcardToWordList, sourceData, destData);
            if (collision != null) {
                return mergePaths(sourceData, destData, collision);
            }

            /* Поиск от конечного узла. */
            collision = searchLevel(wildcardToWordList, destData, sourceData);
            if (collision != null) {
                return mergePaths(sourceData, destData, collision);
            }
        }
        return null;
    }

    /* Вставка в хеш-таблицу отображений вида "шаблон->слово". */
    private HashMap<String, List<String>> getWildcardToWordList(String[] words) {
        HashMap<String, List<String>> wildcardToWords = new HashMap<>();
        for (String word : words) {
            ArrayList<String> linked = getWildcardRoots(word);
            for (String linkedWord : linked) {
                List<String> curr = wildcardToWords.containsKey(linkedWord) ?
                        wildcardToWords.get(linkedWord) : new ArrayList<>();
                curr.add(word);
                wildcardToWords.put(linkedWord, curr);
            }
        }
        return wildcardToWords;
    }

    /* Получение слов, находящихся на расстоянии одного изменения. */
    private ArrayList<String> getValidLinkedWords(String word, HashMap<String, List<String>> wildcardToWords) {
        final ArrayList<String> wildcards = getWildcardRoots(word);
        final ArrayList<String> linkedWords = new ArrayList<>();
        for (String wildcard : wildcards) {
            List<String> words = wildcardToWords.get(wildcard);
            for (String linkedWord : words) {
                if (!linkedWord.equals(word)) {
                    linkedWords.add(linkedWord);
                }
            }
        }
        return linkedWords;
    }

    /* Получение списка шаблонов, связанных со словом. */
    private ArrayList<String> getWildcardRoots(String w) {
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < w.length(); i++) {
            String word = w.substring(0, i) + "_" + w.substring(i + 1);
            words.add(word);
        }
        return words;
    }


    /* Поиск на один уровень и возвращение информации о коллизиях. */
    private String searchLevel(HashMap<String, List<String>> wildcardToWordlist,
                               BFSData primary, BFSData secondary) {

        /* Поиск проводится только на один уровень. Подсчитать количество
           узлов на уровне primary и обработать зто количество узлов.
           Узлы продолжают добавляться в конце. */
        int count = primary.getToVisit().size();
        for (int i = 0; i < count; i++) {
            /* Извлечение первого узла. */
            PathNode pathNode = primary.getToVisit().poll();
            String word = pathNode.getWord();

            /* Проверка посещения узла. */
            if (secondary.visited.containsKey(word))
                return pathNode.getWord();

            /* Добавление друзей в очередь. */
            ArrayList<String> words = getValidLinkedWords(word, wildcardToWordlist);
            for (String w : words) {
                if (!primary.visited.containsKey(w)) {
                    PathNode next = new PathNode(w, pathNode);
                    primary.visited.put(w, next);
                    primary.getToVisit().add(next);
                }
            }
        }
        return null;
    }

    private LinkedList<String> mergePaths(BFSData bfsl, BFSData bfs2, String connection) {
        PathNode end1 = bfsl.visited.get(connection); // eпd1 -> начало
        PathNode end2 = bfs2.visited.get(connection); // епd2 -> конец
        LinkedList<String> pathOne = end1.collapse(false); // в прямом направлении
        LinkedList<String> pathTwo = end2.collapse(true); // в обратном направлении
        pathTwo.removeFirst(); // Удаление связи
        pathOne.addAll(pathTwo); // Добавление второго пути
        return pathOne;
    }
}