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
            }
            else if (mid != n - 1 && arr[mid] == arr[mid + 1]) {
                if (mid % 2 == 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            else {
                return arr[mid];
            }
        }
        return arr[low];
    }

    public static int peakElement(int[] arr){
       return 0;
    }
}
