package parserPack;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Set;

public class InfToPost {
  private Deque<String> infTokeQueue = new LinkedList<String>();
  private HashMap<String, Integer> precedenceMap =
      new HashMap<String, Integer>();
  private static final Set<String> SYMBOLSET = new HashSet<String>();

  public InfToPost(Deque<String> tokenQueue) {
    this.infTokeQueue = tokenQueue;
    this.setPrecedenceMap();
    SYMBOLSET.add("~");
    SYMBOLSET.add("&");
    SYMBOLSET.add("|");
  }

  private void setPrecedenceMap() {
    precedenceMap.put("~", 1);
    precedenceMap.put("&", 2);
    precedenceMap.put("|", 3);
  }

  public Deque<String> shuntingYardAlgo() {
    // currently no precedence for operators as all inputs are assumed to have
    // brackets in formal notation
    // no error checking here right now
    Deque<String> ret = new LinkedList<String>();
    Stack<String> operatorStack = new Stack<String>();
    for (String a : infTokeQueue) {
      if (a.equals("(")) {
        operatorStack.push(a);
      } else if (a.equals(")")) {
        while (!(operatorStack.peek().equals("("))) {
          ret.add(operatorStack.pop());
        }
        operatorStack.pop();
      } else if ((a.equals("&")) || (a.equals("|") || (a.equals("~")))) {
        operatorStack.add(a);
      } else {
        ret.add(a);
      }
    }
    return ret;
  }

  public static void main(String[] args) {
    Deque<String> infToPostTest = new LinkedList<String>();
    infToPostTest.add("(");
    infToPostTest.add("(");
    infToPostTest.add("(");
    infToPostTest.add("~");
    infToPostTest.add("A");
    infToPostTest.add(")");
    infToPostTest.add("|");
    infToPostTest.add("B");
    infToPostTest.add(")");
    infToPostTest.add("&");
    infToPostTest.add("C");
    infToPostTest.add(")");
    // System.out.println(infToPostTest.toString());
    System.out.println(infToPostTest);
    InfToPost n = new InfToPost(infToPostTest);
    System.out.println(n.shuntingYardAlgo());
  }
}
