import java.util.*;
/*This program for
1) Creating graph
2) Doing DFS and BFS in graph
3) Determine it there the undirected graph is cyclic or not
Note:-
1) It is undirected graph
2) It is 0 based graph
* */
public class Graph {
    static void DFS1(ArrayList<Integer>[] graph,boolean[] visitedNodes,int src){
        //Mark
        if(visitedNodes[src]==true){
            return ;
        }
        visitedNodes[src]=true;

        //Work
        System.out.print(src+" ");

        //Visit unvisited nodes
        for(int nbr:graph[src]){
            DFS1(graph,visitedNodes,nbr);
        }
    }
    static void DFS(ArrayList<Integer>[] graph,int noOfNodes){
        boolean[] visitedNodes=new boolean[noOfNodes];
        System.out.println("The DFS traversal for GRAPH:- ");
        DFS1(graph,visitedNodes,0);
        System.out.println();
    }
    static void BFS1(ArrayList<Integer>[] graph, boolean[] visitedNodes, int src){
        Queue<Integer> que=new LinkedList<>();
        que.add(src);
        int level=0;
        while(que.size()>0){
            int size=que.size();
            System.out.print("Level "+level+" -> ");
            while(size-->0) {
                //Remove
                int curr = que.remove();

                //Mark
                if (visitedNodes[curr] == true) continue;
                visitedNodes[curr] = true;
                //Work
                System.out.print(curr + " ");

                //Visit unvisited Nodes
                for (int nbr : graph[curr]) {
                    if(visitedNodes[nbr]==false) {
                        que.add(nbr);
                    }
                }
            }
            System.out.println();
            level++;
        }
    }
    static void BFS(ArrayList<Integer>[] graph,int noOfNodes){
        boolean[] visitedNodes=new boolean[noOfNodes];
        System.out.println("The BFS traversal for GRAPH:- ");
        BFS1(graph,visitedNodes,0);
    }
    static boolean isCyclic2(ArrayList<Integer>[] graph, boolean[] visitedNodes, int noOfNodes, int src){
        Queue<Integer> que=new LinkedList<>();
        que.add(src);
        while(que.size()>0){
            //Remove
            int curr=que.remove();

            //Mark
            if(visitedNodes[curr]==true) return true;
            visitedNodes[curr]=true;

            //Work
            //Nothing here to work

            //Visit unvisited nodes
            for(int nbr:graph[curr]){
                if(visitedNodes[nbr]==true)continue;
                que.add(nbr);
            }
        }
        return false;
    }
    static boolean isCyclic(ArrayList<Integer>[] graph, int noOfNodes){
        boolean[] visitedNodes=new boolean[noOfNodes];
        for(int i=0;i<noOfNodes;i++){
            if(visitedNodes[i]==true){
                continue;
            }
            boolean iscyclic=isCyclic2(graph,visitedNodes,noOfNodes,i);
            if(iscyclic) return true;
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the no.of nodes/vertices:- ");
        int n=sc.nextInt();
        System.out.print("Enter the no.of edges:- ");
        int edge=sc.nextInt();
        ArrayList<ArrayList<Integer>> edges=new ArrayList<>();
        System.out.println("Enter all the edges of the undirected graph");
        for(int edg=0;edg<edge;edg++){
            int first=sc.nextInt();
            int second=sc.nextInt();
            ArrayList<Integer> temp=new ArrayList<>();
            temp.add(first);
            temp.add(second);
            edges.add(temp);
        }
        ArrayList<Integer>[] graph=new ArrayList[n];
        for(int i=0;i<n;i++){
            graph[i]=new ArrayList<Integer>();
        }
        for(ArrayList<Integer> Edge:edges){
            int u=Edge.get(0);
            int v=Edge.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }
        displayOfGraph(graph);
        DFS(graph,n);
        BFS(graph,n);
        boolean findCyclic=isCyclic(graph,n);
        if(findCyclic)
            System.out.println("It is Cyclic Undirected Graph");
        else
            System.out.println("It is Acyclic Undirected Graph");
    }
    static void displayOfGraph(ArrayList<Integer>[] graph){
        System.out.println("GRAPH Representation");
        int idx=0;
        for(ArrayList<Integer> itr:graph){
            System.out.println(idx+++" -> "+itr);
        }
    }
}
