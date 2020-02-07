public class PlayerRecord {
  // set up instance variables
  private String playerName;
  private String position;
  private String teamName;
  private int games;
  private int score;
  private int assists;
  private int penaltyTime;
  private int shots;
  private int winningGoal;

  // constructor
  public PlayerRecord() {
  }

  public PlayerRecord(String playerName, String position, String teamName, int games, int score, int assists, int penaltyTime, int shots, int winningGoal) {
    this.playerName = playerName;
    this.position = position;
    this.teamName = teamName;
    this.games = games;
    this.score = score;
    this.assists = assists;
    this.penaltyTime = penaltyTime;
    this.shots = shots;
    this.winningGoal = winningGoal;
  }
  // getter and setter methods
  public String getPlayerName() {
    return playerName;
  }

  public String getPosition() {
    return position;
  }

  public String getTeamName() {
    return teamName;
  }

  public int getGames() {
    return games;
  }

  public int getScore() {
    return score;
  }

  public int getAssists() {
    return assists;
  }

  public int getPenaltyTime() {
    return penaltyTime;
  }

  public int getShots() {
    return shots;
  }

  public int getWinningGoal() {
    return winningGoal;
  }

  public void setPlayerName(String playerName) {
    playerName = playerName;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public void setGames(int games) {
    this.games = games;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setAssists(int assists) {
    this.assists = assists;
  }

  public void setPenaltyTime(int penaltyTime) {
    this.penaltyTime = penaltyTime;
  }

  public void setShots(int shots) {
    this.shots = shots;
  }

  public void setWinningGoal(int winningGoal) {
    this.winningGoal = winningGoal;
  }

  public String toString() {
    return playerName + " " + position + " " + teamName + " " + games + " " + score + " " + assists + " " + penaltyTime + " " + shots + " " + winningGoal;
  }
}
