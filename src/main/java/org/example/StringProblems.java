package org.example;

import java.util.Stack;

public class StringProblems {

//    1021. Remove Outermost Parentheses
//    https://leetcode.com/problems/remove-outermost-parentheses/description/

    //My first solution beats 70%:
    public static String removeOuterParentheses1(String s) {
        String ans = "";
        int ctr = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                ctr--;
            }
            if (ctr != 0) {
                ans += s.charAt(i);
            }
            if (c == '(') {
                ctr++;
            }
        }
        return ans;
    }

    // Or

    public static String removeOuterParentheses2(String s) {
        StringBuilder ans = new StringBuilder();
        int ctr = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ctr++;
                if (ctr != 1) {
                    ans.append(c);
                }
            } else {
                ctr--;
                if (ctr != 0) {
                    ans.append(c);
                }
            }
        }
        return ans.toString();
    }

    //    https://www.codingninjas.com/studio/problems/maximum-nesting-depth-of-the-parentheses_8144741
    public static int maxDepth(String s) {
        // Write your code here.
        int ctr = 0;
        int max = 0;
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (c == '(') {
                ctr++;
                max = Math.max(max, ctr);
            } else if (c == ')') {
                ctr--;
            }
        }
        return max;
    }

    public static String reverseWords(String s) {
        int n = s.length();
        int low = 0, high = s.length() - 1;
        char[] charArr = s.toCharArray();
        while (low < high) {
            char temp = charArr[low];
            charArr[low] = charArr[high];
            charArr[high] = temp;
            low++;
            high--;
        }
        int i = 0;
        Stack<Character> charStack = new Stack<>();
        while (i < n) {
            char c = s.charAt(i);
            if ((!charStack.isEmpty() && charStack.peek() != ' ') || c != ' ') {
                charStack.push(c);
            }
            i++;
        }
        StringBuilder sr = new StringBuilder();
        while (!charStack.isEmpty()) {
            char element = charStack.pop();
            sr.append(element);
        }
        return sr.toString();
    }

    //    https://leetcode.com/problems/largest-odd-number-in-string/description/
//    Brute force: O(n2) - gave TLE error
    public static String largestOddNumber(String num) {
        int n = num.length();
        String max = "";
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String substr = num.substring(i, j);
                if (substr.charAt(substr.length() - 1) % 2 != 0) {
                    if (max.length() < substr.length() && max.compareTo(substr) < 0) {
                        max = substr;
                    }
                }
            }
        }
        return max;
    }

    //Optimized: O(n)
    public static String largestOddNumber2(String num) {
        int n = num.length();
        for (int i = n - 1; i >= 0; i--) {
            char c = num.charAt(i);
            if ((c - '0') % 2 != 0) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }

    //    https://leetcode.com/problems/longest-common-prefix/description/
    public static String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        if (n == 1) {
            return strs[0];
        }
        java.util.Arrays.sort(strs);
        String first = strs[0];
        String last = strs[n - 1];
        int i;
        for (i = 0; i < first.length() && i < last.length(); i++) {
            if (first.charAt(i) != last.charAt(i)) {
                break;
            }
        }
        return strs[0].substring(0, i);
    }

//    public String longestCommonPrefix2(String[] strs) {
//        if (strs == null || strs.length == 0)
//            return "";
//
//        java.util.Arrays.sort(strs);
//        String first = strs[0];
//        String last = strs[strs.length - 1];
//        int c = 0;
//        while (c < first.length()) {
//            if (first.charAt(c) == last.charAt(c))
//                c++;
//            else
//                break;
//        }
//        return c == 0 ? "" : first.substring(0, c);
//    }

    //    https://leetcode.com/problems/largest-3-same-digit-number-in-string/
    public static String largestGoodInteger(String num) {
        int n = num.length();
        char[] charArray = num.toCharArray();
        String ans = "";
        for (int i = 0; i < n - 2; i++) {
            boolean isValid = true;
            for (int j = i + 1; j < i + 3; j++) {
                if (charArray[i] != charArray[j]) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                String sub = num.substring(i, i + 3);
                if (ans.compareTo(sub) < 0) {
                    ans = sub;
                }
            }
        }
        return ans;
    }

    //Better solution: We dont really need the inner loop as we just want adjacent two numbers which we can find by indices.
    public String largestGoodInteger2(String num) {
        int n = num.length();
        char[] charArray = num.toCharArray();
        String ans = "";
        for (int i = 2; i < n; i++) {
            if (charArray[i] == charArray[i - 1] && charArray[i] == charArray[i - 2]) {
                String sub = num.substring(i - 2, i + 1);
                if (ans.compareTo(sub) < 0) {
                    ans = sub;
                }
            }
        }
        return ans;
    }

    //Unique solution:
    public String largestGoodInteger3(String num) {
        int max = 999;
        while (max > 0) {
            if (num.contains(Integer.toString(max))) {
                return Integer.toString(max);
            }
            max -= 111;
        }
        if (num.contains("000")) {
            return "000";
        }
        return "";
    }

    //    https://leetcode.com/problems/rotate-string/description/
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) return false;
        String combines = s + s;
        if (combines.contains(goal)) {
            return true;
        }
        return false;
    }

}


