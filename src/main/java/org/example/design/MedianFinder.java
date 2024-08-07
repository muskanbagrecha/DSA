package org.example.design;

import java.util.ArrayList;
import java.util.List;

public class MedianFinder {
    List<Integer> list;
    int n;

    public MedianFinder() {
        list = new ArrayList<>();
        n=0;
    }

    public void addNum(int num) {
        if(n==0) list.add(num);
        else if(num>list.get(list.size()-1)){
            list.add(num);
        }
        else if(num<list.get(0)){
            list.add(0, num);
        }
        else{
            for(int i = 1; i<list.size(); i++){
                if(num<list.get(i) && num>=list.get(i-1)){
                    list.add(i-1, num);
                }
            }
        }
        n++;

    }

    public double findMedian() {
        if(n%2!=0) return list.get(n/2);
        return (double)(list.get(n/2) + list.get(n/2-1)) / 2;
    }
}
