import java.util.Scanner;
import java.io.*;
public class OrderedListDemo {
  public static void main(String[] args) throws IOException{
    // read in the two files
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Enter the first filename to read from: ");
    String filename1 = keyboard.nextLine();
    System.out.print("Enter the second filename to read from: ");
    String filename2 = keyboard.nextLine();
    File file1 = new File(filename1);
    File file2 = new File(filename2);
    Scanner inputFile1 = new Scanner(file1);
    Scanner inputfile2 = new Scanner(file2);
    OrderedList<String> list1 = new OrderedList<>();
    OrderedList<String> list2 = new OrderedList<>();
    while (inputFile1.hasNext()){
      String line1 = inputFile1.nextLine();
      list1.insert(line1);
    }
    while (inputfile2.hasNext()){
      String line2 = inputfile2.nextLine();
      list2.insert(line2);
    }
    // print the message and list
    System.out.println("The Ordered Lists contain the following entries:");
    System.out.println("List 1:");
    list1.enumerate();
    System.out.println("List 2：");
    list2.enumerate();
    System.out.println();
    System.out.println("A merged version of the two lists looks like this:");
    // merge two lists and print out the result
    merge(list1, list2).enumerate();
  }
  // The method refers to the two finger walking algorithm taught in lecture
  public static <T extends Comparable<T>> OrderedList<T> merge(OrderedList<T> list1,
                                                               OrderedList<T> list2){
    OrderedList<T> result = new OrderedList<>();
    // compare the first one of the two lists, add the smaller one into the result and delete it from its original list
    // until one of the two lists runs out
    while (list1.size() > 0 && list2.size() > 0) {
      if (list1.get(0).compareTo(list2.get(0)) < 0) {
        result.add(list1.get(0));
        list1.remove(0);
      }
      else if (list2.get(0).compareTo(list1.get(0)) <0){
        result.add(list2.get(0));
        list2.remove(0);
      }
      // the situation when the first one of the two lists are same
      else {
        result.add(list1.get(0));
        list1.remove(0);
        list2.remove(0);
      }
    }

    // add the rest of the remaining list at the end of the ordered list
    if (list1.size() > 0) {
      for (int i=0; i<list1.size();i++){
        result.add(list1.get(i));
      }
    }
    else if (list2.size() > 0) {
      for (int i=0; i<list2.size();i++){
        result.add(list2.get(i));
      }
    }


    return result;
  }
}
