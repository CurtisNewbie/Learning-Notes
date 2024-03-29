Chapter 28 Graphs and Applications:

# 1.Introduction:

"The study of graph problems is known as graph theory" (p.1016) The theory and terminologies were first coined by Leonhard Euler to solve the "Seven Bridges of Konigsberg Problem".

# 2.Basic Graph Terminologies:

"A graph consists of vertices, and edges that connect the vertices." (p.1016)

**Graph Model**

    A graph model is produced that abstractly describe the problems (e.g., for a map).

**Vertex/ Node**

    A node or vertex is graphically represented using a dot on the graph model.

**Edge**

    An edge is a line between the node or vertex.

## 2.1 What is a graph?

"A graph is a mathematical structure that represents relationships among entities in the real world." (p.1016)

    E.g., a graph is defined as G = (V, E), where V represents a set of
    vertices/nodes, and E represents a set of edges.

    Examples of Verticies/nodes:
        V = {"Seattle", "San Francisco", "Los Angeles", "Denver", "Kansas City",
        "Chicago", "Boston", "New York", "Atlanta", "Miami", "Dallas", "Houston"};

    Examples of Edges:
        E = {{"Seattle", "San Francisco"},{"Seattle", "Chicago"}, {"Seattle",
        "Denver"}, {"San Francisco", "Denver"}, ... };

## 2.2 Directed or Undirected Graph:

A graph can be directed or undirected.

- "In a directed graph, each edge has a direction, which indicates that you can move from one vertex to the other through the edge." (p.1017)
- "In an undirected graph, you can move in both directions between vertices." (p.1017)

A Parent/ Child relationship can be modelled using the directed graph. E.g., A -> B, indicating A is a parent of B.

## 2.3 Weighted or Unweighted Graph:

Edges may be weighted or unweighted. Weight can be assigned to easch edge to indicate such as time, distance, etc.

## 2.4 Adjacent Vertices and Incident Edges:

**Adjacent Vertices**

    "Two vertices in a graph are said to be adjacent if they are connected by the
    same edge." (p.1018)

**Adjacent Edges**

    "Two edges are said to be adjacent if they are connected to the same vertex."
    (p.1018)

**Incident Edges**

    "An edge in a graph that joins two vertices is said to be incident to both
    vertices."(p.1018)

**Degree of A Vertex**

    "The degree of a vertex is the number of edges incident to it." (p.1018)

**Neighbors**

    Two vertices or two edges that are adjacent to each other are called neightbors.

**Loop**

    "A loop is an edge that links a vertex to itself." (p.1018)

**Parallel Edges**

    Two or more edges that connects the same two vertices are called parallel edges.

**Simple Graph**

    A simple graph is the one that doesn't have any parallel edges and loops.

**Complete Graph**

    A complete graph is the one wherein "...every two pairs of vertices are
    connected." (p.1018)

**Connected/ Disconnected Graph**

    "A graph is connected if there exists a path between any two vertices in
    the graph." (p.1018) I.e., there is a path (consists of a number of edges)
    from one vertex to any vertex.

**Subgraph**

    A subgraph of a graph(G), is a graph whose vertex set or edge set is a
    subset of that of the graph G.

**Cycle**

    "Assume that the graph is connected and undirected. A cycle is a closed
    path that starts from a vertex and ends at the same vertex."(p.1018)

**Tree**

    If a connected graph that doesn't cycles, it is a tree.

**Spanning Tree**

    A spanning tree is a connected subgraph of a graph(G) that contains
    all the vertices, and is a tree with minimum edges that connect every vertices.

# 3. Representing Graphs

## 3.1 Representing Vertices - Vertex Object

Vertices can be represented by creating class, e.g., City in a graph for city map. The vertices can be labeled using index if the graph is an array. E.g., vertices[0], vertices[1]... and so on.

## 3.2 Representing Edges - Edge Array

