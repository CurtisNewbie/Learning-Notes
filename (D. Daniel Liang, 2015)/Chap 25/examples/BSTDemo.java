
public class BSTDemo {
    public static void main(String[] args) {
        BST<String> bst = new BST<>();
        bst.addNode("Banana");
        bst.addNode("Apple");
        bst.addNode("Cat");
        System.out.println(bst.search("Ca"));
        System.out.println(bst.search("Cat"));
        System.out.println(bst.size());
        System.out.println("\n");

        Integer[] arr = new Integer[] { 60, 55, 100, 45, 57, 67, 107, 59, 101 };
        BST<Integer> bst2 = new BST<>(arr);
        System.out.println("\nInorder");
        bst2.inorder();
        System.out.println("\nPostorder");
        bst2.postorder();
        System.out.println("\nPreorder");
        bst2.preorder();

    }
}