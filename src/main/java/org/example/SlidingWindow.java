package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindow {


    //Pattern 1:
    //Subarray/substring size is fixed, aka, K is fixed

    //https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1
    static long maximumSumSubarray(int K, ArrayList<Integer> Arr, int N){
        // code here
        int l = 0;
        int r = 0;
        long sum = 0;
        long maxSum = 0;
        while(r<Arr.size()){
            sum+=Arr.get(r);
            if(r-l+1<K){
                r++;
            }
            else{
                maxSum = Math.max(maxSum, sum);
                sum-=Arr.get(l);
                l++;
                r++;
            }
        }
        return maxSum;
    }

    //Time: O(n) -> each element is added exactly once and subtracted once.
    //https://www.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1
    //Brute force
    public static long[] printFirstNegativeInteger(long A[], int N, int K)
    {
        long[] ans = new long[N-K+1];
        for(int i = 0; i<N-K+1; i++){
            for(int j = i; j<i+K && j<N; j++){
                if(A[j]<0){
                    ans[i] = A[j];
                    break;
                }
            }
        }
        return ans;
    }

    //Time: O(N*K)

    //Optimal using sliding window
    public static long[] printFirstNegativeIntegerSW(long A[], int N, int K)
    {
        long[] ans = new long[N-K+1];
        int l = 0;
        int r = 0;
        Queue<Long> q = new LinkedList<>();
        while(r<N){
            if(A[r]<0){
                q.offer(A[r]);
            }
            if(r-l+1<K){
                r++;
            }
            else{
                ans[l] = q.isEmpty() ? 0: q.peek();
                if(!q.isEmpty() && A[l]==q.peek()){
                    q.remove();
                }
                l++;
                r++;
            }
        }
        return ans;
    }
    //Time: O(N)
    //Space: O(k)

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
