package algo.lakman.hard.docsimilarity;

import java.util.*;

public class Solution {

    /**
     * Finds a pair of docs with similarity > 0. Brute force.
     * O(D^2* W) time complexity, D - count of arrays, W - arr.length
     * <p>
     * See Lakman p. 657
     */
    public Map<DocPair, Double> computeSimilarities(List<Document> documents) {
        final Map<DocPair, Double> similarities = new HashMap<>();
        for (int i = 0; i < documents.size(); i++) {
            for (int j = i + 1; j < documents.size(); j++) {
                Document doc1 = documents.get(i);
                Document doc2 = documents.get(j);
                double sim = computeSimilarity(doc1, doc2);
                if (sim > 0) {
                    DocPair pair = new DocPair(doc1.getId(), doc2.getId());
                    similarities.put(pair, sim);
                }
            }
        }
        return similarities;
    }

    private double computeSimilarity(Document doc1, Document doc2) {
        int intersection = 0;
        Set<Integer> set1 = new HashSet<>();
        set1.addAll(doc1.getWords());
        for (int word : doc2.getWords()) {
            if (set1.contains(word)) {
                intersection++;
            }
        }
        double union = doc1.size() + doc2.size() - intersection;
        return intersection / union;
    }

    /**
     * Finds a pair of docs with similarity > 0.
     * <p>
     * See Lakman p. 661
     */
    public Map<DocPair, Double> computeSimilarities(Map<Integer, Document> documents) {
        final Map<Integer, List<Integer>> wordToDocs = groupWords(documents);
        final Map<DocPair, Double> similarities = computeIntersections(wordToDocs);
        adjustToSimilarities(documents, similarities);
        return similarities;
    }

    private Map<Integer, List<Integer>> groupWords(Map<Integer, Document> documents) {
        final Map<Integer, List<Integer>> wordToDocs = new HashMap<>();

        for (Document doc : documents.values()) {
            ArrayList<Integer> words = doc.getWords();
            for (int word : words) {
                List<Integer> list = wordToDocs.containsKey(word) ? wordToDocs.get(word) : new ArrayList<>();
                list.add(doc.getId());
                wordToDocs.put(word, list);
            }
        }
        return wordToDocs;
    }


    /* Вычисление пересечения документов: перебор каждого списка документов
       и каждой пары в этом списке и увеличение счетчика для каждой пары. */
    private Map<DocPair, Double> computeIntersections(Map<Integer, List<Integer>> wordToDocs) {
        final Map<DocPair, Double> similarities = new HashMap<>();
        final Set<Integer> words = wordToDocs.keySet();
        for (int word : words) {
            List<Integer> docs = wordToDocs.get(word);
            Collections.sort(docs);
            for (int i = 0; i < docs.size(); i++) {
                for (int j = i + 1; j < docs.size(); j++) {
                    increment(similarities, docs.get(i), docs.get(j));
                }
            }
        }
        return similarities;
    }

    /* Увеличение размера пересечения каждой пары документов. */
    private void increment(Map<DocPair, Double> similarities, int doc1, int doc2) {
        final DocPair pair = new DocPair(doc1, doc2);
        if (!similarities.containsKey(pair)) {
            similarities.put(pair, 1.0);
        } else {
            similarities.put(pair, similarities.get(pair) + 1);
        }
    }


    private void adjustToSimilarities(Map<Integer, Document> documents,
                                      final Map<DocPair, Double> similarities) {
        for (Map.Entry<DocPair, Double> entry : similarities.entrySet()) {
            final DocPair pair = entry.getKey();
            final Double intersection = entry.getValue();
            final Document doc1 = documents.get(pair.getDoc1());
            final Document doc2 = documents.get(pair.getDoc2());
            double union = (double) doc1.size() + doc2.size() - intersection;
            entry.setValue(intersection / union);
        }
    }

    /**
     * Finds a pair of docs with similarity > 0.
     * <p>
     * See Lakman p. 664
     */
    public Map<DocPair, Double> computeSimilaritiesI(Map<Integer, Document> documents) {
        List<Element> elements = sortWords(documents);
        Map<DocPair, Double> similarities = computeIntersections(elements);
        adjustToSimilarities(documents, similarities);
        return similarities;
    }

    /* Все слова объединяются в список, сортируемый по словам и документам. */
    private List<Element> sortWords(Map<Integer, Document> docs) {
        List<Element> elements = new ArrayList<>();
        for (Document doc : docs.values()) {
            ArrayList<Integer> words = doc.getWords();
            for (int word : words) {
                elements.add(new Element(word, doc.getId()));
            }
        }
        Collections.sort(elements);
        return elements;
    }

    /* Вычисление сходства на основании пересечения. */
    private Map<DocPair, Double> computeIntersections(List<Element> elements) {
        final HashMap<DocPair, Double> similarities = new HashMap<>();

        for (int i = 0; i < elements.size(); i++) {
            final Element left = elements.get(i);
            for (int j = i + 1; j < elements.size(); j++) {
                final Element right = elements.get(j);
                if (left.getWord() != right.getWord()) break;
                increment(similarities, left.getDocument(), right.getDocument());
            }
        }
        return similarities;
    }
}
