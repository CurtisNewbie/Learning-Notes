import java.util.*;

/**
 * From Chapter 25 with minus modification <br>
 * Binary Search Tree without duplicates
 */
public class BST<E extends Comparable<E>> implements Iterable<E> {

    private TreeNode<E> root;
    private int size;

    public BST(E e) {
        root = createNode(e);
        size = 1;
    }

    public BST() {
        root = null;
        size = 0;

    }

    public BST(E[] arr) {
        root = null;
        size = 0;

        for (E e : arr) {
            this.addNode(e);
            size++;
        }
    }

    public TreeNode<E> createNode(E e) {
        return new TreeNode<E>(e);
    }

    protected TreeNode<E> getRoot() {
        return root;
    }

    protected void setRoot(TreeNode<E> node) {
        root = node;
    }

    public boolean addNode(E e) {
        TreeNode<E> insertedNode = createNode(e);
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
        size++;
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

    public void inorder() {
        inorderTraversal(root);
    }

    public void postorder() {
        postorderTraversal(root);
    }

    public void preorder() {
        preorderTraversal(root);
    }

    // public void post

    /**
     * Inorder Traversal, left-subtree first, then current node, finally the
     * right-subtree.
     * 
     * @param current current node
     */
    private void inorderTraversal(TreeNode<E> current) {
        if (current != null) {
            // left subtree first
            inorderTraversal(current.getLeft());

            // current node
            System.out.print(current.getElement() + " ");

            // right subtree
            inorderTraversal(current.getRight());
        }
    }

    /**
     * Postorder Traversal, left-subtree first, then right-subtree, finally the
     * current node.
     * 
     * @param current current node
     */
    public void postorderTraversal(TreeNode<E> current) {
        if (current != null) {
            // left subtree first
            postorderTraversal(current.getLeft());

            // right subtree
            postorderTraversal(current.getRight());

            // current node
            System.out.print(current.getElement() + " ");
        }
    }

    /**
     * Preorder Traversal / Depth-First Traversal, current node first, then
     * left-subtree, and finally the right-subtree.
     * 
     * @param current current node
     */
    public void preorderTraversal(TreeNode<E> current) {
        if (current != null) {
            // current node first
            System.out.print(current.getElement() + " ");

            // left subtree
            preorderTraversal(current.getLeft());

            // right subtree
            preorderTraversal(current.getRight());
        }
    }

    /**
     * Delete an element if it is in the tree.
     * 
     * @param e element
     * @return
     */
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.getElement()) > 0) {
                parent = current;
                current = current.getRight();
            } else if (e.compareTo(current.getElement()) < 0) {
                parent = current;
                current = current.getLeft();
            } else { // found

                // case 1: current does not have left child
                if (current.getLeft() == null) {

                    // if parent is null, meaning current is root, and there is not left
                    if (parent == null) {
                        // replace root with the right child
                        root = current.getRight();
                    } else {
                        if (e.compareTo(parent.getElement()) > 0)
                            parent.setRight(current.getRight());
                        else // not duplicates
                            parent.setLeft(current.getRight());
                    }
                } else { // case 2: current does have a left child
                    TreeNode<E> leftTreeParent = current;
                    TreeNode<E> leftTreeRightMost = current.getLeft();
                    while (leftTreeRightMost.getRight() != null) {
                        leftTreeParent = leftTreeRightMost;
                        leftTreeRightMost = leftTreeRightMost.getRight();
                    }
                    current.setElement(leftTreeRightMost.getElement());

                    // remove the previous rightmost
                    if (leftTreeParent == current) {
                        leftTreeParent.setLeft(leftTreeRightMost.getLeft());
                    } else {
                        leftTreeParent.setRight(leftTreeRightMost.getLeft());
                    }
                }
                return true;
            }
        }
        return false;

    }

    @Override
    public String toString() {
        return "BST: Root:" + root.getElement() + "In Total Of " + size + "Nodes";
    }

    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }

    // inorder traversal
    class InorderIterator implements Iterator<E> {

        // use a list to temperarily simulate the traversal
        private ArrayList<E> list;
        private int pointer;

        public InorderIterator() {
            list = new ArrayList<>();
            pointer = 0;

            // put elements to the list through inorder traversal
            inorderTrav();
        }

        public void inorderTrav() {
            inorderTrav(root);
        }

        public void inorderTrav(TreeNode<E> node) {
            if (node != null) {
                // add elements from the left subtree
                inorderTrav(node.getLeft());

                // add current element
                list.add(node.getElement());

                // add elements from the right subtree
                inorderTrav(node.getRight());
            }
        }

        @Override
        public boolean hasNext() {
            if (pointer < size)
                return true;
            else
                return false;
        }

        @Override
        public E next() {
            return list.get(pointer++);
        }

        @Override
        public void remove() {
            delete(list.get(pointer));

            // rebuild the list
            list = new ArrayList<E>();
            pointer = 0;
            inorderTrav();
        }

    }

}