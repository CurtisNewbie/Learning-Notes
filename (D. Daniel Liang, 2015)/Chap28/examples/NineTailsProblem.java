import java.util.*;

public class NineTailsProblem {

    /** 9 coins meaning the 2^9 combinations */
    private final static int NUM_OF_NODES = 512;

    /** The BFS spanning tree for finding the shotest path */
    private AbstractGraph<Integer>.SearchTree bfsTree;

    /** The node that the current gameboard is at. Range 0 - 511 */
    private int currentIndex;

    public NineTailsProblem() {

        // 1. construct edges
        List<Edge> edges = getEdges();

        // 2. construct vertices (vertices are calculated with indices)
        List<Integer> vertices = new ArrayList<>(512 + 1);
        for (int i = 0; i < 512; i++) {
            vertices.add(i);
        }

        // 3. construct graph
        Graph<Integer> graph = new UnweightedGraph<>(vertices, edges);

        // 4. obtain the BFS spanning tree from the BFS operation, rooted at 511
        // (winning condition), so that we can get the shortest path from a specific
        // node to the root.
        bfsTree = graph.breadthFirstSearchTree(511);
    }

    /**
     * Get a {@code String} representing the gameboard. The game board is
     * represented using labels "U" or "D", where "U" means the coin is facing up,
     * and "D" means the coin is facing down.
     * 
     * @param index index of the node
     * @return a {@code String} representing the gameboard
     */
    public String printGameBoard(int index) {
        int[] node = getNode(index);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if (i % 3 != 2)
                sb.append((node[i] == 0 ? "U" : "D") + " ");
            else
                sb.append((node[i] == 0 ? "U" : "D") + "\n");
        }
        return sb.toString();
    }

    /**
     * Set the Nine Tail Problem (the game) to a random status. I.e., Set it to a
     * randomly selected node
     */
    public void setToRandomNode() {
        currentIndex = new Random().nextInt(NUM_OF_NODES);
    }

    /**
     * Set the Nine Tail Problem (the game) to a specific status. I.e., Set it to
     * the selected node {@code i}
     * 
     * @param i index of node
     */

    public void setToNode(int i) {
        currentIndex = (i >= 0 && i < NUM_OF_NODES) ? i : 0;
    }

    /**
     * Calculate which coin should be flipped in the next step
     * 
     * @return which coin should be flipped in the next step
     */
    public int getNextStep() {
        var list = bfsTree.getPathToRoot(getCurrentIndex());
        int nextNode = list.get(1); // index
        for (int i = 0; i < 9; i++) {
            if (getFlippedNode(getNode(currentIndex), i) == nextNode) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Get current Index of the node that the gameboard is currently at
     * 
     * @return {@code currentIndex}
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /**
     * Flip the coin at index {@code i}. This method is for controlling the game.
     * Thus, it will change the {@code currentIndex} stored.
     */
    public void flipCoin(int i) {
        currentIndex = getFlippedNode(getNode(currentIndex), i);
    }

    /**
     * Get edges between the 512 vertices. This is used to construct the graph for
     * this nine tail problem. Internally, it iterates the index from 0 to 512, get
     * each vertex using the index, and try to find the adjacent vertices of this
     * vertex by fliping all its coins one by one.
     * 
     * @return a List of edges between the 512 vertices
     */
    private static List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < 512; i++) {
            // find the adjacent vertices of vertex i by flipping all its coins one by one.
            var vertex = getNode(i);

            // flip all coins, find adjacent vertices and assign edges
            for (int j = 0; j < 9; j++) {
                // edge between i and flipped vertex j
                edges.add(new Edge(i, getFlippedNode(vertex, j)));
            }
        }
        return edges;
    }

    /**
     * Get the index of the node, which is the result of the given node flipping the
     * coin at the speicified position.
     * 
     * @param node     the node wherein a coin will be flipped
     * @param position the position of the coin that will be flipped (0 - 8). 0 - 2
     *                 are at the first row, 3 - 5 are at the second row
     * @return the index of the new node after the coin on the original node being
     *         flipped.
     */
    private static int getFlippedNode(int[] node, int position) {
        int[] flippedNode = new int[9];
        System.arraycopy(node, 0, flippedNode, 0, 9);
        // flip the coin at the current position
        flip(flippedNode, position);

        // flip the adjacent coins
        flipMultipleNodes(flippedNode, adjacentPosition(position));

        // find the index of this flipped node
        return getIndex(flippedNode);
    }

    /**
     * Get the indicdes of the adjacent coins. <br>
     * A gameboard has 9 coins, this method detects the adjacent coins (not
     * diagonally adjacent) and return an {@code int[]} containing the indices of
     * the adjacent coins.
     * 
     * @param position index of the current coin
     * @return an {@code int[]} that contains the indicies of the adjacent coins
     *         (not diagonally adjacent). The value of these indices is assigned
     *         {@code -1} if the calculated indicies are out of boundary.
     */
    private static int[] adjacentPosition(int position) {
        int row = position / 3;
        int column = position % 3;

        int[] adjacentPos = new int[4];
        // calculate adjacency and test whether the position is out of boundary, a value
        // of -1 indicates that the position is out of boundary
        adjacentPos[0] = (row - 1) >= 0 ? (row - 1) * 3 + column : -1;
        adjacentPos[1] = (row + 1) <= 2 ? (row + 1) * 3 + column : -1;
        adjacentPos[2] = (column - 1) >= 0 ? row * 3 + column - 1 : -1;
        adjacentPos[3] = (column + 1) <= 2 ? row * 3 + column + 1 : -1;
        return adjacentPos;
    }

    /**
     * Flip the coin at the speicified position. If the position is illegal (out of
     * boundary), it does nothing.
     * 
     * @param node     node
     * @param position the position where the coin is flipped (0 - 8). 0 - 2 are at
     *                 the first row, 3 - 5 are at the second row
     */
    private static void flip(int[] node, int position) {
        if (position >= 0 && position < 9) {
            node[position] = node[position] == 1 ? 0 : 1;
        }
    }

    /**
     * Flip multiple coins. It uses
     * {@code flip(int[] node, int position) internally}
     * 
     * @param node      node
     * @param positions the positions where a number of coins are flipped (0 - 8). 0
     *                  - 2 are at the first row, 3 - 5 are at the second row
     */
    private static void flipMultipleNodes(int[] node, int[] positions) {
        for (int p : positions) {
            flip(node, p);
        }
    }

    /**
     * <p>
     * Get the vertex/node or the unique combinations of the 9 coins by giving an
     * index.
     * </p>
     * 
     * <p>
     * A unique combination consists of 9 coins. In the returned int[], each element
     * is either 0 or 1 indicating that the coin is facing up or down. Note that the
     * value of 0 indicates that the coin is facing up, and the value of 1 indicates
     * that the coin is facing down. This can be considered as the convertion
     * between decimal number and binary number.
     * </p>
     * 
     * @param index the index of the vertex/node/unique combination of coins
     * @return a unique combination of the 9 coins at the specified index (each
     *         element is either 0 or 1 meaning the coin is facing up or down.)
     */
    private static int[] getNode(int index) {
        int[] reversedDigits = new int[9];

        int count = 0;
        // convert index to 9 digits binary number
        while (index > 0) {
            int remainder = index % 2;
            index /= 2;
            reversedDigits[count++] = remainder;
        }

        // reverse the array
        int[] comb = new int[9];
        for (int i = 8; i >= 0; i--) {
            comb[8 - i] = reversedDigits[i];
        }
        return comb;
    }

    /**
     * <p>
     * A unique combination consists of 9 coins. In the given int[], each element
     * should be either 0 or 1 indicating that the coin is facing up or down. Note
     * that the value of 0 indicates that the coin is facing up, and the value of 1
     * indicates that the coin is facing down. This can be considered as the
     * convertion between decimal number and binary number.
     * </p>
     * 
     * @param node a unique combination of the 9 coins (each element is either 0 or
     *             1 meaning the coin is facing up or down.)
     * @return the index of this node
     */
    private static int getIndex(int[] node) {
        // convert binary to deciaml number
        int index = 0;
        for (int i = 8; i >= 0; i--) {
            if (node[i] == 1) {
                index += Math.pow(2, 8 - i);
            }
        }
        return index;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n-----------------------Nine Tails Problem(Game)------------------------------");
        System.out.println(
                "Intro: 'U' means the coin is facing up and 'D' means the coin is facing down, \nyou can select which coin to flip by entering 1-9. When one coin is flipped, \nthe adjacent (not diagnoally adjacent) coins are flipped as well. When all \ncoins are facing down, you win! [Enter: \"cheat\", when you need tips]");

        // initiate the game
        var ntp = new NineTailsProblem();
        ntp.setToRandomNode();
        // ntp.setToNode(ntp.getIndex(new int[] { 0, 0, 0, 1, 0, 1, 1, 1, 1 }));
        int count = 0;
        while (ntp.getCurrentIndex() != NUM_OF_NODES - 1) {
            // print the gameboard
            System.out.println("\nEnter 1-9 to flip a coin\n");
            System.out.println(ntp.printGameBoard(ntp.getCurrentIndex()));

            String response = sc.nextLine().trim().toLowerCase();
            if (response.contains("cheat")) {
                // print out tip
                System.out.println("You Are Cheating! Flip coin:[" + (ntp.getNextStep() + 1) + "]");
            } else {
                // flip the coin
                if (response.equals(""))
                    continue;
                else
                    ntp.flipCoin(Integer.parseInt(response) - 1);
                count++;
            }
        }
        System.out.println("You Win!!! You took " + count + " steps in total");
    }
}