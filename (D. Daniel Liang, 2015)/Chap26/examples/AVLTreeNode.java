
public class AVLTreeNode<E> extends TreeNode<E> {

    // Height is needed for rebalancing in AVL Tree
    private int height;

    public AVLTreeNode(E e) {
        super(e);
    }

    public AVLTreeNode(E e, int h) {
        super(e);
        setHeight(h);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int h) {
        height = h;
    }

}