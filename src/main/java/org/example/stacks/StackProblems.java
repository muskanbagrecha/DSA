package org.example.stacks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class StackProblems {

    //it's worth noting that for the stack, you could also use `Deque<Integer>` as a stack, with `LinkedList` implementation,
    //because the `Stack` class is considered somewhat outdated. `Deque` is a more complete and consistent set of LIFO stack operations

//    https://www.geeksforgeeks.org/problems/delete-middle-element-of-a-stack/

    public static void deleteMiddle(Stack<Integer> inputStack, int N) {
        // WRITE YOUR CODE HERE
        int k = N % 2 == 0 ? (N / 2) - 1 : N / 2;
        delete(inputStack, k);
    }

    public static void delete(Stack<Integer> stack, int k) {
        if (k == 0) {
            stack.pop();
            return;
        }
        Integer temp = stack.pop();
        delete(stack, k - 1);
        stack.push(temp);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n) - recursion stack

    //Reverse a stack
    public static void reverse(Stack<Integer> stack, int n) {
        if (n == 1) {
            return; //already sorted.
        }
        Integer element = stack.pop();
        insert(stack, element, n - 1);
        reverse(stack, n - 1);
    }

    public static void insert(Stack<Integer> stack, Integer data, int index) {
        if (index == 0) {
            stack.push(data);
            return;
        }
        int top = stack.pop();
        insert(stack, data, index - 1);
        stack.push(top);
    }

    //Time complexity: O(n**2)
    //Space: O(n) - recursion stack

    //https://leetcode.com/problems/valid-parentheses/
    public static boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        int n = charArray.length;
        if (n % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        for (int i = 0; i < n; i++) {
            if (map.get(charArray[i]) == null) {
                if (!stack.isEmpty() && map.get(stack.peek()) - charArray[i] == 0) {
                    stack.pop();
                }
                return false;
            } else {
                stack.push(charArray[i]);
            }
        }
        return stack.isEmpty();
    }

    //Time complexity: O(n)
    //Space: O(n) for stack and map for storing the pairs of brackets

    //Slightly improved version (without maps) but time and space complexity is same.
    public static boolean isValid2(String s) {
        char[] charArray = s.toCharArray();
        int n = charArray.length;
        if (n % 2 != 0) { // just for early return
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = charArray[i];
            if (c == ')' || c == '}' || c == ']') {
                if (!stack.isEmpty() && (c - stack.peek() == 1 || c - stack.peek() == 2)) {
                    stack.pop();
                    continue;
                }
                return false;
            } else {
                stack.push(charArray[i]);
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid3(String s) {
        Stack<Character> stack = new Stack<Character>(); // create an empty stack
        for (char c : s.toCharArray()) { // loop through each character in the string
            if (c == '(') // if the character is an opening parenthesis
                stack.push(')'); // push the corresponding closing parenthesis onto the stack
            else if (c == '{') // if the character is an opening brace
                stack.push('}'); // push the corresponding closing brace onto the stack
            else if (c == '[') // if the character is an opening bracket
                stack.push(']'); // push the corresponding closing bracket onto the stack
            else if (stack.isEmpty() || stack.pop() != c) // if the character is a closing bracket
                // if the stack is empty (i.e., there is no matching opening bracket) or the top of the stack
                // does not match the closing bracket, the string is not valid, so return false
                return false;
        }
        // if the stack is empty, all opening brackets have been matched with their corresponding closing brackets,
        // so the string is valid, otherwise, there are unmatched opening brackets, so return false
        return stack.isEmpty();
    }

    //https://www.geeksforgeeks.org/problems/next-larger-element-1587115620
    public static long[] nextLargerElement(long[] arr, int n) {
        // Your code here
        Stack<Long> s = new Stack<>();
        long[] ans = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!s.empty() && s.peek() <= arr[i]) {
                s.pop();
            }
            ans[i] = s.empty()? -1 : s.peek();
            s.push(arr[i]);
        }
        return ans;
    }
    //Time & space: O(n)
    //Note: pushing of any element happens only once and popping will also happen at most once.
    // So, over the course of the entire function, there will be exactly n pushes and at most n pops in total.
    // This means that the stack operations (pushing and popping) are amortized over the n iterations, resulting in an average cost of O(1) per operation.
    //So total time complexity would be only O(n) for iterative over the original array.

    //Variant 2:
    // https://leetcode.com/problems/next-greater-element-i/
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> d = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[nums1.length];
        for(int i = nums2.length-1; i>=0; i--){
            while(!d.isEmpty() && d.peek()<nums2[i]){
                d.pop();
            }
            if(!d.isEmpty()){
                map.put(nums2[i], d.peek());
            }
            d.push(nums2[i]);
        }
        for(int i = 0; i<nums1.length; i++){
            arr[i] = map.getOrDefault(nums1[i], -1);
        }
        return arr;
    }
    //Time: O(m) + O(n) where m is length of nums1 and n is length of nums2
    //Space: O(n) for stack and O(n) for map where n is length of nums2

    //Nearest Greater to the left
    public static List<Integer> nearestGreaterToLeft(int[] nums){
        Stack<Integer> s = new Stack<>();
        List<Integer> list = new ArrayList<>();
        for(int num: nums){
            while (!s.isEmpty() && s.peek()<num){
                s.pop();
            }
            if(s.isEmpty()){
                list.add(-1);
            }
            else{
                list.add(s.peek());
            }
            s.push(num);
        }
        return list;
    }
    //O(n) - time

    //https://www.geeksforgeeks.org/problems/smallest-number-on-left3403/1
    //Smallest number on left

    static List<Integer> leftSmaller(int n, int a[])
    {
        //code here
        Stack<Integer> s = new Stack<>();
        List<Integer> list = new ArrayList<>();
        for(int num : a){
            while(!s.isEmpty() && s.peek()>=num){
                s.pop();
            }
            if(s.isEmpty()){
                list.add(-1);
            }
            else{
                list.add(s.peek());
            }
            s.push(num);
        }
        return list;
    }
    //Time & space: O(n)

}
