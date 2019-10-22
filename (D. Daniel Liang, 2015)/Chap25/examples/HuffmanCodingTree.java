/**
 * This example demonstrates compressing ASCII standard characters, which is a
 * 8bit encoding schema that allows 2^8 = 256 possibilities. I.e., it allows
 * encoding 256 characters in english.
 */
public class HuffmanCodingTree {

    public static void main(String[] args) {
        String text = "Mississippi";
        System.out.println("Encoding \"Mississippi\"\n\nCodes Table:");
        int[] freq = frequency(text);
        BinaryTree tree = huffmanTree(freq);
        String[] codes = getCodes(tree.root);

        for (int i = 0; i < codes.length; i++)
            if (freq[i] != 0) // (char)i is not in text if counts[i] is 0
                System.out.printf("%-15d%-15s%-15d%-15s\n", i, (char) i + "", freq[i], codes[i]);

        System.out.println("\nText is encoded as:");
        String encoded = encode(text, codes);
        System.out.print(encoded);

        System.out.println("\n\nDecoding based on the codes\n" + decode(encoded, tree));

    }

    public static String encode(String text, String[] codes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            sb.append(codes[(int) c]);
        }
        return sb.toString();
    }

    public static String decode(String encodedBits, BinaryTree huffmanTree) {
        StringBuilder decoded = new StringBuilder();
        BTNode root = huffmanTree.root;
        BTNode current = root;

        for (int i = 0; i < encodedBits.length(); i++) {
            // move
            if (encodedBits.charAt(i) == '0') {
                current = current.left;
            } else {
                current = current.right;
            }
            // check if it is a leaf
            if (current.left == null) {
                decoded.append(current.element);
                current = root;
            }
        }
        return decoded.toString();
    }

    /**
     * Calculate the frequency of each character (256 ASCII characters)
     * 
     * @param text text to be encoded
     * @return an int[] representing the frequency of each character
     */
    public static int[] frequency(String text) {
        int[] freq = new int[256];

        // Identifies the ASCII characters through converting the char to int, and
        // increments the frequnecy by 1;
        for (int i = 0; i < text.length(); i++) {
            freq[(int) text.charAt(i)] += 1;
        }
        return freq;
    }

    /**
     * Construct the huffman tree. The smallest trees are combined first.
     * 
     * @param freq frequency of the 256 ASCII characters
     * @return huffman tree
     */
    public static BinaryTree huffmanTree(int[] freq) {

        // use heap sort to combine all the subtrees
        Heap<BinaryTree> heap = new Heap<>();

        // construct a subtree (leaf node) for each character
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) { // if exists
                BinaryTree leafTree = new BinaryTree();
                leafTree.root = new BTNode();
                leafTree.root.weight = freq[i];
                leafTree.root.element = (char) i;
                heap.add(leafTree);
            }
        }

        // start combing all the subtree, until there is only one tree
        while (heap.size() > 1) {
            // the comparable that the Tree implements is in reverse order
            BinaryTree smallest = heap.removeRoot();
            BinaryTree secSmallest = heap.removeRoot();
            heap.add(BinaryTree.combineTree(smallest, secSmallest));
        }

        BinaryTree lastTree = heap.removeRoot();
        return lastTree;
    }

    /**
     * Get the codes from the root of the huffman tree (recursive), note that this
     * is not the actual compressed text, this is the codes for compressing.
     * 
     * @param root root node of the huffman tree
     * @return the compressed code
     */
    public static String[] getCodes(BTNode root) {

        if (root == null) // nothing to compress
            return null;
        else {
            // 256 ASCII characters
            String[] codes = new String[256];
            assignCode(root, codes);

            return codes;
        }

    }

    /** Recursively assign the code to the String array */
    private static void assignCode(BTNode node, String[] codes) {
        // if left or right child is not null indicating that this node is not a leaf,
        // thus it doesn't have character
        if (node.left != null) {
            node.left.code = node.code + "0";
            assignCode(node.left, codes);

            node.right.code = node.code + "1";
            assignCode(node.right, codes);
        } else {
            // get the code of this node, and write the code to the associated index ((int)
            // char) to the list
            codes[(int) node.element] = node.code;
        }
    }

}

class BinaryTree implements Comparable<BinaryTree> {

    public BTNode root;

    public BinaryTree(BTNode n) {
        root = n;
    }

    public BinaryTree() {
        root = null;
    }

    public static BinaryTree combineTree(BinaryTree tree1, BinaryTree tree2) {
        BinaryTree combinedTree = new BinaryTree();
        BTNode rootNode = new BTNode();
        combinedTree.root = rootNode;

        rootNode.weight = tree1.root.weight + tree2.root.weight;
        rootNode.left = tree1.root;
        rootNode.right = tree2.root;
        return combinedTree;
    }

    /** In reverse order */
    @Override
    public int compareTo(BinaryTree tree) {
        if (root.weight < tree.root.weight)
            return 1;
        else if (root.weight > tree.root.weight)
            return -1;
        else
            return 0;
    }
}

class BTNode {
    char element;
    int weight;
    BTNode left;
    BTNode right;
    String code;

    public BTNode() {
        code = "";
        left = null;
        right = null;
    }
}
