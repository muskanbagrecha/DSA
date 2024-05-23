package org.example.stacks;

import java.util.ArrayList;
import java.util.Stack;

class StockSpanner {

    private ArrayList<Integer> stocks;
    private Stack<Integer> s;
    private int totalStocks = 0;
    private int i;
    public StockSpanner() {
        stocks = new ArrayList<>();
        s  = new Stack<>();
        i = 0;
    }

    public int next(int price) {
        stocks.add(price);
        i++;
        while(!s.isEmpty() && stocks.get(s.peek())<=price){
            s.pop();
        }
        int ans = 0;
        ans = s.isEmpty() ? i : i-s.peek()-1;
        s.push(i - 1);
        return ans;
    }
}