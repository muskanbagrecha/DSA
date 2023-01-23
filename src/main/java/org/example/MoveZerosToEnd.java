package org.example;

public class MoveZerosToEnd {
//    Problem statement: Move all zeroes of an array to the end and maintain the order of the non-zero elements.
//    Input: {0,1,2,0,1,0,5,6,9,0}
//    Output: {1,2,1,5,6,9,0,0,0,0}

    //method one: O(n2) approach

    public static void moveZerosToEndMethodOne(int[] arr){
        int n = arr.length;
        for(int i = 0; i < n; i++){
            if(arr[i] == 0){
                for(int j = i+1; j < n; j++){
                    if(arr[j] != 0){
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        break;
                    }
                }
            }
        }
        Helper.printArray(arr);
    }

    //method two: O(n) single traversal approach
    public static void moveZerosToEndMethodTwo(int[] arr){
        int n = arr.length;
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != 0){
                int temp = arr[i];
                arr[i] = arr[count];
                arr[count] = temp;
                count++;
            }
        }
        Helper.printArray(arr);
    }

}
