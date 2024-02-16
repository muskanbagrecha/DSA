package org.example;

public class BinaryTree<T> {

    public TreeNode<T> root;

    public BinaryTree(){
        this.root = null;
    }

    public BinaryTree(T data){
        this.root = new TreeNode<>(data);
    }
}

