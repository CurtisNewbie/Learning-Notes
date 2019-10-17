
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

        System.out.println();
        bst.inorder();
        bst.delete("Banana");
        System.out.println("\nBanana deleted:");
        bst.inorder();
        System.out.println();

        // test from text book
        BST<String> tree = new BST<>();
        tree.addNode("George");
        tree.addNode("Michael");
        tree.addNode("Tom");
        tree.addNode("Adam");
        tree.addNode("Jones");
        tree.addNode("Peter");
        tree.addNode("Daniel");
        tree.inorder();
        System.out.println();
        tree.preorder();
        System.out.println();
        tree.postorder();
        System.out.println();

        System.out.println("\nAfter delete George:");
        tree.delete("George");
        tree.inorder();
        System.out.println();
        tree.preorder();
        System.out.println();
        tree.postorder();
        System.out.println();

        System.out.println("\nAfter delete Adam:");
        tree.delete("Adam");
        tree.inorder();
        System.out.println();
        tree.preorder();
        System.out.println();
        tree.postorder();
        System.out.println();

        System.out.println("\nAfter delete Michael:");
        tree.delete("Michael");
        tree.inorder();
        System.out.println();
        tree.preorder();
        System.out.println();
        tree.postorder();
        System.out.println();

    }
}