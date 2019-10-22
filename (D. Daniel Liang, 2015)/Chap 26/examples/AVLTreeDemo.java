
/**
 * Demo from TextBook
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
        // Create an AVL tree
        AVLTree<Integer> tree = new AVLTree<>(new Integer[] { 25, 20, 5 });
        System.out.print("After inserting 25, 20, 5:");
        System.out.println();
        tree.inorder();
        System.out.println();

        tree.addNode(34);
        tree.addNode(50);
        System.out.print("\nAfter inserting 34, 50:");
        System.out.println();
        tree.inorder();
        System.out.println();

        tree.addNode(30);
        System.out.print("\nAfter inserting 30");
        System.out.println();
        tree.inorder();
        System.out.println();

        tree.addNode(10);
        System.out.print("\nAfter inserting 10");
        System.out.println();
        tree.inorder();
        System.out.println();

        tree.delete(34);
        tree.delete(30);
        tree.delete(50);
        System.out.print("\nAfter removing 34, 30, 50:");
        System.out.println();
        tree.inorder();
        System.out.println();

        tree.delete(5);
        System.out.print("\nAfter removing 5:");
        System.out.println();
        tree.inorder();
        System.out.println();

        System.out.print("\nTraverse the elements in the tree: ");
        for (int e : tree) {
            System.out.print(e + " ");
        }

    }
}