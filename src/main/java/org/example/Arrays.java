package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Arrays {

    /*
    * Second largest and second smallest
    * Input: 1,2,3,4,5
    * Output: 4, 2
    */
    public static int[] getSecondOrderElements(int [] a) {
        int n = a.length;
        // Write your code here.
        int[] ans = new int[2];
        ans[0] = secondLargest(n, a);
        ans[1] = secondSmallest(n, a);
        return ans;
    }

    public static int secondLargest(int n, int[] a){
        int max = Math.max(a[0], a[1]);
        int secondMax = Math.min(a[0], a[1]);
        for(int i = 2; i<n; i++){
            if(a[i]>max){
                secondMax = max;
                max = a[i];
            }
            else if(a[i]>secondMax){
                secondMax = a[i];
            }
        }
        return secondMax;
    }

    public static int secondSmallest(int n, int[] a){
        int min = Math.min(a[0], a[1]);
        int secondMin = Math.max(a[0], a[1]);
        for(int i = 2; i<n; i++){
            if(a[i]<min){
                secondMin = min;
                min = a[i];
            }
            else if(a[i]<secondMin){
                secondMin = a[i];
            }
        }
        return secondMin;
    }

    //Input: 8,9,9,10,10
    //Output: 9

    //TODO: fix this code
    public static int getSecondSmallestElementsWhenElementsAreRepeating(int [] a, int n) {
        int max = Math.max(a[0], a[1]);
        int secondMax = Math.min(a[0], a[1]);
        if(max==secondMax){
            max = secondMax = -1;
        }
        for(int i = 2; i<n; i++){
            if(a[i]>max){
                secondMax = max;
                max = a[i];
            }
            else if(a[i]>secondMax && a[i]!=max) {
                secondMax = a[i];
            }
        }
        return secondMax;
    }

    //Input: 4, 5, 1, 2, 3
    //Output: true

    // Input: 1, 4, 1, 2, 3
    // Output: false
    public static boolean checkIfRotatedArrayIsSorted(int[] arr) {
        int n = arr.length;
        int k = 0;
        for(int i = 0; i<n; i++){
            int nextIndex = (i+1)%n;
            if(arr[i]>arr[nextIndex]){
                k++;
            }
            if(k>1){
                return false;
            }
        }
        return true;
    }

    //Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
//    Example 1:
//
//    Input: nums = [1,1,2]
//    Output: 2, nums = [1,2,_]
//    Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
//    It does not matter what you leave beyond the returned k (hence they are underscores).

//    Example 2:
//    Input: nums = [0,0,1,1,1,2,2,3,3,4]
//    Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
//    Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
//    It does not matter what you leave beyond the returned k (hence they are underscores).
    public static int removeDuplicatesFromSortedArray(int[] arr){
        int n = arr.length;
        int j = 1;
        for(int i = 1; i<n; i++){
            if(arr[i]!=arr[i-1]){
                arr[j]=arr[i];
                j++;
            }
        }
        return j;
    }

//    Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
//    Input: nums = [1,2,3,4,5,6,7], k = 3
//    Output: [5,6,7,1,2,3,4]
//    Explanation:
//    rotate 1 steps to the right: [7,1,2,3,4,5,6]
//    rotate 2 steps to the right: [6,7,1,2,3,4,5]
//    rotate 3 steps to the right: [5,6,7,1,2,3,4]
//    Example 2:
//
//    Input: nums = [-1,-100,3,99], k = 2
//    Output: [3,99,-1,-100]
//    Explanation:
//    rotate 1 steps to the right: [99,-1,-100,3]
//    rotate 2 steps to the right: [3,99,-1,-100]

    //Brute force
    public static int[] rotate1(int[] nums, int k) {
        int n = nums.length;
        int[] rotatedElements = java.util.Arrays.copyOfRange(nums, 0, n);
        if(k>n){
            k = k%n;
        }
        int j=0;
        for(int i = n-k;i<n;i++){
            nums[j] = rotatedElements[i];
            j++;
        }
        for(int i = 0; i<n-k; i++){
            nums[j] = rotatedElements[i];
            j++;
        }
        return nums;
    }
    public static void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k%n;
        reverse(nums,0, n);
        reverse(nums, 0, k);
        reverse(nums, k,n);
        Helper.printArray(nums);
    }

    public static void reverse(int[] arr, int start, int end){
        while(start<end)
        {
            int i = start;
            int j = end-1;
            int temp = arr[i];
            arr[i] =  arr[j];
            arr[j] = temp;
            start++;
            end--;
        }
    }

    public static ArrayList<Integer> rotateArray(ArrayList<Integer> arr, int k) {
        // Write your code here.
        int n = arr.size();
        k = k % n;
        reverse(arr, 0, n);
        reverse(arr, 0, n-k);
        reverse(arr, n-k, n);
        return arr;
    }

    public static void reverse(ArrayList<Integer> arr, int start, int end){
        // int j = end-1;
        while(start<end)
        {
            int temp = arr.get(start);
            arr.set(start, arr.get(end-1));
            arr.set(end-1, temp);
            start++;
            end--;
        }
    }
