package org.example;

public class Sort {

    public static void bubbleSort(int[] arr, int n) {
        //Your code goes here
        for(int i = 0; i<n;i++){
            boolean swapped = false;
            for(int j=0;j<n-i-1;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr, j, j+1);
                    swapped=true;
                }
            }
            if(!swapped){
                return;
            }
        }
    }

    public static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
