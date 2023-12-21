package algo.lakman.hard.docsimilarity;

import java.util.ArrayList;

public class Document {
    private ArrayList<Integer> words;
    private int docId;

    public Document(int docId, ArrayList<Integer> words) {
        this.words = words;
        this.docId = docId;
    }

    public ArrayList<Integer> getWords() {
        return words;
    }

    public int getId() {
        return docId;
    }

    public int size() {
        return words == null ? 0 : words.size();
    }
}
