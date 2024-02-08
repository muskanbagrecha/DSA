package org.example;

public class ListNode<T> {
    public T data;
    public ListNode prev;
    public ListNode next;

    public ListNode() {

    }

    public ListNode(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    public ListNode(T data, ListNode prev, ListNode next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}
