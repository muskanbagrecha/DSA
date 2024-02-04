package org.example;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {

    private Node head;

    public SinglyLinkedList() {
        this.head = null;
    }



    public SinglyLinkedList(T[] arr) {
        if (arr.length == 0) {
            return;
        }
        this.head = new Node(arr[0]);
        Node temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new Node(arr[i]);
            ;
            temp = temp.next;
        }
    }

    //utility methods
    public int size() {
        Node temp = head;
        int ctr = 0;
        while (temp != null) {
            ctr++;
            temp = temp.next;
        }
        return ctr;
    }

    @Override
    public String toString() {
        String ans = "MyLinkedList{";
        if (head == null) {
            ans += "[]}";
            return ans;
        }
        Node temp = this.head;
        while (temp.next != null) { //ideally we would take it till end, but we are taking it till second last element and then adding last element outside of the loop so we dont add an extra comma.
            ans += temp.getData().toString() + ",";
            temp = temp.next;
        }
        ans += temp.getData().toString() + "}";
        return ans;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void addFirst(T data) {
        Node newNode = new Node(data);
        newNode.next = head; //even if head is null, no problem.
        head = newNode;

    }

    public T get(int index) {
        int size = this.size();
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> temp = head;
        int current = 0;
        while (current != index) {
            temp = temp.next;
            current++;
        }
        return temp.getData();
    }

    public void removeFirstElement() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
    }

    public void removeLastElement() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        if (head.next == null) { //only one element is present
            head = null;
            return;
        }
        Node temp = head;
        Node prev = temp;
        while (temp.next != null) {
            prev = temp;
            temp = temp.next;
        }
        prev.next = null;
    }

    public void removeAt(int index) {
        int size = this.size();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
            return;
        }
        int current = 0;
        Node temp = head;
        Node prev = temp;
        while (current != index) {
            prev = temp;
            temp = temp.next;
            current++;
        }
        prev.next = temp.next;
    }

    //    https://leetcode.com/problems/remove-linked-list-elements/description/
    public void removeElement(T data) {
        if (head == null) {
            return;
        }
        Node temp = head;
        Node prev = null;
        while (temp != null) {
            if (temp.getData() == data) {
                if (prev == null) {
                    head = temp.next;
                } else {
                    prev.next = temp.next;
                }
            } else {
                prev = temp;
            }
            temp = temp.next;
        }
    }

    //Steps:
    //1. prev should be initialized to null, temp will be initialized to head
    //2. we will run the loop till last element (temp!=null)
    //3. Check first if temp data is the element to be removed,
    //3.1. If yes, check if prev is null. If it is it means temp is pointing to the first element in the array, which means we need to update head to point to second element (temp.next)
    //3.2 If prev is not null, it will point to the element after temp so temp can be skipped(removed)
    //4. If temp is not the element to be removed, it means we need to keep going forward so prev will become temp
    //5 Common step: update temp to temp.next. This is common as at any point we need to keep going forward else it will cause infinite loop.

    //Solution 2:
    public Node removeElements2(Node head, int data) {
        if (head == null) {
            return null;
        }
        Node dummy = new Node();
        dummy.next = head;
        Node temp = head;
        Node prev = dummy;
        while (temp != null) {
            if (temp.getData().equals(data)) {
                prev.next = temp.next;
            } else {
                prev = temp;
            }
            temp = temp.next;
        }
        return dummy.next;
    }

    //Here, we create a dummy node and set dummy.next = head. This node helps us keep track of the new head in case the existing head has to be removed.
    //We are always updating dummy as prev = dummy so as and when prev is changing

    public void removeLastOccurenceOf(T data) {
        if (head == null) {
            return;
        }
        int lastIndex = -1;
        int counter = 0;
        Node temp = head;
        while (temp != null) {
            if (temp.getData().equals(data)) {
                lastIndex = counter;
            }
            temp = temp.next;
            counter++;
        }
        if (lastIndex != -1) {
            removeAt(lastIndex);
        }
    }
}

