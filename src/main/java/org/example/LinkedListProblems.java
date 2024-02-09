package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LinkedListProblems {

    //    https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/
    //Solution 1:
    public Node deleteMiddle(Node head) {
        int size = 0;
        Node temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        int middle = size / 2;
        int current = 0;
        if (middle == 0) {
            return null;
        }
        temp = head;
        Node prev = temp;
        while (current != middle) {
            prev = temp;
            temp = temp.next;
            current++;
        }
        prev.next = temp.next;
        return head;
    }
    //O(n) but this is two pass solution: one to find the size, then to remove from mid

    //Solution 2
    public Node deleteMiddle2(Node head) {
        if(head==null || head.next == null){ //0 or 1 elements, we need to return null
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }
    //Hare and Turtle : O(n) - One pass solution
    //Why this works?
    //We have to have the check of head==null or head.next==null so we can straightaway return null as n: 0 => null, n:1 => 1/2 = 0 so we have to delete first element
    //1. Slow is moving at half of the pace of fast, so by the time fast reaches the end, slow will be at mid.
    //2. For two elements, fast will be null and we have to remove the first element. So while loop will never execute and 0th node will point to slow.next.next which is null.
    //3. For three elements, fast will be last element, while loop will never execute as fast.next will be null. So slow.next = slow.next.next will end up making first node point to last element(fast).
    //4. For odd elements, while will execute, fast will point to last element & slow will end up pointing to one index less than the mid so we can remove the next element
    //5. For even elements, while will execute, fast will end up becoming null, slow will point to one element left of element to be removed.

    //https://leetcode.com/problems/middle-of-the-linked-list/description/
    //Similar problem
    public static Node middleNode(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //Important to practice

    //Reverse a singly listed list which has only head

    //Solution 1: Using extra space (arr) to store the elements, we then iterate through the LL again and copy the elements from the end of the array.
    //Use stack instead of arraylist so we pop elements in reversed order by default
    public static Node reverseList(Node head) {
        ArrayList arr = new ArrayList();
        Node temp = head;
        while(temp!=null){
            arr.add(temp.data);
            temp=temp.next;
        }
        System.out.println(arr);
        temp = head;
        while(temp!=null && arr.size()>0){
            int index = arr.size()-1;
            temp.data = arr.get(index);
            arr.remove(index);
        }
        return head;
    }

    //Time complexity: O(n) - two passes
    //Space complexity: O(n)
    public static Node reverseList2(Node head) {
        Node temp = head;
        Node prev = null;
        Node front;
        while(temp!=null){
            front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
        return prev;
    }
//    Time: O(n) single pass
//    Space: O(1)

//    https://leetcode.com/problems/linked-list-cycle/

    //Brute force
    public static boolean hasCycle1(Node head) {
        if(head==null){
            return false;
        }
        HashSet<Node> mySet = new HashSet<>();
        Node temp = head;
        while(temp!=null){
            if(mySet.contains(temp)){
                return true;
            }
            mySet.add(temp);
        }
        return false;
    }

    //Space: O(n)
    //Time: O(n)

    //Optimal
    public boolean hasCycle2(Node head) {
        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                return true;
            }
        }
        return false;
    }
    //Hare and tortoise solution
    //Time: O(n)
    //Space: O(1)

    //Time Complexity (O(n)): The fast pointer moves twice as fast as the slow pointer, so if there's no cycle, the fast pointer will reach the end in n/2 steps. If a cycle exists, the pointers will meet within the cycle at some point, also resulting in O(n) time complexity.


    //https://leetcode.com/problems/linked-list-cycle-ii/description/

    //Naive:
    public Node detectCycle(Node head) {

        Node temp = head;
        HashSet<Node> set = new HashSet<>();
        while(temp!=null){
            if(set.contains(temp)){
                return temp;
            }
            set.add(temp);
            temp = temp.next;
        }
        return null;
    }

    //Time: O(n)
    //Space: O(n)

    //Optimal

    //https://www.codingninjas.com/studio/problems/find-length-of-loop_8160455

    //Naive:
    public static int lengthOfLoop(Node head) {
        // Write your code here

        Node temp = head;
        HashMap<Node, Integer> map = new HashMap<>();
        int ctr = 0;
        while(temp!=null){
            if(map.containsKey(temp)){
                return ctr - map.get(temp);
            }
            map.put(temp, ctr);
            ctr++;
            temp = temp.next;
        }
        return 0;
    }

    //Time: O(n)
    //Space: O(n)

    //Optimal
    public static int lengthOfLoop2(Node head) {
        // Write your code here

        if(head==null || head.next==null){
            return 0;
        }
        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                break;
            }
        }
        if(fast==null || fast.next==null){
            return 0;
        }
        slow = slow.next;
        int ctr = 1;
        while(slow!=fast){
            slow = slow.next;
            ctr++;
        }
        return ctr;
    }
    //Time: O(n)
    //Space: O(1)

}
