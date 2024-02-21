package org.example.trees;

public class TreeNode<T> {

    public int data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        this.left = this.right = null;
    }

}
