import java.util.*;

public class Exercise0 {
  public static void main(String[] args){
    // read in the input and create the table
    Scanner kb = new Scanner(System.in);
    System.out.println("Please enter the table size: ");
    int t = kb.nextInt();
    ArrayList<LinkedList<Integer>> table = new ArrayList<LinkedList<Integer>>(t);
    for (int i = 0; i<t;i++){
      LinkedList<Integer> s = new LinkedList<>();
      table.add(i,s );
    }
    System.out.println("Please enter the number of keys you want to generate: ");
    int n = kb.nextInt();

    // generate random keys and store them into the arraylist
    ArrayList<Integer> list = new ArrayList<>();
    Random r = new Random();
    while (list.size() != n){
      int num = r.nextInt(9999) +1;
      if (!list.contains(num)){
        list.add(num);
      }
    }


    // sort the arraylist
    Collections. sort(list);

    // store the number in the arrayList into the table by its referenced index
    for (int i = 0; i<list.size();i++){
      int pos =list.get(i)%t;
      table.get(pos).add(list.get(i));
    }
    //count for the total collision
    int count = 0;
    // record the length of each linkedList
    int length = 0;
    // the max of length of Linkedlists
    int max = 0;
    System.out.println();
    // print out the reslut of the hash table
    System.out.println("Hash Table created:");
    for (LinkedList<Integer> l : table){
      if (l.isEmpty()){
        System.out.println("--> empty");
      }
      else {
        for (int i=0; i<l.size(); i++){
          length++;
          System.out.print("--> " + l.get(i));
        }
        System.out.println();
        count += length-1;
        if (length>max){
          max = length;
        }
        length = 0;
      }
    }

    // print out the result
    System.out.println();
    System.out.println("Statistics: ");
    System.out.println("Table size: " + t);
    System.out.println("Number of keys: " + n);
    System.out.println("Load factor: " + (double)n/t);
    System.out.println("Number of collisions: "+ count );
    System.out.println("Longest list: " + max);




  }


}
