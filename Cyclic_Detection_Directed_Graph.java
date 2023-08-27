import java.util.ArrayList;
import java.util.Scanner;
public class Cyclic_Detection_Directed_Graph {
    public static boolean isDirectedGraphCyclic2(ArrayList<ArrayList<Integer>> graph, boolean[] path, boolean[] visited, int src){
        //Mark
        if(path[src]){
            return true;
        }
        if(visited[src]){
            return false;
        }
        path[src]=true;
        visited[src]=true;
        //Work
        //Nothing to work
        //Visit unvisited neighbours
        for(int nbr:graph.get(src)){
            if(path[nbr]){
                return true;
            }
            if(!visited[nbr]){
                boolean isCyclic2 = isDirectedGraphCyclic2(graph,path,visited,nbr);
                if(isCyclic2){
                    return true;
                }
            }
        }
        //Unmarked
        path[src]=false;
        return false;
    }
    public static boolean isDirectedGraphCyclic(ArrayList<ArrayList<Integer>> graph,int vertex){
        boolean[] path = new boolean[vertex];
        boolean[] visited = new boolean[vertex];
        for(int i=0;i<vertex;i++){
            if(!visited[i]) {
                boolean isCyclic = isDirectedGraphCyclic2(graph, path, visited, i);
                if(isCyclic){
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number of vertex:- ");
        int vertex=sc.nextInt();
        System.out.print("Enter the number of edges:- ");
        int noOfEdges=sc.nextInt();
        int[][] edges=new int[noOfEdges][2];
        System.out.println("Enter the edges connection");
        for(int i=0;i<noOfEdges;i++){
            edges[i][0]=sc.nextInt();
            edges[i][1]=sc.nextInt();
        }
        ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
        for(int i=0;i<vertex;i++){
            graph.add(new ArrayList<>());
        }
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            //Creating a directed graph
            graph.get(u).add(v);
        }
        System.out.println("Graph:- "+graph);
        boolean isCyclic=isDirectedGraphCyclic(graph,vertex);
        if(isCyclic)
            System.out.println("It is Cyclic Directed Graph");
        else
            System.out.println("It is Acyclic Directed Graph");
    }
}
