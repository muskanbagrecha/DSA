package org.example;

public class Practice {

    public static void swapAlternate(int[] arr){
        int n = arr.length;
        for(int i = 0; i<n; i+=2){
            if(i!=n-1){
                int j = i+1;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Helper.printArray(arr);
    }

}
