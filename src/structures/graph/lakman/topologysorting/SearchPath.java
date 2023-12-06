package structures.graph.lakman.topologysorting;

import java.util.ArrayList;

/**
 * Topology sorting for Graph, O(P + D), where P - projects, D - dependencies
 * <p>
 * Lakman p. 259
 */
public class SearchPath {

    public Project[] findBuildOrder(String[] projects, String[][] dependencies) {
        Graph graph = buildGraph(projects, dependencies);
        return orderProjects(graph.getNodes());
    }

    /* Построить граф с добавлением ребра (а, b), если b зависит от а.
       Предполагается, что зависимость (а, b) означает, что b зависит
       от а, и проект а должен быть построен перед b. */
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

    /* Получение списка проектов в правильном порядке построения. */
    private Project[] orderProjects(ArrayList<Project> projects) {
        Project[] order = new Project[projects.size()];
        /*Сначала добавляются "корни".*/
        int endOfList = addNonDependent(order, projects, 0);
        int toBeProcessed = 0;
        while (toBeProcessed < order.length) {
            Project current = order[toBeProcessed];

            /* Имеется циклическая зависимость, так
             * проектов с нулем зависимостей. */
            if (current == null) {
                return null;
            }

            /* Удаление зависимостей от текущего узла. */
            ArrayList<Project> children = current.getChildren();

            for (Project child : children) {
                child.decrementDependencies();
            }

            /* Добавление дочерних узлов без зависимостей. */
            endOfList = addNonDependent(order, children, endOfList);
            toBeProcessed++;
        }
        return order;
    }

    /* Вспомогательная функция для вставки проектов с нулем зависимостей
     * в массив, начиная с индекса offset. */
    private int addNonDependent(Project[] order, ArrayList<Project> projects, int offset) {
        for (Project project : projects) {
            if (project.getNumberDependencies() == 0) {
                order[offset] = project;
                offset++;
            }
        }
        return offset;
    }
}
