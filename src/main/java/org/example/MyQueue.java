package org.example;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class MyQueue<T> {

    //Array Implementation
    private T[] array;
    private int capacity;
    private int size;
    public int front;
    public int rear;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
        front = 0;
        rear = -1;
    }

    public void enqueue(T e) {
        // Write your code here.
        if(size==capacity){
            throw new RuntimeException("Queue is full");
        }
        int nextIndex = (rear+1)%capacity;
        rear = nextIndex;
        this.array[rear] = e;
        size++;
    }

    // Dequeue (retrieve) the element from the front of the queue.
    public T dequeue() {
        // Write your code here.
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        size--;
        int temp = front;
        front = front+1%capacity;
        return array[temp];
    }

    public T peek(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return array[front];
    }

    public boolean isEmpty(){
        return size==0;
    }

}
