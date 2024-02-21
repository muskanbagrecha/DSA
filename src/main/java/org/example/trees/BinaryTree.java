package org.example.trees;

import java.util.List;
import java.util.Scanner;

public class BinaryTree {

    public TreeNode root;
    Scanner scanner = new Scanner(System.in);

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(int data) {
        this.root = new TreeNode<>(data);
    }

    //TODO: improve insertion method to be more streamlined and creating a version of programmatic insertion
    public void insert() {
        System.out.println("Enter root node");
        int data = scanner.nextInt();
        this.root = new TreeNode(data);
        insertChildren(root);
    }

    private void insertChildren(TreeNode node){
        System.out.println("Do you want to insert to the left? Enter true or false.");
        boolean left = scanner.nextBoolean();
        if(left){
            System.out.println("Enter the data value of left");
            int leftData = scanner.nextInt();
            TreeNode leftNode = new TreeNode(leftData);
            node.left = leftNode;
            insertChildren(leftNode);
        }
        System.out.println("Do you want to insert to the right? Enter true or false.");
        boolean right = scanner.nextBoolean();
        if(right){
            System.out.println("Enter the data value of right");
            int rightData = scanner.nextInt();
            TreeNode rightNode = new TreeNode(rightData);
            node.right = rightNode;
            insertChildren(rightNode);
        }
    }

    //Pre-Order Traversal
    //root-left-right
    public void preOrderTraversal(TreeNode root, List<Integer> list){
        if(root==null){
            System.out.println(" END ");
            return;
        }
        System.out.println(root.data + " -> ");
        preOrderTraversal(root.left, list);
        preOrderTraversal(root.right, list);
    }
}

