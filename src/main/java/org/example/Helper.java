package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Helper {
    public static void printArray(int[] arr){
        System.out.println("--------------");
        System.out.println(Arrays.toString(arr));
        System.out.println("--------------");
    }

    public static int[] inputIntArray(int n){
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        return arr;
    }
}
