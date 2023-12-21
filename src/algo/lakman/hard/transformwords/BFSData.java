package algo.lakman.hard.transformwords;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BFSData {
    private Queue<PathNode> toVisit = new LinkedList<>();
    public Map<String, PathNode> visited = new HashMap<>();

    public BFSData(String root) {
        PathNode sourcePath = new PathNode(root, null);
        toVisit.add(sourcePath);
        visited.put(root, sourcePath);
    }

    public boolean isFinished() {
        return toVisit.isEmpty();
    }

    public Queue<PathNode> getToVisit() {
        return toVisit;
    }
}
