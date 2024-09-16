package org.example.top150;

import org.example.ListNode;
import org.example.trees.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class Problems {

    //https://leetcode.com/problems/merge-sorted-array/?envType=study-plan-v2&envId=top-interview-150
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //maintain two pointers one at m and one at n
        //maintain a k pointer at m + n
        //put larger element on k and move corresponding pointer
        int i = m-1;
        int j = n-1;
        int k = m + n - 1;
        while(i>=0 && j>=0){
            if(nums1[i]>nums2[j]){
                nums1[k] = nums1[i];
                i--;
            }
            else{
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        while(j>=0){
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }

    //https://leetcode.com/problems/minimum-size-subarray-sum/?envType=study-plan-v2&envId=top-interview-150
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = 0, n = nums.length; int sum = 0; int min = Integer.MAX_VALUE;
        while(r<n){
            sum+=nums[r];
            while(sum>=target){
                min = Math.min(min, r-l+1);
                sum-=nums[l];
                l++;
            }
            r++;
        }
        return min==Integer.MAX_VALUE ? 0 : min;
    }

    //Trapping rain water
    public int trap(int[] height) {
        int amt = 0;
        for(int i = 0; i<height.length; i++){
            int leftBound = -1;
            int rightBound = -1;
            for(int j = i-1; j>=0; j--){
                if(height[j]>height[i]){
                    leftBound = Math.max(leftBound, height[j]);
                }
            }
            for(int j = i+1; j<height.length; j++){
                rightBound = Math.max(rightBound, height[j]);
            }
            if(leftBound==-1 || rightBound==-1 || leftBound<=height[i] || rightBound<=height[i]){
                continue;
            }
            amt += Math.min(leftBound, rightBound) - height[i];
        }
        return amt;
    }
    //brute force
    //TLE
    //TC: O(N*N)
    //space: O(1)

    public int trapDP(int[] height) {
        int amt = 0;
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        int leftMaxVal = 0;
        for(int i = 0; i<height.length; i++){
            leftMax[i] = Math.max(height[i], leftMaxVal);
            leftMaxVal = leftMax[i];
        }
        int rightMaxVal = 0;
        for(int i = height.length-1; i>=0; i--){
            rightMax[i] = Math.max(height[i], rightMaxVal);
            rightMaxVal = rightMax[i];
        }
        for(int i = 0; i<height.length; i++){
            amt += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return amt;
    }
    //DP
    //TC: O(N) - 3 iterations
    //Space: O(N)

    //Using stacks

    //Two pointer
    public int trapTwoPointer(int[] height) {
        int amt = 0;
        int i = 0;
        int j = height.length-1;
        int left_max=0; int right_max=0;
        while(i!=j){
            // System.out.println(i + " " + j + " " + left_max + " " + right_max);
            if(height[i]<=height[j]){
                left_max=Math.max(left_max, height[i]);
                amt+=left_max-height[i];
                i++;
            }
            else{
                right_max=Math.max(right_max, height[j]);
                amt+=right_max-height[j];
                j--;
            }
        }
        return amt;
    }

    //https://leetcode.com/problems/game-of-life/?envType=study-plan-v2&envId=top-interview-150
    public void gameOfLife(int[][] board) {
        int[] drow = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dcol = {-1, 0, 1, 1, 1, 0, -1, -1};
        int m = board.length;
        int n = board[0].length;
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                int ones = 0;
                int zeroes = 0;
                for(int k = 0; k<8; k++){
                    int nrow = i + drow[k];
                    int ncol = j + dcol[k];
                    if(nrow<0 || ncol<0 || nrow>=m || ncol>=n){
                        continue;
                    }
                    int oldState = board[nrow][ncol] & 1;
                    if(oldState==0){
                        zeroes++;
                    }
                    else{
                        ones++;
                    }
                }
                if(board[i][j] == 0 && ones==3){
                    board[i][j] = 2;
                }
                else if(board[i][j]==1){
                    if(ones<2 || ones>3){
                        board[i][j] = 1;
                    }
                    else{
                        board[i][j] = 3;
                    }
                }
            }
        }
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                board[i][j] = board[i][j]==2 || board[i][j]==3 ? 1 : 0;
            }
        }
    }

    //https://leetcode.com/problems/minimum-path-sum/description/?envType=study-plan-v2&envId=top-interview-150
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length+1][grid[0].length+1];
        for(int[] row: dp){
            Arrays.fill(row, -1);
        }
        return findminsum(grid, 0, 0, dp);
    }
    public int findminsum(int[][] grid, int m, int n, int[][] dp){
        if(dp[m][n]!=-1){
            return dp[m][n];
        }
        if(m==grid.length && n==grid[0].length){
            return 0; //reached end
        }
        if(m>=grid.length || n>=grid[0].length){
            return Integer.MAX_VALUE;
        }

        int right = findminsum(grid, m, n+1, dp);
        int down = findminsum(grid, m+1, n, dp);

        if(right==Integer.MAX_VALUE && down==Integer.MAX_VALUE){
            return grid[m][n];
        }
        return dp[m][n] = grid[m][n] + Math.min(right, down);
    }

    //tabulation
    public int minPathSumTabulation(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i<=m; i++){
            dp[i][0] = Integer.MAX_VALUE;
        }
        for(int j = 0; j<=n; j++){
            dp[0][j] = Integer.MAX_VALUE;
        }
        for(int i = 1; i<=m; i++){
            for(int j = 1; j<=n; j++){
                if(dp[i-1][j]==Integer.MAX_VALUE && dp[i][j-1]==Integer.MAX_VALUE){
                    dp[i][j] = grid[i-1][j-1];
                }
                else{
                    dp[i][j] = grid[i-1][j-1] + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    //Space optimized
    public int minPathSumSO(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] prev = new int[n+1]; // should be the longer one
        int[] curr = new int[n+1];
        Arrays.fill(prev, Integer.MAX_VALUE);
        Arrays.fill(curr, Integer.MAX_VALUE);
        for(int i = 1; i<=m; i++){
            for(int j = 1; j<=n; j++){
                if(prev[j]==Integer.MAX_VALUE && curr[j-1]==Integer.MAX_VALUE){
                    curr[j] = grid[i-1][j-1];
                }
                else{
                    curr[j] = grid[i-1][j-1] + Math.min(prev[j], curr[j-1]);
                }
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[n];
    }
    //Sum root to leaf numbers
    public int sumNumbers(TreeNode root) {
        int[] sum = {0};
        StringBuilder sb = new StringBuilder();
        findSum(root, sum, sb);
        return sum[0];
    }

    public void findSum(TreeNode root, int[] sum, StringBuilder sb){
        if(root==null){
            return;
        }
        sb.append(root.data);
        if(root.left==null && root.right==null){
            sum[0]+=Integer.parseInt(sb.toString());
        }
        else{
            findSum(root.left, sum, sb);
            findSum(root.right, sum, sb);
        }
        sb.setLength(sb.length()-1);
    }

    public int sumNumbersSecond(TreeNode root) {
        return findSum(root, 0);
    }

    public int findSum(TreeNode root, int sum){
        if(root==null){
            return 0;
        }
        sum = (root.data + sum*10);
        if(root.left==null && root.right==null){
            return sum;
        }

        return findSum(root.left, sum) + findSum(root.right, sum);
    }

    //Two sum 2 - sorted array.
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length-1;
        while(i<j){
            int sum = numbers[i] + numbers[j];
            if(sum==target){
                return new int[]{i+1, j+1};
            }
            if(sum>=target){
                j--;
            }
            else{
                i++;
            }
        }
        return null;
    }

    //Jump 2 using Greedy
    public int jump(int[] nums) {
        int l = 0;
        int r = 0;
        int jumps = 0;
        while(r<nums.length-1){
            int farthest = 0;
            for(int i = l; i<=r; i++){
                farthest = Math.max(farthest, i + nums[i]);
            }
            jumps++;
            l = r+1;
            r = farthest;
        }
        return jumps;
    }
    //TC:: O(n) -- dp soln for this is O(n^2)

    //find ceil in BST
    int findCeil(TreeNode root, int key) {
        // Code here
        int ceil = -1;
        while(root!=null){
            if(root.data==key){
                return root.data;
            }
            else if(root.data<key){
                root = root.right;
            }
            else{
                ceil = root.data;
                root = root.left;
            }
        }
        return ceil;
    }

    //https://leetcode.com/problems/insert-into-a-binary-search-tree/
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null){
            return new TreeNode(root.data);
        }
        if(val<root.data){
            root.left = insertIntoBST(root.left, val);
        }
        else{
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    //Minimum falling path sum
    public int minFallingPathSum(int[][] matrix) {
        int col = matrix.length;
        int[] prev = new int[col+1];
        prev[0] = Integer.MAX_VALUE;
        for(int i = 1; i<=col; i++){
            int[] curr = new int[col+1];
            curr[0] = Integer.MAX_VALUE;
            for(int j = 1; j<=col; j++){
                if(j==col){
                    curr[j] = matrix[i-1][j-1] + Math.min(prev[j], prev[j-1]);
                }
                else{
                    curr[j] = matrix[i-1][j-1] + Math.min(prev[j], Math.min(prev[j-1], prev[j+1]));
                }
            }
            prev = curr;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i<=col; i++){
            min = Math.min(min, prev[i]);
        }
        return min;
    }

    //count and say
    public String countAndSay(int n) {
        return recurse(1, n, "");
    }

    public String recurse(int index, int n, String op){
        if(index>n){
            return op;
        }
        if(index==1){
            return recurse(index+1, n, "1");
        }
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<op.length(); i++){
            if(op.charAt(i-1)==op.charAt(i)){
                count++;
            }
            else{
                sb.append(count).append(op.charAt(i-1));
                count=1;
            }
        }
        sb.append(count).append(op.charAt(op.length()-1));
        return recurse(index+1, n, sb.toString());
    }

    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, ListNode<Integer> head) {
        // code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ListNode<Integer> front = head;
        ListNode<Integer> end = head;
        while(end.next!=null){
            end = end.next;
        }
        while(front!=end){
            if(front.data + end.data == target){
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(front.data);
                pair.add(end.data);
                res.add(pair);
                front = front.next;
            }
            else if(front.data + end.data > target){
                end = end.prev;
            }
            else{
                front = front.next;
            }
        }
        return res;
    }

    //Largest rectangle in histogram
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        int[] smallestOnLeft = sol(heights);
        int[] smallestOnRight = sor(heights);
        for(int i = 0; i<heights.length; i++){
            int currHeight = (smallestOnRight[i]-smallestOnLeft[i]-1) * heights[i];
            max = Math.max(currHeight, max);
        }
        return max;
    }

    public int[] sol(int[] heights){
        int[] smallestOnLeft = new int[heights.length];
        Deque<Integer> s = new ArrayDeque<>();
        for(int i = 0; i<heights.length; i++){
            while(!s.isEmpty() && heights[s.peek()]>=heights[i]){
                s.pop();
            }
            smallestOnLeft[i] = s.isEmpty() ? -1 : s.peek();
            s.push(i);
        }
        return smallestOnLeft;
    }

    public int[] sor(int[] heights){
        int n = heights.length;
        int[] smallestOnRight = new int[n];
        Deque<Integer> s = new ArrayDeque<>();
        for(int i = heights.length-1; i>=0; i--){
            while(!s.isEmpty() && heights[s.peek()]>=heights[i]){
                s.pop();
            }
            smallestOnRight[i] = s.isEmpty() ? n : s.peek();
            s.push(i);
        }
        return smallestOnRight;
    }
    //Above soln is brute force.

    public int largestRectangleAreaOptimized(int[] heights) {
        int max = 0;
        Deque<Integer> s = new ArrayDeque<>();
        for(int i = 0; i<heights.length; i++){
            while(!s.isEmpty() && heights[s.peek()]>heights[i]){
                int barIndex = s.pop();
                int pse = s.isEmpty() ? -1 : s.peek();
                int area = heights[barIndex] * (i - pse - 1);
                max = Math.max(max, area);
            }
            s.push(i);
        }
        while(!s.isEmpty()){
            int barIndex = s.pop();
            int pse = s.isEmpty() ? -1 : s.peek();
            int nse = heights.length;
            int area = heights[barIndex] * (nse - pse - 1);
            max = Math.max(max, area);
        }
        return max;
    }
    //ONE PASS SOLN

    //https://leetcode.com/problems/reverse-words-in-a-string
    public String reverseWords(String s) {
        String[] words = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if (words[i] != "") {
                sb.append(words[i]);
                if (i != 0) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }

    private static int mod = (int)1e9+7;
    public static int countPartitions(int n, int d, int[] arr) {
        // code here
        int sum = 0;
        for(int i = 0; i<n; i++){
            sum+=arr[i];
        }
        if ((sum + d) % 2 != 0) {
            return 0;
        }
        int targetSum = (sum + d)/2;
        int[][] dp = new int[n+1][targetSum+1];
        for(int[] row: dp){
            Arrays.fill(row, -1);
        }
        int partitions = findSubsetWithSum(arr, targetSum, 0, dp);
        return partitions % mod;
    }

    public static int findSubsetWithSum(int[] arr, int sum, int index, int[][] dp){
        if(index==arr.length){
            return sum==0 ? 1 : 0;
        }
        if(dp[index][sum]!=-1){
            return dp[index][sum];
        }
        int pick = 0;
        if(arr[index]<=sum){
            pick = findSubsetWithSum(arr, sum-arr[index], index+1, dp) % mod;
        }
        int dontPick = findSubsetWithSum(arr, sum, index+1, dp) % mod;
        return dp[index][sum] = (pick + dontPick) % mod;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        int res = 0;
        for(int pile : piles){
            high = Math.max(high, pile);
        }
        while(low<=high){
            int mid = low + (high-low)/2;
            if(isValid(piles, mid, h)){
                res = mid;
                high = mid-1;
            }
            else{
                low = mid+1;
            }
        }
        return res;
    }

    public boolean isValid(int[] piles, int k, int h){
        int currHour = 0;
        for(int i=0; i<piles.length; i++){
            int currPile = piles[i];
            currHour+=Math.ceil((double)currPile/k);
            if(currHour>h){
                return false;
            }
        }
        return true;
    }

    public int splitArray(int[] nums, int k) {
        int low = 0;
        int high = 0;
        int res = 0;
        for(int num : nums){
            low = Math.max(low, num);
            high+=num;
        }
        while(low<=high){
            int mid = low + (high-low)/2;
            if(canBeSplit(mid, nums, k)){
                res = mid;
                high = mid-1;
            }
            else{
                low = mid+1;
            }
        }
        return res;
    }

    public boolean canBeSplit(int largestSum, int[] nums, int maxSubArrays){
        int curSubArrays = 1;
        int currSum = 0;
        for(int num : nums){
            if(currSum+num<=largestSum){
                currSum+=num;
            }
            else{
                currSum=num;
                curSubArrays++;
                if(curSubArrays>maxSubArrays){
                    return false;
                }
            }
        }
        return true;
    }

    public static int maximumOnesRow(ArrayList<ArrayList<Integer>> matrix, int n, int m)
    {
        //	  Write your code here.
        int maxOne = 0;
        int index = 0;
        int max = 0;
        for(int i=0; i<matrix.size(); i++){
            int j = getFirstOccurrenceOfOne(matrix.get(i));
            if(j==-1){
                continue;
            }
            int ctr = matrix.get(i).size()-j;
            if(ctr>maxOne){
                maxOne = ctr;
                index = i;
            }
        }
        return index;
    }

    public static int getFirstOccurrenceOfOne(ArrayList<Integer> list){
        int low = 0;
        int high = list.size()-1;
        int res = -1;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(list.get(mid)==1){
                res = mid;
                high = mid-1;
            }
            else{
                low = mid+1;
            }
        }
        return res;
    }
}
