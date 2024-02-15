package org.example;

import java.util.Stack;

public class Recursion {

    public static void printTillN(int N) {
        //1 to N
        if (N == 0) {
            return;
        }
        printTillN(N - 1);
        System.out.print(N + " ");
    }

    public static void printFromN(int N) {
        //N to 1
        if (N == 0) {
            return;
        }
        System.out.print(N + " ");
        printTillN(N - 1);
    }

    /*
     * input: Muskan, 3
     * Output: Muskan Muskan Muskan
     * */
    public static void printNtimes(String s, int n) {
        if (n == 0) {
            return;
        }
        System.out.print(s + " ");
        printNtimes(s, n - 1);
    }

    /*Square of a number
     *Input: 5
     * Output: 25
     * (n-1)**2 = n**2 + 1 - 2n
     * Formula = n**2 = (n-1)**2 + 2n - 1
     * */
    public static int square(int n) {
        if (n == 0) {
            return 0;
        }
        return square(n - 1) + 2 * n - 1;
    }

    /*
     * Reverse a string
     * Input: Muskan B
     * Output: B naksuM
     * */
    public static String reverseString(String str) {
        if (str.isEmpty()) {
            return str;
        }
        int length = str.length();
        return str.charAt(length - 1) + reverseString(str.substring(0, length - 1));
    }

    //Sort an array using recursion only
    int[] sortArr(int[] arr, int n) {
        if (n == 1) {
            return arr; //sorted
        }
        int data = arr[n - 1];
        sortArr(arr, n - 1);
        insert(arr, n - 1, data);
        return arr;
    }

    public static void insert(int[] arr, int n, int data) {
        if (n == 0 || data >= arr[n - 1]) {
            arr[n] = data;
            return;
        }
        int current = arr[n - 1];
        insert(arr, n - 1, data);
        arr[n] = current;
    }

    //Time complexity: O(n**2). O(n) for outer loop. Inner loop in worst case can run upto n time for each elements in the array because it might have to compare the current element with each of the already sorted elements (in a reverse-sorted array situation).
    //Space complexity: O(n) - recursion stack

    //Sort a stack

    public static Stack<Integer> sortStack(Stack<Integer> s)
    {
        int stackSize = s.size();
        if(stackSize==1){ //only one element is present so it is sorted
            return s;
        }

        Integer top = s.pop();
        sortStack(s);
        insert(s, top);
        return s;
    }
    public static void insert(Stack<Integer> s, Integer element){
        int stackSize = s.size();
        if(stackSize == 0 || element >= s.peek()){
            s.push(element);
            return;
        }
        Integer temp = s.pop();
        insert(s, element);
        s.push(temp);
    }

    //Time: O(n**2)
    //Space: O(n)

    //Reverse a stack
    public static void reverse(Stack<Integer> stack, int n){
        if(n==1){
            return; //already sorted.
        }
        Integer element = stack.pop();
        insert(stack, element, n-1);
        reverse(stack, n-1);
    }

    public static void insert(Stack<Integer> stack, Integer data, int index){
        if(index==0){
            stack.push(data);
            return;
        }
        int top = stack.pop();
        insert(stack, data, index-1);
        stack.push(top);
    }

    //Time complexity: O(n**2)
    //Space: O(n) - recursion stack

}
