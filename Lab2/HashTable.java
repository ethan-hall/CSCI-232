package lab2;

//  Ethan Hall
// CSCI 232, Lab2
// 4 March 2015
// *Node class and Tree class are adapted from Lab2 provided materials*

import java.util.LinkedList;

class Node { // tree node
    public int data;
    public Node leftChild;
    public Node rightChild;
}

class Tree { // tree class
    public Node root; // root node
    private LinkedList inOrderList = new LinkedList(); // used for inOrder() tree traverse

    public Tree() { // constructor
        root = null; // empty tree
    }

    public void insert(int data) {
        Node newNode = new Node();
        newNode.data = data;
        if (root == null) { // empty tree
            root = newNode; // new node is root
            root.leftChild = null; // no children
            root.rightChild = null;
        }
        else {
            Node current = root;
            Node parent;
            while(true) {
                parent = current;
                if (data < current.data) { // go left
                    current = current.leftChild;
                    if (current == null) { // if empty leaf node
                        parent.leftChild = newNode; // insert node
                        return;
                    }
                }
                else { // go right
                    current = current.rightChild;
                    if (current == null) { // if empty leaf node
                        parent.rightChild = newNode; // insert node
                        return;
                    }
                }
            }
        }
    }

    // in-order tree traversal, returns linkedlist of ordered values
    public LinkedList inOrder() {
        inOrderList.clear(); // clear list
        inOrderRec(root); // start recursive call
        return inOrderList; // return tree contents
    }
    private void inOrderRec(Node localRoot) { // recursive traverse function
        if (localRoot != null) {
            inOrderRec(localRoot.leftChild);
            inOrderList.add(localRoot.data);
            inOrderRec(localRoot.rightChild);
        }
    }
}

// H(X)=X%arraySize
public class HashTable {
    Tree[] hashTable;

    public HashTable(int tableLength) { // constructor
        hashTable = new Tree[tableLength]; // initialize array
        for (int i = 0; i < hashTable.length; i++) { // initialize trees
            hashTable[i] = new Tree();
        }
    }

    public void insert(int value) { // insert value
        int hashValue = value % hashTable.length; // preform hash function
        if (search(value) == -1) {
            hashTable[hashValue].insert(value); // insert if new value
            System.out.println("'"+value+"'" + " inserted at index " + hashValue + ".");
        }
        else
            System.out.println("'"+value+"'" + " has already been inserted at index " + hashValue + ".");
    }

    // return hash index of value, if value is not found return -1
    public int search(int value) { // search for value
        int hashValue = value % hashTable.length; // preform hash function
        if (hashTable[hashValue].inOrder().contains(value))
            return hashValue; // value found, return hash index
        else
            return -1; // value not found
    }

    public void display() { // print hash table
        for (int i = 0; i < hashTable.length; i++) {
            System.out.print(i + ": "); // print hash table index
            for (int j = 0; j < hashTable[i].inOrder().size(); j++) {
                System.out.print(hashTable[i].inOrder().get(j) + ", "); // print tree contents
            }
            System.out.print("\n"); // next line
        }
    }
}
