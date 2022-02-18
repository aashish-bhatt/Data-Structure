package com.aashish;

import java.util.*;
import java.util.Stack;

class Graph {

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        System.out.print("number of nodes-->");
        int v=s.nextInt();
        System.out.print("number of Edges-->");
        int e=s.nextInt();

        // CREATION OF UNDIRECTED GRAPH
       // ArrayList<ArrayList<Integer>> adj=createGraph(v,e);

        // CREATION OF DIRECTED GRAPH
        ArrayList<ArrayList<Integer>> adj=createGraphDirected(v,e);

        //  BFS TRAVERSAL
        bfsOfGraph(adj,v);

        //  DFS TRAVERSAL
        dfsOfGraph(adj,v);

        //  CYCLE DETECTION IN UNDIRECTED GRAPH USING BFS
        System.out.println(checkCycleB(v,adj));

        //  CYCLE DETECTION IN UNDIRECTED GRAPH USING DFS
        System.out.println(checkCycleD(v,adj));

        //  BIPARTITE GRAPH DETECTION USING BFS
        System.out.println(BipartiteDetectionBfs(adj,v));

        //  BIPARTITE GRAPH DETECTION USING DFS
        System.out.println(BipartiteDetectionDfs(adj,v));

        //  CYCLE DETECTION IN DIRECTED GRAPH USING DFS
        System.out.println(DirectedCheckCycleDfs(v,adj));

        //  TOPOLOGICAL SORT DFS
        topologicalSortDfs(v,adj);

        //  TOPOLOGICAL SORT BFS
        topologicalSortBfs(v,adj);

