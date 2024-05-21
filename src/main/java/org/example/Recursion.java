package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public static Stack<Integer> sortStack(Stack<Integer> s) {
        int stackSize = s.size();
        if (stackSize == 1) { //only one element is present so it is sorted
            return s;
        }

        Integer top = s.pop();
        sortStack(s);
        insert(s, top);
        return s;
    }

    public static void insert(Stack<Integer> s, Integer element) {
        int stackSize = s.size();
        if (stackSize == 0 || element >= s.peek()) {
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

    // https://leetcode.com/problems/k-th-symbol-in-grammar
    //Brute force
    // Generate each row explicitly until the nth row and then returning the kth symbol in that row.
    public int kthGrammar(int n, int k) {
        if (n == 1) return 0; // Base case
        StringBuilder prevRow = new StringBuilder("0"); // Starting with the first row
        StringBuilder currentRow = new StringBuilder();

        for (int row = 2; row <= n; row++) {
            currentRow.setLength(0); // Reset currentRow for the new row
            // Copy previous row
            for (int i = 0; i < prevRow.length(); i++) {
                char c = prevRow.charAt(i);
                // Append '01' for '0' and '10' for '1'
                if (c == '0') {
                    currentRow.append("01");
                } else {
                    currentRow.append("10");
                }
            }
            prevRow.setLength(0); // Clear prevRow
            prevRow.append(currentRow.toString()); // Set prevRow to currentRow for next iteration
        }
        return prevRow.charAt(k - 1) - '0'; // Convert char to int ('0' or '1') and return
    }

//        This has time & space complexity: 2^n (horrible)

    //Optimize using recursion due to the pattern that first half of the elements will be same as previous and second half will be just opposite
    public static int kthGrammar2(int n, int k) {
        if (n == 1) {
            return 0;
        }
        if (k <= Math.pow(2, n - 2)) {
            return kthGrammar2(n - 1, k);
        } else {
            int newK = k - (int) Math.pow(2, n - 2);
            return kthGrammar2(n - 1, newK) == 0 ? 1 : 0;
        }
    }

    //Subset problems
    //https://leetcode.com/problems/subsets/description/
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        findSubsets(list, new ArrayList<>(), nums, 0);
        return list;
    }

    public void findSubsets(List<List<Integer>> list, List<Integer> current, int[] nums, int start) {
        if (start == nums.length) {
            list.add(new ArrayList<Integer>(current));
            return;
        }
        findSubsets(list, current, nums, start + 1);
        int num = nums[start];
        current.add(num);
        findSubsets(list, current, nums, start + 1);
        current.remove(current.size() - 1);
    }

    //Time: O(n*2^n)  where n accounts for the operations to construct and copy subsets, and 2^n represents the total number of subsets.
    //Space: O(n) recursion stack only (not counting output storage)

    //Print all substrings of a string
    public static void printSubsets(String s) {
        printSubsetsHelper("", s, 0);
    }

    public static void printSubsetsHelper(String output, String input, int index) {
        if (index == input.length()) {
            System.out.println(output);
            return;
        }
        // Not including the current character
        printSubsetsHelper(output, input, index + 1);
        // Including the current character
        printSubsetsHelper(output + input.charAt(index), input, index + 1);
    }

    //Time: O(2^n) at each node we have two choices whether to include or not
    //Space: O(n) recursion stack

    //Find unique subsets
    //https://leetcode.com/problems/subsets-ii
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        HashSet<List<Integer>> set = new HashSet<>();
        java.util.Arrays.sort(nums);
        findSubsets(set, new ArrayList<>(), nums, 0);
        return new ArrayList<>(set);
    }

    public void findSubsets(HashSet<List<Integer>> set, List<Integer> current, int[] nums, int start) {
        if (start == nums.length) {
            set.add(new ArrayList<Integer>(current));
            return;
        }
        findSubsets(set, current, nums, start + 1);
        int num = nums[start];
        current.add(num);
        findSubsets(set, current, nums, start + 1);
        current.remove(current.size() - 1);
    }

    //Used a hashset to store only unique subsets.
    //We have to sort because order of elements in subset matters
    //Time: O(nlogn) for sorting, O(2^n) subset generation, O(n) hashset insertion and copying hashset to list
    //Time: O(nlogn) + O(n * 2^n)
    //Space: O(n*2^n) hashset, O(n) recursion stack

    //Approach 2:
    //Without hashset

    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        java.util.Arrays.sort(nums); // Sort the array to handle duplicates
        findSubsets(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void findSubsets2(List<List<Integer>> list, List<Integer> current, int[] nums, int start) {
        list.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            // Skip duplicates
            if (i > start && nums[i] == nums[i - 1]) continue;
            current.add(nums[i]);
            findSubsets2(list, current, nums, i + 1);
            current.remove(current.size() - 1);
        }
    }

    //https://www.geeksforgeeks.org/problems/permutation-with-spaces3627/1
    ArrayList<String> permutation(String S){
        // Code Here
        ArrayList<String> list = new ArrayList<>();
        generatePermutationsWithSpaces(S.substring(1), String.valueOf(S.charAt(0)), list); //we are passing from 1st index as we dont want permutations starting from space. (it will generate all permutations twice)
        return list;
    }

    public void generatePermutationsWithSpaces(String input, String output, ArrayList<String> list){
        if(input.isEmpty()){
            list.add(output);
            return;
        }
        char c = input.charAt(0);
        String newInput = input.substring(1);

        String newOutput = output + c;
        String newOutputWithSpace = output + " " + c;

        //With space
        generatePermutationsWithSpaces(newInput, newOutputWithSpace, list);

        //Without space
        generatePermutationsWithSpaces(newInput, newOutput, list);
    }

    //Time: O(n * 2^(n-1)) => O(n*2^n)
    //Space: O(n) - recursion stack

    //Generate binary strings with no consecutive 1s.
    public static List< String > generateBinaryString(int N) {
        List<String> list = new ArrayList<>();
        generateBinaryStringHelper("", list, N);
        return list;
    }
    public static void generateBinaryStringHelper(String output, List<String> list, int n){
        if(n==0){
            list.add(output);
            return;
        }
        generateBinaryStringHelper(output+"0", list, n-1);
        if(output.length()==0 || output.charAt(output.length()-1)!='1'){
            generateBinaryStringHelper(output+"1", list, n-1);
        }
    }

    //Time: O(Fib(N)) - This is because for any string of length n, you can arrive there by adding a "0" to a string of length n-1 (no constraint here) or by adding "10" to a string of length n-2
    //Space: O(Fib(N)) - recursion stack

    //https://leetcode.com/problems/generate-parentheses/
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        generateParenthesisHelper(res, n, n, "");
        return res;
    }

    public static void generateParenthesisHelper(List<String> res, int open, int close, String s) {
        if (open==0 && close==0) {
            res.add(s);
            return;
        }
        if (open != 0) {
            generateParenthesisHelper(res, open - 1, close, s + "(");
        }
        if (close > open) { //bracket has been opened previously so we can close it
            generateParenthesisHelper(res, open, close - 1, s + ")");
        }
    }

    //Time: O(2^n)
    //Space: O(2^n) - recursion depth

    //If we have to print the subsets: https://www.hackerearth.com/problem/algorithm/print-subset-sum-to-k
    public static void generateSubsets(int n, int[] a, int k, List<Integer> list){
        if(n==a.length){
            if(k==0){
                for(int data : list){
                    System.out.print(data + " ");
                }
                System.out.println();
            }
            return;
        }
        if(a[n]>k){
            generateSubsets(n+1, a, k, list);
            return;
        }
        generateSubsets(n+1, a, k, list);
        list.add(a[n]);
        generateSubsets(n+1, a, k-a[n], list);
        list.remove(list.size()-1);
    }

    //https://leetcode.com/problems/combination-sum
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        generate(candidates, target, res, current, 0);
        return res;
    }

    public void generate(int[] candidates, int target, List<List<Integer>> res, List<Integer> current, int index){
        if(index==candidates.length){
            if(target==0){
                res.add(new ArrayList<>(current));
            }
            return;
        }
        if(candidates[index]<=target){
            current.add(candidates[index]);
            generate(candidates, target-candidates[index], res, current, index);
            current.remove(current.size()-1);
        }
        generate(candidates, target, res, current, index+1);
    }
    //TC: exponential -> 2^t where t is target (t coz we can pick same element multiple times so perhaps even t times.
    //SC: k * x where k is the length of the combination and x is no of combination

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        java.util.Arrays.sort(candidates); //we have to sort it so we can skip duplicates based on next index.
        generate(list, current, candidates, target, 0);
        return list;
    }

    public void generate(List<List<Integer>> list, List<Integer> current, int[] candidates, int target, int index){
        if(target==0){ //valid combination
            list.add(new ArrayList<>(current));
            return;
        }
        if(index==candidates.length || index<0){
            return;
        }
        if(index<candidates.length && candidates[index]<=target){
            current.add(candidates[index]);
            generate(list, current, candidates, target-candidates[index], index+1);
            current.remove(current.size()-1);
        }
        int nextIndex = index+1;
        while(nextIndex<candidates.length && candidates[nextIndex]==candidates[index]){
            nextIndex++;
        }
        generate(list, current, candidates, target, nextIndex);
    }
    //TC: O(2^n) worst case when all elements are unique. With duplicates, some recursive calls are pruned (not explored), which reduces the number of combinations

    //https://leetcode.com/problems/letter-combinations-of-a-phone-number
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        generate(res, letters, digits, 0, "");
        return res;
    }

    public void generate(List<String> res, String[] letters, String digits, int index, String output){
        if(index==digits.length()){
            if(output.length()>0){
                res.add(output);
            }
            return;
        }
        char currentDigit = digits.charAt(index);
        for(char c : letters[currentDigit - '0'].toCharArray()){
            generate(res, letters, digits, index+1, output+c);
        }
    }

    public int subsetXORSum(int[] nums) {
        return sum(nums, 0, 0);
    }
    public int sum(int[] nums, int start, int currSum){
        if(start==nums.length){
            return currSum;
        }
        int ans1 = sum(nums, start+1, currSum);
        int ans2 = sum(nums, start+1, currSum^nums[start]);
        return ans1+ans2;
    }
}
