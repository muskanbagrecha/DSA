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

    //https://leetcode.com/problems/kth-smallest-element-in-a-bst

    public int kthSmallest(TreeNode root, int k) {
        int[] count = new int[]{0};
        return helper(root, k, count);
    }

    public Integer helper(TreeNode root, int k, int[] count){
        if(root==null){
            return null;
        }
        Integer left = helper(root.left, k, count);
        if(left!=null){ //we need to check if left subtree has the smallest index, if yes return it before computing further.
            return left;
        }
        count[0]++;
        if(count[0]==k){
            return root.data;
        }
        return helper(root.right, k, count);
    }
}

