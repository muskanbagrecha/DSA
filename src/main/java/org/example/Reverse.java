package org.example;

public class Reverse {

//    I/P: {30, 20, 5};
//    O/P: 5, 20, 30

//    O(n) Solution
    public static void reverseArray(int[] arr){
        int n = arr.length;
        for(int i = 0, j = n-1; i<j; i++, j--){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        Helper.printArray(arr);
    }

}
