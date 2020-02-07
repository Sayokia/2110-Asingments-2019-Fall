import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Assignment5 {
  public static void main(String[] args) throws IOException{
    //read in the file
    System.out.println("Enter the filename to read from: ");
    Scanner kb = new Scanner(System.in);
    String filename = kb.next();
    File file = new File(filename);
    Scanner inputFile = new Scanner(file);

    // read in the size
    int n = Integer.parseInt(inputFile.nextLine());
    //create the adjacency matrix
    int[][] adj = new int[n][n];

    // fill the matrix
    while(inputFile.hasNext()){
      // code reused from the instruction
      int v0 = inputFile.next().charAt(0)-65;
      int v1 = inputFile.next().charAt(0)-65;
      adj[v0][v1] = adj[v1][v0] = 1;
    }
    ArrayList<String> header = new ArrayList<>();
    header.add("A");
    header.add("B");
    header.add("C");
    header.add("D");
    header.add("E");
    //display Matrix
    System.out.println("Matrix" );
    System.out.println();
    System.out.println("  A B C D E");
    for(int i=0; i<n; i++) {
      System.out.print(header.get(i) + " ");
      for (int j=0; j<n; j++)
        System.out.print(adj[i][j] + " ");
      System.out.println();
    }

    // display the result of BFS
    ArrayList<Integer> resultBFS = BFS(adj, 0);
    System.out.println();
    System.out.println("Vertices Visited: \n");
    for(int i: resultBFS) {
      System.out.println((char)(i+65) + " visited");
    }
    System.out.println();

  }

  // the BFS traverse
  public static ArrayList<Integer> BFS(int[][] adj, int vertax){
    //Initialize an empty queue and a result list
    ArrayList<Integer> q = new ArrayList<>();
    ArrayList<Integer> result = new ArrayList<>();
    //Enqueue the first vertex
    q.add(vertax);
    //While the queue is not empty
    // Dequeue and list the vertex v in the result list
    // Enqueue each neighbor of v (if it is not already in the result list or not already in the queue)
    //End While
    while(!q.isEmpty()){
      int v = q.remove(0);
      result.add(v);
      for(int i=0; i<adj[v].length; i++) {
        // check if this one is neighbour
        if (adj[v][i] == 1){
          // check if v exsits in queue or result
          if (!q.contains(i) && !result.contains(i)){
            q.add(i);
          }

      }
      }

    }

    return  result;
  }
}
