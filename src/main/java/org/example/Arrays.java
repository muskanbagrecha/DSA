package org.example;

import java.util.*;

public class Arrays {

    /*
     * Second largest and second smallest
     * Input: 1,2,3,4,5
     * Output: 4, 2
     */
    public static int[] getSecondOrderElements(int[] a) {
        int n = a.length;
        // Write your code here.
        int[] ans = new int[2];
        ans[0] = secondLargest(n, a);
        ans[1] = secondSmallest(n, a);
        return ans;
    }

    public static int secondLargest(int n, int[] a) {
        int max = Math.max(a[0], a[1]);
        int secondMax = Math.min(a[0], a[1]);
        for (int i = 2; i < n; i++) {
            if (a[i] > max) {
                secondMax = max;
                max = a[i];
            } else if (a[i] > secondMax) {
                secondMax = a[i];
            }
        }
        return secondMax;
    }

    public static int secondSmallest(int n, int[] a) {
        int min = Math.min(a[0], a[1]);
        int secondMin = Math.max(a[0], a[1]);
        for (int i = 2; i < n; i++) {
            if (a[i] < min) {
                secondMin = min;
                min = a[i];
            } else if (a[i] < secondMin) {
                secondMin = a[i];
            }
        }
        return secondMin;
    }

    //Input: 8,9,9,10,10
    //Output: 9

    public static long[] getSecondSmallestElementsWhenElementsAreRepeating(long a[]) {
        long n = a.length;
        long smallest = Math.min(a[0], a[1]);
        long secondSmallest = Math.max(a[0], a[1]);

        for (int i = 2; i < n; i++) {
            if (a[i] < smallest) {
                secondSmallest = smallest;
                smallest = a[i];
            } else if (a[i] < secondSmallest && a[i] != smallest) {
                secondSmallest = a[i];
            }
            if (smallest == secondSmallest) {
                secondSmallest = a[i];
            }
        }

        if (smallest == secondSmallest) {
            return new long[]{-1, -1};
        }

        return new long[]{smallest, secondSmallest};
    }

    //Input: 4, 5, 1, 2, 3
    //Output: true

    // Input: 1, 4, 1, 2, 3
    // Output: false
    public static boolean checkIfRotatedArrayIsSorted(int[] arr) {
        int n = arr.length;
        int k = 0;
        for (int i = 0; i < n; i++) {
            int nextIndex = (i + 1) % n;
            if (arr[i] > arr[nextIndex]) {
                k++;
            }
            if (k > 1) {
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
    public static int removeDuplicatesFromSortedArray(int[] arr) {
        int n = arr.length;
        int j = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] != arr[i - 1]) {
                arr[j] = arr[i];
                j++;
            }
        }
        return j;
    }

