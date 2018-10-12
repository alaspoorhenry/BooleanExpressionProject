package parserPack;

import parserPack.CreateParseTree;
import parserPack.Tree.Node;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class EvaluateGivenParseTree {

  private HashMap<String, Boolean> taMap = new HashMap<String, Boolean>();
  private Queue<String> sentenceQueue = new LinkedList<String>();
  private Tree<String> parseTree = new Tree<String>();
  private Queue<Boolean[]> truthAssignmentPlr = new LinkedList<Boolean[]>();
  private static final Set<String> SYMBOLSET = new HashSet<String>();

  public EvaluateGivenParseTree(Queue<String> sS, Tree<String> treeInput) {
    this.sentenceQueue = sS;
    this.parseTree = treeInput;
    EvaluateGivenParseTree.SYMBOLSET.add("~");
    EvaluateGivenParseTree.SYMBOLSET.add("&");
    EvaluateGivenParseTree.SYMBOLSET.add("|");
    EvaluateGivenParseTree.SYMBOLSET.add("=");
    EvaluateGivenParseTree.SYMBOLSET.add("<");
    EvaluateGivenParseTree.SYMBOLSET.add(">");
    this.generateTableDefault();
  }

  public void evaluateWithCurrentTA() {
    for (Boolean[] a : this.truthAssignmentPlr) {
      generateTAFromIndex(a);
      if (!(evaluateGivenTA(this.parseTree.getRoot()))) {
        System.out.println("This boolean expression is not a tautology.");
        return;
      }
    }
    System.out.println("This boolean Expression is a tautology.");
  }

  private boolean evaluateGivenTA(Node<String> node) {
    if (!(SYMBOLSET.contains(node.data))) {
      return (this.taMap.get(node.data));
    } else if (node.data.equals("~")) {
      return (!(evaluateGivenTA(node.left)));
    } else if (node.data.equals("&")) {
      return ((evaluateGivenTA(node.left)) && (evaluateGivenTA(node.right)));
    } else if (node.data.equals("|")) {
      return ((evaluateGivenTA(node.left)) || (evaluateGivenTA(node.right)));
    } else if (node.data.equals(">")){
      return ((!evaluateGivenTA(node.left))|(evaluateGivenTA(node.right)));
    } else if (node.data.equals("<")){
      return ((!evaluateGivenTA(node.right))|(evaluateGivenTA(node.left)));
    } else if (node.data.equals("=")){
      return (evaluateGivenTA(node.left) && (evaluateGivenTA(node.right))) || (!evaluateGivenTA(node.left) && (!evaluateGivenTA(node.right))); 
    }
    return false;
  }

  // falsifies every variable
  private void generateTAFromIndex(Boolean[] tA) {
    taMap = new HashMap<String, Boolean>();
    int i = 0;
    for (String a : this.sentenceQueue) {
      taMap.put(a, tA[i]);
      i++;
    }
  }

  private void generateTableDefault() {
    this.generateTable(0, sentenceQueue.size(), new int[sentenceQueue.size()]);
  }

  private void generateTable(int index, int size, int[] current) {
    if (index == size) { // generated a full "solution"
      Boolean[] truthAssignment = new Boolean[size];
      for (int i = 0; i < size; i++) {
        if (current[i] == 0) {
          truthAssignment[i] = false;
        } else {
          truthAssignment[i] = true;
        }
        // System.out.print(current[i] + " ");
      }
      this.truthAssignmentPlr.add(truthAssignment);
      // System.out.println();
    } else {
      for (int i = 0; i < 2; i++) {
        current[index] = i;
        generateTable(index + 1, size, current);
      }
    }
  }

//  public static void main(String[] args) {
//    Deque<String> strTest = new LinkedList<String>();
//    strTest.add("A");
//    strTest.add("~");
//    strTest.add("A");
//    strTest.add("|");
//    strTest.add("C");
//    strTest.add("&");
//    Tree<String> newTr = new Tree<String>();
//    CreateParseTree newTree = new CreateParseTree(strTest);
//    newTr.buildTree(newTree.createTree().getRoot(), 0);
//    System.out.println(newTree.createTree().returnSentenceQueue());
//    EvaluateGivenParseTree egpt = new EvaluateGivenParseTree(
//        newTree.createTree().returnSentenceQueue(), newTree.createTree());
//    egpt.evaluateWithCurrentTA();
//  }
}
