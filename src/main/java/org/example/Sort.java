package org.example;

public class Sort {

    //Major sorting algorithms (Iterative & Recursive)

    //Bubble sort
    //Iterative
    public static void bubbleSort(int[] arr, int n) {
        //Your code goes here
        for (int i = 0; i < n; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                return;
            }
        }
    }

    //Time: O(N**2) -
    // Outer loop -> n iterations
    // Inner loop -> n-1 => n-2 => n-3 => 0 iterations
    // Sum = n*(n-1)/2 => O(n**2)
    // So worst complexity is O(n**2)
    // Best time complexity : O(n) // due to early return
    //Space: O(1)

    //Recursive:

    public static void bubbleSort2(int[] arr, int n) {
        if (n == 1) {
            return;
        }

        boolean swapped = false;

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
                swapped = true;
            }
        }

        if (!swapped) {
            return;
        }

        bubbleSort(arr, n - 1); //n will already be in place.

    }

    //Time: O(n**2)
    //Space: O(n) - for recursion stack
    //The early return strategy in this recursive bubble sort implementation works because the hypothesis (recursive step) is effectively applied at the end of each pass through the array. If during any pass no swaps are made (indicating the array or the remaining unsorted portion is already sorted), the algorithm can safely terminate early. This optimization takes advantage of the fact that once an entire pass is completed without any swaps, the array is fully sorted, and there's no need to continue with further recursive calls.

    //Iterative
    public static void selectionSort(int[] arr) {

        //Your code goes here
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int maxIndex = -1;
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < n - i; j++) {
                if (arr[j] > max) {
                    maxIndex = j;
                    max = arr[j];
                }
            }
            int lastUnsortedIndex = n-i-1;
            swap(arr, lastUnsortedIndex, maxIndex);
        }
    }

    //Recursive
    public static void selectionSort2(int[] arr, int n) {
        if (n == 1) {
            return;
        }

        int maxIndex = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (arr[i] > max) {
                maxIndex = i;
                max = arr[i];
            }
        }
        swap(arr, maxIndex, n - 1);
        selectionSort2(arr, n - 1);
    }

    //Time: O(n**2) - one loop for finding the max, one for making recursive calls for n times
    //Space: O(n)

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
