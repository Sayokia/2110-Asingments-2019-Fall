import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Huffman {
  //the encode method that will implement the Huffman coding algorithm
  public static void encode()throws IOException {
    // read in the file name
    Scanner kb = new Scanner(System.in);
    System.out.print("Enter the filename to read from/encode:");
    String filename = kb.nextLine();

    File userFile = new File(filename);
    Scanner inputFile = new Scanner(userFile);
    String text = "";

    // put everything in the file into a String

    while(inputFile.hasNext()){
      String temp = inputFile.next();
      text += temp;
    }

    // count the number of occurrences of each non-whitespace
    //character in the text String

    int[] freq = new int[256];
    char[] chars = text.replaceAll("\\s", "").toCharArray();
    for(char c: chars)
      freq[c]++;


    // derive the relative probabilities of the
    //characters and store them in a list
    double[] prob = new double[256];

    for (int i = 0; i <freq.length; i++){
      prob[i] = Math.round(freq[i]*10000d/chars.length)/10000d;
    }


    //create an Arraylist of Pair Objects to keep track of Pair

    ArrayList<Pair> pairList = new ArrayList<>();

    // create the pair object based on the char and its prob
    // add it into the arraylist after created
    for (int j = 0; j<256;j++){
      if (freq[j] != 0){
        Pair pToAdd = new Pair((char)j,prob[j]);
        pairList.add(pToAdd);

      }

    }

    // create the queue s
    ArrayList<BinaryTree<Pair>> s = new ArrayList<BinaryTree<Pair>>();
    //sort the arrayList of pair
    Collections.sort(pairList);
    // turn every pair in the arrayList into BinaryTree and add them into the S queue
    for (int i = 0; i<pairList.size();i++){
      Pair temp = pairList.get(i);
      BinaryTree<Pair> newTree = new BinaryTree<Pair>();
      newTree.makeRoot(temp);
      s.add(newTree);
    }

    // create the queue t

    ArrayList<BinaryTree<Pair>> t = new ArrayList<BinaryTree<Pair>>();

    // Pick the two smallest weight trees, say A and B
    BinaryTree<Pair> a;
    BinaryTree<Pair> b;

    if(t.isEmpty()&&s.size()>=2) {
      a = s.remove(0);
      b = s.remove(0);
      BinaryTree<Pair> p = new BinaryTree<Pair>();
      double probP = a.getData().getProb()+b.getData().getProb();
      Pair newPair = new Pair('⁂', probP);
      p.makeRoot(newPair);
      p.attachLeft(a);
      p.attachRight(b);
      t.add(p);
    }


    // repeat the process util s is empty
    while(!s.isEmpty()) {
      // If T is not empty:
      //    //i) Find the smaller weight tree of the trees in front of S and in front of T.
      //    //This is A. Dequeue it.
      //    //ii) Find the smaller weight tree of the trees in front of S and in front of T.
      //    //This is B. Dequeue it.

      //s front has smaller prob, let s front be a
      if(s.get(0).getData().getProb() < t.get(0).getData().getProb())
        a = s.remove(0);
      //t front has smaller prob, let t front be a
      else if(s.get(0).getData().getProb() > t.get(0).getData().getProb())
        a = t.remove(0);
      //if same prob, let s front be a
      else
        a = s.remove(0);

      if(s.isEmpty())
        b = t.remove(0);

      else if(t.isEmpty())
        b = s.remove(0);

      else{
        //s front has smaller prob, let s front be b
        if(s.get(0).getData().getProb() < t.get(0).getData().getProb())
          b = s.remove(0);
        //t front has smaller prob, let t front be b
        else if(s.get(0).getData().getProb() > t.get(0).getData().getProb())
          b = t.remove(0);
        //if same prob, let s front be b
        else
          b = s.remove(0);
      }

      // Construct a new tree P by creating a root and attaching A and B as the subtrees of this
      //root
      BinaryTree<Pair> p = new BinaryTree<Pair>();
      double probP = a.getData().getProb()+b.getData().getProb();
      Pair newPair = new Pair('⁂', probP);
      p.makeRoot(newPair);
      p.attachLeft(a);
      p.attachRight(b);
      //) Enqueue tree P to queue T
      t.add(p);
    }

    //If the number of elements in queue T is greater than 1, dequeue two nodes at a time,
    //combine them (see strep 2) and enqueue the combined tree until queue T's size is 1. The
    //last node remaining in the queue T will be the final Huffman tree.
    while(t.size()>1) {
      a = t.remove(0);
      b = t.remove(0);
      BinaryTree<Pair> p = new BinaryTree<Pair>();
      double probP = a.getData().getProb()+b.getData().getProb();
      Pair newPair = new Pair('⁂', probP);
      p.makeRoot(newPair);
      p.attachLeft(a);
      p.attachRight(b);
      t.add(p);
    }

    //dequeue huffman tree from queue t
    BinaryTree<Pair> huffmanTree = t.remove(0);

    //create codes which contains codes for all symbols
    String[] codes = findEncoding(huffmanTree);

    //create output file
    PrintWriter output = new PrintWriter("Huffman.txt");

    // print the header of the output
    output.print("Symbol Prob.\t");
    output.print("Huffman\t");
    output.print("Code");
    output.println("\n");


    // use the pair list to print the result
    for (int i = 0; i<pairList.size();i++){
      Pair n = pairList.get(i);
      output.print(n.getValue() +"\t");
      output.print(n.getProb() + "\t");
      output.print(codes[(byte) n.getValue()]);
      output.println();
    }

    output.close();


    File file = new File("Huffman.txt");
    inputFile = new Scanner(file);
    StringTokenizer token;
    char value;
    String code;
    String[] codeList = new String[256];
    String header = inputFile.nextLine();
    header = inputFile.nextLine();
    while(inputFile.hasNextLine()) {
      String line = inputFile.nextLine();
      value = line.charAt(0);
      token = new StringTokenizer(line, "\t");
      token.nextToken();
      token.nextToken();
      code = token.nextToken();
      //the index is the symbol cast to byte
      codeList[(byte)value] = code;
    }

    // open a new output file for encode result
    output = new PrintWriter("Encoded.txt");

    Scanner user = new Scanner(userFile);
    while (user.hasNext()){
      String line = user.nextLine();
      char[] lineChar = line.toCharArray();
      // scape the space character and copy it to the output
      for(char c: lineChar) {
        if(c!=' ')
          output.print(codeList[(byte)c]);
        else
          output.print(' ');
      }
      output.println();
    }
    output.close();
  }

  // code from the assignment instruction
  public static String[] findEncoding(BinaryTree<Pair> t){
    String[] result = new String[256];
    findEncoding(t, result, "");
    return result;
  }

  private static void findEncoding(BinaryTree<Pair> bt, String[] a, String prefix){
    // test is node/tree is a leaf
    if (bt.getLeft()==null && bt.getRight()==null){
      a[bt.getData().getValue()] = prefix;
    }
    // recursive calls
    else{
      findEncoding(bt.getLeft(), a, prefix+"0");
      findEncoding(bt.getRight(), a, prefix+"1");
    }
  }


  //The Huffman class’s decode method will decode text that has been encoded using the Huffman
  //coding algorithm.

  public static void decode()throws IOException{
    // read in the file name
    Scanner kb = new Scanner(System.in);
    System.out.print("Enter the filename to read from/decode: ");
    String filename = kb.nextLine();
    File userFile = new File(filename);

    System.out.print("Enter the filename of document containing Huffman codes: " );
    filename = kb.nextLine();
    File HuffmanFile = new File(filename);


    // create tw arraylist that store char and codes, the item on the same index are in pair
    ArrayList<Character> charList = new ArrayList<>();
    ArrayList<String> codes = new ArrayList<>();



    Scanner ls = new Scanner(HuffmanFile);
    // consume/discard header row and blank line
    ls.nextLine();
    ls.nextLine();
    while(ls.hasNextLine()){
      String line = ls.nextLine();
      char value = line.charAt(0);
      StringTokenizer token = new StringTokenizer(line, "\t");
      token.nextToken();
      token.nextToken();
      String code = token.nextToken();
      charList.add(value);
      codes.add(code);
    }

    // open a new output file for encode result
    PrintWriter output = new PrintWriter("Decoded.txt");

    // a string to combine 0 and 1 into codes
    String sum = "";
    Scanner user = new Scanner(userFile);
    while(user.hasNextLine()) {
      // go through each line char by char
      String line = user.nextLine();
      char[] chars = line.toCharArray();
      for(char c: chars) {
        // if the char is 1 or 0 add it to the sum String
        if(c =='1' || c == '0'){
          sum += c;
          // compare the sum to the hufman list to find its pair char
          for (int i =0; i<codes.size(); i++){
            if (sum.equals(codes.get(i))){
              output.print(charList.get(i));
              sum = "";
            }
          }
        }
        // if it is space copy it
        else {
          output.print(' ');
        }




      }
      output.print('\n');
    }



    output.close();




  }

}
