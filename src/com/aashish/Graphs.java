package com.aashish;

import javax.imageio.metadata.IIOMetadataFormatImpl;
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
        ArrayList<ArrayList<Integer>> adj=createGraph(v,e);

        // CREATION OF DIRECTED GRAPH
       // ArrayList<ArrayList<Integer>> adj=createGraphDirected(v,e);

        // CREATION OF DIRECTED GRAPH WITH WEIGHT
       // ArrayList<ArrayList<pair>> adj=createWeightedDirectedGraph(v,e);

        // CREATION OF UNDIRECTED WEIGHTED GRAPH
        //ArrayList<ArrayList<pair>> adj=createWeightedUndirectedGraph(v,e);

        //  BFS TRAVERSAL
      //  bfsOfGraph(adj,v);

        //  DFS TRAVERSAL
       // dfsOfGraph(adj,v);

        //  CYCLE DETECTION IN UNDIRECTED GRAPH USING BFS
       // System.out.println(checkCycleB(v,adj));

        //  CYCLE DETECTION IN UNDIRECTED GRAPH USING DFS
      //  System.out.println(checkCycleD(v,adj));

        //  BIPARTITE GRAPH DETECTION USING BFS
      //  System.out.println(BipartiteDetectionBfs(adj,v));

        //  BIPARTITE GRAPH DETECTION USING DFS
      //  System.out.println(BipartiteDetectionDfs(adj,v));

        //  CYCLE DETECTION IN DIRECTED GRAPH USING DFS
       // System.out.println(DirectedCheckCycleDfs(v,adj));

        //  TOPOLOGICAL SORT DFS
      //  topologicalSortDfs(v,adj);

        //  TOPOLOGICAL SORT BFS
       // topologicalSortBfs(v,adj);

        //  CYCLE DETECTION IN DIRECTED GRAPH USING BFS
       // System.out.println(DirectedCheckCycleBfs(v,adj));

        //  SHORTEST PATH IN UNDIRECTED GRAPH WITH UNIT WEIGHTS
        //int source=s.nextInt();
       //   shortestDistanceINUndirectedGraph(adj,v,source);

        //  SHORTEST PATH IN DIRECTED WEIGHTED ACYCLIC GRAPH
       // int source=s.nextInt();
        //  shortestDistanceINDirectedGraph(adj,v,source);

        //  DIJKSTRA ALGORITHM
        //int source=s.nextInt();
        //  Dijkstra(adj,v,source);

        //  PRIMS FOR MST
        //prims(adj,v);

        //  DISJOINT SET
