package org.example;

public class BinarySearch {

    //input: 2, 4, 10, 10, 10, 18, 20
    //element: 10
    //output: 2
    //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
    public static int firstOccurenceOfElement(int[] arr, int element) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int mid = start + (int) ((end - start) / 2);
            if (arr[mid] == element) {
                if (mid == 0 || arr[mid - 1] != element) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            } else if (arr[mid] < element) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    //input: 2, 4, 10, 10, 10, 18, 20
    //element: 10
    //output: 4
    public static int lastOccurenceOfElement(int[] arr, int element) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int mid = start + (int) ((end - start) / 2);
            if (arr[mid] == element) {
                if (mid == n - 1 || arr[mid + 1] != element) {
                    return mid;
                } else {
                    start = mid + 1;
                }
            } else if (arr[mid] < element) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    //Slight optimization, stop search if next element is not target or we have reached end of array (same thing can be done for first occurrence)
    public int lastOccurence(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int n = nums.length;
        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] == target) {
                if (mid == n - 1 || nums[mid + 1] != target) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    //For first occurrence

//    else if (nums[mid] == target) {
//        if (mid == 0 || nums[mid - 1] != target) {
//            return mid;
//        } else {
//            high = mid - 1;
//        }
//    }

    //    Given an ascending sorted rotated array Arr of distinct integers of size N. The array is right rotated K times. Find the value of K.
//    https://www.geeksforgeeks.org/problems/rotation4723/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=bottom_sticky_on_article
    public static int findKRightRotation(int arr[], int n) {
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
            } else if (arr[mid] > arr[n - 1]) {
                low = mid + 1;
            } else return 0;
        }
        return 0;
    }

    // https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
    // Variation of above problem.
    // important - revise
    public int findMin(int[] arr) {
        int n = arr.length;
        int low = 0, high = n - 1;
        if (arr[0] <= arr[n - 1]) {
            return arr[0];
        }
        while (low <= high) {
            int mid = low + ((high - low) / 2);
            int left = (mid - 1 + n) % n;
            if (arr[mid] < arr[left]) {
                return arr[mid];
            } else if (arr[mid] < arr[0]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return arr[0];
    }

    //Input: 11, 12, 13, 10
    //Output: 1
    public static int numberOfTimesSortedArrayIsRotated(int[] arr) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        if (arr[0] <= arr[end]) {
            return 0;
        }
        while (start <= end) {
            int mid = start + (int) (end - start / 2);
            int prev = (mid + n - 1) % n;
            int next = (mid + 1) % n;
            if (arr[mid] < arr[prev] && arr[mid] < arr[next]) {
                return n - mid;
            } else if (arr[mid] >= arr[start]) { //first half is sorted
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    //Search in Rotated Sorted Array
    //https://leetcode.com/problems/search-in-rotated-sorted-array/description/

    public static int search(int[] arr, int target) {
        int ans;
        int n = arr.length;
        if (arr[0] <= arr[n - 1]) {
            ans = find(arr, 0, n - 1, target);
            return ans;
        }
        int low = 0, high = n - 1;
        int pivot = 0;
        while (low <= high) {
            int mid = low + ((high - low) / 2);
            int left = (mid - 1 + n) % n;
            if (arr[mid] < arr[left]) {
                pivot = mid;
                break;
            } else if (arr[mid] < arr[0]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (target >= arr[0]) {
            ans = find(arr, 0, pivot - 1, target);
        } else {
            ans = find(arr, pivot, n - 1, target);
        }
        return ans;
    }
    //find the min index
    //check if element is between min index value and end of array value -> if yes do BS on that portion
    //If element is in first half -> do BS on first portion
    // if -1 then element is absent.

    //Time complexity: The overall time complexity of the search method is dominated by the two binary search operations (finding the pivot and searching for the target), each of which is O(log n). Since these operations are not nested but sequential, the overall time complexity remains O(log n).
    //Space complexity: O(1)

    //Helper function for above problem
    public static int find(int[] arr, int start, int end, int target) {
        while (start <= end) {
            int mid = start + ((end - start) / 2);
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    //Q Find element in a nearly sorted array
    //element can be present at i or i-1 or i+1 position
    //Input: 5,10,30,20,40

    //Solution 1
    public int search1(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] >= arr[0]) {//sorted
                if (target >= arr[0] && target < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target <= arr[n - 1] && target > arr[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    //    Solution 2
    public int search2(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] >= arr[0]) {//sorted
                if (target >= arr[0] && target < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target <= arr[n - 1] && target > arr[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    //    https://www.geeksforgeeks.org/search-almost-sorted-array/
    public static int nearlySortedSearch(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (arr[mid] == target) {
                return mid;
            } else if (mid > 0 && arr[mid - 1] == target) {
                return mid - 1;
            } else if (mid < n - 1 && arr[mid + 1] == target) {
                return mid + 1;
            } else if (target < arr[mid]) {
                high = mid - 2;
            } else {
                low = mid + 2;
            }
        }
        return -1;
    }

    //Q: Find floor of an element in a sorted array
    //Input: 1 2 3 4 8 10 10 12 19, element = 5
    //output: 4

    public static int findFloor(long arr[], int n, long target) {
        int low = 0;
        int high = n - 1;
        if (arr[low] > target) { //this condition will take care of mid-1 never being negative so we dont need to check mid>0
            return -1;
        }
        if (arr[high] < target) { //this condition is not really required but it is just for an early return
            return high;
        }
        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target && arr[mid - 1] < target) {
                return mid - 1;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    //mid is target - done
    //mid is greater than target but previous element is smaller than target return prev - done
    //first element is greater than target so return -1 - done
    //last element is smaller than target - return it' - done

//    Time: O(logn)
//    Space: O(1)

    //Solution 2
    public static int findFloor2(long arr[], int n, long target) {
        int low = 0;
        int high = n - 1;
        int res = -1;
        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                res = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }

    //    https://www.codingninjas.com/studio/problems/ceiling-in-a-sorted-array_1825401?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
    public static int[] getFloorAndCeil(int[] arr, int n, int x) {
        int floor = -1, ceil = -1;
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == x) {
                return new int[]{x, x};
            } else if (arr[mid] < x) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                ceil = arr[mid];
                high = mid - 1;
            }
        }
        return new int[]{floor, ceil};
    }

    //    https://leetcode.com/problems/find-smallest-letter-greater-than-target/
    public static char nextLetter(char[] arr, int n, int targetLetter) {
        int low = 0, high = n - 1;
        char nextLetter = arr[0];
        while (low <= high) {
            int mid = low + ((high - low) / 2);
            if (arr[mid] > targetLetter) {
                nextLetter = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return nextLetter;
    }

    //https://leetcode.com/problems/search-insert-position/
    public int searchInsert(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int ceil = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                ceil = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ceil;
    }

    //    https://www.geeksforgeeks.org/find-position-element-sorted-array-infinite-numbers/
    public static int binarySearchInInfiniteSortedArray(int[] arr, int target) {
        int low = 0, high = 1;
        while (target > arr[high]) {
            if (high * 2 >= arr.length) {
                high = arr.length - 1;
                break;
            }
            low = high;
            high = high * 2;
        }
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int searchOneinInfiniteSortedArray(int[] arr) {
        int low = 0, high = 1;
        while (arr[high] == 0) {
            if (high * 2 >= arr.length) { //this is only for practical purpose. Theoretically we should never get out of bound exception
                low = high;
                high = arr.length - 1;
                break;
            }
            low = high;
            high = high * 2;
        }
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == 1 && (mid == 0 || arr[mid - 1] == 0)) {
                return mid;
            } else if (arr[mid] == 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    //Find the upper bound
//    https://www.codingninjas.com/studio/problems/implement-upper-bound_8165383?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM/
    public static int upperBound(int[] arr, int target, int n) {
        // Write your code here.
        int low = 0, high = n - 1;
        int upper = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                upper = mid;
                high = mid - 1;
            } else if (arr[mid] <= target) {
                low = mid + 1;
            }
        }
        return upper;
    }

    //Find the lower bound
//    https://www.codingninjas.com/studio/problems/lower-bound_8165382?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
    public static int lowerBound(int[] arr, int n, int target) {
        // Write your code here
        int low = 0;
        int high = n - 1;
        int lower = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) {
                high = mid - 1;
            } else if (arr[mid] < target) {
                lower = mid;
                low = mid + 1;
            }
        }
        return lower;
    }

    //    https://leetcode.com/problems/single-element-in-a-sorted-array/description/
    public static int singleNonDuplicate(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return arr[0];
        }
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid != 0 && arr[mid] == arr[mid - 1]) {
                if (mid % 2 != 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else if (mid != n - 1 && arr[mid] == arr[mid + 1]) {
                if (mid % 2 == 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                return arr[mid];
            }
        }
        return arr[low];
    }

//    Binary search on answers
//The algorithm for finding a peak element is often referred to as "binary search on answer" because it uses the binary search technique not on the element values themselves, but on the range of possible solutions—the "answer." It narrows down this range by effectively deciding at each step if a peak can exist to the left or right of the current position, halving the search space with each iteration, much like binary search does when looking for a specific value. This approach is based on the decision, rather than direct value comparison, hence the name.
    public static int findPeakElement(int[] arr) {
        int n = arr.length;
        int low = 0, high = n - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (mid == 0) {
                return arr[0] > arr[1] ? 0 : 1;
            }
            if (mid == n - 1) {
                return arr[n-1] > arr[n-2] ? n-1: n-2;
            }
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid + 1] > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    //This will return as soon as a peak element is found.
    //If mid is 0, and arr[0] < arr[1], we return 0, that makes sense. But in the else, we return 1 because if mid = 0, low is 0, high is 1, so at that point one of the two elements has to be a peak element. Otherwise low and high wouldnt have converged to that point.
    //low<high takes care of n = 1 condition as while is never executed.

    public static int findPeakElement2(int[] arr) {
        int n = arr.length;
        int low = 0, high = n - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (mid!=n-1 && arr[mid] < arr[mid + 1]) {
                low = mid+1;
            } else {
                high=mid;
            }
        }
        return low;
    }
    //This code is less verbose but will run till low<high, no early returns.
    //Since low<high we dont need to handle edge case of one element only.
    //The loop continues until low and high converge to a single index, which is guaranteed to be a peak by the nature of binary search, and it may or may not be the first peak the original algorithm would find.

    //Variation of above problem: find max element in bitonic array or find bitonic point: https://www.geeksforgeeks.org/problems/maximum-value-in-a-bitonic-array3001/1

    public static int sqrtN(long n) {
        if(n==1){ // we need to check this as we are doing (int) n/2. So if n = 1, it will round it down to 0 and ans will be wrong. Else we can take high = n also.
            return 1;
        }
        int low = 0; int high = (int) n/2; int result = 1;
        while(low<=high){
            int mid = low + (high-low)/2;
            int currentSquare = (int) Math.pow(mid, 2);
            if(currentSquare == n){
                return mid;
            }
            else if(currentSquare>n){
                high = mid-1;
            }
            else{
                result = mid;
                low = mid+1;
            }
        }
        return result;
        //instead of storing in result, we can also return high as it will point to the last possible value (floor) and low will point to the first value which does not qualify for the answer. Example below.
    }
    public static int sqrtN2(long n) {
        if(n==1){
            return 1;
        }
        int low = 0; int high = (int) n/2;
        while(low<=high){
            int mid = low + (high-low)/2;
            int currentSquare = mid * mid;
            if(currentSquare == n){
                return mid;
            }
            else if(currentSquare>n){
                high = mid-1;
            }
            else{
                low = mid+1;
            }
        }
        return high;
    }

    public static int NthRoot(int n, int m) {
        // Write your code here.
        int low = 0; int high = m;
        while(low<=high){
            int mid = low + (high-low)/2;
            int current = (int)Math.pow(mid, n);
            if(current == m){
                return mid;
            }
            else if(current > m){
                high = mid-1;
            }
            else{
                low = mid+1;
            }
        }
        return -1;
    }

    public static int findMaximum(int[] arr) {
        int n = arr.length;
        // code here
        int low = 0, high = n-1;
        while(low<high){
            int mid = low + (high-low)/2;
            if(mid!=n-1 && arr[mid]>arr[mid+1]){
                high = mid;
            }
            else{
                low = mid + 1;
            }
        }
        System.out.println(low + " " + high);
        return arr[high];

    }

    //VVV IMP PROBLEMS FROM HERE.

    //https://www.geeksforgeeks.org/allocate-minimum-number-pages/
    public static int findPages(int[] arr, int n, int noOfStudents)
    {
        //Your code here
        if(n<noOfStudents){
            return -1;
        }
        int low = arr[0], high = arr[0]; //low will be the max, high will be the sum as the range will be from maxbooks to total books.
        for(int i = 1; i<n; i++){
            low = Math.max(arr[i], low);
            high+=arr[i];
        }
        int result = high;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(isValid(arr, n, noOfStudents, mid)){
                result=Math.min(result, mid);
                high = mid-1;
            }
            else{
                low = mid+1;
            }
        }
        return result;
    }

//    Time complexity: O(nlogn)

    public static boolean isValid(int[] arr, int n, int noOfStudents, int max){
        int sum = 0, count = 1;
        for(int i = 0; i<n; i++){
            sum += arr[i];
            if(sum>max){
                count++;
                sum = arr[i];
            }
            if(count>noOfStudents){
                return false;
            }
        }
        return true;
    }

//    Koko eating bananas
    public static int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int low = 0, high = piles[0];
        for(int i = 1; i<n; i++){
            high = Math.max(high, piles[i]);
        }
        int result = high;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(isEatingValid(piles, n, h, mid)){
                result = Math.min(result, mid);
                high = mid-1;
            }
            else{
                low = mid + 1;
            }
        }
        return result;
    }
    public static boolean isEatingValid(int[] piles, int n, int h, int limit){
        int count = 0;
        for(int i = 0; i<n; i++){
            if(piles[i]<limit){
                count++;
            }
            else{
//                count = count + (int)Math.ceil(piles[i]/(double)limit); //either this or below, dont use float here as it cannot accurately represent large integers
                count += (piles[i] + limit - 1) / limit;
            }
            if(count>h){
                return false;
            }
        }
        return true;
    }
}
