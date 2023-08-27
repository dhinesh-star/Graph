import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
This project is to
I/P:-
1. vertex:- number of vertex
2. no_of_edges:- number of edges
3. edges[no_of_edges][2]:- enter edge connection
O/P:-
1. Create a undirected graph
2. DFS traversal on undirected graph
3. BFS traversal on undirected graph
4. Determine undirected graph is cyclic or not
*/
public class DFS_and_BFS_and_Cyclic_in_undirected_graph {
    public static void DFS1(ArrayList<ArrayList<Integer>> graph, boolean[] visited,int src){
        //Mark
        if(visited[src]){
            return ;
        }
        visited[src]=true;
        //Work
        System.out.print(src+" ");
        //Visit unvisited neighbours
        for(int nbr:graph.get(src)){
            if(!visited[nbr]){
                DFS1(graph,visited,nbr);
            }
        }
    }
    public static void DFS(ArrayList<ArrayList<Integer>> graph, int vertex){
        System.out.println("DFS Traversal of graph");
        boolean[] visited=new boolean[vertex];
        for(int i=0;i<vertex;i++) {
            if(!visited[i]) {
                DFS1(graph, visited,i);
                System.out.println();
            }
        }
    }
    public static boolean isCyclic2(ArrayList<ArrayList<Integer>> graph,boolean[] visited,int src){
        Queue<Integer> que=new LinkedList<>();
        que.add(src);
        while(que.size()>0){
            //Remove
            int currNode=que.remove();
            //Mark
            if(visited[currNode]){
                return true;
            }
            visited[currNode]=true;
            //Work
            //No work required here
            //Visit unvisited nodes
            for(int nbr:graph.get(currNode)){
                if(!visited[nbr]){
                    que.add(nbr);
                }
            }
        }
        return false;
    }
    public static boolean isCyclic(ArrayList<ArrayList<Integer>> graph,int vertex){
        boolean[] visited=new boolean[vertex];
        //Check for more than one components
        for(int i=0;i<vertex;i++){
            if(!visited[i]){
                boolean iscyclic= isCyclic2(graph,visited,i);
                if(iscyclic){
                    return true;
                }
            }
        }
        return false;
    }
    public static void BFS1(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int src){
        Queue<Integer> que=new LinkedList<>();
        que.add(src);
        int level=0;
        while(que.size()>0){
            ArrayList<Integer> currList=new ArrayList<>();
            int size= que.size();
            while(size-->0) {
                // Remove
                int currNode = que.remove();
                // Mark
                if (visited[currNode]) {
                    continue;
                }
                visited[currNode] = true;
                //Work
                currList.add(currNode);
                //Visit unvisited neighbour
                for (int nbr : graph.get(currNode)) {
                    if (!visited[nbr]) {
                        que.add(nbr);
                    }
                }
            }
            if(currList.size()>0){
                System.out.println("Level:- "+level+" ---> "+currList);
                level++;
            }
        }
    }
    public static void BFS(ArrayList<ArrayList<Integer>> graph,int vertex){
        System.out.println("BFS Traversal of graph");
        boolean[] visited=new boolean[vertex];
        for(int i=0;i<vertex;i++){
            if(!visited[i]) {
                BFS1(graph, visited, i);
            }
        }
    }
    public static void main(String[] args){
        int vertex,no_of_edges;
        System.out.print("Enter the no.of vertex:- ");
        Scanner sc=new Scanner(System.in);
        vertex=sc.nextInt();
        System.out.print("Enter the no.of edges:- ");
        no_of_edges=sc.nextInt();
        int[][] edges=new int[no_of_edges][2];
        System.out.println("Enter all the edges");
        for(int i=0;i<no_of_edges;i++){
            edges[i][0]=sc.nextInt();
            edges[i][1]=sc.nextInt();
        }
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0;i<vertex;i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        System.out.println("Graph:- "+graph);
        DFS(graph,vertex);
        BFS(graph,vertex);
        boolean iscyclic=isCyclic(graph,vertex);
        if(iscyclic)
            System.out.println("It is a cyclic undirected graph");
        else
            System.out.println("It is a uncyclic undirected graph");
    }
}