//    Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
//    Note that you must do this in-place without making a copy of the array.

//    Input: nums = [0,1,0,3,12]
//    Output: [1,3,12,0,0]

//    Input: nums = [0]
//    Output: [0]

    //Brute force
    public void moveZeroes1(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i = 0; i<n; i++){
            if(arr[i]!=0){
                temp.add(arr[i]);
            }
        }
        for(int i = 0; i<temp.size();i++){
            arr[i] = temp.get(i);
        }
        for(int i = temp.size(); i<n; i++){
            arr[i] = 0;
        }
    }
    //Time: O(2n) = O(n)
    //Space: O(n) //worst case

    //Optimal
    public static void moveZeroes2(int[] arr) {
        int n = arr.length;
        int j=-1;
        for(int i = 0; i<n; i++){
            if(arr[i]==0){
                j = i;
                break;
            }
        }
        if(j==-1){
            return;
        }
        for(int i=j+1; i<n;i++){
            if(arr[i]!=0){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
    }

    //Snowball approach: https://leetcode.com/problems/move-zeroes/solutions/172432/the-easiest-but-unusual-snowball-java-solution-beats-100-o-n-clear-explanation
    public void moveZeroes(int[] arr) {
        int n = arr.length;
        int snowBall = 0;
        for(int i = 0; i<n; i++){
            if(arr[i] == 0){
                snowBall++;
            }
            else{
                swap(arr, i, i-snowBall);
            }
        }
    }
    public void swap(int[] arr, int first, int second){
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

//    Problem statement
//    Given two sorted arrays, ‘a’ and ‘b’, of size ‘n’ and ‘m’, respectively, return the union of the arrays.
//    The union of two sorted arrays can be defined as an array consisting of the common and the distinct elements of the two arrays. The final array should be sorted in ascending order.
//    Note: 'a' and 'b' may contain duplicate elements, but the union array must contain unique elements.

//    Brute force
//    Time complexity: O(aloga) + O(blog(a+b) + O(a+b) = O((a + b) log (a + b)) //simplified
    public static List< Integer > sortedArray(int []a, int []b) {
        // Write your code here
        TreeSet<Integer> set = new TreeSet<>();
        for(int i=0; i<a.length; i++){
            set.add(a[i]);
        }
        for(int i=0; i<b.length; i++){
            set.add(b[i]);
        }
        List<Integer> list = new ArrayList<>();
        for(int num:set){
            list.add(num);
        }
        return list;
    }

    //Optimal
    public static List< Integer > sortedArray2(int []a, int []b) {
        // Write your code here
        TreeSet<Integer> set = new TreeSet<>();
        for(int i=0; i<a.length; i++){
            set.add(a[i]);
        }
        for(int i=0; i<b.length; i++){
            set.add(b[i]);
        }
        List<Integer> list = new ArrayList<>();
        for(int num:set){
            list.add(num);
        }
        return list;
    }
//    Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
//    You must implement a solution with a linear runtime complexity and use only constant extra space.

//    Input: nums = [2,2,1]
//    Output: 1

//    Input: nums = [4,1,2,1,2]
//    Output: 4
    public static int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int count = 0; int maxCount = 0;
        for(int i = 0; i<n; i++){
            if(nums[i]==1){
                count++;
            }
            else{
                maxCount = Math.max(count, maxCount);
                count = 0;
            }
        }
        return Math.max(count, maxCount);
    }

    //Brute
    public static int singleNumber(int[] nums) {
        for(int i = 0; i<nums.length; i++){
            boolean flag = false;
            for(int j = 0; j<nums.length; j++){
                if(i!=j && nums[i]==nums[j]){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                return nums[i];
            }
        }
        return -1;
    }

    //Optimal
    public int singleNumber2(int[] nums) {
        int val = 0;
        for(int i = 0; i<nums.length; i++){
            val^=nums[i];
        }
        return val;
    }

//    You are given an array 'a' of size 'n' and an integer 'k'.
//    Find the length of the longest subarray of 'a' whose sum is equal to 'k'.
//    Example :
//    Input: ‘n’ = 7 ‘k’ = 3
//            ‘a’ = [1, 2, 3, 1, 1, 1, 1]
    //Brute
    public static int longestSubarrayWithSumK(int []a, long k) {
        // Write your code here
        int maxCount = 0;
        int n = a.length;
        int j;
        for(int i = 0; i<n ;i++){
            long sum = 0;
            for(j = i; j<n; j++){
                sum+=a[j];
                if(sum==k){
                    maxCount = Math.max(maxCount, j-i+1);
                }
                if(sum>k){
                    break;
                }
            }

        }
        return maxCount;
    }
}
