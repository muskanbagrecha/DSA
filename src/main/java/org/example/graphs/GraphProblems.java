package org.example.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphProblems {
    public static ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
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
}
