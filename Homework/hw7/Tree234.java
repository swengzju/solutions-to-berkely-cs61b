/* Tree234.java */

package dict;

/**
 *  A Tree234 implements an ordered integer dictionary ADT using a 2-3-4 tree.
 *  Only int keys are stored; no object is associated with each key.  Duplicate
 *  keys are not stored in the tree.
 *
 *  @author Jonathan Shewchuk
 **/
public class Tree234 extends IntDictionary {

  /**
   *  (inherited)  size is the number of keys in the dictionary.
   *  root is the root of the 2-3-4 tree.
   *
   *  You may add fields if you wish, but don't change anything that
   *  would prevent toString() or find() from working correctly.
   **/
  Tree234Node root;

  public Tree234() {
    root = null;
    size = 0;
  }

  /**
   *  toString() prints this Tree234 as a String.  Each node is printed
   *  in the form such as (for a 3-key node)
   *
   *      (child1)key1(child2)key2(child3)key3(child4)
   *
   *  where each child is a recursive call to toString, and null children
   *  are printed as a space with no parentheses.  Here's an example.
   *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of the 2-3-4 tree.
   **/
  public String toString() {
    if (root == null) {
      return "";
    } else {
      return root.toString();
    }
  }

  /**
   *  printTree() prints this Tree234 as a tree, albeit sideways.
   *
   *  You're welcome to change this method if you like.  It won't be tested.
   **/
  public void printTree() {
    if (root != null) {
      root.printSubtree(0);
    }
  }

  /**
   *  find() prints true if "key" is in this 2-3-4 tree; false otherwise.
   *
   *  @param key is the key sought.
   *  @return true if "key" is in the tree; false otherwise.
   **/
  public boolean find(int key) {
    Tree234Node node = root;
    while (node != null) {
      if (key < node.key1) {
        node = node.child1;
      } else if (key == node.key1) {
        return true;
      } else if ((node.keys == 1) || (key < node.key2)) { //keys == 1 then key 2 doesn't matter
        node = node.child2;
      } else if (key == node.key2) {
        return true;
      } else if ((node.keys == 2) || (key < node.key3)) {
        node = node.child3;
      } else if (key == node.key3) {
        return true;
      } else {
        node = node.child4;
      }
    }
    return false;
  }

  /**
   *  insert() inserts the key "key" into this 2-3-4 tree.  If "key" is
   *  already present, a duplicate copy is NOT inserted.
   *
   *  @param key is the key sought.
   **/
  public void insert(int key) {
    // Fill in your solution here.
    if (find(key) == true) {
    //do nothing;
    } else if (key <= 0){
    //do nothing;
    } else {
        Tree234Node node = null;
        Tree234Node leftnode = null;
        Tree234Node rightnode = null;
        if (root == null) {
            root = new Tree234Node(null, key);
            return;
        }
        if (root.keys == 3) {
            Tree234Node newroot = new Tree234Node(null, root.key2); // new root;
            leftnode = new Tree234Node(newroot, root.key1);
            rightnode = new Tree234Node(newroot, root.key3);
            leftnode.child1 = root.child1;
            leftnode.child2 = root.child2;
            leftnode.child3 = null;
            leftnode.child4 = null;
            leftnode.keys = 1;
            if (leftnode.child1 != null) {
                leftnode.child1.parent = leftnode;
            }   
            if (leftnode.child2 != null) {
                leftnode.child2.parent = leftnode;
            }   
            rightnode.child1 = root.child3;
            rightnode.child2 = root.child4;
            rightnode.child3 = null;
            rightnode.child4 = null;
            rightnode.keys = 1;
            if (rightnode.child1 != null) {
                rightnode.child1.parent = rightnode;
            }   
            if (rightnode.child2 != null) {
                rightnode.child2.parent = rightnode;
            }   
            root.child3 = null;
            root.child4 = null;
            newroot.keys = 1;
            newroot.child1 = leftnode;
            newroot.child2 = rightnode;
            root = newroot;
        }
        node = root;
        Tree234Node tmp = null;
        while (node != null) {
            if (node.keys == 3) {
                tmp = node.parent;
                leftnode = new Tree234Node(tmp, node.key1);
                rightnode = new Tree234Node(tmp, node.key3);
                leftnode.child1 = node.child1;
                leftnode.child2 = node.child2;
                leftnode.child3 = null;
                leftnode.child4 = null;
                leftnode.keys = 1;
                if (leftnode.child1 != null) {
                    leftnode.child1.parent = leftnode;
                }   
                if (leftnode.child2 != null) {
                    leftnode.child2.parent = leftnode;
                }   
                rightnode.child1 = node.child3;
                rightnode.child2 = node.child4;
                rightnode.child3 = null;
                rightnode.child4 = null;
                rightnode.keys = 1;
                if (rightnode.child1 != null) {
                    rightnode.child1.parent = rightnode;
                }   
                if (rightnode.child2 != null) {
                    rightnode.child2.parent = rightnode;
                }   
                if (tmp.keys == 1) {
                    if (tmp.key1 > node.key2) {
                        tmp.key2 = tmp.key1;
                        tmp.key1 = node.key2;
                        tmp.child1 = leftnode;
                        tmp.child3 = tmp.child2;
                        tmp.child2 = rightnode;
                    } else {
                        tmp.key2 = node.key2;
                        tmp.child2 = leftnode;
                        tmp.child3 = rightnode;
                    }
                    
                } else if (tmp.keys == 2) {
                    if (tmp.key1 > node.key2) {
                        tmp.key3 = tmp.key2;
                        tmp.key2 = tmp.key1;
                        tmp.key1 = node.key2;
                        tmp.child4 = tmp.child3;
                        tmp.child3 = tmp.child2;
                        tmp.child2 = rightnode;
                        tmp.child1 = leftnode;
                    } else if ((tmp.key1 < node.key2) && (tmp.key2 > node.key2)) {
                        tmp.key3 = tmp.key2;
                        tmp.key2 = node.key2;
                        tmp.child4 = tmp.child3;
                        tmp.child3 = rightnode;
                        tmp.child2 = leftnode;
                    } else {
                        tmp.key3 = node.key2;
                        tmp.child3 = leftnode;
                        tmp.child4 = rightnode;
                    }
                } else {
                    System.out.println("Impossible");
                }
                tmp.keys++;
                node = tmp;
            }
            tmp = node;
            if (key < node.key1) {
                node = node.child1;
            } else if ((node.keys == 1) || (key < node.key2)) {
                node = node.child2;
            } else if ((node.keys == 2) || (key < node.key3)) {
                node = node.child3;
            } else {
                node = node.child4;
            }
        }
        if (tmp.keys == 1) {
            if (key < tmp.key1) {
                tmp.key2 = tmp.key1;
                tmp.key1 = key;
            } else {
                tmp.key2 = key;
            }
        } else if (tmp.keys == 2) {
            if (key < tmp.key1) {
                tmp.key3 = tmp.key2;   
                tmp.key2 = tmp.key1;
                tmp.key1 = key;
            } else if (key < tmp.key2) {
                tmp.key3 = tmp.key2;
                tmp.key2 = key;
            } else {
                tmp.key3 = key;
            }
        } else {
            System.out.println("Impossible");
        }
        tmp.keys++;
        size++;
    }
  }

