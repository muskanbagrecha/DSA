package org.example;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeProblems {

    //    https://www.geeksforgeeks.org/problems/height-of-binary-tree/1

    //This is same as finding max depth of a tree.

    public static int height(TreeNode node) {
        // code here
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // We perform DFS as we need to reach max depth.

    // Time: O(n) - where n is the total no of nodes
    // Space: O(n) - recursion stack
    // In the worst case (e.g., a skewed tree), the recursion stack could have n function calls, leading to a space complexity of O(n).
    // For a balanced tree, the space complexity would be O(log n) due to the height of the tree, but the worst-case is considered O(n).

    //Traversals

    //Pre-Order Traversal
    //root-left-right
    public void preOrderTraversal(TreeNode root){
        if(root==null){
            System.out.println("END");
            return;
        }
        System.out.println(root.data + " -> ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    //O(n) time and O(n) due to stack - in iterative we can do in O(1) time.

    public void inOrderTraversal(TreeNode root){
        if(root==null){
            System.out.println("END");
            return;
        }
        inOrderTraversal(root.left);
        System.out.println(root.data + " -> ");
        inOrderTraversal(root.right);
    }
    //O(n) time and O(n) due to stack - in iterative we can do in O(1) time.

    public void postOrderTraversal(TreeNode root){
        if(root==null){
            System.out.println("END");
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.println(root.data + " -> ");
    }
    //O(n) time and O(n) due to stack - in iterative we can do in O(1) time.

    //https://leetcode.com/problems/binary-tree-level-order-traversal
    //Level order traversal- imp
    //Recursive - Brute force

    public List<List<Integer>> levelOrderRecursion(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        int ht = heightHelper(root);
        for(int i = 1; i<=ht; i++){
            List<Integer> current = new ArrayList<>();
            traverse(current, root, i);
            ans.add(current);
        }
        return ans;
    }

    static int heightHelper(TreeNode root){
        if(root==null){
            return 0;
        }
        return 1+Math.max(heightHelper(root.left), heightHelper(root.right));
    }

    static void traverse(List<Integer> current, TreeNode node, int level){
        if(node==null){
            return;
        }
        if(level==1){
            current.add(node.data);
            return;
        }
        traverse(current, node.left, level-1);
        traverse(current, node.right, level-1);

    }

    //Time complexity:
    // 1. O(n) to find height
    // 2. O(n^2) for actual traversal coz for every level we are going from that level to 1st level. So 5th level would be 5th to 1st and 6th will be 6th to 1st and so on.
    // Ex: For a tree with n levels (and thus n nodes in the case of a skewed tree), you would have:
    //1 operation for the 1st level,
    //2 operations for the 2nd level,
    //...
    //n operations for the nth leve
    // Summing these up, you get a total of 1 + 2 + ... + n operations, which equals n(n+1)/2, simplifying to O(n^2) in terms of time complexity.

    // For a balanced tree, while the height is log(n) and the number of nodes at each level doubles, the traversal from the root to each level still involves visiting all nodes on the path to nodes at that level.
    // The method used here does not take advantage of the balanced structure to reduce the complexity to O(n log n) because it unnecessarily traverses the upper levels for each node in the lower levels.

    //Space complexity:
    //1. We will not consider the space to store the output.
    //2. We need to store recursive calls to find the height. So height of a perfect tree will be O(log(n)) for a balanced tree and O(n) for a skewed tree.


    //Iterative using Queues


    //Min depth of a binary tree

    //Max depth of a binary tree
}
