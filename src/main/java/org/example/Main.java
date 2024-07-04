package org.example;

import org.example.pair.Pair;
import org.example.trees.BinaryTree;
import org.example.trees.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        minimumDifference(new int[]{2, -1, 0, 4, -2, -9});
        BinaryTree bt = new BinaryTree();
        numEnclaves(new int[][]{
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 0}
        });
//        bt.insertLevelOrder(new Integer[]{1,2,3,4,5,null, null,6,7,null, 8, null, null, null, 9,null, 11});
//        BinaryTreeProblems.diameterOfBinaryTree(bt.root);
        int W = 7; // Weight capacity of the knapsack
        int val[] = {1, 4, 5, 7}; // Values (stored in val[])
        int wt[] = {3, 3, 4, 5}; // Weights (stored in wt[])
        int n = val.length; // Number of items

        // Calling the knapSack method and storing the result
//        int maxProfit = knapSack(W, wt, val, n);

        // Printing the result
//        System.out.println("Maximum value achievable within weight limit: " + maxProfit);
//        BinaryTree bt = new BinaryTree();
//        bt.insertLevelOrder(new Integer[]{1,2,3,4,5});
//        TreeNode ans = BinaryTreeProblems.levelOrderSuccessor(bt.root, 5);
//        System.out.println(ans);
//        StackProblems.nextGreaterElements(arr);
//        Recursion.printSubsets("xyz");
//        Sort.selectionSort(arr);
//        permutation("ABC");
//        StackProblems.stockSpanOptimized(new int[]{10, 4, 5, 90, 120, 80});
//        StackProblems.getMaxArea(new long[]{6,2,5,4,5,1,6}, 7);
//        StackProblems.leftSmaller(5, new int[]{1,2,3,4,5});
//        List<String> list =  Recursion.generateParenthesis(4);
//        System.out.println(list);
//        GraphProblems.floodFill(new int[][]{
//                {1,1,1},
//                {1,1,0},
//                {1,0,1}
//        }, 1, 1, 2);
//        GraphProblems.numIslands(new char[][]{
//                {'0', '1'}, {'1', '0'}, {'1', '1'}, {'1', '0'}
//        });
//        int n = 3;
//        // Number of edges
//        int m = 2;
//        // Edges in the graph (each edge represented by a pair of vertices)
//        int[][] edges = {
//                {2, 1},
//                {2, 0}
//        };
//        int[][] adjacencyMatrix = printAdjacency(n, m, edges);

//        double ans = myPow(2.0000, -2);
//        int V = 13;
//        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
//
//        // Initialize adjacency list
//        for (int i = 0; i < V; i++) {
//            adj.add(new ArrayList<>());
//        }
//
//        // Add edges
//        adj.get(0).add(1);
//        adj.get(0).add(2);
//        adj.get(0).add(4);
//        adj.get(0).add(8);
//        adj.get(1).add(5);
//        adj.get(1).add(6);
//        adj.get(1).add(9);
//        adj.get(2).add(4);
//        adj.get(3).add(7);
//        adj.get(3).add(8);
//        adj.get(5).add(8);
//        adj.get(6).add(7);
//        adj.get(6).add(9);
//        GraphProblems.bfsOfDirectedGraph(V, adj);
    }

    static int knapSack(int W, int wt[], int val[], int n) {
        int[][] knapSack_matrix = new int[n + 1][W + 1];
        // your code here
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    knapSack_matrix[i][j] = 0;
                    continue;
                }
                if (wt[i - 1] <= j - 1) {
                    knapSack_matrix[i][j] = Math.max(val[i - 1] + knapSack_matrix[i - 1][j - wt[i - 1]], knapSack_matrix[i - 1][j]);
                } else {
                    knapSack_matrix[i][j] = knapSack_matrix[i - 1][j];
                }
            }
        }
        return knapSack_matrix[n][W];
    }

    public static boolean isSymmetric(TreeNode root) {
        return sym(root, true);
    }

    public static boolean sym(TreeNode root, boolean flag) {
        if (root == null || (root.left == null && root.right == null)) {
            return flag;
        }
        if ((root.left != null && root.right == null) || (root.right != null && root.left == null)) {
            return false;
        }
        if (root.left.data != root.right.data) {
            return false;
        }
        return sym(root.left, flag) && sym(root.right, flag);
    }

    public static double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1 / x * myPow(x, n + 1);
        }
        return x * myPow(x, n - 1);
    }

    static ArrayList<String> permutation(String S) {
        // Code Here
        ArrayList<String> list = new ArrayList<>();
//        subsets(S, "", list);
        return list;
    }

    public static int[][] printAdjacency(int n, int m, int[][] edges) {
        int[][] adj = new int[n][];
        // Write your code here.
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j] == 1) {
                    adj[i][adj[i].length] = 1;
                }
            }
        }
        return adj;
    }

//    public static void subsets(String input, String output, ArrayList<String> list){
//        if(input.length()==0){
//            list.add(output.trim());
//            return;
//        }
//        char c = input.charAt(0);
//        String newOutput = output + c;
//        String newOutputWithSpace = output.isEmpty() ? "" + c : output + " " + c;
//
//        String newInput = input.substring(1);
//
//        subsets(newInput, newOutputWithSpace, list);
//
//        subsets(newInput, newOutput, list);
//    }


    public static int[] nextGreaterElementWithStack(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < arr[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.pop();
            }
            stack.push(arr[i]);
        }
        return ans;
    }

//    public List<List<Integer>> levelOrder(TreeNode root) {
//        List<List<Integer>> list = new ArrayList<>();
//        Queue<TreeNode> q = new Queue<>();
//        q.add(root);
//        q.add(null);
//        while(!q.isEmpty()){
//            TreeNode current = q.remove();
//            if(current==null){
//                list.add(new ArrayList<>());
//            }
//            if(current.left!=null){
//                q.push(current.left);
//            }
//            if(current.right!=null){
//                q.push(current.right);
//            }
//        }
//    }

    int maxSum = 0;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSum = maxPathSum(root.left);
        int rightSum = maxPathSum(root.right);

        maxSum = Math.max(maxSum, leftSum + rightSum);

        return root.data + leftSum + rightSum;
    }

    static int[] drow = {-1, 0, 1, 0};
    static int[] dcol = {0, 1, 0, -1};

    public static int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!vis[i][j] && grid[i][j] == 1) {
                    int count = traverse(grid, vis, i, j);
                    ans += count;
                }
            }
        }
        return ans;
    }

    public static int traverse(int[][] grid, boolean[][] vis, int i, int j) {
        vis[i][j] = true;
        boolean touchesBoundary = false;
        if (i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length) {
            touchesBoundary = true;
        }
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(i, j));
        int count = 0;
        while (!q.isEmpty()) {
            count++;
            Pair curr = q.poll();
            for (int k = 0; k < 4; k++) {
                int nrow = drow[k] + curr.first;
                int ncol = dcol[k] + curr.second;
                if (nrow >= 0 && nrow < grid.length && ncol >= 0 && ncol < grid[0].length && grid[nrow][ncol] == 1 && !vis[nrow][ncol]) {
                    if (nrow == 0 || nrow == grid.length - 1 || ncol == 0 || ncol == grid[0].length - 1) {
                        touchesBoundary = true;
                    }
                    vis[nrow][ncol] = true;
                    q.offer(new Pair(nrow, ncol));
                }
            }
        }
        return touchesBoundary == true ? 0 : count;
    }


}