  /**
   *  testHelper() prints the String representation of this tree, then
   *  compares it with the expected String, and prints an error message if
   *  the two are not equal.
   *
   *  @param correctString is what the tree should look like.
   **/
  public void testHelper(String correctString) {
    String treeString = toString();
    System.out.println(treeString);
    if (!treeString.equals(correctString)) {
      System.out.println("ERROR:  Should be " + correctString);
    }
  }

  /**
   *  main() is a bunch of test code.  Feel free to add test code of your own;
   *  this code won't be tested or graded.
   **/
  public static void main(String[] args) {
    Tree234 t = new Tree234();

    System.out.println("\nInserting 84.");
    t.insert(84);
    t.testHelper("84");

    System.out.println("\nInserting 7.");
    t.insert(7);
    t.testHelper("7 84");

    System.out.println("\nInserting 22.");
    t.insert(22);
    t.testHelper("7 22 84");

    System.out.println("\nInserting 95.");
    t.insert(95);
    t.testHelper("(7)22(84 95)");

    System.out.println("\nInserting 50.");
    t.insert(50);
    t.testHelper("(7)22(50 84 95)");

    System.out.println("\nInserting 11.");
    t.insert(11);
    t.testHelper("(7 11)22(50 84 95)");

    System.out.println("\nInserting 37.");
    t.insert(37);
    t.testHelper("(7 11)22(37 50)84(95)");

    System.out.println("\nInserting 60.");
    t.insert(60);
    t.testHelper("(7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 1.");
    t.insert(1);
    t.testHelper("(1 7 11)22(37 50 60)84(95)");

    System.out.println("\nInserting 23.");
    t.insert(23);
    t.testHelper("(1 7 11)22(23 37)50(60)84(95)");

    System.out.println("\nInserting 16.");
    t.insert(16);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95))");

    System.out.println("\nInserting 100.");
    t.insert(100);
    t.testHelper("((1)7(11 16)22(23 37))50((60)84(95 100))");

    System.out.println("\nInserting 28.");
    t.insert(28);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(95 100))");

    System.out.println("\nInserting 86.");
    t.insert(86);
    t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(86 95 100))");

    System.out.println("\nInserting 49.");
    t.insert(49);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))");

    System.out.println("\nInserting 81.");
    t.insert(81);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((60 81)84(86 95 100))");

    System.out.println("\nInserting 51.");
    t.insert(51);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86 95 100))");

    System.out.println("\nInserting 99.");
    t.insert(99);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86)95(99 100))");

    System.out.println("\nInserting 75.");
    t.insert(75);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(75 81)84(86)95" +
                 "(99 100))");

    System.out.println("\nInserting 66.");
    t.insert(66);
    t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(66 75 81))84((86)95" +
                 "(99 100))");

    System.out.println("\nInserting 4.");
    t.insert(4);
    t.testHelper("((1 4)7(11 16))22((23)28(37 49))50((51)60(66 75 81))84" +
                 "((86)95(99 100))");

    System.out.println("\nInserting 80.");
    t.insert(80);
    t.testHelper("(((1 4)7(11 16))22((23)28(37 49)))50(((51)60(66)75" +
                 "(80 81))84((86)95(99 100)))");

    System.out.println("\nFinal tree:");
    t.printTree();
  }

}
