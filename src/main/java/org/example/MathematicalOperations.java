package org.example;

import java.util.HashMap;

public class MathematicalOperations {

    /*Check if n is prime*/
    public static boolean isPrime(int num){
        for(int i = 2; i<=Math.sqrt(num); i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }

    /* Return number of digits in number 'n' */
    // Input: 123
    // Output: 3

    public static int findNumberOfDigits(int n){
        int ctr = 0;
        while(n>0){
            ctr+=1;
            n/=10;
        }
        return ctr;
    }

    public static boolean palindromeNumber(int n){
        String s = n + "";
        for(int i = 0; i<s.length();i++){
            int j = s.length()-i-1;
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
        }
        return true;
    }

    /*
    Find all prime numbers within a range
    Brute force:
    Loop from 2 to n and check each number against isPrime method
    Time Complexity: O(n * sqrt(n))

    Approach 2: Sieve of Eratosthenes
    The Sieve of Eratosthenes is an algorithm for finding all prime numbers up to a specified limit.

    The algorithm works as follows:

    Create a list of consecutive integers from 2 through n: (2, 3, 4, ..., n).
    Initially, let p equal 2, the smallest prime number.
    Enumerate the multiples of p by counting in increments of p from p * 2 to n, and mark them in the list (these will be 2p, 3p, 4p, ...; the p itself should not be marked).
    Find the smallest number in the list greater than p that is not marked. If there was no such number, stop. Otherwise, let p now equal this new number (which is the next prime), and repeat from step 3.
    After this algorithm completes, all the numbers that are not marked are prime. This is because, at each step, we are eliminating all the multiples of a prime number that we have already identified, leaving behind only the primes.

    The time complexity of the algorithm is O(n log log n), which is much faster than testing each number for primality individually.
    */
    public static void findPrimesWithinARange(int num){
        //assume false in array means number is prime
        boolean[] candidates = new boolean[num + 1];
        sieve(candidates, num);
        for (int i = 2; i <= num; i++) {
            if(!candidates[i]){
                System.out.println(i);
            }
        }
    }

    public static boolean[] sieve(boolean[] candidates, int num){
        for(int i = 2; i<=Math.sqrt(num); i++){
            if(!candidates[i]){ //ie number is prime, then make its multiple true (not prime)
                for(int j = 2 * i; j<=num; j+=i){
                    candidates[j]=true;
                }
            }
        }
        return candidates;
    }

    //https://leetcode.com/problems/roman-to-integer/
    public int romanToInt(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            switch (arr[i]) {
                case 'I': {
                    if (i < n - 1 && arr[i + 1] == 'V') {
                        sum += 4;
                        i++;
                    } else if (i < n - 1 && arr[i + 1] == 'X') {
                        sum += 9;
                        i++;
                    } else {
                        sum += 1;
                    }
                    break;
                }
                case 'V': sum+=5; break;
                case 'X': {
                    if(i < n - 1 && arr[i+1]=='L'){
                        sum+=40;
                        i++;
                    }
                    else if(i<n-1 && arr[i+1]=='C'){
                        sum+=90;
                        i++;
                    }
                    else{
                        sum+=10;
                    }
                    break;
                }
                case 'L': sum+=50; break;
                case 'C': {
                    if(i < n - 1 && arr[i+1]=='D'){
                        sum+=400;
                        i++;
                    }
                    else if(i<n-1 && arr[i+1]=='M'){
                        sum+=900;
                        i++;
                    }
                    else{
                        sum+=100;
                    }
                    break;
                }
                case 'D': sum+=500; break;
                case 'M': sum+=1000; break;
            }
        }
        return sum;
    }
    //Soln is fast but very verbose. Can be optimized by using a hashmap to store the values of each roman numeral and then checking if the next numeral is greater than the current numeral. If yes, then subtract the current numeral from the sum, else add it.
    public int romanToInt2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] arr = s.toCharArray();
        int sum = 0;
        for(int i = 0; i<arr.length; i++){
            int current = map.get(arr[i]);
            if(i<arr.length-1 && current<map.get(arr[i+1])){
                sum-=current;
            }
            else{
                sum+=current;
            }
        }
        return sum;
    }
}
