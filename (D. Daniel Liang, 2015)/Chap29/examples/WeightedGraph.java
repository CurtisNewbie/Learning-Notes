import java.util.*;

public class WeightedGraph<E> extends AbstractGraph<E> {

    /** Initiate empty Weighted Graph */
    public WeightedGraph() {
        super();
    }

    /**
     * Construct Weighted Graph using a vertices array and edges array
     * 
     * @param v     vertices represented using array
     * @param edges edges represented using two-dimentional edges array. Wherein
     *              each row represents an edge, which contains three elements:
     *              {@code [0]} is the index of the first vertex, {@code [1]} is the
     *              index of the second vertex and {@code [3]} is the weight of the
     *              edge.
     */
    public WeightedGraph(E[] v, int[][] edges) {
        this();
        for (E e : v) {
            addVertex(e);
        }

        for (int[] e : edges) {
            addEdge(e[0], e[1], e[2]);
        }
    }

    /**
     * Construct Weighted Graph using a vertices List and edges List
     * 
     * @param v     vertices represented using List
     * @param edges edges represented using List
     */
    public WeightedGraph(List<E> v, List<WeightedEdge> edges) {
        this();
        for (E e : v) {
            addVertex(e);
        }
        for (WeightedEdge e : edges) {
            addEdge(e);
        }
    }

    /**
     * {@inheritDoc}. This method initiates and add a
     * {@code new WeightedEdge(vert1, vert2, 0)} with a weight of 0 .
     * 
     * @throws IllegalArgumentException if the indices of the vertices of the edge
     *                                  are illegal
     */
    @Override
    public boolean addEdge(int vert1, int vert2) {
        return addEdge(new WeightedEdge(vert1, vert2, 0));
    }

    /**
     * This method initiates and add a {@code new WeightedEdge(vert1, vert2, w)}
     * between vertices (vert1 and vert2).
     * 
     * @param vert1 vertex 1
     * @param vert2 vertex 2
     * @param w     weight of this edge
     * 
     * @throws IllegalArgumentException if the indices of the vertices of the edge
     *                                  are illegal
     */
    public boolean addEdge(int vert1, int vert2, int w) {
        return addEdge(new WeightedEdge(vert1, vert2, w));
    }

    /**
     * Get Weight of the specific edge
     * 
     * @param vert1 vertex 1 of the edge
     * @param vert2 vertex 2 of the edge
     * @throws Exception if there is no such edge in the graph
     */
    public double getWeight(int vert1, int vert2) throws Exception {
        var vert1List = neighbors.get(vert1);
        for (Edge e : vert1List)
            if (e.getVert2() == vert2)
                return ((WeightedEdge) e).getWeight();
        throw new Exception("Edge {" + vert1 + "," + vert2 + "} does not exist");
    }

