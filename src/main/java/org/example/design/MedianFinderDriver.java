package org.example.design;

public class MedianFinderDriver {
    public static void main(String[] args){
        MedianFinderHeaps medianFinder = new MedianFinderHeaps();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
//        medianFinder.findMedian();
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(5);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());
    }
}
