package org.example.design;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinderHeaps {
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    int n1;
    int n2;

    public MedianFinderHeaps() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        n1=0;
        n2=0;
    }

    public void addNum(int num) {
        if(n1==0 || n1==n2){
            if(n2==0 || num<minHeap.peek()){
                maxHeap.add(num);
            }
            else{
                maxHeap.add(minHeap.remove());
                minHeap.add(num);
            }
            n1++;
        }
        else if(n1>n2){
            maxHeap.add(num);
            minHeap.add(maxHeap.remove());
            n2++;
        }
    }
    public double findMedian() {
        if(n1==n2){
            return (maxHeap.peek()+minHeap.peek())/2.0;
        }
        return maxHeap.peek();
    }
}

//TC: O(Log n) - coz adding a num to PQ or removing a num is O(log n) operation. So in worst case, we add a num and remove it and re-add it to another heap all O(log n) ops.
//TC for findMedian: O(1)