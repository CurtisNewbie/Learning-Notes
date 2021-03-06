
public interface Rebalanceable<E> {

    /** Rebalance the node of the given element all the way to the root */
    void rebalance(E e);

    /** Left Left Rotation */
    void llBalance(TreeNode<E> nodeA, TreeNode<E> parentOfA);

    /** Right Right Rotation */
    void rrBalance(TreeNode<E> nodeA, TreeNode<E> parentOfA);

    /** Left Right Rotation. */
    void lrBalance(TreeNode<E> nodeA, TreeNode<E> parentOfA);

    /** Right Left Rotation */
    void rlBalance(TreeNode<E> nodeA, TreeNode<E> parentOfA);

    /** Return balance factor of the node */
    int balanceFactor(TreeNode<E> node);
}