package org.example;

import org.example.pair.Pair;

import java.util.Comparator;
import java.util.Arrays;

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
}


class sortByEnd implements Comparator<Pair> {
    @Override
    public int compare(Pair a, Pair b){
        return a.second - b.second; //we have to sort by end not start as a meeting starting early can take up all the time there is but it will still be counted towards a single meeting.
    }
}