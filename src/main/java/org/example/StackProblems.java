package org.example;

import java.util.HashMap;
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

    //https://leetcode.com/problems/valid-parentheses/
    public static boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        int n = charArray.length;
        if(n%2!=0){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        for(int i = 0; i<n; i++){
            if(map.get(charArray[i])==null){
                if(!stack.isEmpty() && map.get(stack.peek())-charArray[i]==0){
                    stack.pop();
                }
                return false;
            }
            else{
                stack.push(charArray[i]);
            }
        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }

    //Time complexity: O(n)
    //Space: O(n) for stack and map for storing the pairs of brackets

    //Slightly improved version (without maps) but time and space complexity is same.
    public static boolean isValid2(String s) {
        char[] charArray = s.toCharArray();
        int n = charArray.length;
        if(n%2!=0){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i<n; i++){
            char c = charArray[i];
            if(c==')' || c=='}' || c==']'){
                if(!stack.isEmpty() && (c-stack.peek()==1 || c-stack.peek()==2)){
                    stack.pop();
                    continue;
                }
                return false;
            }
            else{
                stack.push(charArray[i]);
            }
        }
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }
}
