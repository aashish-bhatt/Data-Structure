package com.aashish;

import java.util.Scanner;

public class recursion {

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int n=5,sum=0;
        int  [] a=new int[n];
        for (int i = 0; i < n; i++) {
            a[i]=s.nextInt();
        }
        int x=0,y=a.length-1;
        revArray(a,x,y);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }

    //  SUM OF FIRST N NUMBERS

    // recursion through parameters
    static int f(int num,int sum){
        if(num==0){
            return sum;
        }
        sum+=num;
        num=num-1;
        return f(num,sum);
    }

    //recursion through funcions
    static int f1(int num){
        if(num==0){
            return 0;
        }
        return num+f1(num-1);
    }

                                         //  FACTORIAL OF A NUMBER

    static int fact(int num){
        if(num==0){
            return 1;
        }
        return num* fact(num-1);
    }

                                            // REVERSE A ARRAY USING RECURSION


    private static void revArray(int[] a, int x, int y) {
        if(x>=y){
            return ;
        }
        int temp=a[x];
        a[x]=a[y];
        a[y]=temp;
        x++;
        y--;
        revArray(a,x,y);
    }


}
