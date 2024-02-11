package org.example;

public class BinaryTreeProblems {

    //    https://www.geeksforgeeks.org/problems/height-of-binary-tree/1
    public static int height(TreeNode node) {
        // code here
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // Time: O(n) - where n is the total no of nodes
    // Space: O(n) - recursion stack
    // In the worst case (e.g., a skewed tree), the recursion stack could have n function calls, leading to a space complexity of O(n).
    // For a balanced tree, the space complexity would be O(log n) due to the height of the tree, but the worst-case is considered O(n).
}
