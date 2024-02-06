package org.example;

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
}
