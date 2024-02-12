package org.example;

public class Recursion {

    public static void printTillN(int N){
        //1 to N
        if(N==0){
            return;
        }
        printTillN(N-1);
        System.out.print(N + " ");
    }

    public static void printFromN(int N){
        //N to 1
        if(N==0){
            return;
        }
        System.out.print(N + " ");
        printTillN(N-1);
    }

    /*
    * input: Muskan, 3
    * Output: Muskan Muskan Muskan
    * */
    public static void printNtimes(String s, int n){
        if(n==0){
            return;
        }
        System.out.print(s + " ");
        printNtimes(s,n-1);
    }

    /*Square of a number
    *Input: 5
    * Output: 25
    * (n-1)**2 = n**2 + 1 - 2n
    * Formula = n**2 = (n-1)**2 + 2n - 1
    * */
    public static int square(int n){
        if(n==0){
            return 0;
        }
        return square(n-1) + 2*n - 1;
    }

    /*
    * Reverse a string
    * Input: Muskan B
    * Output: B naksuM
    * */
    public static String reverseString(String str){
        if(str.isEmpty()){
            return str;
        }
        int length = str.length();
        return str.charAt(length-1) + reverseString(str.substring(0,length-1));
    }
}