    public int removeDuplicates2(int[] nums) {
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[j] < nums[i]) {
                nums[++j] = nums[i];
            }
        }
        return j + 1;
    }

    //    https://leetcode.com/problems/remove-element/
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int j = 0;
        int n = nums.length;
        while (j < n) {
            if (nums[j] == val) {
                j++;
            } else {
                nums[i] = nums[j];
                i++;
                j++;
            }
        }
        return i;
    }

    //    https://leetcode.com/problems/apply-operations-to-an-array/
    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        int i;
        int j = 0;
        for (i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
        }
        i = 0;
        j = 0; //go from 0 to n, swap wherever we encounter a non-zero in j. We can also track the first 0th element in the above loop and start the inspection process from that element. Find example below.
        while (j < n) {
            if (nums[j] == 0) {
                j++;
            } else {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j++;
            }
        }
        return nums;
    }

    public int[] applyOperations2(int[] nums) {
        int n = nums.length;
        int i;
        int j = -1;
        for (i = 0; i < n - 1; i++) {
            if (nums[i] == 0) {
                if (j == -1) {
                    j = i;
                }
            }
            if (nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
                if (j == -1) {
                    j = i + 1; //first 0
                }
            }
        }
        if (j == -1) { //no
            return nums;
        }
        i = j;
        while (j < n) {
            if (nums[j] == 0) {
                j++;
            } else {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j++;
            }
        }
        return nums;
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
        if (k > n) {
            k = k % n;
        }
        int j = 0;
        for (int i = n - k; i < n; i++) {
            nums[j] = rotatedElements[i];
            j++;
        }
        for (int i = 0; i < n - k; i++) {
            nums[j] = rotatedElements[i];
            j++;
        }
        return nums;
    }

    public static void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n);
        reverse(nums, 0, k);
        reverse(nums, k, n);
        Helper.printArray(nums);
    }

    public static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int i = start;
            int j = end - 1;
            int temp = arr[i];
            arr[i] = arr[j];
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
        reverse(arr, 0, n - k);
        reverse(arr, n - k, n);
        return arr;
    }

    public static void reverse(ArrayList<Integer> arr, int start, int end) {
        // int j = end-1;
        while (start < end) {
            int temp = arr.get(start);
            arr.set(start, arr.get(end - 1));
            arr.set(end - 1, temp);
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
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                temp.add(arr[i]);
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            arr[i] = temp.get(i);
        }
        for (int i = temp.size(); i < n; i++) {
            arr[i] = 0;
        }
    }
    //Time: O(2n) = O(n)
    //Space: O(n) //worst case

    //Optimal
    public void moveZeroes2(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
    //similar to remove duplicates in sorted array problem: LC - 26

    //Another approach - same as above but swaps immediately
    public void moveZeroes3(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
    }

    //Snowball approach: https://leetcode.com/problems/move-zeroes/solutions/172432/the-easiest-but-unusual-snowball-java-solution-beats-100-o-n-clear-explanation
    public void moveZeroes(int[] arr) {
        int n = arr.length;
        int snowBall = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                snowBall++;
            } else {
                swap(arr, i, i - snowBall);
            }
        }
    }

    public static void swap(int[] arr, int first, int second) {
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
    public static List<Integer> sortedArray(int[] a, int[] b) {
        // Write your code here
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < a.length; i++) {
            set.add(a[i]);
        }
        for (int i = 0; i < b.length; i++) {
            set.add(b[i]);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : set) {
            list.add(num);
        }
        return list;
    }

    //Optimal
    public static List<Integer> sortedArray2(int[] a, int[] b) {
        // Write your code here
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < a.length; i++) {
            set.add(a[i]);
        }
        for (int i = 0; i < b.length; i++) {
            set.add(b[i]);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : set) {
            list.add(num);
        }
        return list;
    }
    //time: O((m+n)log(m+n)) as inserting one node takes O(logn) and we need to insert m+n nodes.
    //space: O(m+n) worst case

    //If you were to avoid using sets.
    public static List< Integer > sortedArrayWithoutSet(int []a, int []b) {
        // Write your code here
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while(i<a.length && j<b.length){
            if(a[i]<=b[j]){
                if(list.size() == 0 || list.get(list.size()-1)!=a[i]){
                    list.add(a[i]);
                }
                i++;
            }
            else if(a[i]>b[j]){
                if(list.size() == 0 || list.get(list.size()-1)!=b[j]){
                    list.add(b[j]);
                }
                j++;
            }
        }
        while (i < a.length) {
            if(list.size() == 0 || list.get(list.size()-1)!=a[i]){
                list.add(a[i]);
            }
            i++;
        }
        while (j < b.length) {
            if(list.size() == 0 || list.get(list.size()-1)!=b[j]){
                list.add(b[j]);
            }
            j++;
        }
        return list;
    }

    //Time: O(m+n)
    //Space: O(m+n)
//    Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
//    You must implement a solution with a linear runtime complexity and use only constant extra space.

//    Input: nums = [2,2,1]
//    Output: 1

    //    Input: nums = [4,1,2,1,2]
//    Output: 4
    public static int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int count = 0;
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                maxCount = Math.max(count, maxCount);
                count = 0;
            }
        }
        return Math.max(count, maxCount);
    }

    //Brute
    public static int singleNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean flag = false;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] == nums[j]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return nums[i];
            }
        }
        return -1;
    }

    //Optimal
    public int singleNumber2(int[] nums) {
        int val = 0;
        for (int i = 0; i < nums.length; i++) {
            val ^= nums[i];
        }
        return val;
    }

    //    You are given an array 'a' of size 'n' and an integer 'k'.
