package org.example;

import org.example.pair.Pair;
import org.example.tri.Tri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class GreedyProblems {
    public static int maxMeetings(int start[], int end[], int n)
    {
        Pair[] pairs = new Pair[n];
        // add your code here
        for(int i = 0; i<start.length; i++){
            pairs[i] = new Pair(start[i], end[i]);
        }
        Arrays.sort(pairs, new sortByEnd());
        int meetings = 0;
        int time = 0;
        for(int i = 0; i<n; i++){
            if(time==0 || pairs[i].first>time){
                meetings++;
                time = pairs[i].second;
            }
        }
        return meetings;
    }

    //TC: O(nlogn)
    //Space: O(n)

    //Problem to print maximum meetings in one room
    public static ArrayList<Integer> maxMeetings(int n, int[] start, int[] end) {
        // code here
        Tri[] pairs = new Tri[n];
        // add your code here
        for(int i = 0; i<start.length; i++){
            pairs[i] = new Tri(start[i], end[i], i+1);
        }
        ArrayList<Integer> res = new ArrayList<>();
//        Arrays.sort(pairs, new sortByEnd()); //uncomment this
        int time = -1;
        for(int i = 0; i<n; i++){
            if(pairs[i].first>time){
//                res.add(pairs[i].index); //uncomment this
                time = pairs[i].second;
            }
        }
        Collections.sort(res);
        return res;
    }

    //https://www.geeksforgeeks.org/problems/shop-in-candy-store1145/1
    static ArrayList<Integer> candyStore(int candies[],int N,int K){
        // code here
        ArrayList<Integer> list = new ArrayList<>(2);
        Arrays.sort(candies);
        int min = 0;
        for(int i = 0; i<N; i++){
            min+=candies[i];
            N-=K;
        }
        int max = 0;
        N = candies.length;
        int i = 0;
        while(i<N){
            max+=candies[N-1];
            i+=K;
            N--;
        }
        list.add(min);
        list.add(max);
        return list;
    }
    //O(nlogn) - TC
    static ArrayList<Integer> candyStore2(int candies[],int N,int K){
        // code here
        ArrayList<Integer> list = new ArrayList<>(2);
        Arrays.sort(candies);
        int min = 0;
        int buy = 0;
        int free = N-1;
        while(buy<=free){
            min += candies[buy];
            buy++;
            free-=K;
        }
        int max = 0;
        buy = N-1;
        free = 0;
        while(free<=buy){
            max+=candies[buy];
            buy--;
            free+=K;
        }
        list.add(min);
        list.add(max);
        return list;
    }
    //https://www.geeksforgeeks.org/problems/chocolate-distribution-problem3825/1
    public long findMinDiff (ArrayList<Integer> a, int n, int m)
    {
        Collections.sort(a);
        int l = 0;
        int r = m-1;
        long min = Long.MAX_VALUE;
        while(r<n && r-l+1==m){
            min = Math.min(min, a.get(r)-a.get(l));
            l++;
            r++;
        }
        return min;
    }

    //https://leetcode.com/problems/assign-cookies/
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int childCounter = 0;
        int sizeCounter = 0;
        while(childCounter<g.length && sizeCounter<s.length){
            if(g[childCounter]<=s[sizeCounter]){
                childCounter++;
            }
            sizeCounter++;
        }
        return childCounter;
    }
}


class sortByEnd implements Comparator<Pair> {
    @Override
    public int compare(Pair a, Pair b){
        return a.second - b.second; //we have to sort by end not start as a meeting starting early can take up all the time there is but it will still be counted towards a single meeting.
    }
}