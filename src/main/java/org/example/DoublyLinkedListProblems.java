package org.example;

public class DoublyLinkedListProblems {

    public static ListNode reverseDLL(ListNode head)
    {
        // Write your code here.
        ListNode temp = head;
        ListNode front;
        while(temp!=null){
            front = temp.next;
            temp.next = temp.prev;
            temp.prev = front;
            if(front==null){ //last element
                head = temp;
                break;
            }
            temp = front;
        }
        return head;
    }
}