//    Find the length of the longest subarray of 'a' whose sum is equal to 'k'.
//    Example :
//    Input: ‘n’ = 7 ‘k’ = 3
//            ‘a’ = [1, 2, 3, 1, 1, 1, 1]
    //Brute
    public static int longestSubarrayWithSumK(int[] a, long k) {
        // Write your code here
        int maxCount = 0;
        int n = a.length;
        int j;
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (j = i; j < n; j++) {
                sum += a[j];
                if (sum == k) {
                    maxCount = Math.max(maxCount, j - i + 1);
                }
                if (sum > k) {
                    break;
                }
            }

        }
        return maxCount;
    }

    //Optimal - Sliding Window
    public static long maximumSumSubarray(int k, ArrayList<Integer> arr, int n) {
        int i = 0, j = 0;
        long sum = 0;
        long maxSum = 0;
        while (j < n) {
            sum += arr.get(j);
            if ((j - i + 1) < k) {
                j++;
            } else if ((j - i + 1) == k) {
                maxSum = Math.max(maxSum, sum);
                sum -= arr.get(i);
                i++;
                j++;
            }
        }
        return maxSum;
    }

    //https://www.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1
    //Brute force
    public long[] printFirstNegativeInteger(long arr[], int n, int k) {
        long[] ans = new long[n - k + 1];
        int i = 0, j = 0;
        int ctr = 0;
        while (j < n) {
            if (j - i + 1 < k) {
                j++;
            }
            if (j - i + 1 == k) {
                int t = i;
                while (t <= j) {
                    if (arr[t] < 0) {
                        ans[i] = arr[t];
                        break;
                    }
                    t++;
                }
                if (t > j) {
                    ans[i] = 0;
                }
                i++;
                j++;
            }
        }
        return ans;
    }

    //Optimal
    public static long[] printFirstNegativeInteger2(long arr[], int n, int k) {
        long[] ans = new long[n - k + 1];
        Queue<Long> q = new LinkedList<>();
        int i = 0, j = 0;
        while (j < n) {
            if (arr[j] < 0) {
                q.add(arr[j]);
            }
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                ans[i] = q.isEmpty() ? 0 : q.peek();
                if (arr[i] < 0) {
                    q.poll();
                }
                i++;
                j++;
            }
        }
        return ans;
    }

    //https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article
    //Given an array containing N integers and an integer K., Your task is to find the length of the longest Sub-Array with the sum of the elements equal to the given value K.
    //Optimal Solution 1
    public static int lenOfLongSubarr(int arr[], int n, int k) {
        //Complete the function
        int i = 0, j = 0;
        int sum = 0;
        int maxCount = 0;
        while (j < n) {
            sum += arr[j];
            if (sum == k) {
                maxCount = Math.max(maxCount, j - i + 1);
                j++;
            } else if (sum < k) {
                j++;
            } else if (sum > k) {
                while (sum > k) { //here i can never exceed j as we will have subtracted everything. assumption: all elements are positive
                    sum = sum - arr[i];
                    i++;
                }
                j++;
            }
        }
        return maxCount;
    }

    //Solution 2 - Using hashmap (TODO)

//    Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
//    https://leetcode.com/problems/two-sum/description/

    //Optimal (1)
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                int complementIndex = map.get(complement);
                return new int[]{i, complementIndex};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{0, 0};
    }
    //Time: O(n)
    //Space: O(n)

//    https://leetcode.com/problems/majority-element/description/
//    Given an array nums of size n, return the majority element.
//    The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
//    Input: nums = [3,2,3]
//    Output: 3

//    Input: nums = [2,2,1,1,1,2,2]
//    Output: 2

    //Brute force (1)
    public static int majorityElement1(int[] nums) {
        java.util.Arrays.sort(nums);
        return nums[nums.length / 2]; //key assumption is majority element occurs greater than n/2 times.
    }
    //Time complexity: O(nlogn)
    //Space complexity: O(1) -- extra space complexity
    //Space complexity: O(n) -- if we also consider input array as we are moving elements around

    //Better
    public static int majorityElement2(int[] nums) {
        int n = nums.length;
        int newValue;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            newValue = 1; //if element is not present, frequency will be 1
            if (map.containsKey(nums[i])) {
                newValue = map.get(nums[i]) + 1; //if element is present frequency gets updated here
                if (newValue > n / 2) {
                    return nums[i];
                }
            }
            map.put(nums[i], newValue);
        }
        return nums[0];
    }

    //Time complexity: O(n)
    //Space complexity: O(n)

    //Optimal: Moore's voting algo
    public static int majorityElement3(int[] nums) {
        int element = nums[0], ctr = 1;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] == element) {
                ctr++;
            } else if (ctr == 0) {
                element = nums[i];
                ctr++;
            } else {
                ctr--;
            }
        }
        return element;
    }
    //Time complexity: O(n)
    //Space complexity: O(1)

