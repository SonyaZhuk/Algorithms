package algo.lakman.hard.docsimilarity;

public class Element implements Comparable<Element> {
    private int word, document;

    public Element(int word, int document) {
        this.word = word;
        this.document = document;
    }

    public int compareTo(Element e) {
        if (word == e.word) {
            return document - e.document;
        }
        return word - e.word;
    }

    public int getWord() {
        return word;
    }

    public int getDocument() {
        return document;
    }
}
