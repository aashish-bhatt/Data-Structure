package com.aashish;
import java.io.*;
import java.util.*;
public class Sorting {
    public static void main(String[] args) {
        int [] a={5,4,-32,2,10,0,-6};
//         bubbleSort(a);
//        selectionSort(a);
        insertionSort(a);
    }
                                            // BUBBLE SORT,SINKING SORT,EXCHANGE SORT

    // Time complexity => Best O(n)(Sorted Array) , Worst O(n^2) (Descending ordered Sorted Array)
    // Space complexity = Constant O(1)
    // In-place sorting algorithm --> No extra space needed
    // As size of array is growing the number of comparisons are also growing
    // Stable Sorting algorithm --> When original order is maintained for values that are equal
    static void bubbleSort(int[] a){
        boolean swapped;
        for (int i = 0; i <a.length-1; i++) {
            swapped=false;
            for (int j = 1; j <a.length-i; j++) {
                if(a[j]<a[j-1]){
                    int temp=a[j];
                    a[j]=a[j-1];
                    a[j-1]=temp;
                    swapped=true;
                }
            }
            // if there is no swap in the pass it means it is already sorted
            if(!swapped) break;
        }
        display(a);
    }
    static void display(int [] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }

                                            // SELECTION SORT

    //Select a element and put it at its correct index
    // Time complexity => Best O(n^2), Worst O(n^2)
    // Space complexity = Constant O(1)
    // Not stable
    
    static void selectionSort(int [] a){
        for (int i = 0; i < a.length-1; i++) {
            // find maximum item in remaining array and put it in correct index
            int last=a.length-i-1;
            int max=getMax(a,last);
            int temp=a[last];
            a[last]=a[max];
            a[max]=temp;
        }
        display(a);
    }

    private static int getMax(int[] a, int last) {
        int max=0;
        for (int i = 0; i <= last; i++) {
            if(a[max]<a[i]){
                max=i;
            }
        }
        return max;
    }

                                                // Insertion Sort

    // Time complexity -> Worst O(n^2)  Best O(n)
    // Stable
    // Used for smaller value of n
    // Best for partially sorted array -> Takes part in hybrid sorted algorithm
    public static void insertionSort(int [] a){
        for(int i=0;i<a.length-1;i++){
            for (int j = i+1; j>0; j--) {
                if(a[j]<a[j-1]){
                    int temp=a[j];
                    a[j]=a[j-1];
                    a[j-1]=temp;
                }
                else{
                    break;
                }
            }
        }
        display(a);
    }
}
