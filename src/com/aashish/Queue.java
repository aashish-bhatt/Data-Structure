package com.aashish;
import java.util.LinkedList;
import java.util.*;
import java.util.Stack;

class queue {
    public static void main(String[] args) throws Exception {
        Scanner s=new Scanner(System.in);
        int [] petrol=new int[34];
        for (int i = 0; i < 34; i++) {
            petrol[i]=s.nextInt();
        }
        int [] distance=new int[34];
        for (int i = 0; i < 34; i++) {
            distance[i]=s.nextInt();
        }
        int n=petrol.length;
        for(int i=0;i<n;i++){
            if(distance[i]<=petrol[i]){
                int c=i;
                int count=0;
                int remain=0;
                for(int j=i;j<i+n;j++){
                    if(distance[j%n]<=petrol[j%n]){
                        remain+=petrol[j%n]-distance[j%n];
                        count++;
                    }
                    else{
                        remain-=distance[j%n]-petrol[j%n];
                        if(remain<0){
                            break;
                        }
                        count++;
                    }
                }
                if(count==4){
                    System.out.println(i);
                }
            }
        }
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

//                                               IMPLEMENT QUEUE USING STACK

class queueUsingStack{
    Stack<Integer> s1=new Stack<>();
    Stack<Integer> s2=new Stack<>();
    void push(int data){
        s1.push(data);
    }
    void pop(){
        while(!s1.isEmpty()){
            s2.push(s1.pop());
        }
        int ele=s2.pop();
        while(!s2.isEmpty()){
            s1.push(s2.pop());
        }
        System.out.println(ele);
    }
}

//                                                IMPLEMENT STACK USING QUEUE

class stackUsingQueue{
        Queue<Integer> q1=new LinkedList<>();
        Queue<Integer> q2=new LinkedList<>();
        void push(int data){
           while(!q1.isEmpty()){
               q2.offer(q1.poll());
           }
           q1.offer(data);
            while(!q2.isEmpty()){
                q1.offer(q2.poll());
            }
        }
        void pop(){
            System.out.println(q1.poll());
        }

}

//                                                              DEQUE