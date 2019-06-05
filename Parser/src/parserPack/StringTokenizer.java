package parserPack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class StringTokenizer {
  private String strToToken;
  private static final Set<String> SYMBOLSET = new HashSet<String>();
  
  /**
   * Just delimit with spaces for now
   * 
   * @param t
   */
  public StringTokenizer(String t) {
    this.strToToken = t;
    this.strToToken = this.strToToken.replaceAll(" ", "");
    StringTokenizer.SYMBOLSET.add("~");
    StringTokenizer.SYMBOLSET.add("&");
    StringTokenizer.SYMBOLSET.add("|");
    StringTokenizer.SYMBOLSET.add(")");
    StringTokenizer.SYMBOLSET.add("(");
    StringTokenizer.SYMBOLSET.add("=");
    StringTokenizer.SYMBOLSET.add("<");
    StringTokenizer.SYMBOLSET.add(">");
  }

  public Queue<String> toQueue() throws NullPointerException { // throw random
                                                               // exception for
                                                               // now
    String replacedStr = this.strToToken.replaceAll("<->","=");
    replacedStr = replacedStr.replaceAll("->", ">");
    replacedStr = replacedStr.replaceAll("<-", "<");
    String[] charArray = replacedStr.split("");
    Queue<String> ret = new LinkedList<String>();
    int invFlag = 0;
    for (String a : charArray) {
      if (SYMBOLSET.contains(a)) {
        if (invFlag > 0) {
          invFlag--;
        }
        ret.add(a);
      } else {
        invFlag++;
        ret.add(a);
        if (invFlag >= 2) {
          throw new NullPointerException();
        }
      }
    }
    return ret;
  }
}
