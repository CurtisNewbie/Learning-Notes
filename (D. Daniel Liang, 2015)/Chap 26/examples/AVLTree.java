import java.util.*;

/**
 * AVL Tree as a subtype of BST tree
 */
public class AVLTree<E extends Comparable<E>> extends BST<E> implements Rebalanceable<E> {

    /**
     * Create AVLTreeNode with element.
     * 
     * @param e element
     * @return a tree node for AVL tree
     */
    @Override
    public TreeNode<E> createNode(E e) {
        return new AVLTreeNode<E>(e);
    }

    /**
     * Create AVLTreeNode with element and height.
     * 
     * @param e element
     * @param h height
     * @return a tree node for AVL tree
     */
    public TreeNode<E> createNode(E e, int h) {
        return new AVLTreeNode<E>(e, h);
    }

    /**
     * Rebalance the node upward (towards the root)
     */
    @Override
    public void rebalance(TreeNode<E> node) {
        ArrayList<TreeNode<E>> path = pathToRoot(node.getElement());
        for (int i = path.size() - 1; i >= 0; i--) {
            var nodeA = path.get(i);
            var parentOfA = (i == 0) ? null : path.get(i - 1);

            switch (balanceFactor(nodeA)) {
            case -2:
                if (balanceFactor(nodeA.getLeft()) <= 0) {
                    llBalance(nodeA, parentOfA);
                } else {
                    lrBalance(nodeA, parentOfA);
                }
                break;
            case 2:
                if (balanceFactor(nodeA.getRight()) >= 0) {
                    rrBalance(nodeA, parentOfA);
                } else {
                    rlBalance(nodeA, parentOfA);
                }
            }
        }

    }

    @Override
    public int balanceFactor(TreeNode<E> node) {
        return (node.getRight() == null ? 0 : ((AVLTreeNode<E>) node.getRight()).getHeight())
                - (node.getLeft() == null ? 0 : ((AVLTreeNode<E>) node.getLeft()).getHeight());
    }

    /**
     * Get a path from the specified element (node) to the root. The first element
     * (index = 0) is the root and the last element is the specified node.
     * 
     * @param e element, which must exist in the tree
     * @return path to the root
     */
    public ArrayList<TreeNode<E>> pathToRoot(E e) {
        ArrayList<TreeNode<E>> path = new ArrayList<>();
        var current = getRoot();
        path.add(current);
        boolean found = false;
        while (!found) {
            if (current.getElement().compareTo(e) == 0) {
                found = true;
                path.add(current);
            } else if (current.getElement().compareTo(e) > 0) {
                current = current.getLeft();
                path.add(current);
            } else {
                current = current.getRight();
                path.add(current);
            }
        }
        return path;
    }

    /**
     * Left Left Rotation. A is the node with a balance factor of -2, B is the left
     * Child of A, where B's balance factor is -1 or 0. This method does a single
     * right rotation.
     * 
     * @param nodeA     node with a balance factor of -2, and this part of the tree
     *                  is in a LL imbalance
     * @param parentOfA parent of the nodeA
     */
    @Override
    public void llBalance(TreeNode<E> nodeA, TreeNode<E> parentOfA) {
        TreeNode<E> nodeB, bRight;
        nodeB = nodeA.getLeft();
        bRight = nodeB.getRight();

        // move B to A, so now A is B
        if (parentOfA == null) { // it's root
            setRoot(nodeB);
        } else {
            // B is reconnnected to A's parent
            if (parentOfA.getElement().compareTo(nodeA.getElement()) > 0) { // nodeA is right child
                parentOfA.setLeft(nodeB);
            } else {
                parentOfA.setRight(nodeB);
            }
        }

        // make (pevious) A to be the B's right child
        nodeB.setRight(nodeA);
        nodeA.setLeft(bRight);

        // update height, the height of the parent of A doesn't change
        updateHeight(nodeA);
        updateHeight(nodeB);
    }

    /**
     * Update the height of the current node by calculating the sum of its child
     * nodes
     * 
     * @param node
     */
    private void updateHeight(TreeNode<E> node) {
        var left = (AVLTreeNode<E>) node.getLeft();
        var right = (AVLTreeNode<E>) node.getRight();
        ((AVLTreeNode<E>) node).setHeight(left.getHeight() + right.getHeight() + 1);
    }

