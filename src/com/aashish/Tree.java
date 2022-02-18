package com.aashish;

import java.util.*;
import java.util.Stack;

public class Tree {
    static class Node {
        int data;
        Node right, left;

        Node(int data) {
            this.data = data;
        }
    }

    static Scanner s = null;

    public static void main(String[] args) {
        s = new Scanner(System.in);
        Node root = createTree();
        inOrder(root);
        System.out.println();

        preOrder(root);
        System.out.println();

        postOrder(root);
        System.out.println();

        // Height of a particular node in a binary tree (we are considering node count as its height ex a single node in binary tree have height =1)
        System.out.println(getHeight(root));

        // Size of a binary tree (No of nodes in a tree);
        System.out.println(getSize(root));

        // Minimum element in the tree
        System.out.println(getMin(root));

        // Maximum element in the tree
        System.out.println(getMax(root));

        // level order traversal in O(N^2) time complexity
        for (int i = 1; i <= getHeight(root); i++) {
            levelOrder(root, i);
        }
        System.out.println();

        // level order optimised traversal in O(N) time complexity using queue data structure
        optimisedLevelOrder(root);
        System.out.println();

        // Print level order traversal in line by line manner
        lineByLineLevelOrder(root);

        // Left view of a Binary Tree
        leftView(root);
        System.out.println();

        // Right view of a Binary Tree
        rightView(root);
        System.out.println();

        // Top View of a Binary Tree
        topView(root);
        System.out.println();

        // Bottom View of a Binary tree
        bottomView(root);

        // Printing all traversals (inorder,reorder,postorder) in one traversal
        allOrder(root);

         //Diameter(Largest path between two leaf Nodes ) of a Binary tree
        diameter(root);

        zigZagOrderTraversal(root);

        }

    static Node createTree() {
        Node root = null;
        System.out.print("Enter Data :");
        int data = s.nextInt();
        if (data == -1) {
            return null;
        } else {
            root = new Node(data);
        }

        System.out.println("Enter left for " + data);
        root.left = createTree();
        System.out.println("Enter Right for " + data);
        root.right = createTree();

        return root;
    }

    static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    static void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    static void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    static int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    static int getSize(Node root) {
        if (root == null) {
            return 0;
        }
        return getSize(root.left) + getSize(root.right) + 1;
    }

    static int getMax(Node root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int max = Math.max(getMax(root.left), getMax(root.right));
        return Math.max(root.data, max);
    }

    static int getMin(Node root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int min = Math.min(getMin(root.left), getMin(root.right));
        return Math.min(root.data, min);
    }

    static void levelOrder(Node root, int level) {
        if (root == null) {
            return;
        } else if (level == 1) {
            System.out.print(root.data + " ");
        } else if (level > 1) {
            levelOrder(root.left, level - 1);
            levelOrder(root.right, level - 1);
        }
    }

