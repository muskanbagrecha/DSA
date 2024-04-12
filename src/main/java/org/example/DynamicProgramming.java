package org.example;

import java.util.HashMap;

//Tabulation is also known as bottom up
//Memoization is also know as top down

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

    //Time complexity: O(2^n) because in worst case scenario weight of all items will be less than W so there will always be two options -> include or not.

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
    //Time Complexity: O(N*W) - because each state (defined by n and W) is computed once.
    //Space Complexity: O(N*W) - primarily due to the memoization cache storing O(N*W) unique states. The recursion call stack adds a lesser O(N) space requirement.

    static int knapSackTabulation(int W, int wt[], int val[], int n)
    {
        int[][] knapSack_matrix = new int[n+1][W+1];
        // your code here
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=W; j++){
                if(i==0 || j==0){
                    knapSack_matrix[i][j]=0;
                    continue;
                }
                if(wt[i-1]<=j){
                    knapSack_matrix[i][j] = Math.max(val[i-1]+knapSack_matrix[i-1][j-wt[i-1]], knapSack_matrix[i-1][j]);
                }
                else{
                    knapSack_matrix[i][j] = knapSack_matrix[i-1][j];
                }
            }
        }
        return knapSack_matrix[n][W];
    }

    //Subset sum problem

    //1. Recursion. Gives TLE after a point.
    static Boolean isSubsetSum(int N, int arr[], int sum){
        if(sum==0){
            return true; //empty subset yields sum 0;
        }
        if(N==0){
            return false; //If N = 0 and sum is anything other than 0, then it will be false.
        }
        boolean dontPick = isSubsetSum(N-1, arr, sum);
        if(arr[N-1]<=sum){
            boolean pick = isSubsetSum(N-1, arr, sum-arr[N-1]);
            return dontPick || pick;
        }
        return dontPick;
    }

    //Time: O(2^N)
    //Space: O(N) - max depth is N assuming choice till last element does not yield target. Then one branch is popped completely before another branch is traversed. Kinda like DFS.

    static Boolean isSubsetSumMemo(int N, int arr[], int sum){
        Boolean memo[][] = new Boolean[N+1][sum+1];
        return isSubsetSumMemoHelper(N, arr, sum, memo);
    }

    static Boolean isSubsetSumMemoHelper(int N, int arr[], int sum, Boolean[][] memo){
        if(memo[N][sum]!=null){
            return memo[N][sum];
        }
        if(sum==0){
            return memo[N][sum]=true; //empty subset yields sum 0;
        }
        if(N==0){
            return memo[N][sum]=false; //If N = 0 and sum is anything other than 0, then it will be false.
        }
        boolean dontPick = isSubsetSumMemoHelper(N-1, arr, sum, memo);
        if(arr[N-1]<=sum){
            boolean pick = isSubsetSumMemoHelper(N-1, arr, sum-arr[N-1], memo);
            return memo[N][sum]=dontPick || pick;
        }
        return memo[N][sum]=dontPick;
    }
    //Time: O(N*sum) - every sub problem is computed exactly once
    //Space: O(N*sum) - DP table

    static Boolean isSubsetSumTabulation(int N, int arr[], int sum){
        // code here
        boolean[][] t = new boolean[N+1][sum+1];
        for(int i = 0; i<=N; i++){
            for(int j = 0; j<=sum; j++){
                if(i==0 || j==0){
                    if(i==0){
                        t[i][j]=false;
                    }
                    if(j==0){
                        t[i][j]=true;
                    }
                    continue;
                }

                if(arr[i-1]<=j){ // can pick or not pick
                    t[i][j] = t[i-1][j] || t[i-1][j-arr[i-1]];
                }
                else{ //cant pick
                    t[i][j] = t[i-1][j];
                }
            }
        }
        return t[N][sum];
    }
    //Time and space same as memo
}