package org.example;

public class SlidingWindow {

    //https://www.naukri.com/code360/problems/longest-subarray-with-sum-k_6682399
    public static int longestSubarrayWithSumK(int []a, long k) {
        // Write your code here
        int l = 0, r =0; int n = a.length;
        int max = 0; long sum = 0;
        while(r<n){
            sum+=a[r];
            while(sum>k){
                sum-=a[l];
                l++;
            }
            if(sum==k){
                max = Math.max(max, r-l+1);
            }
            r++;
        }
        return max;
    }

    //Time: O(2N) => O(N), space O(1)

    public static int longestSubarrayWithSumK2(int []a, long k) {
        // Write your code here
        int l = 0, r =0; int n = a.length;
        int max = 0; long sum = 0;
        while(r<n){
            sum+=a[r];
            if(sum>k){ //
                sum-=a[l];
                l++;
            }
            if(sum==k){
                max = Math.max(max, r-l+1);
            }
            r++;
        }
        return max;
    }

    //Time: O(2N) => O(N), space O(1)
}
