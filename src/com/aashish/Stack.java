package com.aashish;

import java.io.*;
import java.util.*;

public class Stack {
    public static void main(String[] args) throws Exception {
        LLStack s=new LLStack();
        s.push(9);
        s.push(5);
        s.push(3);
        s.push(7);
        s.peek();
        s.pop();
        s.peek();
        s.pop();
        s.pop();
        s.pop();
        s.size();
    }
}
//                                                       STACK WITH ARRAYS
class MyStack{
    int top;
    int capacity;
    int [] a;
    MyStack(int size){
        capacity=size;
        top=-1;
        a=new int[size];
    }
    void push(int data) throws Exception {
        if(top==capacity-1){
            throw new Exception("Overflow");
        }
        top++;
        a[top]=data;
    }
    void pop() throws Exception {
        if(top==-1){
            throw new Exception("Underflow");
        }
        int res=a[top];
        top--;
        System.out.println(res);
    }
    void peek() throws Exception {
        if(top==-1){
            throw new Exception("Underflow");
        }
        int res=a[top];
        System.out.println(res);
    }
}
//                                               STACK WITH LINKED LIST
class Node{
    int data;
    Node next;
    Node(int d){
        this.data=d;
        this.next=null;
    }
}

class LLStack{
    int size;
    Node head;
    void push(int data){
        Node temp=new Node(data);
        temp.next=head;
        size++;
        head=temp;
    }
    void pop() throws Exception {
        if(head==null){
            throw new Exception("Underflow");
        }
        int res=head.data;
        head=head.next;
        size--;
        System.out.println(res);
    }
    void peek() throws Exception{
        if(head==null){
            throw new Exception("Underflow");
        }
        int res=head.data;
        System.out.println(res);
    }
    void size(){
        System.out.println(size);
    }
}