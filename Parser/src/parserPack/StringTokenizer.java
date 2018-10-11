package parserPack;

import java.util.LinkedList;
import java.util.Queue;

public class StringTokenizer {
  private String strToToken;

  /**
   * Just delimit with spaces for now
   * 
   * @param t
   */
  public StringTokenizer(String t) {
    this.strToToken = t;
    this.strToToken = this.strToToken.replaceAll(" ", "");
  }

  public Queue<String> toQueue() throws NullPointerException { // throw random
                                                               // exception for
                                                               // now
//    String replacedStr = strToToken.replaceAll("->", "-");
//    System.out.println(replacedStr);
    String[] charArray = this.strToToken.split("");
    Queue<String> ret = new LinkedList<String>();
    int invFlag = 0;
    for (String a : charArray) {
      switch (a) {
        case "|":
          if (invFlag > 0) {
            invFlag--;
          }
          ret.add(a);
          break;
        case "~":
          if (invFlag > 0) {
            invFlag--;
          }
          ret.add(a);
          break;
        case "&":
          if (invFlag > 0) {
            invFlag--;
          }
          ret.add(a);
          break;
        case "(":
          if (invFlag > 0) {
            invFlag--;
          }
          ret.add(a);
          break;
        case ")":
          if (invFlag > 0) {
            invFlag--;
          }
          ret.add(a);
          break;
        default:
          invFlag++;
          ret.add(a);
          if (invFlag >= 2) {
            throw new NullPointerException();
          } ;
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    String str = "n ( ( ( ) ) ) )";
    StringTokenizer sT = new StringTokenizer(str);
    System.out.println(sT.toQueue().toString());
  }
}
