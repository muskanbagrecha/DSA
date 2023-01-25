package org.example;

public class RotateAnArray {

//    I/P: 1, 2, 3, 4, 5
//    o/p: 2, 3, 4, 5, 1

//    I/P: 30, 5, 20
//    O/P: 5, 20, 30

    public static void leftRotateByOne(int[] arr){
        int n = arr.length;
        int temp = arr[0];
        for(int i = 1; i<n; i++){
            arr[i-1] = arr[i];
        }
        arr[n-1] = temp;
        Helper.printArray(arr);
    }

}
