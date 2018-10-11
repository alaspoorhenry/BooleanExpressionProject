package parserPack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Tree<E> {

  // private static final String[] SENTENCESYMBOLS = {"A","B"};
  private Node<E> root;
  private Queue<E> sentenceQueue = new LinkedList<E>();
  private Stack<Node<E>> treeStack = new Stack<Node<E>>();

  public Tree(E e) {
    this.root = new Node<E>(e);
    this.root.parent = null;
  }

  public Tree() {}

  public void putIntoSQ(E data) {
    if (!(sentenceQueue.contains(data))) {
      this.sentenceQueue.add(data);
    }
  }

  public Queue<E> returnSentenceQueue() {
    return this.sentenceQueue;
  }

  public void createLeaf(E e) {
    Node<E> ret = new Node<E>(e);
    ret.parent = null;
    treeStack.push(ret);
  }

  public Node<E> popRet() {
    return treeStack.pop();
  }

  public void pushRet(Node<E> n) {
    treeStack.push(n);
  }

  public void markRoot() {
    this.root = treeStack.pop();
  }

  public void buildTreeDefault() {
    this.buildTree(this.root, 0);
  }

  public void buildTree(Node<E> e, int tabNo) {
    if (e != null) {
      String dirName = new String(new char[tabNo]).replace("\0", "\t")
          + e.data.toString() + "\n";
      System.out.println(dirName);
      buildTree(e.left, tabNo + 1);
      buildTree(e.right, tabNo + 1);
    }
  }

  public Node<E> getRoot() {
    return this.root;
  }

  static class Node<E> {
    public E data;
    public Node<E> left = null;
    public Node<E> right = null;
    public Node<E> parent = null;

    // refactor to children
    public Node<E>[] children;

    public Node(E e) {
      this.data = e;
    }

    public Node(E e, Node<E> l, Node<E> r) {
      this.left = l;
      this.right = r;
      this.data = e;
      l.parent = this;
      r.parent = this;
    }

    public Node(E e, Node<E> p) {
      this.parent = p;
      this.left = null;
      this.right = null;
      this.data = e;
    }
  }
}

