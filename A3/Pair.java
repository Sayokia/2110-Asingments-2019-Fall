public class Pair implements Comparable<Pair> {
  private char value;
  private double prob;

  //constructor
  public Pair(char value, double prob) {
    this.value = value;
    this.prob = prob;
  }

  //get methods
  public char getValue() {
    return value;
  }

  public double getProb() {
    return prob;
  }

  //set methods
  public void setValue(char value) {
    this.value = value;
  }

  public void setProb(double prob) {
    this.prob = prob;
  }

  //toString
  public String toString() {
    return value + "\t" + Double.toString(prob);
  }

  /**
   The compareTo method overrides the compareTo method of the
   Comparable interface.
   */
  @Override
  public int compareTo(Pair p){
    return Double.compare(this.getProb(), p.getProb());
  }

}
