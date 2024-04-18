package org.example.trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
//    public void insert() {
//        System.out.println("Enter root node");
//        int data = scanner.nextInt();
//        this.root = new TreeNode(data);
//        insertChildren(root);
//    }

//    private void insertChildren(TreeNode node){
//        System.out.println("Do you want to insert to the left? Enter true or false.");
//        boolean left = scanner.nextBoolean();
//        if(left){
//            System.out.println("Enter the data value of left");
//            int leftData = scanner.nextInt();
//            TreeNode leftNode = new TreeNode(leftData);
//            node.left = leftNode;
//            insertChildren(leftNode);
//        }
//        System.out.println("Do you want to insert to the right? Enter true or false.");
//        boolean right = scanner.nextBoolean();
//        if(right){
//            System.out.println("Enter the data value of right");
//            int rightData = scanner.nextInt();
//            TreeNode rightNode = new TreeNode(rightData);
//            node.right = rightNode;
//            insertChildren(rightNode);
//        }
//    }

    public TreeNode insertLevelOrder(Integer[] array){
        if (array == null || array.length == 0) return null;
        this.root = new TreeNode(array[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while(!q.isEmpty() && i<array.length){
            TreeNode curr = q.poll();
            if(curr!=null && curr.left==null){
                if(array[i]!=null){
                    curr.left = new TreeNode(array[i]);
                    q.add(curr.left);
                }
                i++;
            }
            if(curr!=null && curr.right==null){
                if(array[i]!=null){
                    curr.right = new TreeNode(array[i]);
                    q.add(curr.right);
                }
                i++;
            }
        }
        return root;
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

    public void levelOrderTraversal(TreeNode root, List<Integer> list){
        Queue<TreeNode> q = new LinkedList<>();
        
    }
}

