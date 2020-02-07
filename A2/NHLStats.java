import java.util.ArrayList;
import java.util.Collections;

public class NHLStats {
  private List<PlayerRecord> list;

  // constructor
  public NHLStats(){
    list = new List<PlayerRecord>();
  }

  // some code reused from previous lab
  public boolean isEmpty()
  {
    return list.isEmpty();
  }
  public boolean contains(PlayerRecord record)
  {
    return list.contains(record);
  }
  public PlayerRecord first()
  {
    return list.first();
  }
  public PlayerRecord next()
  {
    return list.next();
  }
  public void enumerate()
  {
    list.enumerate();
  }
  public void add(PlayerRecord n){
    list.add(n);
  }

  //Display the name and team name for the player with the most points (Goals+Assists)
  // The first while loop is used to find the max and second one is used to display the name of the player
  public void findMostPoint(){
    int points = 0;
    int goal,assist;
    PlayerRecord pr = list.first();
    while(pr!=null){
      goal = pr.getScore();
      assist = pr.getAssists();
      if(goal+assist > points){
        points = goal+assist;
      }
      pr = list.next();
    }
    PlayerRecord pr2 = list.first();
    while (pr2!=null){
      goal = pr2.getScore();
      assist = pr2.getAssists();
      if(goal+assist == points){
        System.out.println(pr2.getPlayerName() + " " + pr2.getTeamName());
      }
      pr2 = list.next();
    }

  }

  //Display the name, team name, and position for the player who was the most aggressive
  // The first while loop is used to find the max and second one is used to display the name of the player
  public void findMostAggressive(){
    int max = 0;
    int penalty;
    PlayerRecord pr = list.first();
    while (pr!=null){
      penalty = pr.getPenaltyTime();
      if(penalty>=max){
        max = penalty;
      }
      pr = list.next();
    }
    PlayerRecord pr2 = list.first();
    while (pr2 !=null){
      penalty = pr2.getPenaltyTime();
      if(penalty == max){
        System.out.println(pr2.getPlayerName() + " " + pr2.getTeamName() + " " + pr2.getPosition());
      }
      pr2 = list.next();
    }
  }

  //Display the name and team name for the player who was the MVP
  // The first while loop is used to find the max and second one is used to display the name of the player
  public void findMVP(){
    int max = 0;
    int winning;
    PlayerRecord pr = list.first();
    while (pr!=null){
      winning = pr.getWinningGoal();
      if(winning>=max){
        max = winning;
      }
      pr = list.next();
    }
    PlayerRecord pr2 = list.first();
    while (pr2 !=null){
      winning = pr2.getWinningGoal();
      if(winning == max){
        System.out.println(pr2.getPlayerName() + " " + pr2.getTeamName());
      }
      pr2 = list.next();
    }
  }

  //Display the name and team name for the most promising player (had the most shots on goal)
  // The first while loop is used to find the max and second one is used to display the name of the player
  public void findMostPromising(){
    int max = 0;
    int shots;
    PlayerRecord pr = list.first();
    while (pr!=null){
      shots = pr.getShots();
      if(shots>=max){
        max = shots;
      }
      pr = list.next();
    }
    PlayerRecord pr2 = list.first();
    while (pr2 !=null){
      shots = pr2.getShots();
      if(shots == max){
        System.out.println(pr2.getPlayerName() + " " + pr2.getTeamName());
      }
      pr2 = list.next();
    }
  }

  //Display the team name for the team that had the most penalty minutes
  //the first loop create a array list with all team's name , the second loop calculate and store the total penalty time in another
  // array list but in the same index
  public void findMostAggressiveTeam(){
    ArrayList<String> names = new ArrayList<>();
    PlayerRecord pr = list.first();
    while (pr!= null){
      if(!names.contains(pr.getTeamName())){
        names.add(pr.getTeamName());
      }
      pr = list.next();
    }
    ArrayList<Integer> n = new ArrayList<>(Collections.nCopies(names.size(), 0));
    PlayerRecord pr2 = list.first();
    while(pr2!=null){
      for (int i =0; i<names.size();i++){
        if(pr2.getTeamName().equals(names.get(i))){
          n.set(i, n.get(i)+pr2.getPenaltyTime());
        }
      }
      pr2 = list.next();
    }
    int max = 0;
    for(int i=0; i<n.size(); i++){
      if(n.get(i)>max){
        max = n.get(i);
      }
    }
    for(int j=0;j<n.size();j++){
      if(n.get(j)==max){
        System.out.println(names.get(j));
      }
    }
  }

  //Display the team name for the team that had the most game winning goals
  //the first loop create a array list with all team's name , the second loop calculate and store the total  winning goal in another
  // array list but in the same index
  public void findMVPTeam(){
    ArrayList<String> names = new ArrayList<>();
    PlayerRecord pr = list.first();
    while (pr!= null){
      if(!names.contains(pr.getTeamName())){
        names.add(pr.getTeamName());
      }
      pr = list.next();
    }
    ArrayList<Integer> n = new ArrayList<>(Collections.nCopies(names.size(), 0));
    PlayerRecord pr2 = list.first();
    while(pr2!=null){
      for (int i =0; i<names.size();i++){
        if(pr2.getTeamName().equals(names.get(i))){
          n.set(i, n.get(i)+pr2.getWinningGoal());
        }
      }
      pr2 = list.next();
    }
    int max = 0;
    for(int i=0; i<n.size(); i++){
      if(n.get(i)>max){
        max = n.get(i);
      }
    }
    for(int j=0;j<n.size();j++){
      if(n.get(j)==max){
        System.out.println(names.get(j));
      }
    }
  }
}
