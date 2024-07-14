package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    //If we have to print the subsets as well: https://www.hackerearth.com/problem/algorithm/print-subset-sum-to-k
    public static void generateSubsets(int n, int[] a, int k, List<Integer> list){
        if(n==a.length){
            if(k==0){
                for(int data : list){
                    System.out.print(data + " ");
                }
                System.out.println();
            }
            return;
        }
        if(a[n]>k){
            generateSubsets(n+1, a, k, list);
            return;
        }
        generateSubsets(n+1, a, k, list);
        list.add(a[n]);
        generateSubsets(n+1, a, k-a[n], list);
        list.remove(list.size()-1);
    }

    //https://leetcode.com/problems/partition-equal-subset-sum
    private Boolean[][] dp;

    public boolean canPartition(int[] nums) {
        int sum = computeSum(nums);
        if (sum % 2 != 0) {
            return false;
        }
        dp = new Boolean[nums.length + 1][sum / 2 + 1];
        return subsetSum(nums, nums.length, sum / 2);
    }

    private int computeSum(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        return total;
    }

    private boolean subsetSum(int[] nums, int itemCount, int targetSum) {
        if (dp[itemCount][targetSum] != null) {
            return dp[itemCount][targetSum];
        }
        if (targetSum == 0) {
            return dp[itemCount][targetSum] = true;
        }
        if (itemCount == 0) {
            return dp[itemCount][targetSum] = false;
        }
        if (nums[itemCount - 1] > targetSum) {
            return dp[itemCount][targetSum] = subsetSum(nums, itemCount - 1, targetSum);
        }
        return dp[itemCount][targetSum] = subsetSum(nums, itemCount - 1, targetSum) ||
                subsetSum(nums, itemCount - 1, targetSum - nums[itemCount - 1]);
    }

    //Time: O(n) + O(n/2 * sum) => O(n * sum)
    //Space: O(n/2 * sum) => O(n * sum)

    public boolean canPartition2(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (total % 2 != 0) {
            return false;
        }
        boolean[][] dp = new boolean[nums.length + 1][total / 2 + 1];
        for (int i = 0; i <= nums.length; i++) {
            for (int j = 0; j <= total / 2; j++) {
                if (i == 0) {
                    dp[i][j] = false;
                } else if (j == 0) {
                    dp[i][j] = true;
                } else if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length][total / 2];
    }

    //this sol was slightly slower than memo one.

    //https://www.geeksforgeeks.org/problems/minimum-sum-partition3317/1
    //For positive numbers
    public int minDifference(int nums[], int n)
    {
        // Your code goes here
        int range = 0;
        for (int i = 0; i < n; i++) {
            range += Math.abs(nums[i]);
        }
        int halfRange = range / 2;
        boolean[][] dp = new boolean[n + 1][halfRange + 1];
        for(int i = 0; i<=n; i++){
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= halfRange; j++) {
                if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        for (int i = halfRange; i >= 0; i--) {
            if (dp[n][i]) {
                return range - 2 * i;
            }
        }
        return 0;
    }
    //this soln wont work for arrays with negative nos.

    //https://leetcode.com/problems/climbing-stairs
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        java.util.Arrays.fill(dp, -1);
        return compute(n, dp);
    }

    public int compute(int n, int[] dp){
        if(n==0 || n==1){
            return 1;
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        return dp[n] = compute(n-1, dp) + compute(n-2, dp);
    }

    //Space optimized:
    public int climbStair2(int n) {
        int prev = 1; //for n=0, there is exactly one way to do NOTHING.
        int prev2 = 1;//for n=1, there is exactly one way that is to climb stairs.
        for(int i = 2; i<=n; i++){
            int curr = prev + prev2;
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    //unboundedknapsack
    public static int unboundedknapsack(int n, int w, int[] profit, int[] weight, int[][]dp){
        if(w==0 || n==0){
            return 0;
        }
        if(dp[n][w]!=-1){ //we will assume dp is initialized by -1 in initial method
            return dp[n][w];
        }
        if(weight[n-1]<=w){
            return dp[n][w] = Math.max(profit[n-1] + unboundedknapsack(n, w-weight[n-1], profit, weight, dp), unboundedknapsack(n-1, w, profit, weight, dp));
        }
        else{
            return dp[n][w] = unboundedknapsack(n-1, w, profit, weight, dp);
        }
    }
    //time: O(n*w)

    public int cutRod(int price[], int n) {
        //code here
        int[][] dp = new int[n+1][n+1];
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=n; j++){
                dp[i][j] = -1;
            }
        }
        return dpHelper(price, n, n, dp);
    }

    public int dpHelper(int price[], int total, int n, int[][] dp){
        if(n==0 || total==0){
            return 0;
        }
        if(dp[n][total]!=-1){
            return dp[n][total];
        }
        if(n<=total){
            return dp[n][total] = Math.max((price[n-1] + dpHelper(price, total-n, n, dp)), dpHelper(price, total, n-1, dp));
        }
        return dp[n][total] = dpHelper(price, total, n-1, dp);
    }


    //https://www.geeksforgeeks.org/problems/coin-change2448/1
    public long noOfWaysCoinChange(int coins[], int N, int sum) {
        // code here.
        long dp[][] = new long[N+1][sum+1];
        for(int i = 0; i<=N; i++){
            for(int j = 0; j<=sum; j++){
                dp[i][j] = -1;
            }
        }
        return noOfWays(coins, dp, N, sum);
    }

    public long noOfWays(int[] coins, long[][] dp, int N, int sum){
        if(N==0){
            if(sum==0){
                return 1;
            }
            return 0;
        }
        if(dp[N][sum]!=-1){
            return dp[N][sum];
        }
        if(coins[N-1]<=sum){
            return dp[N][sum] = noOfWays(coins, dp, N, sum-coins[N-1]) + noOfWays(coins, dp, N-1, sum);
        }
        return dp[N][sum] = noOfWays(coins, dp, N-1, sum);
    }
    //TC: O(N*sum)

    //Bottom up:
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        for(int j = 0; j<=amount; j++){
            dp[0][j] = 0;
        }
        for(int i = 0; i<=n; i++){
            dp[i][0] = 1;
        }
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=amount; j++){
                if(coins[i-1]<=j){
                    dp[i][j] = dp[i][j-coins[i-1]] + dp[i-1][j];
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][amount];
    }

    //LCS top down
    public int longestCommonSubsequence(String text1, String text2) {
        int dp[][] = new int[text1.length()+1][text2.length()+1];
        for(int i = 0; i<=text1.length(); i++){
            for(int j = 0; j<=text2.length(); j++){
                dp[i][j] = -1;
            }
        }
        return lcs(text1.toCharArray(), text2.toCharArray(), text1.length(), text2.length(), dp);
    }

    public int lcs(char[] text1, char[] text2, int m, int n, int[][] dp) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if(dp[m][n]!=-1){
            return dp[m][n];
        }
        if(text1[m-1]==text2[n-1]){
            return dp[m][n] = 1 + lcs(text1, text2, m-1, n-1, dp);
        }
        if(dp[m-1][n]==-1){
            dp[m-1][n] = lcs(text1, text2, m-1, n, dp);
        }
        if(dp[m][n-1]==-1){
            dp[m][n-1] = lcs(text1, text2, m, n-1, dp);
        }
        return dp[m][n] = Math.max(dp[m-1][n], dp[m][n-1]);
    }
    //TC: O(M*N)
    //SC: O(M*N)

    public static String printLcs(int n, int m, String text1, String text2){
        // Write your code here.
        StringBuffer sb = new StringBuffer();
        int dp[][] = new int[n+1][m+1];
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=m; j++){
                if(i==0 || j==0){
                    dp[i][j] = 0;
                }
                else if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        int i = n;
        int j = m;
        while(i>0 && j>0){
            if(text1.charAt(i)==text2.charAt(j)){
                sb.append(text1.charAt(i));
                i--;
                j--;
            }
            else if(dp[i-1][j]>dp[i][j-1]){
                i--;
            }
            else{
                j--;
            }
        }
        return sb.reverse().toString();
        //TC: O(M*N) for dp + O(N+M) for backtracking - we have to use 2d array for printing.
        //SC: O(M*N)
    }

    //https://www.geeksforgeeks.org/problems/geek-jump/1
    public int minimumEnergy(int arr[],int n){
        //code here
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return energy(arr, n-1, dp);
    }

    public int energy(int[] arr, int n, int[] dp){
        if(n==0){
            return 0;
        }
        if(dp[n]!=-1){
            return dp[n];
        }

        int oneStepHeight = Math.abs(arr[n]-arr[n-1]) + energy(arr, n-1, dp);
        int twoStepHeight = Integer.MAX_VALUE;
        if(n>1){
            twoStepHeight = Math.abs(arr[n]-arr[n-2]) + energy(arr, n-2, dp);
        }
        return dp[n] = Math.min(oneStepHeight, twoStepHeight);
    }

    public int minimizeCost(int arr[], int N, int K) {
        // code here
        int[] dp = new int[N+1];
        Arrays.fill(dp, -1);
        return minimizeCostHelper(arr, N, K, dp);
    }

    public int minimizeCostHelper(int[] arr, int N, int K, int[] dp){
        if(N==1){
            return 0;
        }
        if(dp[N]!=-1){
            return dp[N];
        }
        int cost = Integer.MAX_VALUE;
        for(int i = 1; i<=K; i++){
            if(N-1-i>=0){
                int kthStepCost = Math.abs(arr[N-1] - arr[N-1-i]) + minimizeCostHelper(arr, N-i, K, dp);
                cost = Math.min(cost, kthStepCost);
            }
        }
        return dp[N] = cost;
    }
}
