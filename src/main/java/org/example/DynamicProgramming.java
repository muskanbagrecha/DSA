package org.example;

public class DynamicProgramming {
    //https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1

    //Recursive without DP
    static int knapSack(int W, int wt[], int val[], int n)
    {
        // your code here
        if(n==0 || W==0){
            return 0;
        }

        if(wt[n-1]<=W){
            return Math.max(val[n-1] + knapSack(W-wt[n-1], wt, val, n-1), knapSack(W, wt, val, n-1));
        }
        else{
            return knapSack(W, wt, val, n-1);
        }
    }
}
