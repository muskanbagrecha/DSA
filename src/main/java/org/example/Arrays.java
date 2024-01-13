package org.example;

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


}
