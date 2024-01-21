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
        if(arr[0]<=arr[n-1]){
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

    public static int findAnElementInSortedRotatedArray(int[] arr) {
        //find the min index
        //check if element is between min index value and end of array value -> if yes do BS on that portion
        //If element is in first half -> do BS on first portion
        // if -1 then element is absent.
        return -1;
    }

    //Answer
    public static int search(int[] nums, int target) {
        int pivot = findMinIndex(nums);
        int n = nums.length;
        if (n == 1) {
            if (nums[0] != target) {
                return -1;
            } else {
                return 0;
            }
        }
        if (target >= nums[0]) {
            return bs(nums, 0, pivot - 1, target);
        } else {
            return bs(nums, pivot, n - 1, target);
        }
    }

    public static int findMinIndex(int[] arr) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        if (arr[0] <= arr[end]) {
            return 0;
        }
        while (start <= end) {
            int mid = start + (int) ((end - start) / 2);
            int prev = (mid + n - 1) % n;
            int next = (mid + 1) % n;
            if (arr[mid] < arr[prev] && arr[mid] < arr[next]) {
                return mid;
            } else if (arr[mid] >= arr[0]) { //first half is sorted
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static int bs(int[] arr, int start, int end, int element) {
        while (start <= end && start >= 0 && end <= arr.length - 1) {
            int mid = start + (int) ((end - start) / 2);
            if (arr[mid] == element) {
                return mid;
            } else if (element < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
    //Answer end

    //Q Find element in a nearly sorted array
    //element can be present at i or i-1 or i+1 position
    //Input: 5,10,30,20,40

    public static int nearlySortedSearch(int[] arr, int target) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int mid = start + (int) ((end - start) / 2);
            int prev = (mid - 1 + n) % n;
            int next = (mid + 1) % n;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[prev] == target) {
                return prev;
            }
            if (arr[next] == target) {
                return next;
            }
            if (target < arr[mid]) {
                end = mid - 2;
            } else {
                start = mid + 2;
            }
        }
        return -1;
    }

    //Q: Find floor of an element in a sorted array
    //Input: 1 2 3 4 8 10 10 12 19, element = 5
    //output: 4

    public static int floorInSortedAarray(int[] arr, int ele) {
        int n = arr.length;
        int start = 0;
        int end = n - 1;
        if (ele > arr[end]) {
            return end;
        }
        while (start <= end) {
            int mid = start + (int) ((end - start) / 2);
            int prev = (mid + n - 1) % n;
            int next = (mid + 1) % n;
            if (arr[mid] == ele) {
                return mid;
            }
            if (arr[mid] < ele && arr[next] > ele) {
                return mid;
            }
            if (arr[mid] < ele) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
