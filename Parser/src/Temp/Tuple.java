package Temp;

public class Tuple<X, Y> { 
  public X x; 
  public Y y;
  public void setX(X x) {
    this.x = x;
  }
  public void setY(Y y) {
    this.y = y;
  }
  public Tuple(X x, Y y) { 
    this.x = x; 
    this.y = y; 
  } 
} 