    static void optimisedLevelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            System.out.print(cur.data + " ");
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
    }

    static void lineByLineLevelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur == null) {
                if (q.isEmpty()) return;
                q.offer(null);
                System.out.println();
                continue;
            }
            System.out.print(cur.data + " ");
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
    }

    static void leftView(Node root) {
       ArrayList<Node> list=new ArrayList<>();
       leftView1(root,list,0);
        for (Node cur:list) {
            System.out.print(cur.data+" ");
        }
    }
    private static void leftView1(Node root, ArrayList<Node> list, int level) {
       if(root==null) return;
        if(list.size()==level){
            list.add(root);
        }
       leftView1(root.left,list,level+1);
       leftView1(root.right,list,level+1);
    }

    static void rightView(Node root) {
        ArrayList<Node> list=new ArrayList<>();
        rightView1(root,list,0);
        for (Node cur:list) {
            System.out.print(cur.data+" ");
        }
    }
    private static void rightView1(Node root, ArrayList<Node> list, int level) {
        if(root==null) return;
        if(list.size()==level){
            list.add(root);
        }
        else{
            list.set(level,root);
        }
        rightView1(root.left,list,level+1);
        rightView1(root.right,list,level+1);
    }


    static class Pair{
        Node a;
        int b;
        Pair(Node a, int b){
            this.a = a;
            this.b = b;
        }
    }
    public static void topView(Node root)
    {
        ArrayList<Integer> list=new ArrayList<>();
        if(root==null) System.out.println("null");
        TreeMap<Integer,Node> map=new TreeMap<>();
        Queue<Pair> q=new LinkedList<Pair>();
        q.add(new Pair(root,0));
        while(!q.isEmpty()){
            Pair p=q.remove();
            Node node=p.a;
            int line=p.b;
            if(!map.containsKey(line)){
                map.put(line,node);
            }
            if(node.left!=null){
                q.add(new Pair(node.left,line-1));
            }
            if(node.right!=null){
                q.add(new Pair(node.right,line+1));
            }
        }
        for(Map.Entry<Integer,Node> e:map.entrySet()){
            list.add(e.getValue().data);
        }
        for(int i:list){
            System.out.print(i+" ");
        }
    }
    public static void bottomView(Node root)
    {
        ArrayList<Integer> list=new ArrayList<>();
        if(root==null) System.out.println("null");
        TreeMap<Integer,Node> map=new TreeMap<>();
        Queue<Pair> q=new LinkedList<Pair>();
        q.add(new Pair(root,0));
        while(!q.isEmpty()){
            Pair p=q.remove();
            Node node=p.a;
            int line=p.b;
            map.put(line,node);
            if(node.left!=null){
                q.add(new Pair(node.left,line-1));
            }
            if(node.right!=null){
                q.add(new Pair(node.right,line+1));
            }
        }
        for(Map.Entry<Integer,Node> e:map.entrySet()){
            list.add(e.getValue().data);
        }
        for(int i:list){
            System.out.print(i+" ");
        }
    }

    private static void allOrder(Node root) {
        Stack<Pair> st=new Stack<Pair>();
        st.push(new Pair(root,1));
        ArrayList<Integer> pre=new ArrayList<>();
        ArrayList<Integer> in=new ArrayList<>();
        ArrayList<Integer> post=new ArrayList<>();
        while(!st.isEmpty()){
            Pair p=st.pop();
            if(p.b==1){
                pre.add(p.a.data);
                p.b ++;
                st.push(p);
                if(p.a.left!=null){
                    st.push(new Pair(p.a.left,1));
                }
            }
            else  if(p.b==2){
                in.add(p.a.data);
                p.b++;
                st.push(p);
                if(p.a.right!=null){
                    st.push(new Pair(p.a.right,1));
                }
            }
            else{
                post.add(p.a.data);
            }
        }
        System.out.println();
        System.out.print("Pre order Traversal :--");
        for(int i:pre){
           System.out.print(i+" ");
        }
        System.out.println();
        System.out.print("In order Traversal :--");
        for(int i:in){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.print("Post order Traversal :--");
        for(int i:post){
            System.out.print(i+" ");
        }
        System.out.println();
    }


    private static void diameter(Node root) {
       int[] diameter=new int[1];
       height(root,diameter);
        System.out.println(diameter[0]);
    }

    private static int height(Node node, int[] diameter) {
        if (node == null) {
            return 0;
        }
        int lh = height(node.left, diameter);
        int rh = height(node.right, diameter);
        diameter[0] = Math.max(diameter[0], lh + rh);
        return 1 + Math.max(lh, rh);
    }

    private static void zigZagOrderTraversal(Node root) {
        ArrayList<ArrayList<Integer>> list=new ArrayList<>();
        Queue<Node> q=new LinkedList<>();
        q.offer(root);
        q.offer(null);
        int count=0;
        ArrayList<Integer> l=new ArrayList<Integer>();
        while(!q.isEmpty()){
            Node cur=q.poll();
            if(cur==null){
                if(count%2==0){
                    list.add(l);
                }
                else{
                    ArrayList<Integer> temp=reverseArrayList(l);
                    list.add(temp);
                }
                count++;
                if(q.isEmpty()) break;
                q.offer(null);
                l=new ArrayList<Integer>();
            }
            else{
                l.add(cur.data);
                if(cur.left!=null){
                    q.offer(cur.left);
                }
                if(cur.right!=null){
                    q.offer(cur.right);
                }
            }
        }
       for(ArrayList<Integer> e:list){
           System.out.print(e);
       }
    }
    public static ArrayList<Integer> reverseArrayList(ArrayList<Integer> alist)
    {
        // Arraylist for storing reversed elements
        ArrayList<Integer> revArrayList = new ArrayList<Integer>();
        for (int i = alist.size() - 1; i >= 0; i--) {

            // Append the elements in reverse order
            revArrayList.add(alist.get(i));
        }

        // Return the reversed arraylist
        return revArrayList;
    }
}
