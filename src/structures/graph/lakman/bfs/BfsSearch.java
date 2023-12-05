package structures.graph.lakman.bfs;

import structures.graph.lakman.bfs.Graph.Node;

import java.util.LinkedList;

/**
 * Trees and Graphs.
 * <p>
 * See Lakman p. 248
 */
public class BfsSearch {

    public boolean search(Graph g, Node start, Node end) {
        if (start == end) return true;

        LinkedList<Node> queue = new LinkedList<>();

        for (Node u : g.getNodes()) {
            u.state = State.UNVISITED;
        }

        start.state = State.VISITING;
        queue.add(start);
        Node u;

        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            if (u != null) {
                for (Node v : u.children) { //Adjacent
                    if (v.state == State.UNVISITED) {
                        if (v == end) {
                            return true;
                        }
                        v.state = State.VISITING;
                        queue.add(v);
                    }
                }
                u.state = State.VISITED;
            }
        }

        return false;
    }
}
