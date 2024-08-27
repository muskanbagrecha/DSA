package org.example.top150;

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
}
