package org.example;

public class DoublyLinkedList<T> {

    private class ListNode {
        public T data;
        public ListNode prev;
        public ListNode next;

        public ListNode(){

        }

        public ListNode(T data){
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

    public ListNode head;
    public ListNode tail; //Not really required but we will implement it.
    public int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public DoublyLinkedList(T[] arr) {
        if(arr.length==0){
            return;
        }

        this.head = new ListNode(arr[0]);
        ListNode temp = head;
        int current = 1;
        while(current<arr.length){
            temp.next = new ListNode(arr[current]);
            temp = temp.next;
            current++;
        }
        temp.next = new ListNode(arr[arr.length-1]);
        this.tail = temp.next;
    }

    @Override
    public String toString() {
        String ans =  "MyLinkedList{[";
        if(this.size == 0){
            return ans+="]}";
        }
        ListNode temp = this.head;
        while(temp!=null){
            ans+=temp.data + "->";
            temp = temp.next;
        }
        ans+="END";
        return  ans;
    }

    public boolean isEmpty(){
        return this.head==null;
    }


}
