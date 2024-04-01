package org.example;

import java.util.HashMap;

public class DynamicProgramming {
    //https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1

    //Recursive without DP
    static int knapSack(int W, int wt[], int val[], int n)
    {
        // your code here
        if(n==0 || W==0){
            return 0;
        }

        if(wt[n-1]<=W){
            return Math.max(val[n-1] + knapSack(W-wt[n-1], wt, val, n-1), knapSack(W, wt, val, n-1));
        }
        else{
            return knapSack(W, wt, val, n-1);
        }
    }

    //Memoized
    static int knapSackMemo(int W, int wt[], int val[], int n)
    {
        HashMap<String, Integer> map = new HashMap<>();
        return knapSackMemoHelper(W, wt, val, n, map);
    }

    static int knapSackMemoHelper(int W, int wt[], int val[], int n, HashMap<String, Integer> map){
        if(n==0 || W==0){
            return 0;
        }
        String key = n + "_" + W;
        if(map.containsKey(key)){
            return map.get(key);
        }
        int profit = 0;
        if(wt[n-1]<=W){
            profit =  Math.max(val[n-1] + knapSackMemoHelper(W-wt[n-1], wt, val, n-1, map), knapSackMemoHelper(W, wt, val, n-1, map));
        }
        else{
            profit = knapSackMemoHelper(W, wt, val, n-1, map);
        }
        map.put(key, profit);
        return profit;
    }
}
