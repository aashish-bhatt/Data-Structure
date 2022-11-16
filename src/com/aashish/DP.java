package com.aashish;

public class DP {
    public static void main(String[] args) {

    }

    //  FIBONACCI NUMBER

    // 1) MEMOIZATION => DP IS 1D ARRAY WITH ALL VALUES -1
    private static int f1(int n, int[] dp) {
        if (n <= 1) {
            return n;
        }
        if (dp[n] != -1) return dp[n];
        return dp[n] = f1(n - 1, dp) + f1(n - 2, dp);
    }

    //  2) TABULATION

    public static int f2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //  3) SPACE OPTIMIZATION

    public static int f3(int n) {
        int prev2 = 0;
        int prev1 = 1;
        for (int i = 2; i <= n; i++) {
            int cur = prev1 + prev2;
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    //  FROG JUMP


    // 1) RECURSIVE

    static int f(int ind, int[] a) {
        if (ind == 0) return 0;
        int left = f(ind - 1, a) + Math.abs(a[ind] - a[ind - 1]);
        int right = Integer.MAX_VALUE;
        if (ind > 1) right = f(ind - 2, a) + Math.abs(a[ind] - a[ind - 2]);
        return Math.min(left, right);
    }


    //  2)  MEMOIZATION => dp array contains a.length+1 index with values as -1

    static int f(int ind,int [] a,int [] dp){
        if(ind==0) return 0;
        if(dp[ind]!=-1) return dp[ind];
        int left=f(ind-1,a,dp)+Math.abs(a[ind]-a[ind-1]);
        int right=Integer.MAX_VALUE;
        if(ind>1) right=f(ind-2,a,dp)+Math.abs(a[ind]-a[ind-2]);
        return dp[ind]=Math.min(left,right);
    }

    //  3)  TABULATION

    static int f(int [] a,int n){
        int [] dp=new int[n];
        dp[0]=0;
        for(int ind=1;ind<n;ind++){
            int fs=dp[ind-1]+Math.abs(a[ind]-a[ind-1]);
            int ss=Integer.MAX_VALUE;
            if(ind>1) ss=dp[ind-2]+Math.abs(a[ind]-a[ind-2]);
            dp[ind]=Math.min(fs,ss);
        }
        return dp[n-1];
    }

    //   4)  SPACE OPTIMIZATION

    static int f1(int [] a,int n){
        int prev=0;
        int prev2=0;
        for(int ind=1;ind<n;ind++){
            int fs=prev+Math.abs(a[ind]-a[ind-1]);
            int ss=Integer.MAX_VALUE;
            if(ind>1) ss=prev2+Math.abs(a[ind]-a[ind-2]);
            int curi=Math.min(fs,ss);
            prev2=prev;
            prev=curi;
        }
        return prev;
    }
}