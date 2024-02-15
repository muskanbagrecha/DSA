package org.example;

import java.util.Stack;

public class StackProblems {

//    https://www.geeksforgeeks.org/problems/delete-middle-element-of-a-stack/

    public static void deleteMiddle(Stack<Integer> inputStack, int N) {
        // WRITE YOUR CODE HERE
        int k = N%2==0 ? (N/2)-1: N/2;
        delete(inputStack, k);
    }

    public static void delete(Stack<Integer> stack, int k){
        if(k==0){
            stack.pop();
            return;
        }
        Integer temp = stack.pop();
        delete(stack, k-1);
        stack.push(temp);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n) - recursion stack
}
