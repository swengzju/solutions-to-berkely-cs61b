/* DList.java */

 

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *  size is the number of items in the list.  (The sentinel node does not
   *       store an item.)
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected int size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

  /**
   *  newNode() calls the DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected DListNode newNode(Object item, DListNode prev, DListNode next) {
    return new DListNode(item, prev, next);
  }

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
    //  Your solution here.
    head = newNode(Integer.MIN_VALUE, null, null); //Interger.MIN_VALUE could be null.
    //head.item = Integer.MIN_VALUE;
    head.next = head;
    head.prev = head;
    size = 0;
  }

  /**
   *  isEmpty() returns true if this DList is empty, false otherwise.
   *  @return true if this DList is empty, false otherwise. 
   *  Performance:  runs in O(1) time.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  length() returns the length of this DList. 
   *  @return the length of this DList.
   *  Performance:  runs in O(1) time.
   */
  public int length() {
    return size;
  }

  /**
   *  insertFront() inserts an item at the front of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertFront(Object item) {
    // Your solution here.
    DListNode temp = newNode(item, head, head.next);
    head.next.prev = temp;
    head.next = temp;
    size++;
  }

  /**
   *  insertBack() inserts an item at the back of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertBack(Object item) {
    // Your solution here.
    DListNode temp = newNode(item, head.prev, head);
    head.prev.next = temp;
    head.prev = temp;
    size++;
  }

  /**
   *  front() returns the node at the front of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the front of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode front() {
    // Your solution here.
    if (size == 0) {
        return null;
    } else {
        return head.next;
    }
  }

  /**
   *  back() returns the node at the back of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the back of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode back() {
    // Your solution here.
    if (size == 0) {
        return null;
    } else {
        return head.prev;
    }
  }

  /**
   *  next() returns the node following "node" in this DList.  If "node" is
   *  null, or "node" is the last node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose successor is sought.
   *  @return the node following "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode next(DListNode node) {
    // Your solution here.
    if (node == null || node == head.prev) {
        return null;
    } else {
        return node.next;
    }
  }

  /**
   *  prev() returns the node prior to "node" in this DList.  If "node" is
   *  null, or "node" is the first node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node prior to "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode prev(DListNode node) {
    // Your solution here.
    if (node == null || node == head.next) {
        return null;
    } else {
        return node.prev;
    }
  }

  /**
   *  insertAfter() inserts an item in this DList immediately following "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item after.
   *  Performance:  runs in O(1) time.
   */
  public void insertAfter(Object item, DListNode node) {
    // Your solution here.
    if (node == null) {
        //do nothing;
    } else {
        DListNode temp = newNode(item, node, node.next);
        node.next.prev = temp;
        node.next = temp;
        size++;
    }
  }

  /**
   *  insertBefore() inserts an item in this DList immediately before "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item before.
   *  Performance:  runs in O(1) time.
   */
  public void insertBefore(Object item, DListNode node) {
    // Your solution here.
    if (node == null) {
        //do nothing;
    } else {
        DListNode temp = newNode(item, node.prev, node);
        node.prev.next = temp;
        node.prev = temp;
        size++;
    }
  }

  /**
   *  remove() removes "node" from this DList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(DListNode node) {
    // Your solution here.
    if (node == null) {
        //do nothing;
    } else {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        size--;
    }
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }
  public static void main(String[] args) {
      System.out.println("testing");
      DList lst = new DList();
      System.out.println("length :"+lst.length());
      System.out.println("is empty? "+lst.isEmpty());
      System.out.println("Testing empty: " + lst);
      lst.insertFront(3);
      System.out.println("Testing insert front"+lst);
      lst.insertBack(55.55);
      System.out.println("Testing insert back"+ lst);
      lst.insertFront(1000);
      System.out.println("Testing insert front with 1000"+lst);
      System.out.println("Testing front "+lst.front().item);
      System.out.println("Testing back() "+lst.back().item);
      // get node with 3
      System.out.println("Testing next(), should be 3: "+lst.next(lst.head.next).item);
      DListNode midNode=lst.next(lst.head.next);
      // test insertAfter and insertBefore
      lst.insertBefore("hello",midNode);
      System.out.println("Testing insertBefore(): "+lst);
      lst.insertAfter("what-ever",midNode);
      System.out.println("Testing insertAfter(): "+lst);
      // print length
      System.out.println("Length :"+lst.length());
      System.out.println("is empty?"+lst.isEmpty());

      // try remove head
      System.out.println("Trying to remove head");
      lst.remove(lst.front());
      System.out.println(lst);

      // try to corrupt the DList
      System.out.println("Corrupting the list");
      DListNode tmp = new DListNode(2,lst.front(),lst.back());
      lst.insertAfter("shit",tmp);
      System.out.println(lst+"length : " +lst.length());

      // testing prev
      System.out.println("Testing prev(), should be hello:"+lst.prev(midNode).item);
      // testing remove
      for(int i=0; i<5;i++) {
          lst.remove(lst.head.prev);
          System.out.println(lst+ "length is "+lst.length());
      }
  }
}

