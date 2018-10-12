package parserPack;

import parserPack.Tree;
import parserPack.Tree.Node;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class CreateParseTree {
  private Deque<String> postfixExp = new LinkedList<String>();
  private static final Set<String> BINARYCONNECTIVES = new HashSet<String>();

  public CreateParseTree(Deque<String> pFE) {
    this.postfixExp = pFE;
    BINARYCONNECTIVES.add("|");
    BINARYCONNECTIVES.add("&");
    BINARYCONNECTIVES.add("<");
    BINARYCONNECTIVES.add(">");
    BINARYCONNECTIVES.add("=");
  }

  public Tree<String> createTree() {
    Tree<String> retTree = new Tree<String>();
    Node<String> temp;
    Node<String> tempOne;
    Node<String> tempTwo;
    for (String a : postfixExp) {
      if (BINARYCONNECTIVES.contains(a)) {
        retTree.createLeaf(a);
        temp = retTree.popRet();
        tempOne = retTree.popRet();
        tempTwo = retTree.popRet();
        tempOne.parent = temp;
        tempTwo.parent = temp;
        temp.right = tempOne;
        temp.left = tempTwo;
        retTree.pushRet(temp);
      } else if (a.equals("~")) {
        retTree.createLeaf(a);
        temp = retTree.popRet();
        tempOne = retTree.popRet();
        tempOne.parent = temp;
        temp.left = tempOne;
        retTree.pushRet(temp);
      } else {
        retTree.createLeaf(a);
        retTree.putIntoSQ(a);
      }
    }
    retTree.markRoot();
    return retTree;
  }

//  public static void main(String[] args) {
//    Deque<String> strTest = new LinkedList<String>();
//    strTest.add("A");
//    strTest.add("~");
//    strTest.add("B");
//    strTest.add("|");
//    strTest.add("C");
//    strTest.add("&");
//    Tree<String> newTr = new Tree<String>();
//    CreateParseTree newTree = new CreateParseTree(strTest);
//    newTr.buildTree(newTree.createTree().getRoot(), 0);
//  }
}
