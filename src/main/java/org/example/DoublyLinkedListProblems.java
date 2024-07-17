package org.example;

import java.util.ArrayList;
import java.util.HashSet;

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

    //Find pairs of nos which have sum = k in a SORTED array.
    //Method 1: HashSet
    public static ArrayList<int[]> findPairs(ListNode<Integer> head, int k) {

        // Write your code here.
        ArrayList<int[]> list = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        ListNode<Integer> temp = head;
        while(temp!=null){
            if(set.contains(k-temp.data)){
                list.add(new int[]{k-temp.data, temp.data});
            }
            set.add(temp.data);
            temp = temp.next;
        }
        return list;
    }
    //Time: O(N)
    //Space: O(N)

    //Method 2: Two pointer
    public static ArrayList<int[]> findPairs2(ListNode<Integer> head, int k) {

        // Write your code here.
        ListNode<Integer> left = head;
        ListNode<Integer> right = head;
        while(right.next!=null){
            right = right.next;
        }
        ArrayList<int[]> list = new ArrayList<>();
        while (left != right && left.prev != right) {
            int sum = left.data + right.data;
            if (sum == k) {
                list.add(new int[]{left.data, right.data});
                left = left.next;
                right = right.prev;
            } else if (sum < k) {
                left = left.next;
            } else {
                right = right.prev;
            }
        }
        return list;
    }
    //Time: O(N)
    //Space: O(1)
}
