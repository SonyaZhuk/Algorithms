package structures.graph.lakman.bfs;


import structures.graph.lakman.bfs.State;

/**
 * Graph.
 * <p>
 * Lakman p. 100
 */
public class Graph {

    public Node[] nodes;

    public class Node {
        public String name;
        public Node[] children;
        public State state;
    }

    public Node[] getNodes() {
        return this.nodes;
    }
}