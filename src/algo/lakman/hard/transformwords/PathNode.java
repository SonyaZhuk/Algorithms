package algo.lakman.hard.transformwords;

import java.util.LinkedList;

public class PathNode {
    private String word;
    private PathNode previousNode;

    public PathNode(String word, PathNode previous) {
        this.word = word;
        this.previousNode = previous;
    }

    public String getWord() {
        return this.word;
    }

    public LinkedList<String> collapse(boolean startsWithRoot) {
        LinkedList<String> path = new LinkedList<>();
        PathNode node = this;
        while (node != null){
            if (startsWithRoot) {
                path.addLast(node.word);
            } else {
                path.addFirst(node.word);
            }
            node = node.previousNode;
        }
        return path;
    }
}
