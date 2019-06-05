package parserPack;

import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;
import java.lang.Exception;

public class ParserDriver {
  private static Scanner reader;
  public static void main(String[] args) {
    System.out.println(
        "This program only accepts boolean expressions with the following connectives: { ~ , | , & , -> , <- , <-> }");
    System.out.println("Enter \"Exit\" to terminate the program.");
    while(true) {
      try {
      reader = new Scanner(System.in);
      System.out.println("Enter a boolean expression in formal notation: ");
      String userIn = reader.nextLine();
      if (userIn.equals("exit") || userIn.equals("Exit")) {
        break;
      }
      System.out.println();
      StringTokenizer stringT = new StringTokenizer(userIn);
      Queue<String> infixNotBool = stringT.toQueue();
      InfToPost infToPost = new InfToPost((Deque<String>) infixNotBool);
      Deque<String> postFixBool = infToPost.shuntingYardAlgo();
      CreateParseTree parseTree = new CreateParseTree(postFixBool);
      Tree<String> newTree = parseTree.createTree();
      EvaluateGivenParseTree evParseTree =
          new EvaluateGivenParseTree(newTree.returnSentenceQueue(), newTree);
      evParseTree.evaluateWithCurrentTA();
      } catch (Exception e) {
        System.out.println("Error please retry");
        System.out.println(
            "This program only accepts boolean expressions with the following connectives: { ~ , | , & , -> , <- , <-> }");
        System.out.println("Enter \"Exit\" to terminate the program.");
      }
    }
    reader.close();
  }
}
