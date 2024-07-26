package org.example;

import org.example.pair.Pair;

import java.util.*;

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

    //Find K closest elements
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(new ClosestElementsComparator());
        for (int i = 0; i < arr.length; i++) {
            pq.add(new Pair(Math.abs(arr[i] - x), arr[i]));
            if (pq.size() > k) {
                pq.remove();
            }
        }
        pq.forEach(pair -> res.add(pair.second));
        Collections.sort(res);
        return res;
    }
    //TC: O(n log k) + O(k) + O(klogk) => O(nlogk) + O(klogk)

    //VVIMP
    //https://leetcode.com/problems/find-k-pairs-with-smallest-sums
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0]-b[0]); //min-heap
        for(int i = 0; i<nums1.length; i++){
            pq.add(new int[]{nums1[i]+nums2[0], 0});
        }
        while(k>0 && !pq.isEmpty()){
            int[] curr = pq.remove();
            int u = curr[0]-nums2[curr[1]];
            int v = nums2[curr[1]];
            List<Integer> currentPair = new ArrayList<>();
            currentPair.add(u);
            currentPair.add(v);
            res.add(currentPair);
            int nextIndex = curr[1] + 1;
            if(nextIndex<nums2.length){
                pq.add(new int[]{u+nums2[nextIndex], nextIndex});
            }
            k--;
        }
        return res;
    }
}

class ClosestElementsComparator implements Comparator<Pair> {
    @Override
    public int compare(Pair a, Pair b) {
        if (a.first != b.first) {
            return b.first - a.first; // Max-Heap: Larger differences come first
        }
        return b.second - a.second; // For same differences, larger numbers come first
    }
}