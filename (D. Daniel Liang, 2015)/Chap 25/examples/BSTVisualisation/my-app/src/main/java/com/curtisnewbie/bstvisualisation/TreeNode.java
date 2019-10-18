package com.curtisnewbie.bstvisualisation;

public class TreeNode<E> {
    private E element;
    // less than this node
    private TreeNode<E> left;
    // greater than this node
    private TreeNode<E> right;

    public TreeNode(E e) {
        this.element = e;
        left = null;
        right = null;
    }

    public void setLeft(TreeNode<E> node) {
        this.left = node;
    }

    public void setRight(TreeNode<E> node) {
        this.right = node;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E e) {
        this.element = e;
    }
}
