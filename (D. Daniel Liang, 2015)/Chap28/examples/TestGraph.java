import java.util.Arrays;

/** Test Cases from text book */
public class TestGraph {

    public static void main(String[] args) {
        // -------------------------------Test (Graph) Data----------------------------

        String[] vertices = { "Seattle", "San Francisco", "Los Angeles", "Denver", "Kansas City", "Chicago", "Boston",
                "New York", "Atlanta", "Miami", "Dallas", "Houston" };
        // Edge array for graph in Figure 28.1
        int[][] edges = { { 0, 1 }, { 0, 3 }, { 0, 5 }, { 1, 0 }, { 1, 2 }, { 1, 3 }, { 2, 1 }, { 2, 3 }, { 2, 4 },
                { 2, 10 }, { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 4 }, { 3, 5 }, { 4, 2 }, { 4, 3 }, { 4, 5 }, { 4, 7 },
                { 4, 8 }, { 4, 10 }, { 5, 0 }, { 5, 3 }, { 5, 4 }, { 5, 6 }, { 5, 7 }, { 6, 5 }, { 6, 7 }, { 7, 4 },
                { 7, 5 }, { 7, 6 }, { 7, 8 }, { 8, 4 }, { 8, 7 }, { 8, 9 }, { 8, 10 }, { 8, 11 }, { 9, 8 }, { 9, 11 },
                { 10, 2 }, { 10, 4 }, { 10, 8 }, { 10, 11 }, { 11, 8 }, { 11, 9 }, { 11, 10 } };

        System.out.println("The array for Vertices:\n" + Arrays.toString(vertices) + "\n");

        System.out.println("The two-dimensional array for edges:");
        for (int[] r : edges) {
            System.out.print(Arrays.toString(r));
        }
        System.out.println("\n\n");

        // -----------------------------------------------------------------------------

        // Unweighted Graph
        Graph<String> graph1 = new UnweightedGraph<>(vertices, edges);
        System.out.println("The number of vertices in graph1: " + graph1.getSize());
        System.out.println("The vertex with index 1 is " + graph1.getVertex(1));
        System.out.println("The index for Miami is " + graph1.getIndex("Miami"));
        System.out.println("The edges for graph1:\n" + graph1.toString());

        // DFS Tree
        AbstractGraph.SearchTree dfs = graph1.depthFirstSearchTree(graph1.getIndex("Miami"));
        System.out.println("DFS1 from " + vertices[dfs.getRoot()] + ": \n  " + dfs.toString());
        System.out.println("    Path from Dallas to Root " + dfs.getPathToRoot(graph1.getIndex("Dallas")));
        System.out.println(
                "    Path from Dallas to Root " + dfs.pathToName(dfs.getPathToRoot(graph1.getIndex("Dallas"))));

        // BFS Tree
        AbstractGraph.SearchTree bfs = graph1.breadthFirstSearchTree(graph1.getIndex("Miami"));
        System.out.println("BFS1 from " + vertices[bfs.getRoot()] + ": \n  " + bfs.toString());
        System.out.println("    Path from Dallas to Root " + bfs.getPathToRoot(graph1.getIndex("Dallas")));
        System.out.println(
                "    Path from Dallas to Root " + bfs.pathToName(bfs.getPathToRoot(graph1.getIndex("Dallas"))));
        System.out.println("");

        // DFS Tree
        AbstractGraph.SearchTree dfs2 = graph1.depthFirstSearchTree(graph1.getIndex("Boston"));
        System.out.println("DFS2 from " + vertices[dfs2.getRoot()] + ": \n  " + dfs2.toString());
        System.out.println("    Path from Dallas to Root " + dfs2.getPathToRoot(graph1.getIndex("Dallas")));
        System.out.println(
                "    Path from Dallas to Root " + dfs2.pathToName(dfs2.getPathToRoot(graph1.getIndex("Dallas"))));

        // BFS Tree
        AbstractGraph.SearchTree bfs2 = graph1.breadthFirstSearchTree(graph1.getIndex("Boston"));
        System.out.println("BFS2 from " + vertices[bfs2.getRoot()] + ": \n  " + bfs2.toString());
        System.out.println("    Path from Dallas to Root " + bfs2.getPathToRoot(graph1.getIndex("Dallas")));
        System.out.println(
                "    Path from Dallas to Root " + bfs2.pathToName(bfs2.getPathToRoot(graph1.getIndex("Dallas"))));

    }

}
