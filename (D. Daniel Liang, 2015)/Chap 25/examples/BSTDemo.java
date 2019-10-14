
public class BSTDemo {
    public static void main(String[] args) {
        BST<String> bst = new BST<>();
        bst.addNode("Banana");
        bst.addNode("Apple");
        bst.addNode("Cat");
        System.out.println(bst.search("Ca"));
        System.out.println(bst.search("Cat"));
        System.out.println(bst.size());
    }
}