/**
 * Binary Search Tree without duplicates
 */
public class BST<E extends Comparable<E>> {

    private TreeNode<E> root;

    public BST(E e) {
        root = new TreeNode<E>(e);
    }

    public BST() {
        root = null;
    }

    public boolean addNode(E e) {
        TreeNode<E> insertedNode = new TreeNode<E>(e);
        if (root == null)
            root = insertedNode;
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                parent = current;
                if (e.compareTo(current.getElement()) > 0) {
                    // go right
                    current = current.getRight();
                } else if (e.compareTo(current.getElement()) < 0) {
                    // go left
                    current = current.getLeft();
                } else {
                    return false; // duplicates
                }
            }

            // attach the insertedNode to the parent
            if (e.compareTo(parent.getElement()) > 0) {
                // attach to right
                parent.setRight(insertedNode);
            } else {
                // attach to left, as verifed above, no worry about duplicates
                parent.setLeft(insertedNode);
            }
        }
        return true;
    }

    public boolean search(E e) {
        if (root == null)
            return false;

        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.getElement()) > 0)
                current = current.getRight();
            else if (e.compareTo(current.getElement()) < 0)
                current = current.getLeft();
            else
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BST: Root:" + root.getElement() + "In Total Of " + size() + "Nodes";
    }

    public int size() {
        return sumNode(root);
    }

    private int sumNode(TreeNode<E> node) {
        int sum = 0;
        if (node != null) {
            sum += 1;
            TreeNode<E> left = node.getLeft();
            TreeNode<E> right = node.getRight();

            if (left != null) {
                // calculate left subtree
                sum += sumNode(left);
            }
            if (right != null) {
                // calculate right subtree
                sum += sumNode(right);
            }
        }
        return sum;
    }
}