import java.util.*;

public abstract class AbstractGraph<E> implements Graph<E> {

    private List<E> vertices;
    /** Adjacency List for Edges */
    private List<List<Edge>> neighbors;

    /** Empty Graph */
    AbstractGraph() {
        this.vertices = new ArrayList<>();
        this.neighbors = new ArrayList<>();
    }

    /**
     * Construct Graph using a vertices array and edges array
     * 
     * @param vertices vertices represented using array
     * @param edges    edges represented using two-dimentional edges array
     */
    AbstractGraph(E[] vertices, int[][] edges) {
        this.vertices = new ArrayList<>();
        this.neighbors = new ArrayList<>();
        for (E e : vertices)
            addVertex(e);
        addEdges(edges);
    }

    /**
     * Construct Graph using a vertices List and edges List
     * 
     * @param vertices vertices represented using List
     * @param edges    edges represented using two-dimentional edges List
     */
    AbstractGraph(List<E> vesrtices, List<Edge> edges) {
        this.vertices = new ArrayList<>();
        this.neighbors = new ArrayList<>();
        for (E e : vertices)
            addVertex(e);
        addEdges(edges);
    }

    /**
     * Add a number of edges, creating the adjacency list for edges
     * 
     * @param edges edges represented with two-dimentional array
     * 
     */
    private void addEdges(int[][] edges) {
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1]);
        }
    }

    /**
     * Add a number of edges, creating the adjacency list for edges
     * 
     * @param edges edges represented with two-dimentional array
     * 
     */
    private void addEdges(List<Edge> edges) {
        for (Edge edge : edges) {
            addEdge(edge.getVert1(), edge.getVert2());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @exception IllegalArgumentException if the indices of the vertices of the
     *                                     edge are illegal
     */
    @Override
    public boolean addEdge(int vert1, int vert2) {
        return addEdge(new Edge(vert1, vert2));
    }

    /**
     * {@inheritDoc}
     *
     * @exception IllegalArgumentException if the indices of the vertices of the
     *                                     edge are illegal
     */
    @Override
    public boolean addEdge(Edge edge) {
        // validate
        if (edge.getVert1() < 0 && edge.getVert1() > getSize() - 1) {
            throw new IllegalArgumentException("Illegal Index at: " + edge.getVert1());
        }

        if (edge.getVert2() < 0 && edge.getVert2() > getSize() - 1) {
            throw new IllegalArgumentException("Illegal Index at: " + edge.getVert2());
        }

        // adjacent edges share the same vertex (vert1), which connects vert1 and
        // vert2,
        // for vertex vert1:
        if (edge.getVert1() > neighbors.size()) {

        }
        var edgesForVertex1 = neighbors.get(edge.getVert1());
        if (edgesForVertex1.contains(edge))
            return false;
        else {
            // add it to the adjacency List
            edgesForVertex1.add(edge);
            return true;
        }
    }

    @Override
    public int getSize() {
        return vertices.size();
    }

    @Override
    public List<E> getVertices() {
        return (List<E>) new ArrayList<E>(vertices);
    }

    @Override
    public boolean addVertex(E e) {
        if (!vertices.contains(e)) {
            vertices.add(e);
            neighbors.add(new ArrayList<Edge>());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public E getVertex(int i) {
        return vertices.get(i);
    }

    @Override
    public int getIndex(E e) {
        return vertices.indexOf(e);
    }

    @Override
    public List<Integer> getNeighbors(int i) {
        List<Integer> n = new ArrayList<>();
        // get the corresponding adjacent vertex of the vertex (of index i)
        for (Edge e : neighbors.get(i)) {
            n.add(e.getVert2());
        }
        return n;
    }

    @Override
    public int getDegree(int i) {
        return neighbors.get(i).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Adjacency List for Edges\n");
        for (int i = 0; i < neighbors.size(); i++) {
            sb.append("    For ([" + i + "]" + vertices.get(i) + ") [");
            for (Edge e : neighbors.get(i)) {
                // print the adjacent edges for edge[i]
                sb.append("(" + e.getVert1() + ", " + e.getVert2() + ")");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    /** Breadth first search tree or depth first search tree */
    public class SearchTree {

    }

    public class Edge {

        private int vert1;
        private int vert2;

        public Edge(int vert1, int vert2) {
            this.vert1 = vert1;
            this.vert2 = vert2;
        }

        public boolean equals(Edge e) {
            return this.getVert1() == e.getVert1() && this.getVert2() == e.getVert2();
        }

        public int getVert1() {
            return vert1;
        }

        public int getVert2() {
            return vert2;
        }

    }

}