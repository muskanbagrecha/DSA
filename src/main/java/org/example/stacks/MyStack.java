package org.example;

import java.util.EmptyStackException;

@SuppressWarnings("unchecked")
public class MyStack<T> {

    //Array Implementation

    private T[] array;
    private int maxSize;
    public int top;

    public MyStack() {
        this.top = -1;
    }

    public MyStack(int capacity) {
        this.maxSize = capacity;
        this.top = -1;
        this.array = (T[]) new Object[capacity];
    }

    public MyStack(T[] arr){
        this.maxSize = arr.length;
        this.top = -1;
        for(int i = 0; i<arr.length; i++){
            this.push(arr[i]); //this functionality has to be implemented
        }
    }

    public void push(T data){
        if(this.array==null){
            this.array = (T[]) new Object[10];
        }
        if(maxSize==array.length){
            resize();
        }
        top++;
        this.array[top] = data;
    }

    public T pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        T data = this.array[top];
        this.array[top] = null;
        top--;
        return data;
    }

    public T peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return this.array[top];
    }
    public boolean isEmpty(){
        return top==-1;
    }

    private void resize(){
        this.maxSize = 2*this.maxSize;
        T[] temp = (T[]) new Object[maxSize];
        for(int i = 0; i< array.length; i++){
            temp[i] = array[i];
        }
        this.array = temp;
    }
}

//        Operation     Time Complexity
//        isEmpty	    O(1)
//        peek	        O(1)
//        push	        O(1)
//        pop	        O(1)
//        resize        O(n)