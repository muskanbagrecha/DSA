package org.example;

public class Node<T> {
    public T data;
    public Node<T> next;

    @Override
    public String toString() {
        return data.toString();
    }

    public Node() {

    }

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
