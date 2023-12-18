//package algo.lakman.hard.popularname;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Counts the most freq names. O(N + S) time, where N - names, S - synonyms.
// * <p>
// * See Lakman p. 577
// */
//public class PopularNamesGraph {
//
//    public Map<String, Integer> mostPopular(HashMap<String, Integer> names, String[][] synonyms) {
//        /* Соэдание данных. */
//        Graph graph = constructGraph(names);
//        connectEdges(graph, synonyms);
//
//        /* Поиск компонентов. */
//        return getTrueFrequencies(graph);
//    }
//
//
//    /* Добавление всех имен в качестве узлов графа. */
//    private Graph constructGraph(HashMap<String, Integer> names) {
//        Graph graph = new Graph();
//        for (Map.Entry<String, Integer> entry : names.entrySet()) {
//            String name = entry.getKey();
//            int frequency = entry.getValue();
//            graph.createNode(name, frequency);
//        }
//        return graph;
//    }
//
//    /* Соединение эквивалентных вариантов. */
//    private void connectEdges(Graph graph, String[][] synonyms) {
//        for (String[] entry : synonyms) {
//            String name1 = entry[0];
//            String name2 = entry[1];
//            graph.addEdge(name1, name2);
//
//        }
//    }
//
//
//    /* Выполнить поиск в глубину для каждого компонента. Если узел посещался ранее, его компонент уже обработан. */
//    private Map<String, Integer> getTrueFrequencies(Graph graph) {
//        HashMap<String, Integer> rootNames = new HashMap<>();
//        for (GraphNode node : graph.getNodes()) {
//            if (!node.isVisited()) {
//                int frequency = getComponentFrequency(node);
//                String name = node.getName();
//                rootNames.put(name, frequency);
//            }
//        }
//        return rootNames;
//    }
//
//
//    /* Провести поиск в глубину для нахождения суммарной частоты
//     * компонента и пометить каждый узел как посещенный.*/
//    private int getComponentFrequency(GraphNode node) {
//        if (node.isVisited()) return 0;
//
//        node.setIsVisited(true);
//        int sum = node.getFrequency();
//        for (GraphNode child : node.getNeighbors()) {
//            sum += getComponentFrequency(child);
//        }
//        return sum;
//    }
//}
