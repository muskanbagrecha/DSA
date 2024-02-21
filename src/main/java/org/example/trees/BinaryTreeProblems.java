package org.example.trees;

import java.util.*;

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
    //O(n) time and O(n) due to stack


    //Iterative
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        stack.add(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                ans.add(node.data);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return ans;
    }
    //O(n) time and O(n) due to stack

    public List<Integer> preorderNAryTree(NaryNode root) {
        List<Integer> list = new ArrayList<>();
        traverse(root, list);
        return list;
    }

    public void traverse(NaryNode root, List<Integer> list){
        if(root==null){
            return;
        }
        list.add(root.data);
        for(NaryNode child : root.children){
            traverse(child, list);
        }
    }

    public List<Integer> preorderNaryIterative(NaryNode root) {
        List<Integer> list = new LinkedList<>();
        Stack<NaryNode> s = new Stack<>();
        if(root==null){
            return list;
        }
        s.add(root);
        while(!s.isEmpty()){
            NaryNode node = s.pop();
            list.add(node.data);
            for(int i = node.children.size()-1; i>=0; i--){
                s.push(node.children.get(i));
            }
        }
        return list;
    }

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

    static void traverse(List<Integer> current, TreeNode node, int level){ //In this problem we have a requirement to store output in list of list so we are passing a new current object for every level to store elements in that level.
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
    // 2. O(n^2) for actual traversal coz for every level we are going from that root to that level. So 5th level would be 1st to 5th and 6th will be 1st to 6th and for each and every node.
    // Ex: For a tree with n levels (and thus n nodes in the case of a skewed tree), you would have:
    //1 operation for the 1st level,
    //2 operations for the 2nd level,
    //...
    //n operations for the nth leve
    // Summing these up, you get a total of 1 + 2 + ... + n operations, which equals n(n+1)/2, simplifying to O(n^2) in terms of time complexity.

     //Think of it as doing DFS for each level but unlike standard DFS that explores all nodes in a go,this approach starts anew from root for each level.

    // For a balanced tree, while the height is log(n) and the number of nodes at each level doubles, the traversal from the root to each level still involves visiting all nodes on the path to nodes at that level.
    // The method used here does not take advantage of the balanced structure to reduce the complexity to O(n log n) because it unnecessarily traverses the upper levels for each node in the lower levels.

    //Space complexity:
    //1. We will not consider the space to store the output.
    //2. We need to store recursive calls to find the height. So height of a perfect tree will be O(log(n)) for a balanced tree and O(n) for a skewed tree.


    //Iterative using Queues
    //When we want to store output in list of lists

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer> > ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root==null){
            return ans;
        }
        q.add(root);
        q.add(null);
        List<Integer> current = new ArrayList<>();
        while(!q.isEmpty()){
            TreeNode element = q.remove();
            if(element==null){
                ans.add(current);
                if(q.isEmpty()){
                    return ans;
                }
                current = new ArrayList<>();
                q.add(null);
                continue;
            }
            current.add(element.data);
            if(element.left!=null){
                q.add(element.left);
            }
            if(element.right!=null){
                q.add(element.right);
            }
        }
        return ans;
    }

    //Here, null is used to track it is the end of the level.

    //Without null:
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer> > ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root==null){
            return ans;
        }
        q.add(root);
        while(!q.isEmpty()){
            List<Integer> current = new ArrayList<>();
            int count = q.size();
            for(int i = 0; i<count; i++){
                TreeNode element = q.remove();
                current.add(element.data);
                if(element.left!=null){
                    q.add(element.left);
                }
                if(element.right!=null){
                    q.add(element.right);
                }
            }
            ans.add(current);
        }
        return ans;
    }

    //At any point, number of elements in the queue will correspond to number of elements in a level

    //Both the above methods are same in terms of complexity.

    //Time: O(n) -> visiting each node once.
    //Space: O(n) -> queue

    //When we only want to print the data (https://www.geeksforgeeks.org/problems/level-order-traversal)
    static ArrayList <Integer> levelOrder3(TreeNode root)
    {
        // Your code here
        ArrayList<Integer> list = new ArrayList<>();

        if(root==null){
            return list;
        }

        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.remove();
            list.add(node.data);
            if(node.left!=null){
                q.add(node.left);
            }
            if(node.right!=null){
                q.add(node.right);
            }
        }
        return list;
    }

    public void Helper(TreeNode node, int level, List<List<Integer>> result)
    {
        if(result.size()==level)
            result.add(new ArrayList<Integer>());

        result.get(level).add(node.data);

        if(node.left!=null)
            Helper(node.left, level+1, result);
        if(node.right!=null)
            Helper(node.right, level+1, result);
    }

    public List<List<Integer>> levelOrder4(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null)
            return result;
        Helper(root,0, result);
        return result;
    }

    //Min depth of a binary tree

    //Max depth of a binary tree
}
