package org.example;

public class Stack<T> {

    private T[] array;
    private int maxSize;
    public int top;

    public Stack() {
        this.top = -1;
    }

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        this.maxSize = capacity;
        this.top = -1;
        this.array = (T[]) new Object[capacity];
    }

    public Stack(T[] arr){
        this.maxSize = arr.length;
        this.top = -1;
        for(int i = 0; i<arr.length; i++){
            this.push(arr[i]); //this functionality has to be implemented
        }
    }

    public void push(T data){
        top++;
        array[top] = data;
    }

    public T pop(){
        T data = array[top];
        array[top] = null;
        top--;
        return data;
    }
}
