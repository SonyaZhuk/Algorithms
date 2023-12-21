package algo.lakman.hard.docsimilarity;

import java.util.Objects;

public class DocPair {
    private int doc1, doc2;

    public DocPair(int d1, int d2) {
        doc1 = d1;
        doc2 = d2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocPair docPair = (DocPair) o;
        return doc1 == docPair.doc1 && doc2 == docPair.doc2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(doc1, doc2);
    }

    public int getDoc1() {
        return doc1;
    }

    public int getDoc2() {
        return doc2;
    }
}
