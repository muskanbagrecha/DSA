package org.example;

import java.util.NoSuchElementException;

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

    public MyLinkedList(T[] arr){
        if(arr.length == 0){
            return;
        }
        this.head = new Node(arr[0]);
        Node temp = head;
        for(int i = 1; i<arr.length; i++){
            temp.next = new Node(arr[i]);;
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
        Node temp = head;
        int current = 0;
        while (current != index) {
            temp = temp.next;
            current++;
        }
        return temp.getData();
    }

    public void removeFirst() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
    }

    public void removeLast() {
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

    @Override
    public String toString() {
        String ans = "MyLinkedList{";
        Node temp = this.head;
        while(temp.next!=null){ //ideally we would take it till end, but we are taking it till second last element and then adding last element outside of the loop so we dont add an extra comma.
            ans+= temp.getData().toString() + ",";
            temp = temp.next;
        }
        ans += temp.getData().toString() + "}";
        return ans;
    }

    public void removeAt(int index) {
        int size = this.size();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            if (head.next != null) {
                head = head.next;
            } else {
                head = null; //only one element is present
            }
            return;
        }
        int current = 0;
        Node prev = null;
        Node temp = head;
        while (current != index) {
            current++;
            prev = temp;
            temp = temp.next;
        }
        prev.next = temp.next;
    }
}

