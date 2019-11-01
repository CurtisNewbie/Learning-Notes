import java.util.List;

/** Simple graph without loop and parallel edges */
public interface Graph<E> {

    /**
     * get number of vertices
     * 
     * @return num of vertices
     */
    int getSize();

    /**
     * get list of vertices
     * 
     * @return list of vertices
     */
    List<E> getVertices();

    /**
     * Get specific vertex
     * 
     * @param i index
     * @return vertex
     */
    E getVertex(int i);

    /**
     * Get index of the vertex
     * 
     * @param e vertex
     * @return index
     * 
     */
    int getIndex(E e);

    /**
     * Get neighbors of the vertex i.e., adjacent vertices
     * 
     * @param i index
     * @return neighbors of the vertex (adjacent vertices)
     */
    List<Integer> getNeighbors(int i);

    /**
     * Get degree of the vertex
     * 
     * @param i index
     * @return degree of the vertex
     */
    int getDegree(int i);

    /** Clear the graph */
    void clear();

    /**
     * Add a vertex into the graph
     * 
     * @param e vertex
     * @return whether the insertion is successful or not
     */
    boolean addVertex(E e);

    /**
     * Add an edge (between vertices - vert1 and vert2) in the graph
     * 
     * @param vert1 index of a vertex
     * @param vert2 index of a vertex
     * @return whether the insertion is successful or not
     */
    boolean addEdge(int vert1, int vert2);

    /**
     * Add an edge (between vertices - vert1 and vert2) in the graph
     * 
     * @param edge edge
     * @return whether the insertion is successful or not
     */
    boolean addEdge(Edge edge);

    /**
     * Get a depth first search tree starting from the specified vesrtex
     * 
     * @param i index of a vertex
     * @return a depth first search tree starting from the specified vesrtex
     */
    AbstractGraph<E>.SearchTree depthFirstSearchTree(int i);

    /**
     * Get a breadth first search tree starting from the specified vesrtex
     * 
     * @param i index of a vertex
     * @return a breadth first search tree starting from the specified vesrtex
     */
    AbstractGraph<E>.SearchTree breadthFirstSearchTree(int i);

}