    /**
     * Right Right Rotation. A is the node with a balance factor of 2, B is the
     * right child of A, where B's balance factor is +1 or 0. This method does a
     * single left rotation.
     * 
     * @param nodeA     node with a balance factor of +2, and this part of the tree
     *                  is in a LL imbalance
     * @param parentOfA parent of the nodeA
     * 
     */
    @Override
    public void rrBalance(TreeNode<E> nodeA, TreeNode<E> parentOfA) {
        TreeNode<E> nodeB, bLeft;
        nodeB = nodeA.getRight();
        bLeft = nodeB.getLeft();

        // move B to A, so now A is B
        if (parentOfA == null) { // it's root
            setRoot(nodeB);
        } else {
            // B is reconnnected to A's parent
            if (parentOfA.getElement().compareTo(nodeA.getElement()) > 0) { // nodeA is right child
                parentOfA.setLeft(nodeB);
            } else {
                parentOfA.setRight(nodeB);
            }
        }

        // make (pevious) A to be the B's left child
        nodeB.setLeft(nodeA);
        nodeA.setRight(bLeft);

        // update height, the height of the parent of A doesn't change
        updateHeight(nodeA);
        updateHeight(nodeB);
    }

    /**
     * Left Right Rotation. A is the node at the top with a balance factor of -2. B
     * is the left child of A with a balance factor of +1 and C is the right child
     * of B. The Double rotation starts from single left rotation on B, and then a
     * single right rotation on A.
     * 
     * @param nodeA     node with a balance factor of -2, and this part of the tree
     *                  is in a LR imbalance
     * @param parentOfA parent of nodeA
     */
    @Override
    public void lrBalance(TreeNode<E> nodeA, TreeNode<E> parentOfA) {
        TreeNode<E> nodeB, nodeC, cLeft, cRight;
        nodeB = nodeA.getLeft();
        nodeC = nodeB.getRight();
        cLeft = nodeC.getLeft();
        cRight = nodeC.getRight();

        // Single left rotation on B (redundant, for demo only)
        nodeA.setLeft(nodeC);
        nodeC.setLeft(nodeB);
        nodeB.setRight(cLeft);

        // Single right rotation on A
        if (parentOfA == null) {
            setRoot(nodeC);
        } else {
            if (parentOfA.getElement().compareTo(nodeA.getElement()) > 0) { // nodeA is right child
                parentOfA.setLeft(nodeC);
            } else {
                parentOfA.setRight(nodeC);
            }
        }
        nodeC.setRight(nodeA);
        nodeA.setLeft(cRight);

        // update height
        updateHeight(nodeA);
        updateHeight(nodeB);
        updateHeight(nodeC);
    }

    /**
     * Right Left Rotation. A is the node at the top with a balance factor of +2. B
     * is the right child of A with a balance factor of -1 and C is the left child
     * of B. The Double rotation starts from single right rotation on B, and then a
     * single left rotation on A.
     * 
     * @param nodeA     node with a balance factor of -2, and this part of the tree
     *                  is in a RL imbalance
     * @param parentOfA parent of NodeA
     */
    @Override
    public void rlBalance(TreeNode<E> nodeA, TreeNode<E> parentOfA) {
        TreeNode<E> nodeB, nodeC, cLeft, cRight;
        nodeB = nodeA.getRight();
        nodeC = nodeB.getLeft();
        cLeft = nodeC.getLeft();
        cRight = nodeC.getRight();

        // Single right rotation on B (redundant, for demo only)
        nodeA.setRight(nodeC);
        nodeC.setRight(nodeB);
        nodeB.setLeft(cRight);

        // Single left rotation on A
        if (parentOfA == null) {
            setRoot(nodeC);
        } else {
            if (parentOfA.getElement().compareTo(nodeA.getElement()) > 0) { // nodeA is right child
                parentOfA.setLeft(nodeC);
            } else {
                parentOfA.setRight(nodeC);
            }
        }
        nodeC.setLeft(nodeA);
        nodeA.setRight(cLeft);

        // update height
        updateHeight(nodeA);
        updateHeight(nodeB);
        updateHeight(nodeC);
    }
}