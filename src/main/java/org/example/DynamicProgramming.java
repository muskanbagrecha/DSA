package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//Tabulation is also known as bottom up
//Memoization is also known as top down

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

    public int changeSpaceOptimized(int amount, int[] coins) {
        int n = coins.length;
        int[] prev = new int[amount+1];
        int[] curr = new int[amount+1];
        prev[0] = 1;
        for(int i = 1; i<=n; i++){
            curr[0] = 1;
            for(int j = 1; j<=amount; j++){
                if(coins[i-1]<=j){
                    curr[j] = curr[j-coins[i-1]] + prev[j];
                }
                else{
                    curr[j] = prev[j];
                }
            }
            prev = curr;
        }
        return prev[amount];
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

    public static int longestCommonSubsequenceTabulation(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 1; i<=m; i++){
            for(int j = 1; j<=n; j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else if(dp[i-1][j]>dp[i][j-1]){
                    dp[i][j] = dp[i-1][j];
                }
                else{
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        //print dp table
        for(int i = 0; i<=m; i++){
            for(int j = 0; j<=n; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[m][n];
    }

    public int longestCommonSubsequence1D(String text1, String text2) {
        if (text2.length() > text1.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }
        int m = text1.length(), n = text2.length();
        int[] prev = new int[n+1];
        int[] curr = new int[n+1];
        for(int i = 1; i<=m; i++){
            for(int j = 1; j<=n; j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    curr[j] = 1 + prev[j-1];
                }
                else{
                    curr[j] = Math.max(curr[j-1], prev[j]);
                }
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[n];
    }
    //Space optimized.
    //TC: O(m*n)
    //SC: O(n)

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

    //https://leetcode.com/problems/house-robber
    // Memoization
    public int rob(int[] nums) {
        int[] dp = new int[nums.length+1];
        Arrays.fill(dp, -1);
        return robHelper(nums, nums.length, dp);
    }

    public int robHelper(int[] nums, int n, int[] dp){
        if(n==0){
            return 0;
        }
        if(n==1){
            return nums[n-1];
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        return dp[n] = Math.max(robHelper(nums, n-1, dp), nums[n-1] + robHelper(nums, n-2, dp));
    }
    //Time: O(N)
    //Space: O(N)

    //Tabulation
    public int robTabulation(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        }
        return dp[n];
    }
    //Space: O(n)

    //Using constant space
    public int rob3(int[] nums) {
        int n = nums.length;
        int prev2 = 0;
        int prev = nums[0];
        int curr = nums[0];
        for(int i = 2; i<=n; i++){
            curr = Math.max(prev, nums[i-1] + prev2);
            prev2 = prev;
            prev = curr;
        }
        return curr;
    }
    //SC: O(1)

    public int maximumPoints(int points[][], int N) {
        // code here
        int[][] dp = new int[N+1][3];
        for(int i = 0; i<=N; i++){
            Arrays.fill(dp[i], -1);
        }
        return findMaxPoints(points, N, -1, dp);
    }

    public int findMaxPoints(int points[][], int N, int prev, int[][] dp){
        if(prev!=-1 && dp[N][prev]!=-1){
            return dp[N][prev];
        }
        if(N==0){
            return 0;
        }
        int maxi = 0;
        for(int i = 0; i<3; i++){
            if(i!=prev){
                maxi = Math.max(maxi, points[N-1][i] + findMaxPoints(points, N-1, i, dp));
            }
        }
        if(prev!=-1){
            dp[N][prev] = maxi;
        }
        return maxi;
    }
    //Above TC: O(N*3)
    //SC: O(N) - recursion at max N days before you explore different pattern + O(N*3) - dp array

    //Tabulation
    public int maximumPointsTabulation(int points[][], int N) {
        // code here
        int[][] dp = new int[N][3];
        dp[0][0] = points[0][0];
        dp[0][1] = points[0][1];
        dp[0][2] = points[0][2];
        for(int i = 1; i<N; i++){
            dp[i][0] = points[i][0] + Math.max(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = points[i][1] + Math.max(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = points[i][2] + Math.max(dp[i-1][0], dp[i-1][1]);
        }
        int maxi = 0;
        for(int i = 0; i<3; i++){
            maxi = Math.max(maxi, dp[N-1][i]);
        }
        return maxi;
    }
    //TC: O(N*3)
    //SC: O(N*3)

    //https://leetcode.com/problems/unique-paths/
    //Without DP
    public int uniquePaths(int m, int n) {
        return paths(m, n, 0, 0);
    }

    public int paths(int m, int n, int i, int j){
        if(i>=m || j>=n){
            return 0;
        }
        if(i==m-1 && j==n-1){
            return 1;
        }
        return paths(m, n, i+1, j) + paths(m, n, i, j+1);
    }
    //Memoization
    public int uniquePathsMemo(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        return paths(m, n, 0, 0, dp);
    }

    public int paths(int m, int n, int i, int j, int[][] dp){
        if(i>=m || j>=n){
            return 0;
        }
        if(i==m-1 && j==n-1){
            return 1;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        return dp[i][j] = paths(m, n, i+1, j, dp) + paths(m, n, i, j+1, dp);
    }

    //Tabulation
    public int uniquePathsTabulation(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(i==0 || j==0){
                    dp[i][j] = 1;
                }
                else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    //Space optimization
    public int uniquePathsTabulation2(int m, int n){
        int[] prevRow = new int[n];
        Arrays.fill(prevRow, 1);
        for(int i = 1; i<m; i++){
            int[] current = new int[n];
            Arrays.fill(current, 1);
            for(int j = 1; j<n; j++){
                current[j] = current[j-1] + prevRow[j];
            }
            prevRow=current;
        }
        return prevRow[n-1];
    }

    //https://leetcode.com/problems/unique-paths-ii/
    //Memo
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i<=m; i++){
            Arrays.fill(dp[i], -1);
        }
        return countPaths(obstacleGrid, 0, 0, dp);
    }

    public int countPaths(int[][] grid, int m, int n, int[][] dp){
        if(dp[m][n]!=-1){
            return dp[m][n];
        }
        if(m>=grid.length || n>=grid[0].length || grid[m][n]==1){
            return 0;
        }
        if(m==grid.length-1 && n==grid[0].length-1){
            return 1;
        }
        return dp[m][n] = countPaths(grid, m+1, n, dp) + countPaths(grid, m, n+1, dp);
    }

    //Tabulation
    public int uniquePathsWithObstaclestabulation(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = obstacleGrid[0][0]==1?0:1;
        for(int i = 1; i<m; i++){
            if(obstacleGrid[i][0]==1 || dp[i-1][0]==0){
                dp[i][0] = 0;
            }
            else{
                dp[i][0] = 1;
            }
        }
        for(int j = 1; j<n; j++){
            if(obstacleGrid[0][j]==1 || dp[0][j-1]==0){
                dp[0][j] = 0;
            }
            else{
                dp[0][j] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    //Space optimized
    public int uniquePathsWithObstaclesSpaceOptimized(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = obstacleGrid[0][0]==1?0:1;
        for(int j = 1; j<n; j++){
            if(obstacleGrid[0][j]==1 || dp[j-1]==0){
                dp[j] = 0;
            }
            else{
                dp[j] = 1;
            }
        }
        int[] current = new int[n];
        for (int i = 1; i < m; i++) {
            current[0] = obstacleGrid[i][0] == 1 ? 0 : dp[0];
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    current[j] = 0;
                } else {
                    current[j] = dp[j] + current[j - 1];
                }
            }
            dp = current;
        }
        return dp[n-1];
    }


    //https://leetcode.com/problems/word-break
    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean[][] dp = new Boolean[s.length()+2][s.length()+2];
        return segment(s, wordDict, 0, 1, dp);
    }

    public boolean segment(String s, List<String> wordDict, int start, int curr, Boolean[][] dp){
        if(dp[start][curr]!=null){
            return dp[start][curr];
        }
        if(curr>s.length()){
            return false; //it was not able to satisfy
        }
        String word = s.substring(start, curr);
        boolean selectCurrentWord = false;
        if(wordDict.contains(word)){
            if(curr==s.length()){
                return dp[start][curr] = true;
            }
            selectCurrentWord = segment(s, wordDict, curr, curr+1, dp);
            dp[curr][curr+1] = selectCurrentWord;
        }
        if(selectCurrentWord || segment(s, wordDict, start, curr+1, dp)){
            return dp[start][curr] = true;
        }
        return dp[start][curr] = false;
    }

    //TODO: implement space optimized soln in memo and tabulation.

    //https://leetcode.com/problems/jump-game
    public boolean canJump(int[] nums) {
        Boolean[] dp = new Boolean[nums.length];
        return jump(0, nums, dp);
    }

    public boolean jump(int index, int[] nums, Boolean[] dp){
        if(dp[index]!=null){
            return dp[index];
        }
        if(index==nums.length-1){
            return dp[index] = true;
        }
        for(int i = 1; i<=nums[index]; i++){
            if(jump(index + i, nums, dp)){
                return dp[index] = true;
            }
        }
        return dp[index] = false;
    }

    //Tabulation
    public boolean canJumpTabulation(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        for(int i = 0; i<n; i++){
            if(!dp[i])
                continue;
            int farthestJump = i+nums[i];
            if(farthestJump>=n){
                farthestJump = n-1;
            }
            for(int j = i+1; j<=farthestJump; j++){
                dp[j] = true;
            }
        }
        return dp[n-1];
    }

    //Actually this problem can be solvewd using greedy in O(N) -> refer greedy problems for the soln.

    //DP on stocks
    //Buy and sell stocks - 2
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return profit(prices, 0, 0, dp);
    }

    public int profit(int[] prices, int buy, int index, int[][] dp) {
        if (index == prices.length) {
            return 0;
        }

        if (dp[index][buy] != -1) {
            return dp[index][buy];
        }

        if (buy == 0) { // Nothing is bought
            int buyToday = -prices[index] + profit(prices, 1, index + 1, dp); // Buy today
            int skipToday = profit(prices, 0, index + 1, dp); // Skip today
            return dp[index][buy] = Math.max(buyToday, skipToday);
        } else { // A stock is already bought
            int sellToday = prices[index] + profit(prices, 0, index + 1, dp); // Sell today
            int hold = profit(prices, 1, index + 1, dp); // Hold the stock
            return dp[index][buy] = Math.max(sellToday, hold);
        }
    }
    //TC: O(N*2) + O(N) - recstack
    //SC: O(N*2)

    public int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for(int index = 1; index<n; index++){
            for(int buy = 0; buy<2; buy++){
                if(buy==0){
                    int buyToday = -prices[index] + dp[index - 1][1];
                    int skipToday = dp[index - 1][0];
                    dp[index][0] = Math.max(buyToday, skipToday);
                }
                else{
                    int sellStock = prices[index] + dp[index - 1][0];
                    int holdStock = dp[index - 1][1];
                    dp[index][1] = Math.max(sellStock, holdStock);
                }
            }
        }
        return dp[n-1][1];
    }
    //TC: O(N*2)
    //SC: O(N*2)

    public int maxProfitSpaceOptimized(int[] prices) {
        int n = prices.length;
        int[] prev = new int[2];
        int[] curr = new int[2];
        prev[0] = -prices[0];
        prev[1] = 0;
        for(int index = 1; index<n; index++){
            for(int buy = 0; buy<2; buy++){
                if(buy==0){
                    int buyToday = -prices[index] + prev[1];
                    int skipToday = prev[0];
                    curr[0] = Math.max(buyToday, skipToday);
                }
                else{
                    int sellStock = prices[index] + prev[0];
                    int holdStock = prev[1];
                    curr[1] = Math.max(sellStock, holdStock);
                }
            }
            prev = curr;
        }
        return prev[1];
    }
    //space optimized
    //TC: O(N*2)
    //SC: O(1)

    //https://www.geeksforgeeks.org/problems/shortest-common-supersequence0322/1
    public static int shortestCommonSuperSequence(String X,String Y,int m,int n)
    {
        if(m<n){
            String temp = X;
            X = Y;
            Y = temp;
        }
        m = X.length();
        n = Y.length();
        int[] prev = new int[n+1];
        int[] curr = new int[n+1];
        for(int i = 1; i<=m; i++){
            for(int j = 1; j<=n; j++){
                if(X.charAt(i-1)==Y.charAt(j-1)){
                    curr[j] = 1 + prev[j-1];
                }
                else{
                    curr[j] = Math.max(prev[j], curr[j-1]);
                }
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[n] + (m-prev[n]) + (n-prev[n]);
    }

    //print SCS
    public String shortestCommonSupersequence(String str1, String str2) {
        // Convert the strings to char arrays
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        int m = arr1.length;
        int n = arr2.length;
        int[][] dp = new int[m+1][n+1];
        int i, j;

        // Fill the dp table
        for(i = 1; i <= m; i++) {
            for(j = 1; j <= n; j++) {
                if(arr1[i-1] == arr2[j-1]) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // Build the shortest common supersequence from the dp table
        i = m; j = n;
        StringBuilder sb = new StringBuilder();
        while(i > 0 && j > 0) {
            if(arr1[i-1] == arr2[j-1]) {
                sb.append(arr1[i-1]);
                i--;
                j--;
            } else if(dp[i-1][j] > dp[i][j-1]) {
                sb.append(arr1[i-1]);
                i--;
            } else {
                sb.append(arr2[j-1]);
                j--;
            }
        }

        // Add the remaining characters from arr1 and arr2
        while(i > 0) {
            sb.append(arr1[i-1]);
            i--;
        }
        while(j > 0) {
            sb.append(arr2[j-1]);
            j--;
        }

        // Return the result as a string
        return sb.reverse().toString();
    }

    //Buy and sell stock - 3 //HARD
    public int maxProfitBuyAndSell3(int[] prices) {
        return profitHelper(prices, 0, 0, true);
    }

    public int profitHelper(int[] prices, int n, int noOfTxn, boolean ableToBuyStock){
        if(n==prices.length || noOfTxn==2){
            return 0;
        }
        if(ableToBuyStock){
            int bought = -prices[n] + profitHelper(prices, n+1, noOfTxn, false);
            int skip = profitHelper(prices, n+1, noOfTxn, true);
            return Math.max(bought, skip);
        }
        //sell
        int sellStock = prices[n] + profitHelper(prices, n+1, noOfTxn+1, true);
        int holdStock = profitHelper(prices, n+1, noOfTxn, false);
        return Math.max(sellStock, holdStock);
    }
    //above is brute force.

    public int maxProfitBuyAndSell3Memo(int[] prices) {
        int[][][] dp = new int[prices.length][2][2];
        for(int[][] row1: dp){
            for(int[] row2: row1){
                Arrays.fill(row2, -1);
            }
        }
        return maxProfitBuyAndSell3MemoProfit(prices, 0, 0, 1, dp);
    }

    public int maxProfitBuyAndSell3MemoProfit(int[] prices, int n, int noOfTxn, int ableToBuyStock, int[][][] dp){
        if(n==prices.length || noOfTxn==2){
            return 0;
        }
        if(dp[n][noOfTxn][ableToBuyStock]!=-1){
            return dp[n][noOfTxn][ableToBuyStock];
        }
        if(ableToBuyStock==1){
            int bought = -prices[n] + maxProfitBuyAndSell3MemoProfit(prices, n+1, noOfTxn, 0, dp);
            int skip = maxProfitBuyAndSell3MemoProfit(prices, n+1, noOfTxn, 1, dp);
            return dp[n][noOfTxn][ableToBuyStock] = Math.max(bought, skip);
        }
        //sell
        int sellStock = prices[n] + maxProfitBuyAndSell3MemoProfit(prices, n+1, noOfTxn+1, 1, dp);
        int holdStock = maxProfitBuyAndSell3MemoProfit(prices, n+1, noOfTxn, 0, dp);
        return dp[n][noOfTxn][ableToBuyStock] = Math.max(sellStock, holdStock);
    }
}
