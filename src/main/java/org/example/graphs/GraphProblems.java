package org.example.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphProblems {
    public static ArrayList<Integer> bfsOfDirectedGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        boolean[] vis = new boolean[V];
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        vis[0] = true;
        while(!q.isEmpty()){
            int element = q.remove();
            ans.add(element);
            for(int neighbour : adj.get(element)){
                if(vis[neighbour]!=true){
                    vis[neighbour] = true;
                    q.add(neighbour);
                }
            }
        }
        return ans;
    }
    //Space: O(V) for queue + O(V) for visited array + O(V) storage of output
    //Time: While will run for V times at max (each node is pushed into queue only once) + total degrees (for the inner for loop)
    //Total degrees = no of edges as it is directed graph
    //Total time: O(V) + O(E) = O(V+E)

    //So, for a directed graph, the overall time complexity of the BFS traversal remains O(V + E), acknowledging that each edge is considered once in its directed context, as opposed to the undirected scenario where each edge was considered twice (once from each of its two vertices).

    //https://www.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1
    public ArrayList<Integer> dfsOfUnconnectedGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[V];
        dfsRecursionHelper(0, adj, vis, ans);
        return ans;
    }

    public void dfsRecursionHelper(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis, ArrayList<Integer> ans){
        vis[node] = true;
        ans.add(node);
        for(int neighbour : adj.get(node)){
            if(!vis[neighbour]){
                dfsRecursionHelper(neighbour, adj, vis, ans);
            }
        }
    }
    //Space: O(V) worst case when we have a skewed tree like structure (recursion stack) + O(V) for visited graph + O(V) for output array => O(V)
    //Time: O(V) + O(2*E) => O(V+E)

    public static int findNumOfProvinces(int[][] roads, int n) {
        // Write your code here.
        boolean[] vis = new boolean[n];
        int provinces = 0;
        for(int i = 0; i<n; i++){
            if(!vis[i]){
                dfsFindNumOfProvincesHelper(roads, i, vis);
                provinces++;
            }
        }
        return provinces;
    }

    public static void dfsFindNumOfProvincesHelper(int[][] roads, int city, boolean[] vis){
        vis[city]=true;
        for (int j = 0; j < roads[city].length; j++) {
            if (roads[city][j] == 1 && !vis[j]) {
                dfsFindNumOfProvincesHelper(roads, j, vis);
            }
        }
    }

    //Space: O(n) for visited array
    //Time: O(V^2) coz we are checking each element in the 2d matrix
}
