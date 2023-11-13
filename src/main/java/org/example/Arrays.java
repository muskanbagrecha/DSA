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
    public static int getSecondOrderElementsWhenElementsAreRepeating(int [] a, int n) {
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

}
