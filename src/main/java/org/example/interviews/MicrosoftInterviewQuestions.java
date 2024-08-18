package org.example.interviews;

import java.util.Arrays;

public class MicrosoftInterviewQuestions {

    //Coin change 2
    //Memoization using 2d array
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        for(int[] row: dp){
            Arrays.fill(row, -1);
        }
        return findCombinations(amount, coins, coins.length, dp);
    }

    public int findCombinations(int amount, int[] coins, int n, int[][] dp){
        if(amount==0){
            return 1; //1 combination led to amount being 0.
        }
        if(n==0){ // we are out of elements which means combination could not lead up to amount.
            return 0;
        }
        if(dp[n][amount]!=-1){
            return dp[n][amount];
        }
        int include = 0;
        if(coins[n-1]<=amount){
            include = findCombinations(amount-coins[n-1], coins, n, dp);
        }
        int dontinclude = findCombinations(amount, coins, n-1, dp);
        return dp[n][amount] = include + dontinclude;
    }

    //Tabulation using 2d array
    public int changetab(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[coins.length+1][amount+1];
        for(int j = 0; j<=amount; j++){
            dp[0][j] = 0; //if there are no coins, it is not possible to make an amt n;
        }
        for(int i = 0; i<=n; i++){
            dp[i][0] = 1;
            //if there are n coins, we can make up amount 0. using 0 supply of each coin.
        }
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=amount; j++){
                if(coins[i-1]<=j){
                    dp[i][j] = dp[i][j-coins[i-1]] + dp[i-1][j];
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][amount];
    }

    public int changeSpaceOptimized(int amount, int[] coins) {
        int n = coins.length;
        int[] prev = new int[amount+1];
        int[] curr = new int[amount+1];
        prev[0] = 1;
        for(int i = 1; i<=n; i++){
            curr[0] = 1;
            for(int j = 1; j<=amount; j++){
                if(coins[i-1]<=j){
                    curr[j] = curr[j-coins[i-1]] + prev[j];
                }
                else{
                    curr[j] = prev[j];
                }
            }
            prev = curr;
        }
        return prev[amount];
    }

    //Given a string, find the first non-repeating character.
    //https://www.geeksforgeeks.org/problems/non-repeating-character-1587115620/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
    static char nonrepeatingCharacter(String S)
    {
        //Your code here
        int[] map = new int[26];
        for(char c : S.toCharArray()){
            map[c-'a']=map[c-'a']+1;
        }
        for(char c : S.toCharArray()){
            if(map[c-'a']==1){
                return c;
            }
        }
        return '$';
    }
}