    /** Display edges with weights */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("WeightedGraph: ");
        for (int i = 0; i < getSize(); i++) {
            sb.append(getVertex(i) + "[" + i + "]:{");
            for (Edge e : neighbors.get(i)) {
                sb.append("{" + e.getVert1() + "," + e.getVert2() + "," + ((WeightedEdge) e).getWeight() + "}");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * <P>
     * Get Minimum Spanning Tree rooted at vertex 0
     * </P>
     * <p>
     * This method uses Prim's algorithm. It first constructs a
     * {@code List<Integer> t} that stores the vertices in the spanning tree. At
     * first, this list {@code t} only has vertex {@code 0}, and then it repeatedly
     * checks the weights of edges that connect between the vertices in {@code t}
     * and those vertices that are not in {@code t}; adding the vertex (which's edge
     * has the minimum weight among those edges). This process repeats until all
     * unincluded vertices are added. This algorithm is greedy.
     * </p>
     * 
     * @return {@code MST} rooted at vertex 0.
     */
    public MST getMST() {
        return getMST(0);
    }

    /**
     * <p>
     * Get Minimum Spanning Tree rooted at vertex {@code i}
     * </p>
     * <p>
     * This method uses Prim's algorithm. It first constructs a
     * {@code List<Integer> t} that stores the vertices in the spanning tree. At
     * first, this list {@code t} only has vertex {@code i}, and then it repeatedly
     * checks the weights of edges that connect between the vertices in {@code t}
     * and those vertices that are not in {@code t}; adding the vertex (which's edge
     * has the minimum weight among those edges). This process repeats until all
     * unincluded vertices are added. This algorithm is greedy.
     * </p>
     * 
     * 
     * @param i index of the vertex
     * @return {@code MST} rooted at vertex {@code i}.
     * @throws IllegalArgumentException if the given index {@code i} is ouf of bound
     */
    public MST getMST(int i) throws IllegalArgumentException {
        if (i < 0 || i > getSize())
            throw new IllegalArgumentException("There is no vertex " + i);

        // vertices in the spanning tree t (i.e., the vertices that are added)
        List<Integer> t = new ArrayList<>();

        // record the costs/weights of the edges, set all vertices' costs except vertex
        // i as infinity. This is used to compare which edge has the least weight.
        double[] costs = new double[getSize()];

        // The Infinity value is perfect for this practical use, as we are trying to
        // find the minimum value, and it's not sensible to assign it a speicific value.
        costs[i] = 0; // note that 0 is less than POSITIVE_INFINITY
        for (int j = 0; j < getSize(); j++)
            if (j != i)
                costs[j] = Double.POSITIVE_INFINITY;

        // check parents of added veretices
        int[] parents = new int[getSize()];
        parents[i] = -1; // root doesn't have parent

        // total weight of the MST
        double totalWeight = 0;

        // here start adding vertices in list t, i.e., expand t
        while (t.size() < getSize()) {
            // while there are vertices not added

            // find vertex of smallest cost that is not in t.
            int vert1 = -1;
            double minCost = Double.POSITIVE_INFINITY;
            for (int j = 0; j < getSize(); j++) {
                if (!t.contains(j) && costs[j] < minCost) {
                    vert1 = j;
                    minCost = costs[j];
                }
            }

            // add found vert1 and update total weight
            t.add(vert1);
            totalWeight += costs[vert1];

            // find adjacent vertices of vert1 that are not added, and update their
            // corresponding costs
            for (Edge e : neighbors.get(vert1)) {
                // positive finite values are always less than postive infinity
                if (!t.contains(e.getVert2()) && costs[e.getVert2()] > ((WeightedEdge) e).getWeight()) {
                    // update weights and parents
                    costs[e.getVert2()] = ((WeightedEdge) e).getWeight();
                    parents[e.getVert2()] = vert1;
                }
            }
        }
        return new MST(i, parents, t, totalWeight);
    }

    /**
     * <p>
     * Get a Shortest-Path Tree rooted at vertex 0. I.e., vertex 0 is the source.
     * </p>
     * <p>
     * This method uses the Dijkstra's algorithm. For example, there are three edges
     * v1-> v2, v1-> v3, and v3-v2. Their weights are 4, 1, 2 respectively. So, if
     * the source is v1, this opeartion will be like this: (costs[v1,v2,v3])<br>
     * <br>
     * 1) costs[0, INF, INF], go to v1.<br>
     * <br>
     * 2) update costs of v2 and v3, costs[0, 4, 1], we are now at v1, and we go to
     * v3. <br>
     * <br>
     * 3) update costs of v2, costs[0, 4, 1], update cost of v2 to (costs[v3] +
     * v3->v2) as we are now moving from v3 to v2 and this path has less weight. The
     * previous recorded costs of v2 is the cost moving from v1 to v2. So the costs
     * will be udpated to be costs[0, 3, 2].<br>
     * <br>
     * This is essentially the process of keeping track of how costs of each
     * vertices are updated. Or, i.e., keeping records of the shortest weight from
     * source to each vertices <br>
     * </p>
     * 
     * @return {@code SPT} rooted at 0
     */
    public SPT getSPT() {
        return getSPT(0);
    }

    /**
     * <p>
     * Get a Shortest-Path Tree rooted at vertex {@code i}. I.e., vertex {@code i}
     * is the source.
     * </p>
     * <p>
     * This method uses the Dijkstra's algorithm. For example, there are three edges
     * v1-> v2, v1-> v3, and v3-v2. Their weights are 4, 1, 2 respectively. So, if
     * the source is v1, this opeartion will be like this: (costs[v1,v2,v3])<br>
     * <br>
     * 1) costs[0, INF, INF], go to v1.<br>
     * <br>
     * 2) update costs of v2 and v3, costs[0, 4, 1], we are now at v1, and we go to
     * v3.<br>
     * <br>
     * 3) update costs of v2, costs[0, 4, 1], update cost of v2 to (costs[v3] +
     * v3-v2) as we are now moving from v3 to v2 and this path has less weight. The
     * previous recorded costs of v2 is the cost moving from v1 to v2. So the costs
     * will be udpated to be costs[0, 3, 2].<br>
     * <br>
     * This is essentially the process of keeping track of how costs of each
     * vertices are updated. Or, i.e., keeping records of the shortest weight from
     * source to each vertices <br>
     * 
     * @param i index of vertex i
     * @return {@code SPT} rooted at {@code i}
     */
    public SPT getSPT(int i) {
        // the cost from each vertex to the source
        double[] costs = new double[getSize()];
        for (int j = 0; j < costs.length; j++) {
            // set all costs to infinity
            costs[j] = Double.POSITIVE_INFINITY;
        }
        // cost of source itself should be 0
        costs[i] = 0;

        int[] parents = new int[getSize()];
        parents[i] = -1;

        // t stores the vertices whose shortest paths are found
        List<Integer> t = new ArrayList<>();

        // incrementally find shortest path, i.e., expand t
        while (t.size() < getSize()) {
            int vert1 = -1;
            double minCost = Double.POSITIVE_INFINITY;
            for (int j = 0; j < costs.length; j++) {
                if (!t.contains(j) && costs[j] < minCost) {
                    vert1 = j;
                    minCost = costs[j];
                }
            }
            t.add(vert1);

            // adjust the costs of the adjacent vertices of vert1
            for (Edge e : neighbors.get(vert1)) {
                /*
                 * if the weight of the current path is less than original calculated cost for
                 * the previous vertex that is added into t, updates it. I.e., update it if
                 * current path is better, else the costs of it is unchanged. If costs[vert2] is
                 * not infinity, and it is not updated, that means connecting from the previous
                 * parents to this vert2 has shorter path.
                 */
                if (!t.contains(e.getVert2()) && costs[e.getVert2()] > costs[vert1] + ((WeightedEdge) e).getWeight()) {
                    // when this vertex is not added, and the current path is shorter than previous
                    // one in terms of costs
                    costs[e.getVert2()] = costs[vert1] + ((WeightedEdge) e).getWeight();
                    parents[e.getVert2()] = vert1;
                }
            }
        }
        return new SPT(i, parents, t, costs);
    }

    /**
     * Minimum Spanning Tree. An inner class in WeightedGraph. A normal spanning
     * tree except that it records the total weight of the tree, and it is meant to
     * be a tree that reaches all vertices in the graph.
     */
    public class MST extends SearchTree {

        protected double totalWeight;

        /**
         * Instantiate MST.
         * 
         * @param r  root
         * @param p  parents
         * @param so search order
         * @param tw total weight
         */
        public MST(int r, int[] p, List<Integer> so, double tw) {
            super(r, p, so);
            this.totalWeight = tw;
        }

        /**
         * Get total weight of the MST
         * 
         * @return total weight
         */
        public double getTotalWeight() {
            return this.totalWeight;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < parentOfVertices.length; i++) {
                if (i != root)
                    sb.append(" (" + vertices.get(parentOfVertices[i]) + "->" + vertices.get(i) + ") ");
            }
            return sb.toString();
        }

    }

    /**
     * Shortest Path Tree. An inner class in WeightedGraph. A normal spanning tree
     * except that it records the total weight of the tree, and it is meant to be a
     * tree that helps find the shortest path between two vertices. It's
     * unnecessarily reaching all the vertice in the graph.
     */
    public class SPT extends SearchTree {

        /** costs[i] indicates that the cost from i to the source (root) */
        protected double[] costs;

        /**
         * Instantiate Shortest Path Tree
         * 
         * @param r     root
         * @param p     parents
         * @param so    search order
         * @param costs costs for each vertices to the source
         */
        public SPT(int r, int[] p, List<Integer> so, double[] costs) {
            super(r, p, so);
            this.costs = costs;
        }

        /**
         * Get cost from vertex v to source, vice versa
         * 
         * @param v index of vertex
         * @return cost from vertex v to source
         */
        public double getCosts(int v) {
            return costs[v];
        }

        public String getAllPathsToRoot() {
            StringBuilder sb = new StringBuilder("All shortest paths to " + vertices.get(root) + " are:\n");
            for (int i = 0; i < costs.length; i++) {
                if (i != root)
                    sb.append("    " + pathToName(getPathToRoot(i)) + " Costs:" + getCosts(i) + "\n");
            }
            return sb.toString();
        }

        public List<String> getPath(int i) {
            return pathToName(getPathToRoot(i));
        }
    }

}