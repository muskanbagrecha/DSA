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
}


class sortByEnd implements Comparator<Pair> {
    @Override
    public int compare(Pair a, Pair b){
        return a.second - b.second; //we have to sort by end not start as a meeting starting early can take up all the time there is but it will still be counted towards a single meeting.
    }
}