Edges of a graph can be represented using a two-dimentional array. E.g.,

    int[][] edges = {
        {0, 1}, {0, 3}, {0, 5},
        {1, 0}, {1, 2}, {1, 3},
        {2, 1}, {2, 3}, {2, 4}, {2, 10},
        {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
        {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
        {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
        {6, 5}, {6, 7},
        {7, 4}, {7, 5}, {7, 6}, {7, 8},
        {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
        {9, 8}, {9, 11},
        {10, 2}, {10, 4}, {10, 8}, {10, 11},
        {11, 8}, {11, 9}, {11, 10} };

    where the number in the two-dimentional array refers to the index of the
    vertices. E.g., Vertex[] vertices = ...

    More specifically, the edge array of a list of vertices is as follows:

    String[] names = {"Peter", "Jane", "Mark", "Cindy", "Wendy"};
    int[][] edges = {{0, 2}, {1, 2}, {2, 4}, {3, 4}};

## 3.3 Representing Edges - Edge Object

One way to represent edges without using edge array is to create a class for the edge, treating it as an object.

## 3.4 Representing Edges - Adjacency Matrices

Assuming that the graph has n vertices, the edges can be represented using a two-dimentioanl n \* n matrix (Adjacency Matrix). Adjacency Matrices and Adjacency Lists are efficient for processing graphs.

    In the Adjacency Matrices, the value of each element is either 0 or 1, where
    0 indicates that the vertex i has no incident edge to vertex j, and 1 indicates
    that there is an edge between the vertex i and j.

    E.g., adjacencyMatrix[i][j], i and j are the indices of two veritices,
    and the value of them (0 or 1) indicating that whether the correspondent
    vertices i and j has connected edge between them.

    int[][] adjacencyMatrix = {
        {0,1,0,1,0,1,0,0,0,0,0,},
        {0,0,0,0,0,1,1,1,0,1,0,},
        ...
    };

## 3.5 Representing Edges - Adjacency Lists For Vertices and Edges

Edges can also be represented using Adjacency Vertex Lists or Adjacency Edge Lists. "An adjacency vertex list for a vertex i contains the vertices that are adjacent to i and an adjacency edge list for a vertex i contains the edges that are adjacent to i." (p.1022)

    This is very similar to the Adjacency Matrices, except that it doesn't use
    the value of 0 and 1 to indicate which vertices are adjacent to them.

    E.g.,
        // for vertex i = 0, the vertices that are adjacent include: 1, 3, 5 (indices)
        adjacencyList[0] = {1, 3, 5};

    Same applied to Adjacency Lists for Edges:
    E.g.,
        // for edge i = 0
        adjacencyList[0] = {2, 5, 8};

        or

        adjacencyList[0] = {edgeObj2, edgeObj5, edgObj8}; // these are objects

### 3.5.1 Adjacency Vertex List or Adjacency Edge List?

Adjacency Vertex list is easier to implement in comparison to Adjacency Edge List, nonetheless, the Adjacency Edge List is more flexible, and it's easier to add additional constraints on edges.

## 3.6 Using Adjacency Matricies or Adjacency Lists?

Adjacency Matrix is a two dimensional array (or list) that includes the relationship between all vertices regardless of whether they are adjacent. Though the Adjacency List is quite similar, it ignores the vertices that are not adjacenct.

The general rule to select one among them, is based on whether the graph is dense (of many edges) or sparse (of few edges). If the graph is dense, Adjacency Matrix is preferred. While, if the graph is sparse, the Adjacency List should be used as the Adjacency Matrix can waste a lot of space in such case (e.g., storing many unnecessary 0).

# 4. Modeling Graphs

Simlar to the collections in Java Collection Framwork, the Graph interface and implementation can be designed as follows:

                                          | <-- UnweightedGraph
    Graph<interface> <-- AbstractGraph <--|
                                          | <-- WeightedGraph

In this design including the ones in example, the unweightedGraph is simply the extension of AbstractGraph without introducing new methods, it is used to indicate that the graph is unweighted. It doesn't realy distinguish from the AbstractGraph except that it can be instantiated.

# 5. Graph Traversals

There are two ways to traverse the graph, Depth-First Search and Breath-First Search. Both of them create spanning tree, which is named "SearchTree" in the example. This tree specialises at describing the parent-child relationships of the vertices. And such tree maintains the order of traversal.

## 5.1. Depth-First Search in Graph

"The depth-first search of a graph starts from a vertex in the graph and visits all vertices in the graph as far as possible before backtracking." (p.1038)

The DFS in a Graph is similar to the DFS in a tree, except that the DFS in a tree starts from a root or a root of a subtree, and the DFS can start from any vertex in the Graph.

The general idea is as follows:

1. Visits a vertex (from which the DFS is started)
2. Recursively visists all the adjacent vertices to this vertex.

So, the DFS in Graph is very similar to that in Tree. However, in Graph, there can be circles that leading to the infinite recursion. So, the vertices that are visited should be tracked.

    The Breadth-First Search is similar to this logic, however, the DFS prioritises
    visiting the unvisited neighbors.

    "
        Input: Graph G = (V, E) and a starting vertex v
        Output: a DFS tree rooted at v

        Tree dfs(vertex v) {
        visit v;
        for each neighbor w of v
            if (w has not been visited) {
                set v as the parent for w in the tree;
                dfs(w);
            }
        }

    "

### 5.1.1 Applications of DFS

DFS can be used to solve following problems: (p.1042)

- Detecting whether a graph is connected, see whether the number of vertices traversed/searched is the same as the number of vertices in the graph.
- Detecting whether there is a path between vertices
- Finding a path between two vertices (that consists of multiple edges)
- Finding all connnected components (a connected component is a maximal connnected subgraph)
- Detecting whether there is a cycle in the graph
- Finding a Hamitonian path/cycle (which is a path that visits each vertex exactly once and returns to the starting vertex).

## 5.2 Breath-First Search In Graph

"The breadth-first search of a graph visits the vertices level by level. The first level consists of the starting vertex. Each next level consists of the vertices adjacent to the vertices in the preceding level." (p.1045)

The general idea is as follows:

1.  Visits the vertex (from which the BFS is started at)
2.  Visits all the neighbors of current vertex
3.  Go down one level of these neighbors, visits the neighbors (one level down) of these neighbors one by one. Say, in step 2, there are neighbors A, B, C found, in step 3 will visits the neighbors of A first, then the neighbors of B, and finally the neighbors of C. Then, this further goes down one level, until there is no unvisited neighbors.

    "  
    Input: G = (V, E) and a starting vertex v
    Output: a BFS tree rooted at v

        Tree bfs(vertex v) {
            create an empty queue for storing vertices to be visited;
            add v into the queue;
            mark v visited;

            while (the queue is not empty) {
                dequeue a vertex, say u, from the queue;
                add u into a list of traversed vertices;

                for each neighbor w of u
                    if w has not been visited {
                        add w into the queue;
                        set u as the parent for w in the tree;
                        mark w visited;
                }
            }
        }

    "

### 5.2.1 Applications of BFS

BFS can be used to solve: (p.1047)

- Detecting whether a graph is connected, (if there is a path between any two vertices in the graph).
- Detecting wehther there is a paht between two vertices.
- Find shortest path between two vertices. (In the resulted SpanningTree of BFS, the path between the root and any node is the shortest path).
- Find all connected components (a connected component is a maximal connnected subgraph)
- Detect and find whether there is a cycle in the path
- Test whether a graph is bipartite. (a bipartite graph (or bigraph) is a graph whose vertices can be divided into two disjoint and independent sets U and V such that every edge connects a vertex in U to one in V).

# 4. Graph Visualisation

To display a graph, the coordinates of each vertex should be known. One way to design the graph visualisation is to create an interface for display (e.g., call Displayble), and make the classes that will be visually displayed to implement this interface. Using the coordinates provided to draw the vertices and the edges (based on the Adjacent List).

    e.g.,
        class E implements Displayable{...}

        Graph <E extends Displayable>{...}

        interface Displayable{
            int coordinateX();
            int coordinateY();
            ...
        }

# 5. The Nine Tails Problem

"Nine coins are placed in a three-by-three matrix with some face up and some face down. A legal move is to take a coin that is face up and reverse it, together with the coins adjacent to it (this does not include coins that are diagonally adjacent). Your task is to find the minimum number of moves that lead to all coins being face down." (p.1048)

    I.e., select one facing up, flip it, and the adjacent (up, bottm, left and
    right) coins will reverse their facing. When all coins are facing down, it wins.

    e.g.,
    a)
        U U U
        D D D
        U U U

    After fliping the second coin in the last row:
    b)
        U U U
        D U D
        D D D

    After fliping the second coin in the first row:
    c) Win!
        D D D
        D D D
        D D D

    There are nine coins in this game board, which has a 2^9 possible combinations.
    Considering each combination is a node, and en edge between two nodes
    (two combinations) is assinged if it is a legal move between them.

    Say, the node [index of 511] (note that the index starts from 0 to 511, with a
    total of 512 (2^9)), is one has all the coins facing down.

    I.e., Node[511] (last node)

        D D D
        D D D
        D D D

## 8.1 Finding The Path

The process of solving the game will be to find the shortest path from the current node to the final node, which is the winning condition. Thus, this problem can be treated as a graph traversal problem. Note that this gameborad can be represented using binary, where 1 is down, 0 is up, etc.

    The position of each coin can be considered as follows:

        U U U
        D D D   -->   U U U D D D U U U
        U U U         0 1 2 3 4 5 6 7 8

    In other words, the Node[0] (it means the vertex at index 0) is as follows:

        U U U U U U U U U

    And the second vertex is:

        U U U U U U U U D

    ...

    There are 512 unique vertices in the graph. And this path can be found by
    constructing a graph consists of the 512 vertices, then use this graph to
    produce a BFS tree for getting the shortest path between the current vertex
    and the target vertex.