//    https://leetcode.com/problems/sort-colors/description/
//    Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
//    We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

    //Brute force
    public static void sortColors1(int[] nums) {
        int j;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    //Time complexity: O(n*n) using bubble sort algo
    //Space: O(1)

    //Solution 2: Use merge sort (most efficient sort algo)
    //Time complexity: O(nlogn)
    //Space: O(n)

    //Solution 3: using ctr
    public static void sortColors2(int[] nums) {
        int ctr0 = 0, ctr1 = 0, ctr2 = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                ctr0++;
            } else if (nums[i] == 1) {
                ctr1++;
            } else {
                ctr2++;
            }
        }
        for (int i = 0; i < ctr0; i++) {
            nums[i] = 0;
        }
        for (int i = ctr0; i < ctr0 + ctr1; i++) {
            nums[i] = 1;
        }
        for (int i = ctr0 + ctr1; i < n; i++) {
            nums[i] = 2;
        }
    }

    //Optimal - single pass
    //Dutch national flag algo
    public static void sortColors(int[] arr) {
        int low = 0, mid = 0, high = arr.length - 1;
        while (mid <= high) {
            if (arr[mid] == 0) {
                swap(arr, low, mid);
                low++;
                mid++;
            } else if (arr[mid] == 2) {
                swap(arr, mid, high);
                high--;
            } else if (arr[mid] == 1) {
                mid++;
            }
        }
    }
    //Time complexity: O(n)
    //Space: O(1)
    // Check course sheet notes for intuition.


//    Given an integer array nums, find the subarray with the largest sum, and return its sum.

//    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//    Output: 6
//    Explanation: The subarray [4,-1,2,1] has the largest sum 6.

//    Input: nums = [1]
//    Output: 1
//    Explanation: The subarray [1] has the largest sum 1.

    //Brute force
    public static long maxSubarraySum(int[] arr, int n) {
        // write your code here
        int maxSum = arr[0], sum = 0;
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    //Optimal
    //Kadane's algorithm
    public static long maxSubarraySum2(int[] arr, int n) {
        // write your code here
        int max = arr[0], sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
            max = Math.max(max, sum);
            sum = sum >= 0 ? sum : 0;
        }
        return max;
    }

    //    Given an ascending sorted rotated array Arr of distinct integers of size N. The array is right rotated K times. Find the value of K.
//    https://www.geeksforgeeks.org/problems/rotation4723/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article
    //Brute force
    int findKRotation1(int arr[], int n) {
        // code here
        int min = arr[0];
        int minIndex = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    //    linear search
//    Time: O(n), space: O(1)

    //    Optimal
//    Binary search
    public static int findKRotation3(int arr[], int n) {
        // code here
        int low = 0, high = n - 1;
        if (arr[0] <= arr[n - 1]) {
            return 0;
        }
        while (low <= high) {
            int mid = low + ((high - low) / 2);
            int left = (mid - 1 + n) % n;
            if (arr[mid] < arr[left]) {
                return mid;
            } else if (arr[mid] < arr[0]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return 0;
    }

    //https://leetcode.com/problems/island-perimeter
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int num = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0 || grid[i - 1][j] == 0) num++; // UP
                    if (j == 0 || grid[i][j - 1] == 0) num++; // LEFT
                    if (i == rows - 1 || grid[i + 1][j] == 0) num++; // DOWN
                    if (j == cols - 1 || grid[i][j + 1] == 0) num++; // RIGHT
                }
            }
        }
        return num;
    }

    //https://leetcode.com/problems/missing-number/
    public int missingNumber(int[] nums) {
        java.util.Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }
    //Time: O(logn) to sort + O(n)
    //Space: O(1)

    //Approach 2:
    public int missingNumber2(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        int actualSum = (n * (n + 1)) / 2;
        return actualSum - sum;
    }
    //Time: O(n)
    //Space: O(1)

}
