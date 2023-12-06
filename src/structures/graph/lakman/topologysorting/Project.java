package structures.graph.lakman.topologysorting;

import java.util.ArrayList;
import java.util.HashMap;

public class Project {
    private ArrayList<Project> children = new ArrayList<>();
    private HashMap<String, Project> map = new HashMap<>();
    private String name;
    private int dependencies = 0;

    public Project(String n) {
        name = n;
    }
    public void addNeighbor(Project node) {
        if (!map.containsKey(node.getName())) {
            children.add(node);
            node.incrementDependencies();
        }
    }
    public void incrementDependencies() {
        dependencies++;
    }

    public void decrementDependencies() {
        dependencies--;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Project> getChildren() {
        return children;
    }

    public int getNumberDependencies() {
        return dependencies;
    }


    //state is using only for the second solution
    private State state = State.BLANK;

    public State getState() {
        return state;
    }

    public void setState(State st) {
        state = st;
    }
}
