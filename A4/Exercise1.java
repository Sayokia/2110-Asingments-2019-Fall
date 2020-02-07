import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Exercise1 {

  public static void main(String[] args) throws IOException {
    //create two empty HashMap
    //one with username as key and the password as value
    // the other with the username as key and full name as value
    HashMap<String, String> pass = new HashMap<String, String>();
    HashMap<String, String> name = new HashMap<String, String>();

    // ask user's input to read in file
    System.out.println("Enter the file name: ");
    Scanner kb = new Scanner(System.in);
    String filename = kb.nextLine();
    File file = new File(filename);
    Scanner inputFile = new Scanner(file);
    String line, fullname, username, password;
    // read the file line by line and insert the information into the hashmap
    while (inputFile.hasNext()) {
      line = inputFile.nextLine();
      StringTokenizer tokenizer = new StringTokenizer(line, "\t");
      fullname = tokenizer.nextToken();
      username = tokenizer.nextToken();
      password = tokenizer.nextToken();
      pass.put(username, password);
      name.put(username, fullname);
    }
    inputFile.close();

    //prompt user to enter login name and password
    //count number of tries.  After 3 unsuccessful login attempts, the program exit
    int count = 1;
    boolean correct = false;
    // a while loop to accept user's input and compare it with value stored in the hashmap, until they matches or attampts runs out
    while (count < 4 && !correct) {
      System.out.println("Login: ");
      username = kb.nextLine();
      System.out.println("Password: ");
      password = kb.nextLine();

      // the situation that the password entered matches the user's paasword in the hashmap
      if (password.equals(pass.get(username))) {
        correct = true;
        System.out.println("Login successful");
        System.out.println("Welcome " + name.get(username));
      } else {
        // when user still have chances to try
        if (count < 3){
          System.out.println("Either the username or password is incorrect. You have " + (3-count) + " more attempts.");
        }
        else{
          System.out.println("Sorry. Incorrect login. Please contact the system administrator.");
        }
        count++;
      }

    }
    kb.close();
  }
}
