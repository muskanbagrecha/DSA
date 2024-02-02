package org.example;

public class MyLinkedList<T> {

    private Node head;

    public MyLinkedList() {
        this.head = null;
    }

    private class Node {
        private T data;
        public Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }
    }

    //utility methods
    public int size(){
        Node temp = head;
        int ctr=0;
        while(temp!=null){
            ctr++;
            temp = temp.next;
        }
        return ctr;
    }

    public boolean isEmpty(){
        return head==null;
    }

    public void add(T data){
        Node newNode = new Node(data);
        if(head==null){
            head = newNode;
            return;
        }
        Node temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = newNode;
    }

}

