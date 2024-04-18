package org.example;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {


    public ListNode head;
    public ListNode tail; //Not really required but we will implement it.
    public int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public DoublyLinkedList(T[] arr) {
        if (arr.length == 0) {
            return;
        }

        this.head = new ListNode(arr[0]);
        ListNode temp = head;
        int current = 1;
        while (current < arr.length - 1) {
            temp.next = new ListNode(arr[current]);
            temp = temp.next;
            current++;
        }
        temp.next = new ListNode(arr[arr.length - 1]);
        this.tail = temp.next;
        this.size = arr.length;
    }

    @Override
    public String toString() {
        String ans = "MyLinkedList{";
        if (this.size == 0) {
            return ans += "}";
        }
        ListNode temp = this.head;
        while (temp != null) {
            ans += temp.data + "->";
            temp = temp.next;
        }
        ans += "END}";
        return ans;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void insertAtBeginning(T data) {
        if (head == null) {
            head = tail = new ListNode(data);
        } else {
            ListNode newNode = new ListNode(data);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        this.size++;
    }

    public void insertAtEnd(T data) {
        if (head == null) {
            head = tail = new ListNode(data);
        } else {
            ListNode temp = this.head;
            while (temp.next != null) {
                temp = temp.next;
            }
            ListNode newNode = new ListNode(data);
            temp.next = newNode;
            newNode.prev = temp;
            tail = newNode;
        }
        size++;
    }

    public void insertAtIndex(int index, T data) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            insertAtBeginning(data);
        }
        if (index == size) {
            insertAtEnd(data);
        }
        int current = 0;
        ListNode temp = head;
        while (current != index) {

        }
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (head == tail) {
            head = tail = null;
            return;
        }
        head = head.next;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (head == tail) {
            head = tail = null;
            return;
        }
        ListNode temp = tail.prev;
        tail = temp;
        temp.next = null;
    }

    public void removeAtIndex() {

    }

    public void removeElement() {

    }


}