//        makeSet(v);
//        int operation=s.nextInt();
//        while(operation>0){
//            int a=s.nextInt();
//            int b=s.nextInt();
//            union(a,b);
//            operation--;
//        }
//        //if 2 and 3 belongs to same component
//        if(findParent(2)!=findParent(3)){
//            System.out.println("Different component");
//        }
//        else{
//            System.out.println("Same Component");
//        }

        //  BRIDGES IN GRAPH ==> BRIDGES ARE THOSE EDGES WHOSE REMOVAL FROM GRAPH DIVIDES GRAPH INTO 2 OR MORE COMPONENTS
        printEdges(adj,v);

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

                                                            //  GRAPH CREATION FOR DIRECTED WEIGHTED GRAPH
    private static ArrayList<ArrayList<pair>> createWeightedDirectedGraph(int v, int e) {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter Edges");
        ArrayList<ArrayList<pair>> adj=new ArrayList<ArrayList<pair>>();
        for (int i = 0; i <=v; i++) {
            adj.add(new ArrayList<>());
        }
        while(e>0){
            int startEdge=s.nextInt();
            int endEdge=s.nextInt();
            int weight=s.nextInt();
            adj.get(startEdge).add(new pair(endEdge,weight));
            e--;
        }
        return adj;
    }

                                                            //  GRAPH CREATION FOR UNDIRECTED WEIGHTED GRAPH

    private static ArrayList<ArrayList<pair>> createWeightedUndirectedGraph(int v, int e) {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter Edges");
        ArrayList<ArrayList<pair>> adj=new ArrayList<ArrayList<pair>>();
        for (int i = 0; i <=v; i++) {
            adj.add(new ArrayList<>());
        }
        while(e>0){
            int startEdge=s.nextInt();
            int endEdge=s.nextInt();
            int weight=s.nextInt();
            adj.get(startEdge).add(new pair(endEdge,weight));
            adj.get(endEdge).add(new pair(startEdge,weight));
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
        pair(){}
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

                                                        //  CYCLE DETECTION IN DIRECTED GRAPH USING DFS

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

                                                        //  SHORTEST PATH IN UNDIRECTED GRAPH WITH UNIT WEIGHTS

    private static void shortestDistanceINUndirectedGraph(ArrayList<ArrayList<Integer>> adj, int v, int source) {
        int[] dis=new int[v+1];
        Arrays.fill(dis,1000000000);
        dis[source]=0;
        Queue<Integer> q=new LinkedList<>();
        q.add(source);
        while(!q.isEmpty()){
            int node =q.poll();
            for(Integer e:adj.get(node)){
                if(dis[node]+1<dis[e]){
                    q.add(e);
                    dis[e]=dis[node]+1;
                }
            }
        }

        // minimum distance of all the nodes from source
        for(int i=1;i<=v;i++){
            System.out.print(dis[i]+" ");
        }
        System.out.println();
    }

                                                        //  SHORTEST PATH IN DIRECTED WEIGHTED ACYCLIC GRAPH

    private static void shortestDistanceINDirectedGraph(ArrayList<ArrayList<pair>> adj, int v,int source1) {
        Stack<Integer> st=new Stack<>();
        int [] dis=new int[v+1];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[source1]=0;
        Boolean[] vis=new Boolean[v+1];
        Arrays.fill(vis,false);
        for(int i=1;i<=v;i++){
            if(!vis[i]){
                topoSortDfs(i,st,adj,vis);
            }
        }
        while(!st.isEmpty()){
            int node=st.pop() ;
            if(dis[node]!=Integer.MAX_VALUE){
                for(pair e:adj.get(node)){
                    if(dis[node]+e.second<dis[e.first]){
                        dis[e.first]=dis[node]+dis[e.second];
                    }
                }
            }
        }
        for(int i=1;i<=v;i++){
             if(dis[i]==Integer.MAX_VALUE){
                 System.out.print("Unrechable");
             }
             else{
                 System.out.print(dis[i]);
             }
        }
        System.out.println();
    }

    private static void topoSortDfs(int i, Stack<Integer> st, ArrayList<ArrayList<pair>> adj, Boolean[] vis) {
        vis[i]=true;
        for(pair e:adj.get(i)){
            if(!vis[e.first]){
                topoSortDfs(e.first,st,adj,vis);
            }
        }
        st.push(i);
    }

                                                                //  DIJKSTRA ALGORITHM


    private static void Dijkstra(ArrayList<ArrayList<pair>> adj, int v, int source) {
        int dist[] = new int[v+1];

       Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Noode> pq = new PriorityQueue<Noode>(v, new Noode());
        pq.add(new Noode(source, 0));

        while(pq.size() > 0) {
            Noode node = pq.poll();

            for(pair it: adj.get(node.getV())) {
                if(dist[node.getV()] + it.second < dist[it.first]) {
                    dist[it.first] = dist[node.getV()] + it.second;
                    pq.add(new Noode(it.first, dist[it.first]));
                }
            }
        }

        for (int i = 1; i <=v; i++)
        {
            System.out.print( dist[i] + " ");
        }
    }

                                                            //  MINIMUM SPANNING TREE USING PRIMS ALGO

    //  1) Brute Force ->  T.C ->O(n^2)

    private static void prims(ArrayList<ArrayList<pair>> adj, int v) {
        int[] key=new int[v];
        boolean[] mst=new boolean[v];
        int[] parent=new int[v];
        Arrays.fill(key,Integer.MAX_VALUE);
        Arrays.fill(mst,false);
        Arrays.fill(parent,-1);

        key[0]=0;

        for (int i = 0; i <v-1; i++) {
            int min=Integer.MAX_VALUE,u=0;
            for (int j = 0; j < v; j++) {
                if(!mst[j] && key[j]<min){
                    min=key[j];
                    u=j;
                }
            }
            mst[u]=true;
            for(pair it:adj.get(u)){
                if(!mst[it.first] && it.second<key[it.first]){
                    parent[it.first] =u;
                    key[it.first]=it.second;
                }
            }

        }
        for (int i = 1; i < v; i++) {
            System.out.println(parent[i]+"--"+ i);
        }
    }

    //  2) Efficient Way : T.C ->O(n*log(n))

    private static void prims1(ArrayList<ArrayList<Noode>> adj, int v) {
        int[] key = new int[v];
        boolean[] mst = new boolean[v];
        int[] parent = new int[v];
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(mst, false);
        Arrays.fill(parent, -1);
        PriorityQueue<Noode> pq=new PriorityQueue<Noode>(v,new Noode());
        key[0]=0;
        pq.add(new Noode(0,key[0]));

        while(!pq.isEmpty()) {
            int u=pq.poll().getV();
            mst[u] = true;
            for (Noode it : adj.get(u)) {
                if (!mst[it.getV()] && it.getWeight() < key[it.getV()]) {
                    parent[it.getV()] = u;
                    key[it.getV()] = it.getWeight();
                    pq.add(new Noode(it.getV(),key[it.getV()]));
                }
            }
        }
        for (int i = 1; i < v; i++) {
            System.out.println(parent[i] + "--" + i);
        }
    }

                                                           //  DISJOINT SET

    static int[] parent=new int[10000];
    static int[] rank=new int[10000];
    private static void makeSet(int v) {
        for (int i = 1; i <=v; i++) {
            parent[i]=i;
            rank[i]=0;
        }
    }

    static int findParent(int node){
        if(node==parent[node]){
            return node;
        }
        return parent[node]=findParent(parent[node]);
    }

    static void union(int u,int v){
        int u1=findParent(u);
        int v1=findParent(v);
        if(rank[u1]<rank[v1]){
            parent[u1]=v1;
        }
        else if(rank[u1]>rank[v1]){
            parent[v1]=u1;
        }
        else {
            parent[v1] = u1;   // or it can be parent[u]=v;
            rank[u1]++;
        }
    }

                                                            //  PRINT BRIDGES IN UNDIRECTED GRAPH

    private static void printEdges(ArrayList<ArrayList<Integer>> adj,int v) {
        int[] vis=new int[v+1];
        int[] toi=new int[v+1];      // TIME OF INSERTION
        int[] ltoi=new int[v+1];     // LOWEST TIME OF INSERTION

        int timer=0;
        for (int i = 1; i <=v; i++) {
            if(vis[i]==0){
                bridgeDfs(i,-1,adj,vis,toi,ltoi,timer);
            }
        }
    }

    private static void bridgeDfs(int i, int parent, ArrayList<ArrayList<Integer>> adj, int[] vis, int[] toi, int[] ltoi, int timer) {
        vis[i]=1;
        toi[i] = ltoi[i]= timer++;
        for(Integer e:adj.get(i)){
            if(e==parent) continue;

            if(vis[e]==0){
                bridgeDfs(e,i,adj,vis,toi,ltoi,timer);
                ltoi[i]=Math.min(ltoi[i],ltoi[e]);

                if(ltoi[e]>toi[i]){
                    System.out.println(i+" "+ e);
                }
            }
            else{
                ltoi[i]=Math.min(ltoi[e],ltoi[i]);
            }
        }
    }

}
class Noode implements Comparator<Noode>
{
    private int v;
    private int weight;

    Noode(int _v, int _w) { v = _v; weight = _w; }

    Noode() {}

    int getV() { return v; }
    int getWeight() { return weight; }

    @Override
    public int compare(Noode node1, Noode node2)
    {
        if (node1.weight < node2.weight)
            return -1;
        if (node1.weight > node2.weight)
            return 1;
        return 0;
    }

}
