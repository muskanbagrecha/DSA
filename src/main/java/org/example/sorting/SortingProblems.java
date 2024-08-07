package org.example.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SortingProblems {

//    https://leetcode.com/problems/sort-the-people/
    public String[] sortPeople(String[] names, int[] heights) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<heights.length; i++){
            map.put(heights[i], i);
        }
        mergeSortDesc(heights, 0, heights.length-1);
        String[] sorted = new String[names.length];
        for(int i = 0; i<names.length; i++){
            sorted[i] = names[map.get(heights[i])];
        }
        return sorted;
    }

    public void mergeSortDesc(int[] arr, int low, int high){
        if(low==high){
            return;
        }
        int mid = low + (high-low)/2;
        mergeSortDesc(arr, low, mid);
        mergeSortDesc(arr, mid+1, high);
        merge(arr, low, high, mid);
    }

    public void merge(int[] arr, int low, int high, int mid){
        int leftPointer = low;
        int rightPointer = mid+1;
        List<Integer> list = new ArrayList<>();
        while(leftPointer<=mid && rightPointer<=high){
            if(arr[leftPointer]>=arr[rightPointer]){
                list.add(arr[leftPointer++]);
            }
            else{
                list.add(arr[rightPointer++]);
            }
        }
        while(leftPointer<=mid){
            list.add(arr[leftPointer++]);
        }
        while(rightPointer<=high){
            list.add(arr[rightPointer++]);
        }
        for(int i = low; i<=high; i++){
            arr[i] = list.get(i-low);
        }
    }

    //Approach 2:
    //Sorting  using Arrays.sort() will sort in asc order, but you cannot directly pass comparator to sort in desc order for primitive types. So instead sort in asc order and reverse the array.
    public String[] sortPeople2(String[] names, int[] heights) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<heights.length; i++){
            map.put(heights[i], i);
        }
        Arrays.sort(heights);
        int l = 0; int r = heights.length-1;
        while(l<r){
            int temp = heights[l];
            heights[l] = heights[r];
            heights[r] = temp;
            l++;
            r--;
        }
        String[] sorted = new String[names.length];
        for(int i = 0; i<names.length; i++){
            sorted[i] = names[map.get(heights[i])];
        }
        return sorted;
    }
}