        //  CYCLE DETECTION IN DIRECTED GRAPH USING BFS
        System.out.println(DirectedCheckCycleBfs(v,adj));
    }



    // UNDIRECTED GRAPH CREATION

    private static ArrayList<ArrayList<Integer>> createGraph(int v,int e) {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter Edges");
        ArrayList<ArrayList<Integer>> adj=new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <=v; i++) {
            adj.add(new ArrayList<>());
        }
        while(e>0){
            int startEdge=s.nextInt();
            int endEdge=s.nextInt();
            adj.get(startEdge).add(endEdge);
            adj.get(endEdge).add(startEdge);
            e--;
        }
        return adj;
    }

                                                                    // DIRECTED GRAPH CREATION

    private static ArrayList<ArrayList<Integer>> createGraphDirected(int v,int e) {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter Edges");
        ArrayList<ArrayList<Integer>> adj=new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <=v; i++) {
            adj.add(new ArrayList<>());
        }
        while(e>0){
            int startEdge=s.nextInt();
            int endEdge=s.nextInt();
            adj.get(startEdge).add(endEdge);
            e--;
        }
        return adj;
    }


                                                                        //  BFS TRAVERSAL

    private static void bfsOfGraph(ArrayList<ArrayList<Integer>> adj, int v) {
        ArrayList<Integer> bfs=new ArrayList<>();
        boolean [] vis=new boolean[v+1];

        for(int i=1;i<=v;i++){
            if(!vis[i]){
                Queue<Integer> q=new LinkedList<>();
                q.add(i);
                vis[i]=true;
                while(!q.isEmpty()){
                    Integer node=q.poll();
                    bfs.add(node);

                    for(Integer it:adj.get(node)){
                        if(!vis[it]){
                            vis[it]=true;
                            q.add(it);
                        }
                    }
                }
            }
        }

        for(int e:bfs){
            System.out.print(e+" ");
        }
        System.out.println();
    }

                                                                           //  DFS TRAVERSAL

    private static void dfsOfGraph(ArrayList<ArrayList<Integer>> adj, int v ){
        ArrayList<Integer> storeDfs=new ArrayList<>();
        boolean[] vis=new boolean[v+1];
        for (int i = 1; i <=v; i++) {
            if(!vis[i]){
                dfs(i,vis,adj,storeDfs);
            }
        }
        for (Integer e:storeDfs) {
            System.out.print(e+" ");
        }
        System.out.println();
    }

    private static void dfs(int node, boolean[] vis, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> storeDfs) {
        storeDfs.add(node);
        vis[node]=true;
        for(Integer it:adj.get(node)){
            if(!vis[it]){
                dfs(it,vis,adj,storeDfs);
            }
        }
    }

                                                                //  CHECK CYCLE IN UNDIRECTED  USING BFS

    static class pair{
        int first;
        int second;
        pair(int first,int second){
            this.first=first;
            this.second=second;
        }
    }

    private static boolean checkCycleB(int v, ArrayList<ArrayList<Integer>> adj) {
        boolean [] vis=new boolean[v+1];
        Arrays.fill(vis,false);

        for (int i = 1; i <=v; i++) {
            if(!vis[i]){
                if(undirectedCycleDetection(adj,vis,i)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean undirectedCycleDetection(ArrayList<ArrayList<Integer>> adj, boolean[] vis, int i) {
        Queue<pair> q=new LinkedList<>();
        q.add(new pair(i,-1));
        vis[i]=true;
        while(!q.isEmpty()){
            pair p=q.poll();
            int f=p.first;
            int l=p.second;
            for(Integer e:adj.get(f)){
                if(!vis[e]){
                    q.add(new pair(e,f));
                    vis[e]=true;
                }
                else if(l!=e){
                    return true;
                }
            }
        }
        return false;
    }
                                                            // CHECK CYCLE IN UNDIRECTED  USING DFS

    private static boolean checkCycleD(int v, ArrayList<ArrayList<Integer>> adj) {
        boolean [] vis=new boolean[v+1];
        Arrays.fill(vis,false);

        for (int i = 1; i <=v; i++) {
            if(!vis[i]){
                if(undirectedCycleDetectionD(i,-1,adj,vis)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean undirectedCycleDetectionD(int i, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        vis[i]=true;
        for(Integer e:adj.get(i)){
            if(!vis[e]){
                if(undirectedCycleDetectionD(e, i, adj, vis)) return true;
            }
            else if(e!=parent){
                return true;
            }
        }
        return false;
    }

                                                                    // BIPARTITE GRAPH DETECTION USING BFS

    private static boolean BipartiteDetectionBfs(ArrayList<ArrayList<Integer>> adj, int v) {
        int [] color=new int[v+1];
        Arrays.fill(color,-1);

        for(int i=1;i<=v;i++){
            if(color[i]==-1){
                if(!BipartiteCheckBfs(i,adj,color)) return false;
            }
        }
        return true;
    }

    private static boolean BipartiteCheckBfs(int i, ArrayList<ArrayList<Integer>> adj, int[] color) {
        Queue<Integer> q=new LinkedList<>();
        q.add(i);
        color[i]=1;
        while(!q.isEmpty()){
            Integer node=q.poll();
            for(Integer e:adj.get(node)){
                if(color[e]==-1) {
                    q.add(e);
                    color[e]=1-color[node];
                }
                else if(color[e]==color[node]){
                    return false;
                }
            }
        }
        return true;
    }
                                                                // BIPARTITE GRAPH DETECTION USING DFS

    private static boolean BipartiteDetectionDfs(ArrayList<ArrayList<Integer>> adj, int v) {
        int [] color=new int[v+1];
        Arrays.fill(color,-1);

        for(int i=1;i<=v;i++){
            if(color[i]==-1){
                if(!BipartiteCheckDfs(i,adj,color)) return false;
            }
        }
        return true;
    }

    private static boolean BipartiteCheckDfs(int i, ArrayList<ArrayList<Integer>> adj, int[] color) {
        if(color[i]==-1){
            color[i]=1;
        }
        for(Integer e:adj.get(i)){
            if(color[e]==-1){
                color[e]=1-color[i];
                if(!BipartiteCheckDfs(e,adj,color)) return false;
            }
            else if(color[e]==color[i]){
                return false;
            }
        }
        return true;
    }

                                                        //  CYCLE DETECTION IN DIRECTED GRAPH

    private static boolean DirectedCheckCycleDfs(int v, ArrayList<ArrayList<Integer>> adj) {
        int [] vis=new int[v+1];
        int [] dfs=new int[v+1];
        for (int i = 1; i <=v; i++) {
            if(vis[i]==0){
                if(DirectedDetectCycleDfs(i, adj, vis, dfs)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean DirectedDetectCycleDfs(int i, ArrayList<ArrayList<Integer>> adj, int[] vis, int[] dfs) {
        vis[i]=1;
        dfs[i]=1;
        for(Integer e:adj.get(i)){
            if(vis[e]==0){
                if(DirectedDetectCycleDfs(e,adj,vis,dfs)) return true;
            }
            else if(dfs[e]==1){
                return true;
            }
        }
        dfs[i]=0;
        return false;
    }

                                                                        //  TOPOLOGICAL SORT DFS

    public static void topologicalSortDfs(int v,ArrayList<ArrayList<Integer>> adj){
        Stack<Integer> st=new Stack<>();
        int [] vis=new int[v+1];
        for (int i = 1; i <=v; i++) {
            if(vis[i]==0) {
                topoDfs(i, st, adj, vis);
            }
        }
       ArrayList<Integer> topo=new ArrayList<>();
        while(!st.isEmpty()){
            topo.add(st.pop());
        }
        // print
        for (int i = 0; i < topo.size(); i++) {
            System.out.print(topo.get(i)+" ");
        }
        System.out.println();
    }

    private static void topoDfs(int i, Stack<Integer> st, ArrayList<ArrayList<Integer>> adj, int[] vis) {
        vis[i]=1;
        for(Integer e:adj.get(i)){
            if(vis[e]==0){
                topoDfs(e,st,adj,vis);
            }
        }
        st.push(i);
    }

                                                                    // TOPOLOGICAL SORT BFS

    private static void topologicalSortBfs(int v, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> top=new ArrayList<>();
        int[] inDegree=new int[v+1];
        for(int i=1;i<=v;i++){
            for(Integer e:adj.get(i)){
                inDegree[e]++;
            }
        }
        Queue<Integer> q=new LinkedList<>();
        for(int i=1;i<=v;i++){
            if(inDegree[i]==0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int node=q.poll();
            top.add(node);
            for(Integer e:adj.get(node)){
                inDegree[e]--;
                if(inDegree[e]==0){
                    q.add(e);
                }
            }
        }
        for(int a:top){
            System.out.print(a+" ");
        }
        System.out.println();
    }

                                                //  DETECT CYCLE IN DIRECTED GRAPH USING BFS

    private static boolean DirectedCheckCycleBfs(int v, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> top=new ArrayList<>();
        int[] inDegree=new int[v+1];
        for(int i=1;i<=v;i++){
            for(Integer e:adj.get(i)){
                inDegree[e]++;
            }
        }
        Queue<Integer> q=new LinkedList<>();
        for(int i=1;i<=v;i++){
            if(inDegree[i]==0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int node=q.poll();
            top.add(node);
            for(Integer e:adj.get(node)){
                inDegree[e]--;
                if(inDegree[e]==0){
                    q.add(e);
                }
            }
        }
        if(top.size()==v){
            return false;
        }
        return true;
    }

}
