import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

public class NHLListDemo {
  public static void main(String[] args) throws IOException{
    //some code reused from previous lab
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Enter the filename to read from: ");
    String filename = keyboard.nextLine();

    File file = new File(filename);
    Scanner inputFile = new Scanner(file);

    // create objects to store input
    NHLStats list = new NHLStats();
    String playerName, position, teamName;
    int games,score,assists,penaltyTime,shots,winningGoal;
    PlayerRecord pr = null;
    // read in input and store the records into the list
    while (inputFile.hasNext()){
      String line = inputFile.nextLine();
      StringTokenizer token = new StringTokenizer(line, "\t");

      playerName = token.nextToken();
      position = token.nextToken();
      teamName = token.nextToken();
      games = Integer.parseInt(token.nextToken());
      score = Integer.parseInt(token.nextToken());
      assists = Integer.parseInt(token.nextToken());
      penaltyTime = Integer.parseInt(token.nextToken());
      shots = Integer.parseInt(token.nextToken());
      winningGoal = Integer.parseInt(token.nextToken());
      pr = new PlayerRecord(playerName,position,teamName,games,score,assists,penaltyTime,shots,winningGoal);
      list.add(pr);
    }

    // print out required message
    System.out.println("NHL Results Summary");
    System.out.println();
    System.out.println("Players with highest points and their teams:");
    list.findMostPoint();
    System.out.println();
    System.out.println("Most aggressive players, their teams and their positions:");
    list.findMostAggressive();
    System.out.println();
    System.out.println("Most valuable players and their teams:");
    list.findMVP();
    System.out.println();
    System.out.println("Most promising players and their teams:");
    list.findMostPromising();
    System.out.println();
    System.out.println("Teams that had the most number of penalty minutes:");
    list.findMostAggressiveTeam();
    System.out.println();
    System.out.println("Teams that had the most number of game winning goals:");
    list.findMVPTeam();
  }
}
