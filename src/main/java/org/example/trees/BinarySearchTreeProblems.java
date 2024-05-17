package org.example.trees;

public class BinarySearchTreeProblems {

    //https://leetcode.com/problems/search-in-a-binary-search-tree
    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null){
            return null;
        }
        if(root.data==val){
            return root;
        }
        else if(val<root.data){
            return searchBST(root.left, val);
        }
        return searchBST(root.right, val);
    }
    //TC: O(logn) for most cases but O(n) in worst case when tree is skewed.

    //https://www.geeksforgeeks.org/problems/minimum-element-in-bst/1
    int minValue(Node root) {
        // code here
        if(root==null){
            return -1;
        }
        if(root.left!=null){
            return minValue(root.left);
        }
        return root.val;
    }
}
