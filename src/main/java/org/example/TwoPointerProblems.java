package org.example;

import java.util.ArrayList;
import java.util.List;

public class TwoPointerProblems {

    //https://leetcode.com/problems/find-k-closest-elements
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int start = 0;
        int end = arr.length-1;
        while(end-start+1!=k){
            if(Math.abs(arr[start]-x)<=Math.abs(arr[end]-x)){
                end--;
            }
            else{
                start++;
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int i = start; i<=end; i++){
            res.add(arr[i]);
        }
        return res;
    }
    //TC: O(n)
}
