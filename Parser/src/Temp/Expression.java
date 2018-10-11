package Temp;
//package parserPack;
//import java.util.Stack;
////import java.util.HashMap;
////import java.util.Map;
//
//public class Expression {
//  private String expHere;
//  private Stack<Integer> charStack = new Stack<Integer>();
//  private int rootInd;
//  
//  public Expression(String exp) {
//    this.expHere = exp;
//    this.rootInd = findRootOperator();
//  }
//  
//  private int findRootOperator() {
//    int ret = 0;
//    int pCount = 0;
//    for (int i = 0; i < this.expHere.length(); i++){
//      char c = this.expHere.charAt(i);        
//      if (i == this.expHere.length()-1) { // should also have a last ) element
//        ret = charStack.pop();
//        pCount = 0;
//      }
//      else if (c == '(') {
//        pCount += 1;
//        }
//      else if (c == ')') {
//        pCount -= 1;
//        charStack.pop(); //if not throw exception
//      }
//      else if ((c == 'o')||(c == 'a')||(c == 'b')) {
//        charStack.push(i);
//      }
//      else {
//        System.out.println("Throw exception here");
//      }
//    }
//    return ret;
//  }
//  
//  public static Tree buildExpTree(String expGiven) {
//    Expression exp = new Expression(expGiven);
//    Tree newT = new Tree<Character>(exp.expHere.charAt(exp.rootInd));
//    String newStr = exp.expHere.substring(1, exp.expHere.length());
//    String leftForm = newStr.substring(0, exp.rootInd);
//    String rightForm =  newStr.substring(exp.rootInd+1);
//    return null;
//  }
//  
//  public static void main(String[] args) {
////    Expression exp = new Expression("((A a (A a B)) o (A b B))");
////    System.out.println(exp.findRootOperator());
//  }
//}
