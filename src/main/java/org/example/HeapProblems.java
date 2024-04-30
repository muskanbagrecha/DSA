package org.example;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class HeapProblems {
    //Sort K nearly sorted array
    ArrayList<Integer> nearlySorted(int arr[], int num, int k)
    {
        // your code here
        ArrayList<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i<arr.length; i++){
            pq.add(arr[i]);
            if(pq.size()>k){
                int element = pq.poll();
                list.add(element);
            }
        }
        while(!pq.isEmpty()){
            list.add(pq.poll());
        }
        return list;
    }
    //Time: O(klogk)
    //Space: O(K)
}
