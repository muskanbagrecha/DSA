package org.example;

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
}
