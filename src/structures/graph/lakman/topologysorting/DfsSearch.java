package structures.graph.lakman.topologysorting;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Dfs for the Graph path with using State and topology sorting, O(P + D), where P - projects, D - dependencies
 * <p>
 * Lakman p. 262
 */
public class DfsSearch {

    public Stack<Project> findBuildOrder(String[] projects, String[][] dependencies) {
        Graph graph = buildGraph(projects, dependencies);
        return orderProjects(graph.getNodes());
    }
    private Graph buildGraph(String[] projects, String[][] dependencies) {
        Graph graph = new Graph();
        for (String project : projects) {
            graph.getOrCreateNode(project);
        }
        for (String[] dependency : dependencies) {
            String first = dependency[0];
            String second = dependency[1];
            graph.addEdge(first, second);
        }
        return graph;
    }
    private Stack<Project> orderProjects(ArrayList<Project> projects) {
        Stack<Project> stack = new Stack<>();
        for (Project project : projects) {
            if (project.getState() == State.BLANK) {
                if (!doDFS(project, stack)) {
                    return null;
                }
            }
        }
        return stack;
    }
    private boolean doDFS(Project project, Stack<Project> stack) {
        if (project.getState() == State.PARTIAL) {
            return false;
        }

        if (project.getState() == State.BLANK) {
            project.setState(State.PARTIAL);
            ArrayList<Project> children = project.getChildren();
            for (Project child : children) {
                if (!doDFS(child, stack)) {
                    return false;
                }
            }
            project.setState(State.COMPLETE);
            stack.push(project);
        }
        return true;
    }
}

