package org.example;

import java.util.*;

public class SlidingWindow {


    //Pattern 1:
    //Subarray/substring size is fixed, aka, K is fixed

    //https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1
    static long maximumSumSubarray(int K, ArrayList<Integer> Arr, int N) {
        // code here
        int l = 0;
        int r = 0;
        long sum = 0;
        long maxSum = 0;
        while (r < Arr.size()) {
            sum += Arr.get(r);
            if (r - l + 1 < K) {
                r++;
            } else {
                maxSum = Math.max(maxSum, sum);
                sum -= Arr.get(l);
                l++;
                r++;
            }
        }
        return maxSum;
    }

    //Time: O(n) -> each element is added exactly once and subtracted once.
    //https://www.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1
    //Brute force
    public static long[] printFirstNegativeInteger(long A[], int N, int K) {
        long[] ans = new long[N - K + 1];
        for (int i = 0; i < N - K + 1; i++) {
            for (int j = i; j < i + K && j < N; j++) {
                if (A[j] < 0) {
                    ans[i] = A[j];
                    break;
                }
            }
        }
        return ans;
    }

    //Time: O(N*K)

    //Optimal using sliding window
    public long[] printFirstNegativeInteger2(long A[], int N, int k) {
        Queue<Long> q = new LinkedList<>();
        int l = 0;
        int r = 0;
        long[] ans = new long[N - k + 1];
        while (r < N) {
            if (A[r] < 0) {
                q.offer(A[r]);
            }
            if (r - l + 1 == k) {
                ans[l] = q.isEmpty() ? 0 : q.peek();
                if (A[l] < 0) {
                    q.remove();
                }
                l++;
            }
            r++;
        }
        return ans;
    }
    //Time: O(N)
    //Space: O(k)

    ArrayList<Integer> countDistinct(int A[], int n, int k) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        int l = 0;
        int r = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (r < n) {
            map.put(A[r], map.getOrDefault(A[r], 0) + 1);
            if (map.get(A[r]) == 1) count++;
            if (r - l + 1 == k) {
                ans.add(count);
                map.put(A[l], map.get(A[l]) - 1);
                // If the frequency becomes zero, reduce the distinct count
                if (map.get(A[l]) == 0) {
                    count--;
                }
                l++;
            }
            r++;
        }
        return ans;
    }

    //https://www.naukri.com/code360/problems/longest-subarray-with-sum-k_6682399
    public static int longestSubarrayWithSumK(int[] a, long k) {
        // Write your code here
        int l = 0, r = 0;
        int n = a.length;
        int max = 0;
        long sum = 0;
        while (r < n) {
            sum += a[r];
            while (sum > k) {
                sum -= a[l];
                l++;
            }
            if (sum == k) {
                max = Math.max(max, r - l + 1);
            }
            r++;
        }
        return max;
    }


    //Time: O(2N) => O(N), space O(1)

    public static int longestSubarrayWithSumK2(int[] a, long k) {
        // Write your code here
        int l = 0, r = 0;
        int n = a.length;
        int max = 0;
        long sum = 0;
        while (r < n) {
            sum += a[r];
            if (sum > k) { //
                sum -= a[l];
                l++;
            }
            if (sum == k) {
                max = Math.max(max, r - l + 1);
            }
            r++;
        }
        return max;
    }

    //https://www.geeksforgeeks.org/problems/max-sum-in-sub-arrays0824/0
    public static long pairWithMaxSum(long arr[], long N) {
        long max = Integer.MIN_VALUE;
        int l = 0;
        int r = 0;
        long sum = 0;
        while (r < N) {
            sum += arr[r];
            if (r - l + 1 == 2) {
                max = Math.max(max, sum);
                sum -= arr[l];
                l++;
            }
            r++;
        }
        return max;
    }

    //Time: O(2N) => O(N), space O(1)

    //Count occurences of anagrams
    int search(String pat, String txt) {
        // code here
        int l = 0;
        int r = 0;
        int count = 0;
        int k = pat.length();
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : pat.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                count++;
                map.put(c, 1);
            }
        }
        while (r < txt.length()) {
            char currentChar = txt.charAt(r);
            if (map.containsKey(currentChar)) { //check if it is a candidate
                int updatedFreq = map.get(currentChar) - 1;
                map.put(currentChar, updatedFreq);
                if (updatedFreq == 0) {
                    count--;
                }
            }

            if (r - l + 1 == k) { //slide
                if (count == 0) res++;
                char prevChar = txt.charAt(l);
                if (map.containsKey(prevChar)) {
                    int updatedFreq = map.get(prevChar) + 1;
                    map.put(prevChar, updatedFreq);
                    if (updatedFreq == 1) {
                        count++;
                    }
                }
                l++;
            }
            r++;
        }
        return res;
    }
    //Time: O(k+n) where k is length of pattern and n is length of txt.

    static ArrayList<Integer> max_of_subarrays(int arr[], int n, int k) {
        // Your code here
        Deque<Integer> q = new LinkedList<>();
        int l = 0;
        int r = 0;
        ArrayList<Integer> res = new ArrayList<>();
        while (r < n) {
            while (!q.isEmpty() && q.peekLast() < arr[r]) {
                q.removeLast();
            }
            q.add(arr[r]);
            if (r - l + 1 == k) {
                res.add(q.peek());
                if (q.peek() == arr[l]) {
                    q.removeFirst();
                }
                l++;
            }
            r++;
        }
        return res;
    }

    //https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1
    public int longestkSubstr(String s, int k) {
        // code here
        int max = -1;
        int l = 0;
        int r = 0;
        int unique = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (r < s.length()) {
            char c = s.charAt(r);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) == 1) {
                unique++;
            }
            while (unique > k) {
                char start = s.charAt(l);
                map.put(start, map.get(start) - 1);
                if (map.get(start) == 0) {
                    unique--;
                }
                l++;
            }
            if (unique == k) {
                max = Math.max(max, r - l + 1);
            }
            r++;
        }
        return max;
    }

    //https://leetcode.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int l = 0;
        int r = 0;
        int n = s.length();
        int max = 0;
        while (r < n) {
            char c = s.charAt(r);
            while (set.contains(c)) {
                char firstChar = s.charAt(l);
                set.remove(firstChar);
                l++;
            }
            set.add(c);
            max = Math.max(max, set.size());
            r++;
        }
        return max;
    }

    public int longestOnes(int[] nums, int k) {
        int flipped = 0;
        int l = 0;
        int r = 0;
        int maxLen = 0;
        while(r<nums.length){
            if(nums[r]==0){
                flipped++;
            }
            while(flipped>k){
                if(nums[l]==0){
                    flipped--;
                }
                l++;
            }
            maxLen = Math.max(maxLen, r-l+1);
            r++;
        }
        return maxLen;
    }
}
