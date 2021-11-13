package com.aashish;
import java.util.LinkedList;
import java.util.*;
public class Queue {
    public static void main(String[] args) throws Exception {
//        MyQueue q=new MyQueue();
//        q.enqueue(1);
//        q.enqueue(11);
//        q.dequeue();
//        q.enqueue(12);
//        q.enqueue(13);
//        q.dequeue();
//        q.enqueue(14);
//        q.dequeue();
//        q.dequeue();

        ArQueue q=new ArQueue(5);
        q.enqueue(1);
        q.enqueue(11);
        q.dequeue();
        q.enqueue(12);
        q.getFront();
        q.enqueue(13);
        q.dequeue();
        q.enqueue(14);
        q.dequeue();
        q.getFront();
        q.dequeue();

    }
}
//                                               QUEUE USING LINKED LIST
class MyQueue{
    Node front,rear;
    void enqueue(int data){
        Node newNode=new Node(data);
        if(front==null){
            front=newNode;
            rear=newNode;
        }
        rear.next=newNode;
        rear=rear.next;
    }
    void dequeue() throws Exception{
        if(front==null){
           throw new Exception("Empty queue");
        }
        int ele= front.data;
        front=front.next;
        System.out.println(ele);
    }

}

//                                             QUEUE USING ARRAYS

class ArQueue{
    int [] a;
    int capacity;
    int rear;
    ArQueue(int n){
        capacity=n;
        a=new int[n];
        rear=-1;
    }
    void enqueue( int data) throws  Exception{
       if(rear==capacity-1){
           throw new Exception("Overflow");
       }
       rear++;
       a[rear]=data;
    }
    void dequeue() throws Exception{
        if(rear==-1){
            throw new Exception("Underflow");
        }
        int ele=a[0];
        for (int i = 0; i < rear; i++) {
            a[i]=a[i+1];
        }
        rear--;
        System.out.println(ele);
    }
    void getFront() throws Exception{
        if(rear==-1){
            throw new Exception("Empty Array");
        }
        System.out.println(a[0]);
    }

}
                                                 // CIRCULAR QUEUE

class circularQueue{
    int [] a;
    int n;
    int front=-1,rear=-1;
    circularQueue(int n){
        this.n=n;
        a=new int[n];
    }
    void enqueue(int data) throws Exception{
        if((rear+1)%n==front){
            throw new Exception("Overflow");
        }
        if(front==-1){
            front=0;
        }
        rear=(rear+1)%n;
        a[rear]=data;
    }
    void dequeue() throws Exception{
        if(front == -1){
            throw new Exception("Underflow");
        }
        int ele=a[front];
        if(front==rear){
            front=-1;
            rear=-1;
        }
        else{
            front = (front+1)%n;
        }
        System.out.println(ele);
    }
    
}